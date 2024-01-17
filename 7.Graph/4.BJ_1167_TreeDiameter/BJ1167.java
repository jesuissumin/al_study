import java.util.StringTokenizer;
import java.io.*;
import java.util.LinkedList;

class BJ1167 {
    static final int MAX_N = 100000;
    static final int MAX_W = 10000;

    static int N;
    static int Answer;
    static LinkedList<Edge>[] Vertexes = new LinkedList[MAX_N + 1];
    static int[] dist = new int[MAX_N + 1];
    static int[] prev = new int[MAX_N + 1];

    public static void main(String[] args) throws IOException {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            String line = br.readLine();
            if (line == null)
                break;
            StringTokenizer stk = new StringTokenizer(line);
            if (!stk.hasMoreTokens())
                break; // End of input

            N = Integer.parseInt(stk.nextToken());

            for (int i = 1; i <= N; i++) {
                Vertexes[i] = new LinkedList<>();
            }

            // 입력 읽기, 수행시간 : O(E)
            for (int u = 1; u <= N; u++) {
                stk = new StringTokenizer(br.readLine());
                int U = Integer.parseInt(stk.nextToken());
                while (stk.hasMoreTokens()) {
                    int V = Integer.parseInt(stk.nextToken());
                    if (V == -1)
                        break;
                    int W = Integer.parseInt(stk.nextToken());
                    Vertexes[U].add(new Edge(V, W));
                }
            }

            // DFS 2번 진행하여 가장 먼 경로의 leaf에 도달
            for (int i = 1; i <= N; i++) {
                dist[i] = 0;
                prev[i] = 0;
            }
            prev[1] = 1;
            DFS(1);
            int max_node = 1;
            for (int i = 1; i <= N; i++) {
                if (dist[i] > dist[max_node])
                    max_node = i;
            }

            // Reset dist array and perform DFS from the farthest leaf
            for (int i = 1; i <= N; i++) {
                dist[i] = 0;
                prev[i] = 0;
            }
            prev[max_node] = max_node;
            DFS(max_node);
            for (int i = 1; i <= N; i++) {
                Answer = Math.max(dist[i], Answer);
            }

            bw.write(Integer.toString(Answer));
            bw.newLine();
            bw.flush();
        }
        br.close();
        bw.close();
    }

    static void DFS(int i) {
        for (Edge e : Vertexes[i]) {
            int v = e.v;
            int w = e.w;
            if (prev[i] != v) {
                prev[v] = i;
                dist[v] = dist[i] + w;
                DFS(v);
            }
        }
    }
}

class Edge {
    public int v;
    public int w;

    public Edge(int V, int W) {
        v = V;
        w = W;
    }
}
