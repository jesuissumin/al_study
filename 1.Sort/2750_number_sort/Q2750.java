import java.io.*;
import java.util.StringTokenizer;

class Q2750{
    static final int MAX_N = 1000;
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

            // merge sort
            merge_sort(A,0,seqLen-1);

            for (int i=0; i<seqLen; i++){
                bw.write(A[i] + "\n");
            }
            bw.flush();
        }
        br.close();
        bw.close();
    }

    private static void merge_sort(int[] A, int p, int r) {
        if (p<r) {
            int q = (p+r)/2;
            merge_sort(A, p, q);
            merge_sort(A, q+1, r);
            merge(A, p, q, r);
        }
    }

    private static void merge(int[] A, int p, int q, int r) {
        int n1 = q-p+1;
        int n2 = r-q;
        int[] L = new int[n1+1];
        int[] R = new int[n2+1];
        for (int i=0; i<n1; i++) {
            L[i] = A[p+i];
        }
        for (int j=0; j<n2; j++) {
            R[j] = A[q+j+1];
        }
        L[n1] = Integer.MAX_VALUE;
        R[n2] = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;
        for (int k=p; k<=r; k++) {
            if (L[i]<=R[j]) {
                A[k] = L[i];
                i = i+1;
            } else {
                A[k] = R[j];
                j = j+1;
            }
        }
    }
}