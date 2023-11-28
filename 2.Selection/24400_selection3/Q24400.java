import java.io.*;
import java.util.StringTokenizer;

class Q24400 {
    static final int MAX_N = 10000;
    static int seqLen;
    static int[] A = new int[MAX_N];
    static int[] B = new int[MAX_N];
    static int toFind;
    static int isSame;

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
            toFind = Integer.parseInt(stk.nextToken());
            isSame = 0;

            stk = new StringTokenizer(br.readLine());
            for (int i=0; i < seqLen; i++){
                A[i] = Integer.parseInt(stk.nextToken());
            }
            
            stk = new StringTokenizer(br.readLine());
            for (int i=0; i < seqLen; i++){
                B[i] = Integer.parseInt(stk.nextToken());
            }

            select(A, 0, seqLen-1, toFind);

            bw.write(isSame + "\n");
            bw.flush();
        }
        br.close();
        bw.close();
    }

    private static int select(int[] A, int p, int r, int q){
        if (p==r) return A[p];
        int t = partition(A, p, r);
        int k = t - p + 1;
        if (q<k) return select(A, p, t-1, q);
        else if (q==k) return A[t];
        else return select(A, t+1, r, q-k-1);
    }

    private static int partition(int[] A, int p, int r){
        int x = A[r];
        int i = p-1;
        for (int j=p; j <= r-1; j++){
            if (A[j]<=x) {
                i++;
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
                System.out.printf("swap i:%d (%d) <--> j: %d (%d)\n", i,A[i],j,A[j]);
                checkSame(A);
            }
        }
        if (i+1 != r) {
            int tmp = A[i+1];
            A[i+1] = A[r];
            A[r] = tmp;
            System.out.printf("swap i+1:%d (%d) <--> r: %d (%d)\n", i+1,A[i+1],r,A[r]);
            checkSame(A);
        }
        return i+1;
    }

    private static void checkSame(int[] A){
        for (int i=0; i<seqLen; i++){
            if (A[i]!=B[i]) {
                String a = "";
                for (int j=0; j<seqLen; j++){
                    a += A[j];
                }
                System.out.println("==A: "+a);
                a = "";
                for (int j=0; j<seqLen; j++){
                    a += B[j];
                }
                System.out.println("  B: "+a);
                return;
            } 
        }
        isSame = 1;
    }

}
