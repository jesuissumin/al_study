import java.util.Scanner;

class Solution{
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        a = 1000 - a;

        int[] coins = {500, 100, 50, 10, 5, 1};

        int Answer = 0;
        for (int i = 0; i<6; i++){
            while (a/coins[i]>0){
                Answer += a/coins[i];
                a = a%coins[i];
            }
        }
        System.out.println(Answer);
        sc.close();
    }
}