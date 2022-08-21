package algorithms;

import java.io.File;
import java.util.LinkedList;

public class QuickSort {
    private static final int PIVOT_PATTERN = 3;
    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    private static final int GOOD_ONE = 3;

    public static void main(String args[]) {
        File f = new File(".\\QuickSort.txt");
        int[] numbers = ReadText.readInts(f);
        //System.out.println(countComparison(numbers));
        sort(numbers);
        IntArray.output(numbers, 10);
    }

    public static long countComparison(int[] nums) {
        return sort_Eff(nums);
    }

    public static long sort(int[] nums) {
        return quickSortRecurse(nums, 0, nums.length);
    }

    private static long quickSortRecurse(int[] nums, int start, int end) {
        if (end - start <= 1) return 0;
        int pivotIndex = biSort(nums, start, end);
        long left = quickSortRecurse(nums, start, pivotIndex);
        long right = quickSortRecurse(nums, pivotIndex + 1, end);
        return left + right + (end - start - 1);//count Comparison
    }

    public static int biSort(int[] nums, int start, int end) {
        int pivot = choosePivot(nums, start, end);

        //push forward on one side;
        //choose a less number on the right part, move it to the end of left part. Circulate this procedure.
        int i = start + 1;
        int j = start + 1;
        int temp;
        while (j < end) {
            if (nums[j] < pivot) {
                temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
            }
            j++;
        }
        nums[start] = nums[i - 1];
        nums[i - 1] = pivot;

        return i - 1;
    }

    public static int biSort(long[] nums, int start, int end) {
        long pivot = choosePivot(nums, start, end);

        //push forward on one side;
        //choose a less number on the right part, move it to the end of left part. Circulate this procedure.
        int i = start + 1;
        int j = start + 1;
        long temp;
        while (j < end) {
            if (nums[j] < pivot) {
                temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
            }
            j++;
        }
        nums[start] = nums[i - 1];
        nums[i - 1] = pivot;

        return i - 1;
    }

    public static int biSort_2(int[] nums, int start, int end) {
        int pivot = choosePivot(nums, start, end);

        //push forward on the double side
        //choose a less number to move to the left part. And choose a bigger number to move to the right part.
        //Circulate this.
        int j = end;
        int i = start;
        int temp;
        while (i < j) {
            //the right circulate should go first because it should find a less number to fill in the nums[start]
            for (j--; i < j && pivot <= nums[j]; j--) ;
            temp = nums[j];
            nums[j] = nums[i];
            nums[i] = temp;

            if (i >= j) break;//$Here also have chance to jump out. Without this primitives, i may bigger than j.

            for (i++; i < j && nums[i] <= pivot; i++) ;
            temp = nums[j];
            nums[j] = nums[i];
            nums[i] = temp;
        }
        nums[i] = pivot;

        return i;
    }

    public static final int choosePivot(int[] nums, int start, int end) {
        int pivot;
        switch (PIVOT_PATTERN) {
            case LEFT:
                pivot = nums[start];
                break;
            case RIGHT:
                pivot = nums[end - 1];
                nums[end - 1] = nums[start];
                break;
            case GOOD_ONE:
                //choose the median of three
                int mid = (start + end - 1) / 2;
                int a, b, c;
                if (nums[start] > nums[mid]) a = 0;
                else a = 1;
                if (nums[mid] > nums[end - 1]) b = 0;
                else b = 1;

                if (a == b) {
                    pivot = nums[mid];
                    nums[mid] = nums[start];
                    break;
                } else {
                    if (nums[start] > nums[end - 1]) c = 1;
                    else c = 0;
                    if (b == c) {
                        pivot = nums[end - 1];
                        nums[end - 1] = nums[start];
                        break;
                    } else {
                        pivot = nums[start];
                        break;
                    }
                }
            default:
                return -1;
        }

        return pivot;
    }

