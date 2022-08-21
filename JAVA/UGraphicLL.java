package algorithms;

import java.util.LinkedList;

public class UGraphicLL {
    UVertexList vl;
    EdgeList el;

    boolean[] vertexVisited;//available from index 1

    UGraphicLL() {}
    UGraphicLL(EdgeList el) {
        this.vl = new UVertexList(el);
        this.el = el;//$without this, why null?
        this.vertexVisited = new boolean[vl.out.length];
    }

    public void dfsTraversal(DFSRecurse dfsRecurse) {
        int[] order = IntArray.increasingInts(vl.out.length - 1);
        dfsTraversal(order, dfsRecurse);
    }

    public void dfsTraversal(int[] order, DFSRecurse dfsRecurse) {
        for(int i = 0; i < order.length; i++) {
            if(!vertexVisited[order[i]])
                dfsRecurse.recurse(order[i], this);
        }
        clearVisit();
    }

    public final void dfsRecurse(int vertexLabel, DFSRecurse dfsRecurse) {
        vertexVisited[vertexLabel] = true;
        int nextVertex;
        for(int nextEdge: vl.out[vertexLabel]) {
            nextVertex = el.forth[nextEdge];
            if(!vertexVisited[nextVertex]) {
                dfsRecurse.recurse(nextVertex, this);
            }
        }
    }

    public void dfsTraversal_Eff(DFSProcessor dfsProcessor) {
        int[] order = IntArray.increasingInts(vl.out.length - 1);
        dfsTraversal_Eff(order, dfsProcessor);
    }

    public void dfsTraversal_Eff(int[] order, DFSProcessor dfsProcessor) {//efficient method of dfs
        LinkedList<Integer> stack = new LinkedList<>();
        int[] label = new int[vl.out.length];//label record the first unvisited edge's index in out[x] Array
        //not needed, but make program more efficient

        for(int od: order) {//do dfs for every vertex unvisited
            if(vertexVisited[od]) continue;

            dfsProcessor.init(od, this);
            dfsProcessor.beforePush(od, this);
            vertexVisited[od] = true;
            stack.push(od);
            while(!stack.isEmpty()) {//do dfs until stack is empty
                int top = stack.peek();
                int edge;
                int vertex = -1;
                //find the first unvisited vertex connected to the top
                for(; label[top] < vl.out[top].length; label[top]++) {
                    edge = vl.out[top][label[top]];
                    vertex = el.forth[edge];
                    if(!vertexVisited[vertex]) break;
                }

                if(label[top] < vl.out[top].length && !vertexVisited[vertex]) {
                    //if found one, push it into stack
                    dfsProcessor.beforePush(vertex, this);
                    vertexVisited[vertex] = true;
                    stack.push(vertex);
                    label[top]++;
                } else {
                    //if not, pop();
                    vertex = stack.pop();
                    dfsProcessor.afterPop(vertex, this);
                }
            }
            dfsProcessor.finish(od, this);
        }

        clearVisit();
    }

    int mergeVertex(int edgeLabel) {//something wrong
        if(el.back[edgeLabel] == 0) { return -1; }

        int back = el.back[edgeLabel];
        int forth = el.forth[edgeLabel];

        //remove connections between back and forth
        vl.removeEdge(back, forth, edgeLabel);
        el.removeEdge(edgeLabel);

        int edge;
        while((edge = findEdge(back, forth)) != -1) {
            vl.removeEdge(back, forth, edge);
            el.removeEdge(edge);
        }

        //move edges from back to forth
        vl.moveEdges(back, forth);
        el.moveEdges(back, forth);

        return 1;
    }

    int findEdge(int back, int forth) {
        int[] t = vl.out[back];
        for(int edge: t) {
            if(el.forth[edge] == forth) {
                return edge;
            }
        }

        return -1;//if not found
    }

    void clearVisit() {
        vertexVisited = new boolean[vertexVisited.length];
    }

    protected UGraphicLL clone() {
        UGraphicLL r = new UGraphicLL();
        r.vl = vl.clone();
        r.el = el.clone();
        return r;
    }
}
