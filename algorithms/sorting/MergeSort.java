package algorithms.sorting;

public class MergeSort {

    /*
        Merge Sort is a Divide-and-Conquer algorithm
        - The Top-Down Merge sort approach is uses recursion mechanism. It divides the input array into two halves, calls
          itself for the two halves, and then merges the two sorted halves.
        - The Bottom-Up Merge sort approach uses iterative methodology. It starts with the single-element arrays, combines
          and sorts two adjacent arrays. The combined-sorted arrays are again combined and sorted with each other until
          one big sorted array is achieved.


        * Comparison-based sorting.
        * Time Complexity  O(N logN)
           - The complexity of the merge function is O(len1 + len2), where len1 is the length of the first half, and len2
           is the length of the second half.
        * Space Complexity O(N)
           - Not in-place algorithm
           - Requires temporary array to store the merged and sorted subarrays. All elements are copied into an auxiliary
           array so N auxiliary space is required for merge sort.
           - There are variant with reduced space complexity and cost of copying (example Block (Merge) sort).
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
    // Top-Down Merge sort implementation (Recursive):
    public static void recursiveSort(int[] array) {
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
        int firstArrayIndex = left;
        int secondArrayIndex = mid + 1;
        int mergedArrayIndex = 0;

        // Traversing both subarrays while both are non-empty and adding the smallest of both elements to the merged array
        while (firstArrayIndex <= mid && secondArrayIndex <= right) {

            // Stable sort -> if current elements of both subarrays are equal, select the element from the left subarray.
            if (array[firstArrayIndex] <= array[secondArrayIndex]) {
                mergedArray[mergedArrayIndex] = array[firstArrayIndex];
                ++mergedArrayIndex;
                ++firstArrayIndex;
            } else {
                mergedArray[mergedArrayIndex] = array[secondArrayIndex];
                ++mergedArrayIndex;
                ++secondArrayIndex;
            }
        }

        // Add remaining elements of the left subarray
        while (firstArrayIndex <= mid) {
            mergedArray[mergedArrayIndex] = array[firstArrayIndex];
            ++mergedArrayIndex;
            ++firstArrayIndex;
        }
        // Add remaining elements of the right subarray
        while (secondArrayIndex <= right) {
            mergedArray[mergedArrayIndex] = array[secondArrayIndex];
            ++mergedArrayIndex;
            ++secondArrayIndex;
        }

        // Copy merged array to array
        for (int i = left; i <= right; ++i) {
            array[i] = mergedArray[i - left];
        }
    }


    // Bottom-Up Merge sort implementation (Iterative):
    public static void iterativeSort(int[] array) {

        // Each subarray of size 1 is trivially sorted.
        // First merge and sort subarrays of size 1 to create sorted subarrays of size 2, then merge and sort subarrays
        // of size 2 to create sorted subarrays of size 4, and so on.
        for (int currentSize = 1; currentSize < array.length; currentSize *= 2) {

            // Merge the two subarrays: [i ... i+size-1] and [i+size ... i+2*size-1]
            for (int i = 0; i < array.length; i += (2 * currentSize)) {
                int left = i;
                int mid = Math.min(i + currentSize - 1, array.length - 1);
                int right = Math.min(i + 2 * currentSize - 1, array.length - 1);

                merge(array, left, mid, right);
            }
        }
    }

}
