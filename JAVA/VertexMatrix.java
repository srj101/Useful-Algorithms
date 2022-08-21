package algorithms;

public class VertexMatrix {
    int[][] vm;

    VertexMatrix() {}
    VertexMatrix(int[][] vm) {
        init(vm);
    }
    VertexMatrix(EdgeList el) {
        int size = el.maxVertexLabel();
        vm = new int[size + 1][size + 1];
        int a, b;
        for(int i = 1; i < el.back.length; i++) {
            a = el.back[i];
            b = el.forth[i];
            vm[a][b] += 1;
            vm[b][a] += 1;
        }
    }

    protected void init(int[][] vm) {
        this.vm = new int[vm.length][vm.length];
        for(int i = 0; i < this.vm.length; i++) {
            for(int j = 0; j < this.vm[i].length; j++) {
                this.vm[i][j] = vm[i][j];
            }
        }
    }

    public static VertexMatrix clone(VertexMatrix v) {
        return new VertexMatrix(v.vm);
    }

    public VertexMatrix clone() {
        return new VertexMatrix(this.vm);
    }
}
