import java.util.Scanner;
class Solution{

    static String T;
    static String P;
    static int[] pi;
    static int m;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextLine();
        P = sc.nextLine();
        m = P.length();
        int n = T.length();
        int Answer = 0;

        //====================
        // KMP algorithm
        //====================
        preprocessing();
        int i = 1;
        int j = 1;
        while (i<=n) {
            if ((j==0)||(T.charAt(i-1)==P.charAt(j-1))){
                i++; j++;
            } else {
                j = pi[j-1];
            }
            if (j==(m+1)){
                Answer = 1;
                break;
            }
        }

        // print answer
        System.out.println(Answer);
    }

    static void preprocessing(){
        pi = new int[m+1];
        int j = 1;
        int k = 0;
        pi[0] = 0;
        while (j<=m){
            if ((k==0)||(P.charAt(j-1)==P.charAt(k-1))) {
                j++;
                k++;
                pi[j-1] = k;
            } else {
                k = pi[k-1];
            }
        }
    }

}