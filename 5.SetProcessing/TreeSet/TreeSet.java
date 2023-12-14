    class TreeSet{
        Node[] nodeList;

        public TreeSet(){
            nodeList = new Node[MAX_N];
        }

        public TreeSet(int n){
            nodeList = new Node[n];
        }

        public void MakeSet(int x){
            Node xNode = new Node(x);
            nodeList[x] = xNode;
        }

        public Node FindSet(Node x){
            if (x==x.p) {
                return x;
            }
            else {
                x.p = FindSet(x.p);
                return x.p;
            }
        }

        public void Union(Node x, Node y){
            Node xp = FindSet(x);
            Node yp = FindSet(y);
            if (xp.rank > y.rank){
                yp.setParent(xp);
            } else {
                xp.setParent(yp);
                if (yp.rank==xp.rank) {
                    yp.rank ++;
                }
            }
        }
    }

    class Node{
        Node p;
        int item;
        int rank;

        public Node(int x){
            this.item = x;
            this.p = this;
            this.rank = 0;
        }

        public void setParent(Node p){
            this.p = p;
        }
    } 

