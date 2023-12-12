import java.io.*;
import java.util.StringTokenizer;

class Q1539{
    // 시간초과 엉엉 ㅠㅠㅠ
    static final int MAX_N = 250000;

    public static void main(String[] args)  throws IOException {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            String line = br.readLine();
            if (line==null || line.isEmpty()) break;

            StringTokenizer stk = new StringTokenizer(line);        
            int seqLen = Integer.parseInt(stk.nextToken());

            BinarySearchTree tree = new BinarySearchTree();

            for (int i=0; i< seqLen; i++){
                stk = new StringTokenizer(br.readLine());
                tree.insert(tree.getRoot(), Integer.parseInt(stk.nextToken()));
            }

            bw.write(tree.sumIPL() + "\n");
            bw.flush();
        }
        br.close();
        bw.close();
    }

    private static class BinarySearchTree{
        private Node root;
        private Integer IPL;

        public BinarySearchTree(){
            this.root = null;
        }

        public Node getRoot(){
            return this.root;
        }

        public void insert(Node root, int obj){
            if (root == null){
                this.root = new Node(obj);
                return;
            }
            if (obj < root.getItem()) {
                if (root.getLeft()==null){
                    root.setLeft(new Node(obj));
                } else {
                    insert(root.getLeft(), obj);
                }
            } else {
                if (root.getRight()==null){
                    root.setRight(new Node(obj));
                } else {
                    insert(root.getRight(), obj);
                }
            }
        }

        public Integer sumIPL(){
            this.IPL = 0; // internal path length
            DFS(this.root, 1);
            return this.IPL;
        }

        public void DFS(Node root, Integer height){
            this.IPL += height;
            if (root.getLeft()!=null){
                DFS(root.getLeft(), height+1);
            }
            if (root.getRight()!=null){
                DFS(root.getRight(), height+1);
            }
        }
    }

    private static class Node{
        private int item;
        private Node parent;
        private Node right;
        private Node left;

        public Node(int item) {
            this.item = item;
            this.parent = null;
            this.right = null;
            this.left = null;
        }

        public int getItem() {
            return this.item;
        }
        
        public void setRight(Node right){
            this.right = right;
        }

        public Node getRight(){
            return this.right;
        }        
        
        public void setLeft(Node left){
            this.left = left;
        }
        
        public Node getLeft(){
            return this.left;
        }

    }
}

