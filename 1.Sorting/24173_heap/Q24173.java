import java.io.*;
import java.util.StringTokenizer;

class Q24173 {
    static final int MAX_N = 10000000;
    static int seqLen;
    static int[] A = new int[MAX_N+1];
    static int checkSwap;
    static int swapCount;
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
            swapCount = 0;

            stk = new StringTokenizer(br.readLine());
            for (int i=1; i <= seqLen; i++){
                A[i] = Integer.parseInt(stk.nextToken());
            }

            heap_sort(A);
            if (swapCount<checkSwap) answer = "-1";

            bw.write(answer + "\n");
            bw.flush();
        }
        br.close();
        bw.close();
    }

    private static void heap_sort(int[] A){
        build_min_heap(A, seqLen);
        for (int i=seqLen; i>1; i--){
            int tmp = A[1];
            A[1] = A[i];
            A[i] = tmp;
            checkAnswer(A[1],A[i]);
            // printList(A, 1, i);
            heapify(A, 1, i-1);
        }
    }

    private static void build_min_heap(int[] A, int ed){
        for (int i= (int) Math.floor(ed/2); i>0;i--){
            heapify(A, i, ed);
        }
    } 

    private static void heapify(int[] A, int root, int n){
        int l = 2*root;
        int r = 2*root+1;
        int smaller = 0;
        if (r <= n) {
            if (A[l]<A[r]) smaller = l;
            else smaller = r;
        }
        else if (l <= n) smaller = l;
        else return;

        if (A[smaller] < A[root]) {
            int tmp = A[root];
            A[root] = A[smaller];
            A[smaller] = tmp;
            checkAnswer(A[root], A[smaller]);
            // printList(A, root, smaller);
            heapify(A, smaller, n);
        }
    }

    private static void checkAnswer(int a, int b){
        swapCount++;
        if (swapCount==checkSwap){
            if (a<b) answer = String.format("%d %d", a, b);
            else answer = String.format("%d %d", b, a);
        }
    }

    private static void printList(int[] A, int a, int b){
        String str = String.format("%d switch: ", swapCount);
        for (int ii=1; ii<=seqLen; ii++) {
            if ((ii==a)||(ii==b)) str = str + String.format("*%d", A[ii]); 
            else str = str + String.format(" %d", A[ii]);
        }
        System.out.println(str);
    }
}