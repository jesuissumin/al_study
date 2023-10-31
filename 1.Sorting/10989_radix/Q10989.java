import java.io.*;
import java.util.StringTokenizer;

class Q10989{
    static final int MAX_N = 10000000;
    static int seqLen;
    static int[] A = new int[MAX_N];

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

            for (int i=0; i< seqLen; i++){
                stk = new StringTokenizer(br.readLine());
                A[i] = Integer.parseInt(stk.nextToken());
            }

            // radix sort
            radix_sort(A);

            for (int i=0; i<seqLen; i++){
                bw.write(A[i] + "\n");
            }
            bw.flush();
        }
        br.close();
        bw.close();
    }

    private static void radix_sort(int[] A) {
        int max = A[0];
        for (int i=1; i<seqLen; i++) {
            if (A[i]>max) max = A[i];
        }
        int exp = 1;
        while (max/exp>0) {
            counting_sort(A, exp);
            exp *= 10;
        }
    }

    private static void counting_sort(int[] A, int exp) {
        int[] C = new int[10];
        int[] B = new int[seqLen];
        for (int i=0; i<seqLen; i++) {
            C[(A[i]/exp)%10]++;
        }
        for (int i=1; i<10; i++) {
            C[i] += C[i-1];
        }
        for (int i=seqLen-1; i>=0; i--) {
            B[C[(A[i]/exp)%10]-1] = A[i];
            C[(A[i]/exp)%10]--;
        }
        for (int i=0; i<seqLen; i++) {
            A[i] = B[i];
        }
    }
}