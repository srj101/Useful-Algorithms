package algorithms;

public abstract class Heap {
    int[] data;
    int tail;

    void init(int size) {
        data = new int[size];
        tail = 0;
    }

    public abstract void insert(int element);
    public abstract int removeTop();

    public void insert(int[] array) {
        for(int x: array) {
            insert(x);
        }
    }

    protected static void sort(Heap h, int[] array) {
        h.insert(array);
        int t;
        for(int i = 0; i < array.length; i++) {
            t = h.tail - 1;
            array[t] = h.removeTop();
        }
    }

    public int peek() {
        return data[0];
    }

    public int size() {
        return tail;
    }
}
