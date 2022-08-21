package algorithms;

import java.io.File;
import java.util.LinkedList;

public class DijkstraAlgorithm {
    public static final int INFINITE = 1000000;

    public static void main(String args[]) {
        File f = new File(".\\dijkstraData.txt");
        int[][] integers = ReadText.readIntArrays(f);
        ValueEdgeList el = ValueEdgeList.createValueEdgeList(integers);
        UGraphicLVL g = new UGraphicLVL(el);

        int[] minPathLengths = computeShortestPath(g, 1);

        int[] indices = {7,37,59,82,99,115,133,165,188,197};
        IntArray.output(minPathLengths, indices);
    }

    static int[] computeShortestPath(UGraphicLVL g, int source) {
        int[] min = new int[g.vl.out.length];//index stands for vertexLabel, value stands for shortest length
        int[] vertices = IntArray.increasingInts(g.vl.out.length - 1);//two vertices sets contains vertices' label
        //left part is determined set, right part is undermined set

        //init min
        for(int i = 1; i < min.length; i++) {
            min[i] = INFINITE;
        }
        min[source] = 0;

        //make source added to the determined set
        IntArray.swap(vertices, 0, source - 1);

        int w = source;//the label of vertex just determined
        int undeterminedStart = 1;//the start Index of undetermined set

        int edge;
        int vertex;
        while(undeterminedStart < vertices.length) {
            //update min[]
            for(int i = undeterminedStart; i < vertices.length; i++) {
                vertex = vertices[i];
                if(w < vertices[i]) {
                    edge = g.findEdge(w, vertex);
                } else {
                    edge = g.findEdge(vertex, w);
                }

                if(edge != -1) {
                    min[vertex] = Simple.min(min[vertex], min[w] + g.el.values[edge]);
                }
            }

            //renew w with the next minimum
            w = IntArray.min(min, vertices, undeterminedStart, vertices.length);

            //move w to determined set
            IntArray.swap(vertices, undeterminedStart, IntArray.find(vertices, w));
            undeterminedStart++;
        }

        return min;
    }
}
