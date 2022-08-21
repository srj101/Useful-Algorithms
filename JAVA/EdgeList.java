package algorithms;

public class EdgeList {
    int[] back;//index begin from 1, equal to label. Value 0 means this edge not exist
    int[] forth;

    EdgeList() {}

    public static EdgeList createEdgeList(int[][] points) {//for undirected graph
    //points stands for arrays that all members connect to the the first member
        int sum = 0;
        for(int i = 0; i < points.length; i++) {
            sum += points[i].length;
        }
        sum -= points.length;
        sum /= 2;

        EdgeList r = new EdgeList();
        r.back = new int[sum + 1];
        r.forth = new int[sum + 1];
        int num = 1;
        for(int i = 0; i < points.length; i++) {
            for(int j = 1; j < points[i].length; j++) {
                if(points[i][0] <= points[i][j]) {
                    //In undirected Graphic, edge's head is always less than edge's tail.
                    r.back[num] = points[i][0];
                    r.forth[num] = points[i][j];
                    num++;
                }
            }
        }

        return r;
    }
    EdgeList (int[] back, int[] forth) {
        this.back = new int[back.length + 1];
        this.forth = new int[forth.length + 1];
        System.arraycopy(back, 0, this.back, 1, back.length);
        System.arraycopy(forth, 0, this.forth, 1, forth.length);
    }

    //move All edges from one vertex to a new vertex
    void moveEdges(int from, int to) {
        for(int i = 1; i < back.length; i++) {
            if(back[i] == from) {
                if(to < forth[i]) {
                    back[i] = to;
                } else {
                    back[i] = forth[i];
                    forth[i] = to;
                }
            } else if(forth[i] == from) {
                if(to > back[i]) {
                    forth[i] = to;
                } else {
                    forth[i] = back[i];
                    back[i] = to;
                }
            } else {
                continue;
            }

            //remove the edge on self connection
            if(back[i] == forth[i]) {
                back[i] = 0;
                forth[i] = 0;
            }
        }
    }

    int getAmount() {
        int sum = 0;
        for(int i = 1; i < back.length; i++) {
            if(back[i] != 0)
                sum++;
        }

        return sum;
    }

    int maxVertexLabel() {
        int max = 0;
        for(int i = 1; i < back.length; i++) {
            if(back[i] > max) max = back[i];
            if(forth[i] > max) max = forth[i];
        }

        return max;
    }

    void removeEdge(int edgeLabel) {
        back[edgeLabel] = 0;
        forth[edgeLabel] = 0;
    }

    public static EdgeList clone(EdgeList e) {
        EdgeList r = new EdgeList();
        r.back = new int[e.back.length];
        r.forth = new int[e.forth.length];
        System.arraycopy(e.back, 0, r.back, 0, e.back.length);
        System.arraycopy(e.forth, 0, r.forth, 0, e.forth.length);
        return r;
    }

    public EdgeList clone() {
        return clone(this);
    }
}
