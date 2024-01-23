import java.util.Scanner;
import java.util.LinkedList;
import java.util.ArrayList;

class Solution{
    static int[] weight;
    static ArrayList<LinkedList<Integer>> Edges;
    static boolean[] visited;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        weight = new int[N];
        for (int i=0; i<N; i++){
            weight[i] = sc.nextInt();
        }

        Edges = new ArrayList<LinkedList<Integer>>(N);
        visited = new boolean[N];
        // 수민: List<LinkedList<Integer>> Edges = new LinkedList[N]; 로 하니 또 warning이 떠요 ㅠㅠ
        // Integer 대신 사용자 정의 class는 되었는데 왜 이건 안 되는 걸까? 7.Graph/1.DAG 최단 경로/Solution1.java

        // 채점 준비중에서 시간 초과 뜹니다. ㅠㅠ
        for (int i = 0; i < N; i++) {
            Edges.add(new LinkedList<Integer>());
        }

        for (int i=0; i<N-1; i++){
            int a = sc.nextInt()-1;
            int b = sc.nextInt()-1;
            Edges.get(a).add(b);
            Edges.get(b).add(a);
        }

        int Answer = 0;
        int maxIndex = 0;
        for (int i=1; i<N; i++){
            if (weight[maxIndex]<weight[i]){
                maxIndex = i;
            }
        }
        while (weight[maxIndex]>0){
            // find the minimum non-zero weight node
            // O(N)
            int root = maxIndex;
            for (int i=1; i<N; i++){
                if ((weight[root] > weight[i]) && (weight[i]>0)) {
                    root = i;
                }
            }

            // run DFS 
            // O(N+E) = O(2N)
            for (int i=0; i<N; i++){
                visited[i] = false;
            }
            DFS(root);

            // increase the operator number
            Answer ++;

            // find maxIndex
            // O(N)
            for (int i=0; i<N; i++){
                if (weight[maxIndex] < weight[i]) {
                    maxIndex = i;
                }
            }
        }
        System.out.println(Answer);
        sc.close();
    }

    static private void DFS(int root){
        weight[root] = weight[root] - 1;
        visited[root] = true;
        for (Integer i : Edges.get(root)){
            if ((weight[i]>0) && (visited[i] == false)){
                DFS(i);
            }
        }
    }
}