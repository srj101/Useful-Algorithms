package algorithms;

public class RedBlackTree {
    BiTreeEntry root;//root has minimum value
    BiTreeEntry[] nodes;//Index available from 1, equal to label
    BiTreeEntry entrance;//The entrance to bubble up
    
    RedBlackTree() {}
    RedBlackTree(int[] values) {
        if(values.length == 0) return;

        root = new BiTreeEntry(null, null, null, values[0]);
        nodes = new BiTreeEntry[values.length + 1];
        entrance = root;
    }
    
    void insert(int value) {
        BiTreeEntry position = entrance;
    }
}

class BiTreeEntry {
    BiTreeEntry parent;
    BiTreeEntry left;
    BiTreeEntry right;
    int value;
    BiTreeEntry() {
        parent = null;
        left = null;
        right = null;
        value = 0;
    }
    BiTreeEntry(BiTreeEntry parent, BiTreeEntry left, BiTreeEntry right, int value) {
        this.parent = parent;
        this.left = left;
        this.right = right;
        this.value = value;
    }
}
