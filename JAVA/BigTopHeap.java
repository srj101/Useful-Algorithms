package algorithms;

public class BigTopHeap extends Heap {
    public static BigTopHeap createEmptyHeap(int size) {
        BigTopHeap r = new BigTopHeap();
        r.init(size);
        return r;
    }

    public void insert(int element) {
        if(tail == 0) {
            data[tail] = element;
            tail++;
            return;
        }

        int previous = (tail - 1) / 2;
        int current = tail;
        while(current > 0) {
            if(element < data[previous]) {
                data[current] = element;
                tail++;
                return;
            }
            data[current] = data[previous];
            current = previous;
            previous = (previous - 1) / 2;
        }

        data[0] = element;
        tail++;
    }

    public int removeTop() {
        if(tail < 1) {
            return 0x11111111;
        } else if(tail == 1) {
            int t = data[0];
            data[0] = 0;
            tail--;
            return t;
        }

        int r = data[0];
        int element = data[--tail];
        data[tail] = 0;
        int current = 0;
        while(2 * current + 2 < tail) {
            switch(Simple.maxOf(element, data[2 * current + 1], data[2 * current + 2])) {
                case 1:
                    data[current] = element;
                    return r;
                case 2:
                    data[current] = data[2 * current + 1];
                    current = 2 * current + 1;
                    break;
                case 3:
                    data[current] = data[2 * current + 2];
                    current = 2 * current + 2;
                    break;
            }
        }

        if(2 * current + 2 == tail) {
            if(element > data[2 * current + 1]) {
                data[current] = element;
            } else {
                data[current] = data[2 * current + 1];
                data[2 * current + 1] = element;
            }
        } else {
            data[current] = element;
        }

        return r;
    }

    public static void sort(int[] array) {
        sort(createEmptyHeap(array.length), array);
    }
}