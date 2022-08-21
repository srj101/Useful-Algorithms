package algorithms;

public class TwoHeap {
    Heap low;
    Heap high;

    public void init(int size) {
        low = BigTopHeap.createEmptyHeap((size + 1) / 2 + 1);
        high = SmallTopHeap.createEmptyHeap(size - (size + 1) / 2 + 1);
    }

    public static long computeMedianSum(int[] array) {
        TwoHeap th = new TwoHeap();
        th.init(array.length);
        final int range = 10000;
        long sum = 0;

        th.low.insert(array[0]);
        sum += array[0];
        sum %= range;

        int key;
        for(int i = 1; i < array.length; i++) {
            key = array[i];
            if(key < th.low.peek()) {
                th.low.insert(key);
                if(th.low.size() > th.high.size() + 1) {
                    th.high.insert(th.low.removeTop());
                }
            } else {
                th.high.insert(key);
                if(th.high.size() > th.low.size()) {
                    th.low.insert(th.high.removeTop());
                }
            }

            sum += th.low.peek();
            sum %= 10000;
        }

        return sum;
    }
}
