import java.io.*;
import java.util.StringTokenizer;

class Q24090{
    static final int MAX_N = 10000;
    static int seqLen;
    static int[] A = new int[MAX_N];
    static int checkSwap;
    static String answer;
    static int swapCount;

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
            swapCount = 0;

            stk = new StringTokenizer(br.readLine());
            for (int i=0; i< seqLen; i++){
                A[i] = Integer.parseInt(stk.nextToken());
            }

            // quick sort
            quick_sort(A,0,seqLen-1);
            if (swapCount < checkSwap) answer = "-1";

            bw.write(answer + "\n");
            bw.flush();
        }
        br.close();
        bw.close();

    }
    private static void quick_sort(int[] A, int p, int r) {
        if (p<r) {
            int q = partition(A, p, r);
            quick_sort(A, p, q-1);
            quick_sort(A, q+1, r);
        }
    }
    private static int partition(int[] A, int p, int r) {
        int x = A[r];
        int i = p-1;
        int tmp = 0;
        for (int j=p; j<r; j++) {
            if (A[j]<=x) {
                i = i+1;
                tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
                swapCount = swapCount + 1;
                if (swapCount==checkSwap){
                    answer = String.format("%d %d",A[i], A[j]);
                }
            }
        }
        if (i+1!=r) {
            tmp = A[i+1];
            A[i+1] = A[r];
            A[r] = tmp;
            swapCount = swapCount + 1;
            if (swapCount==checkSwap){
                answer = String.format("%d %d",A[i+1], A[r]);
            }
        }
        return i+1;
    }
}