package algorithms;

class GraphicML {
    VertexMatrix vm;
    EdgeList el;

    GraphicML() {}
    GraphicML(VertexMatrix vm, EdgeList el) {
        this.vm = vm;
        this.el = el;
    }

    int mergeVertex(int edgeLabel) {
        if(el.back[edgeLabel] == 0) { return -1; }

        int back = el.back[edgeLabel];
        int forth = el.forth[edgeLabel];

        //1)process v
        vm.vm[back][0] = -1;//label with no existence
        vm.vm[0][back] = -1;

        //move to new vertex
        for(int i = 1; i < vm.vm[back].length; i++) {
            vm.vm[forth][i] += vm.vm[back][i];
            vm.vm[i][forth] += vm.vm[back][i];
            //remove the connection about back
            vm.vm[back][i] = 0;
            vm.vm[i][back] = 0;
        }
        //remove self connection
        vm.vm[forth][forth] = 0;

        //2)process e
        //move edge from vertex back, to vertex forth
        el.moveEdges(back, forth);

        return 1;
    }

    public static GraphicML clone(GraphicML g) {
        GraphicML r = new GraphicML();
        r.vm = g.vm.clone();
        r.el = g.el.clone();
        return r;
    }

    public GraphicML clone() {
        return clone(this);
    }
}

