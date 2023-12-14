import java.io.*;
import java.util.StringTokenizer;

class Q2669{
    static int MAX_N;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] box = new int[4][4];
        MAX_N = 99*99;
        int answer;

        for (int i=0; i<4; i++){
            String line = br.readLine();
            StringTokenizer stk = new StringTokenizer(line);
            for (int j=0; j<4; j++){
                box[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        //answer = solutionUsingHash(box);
        answer = solutionUsingCompare(box);
        System.out.println(answer);
        
        br.close();
    }

    public static int solutionUsingCompare(int[][] box){
        int answer = 0;
        int nBox = box.length;

        for (int i=0; i<nBox;i++){
            // [x1, y1, x2, y2] (lower left, upper right)
            int dx = box[i][2]-box[i][0];
            int dy = box[i][3]-box[i][1];
            answer += dx*dy;
            System.out.println(String.format("new added %d:%d", i, dx*dy));
            for (int j=0; j<i; j++){
                boolean inxrange1 = (box[j][0]<box[i][0] && box[i][0]<box[j][2]);
                boolean inyrange1 = (box[j][1]<box[i][1] && box[i][1]<box[j][3]);
                boolean inxrange2 = (box[j][0]<box[i][2] && box[i][2]<box[j][2]);
                boolean inyrange2 = (box[j][1]<box[i][3] && box[i][3]<box[j][3]);
                dx = 0;
                dy = 0;
                if (inxrange1 && inyrange1){
                    if (inxrange2 && inyrange2) {
                        dx = box[i][2]-box[i][0];
                        dy = box[i][3]-box[i][1];
                    } 
                    else if (!inxrange2 && !inyrange2) {
                        dx = box[j][2]-box[i][0];
                        dy = box[j][3]-box[i][1];
                    }
                    else if (inxrange2){
                        dx = box[i][2]-box[i][0];
                        dy = box[j][3]-box[i][1];
                    } else {
                        dx = box[j][2]-box[i][0];
                        dy = box[i][3]-box[i][1];
                    }
                }
                else if (inxrange2 && inyrange2){
                    if (!inxrange1 && !inyrange1) {
                        dx = box[i][2]-box[j][0];
                        dy = box[i][3]-box[j][1];
                    }
                    else if (inxrange1){
                        dx = box[i][2]-box[i][0];
                        dy = box[i][3]-box[j][1];
                    } else {
                        dx = box[i][2]-box[j][0];
                        dy = box[i][3]-box[i][1];
                    }
                } 
                answer += -dx*dy;
                System.out.println(String.format("overlap (x1:%b,y1:%b,x2:%b,y2:%b) %d: %d", inxrange1, inyrange1,inxrange2, inyrange2, j, dx*dy));
            }
        }
        return answer;
    }

    public static int solutionUsingHash(int[][] box){
        int answer = 0;
        int nBox = box.length;
        int[] s = new int[MAX_N];
        for (int i=0; i<nBox; i++){
            for (int x=0; x<(box[i][2]-box[i][0]); x++){
                for (int y=0; y<(box[i][3]-box[i][1]); y++){
                    int idx = (x+box[i][0]-1)*99 + (y+box[i][1]-1);
                    if (s[idx]==0) {
                        s[idx]=1;
                        answer += 1;
                    }
                }
            }
        }
        return answer;
    }
}