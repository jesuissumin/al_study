import java.io.*;
import java.util.StringTokenizer;

class Q24060 {
    static final int MAX_N = 500000;
    static int seqLen;
    static int[] A = new int[MAX_N+1];
    static int checkSave;
    static int saveCount;
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
            checkSave = Integer.parseInt(stk.nextToken());
            saveCount = 0;s

            stk = new StringTokenizer(br.readLine());
            for (int i=1; i <=seqLen; i++){
                A[i] = Integer.parseInt(stk.nextToken());
            }

            merge_sort(A, 1, seqLen);
            if (saveCount<checkSave) answer = "-1";

            bw.write(answer + "\n");
            bw.flush();
        }
        br.close();
        bw.close();
    }

    private static void merge_sort(int[] A, int p, int r) {
        if (p<r) {
            int q = (int) Math.floor((p+r)/2);
            merge_sort(A, p, q);
            merge_sort(A, q+1, r);
            merge(A, p, q, r);
        }
    }

    private static void merge(int[] A, int p, int q, int r){
        int i = p; int j = q+1; int t = 1;
        int[] tmp = new int[r-p+2];
        while (i <= q && j<=r) {
            if (A[i]<=A[j]) tmp[t++] = A[i++];
            else tmp[t++] = A[j++];
        }
        while (i<=q){
            tmp[t++] = A[i++];
        }
        while (j<=r){
            tmp[t++] = A[j++];
        }
        i = p; t = 1;
        while (i<=r){
            A[i++] = tmp[t++];
            saveCount++;
            // printList(A, i-1);
            if (saveCount==checkSave) answer = Integer.toString(A[i-1]);
        }
    }
    private static void printList(int[] A, int a){
        String str = String.format("%d save: ", saveCount);
        for (int ii=1; ii<=seqLen; ii++) {
            if (ii==a) str = str + String.format("*%d", A[ii]); 
            else str = str + String.format(" %d", A[ii]);
        }
        System.out.println(str);
    }
}