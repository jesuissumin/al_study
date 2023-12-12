import java.io.*;

public class RedBlackTree{
    // red-black tree rules
    // 1. The root is black.
    // 2. All leaves (null) are black .
    // 3. If a node is red, the children of the node must be black.
    // 4. The number of black nodes in the path from the root to any leaf is same.
    private Node root;

    public RedBlackTree(){
        this.root = null;
    }

    public Node treeSearch(Node t, Integer x){
        // this is same to the Binary Search Tree.
        if ( t==null || t.getItem()==x) return t;
        if (x<t.getItem()) {
            return treeSearch(t.getLeft(), x);
        } else {
            return treeSearch(t.getRight(), x);
        }
    }

    public void treeInsert(Node root, Integer obj){
        if (root == null){
            // initialize
            root = new Node(obj, 0);
            return;
        }
        Node newNode = new Node(obj, 1);
        if (obj < root.getItem()){
            if (root.getLeft()==null){
                root.setLeft(newNode);
                newNode.setParent(root);
            } else {
                treeInsert(root.getLeft(), obj);
                return;
            } 
        } else {
            if (root.getRight()==null){
                root.setRight(newNode);
                newNode.setParent(root);
            } else {
                treeInsert(root.getRight(), obj);
                return;
            }
        }
        
        resolveInsertConflict(newNode);
    }

    public void treeDelete(Node root, Integer obj){
        // same as Binary Search Tree
        Node r = treeSearch(root, r);
        if (r.getLeft()==null && r.getRight()==null) {
            if (r.getItem()<r.getParent().getItem()){
                t.getParent().setLeft(null);
            } else {
                r.getParent().setRight(null);
            }
        }
        else if (r.getLeft()!=null & r.getRight()!=null) {
            Node minimum = findLeastNode(r.getRight());
            minimum.getParent().setLeft(null);
            r.setItem(minimum.getItem());
            if (minimum.getRight()!=null){
                minimum.getParent().setLeft(minimum.getRight());
                minimum.getRight().setParent(minimum.getParent());
            }
            r = minimum;
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

        resolveDeleteConflict(r);
    }

    public void resolveInsertConflict(Node x){
        // if the parent is red, the conflicts between p(red) and new(red) need to be modified.
        if (x.getParent().getColor()==0){
            return;
        }

        Node p = x.getParent();
        Node s = new Node(0,0);
        if (p.getParent().getLeft()==root){
            s = p.getRight();
        } else {
            s = p.getLeft();
        }

        // case 1: s (brother of p) is red.
        if (s.getColor()==1){
            s.setColor(0);
            p.getParent().setColor(1);
            if (p.getParent() == this.root){
                p.getParent().setColor(0);
                return;
            }
            if (p.getParent().getParent().getColor()==0){
                return;
            } 
            resolveInsertConflict(p.getParent());
            return;
        }

        // case 2: s (brother of p) is black. 
        // 2-1: new is right child of p.
        if (p.getRight()==x){
            turnLeft(p);
            p = x;
        } 
        // 2-2: new is left child of p. 
        Node pp = p.getParent();
        turnRight(pp);
        int ppColor = pp.getColor();
        pp.setColor(p.getColor());
        p.setColor(ppColor);
    }

    public void turnLeft(Node x){
        //         _ p                      _ p
        //     _(x)_         =====>      _ r _
        //    l    _ r _             _(x)_     rr
        //       rl      rr         l      rl
        Node p = x.getParent();
        Node r = x.getRight();
        Node l = x.getLeft();
        Node rl = r.getLeft();
        Node rr = r.getRight();

        x.setRight(rl);
        rl.setParent(x);
        x.setParent(r);
        r.setLeft(x);
        r.setParent(p);
        p.setLeft(r);
    }

    public void turnRight(Node x){
        //           _ pp                _pp
        //       _ p _               _(x)_ 
        //   _(x)_     s    =====>  r   _ p _
        //  r     l                    l     s
        Node p = x.getParent();
        Node r = x.getRight();
        Node l = x.getLeft();
        Node pp = p.getParent();
        Node s = pp.getRight();

        p.setLeft(l);
        l.setParent(p);
        p.setParent(x);
        x.setRight(p);
        x.setParent(pp);
        if (pp.getLeft().getItem()==p.getItem()){
            pp.setLeft(x);
        } else {
            pp.setRight(x);
        }
    }

    public void resolveDeleteConflict(Node r){
        // (node to remove) r has at most one child.
        if (r.getColor()==1) {
            return;
        }
        if (r.getLeft()!=null && r.getLeft().getColor()==1){
            r.getLeft().setColor(0);
            return;
        }
        if (r.getRight()!=null && r.getRight().getColor()==1){
            r.getRight().setColor(0);
        }
        // When x is black and its child is also black, 
        // deletion of x violates the Red-Black-Tree peroperty.
        
        if (r.getItem() < r.getParent().getItem()){
            // r is the left child.
            Node p = r.getPatent();
            Node x = p.getLeft();
            Node s = p.getRight();
            Node sl = s.getLeft();
            Node sr = s.getRight();

            if (p.getColor()==1){
                // case 1. p is red (x must be black)
                if (l.getColor()==0 && r.getColor()==1){
                    // case 1-1. (l,r) = black, black
                    HelpDeleteConflicts1(r);
                }
                else if (l.getColor()==1 && r.getColor()==0){
                    // case 1-2. (l,r) = red, black
                    HelpDeleteConflicts3(r);
                } 
                else {
                    // case 1-3. (l,r) = (black, red) or (red, red)
                    HelpDeleteConflicts2(r);
                }
            } else {
                // case 2. p is black (x can be both red and black)
                

            }

        }
        else {
            // r is the right child. symmetric to left child case.

        }

    }

    private void HelpDeleteConflicts1(Node x){

    }
    private void HelpDeleteConflicts2(Node x){

    }
    private void HelpDeleteConflicts3(Node x){

    }
    private void HelpDeleteConflicts4(Node x){

    }
    private void HelpDeleteConflicts5(Node x){

    }

}

class Node{
    private Integer item;
    private Node parent;
    private Node left;
    private Node right;
    private int color; // red = 1, black = 0


    public Node (Integer item, int color) {
        this.item = item;
        this.parent = null;
        this.left = null;
        this.right = null;
        this.color = color;
    }

    public void setItem(Integer item){
        this.item = item;
    }
    public int getItem(){
        return this.item;
    }

    public void setColor(int color){
        this.color = color;
    }
    public int getColor(){
        return this.color;
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