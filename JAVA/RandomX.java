package algorithms;

public class RandomX {
    static int[] sequence(int bottom, int top) {
        int[] r = sequence(top - bottom);
        for(int i = 0; i < r.length; i++) {
            r[i] += bottom - 1;
        }
        return r;
    }

    static int[] sequence(int size) {
        int[] r = IntArray.increasingInts(size);

        int random;
        int temp;
        for(int j = 0; j < r.length; j++) {
            random = (int)(Math.random() * size);
            temp = r[j];
            r[j] = r[random];
            r[random] = temp;
        }

        return r;
    }
}
