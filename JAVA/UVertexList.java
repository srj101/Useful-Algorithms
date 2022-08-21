package algorithms;

public class UVertexList {
    int out[][];//index begin from 1, equal to label

    UVertexList() {
        out = new int[0][];
    }
    UVertexList(EdgeList el) {
        int max = el.maxVertexLabel();

        out = new int[max + 1][];
        int[] outLength = new int[max + 1];
        for(int i = 1; i < el.back.length; i++) {
            outLength[el.back[i]]++;
            outLength[el.forth[i]]++;
        }

        for(int i = 0; i < out.length; i++) {
            out[i] = new int[outLength[i]];
        }

        int[] outIndex = new int[max + 1];
        int back;
        int forth;
        for(int i = 1; i < el.back.length; i++) {
            back = el.back[i];
            forth = el.forth[i];
            out[back][outIndex[back]++] = i;
            out[forth][outIndex[forth]++] = i;
        }
    }

    void moveEdges(int from, int to) {
        int[] edges = new int[out[from].length + out[to].length];

        System.arraycopy(out[from], 0, edges, 0, out[from].length);
        System.arraycopy(out[to], 0, edges, out[from].length, out[to].length);

        out[from] = null;
    }

    void removeEdge(int back, int forth, int edgeLabel) {
        for(int i = 0; i < out[back].length; i++) {
            if(out[back][i] == edgeLabel) {
                out[back] = IntArray.deleteOne(out[back], i);
            }
        }

        for(int i = 0; i < out[forth].length; i++) {
            if(out[forth][i] == edgeLabel) {
                out[forth] = IntArray.deleteOne(out[forth], i);
            }
        }
    }

    protected UVertexList clone() {
        UVertexList r = new UVertexList();
        r.out = new int[out.length][];
        for(int i = 0; i < out.length; i++) {
            r.out[i] = IntArray.clone(out[i]);
        }

        return r;
    }
}
