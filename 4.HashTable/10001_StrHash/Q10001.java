import java.io.*;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

class Q10001 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = 0; // string size
        int K = 0; // hash
        int M = 0; // modulo divisor
        int answer = 0;

        while (true) {
            //
            String line = br.readLine();
            if ((line == null) || (line.isEmpty()))
                break;
            StringTokenizer stk = new StringTokenizer(line);

            N = Integer.parseInt(stk.nextToken());
            K = Integer.parseInt(stk.nextToken());
            M = Integer.parseInt(stk.nextToken());

            answer = solution(N, K, M);

            bw.write(answer + "\n");
            bw.flush();
        }
        br.close();
        bw.close();
    }

    private static int solution(int N, int K, int M) {
        // ERROR: time out!!
        // initialize tables
        int tableSize = (int) Math.pow(2, M);
        HashMap<Integer, Integer> oldTable = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> newTable = new HashMap<Integer, Integer>();
        for (int i = 1; i <= 26; i++) {
            oldTable.put(i, 1);
        }

        int newHash = 0;
        int cnt = 0;
        for (int i = 0; i < N - 1; i++) {
            Iterator<Integer> keys = oldTable.keySet().iterator();
            while (keys.hasNext()) {
                int oldHash = keys.next();
                for (int k = 1; k <= 26; k++) {
                    newHash = ((oldHash * 33) ^ k) % tableSize;
                    cnt = newTable.getOrDefault(newHash, 0) + oldTable.get(oldHash);
                    newTable.put(newHash, cnt);
                }
            }
            oldTable = new HashMap<Integer, Integer>(newTable);
        }

        return oldTable.getOrDefault(K, 0);
    }

    private static int strHash(String A, int M) {
        if (A.isEmpty())
            return 0;
        int n = A.length();
        if (n == 1)
            return (A.charAt(0) - 96);
        int dividend = (strHash(A.substring(0, n - 2), M) * 33) ^ strHash(A.substring(n - 1), M);
        int divisor = (int) Math.pow(2, M);
        return dividend % divisor;
    }
}