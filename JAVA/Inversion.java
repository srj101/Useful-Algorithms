package algorithms;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

//Divide & Conquer Algorithm of Sorting & Count Inversions
public class Inversion {
    public static void main(String args[]) {
        File f = new File(".\\IntegerArray.txt");

        int[] numbers = ReadText.readInts(f);
        System.out.println(CountInversions(numbers));
    }

    static long CountInversions(int [] n) {
        return count(n, 0, n.length);
    }

    private static long count(int[] n, int start, int end) {
        if(end - start < 2) return 0;

        return count(n, start, (start + end + 1) / 2) + count(n, (start + end + 1) / 2, end)
                + countSplitInversions(n, start, end);
                //can't change the sequence of add
    }

    private static long countSplitInversions(int[] n, int start, int end) {
        //Assume two half parts between start and end(not inclusive) are sorted
        long sum = 0;//count inversions
        int mid = (start + end + 1) / 2;//the end(not inclusive) of first part
        int[] temp = new int[end - start];
        int i = 0;
        int j = start;
        int k = mid;
        //merge two sorted half part to a new array temp
        for(; j < mid && k < end; i++) {

            if(n[j] <= n[k]) {
            //focus on the equal term, it make algorithm more precise.
            //Added the equal term, it won't count Inversions when two numbers are equal
                sum += k - mid;
                temp[i] = n[j];
                j++;
            } else {
                temp[i] = n[k];
                k++;
            }
        }

        //copy the rest to temp
        if(j < mid) {
            for(; j < mid; j++) {
                sum += end - mid;
                temp[i++] = n[j];
            }
        } else {
            for(; k < end; k++) {
                temp[i++] = n[k];
            }
        }

        //Copy all back from temp
        i = start;
        for(int x: temp) {
            n[i++] = x;
        }
        return sum;
    }
}
