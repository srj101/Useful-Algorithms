package algorithms;

import java.io.File;

public class TwoSumAlgorithm {
    public static void main(String args[]) {
        File f = new File(".\\algo1-programming_prob-2sum.txt");
        long[] integers = ReadText.readLongs(f);
        QuickSort.sort_Eff(integers);
        integers = IntArray.distinct(integers);
        System.out.println(findPairs(-10000, 10000, integers));
    }

    public static int findPairs(int low, int high, long[] sortedArray) {
        boolean[] hasDivision = new boolean[high - low + 1];
        long key;
        int[] x;
        int leftBound;
        int rightBound;
        long another;
        for(int i = 0; i < sortedArray.length; i++) {
            key = sortedArray[i];

            x = QuickSort.BiSearch(low - key, high - key, sortedArray);
            if(x == null) continue;
            leftBound = x[0];
            rightBound = x[1];

            for(int j = leftBound; j <= rightBound; j++) {
                another = sortedArray[j];
                if(key != another) hasDivision[(int)(another + key) - low] = true;
            }
        }

        int r = 0;
        for(boolean t: hasDivision) {
            if(t) r++;
        }

        return r;
    }
}