package algorithms.sorting;

import java.util.Arrays;

public class MergeSort {

    /*
        Merge Sort is a Divide-and-Conquer algorithm
        - The Top-Down Merge sort approach is the methodology which uses recursion mechanism. It divides the input array
          into two halves, calls itself for the two halves, and then merges the two sorted halves.
        - The Bottom-Up Merge sort approach uses iterative methodology. It starts with the single-element arrays, combines
          and sorts two adjacent arrays. The combined-sorted arrays are again combined and sorted with each other until
          one big sorted array is achieved.

        * Time Complexity  O(N logN)
           - The complexity of the merge function is O(len1 + len2), where len1 is the length of the first half, and len2 is the length of the second half.
        * Space Complexity O(N)
           - Not in-place algorithm
           - Requires temporary arrays to store the sorted sub-arrays. All elements are copied into an auxiliary array
             so N auxiliary space is required for merge sort.
        * Stable algorithm
        * Not Adaptive


         USE:
        - Works well for larger lists
        - Has a consistent running time
        - Preserves the order of equal elements
        - Handles slow-to-access sequential data efficiently
        - The merge operation of merge sort can be implemented without extra space for linked lists.

        AVOID:
        - Slower comparative to the other sort algorithms for smaller tasks.
        - The algorithm requires an additional memory space of O(N) for the temporary array.
        - It goes through the whole process even if the array is sorted.

    */

    // Top-down Merge Sort Implementation (Recursive):
    public static void sort(int[] array) {
        recurse(array, 0, array.length - 1);
    }

    private static void recurse(int[] array, int left, int right) {
        // Base case: if the size of the array is 1 then the array is trivially sorted
        if (right - left < 1) {
            return;
        }

        // Divide the array into two subarrays (almost equal halves) and make a recursive call to sort the subarrays.
        int mid = left + (right - left) / 2;

        recurse(array, left, mid);
        recurse(array, mid + 1, right);

        // Merge both sorted subarrays array[left, mid] and array[mid + 1, right] to form sorted array
        merge(array, left, mid, right);
    }

    private static void merge(int[] array, int left, int mid, int right) {
        int[] mergedArray = new int[right - left + 1];
        int leftArrayIndex = left;
        int rightArrayIndex = mid + 1;
        int mergedArrayIndex = 0;

        // Traversing both subarrays while both are non-empty and adding the smaller of both elements to the merged array
        while (leftArrayIndex <= mid && rightArrayIndex <= right) {

            // Stable sort -> if current elements of both subarrays are equal, select the element from the left subarray.
            if (array[leftArrayIndex] <= array[rightArrayIndex]) {
                mergedArray[mergedArrayIndex] = array[leftArrayIndex];
                ++mergedArrayIndex;
                ++leftArrayIndex;
            } else {
                mergedArray[mergedArrayIndex] = array[rightArrayIndex];
                ++mergedArrayIndex;
                ++rightArrayIndex;
            }
        }

        // Add remaining elements of the left subarray
        while (leftArrayIndex <= mid) {
            mergedArray[mergedArrayIndex] = array[leftArrayIndex];
            ++mergedArrayIndex;
            ++leftArrayIndex;
        }
        // Add remaining elements of the right subarray
        while (rightArrayIndex <= right) {
            mergedArray[mergedArrayIndex] = array[rightArrayIndex];
            ++mergedArrayIndex;
            ++rightArrayIndex;
        }

        // Copy merged array to array
        for (int i = left; i <= right; ++i) {
            array[i] = mergedArray[i - left];
        }
    }

}
