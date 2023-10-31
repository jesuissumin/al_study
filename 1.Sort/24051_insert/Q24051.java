import java.io.*;
import java.util.StringTokenizer;

class Q24051{
    static final int MAX_N = 10000;
    static int seqLen;
    static int[] A = new int[MAX_N];
    static int checkSave;
    static int answer;
    static int saveCount;

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

            stk = new StringTokenizer(br.readLine());
            for (int i=0; i< seqLen; i++){
                A[i] = Integer.parseInt(stk.nextToken());
            }

            // insertion sort
            saveCount = 0;
            int loc = 0;
            int newItem = 0;
            for (int i=1; i<seqLen ; i++){
                loc = i-1;
                newItem = A[i];
                if (A[loc]<newItem) continue; // sorted
                while (loc>=0 && A[loc]>newItem){
                    A[loc+1] = A[loc];
                    saveCount = saveCount + 1;
                    if (saveCount==checkSave){
                        answer = A[loc+1];
                    }
                    loc = loc - 1;
                }
                A[loc+1] = newItem;
                saveCount = saveCount + 1;
                if (saveCount==checkSave){
                    answer = A[loc+1];
                }
            }

            if (saveCount < checkSave) answer = -1;

            bw.write(answer + "\n");
            bw.flush();
        }
        br.close();
        bw.close();

    }
}