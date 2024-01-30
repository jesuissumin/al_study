import java.util.Scanner;

class Solution{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        String[] S = new String[N];
        for (int i=0; i<N;i++){
            S[i] = sc.nextLine();
        }

        // binary Tree search

        int Answer = 0;
        for (int i=0; i<M;i++){
            // check prefix
            Answer++;
        }

        System.out.println(Answer);
    }

    class Node{
        public Node(){

        }
    }

    class Tree{
        public Tree(){
            
        }
    }
}