package algorithms;

public class VertexList extends UVertexList {
    int[][] in;//index begin from 1, equal to label

    VertexList() {
        in = new int[0][];
    }
    VertexList(EdgeList el) {
        int max = el.maxVertexLabel();

        out = new int[max + 1][];
        in = new int[max + 1][];
        int[] outLength = new int[max + 1];
        int[] inLength = new int[max + 1];
        for(int i = 1; i < el.back.length; i++) {
            outLength[el.back[i]]++;
            inLength[el.forth[i]]++;
        }

        for(int i = 0; i < out.length; i++) {
            out[i] = new int[outLength[i]];
            in[i] = new int[inLength[i]];
        }

        int[] outIndex = new int[max + 1];
        int[] inIndex = new int[max + 1];
        for(int i = 1; i < el.back.length; i++) {
            out[el.back[i]][outIndex[el.back[i]]++] = i;
            in[el.forth[i]][inIndex[el.forth[i]]++] = i;
        }
    }
}
