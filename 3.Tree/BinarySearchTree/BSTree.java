import java.io.*;

// 입력이 가능하도록 콘솔을 만들고 프린트해서 확인할 수 있어야 함!!! 

class BinarySearchTree{
    private Node root;

    public BinarySearchTree(){
        this.root = null;
    }

    public Node treeSearch(Node t, Integer x){
        if ( t==null || t.getItem()==x) return t;
        if (x<t.getItem()) {
            return treeSearch(t.getLeft(), x);
        } else {
            return treeSearch(t.getRight(), x);
        }
    }

    public Node treeInsert(Node root, Integer obj){
        if (root == null) {
            root = new Node(obj);
            return root;
        }
        if (obj < root.getItem()) {
            if (root.getLeft()==null){
                root.setLeft(new Node(obj));
                return root.getLeft();
            } else {
                return treeInsert(root.getLeft(), obj);
            }
        } else {
            if (root.getRight()==null){
                root.setRight(new Node(obj));
                return root.getRight();
            } else {
                return treeInsert(root.getRight(), obj);
            }
        }
    }

    public void treeDelete(Node t, Integer v){
        Node r = treeSearch(t,v);
        if (r.getLeft()==null && r.getRight()==null) {
            if (r.getItem() < r.getParent().getItem()){
                r.getParent().setLeft(null);
            } else {
                r.getParent().setRight(null);
            }
        } 
        else if (r.getLeft()!=null && r.getRight()!=null) {
            Node minimum = findLeastNode(r.getRight());
            minimum.getParent().setLeft(null);
            r.setItem(minimum.getItem());
            if (minimum.getRight()!=null){
                minimum.getParent().setLeft(minimum.getRight());
                minimum.getRight().setParent(minimum.getParent());
            }
        } 
        else {
            if (r.getLeft()!=null){
                Node child = r.getLeft();  
                child.setParent(r.getParent());
            } else {
                Node child = r.getRight();
                child.setParent(r.getParent());
            }
            if (r.getParent().getLeft()==r){
                r.getParent().setLeft(child);
            } else {
                r.getParent().setRight(child);
            }
        }
    }

    public Node findLeastNode(Node t){
        if (t.getLeft()==null) return t;
        return findLeastNode(t.getLeft());
    }
}

class Node{
    private Integer item;
    private Node parent;
    private Node left;
    private Node right;

    public Node (Integer item) {
        this.item = item;
        this.parent = null;
        this.left = null;
        this.right = null;
    }

    public void setItem(Integer item){
        this.item = item;
    }
    public int getItem(){
        return this.item;
    }

    public void setParent(Node i){
        this.parent = i;
    }
    public Node getParent(){
        return this.parent;
    }
    
    public void setLeft(Node i){
        this.left = i;
    }
    public Node getLeft(){
        return this.left;
    }

    public void setRight(Node i){
        this.right = i;
    }
    public Node getRight(){
        return this.right;
    }
}