    public static final long choosePivot(long[] nums, int start, int end) {
        long pivot;
        switch (PIVOT_PATTERN) {
            case LEFT:
                pivot = nums[start];
                break;
            case RIGHT:
                pivot = nums[end - 1];
                nums[end - 1] = nums[start];
                break;
            case GOOD_ONE:
                //choose the median of three
                int mid = (start + end - 1) / 2;
                int a, b, c;
                if (nums[start] > nums[mid]) a = 0;
                else a = 1;
                if (nums[mid] > nums[end - 1]) b = 0;
                else b = 1;

                if (a == b) {
                    pivot = nums[mid];
                    nums[mid] = nums[start];
                    break;
                } else {
                    if (nums[start] > nums[end - 1]) c = 1;
                    else c = 0;
                    if (b == c) {
                        pivot = nums[end - 1];
                        nums[end - 1] = nums[start];
                        break;
                    } else {
                        pivot = nums[start];
                        break;
                    }
                }
            default:
                return -1;
        }

        return pivot;
    }

    public static long sort_Eff(int[] nums) {
        LinkedList<Integer> stack = new LinkedList<>();//stack contains All the compare area's edge

        stack.push(nums.length);
        //Current compare area is from start(inclusive) to the comparingRightEdge(not inclusive).
        int start = 0;
        int comparingRightEdge = stack.peek();//the very left area's right edge

        long countComparison = 0;

        while (!stack.isEmpty()) {
            //push backward comparingRightEdge and do comparing
            while (comparingRightEdge - start > 1) {
                int pivotIndex = biSort(nums, start, comparingRightEdge);
                countComparison += comparingRightEdge - start - 1;
                stack.push(pivotIndex);
                comparingRightEdge = pivotIndex;
            }

            //push forward start. push forward comparingRightEdge. remove the area completed comparing
            while (comparingRightEdge - start <= 1 && !stack.isEmpty()) {
                start = stack.pop() + 1;
                if (!stack.isEmpty())
                    comparingRightEdge = stack.peek();
            }
        }

        return countComparison;
    }

    public static long sort_Eff(long[] nums) {
        LinkedList<Integer> stack = new LinkedList<>();//stack contains All the compare area's edge

        stack.push(nums.length);
        //Current compare area is from start(inclusive) to the comparingRightEdge(not inclusive).
        int start = 0;
        int comparingRightEdge = stack.peek();//the very left area's right edge

        long countComparison = 0;

        while (!stack.isEmpty()) {
            //push backward comparingRightEdge and do comparing
            while (comparingRightEdge - start > 1) {
                int pivotIndex = biSort(nums, start, comparingRightEdge);
                countComparison += comparingRightEdge - start - 1;
                stack.push(pivotIndex);
                comparingRightEdge = pivotIndex;
            }

            //push forward start. push forward comparingRightEdge. remove the area completed comparing
            while (comparingRightEdge - start <= 1 && !stack.isEmpty()) {
                start = stack.pop() + 1;
                if (!stack.isEmpty())
                    comparingRightEdge = stack.peek();
            }
        }

        return countComparison;
    }

    public static int BiSearch(long key, long[] sortedArray) {
        return BiSearchRecurse(key, sortedArray, 0, sortedArray.length);
    }

    public static int BiSearchRecurse(long key, long[] sortedArray, int start, int end) {
        if(start >= end) {
            return 0x00000000;
        }

        int mid = (start + end - 1) / 2;
        if (key < sortedArray[mid]) {
            return BiSearchRecurse(key, sortedArray, start, mid);
        } else if (key > sortedArray[mid]) {
            return BiSearchRecurse(key, sortedArray, mid + 1, end);
        } else {
            return mid;
        }
    }

    public static int[] BiSearch(long low, long high, long[] sortedArray) {
        return BiSearchRecurse(low, high, sortedArray, 0, sortedArray.length);
    }

    public static int[] BiSearchRecurse(long low, long high, long[] sortedArray, int start, int end) {
        if(start >= end) {
            return null;
        }

        int mid = (start + end - 1) / 2;
        if (high < sortedArray[mid]) {
            return BiSearchRecurse(low, high, sortedArray, start, mid);
        } else if (low > sortedArray[mid]) {
            return BiSearchRecurse(low, high, sortedArray, mid + 1, end);
        } else {
            int leftBound = IntArray.findLittleBigger(sortedArray, start, end, low);
            int rightBound = IntArray.findLittleLess(sortedArray, start, end, high);
            int[] r = new int[2];
            r[0] = leftBound;
            r[1] = rightBound;
            return r;
        }
    }
}
