import java.io.*;
import java.util.StringTokenizer;

class Q23881{
    static final int MAX_N = 10000;
    static int seqLen;
    static int[] A = new int[MAX_N];
    static int checkSwap;
    static String answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
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

            // selection sort
            int largest = 0;
            int largestID = 0;
            int swapCount = 0;
            for (int i=seqLen; i>1; i--) {
                largest = 0;
                largestID = 0;
                for (int j=0; j<i-1; j++){
                    if (largest < A[j]) {
                        largest = A[j];
                        largestID = j;
                    }
                }
                if (A[i-1]<largest){
                    swapCount = swapCount + 1;
                    A[largestID] = A[i-1];
                    A[i-1] = largest;
                    if (swapCount==checkSwap){
                        answer = String.format("%d %d", A[largestID], A[i-1]);
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