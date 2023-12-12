import java.io.*;
import java.util.StringTokenizer;

class Q9855 {
    private static int N1 = 0; // size of T1
    private static int N2 = 0; // size of T2
    private static int N = 0; // number of elements to insert
    private static int nCase = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            String line = br.readLine();
            if ((line == null) || (line.isEmpty()))
                break;
            StringTokenizer stk = new StringTokenizer(line);

            N1 = Integer.parseInt(stk.nextToken());
            N2 = Integer.parseInt(stk.nextToken());
            N = Integer.parseInt(stk.nextToken());
            if (N==0) continue; // empty table
            nCase += 1;

            int[] elements = new int[N];

            line = br.readLine();
            stk = new StringTokenizer(line);
            for (int i = 0; i < N; i++) {
                if (stk.hasMoreTokens()) {
                    elements[i] = Integer.parseInt(stk.nextToken());
                } else {
                    line = br.readLine();
                    stk = new StringTokenizer(line);
                    elements[i] = Integer.parseInt(stk.nextToken());
                }
                
            }
            bw.write(String.format("Case %d:\n",nCase));
            String answer = solution(N1, N2, N, elements);

            bw.write(answer);
            bw.flush();
        }

        br.close();
        bw.close();
    }

    private static String solution(int N1, int N2, int N, int[] elements) {
        int[] T1 = new int[N1];
        int[] T2 = new int[N2];
        for (int i = 0; i < N1; i++) {
            T1[i] = -1;
        }
        for (int i = 0; i < N2; i++) {
            T2[i] = -1;
        }

        for (int i = 0; i < N; i++) {
            cuckooInsert(T1, T2, elements[i], 1);
        }

        String output = "";
        output += "Table 1\n";
        for (int i=0; i< N1; i++){
            if (T1[i] != -1 ){
                output += String.format("%d:%d\n", i, T1[i]);
            }
        }
        output += "Table 2\n";
        for (int i=0; i< N2; i++){
            if (T2[i] != -1 ){
                output += String.format("%d:%d\n", i, T2[i]);
            }
        }

        return output;
    }

    private static void cuckooInsert(int[] T1, int[] T2, int newE, int tableIdx) {
        // try tableIdx table first, if failed, recursively call itself
        int hash = 0;
        if (tableIdx == 1) {
            hash = hashF(newE, N1);
            if (T1[hash] == -1) {
                T1[hash] = newE;
                return;
            } else {
                int prevE = T1[hash];
                T1[hash] = newE;
                cuckooInsert(T1, T2, prevE, 2);
            }
        } else {
            hash = hashF(newE, N2);
            if (T2[hash] == -1) {
                T2[hash] = newE;
                return;
            } else {
                int prevE = T2[hash];
                T2[hash] = newE;
                cuckooInsert(T1, T2, prevE, 1);
            }
        }
    }

    private static int hashF(int x, int m) {
        return x % m;
    }
}