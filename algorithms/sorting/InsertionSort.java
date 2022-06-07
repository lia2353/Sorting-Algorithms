package algorithms.sorting;

import java.util.Arrays;

public class InsertionSort {
    /*
        The algorithm maintains two subarrays in a given array:
        - an already sorted subarray
        - remaining subarray which is unsorted
        Start with trivially sorted subarray of size 1. Pick an elements from the unsorted part and insert it at the correct
        place in the sorted part (shifts all larger elements back to make space, and inserts into that correct position).


        * Time Complexity
           - Worst-case and Average complexity of O(N^2);
           - Best-case of O(N) , when an array is already sorted (During each iteration, the element to be sorted is only
                                                            compared with the right-most element of the sorted subarray.)
        * Space Complexity O(1)
           - In-place algorithm
        * Stable algorithm
           - As elements bubble to the correct position in the sorted subarray, the original relative order of equal
             elements is maintained.
        * Adaptive
           - Efficient for data sets that are already substantially sorted.


        USE:
        - When the array is nearly sorted - since insertion sort is adaptive
        - When we have memory usage constraints
        - When a simple sorting implementation is desired
        - When the array to be sorted is relatively small
        - When you need to sort elements online - that is sorting them as they come in.
        * Insertion sort is one of the fastest algorithms for sorting very small arrays, even faster than quicksort;
          indeed, good quicksort implementations use insertion sort for arrays smaller than a certain threshold, also
          when arising as subproblems; the exact threshold must be determined experimentally and depends on the machine,
          but is commonly around ten.

        AVOID:
        - When the array to be sorted has a large number of elements
        - The array is completely  unsorted
        - You want a faster run time and memory is not a concern.
    */
    public static void sort(int[] array) {

        // Runs over the unsorted subarray [1 ... length]; Starts from index 1, because single-element array is trivially sorted
        for (int i = 1; i < array.length; ++i) {
            int currentElement = array[i];

            // In the sorted subarray shifts all larger elements back to make space for currentElement right positioning
            int j = i - 1;
            while (j >= 0 && currentElement < array[j]) {
                array[j + 1] = array[j];
                --j;
            }

            array[j + 1] = currentElement;
        }
    }

    /*
        Recursive Insertion Sort has no performance/implementation advantages over Iterative Insertion Sort.
        * Recursion Idea:
         - Base Case: If array size is 1, return.
         - Recursively sort first size-1 elements.
         - Insert last element at its correct position in sorted array.

        This increases the additional memory consumption from O(1) to O(N) (at the deepest level of recursion the stack
        contains N references to the array, each with accompanying value of variable size from N down to 1)
    */
    public static void recursiveSort(int[] array) {
        recurse(array, array.length);
    }

    private static void recurse(int[] array, int size) {
        // Base case
        if (size <= 1) {
            return;
        }

        // Sort first size-1 elements
        recurse(array, size - 1);

        // Insert last element at its correct position in the sorted array
        int lastElement = array[size - 1];
        int j = size - 2;
        while (j >= 0 && lastElement < array[j]) {
            array[j + 1] = array[j];
            --j;
        }
        array[j + 1] = lastElement;
    }

    /*
        Binary Insertion Sort

        Binary Insertion Sort uses binary search to find the proper location to insert the selected item at each iteration.
        In normal insertion, sorting takes O(i) (at ith iteration) in worst case. We can reduce it to O(logi) by using
        binary search.

        The algorithm, as a whole, still has worst case running time of O(N^2) because of the series of swaps required
        for each insertion.
    */
    public static void binarySort(int[] array) {

        // Runs over the unsorted subarray [1 ... length]; Starts from index 1, because single-element array is trivially sorted
        for (int i = 1; i < array.length; ++i) {

            int currentElement = array[i];
            // Find the position where currentElement should be inserted
            int insertPosition = binarySearch(array, 0, i - 1, currentElement);

            // In the sorted subarray shifts all elements after insertPosition back to make space for currentElement
            for (int j = i - 1; j >= insertPosition; --j) {
                array[j + 1] = array[j];
            }

            array[insertPosition] = currentElement;
        }
    }

    // Binary search based function to find the position of the element just greater than key-element in [start ... end] array
    private static int binarySearch(int[] array, int start, int end, int key) {
        if (start >= end) {
            return (key < array[end]) ? end : end + 1;
        }

        // addition can overflow -> using turnaround
        int mid = end + (start - end) / 2;

        if (key >= array[mid]) {
            return binarySearch(array, mid + 1, end, key);
        }

        return binarySearch(array, start, mid - 1, key);
    }
}
