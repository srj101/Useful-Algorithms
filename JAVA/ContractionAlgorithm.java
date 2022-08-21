package algorithms;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.function.Supplier;

//Karger's algorithm is a randomized algorithm to compute a minimum cut of a connected graph.
public class ContractionAlgorithm {
    public static void main(String args[]) {

        File f = new File("kargerMinCut.txt");
        int[][] integers = ReadText.readIntArrays(f);

        EdgeList e = EdgeList.createEdgeList(integers);
        VertexMatrix v = new VertexMatrix(e);
        GraphicML g = new GraphicML(v, e);

        //Keep doing Kager's randomized algorithm enough time to ensure the probability of mistake is less than 1/n
        int times = (int)(Math.pow(v.vm.length - 1, 2) * Math.log(v.vm.length - 1));

        //UGraphicLL g = new UGraphicLL(e);
        //int times = (int)(Math.pow(g.vl.out.length - 1, 2) * Math.log(g.vl.out.length - 1));

        System.out.println(randomContractionEff(g, times));
    }

    static int randomContraction(GraphicML g, int times) {
        int minCrossing = g.el.getAmount();
        int edgesAmount = g.el.getAmount();
        int[] order;
        GraphicML gCopy;
        int t;
        //Keep doing Karger's randomized algorithm many times to ensure find the minute crossing edge
        for(int i = 0; i < times; i++) {
            gCopy = g.clone();
            order = RandomX.sequence(edgesAmount);
            t = contractionBy(gCopy, order);
            if(t < minCrossing)
                minCrossing = t;
        }

        return minCrossing;
    }

    static int randomContraction(UGraphicLL g, int times) {
        int minCrossing = g.el.getAmount();
        int edgesAmount = g.el.getAmount();
        int[] order;
        UGraphicLL gCopy;
        int t;
        //Keep doing Karger's randomized algorithm many times to ensure find the minute crossing edge
        for(int i = 0; i < times; i++) {
            gCopy = g.clone();
            order = RandomX.sequence(edgesAmount);
            t = contractionBy(gCopy, order);
            if(t < minCrossing)
                minCrossing = t;
        }

        return minCrossing;
    }

    static int randomContractionEff(GraphicML g, int times) {
        final int threadAmount = 4;

        ArrayList<CompletableFuture<Integer>> futureList = new ArrayList<CompletableFuture<Integer>>();

        int timesPerThread = (times + threadAmount - 1) / threadAmount;

        for(int i = 0; i < threadAmount - 1; i++) {
            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
                @Override
                public Integer get() {
                    return randomContraction(g, timesPerThread);
                }
            });
            futureList.add(future);
        }

        int minCross = randomContraction(g, timesPerThread);

        for(CompletableFuture<Integer> future : futureList) {
            try {
                int temp = future.get();
                assert future.isDone();
                if (temp < minCross) {
                    minCross = temp;
                }
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return minCross;
    }

    static int contractionBy(GraphicML g, int[] order) {
        int vertexLeft = g.vm.vm.length - 1;
        //merge vertex identified by edge's label in the random order.
        for(int i = 0; vertexLeft > 2 && i < order.length; i++) {
            if(g.mergeVertex(order[i]) == 1)
                vertexLeft--;
        }

        return g.el.getAmount();
    }

    static int contractionBy(UGraphicLL g, int[] order) {
        int vertexLeft = g.vl.out.length - 1;
        //merge vertex identified by edge's label in the random order.
        for(int i = 0; vertexLeft > 2 && i < order.length; i++) {
            if(g.mergeVertex(order[i]) == 1)
                vertexLeft--;
        }

        return g.el.getAmount();
    }
}
