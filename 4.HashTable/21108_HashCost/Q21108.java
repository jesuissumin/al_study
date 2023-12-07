import java.io.*;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

class Q21108 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = 0; // number of test cases
        int A = 0; // number of elements from 0 to A-1
        int B = 0; // divisor of the mod operation
        int answer = 0;

        String line = br.readLine();
        StringTokenizer stk = new StringTokenizer(line);

        N = Integer.parseInt(stk.nextToken());

        for (int i = 0; i < N; i++) {
            line = br.readLine();
            stk = new StringTokenizer(line);
            A = Integer.parseInt(stk.nextToken());
            B = Integer.parseInt(stk.nextToken());
            answer = solution(A, B);
            bw.write(answer + "\n");
            bw.flush();
        }

        br.close();
        bw.close();
    }

    private static int solution1(int A, int B) {
        int cost = 0;
        int hash = 0;
        Map<Integer, Integer> HashTable = new HashMap<Integer, Integer>();
        for (int i = 0; i < A; i++) {
            hash = hashF(i, B);
            cost += HashTable.getOrDefault(hash, 0);
            HashTable.put(hash, HashTable.getOrDefault(hash, 0) + 1);
        }
        return cost;
    }

    private static int solution2(int A, int B) {
        int cost = 0;
        int hash = 0;
        int[] HashTable = new int[B];
        for (int i = 0; i < A; i++) {
            hash = hashF(i, B);
            cost += HashTable[hash];
            HashTable[hash] += 1;
        }
        return cost;
    }

    private static int hashF(int x, int m) {
        return (x * x) % m;
    }
}