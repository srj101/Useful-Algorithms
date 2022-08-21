package algorithms;

import java.util.LinkedList;

public class GraphicLL extends UGraphicLL {
    VertexList vl;

    GraphicLL() {}
    GraphicLL(EdgeList el) {
        this.vl = new VertexList(el);
        super.vl = this.vl;
        this.el = el;
        this.vertexVisited = new boolean[vl.out.length];
    }

    public final void dfsRecurseRvs(int vertexLabel, DFSRecurseDG dfsRecurse) {
        vertexVisited[vertexLabel] = true;
        int nextVertex;
        for(int nextEdge: vl.in[vertexLabel]) {
            nextVertex = el.back[nextEdge];
            if(!vertexVisited[nextVertex]) {
                dfsRecurse.recurse(nextVertex, this);
                //When running, call one object's function(), the function's signature is determined after compiled.
                //Explanation:
                //If you call a function which has a overload function in a subclass, they have one different parameters
                // and the subclass's function's parameter is the subclass of the other parameter in the super class's
                // function.
                //When you call function like this by its name somewhere, like object.function(a, b). The object is also
                // a parameter here sent from where call here.
                //Now, which function is called is determined on the whether super class or subclass is claimed for the
                // object parameter. If superclass claimed, it will call the super class's function rather than
                // subclass's function. It will be the same even if you send parameters to the function which match the
                // subclass's function more precisely. It means you send a parameter which is the subclass of the
                // parameter in the function claimed in the object's superclass. It still matches, But not matches as
                // precise as it in the object's subclass.
            }
        }
    }

    public void dfsTraversalRvs_Eff(int[] order, DFSProcessorDG dfsProcessor) {//almost the same to dfsTraversal_Eff
        LinkedList<Integer> stack = new LinkedList<>();
        int[] label = new int[vl.in.length];

        for(int od: order) {
            if(vertexVisited[od]) continue;

            dfsProcessor.init(od, this);
            dfsProcessor.beforePush(od, this);
            vertexVisited[od] = true;
            stack.push(od);
            while(!stack.isEmpty()) {
                int top = stack.peek();
                int edge;
                int vertex = -1;
                for(; label[top] < vl.in[top].length; label[top]++) {
                    edge = vl.in[top][label[top]];
                    vertex = el.back[edge];
                    if(!vertexVisited[vertex]) break;
                }

                if(label[top] < vl.in[top].length && !vertexVisited[vertex]) {
                    dfsProcessor.beforePush(vertex, this);
                    vertexVisited[vertex] = true;
                    stack.push(vertex);
                    label[top]++;
                } else {
                    vertex = stack.pop();
                    dfsProcessor.afterPop(vertex, this);
                }
            }
            dfsProcessor.finish(od, this);
        }

        clearVisit();
    }
}
