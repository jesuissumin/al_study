import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;


class Solution{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] Time = new int[N][2];

        for (int i=0; i<N; i++){
            int st = sc.nextInt();
            int ed = sc.nextInt();
            Time[i][0] = st;
            Time[i][1] = ed;
        }

        Arrays.sort(Time, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b){
                if (a[1]==b[1]){
                    return a[0]-b[0];
                }
                return a[1]-b[1];
            }
        });

        int Answer = 0;
        int endTime = 0;
        for (int i=0; i<N; i++){
            if (Time[i][0] >= endTime){
                Answer ++;
                endTime = Time[i][1];
            }
        }
        System.out.println(Answer);
        sc.close();
    }
}