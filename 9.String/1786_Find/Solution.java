import java.util.Scanner;
import java.util.LinkedList;

class Solution{
    static String T;
    static String P;
    static int[] pi;
    static int[] jump;
    static int m;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextLine();
        P = sc.nextLine();
        m = P.length();
        int n = T.length();
        LinkedList<Integer> Answer = new LinkedList<>();

        //====================
        // KMP algorithm : 시간초과..
        //====================
        // preprocessing();
        // int i = 1;
        // int j = 1;
        // while (i<=n) {
        //     if ((j==0)||(T.charAt(i-1)==P.charAt(j-1))){
        //         i++; j++;
        //     } else {
        //         j = pi[j-1];
        //     }
        //     if (j==(m+1)){
        //         Answer.add(i-m);
        //         j = pi[j-1];
        //     }
        // }

        //====================
        // Boyer-Moore algorithm : 틀렸음...
        //====================
        computeJump();
        int i = 1;
        System.out.println(n +" " + m);
        while (i<=(n-m+1)) {
            int j = m;
            int k = i+m-1;
            while ((j>0)&&(P.charAt(j-1)==T.charAt(k-1))) {
                j--;
                k--;
            }
            if (j==0) {
                Answer.add(i);
            }
            i = i+calcJump(T.charAt(i+m-2));
        }

        // print answer
        System.out.println(Answer.size());
        if (Answer.size()==0) {
            System.out.println("");
        } else {
            String a = Answer.get(0).toString();
            for (i=1; i<Answer.size(); i++) {
                a += String.format(" %d", Answer.get(i));
            }
            System.out.println(a);
        }

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

    static void computeJump(){
        jump = new int[26+26+1]; // capital, small alphabets, and whitespace
        for (int i=0; i<26+26+1; i++){
            jump[i] = m;
        }
        for (int i=0; i<m-1; i++){
            int c = ascii2index((int) P.charAt(i));
            jump[c] = m-i-1;
        }
    }

    static int calcJump(char a){
        int idx = ascii2index((int) a);
        return jump[idx];
    }

    static int ascii2index(int i){
        if (i==32) {
            return 26+26;
        }
        if (i>=65) {
            if (i>=97) {
                // small alphabets
                return i-97+26;
            } else {
                // capital alphabets
                return i-65;
            }
        }
        else {
            return 26+26+1; // error
        }
    }
}