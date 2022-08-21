package algorithms;

class UGraphicLVL extends UGraphicLL {
    ValueEdgeList el;

    UGraphicLVL() {}
    UGraphicLVL(ValueEdgeList el) {
        super(el);
        this.el = el;
    }

    @Override
    int findEdge(int back, int forth) {
        int[] t = vl.out[back];
        for(int edge: t) {
            if(el.forth[edge] == forth) {
                return edge;
            }
        }

        return -1;//if not found
    }
}
