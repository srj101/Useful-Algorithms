package algorithms;

import java.io.File;

public class MedianMaintenance {
    public static void main(String args[]) {
        File f = new File(".\\Median.txt");
        int[] integers = ReadText.readInts(f);
        System.out.println(TwoHeap.computeMedianSum(integers));
    }
}
