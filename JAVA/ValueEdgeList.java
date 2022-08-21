package algorithms;

public class ValueEdgeList extends EdgeList {
    int[] values;

    ValueEdgeList() {}

    public static ValueEdgeList createValueEdgeList(int[][] points) {//for undirected graph
        //points stands for arrays that all vertices with values connect to the the first member
        int sum = 0;
        for(int i = 0; i < points.length; i++) {
            sum += points[i].length;
        }
        sum -= points.length;
        sum /= 4;

        ValueEdgeList r = new ValueEdgeList();
        r.back = new int[sum + 1];
        r.forth = new int[sum + 1];
        r.values = new int[sum + 1];
        int num = 1;
        for(int i = 0; i < points.length; i++) {
            for(int j = 1; j < points[i].length; j += 2) {
                if(points[i][0] <= points[i][j]) {
                    //In undirected Graphic, edge's head is always less than edge's tail.
                    r.back[num] = points[i][0];
                    r.forth[num] = points[i][j];
                    r.values[num] = points[i][j + 1];
                    num++;
                }
            }
        }

        return r;
    }
}
