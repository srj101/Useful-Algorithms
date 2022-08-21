package algorithms;

import java.io.File;
import java.util.*;

//Kosaraju’s Two-Pass Algorithm to Compute Strong Connected Components
public class StrongConnectedComponents {
    public static void main(String args[]) {
        File f = new File(".\\SCC.txt");
        //get data from file
        int[][] integers = ReadText.readIntArrays(f);
        int[] back = new int[integers.length];
        int[] forth = new int[integers.length];
        for(int i = 0; i < integers.length; i++) {
            back[i] = integers[i][0];
            forth[i] = integers[i][1];
        }

        //init g
        EdgeList el = new EdgeList(back, forth);
        GraphicLL g = new GraphicLL(el);

        //Count Strong Connected Components' sizes, and output them.
        int[] countSCCs = countSCCs_Eff(g);
        //QuickSort.sort_Eff(countSCCs);
        BigTopHeap.sort(countSCCs);

        IntArray.output(countSCCs, 50);
    }

    static int[] countSCCs_Eff(GraphicLL g) {
        class FinishTime extends DFSProcessorDG {
            int[] finishTime = new int[g.vl.out.length];
            int currentTime = 1;
            public void afterPop(int vertexLabel, UGraphicLL graphic) {
                //take down the finishTime after the successive inspection of the vertex
                finishTime[vertexLabel] = currentTime++;
            }
        }

        //calculate finishTime of the vertices, finishTime determine the order of next dfsTraversal
        FinishTime ft = new FinishTime();
        g.dfsTraversal_Eff(ft);
        int[] temp = new int[ft.finishTime.length - 1];
        System.arraycopy(IntArray.indexValueSwap(ft.finishTime), 1, temp, 0, temp.length);
        int[] order = IntArray.reverse(temp);

        class FindSCCs extends DFSProcessorDG {
            int currentLeader = order[0];
            int currentSize = 0;
            int[] leader = new int[g.vl.out.length];//leader means the first vertex visited of one SCC
            LinkedList<Integer> sccSize = new LinkedList<>();
            public void init(int vertexLabel, GraphicLL graphic) {
                currentLeader = vertexLabel;
                currentSize = 0;
            }

            public void beforePush(int vertexLabel, GraphicLL graphic) {
                leader[vertexLabel] = currentLeader;
                currentSize++;
            }

            public void finish(int VertexLabel, GraphicLL graphic) {
                sccSize.add(currentSize);//collect each SCC's size
            }
        }

        //Make dfsTraversal of the reverse graphic in the decreasing order of finishTime
        FindSCCs findSCCs = new FindSCCs();
        g.dfsTraversalRvs_Eff(order, findSCCs);
        return IntArray.integerCollection2IntArray(findSCCs.sccSize);
    }

    //Method to count Strong Connected Components' sizes
    static int[] countSCCs(GraphicLL g) {
        class FinishTime extends DFSRecurseDG {
            int[] finishTime = new int[g.vl.out.length];
            int currentTime = 1;
            public void recurse(int vertexLabel, UGraphicLL graphic) {
                graphic.dfsRecurse(vertexLabel, this);
                //take down the finishTime after the successive inspection of the vertex
                finishTime[vertexLabel] = currentTime++;
            }
        }

        //calculate finishTime of the vertices, finishTime determine the order of next dfsTraversal
        FinishTime ft = new FinishTime();
        g.dfsTraversal(ft);
        int[] temp = new int[ft.finishTime.length - 1];
        System.arraycopy(IntArray.indexValueSwap(ft.finishTime), 1, temp, 0, temp.length);
        int[] order = IntArray.reverse(temp);

        //Make dfsTraversal of the reverse graphic in the decreasing order of finishTime
        return customDFSTraversal(g, order);
    }

    static int[] customDFSTraversal(GraphicLL g, int[] order) {
        class FindSCCs extends DFSRecurseDG {
            int currentLeader = order[0];
            int currentSize = 0;
            int[] leader = new int[g.vl.out.length];//leader means the first vertex visited of one SCC
            LinkedList<Integer> sccSize = new LinkedList<>();
            public void recurse(int vertexLabel, GraphicLL graphic) {
                leader[vertexLabel] = currentLeader;
                currentSize++;
                graphic.dfsRecurseRvs(vertexLabel, this);
            }
        }

        FindSCCs findSCCs = new FindSCCs();
        for(int i = 0; i < order.length; i++) {
            if(!g.vertexVisited[order[i]]) {
                findSCCs.currentLeader = order[i];
                findSCCs.currentSize = 0;
                findSCCs.recurse(order[i], g);
                findSCCs.sccSize.add(findSCCs.currentSize);//collect each SCC's size
            }
        }

        g.clearVisit();

        return IntArray.integerCollection2IntArray(findSCCs.sccSize);
    }
}
