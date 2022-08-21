package algorithms;

import java.util.Collection;

public class IntArray {
    static int[] increasingInts(int bottom, int top) {
        int[] r = new int[top - bottom];
        for(int i = 0; i < r.length; i++) {
            r[i] = i + bottom;
        }

        return r;
    }

    static int[] increasingInts(int size) {
        return increasingInts(1, 1 + size);
    }

    static int[] integerCollection2IntArray(Collection<Integer> cll) {
        int[] r = new int[cll.size()];
        int i = 0;
        for(int t: cll) {
            r[i++] = t;
        }

        return r;
    }

    static int[] indexValueSwap(int[] values) {
        int[] r = new int[values.length];
        for(int i = 0; i < values.length; i++) {
            r[values[i]] = i;
        }

        return r;
    }

    static int[] reverse(int[] array) {
        int[] r = new int[array.length];
        for(int i = 0; i < array.length; i++) {
            r[array.length - i - 1] = array[i];
        }

        return r;
    }

    static int min(int[] array) {
        return min(array, 0, array.length);
    }

    static int min(int[] array, int start, int end) {
        int minIndex = start;
        int minimum = array[start];
        for(int i = start + 1; i < end; i++) {
            if(array[i] < minimum) {
                minIndex = i;
                minimum = array[i];
            }
        }

        return minIndex;
    }

    static int min(int[] array, int[] indices, int start, int end) {
        int minIndex = indices[start];
        int min = array[indices[start]];
        for(int i = start; i < end; i++) {
            if(array[indices[i]] < min) {
                minIndex = indices[i];
                min = array[minIndex];
            }
        }

        return minIndex;
    }

    static int find(int[] array, int value) {
        for(int i = 0; i < array.length; i++) {
            if(array[i] == value) {
                return i;
            }
        }

        return -1;
    }

    static int findLittleBigger(long[] sortedArray, int start, int end, long value) {
        for(int i = start; i < end; i++) {
            if(sortedArray[i] >= value) {
                return i;
            }
        }

        return end;
    }

    static int findLittleLess(long[] sortedArray, int start, int end, long value) {
        for(int i = end - 1; i >= start; i--) {
            if(sortedArray[i] <= value) {
                return i;
            }
        }

        return start - 1;
    }

    static int replace(int[] array, int a, int replacement) {
        int index = find(array, a);
        array[index] = replacement;
        return index;
    }

    static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    static int[] deleteOne(int[] array, int pos) {
        int[] r = new int[array.length - 1];
        for(int i = 0; i < pos; i++) {
            r[i] = array[i];
        }

        for(int i = pos; i < r.length; i++) {
            r[pos] = array[pos + 1];
        }

        return r;
    }

    static long[] distinct(long[] sortedArray) {
        int i = 0;
        int j = 0;
        long previous = sortedArray[0] + 1;
        for(; i < sortedArray.length; i++) {
            if(sortedArray[i] != previous) {
                previous = sortedArray[i];
                sortedArray[j++] = previous;
            }
        }
        long[] r = new long[j];
        System.arraycopy(sortedArray, 0, r, 0, j);
        return r;
    }

    static void output(int[] arrays, int lineLength) {
        int i = 0;
        for(int t: arrays) {
            if(i > lineLength - 1) {
                System.out.print("\n");
                i = 0;
            }
            System.out.print(t);
            System.out.print(" ");
            i++;
        }
    }

    static void output(int[] arrays, int[] indices) {
        for(int index: indices) {
            System.out.print(arrays[index]);
            System.out.print(" ");
        }
    }

    static int[] clone(int[] array) {
        int[] r = new int[array.length];
        System.arraycopy(array, 0, r, 0, array.length);
        return r;
    }
}