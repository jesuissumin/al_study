import java.io.*;
import java.util.StringTokenizer;

class Q2669{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[][] box = new int[4][4];
        final int MAX_N = 10000;
        int answer;

        for (int i=0; i<4; i++){
            String line = br.readLine();
            StringTokenizer stk = new StringTokenizer(line);
            for (int j=0; j<4; j++){
                box[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        //answer = solutionUsingSet(box);
        answer = solutionUsingCompare(box);
        bw.write(answer);
        bw.flush();

        br.close();
        bw.close();
    }

    public static int solutionUsingCompare(int[][] box){
        int answer = 0;

        for (int i=0; i<4;i++){
            // [x1, y1, x2, y2] (lower left, upper right)
            int dx = box[i][2]-box[i][0];
            int dy = box[i][3]-box[i][1];
            answer += dx*dy;
            for (int j=0; j<i; j++){
                boolean inxrange1 = (box[j][0]<box[i][0] && box[i][0]<box[j][2]);
                boolean inyrange1 = (box[j][1]<box[i][1] && box[j][1]<box[i][3]);
                boolean inxrange2 = (box[j][0]<box[i][0] && box[i][0]<box[j][2]);
                boolean inyrange2 = (box[j][1]<box[i][1] && box[j][1]<box[i][3]);
                if (inxrange1 && inyrange1){
                    if (inxrange2 && inyrange2) {
                        dx = box[i][2]-box[i][0];
                        dy = box[i][3]-box[i][1];
                        answer -= dx*dy;
                    } 
                    else if (!inxrange2 && !inyrange2) {
                        dx = box[j][2]-box[i][0];
                        dy = box[j][3]-box[i][1];
                        answer -= dx*dy;
                    }
                    else if (inxrange2){
                        dx = box[i][2]-box[i][0];
                        dy = box[j][3]-box[i][1];
                        answer -= dx*dy;
                    } else {
                        dx = box[j][2]-box[i][0];
                        dy = box[i][3]-box[i][1];
                        answer -= dx*dy;
                    }
                }
                else if (inxrange2 && inyrange2){
                    if (!inxrange1 && !inyrange1) {
                        dx = box[i][2]-box[j][0];
                        dy = box[i][3]-box[j][1];
                        answer -= dx*dy;
                    }
                    else if (inxrange1){
                        dx = box[i][2]-box[i][0];
                        dy = box[i][3]-box[j][1];
                        answer -= dx*dy;
                    } else {
                        dx = box[i][2]-box[j][0];
                        dy = box[i][3]-box[i][1];
                        answer -= dx*dy;
                    }
                }
            }
        }
        return answer;
    }

    // class TreeSet{
    //     Node[] nodeList;

    //     public TreeSet(){
    //         nodeList = new Node[MAX_N];
    //     }

    //     public TreeSet(int n){
    //         nodeList = new Node[n];
    //     }

    //     public void MakeSet(int x){
    //         Node xNode = new Node(x);
    //         nodeList[x] = xNode;
    //     }

    //     public Node FindSet(Node x){
    //         if (x==x.p) {
    //             return x;
    //         }
    //         else {
    //             x.p = FindSet(x.p);
    //             return x.p;
    //         }
    //     }

    //     public void Union(Node x, Node y){
    //         Node xp = FindSet(x);
    //         Node yp = FindSet(y);
    //         if (xp.rank > y.rank){
    //             yp.setParent(xp);
    //         } else {
    //             xp.setParent(yp);
    //             if (yp.rank==xp.rank) {
    //                 yp.rank ++;
    //             }
    //         }
    //     }
    // }

    // class Node{
    //     Node p;
    //     int item;
    //     int rank;

    //     public Node(int x){
    //         this.item = x;
    //         this.p = this;
    //         this.rank = 0;
    //     }

    //     public void setParent(Node p){
    //         this.p = p;
    //     }
    // } 

}