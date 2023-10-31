import java.io.*;
import java.util.StringTokenizer;

class Q23968{
    static final int MAX_N = 10000;
    static int seqLen;
    static int[] A = new int[MAX_N];
    static int checkSwap;
    static String answer;

    public static void main(String[] args) throws IOException {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            String line = br.readLine();
            if (line==null) break;
            StringTokenizer stk = new StringTokenizer(line);
            if (!stk.hasMoreTokens()) break;

            seqLen = Integer.parseInt(stk.nextToken());
            checkSwap = Integer.parseInt(stk.nextToken());

            stk = new StringTokenizer(br.readLine());
            for (int i=0; i< seqLen; i++){
                A[i] = Integer.parseInt(stk.nextToken());
            }

            // bobble sort
            int smaller = 0;
            int larger = 0;
            int swapCount = 0;
            for (int i=seqLen; i>1; i--) {
                for (int j=0; j<i-1; j++){
                    if (A[j] > A[j+1]) {
                        smaller = A[j+1];
                        larger = A[j];
                        A[j] = smaller;
                        A[j+1] = larger;
                        swapCount = swapCount + 1;
                        if (swapCount==checkSwap){
                            answer = String.format("%d %d", smaller, larger);
                        }
                    }
                }
            }
            if (swapCount < checkSwap) answer = "-1";

            bw.write(answer + "\n");
            bw.flush();
        }
        br.close();
        bw.close();

    }

}