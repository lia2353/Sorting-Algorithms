package algorithms.sorting;

public class BubbleSort {
    /*
        Bubble sort also known as Sinking sort.

        The algorithm works by repeatedly swapping the adjacent elements if they are in the wrong order. The pass through
        the array is repeated until the array is sorted.

         * Time Complexity
            - Worst-case and Average complexity of O(N^2);
            - Best-case of O(N) , when an array is already sorted
         * Space Complexity O(1)
            - In-place algorithm
         * Stable algorithm
         * improvedSort version is Adaptive. If the array is partially sorted (no swaps were performed), we'll terminate
           the sorting early.

         * Number of Comparisons (Worst case) O(N^2)
            - every element is compared to every other element
         * Number of Swaps (Worst case) O(N^2)


          USE:
          - When the array is partially sorted - since bubble sort is adaptive
          - When we have memory usage constraints
          - When a simple sorting implementation is desired
          - When the array to be sorted is relatively small

          AVOID:
          - When the array-to-be-sorted has a large number of elements
          - When the array is completely  unsorted
          - When wanted a faster run time and memory is not a concern.
    */

    //Note that this function always runs O(N^2) time even if the array is sorted. Best-case time complexity is also O(N^2)
    public static void sort(int[] array) {

        for (int i = 0; i < array.length - 1; ++i) {

            // Last i elements are already sorted
            for (int j = 0; j < array.length - 1 - i; ++j) {

                // Swap if this pair is out of order
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }


    // We can optimize by stopping the algorithm if the inner loop didn’t cause any swap.
    public static void improvedSort(int[] array) {
        // After each iteration we keep track whether any elements were swapped.
        boolean swapped;

        for (int i = 0; i < array.length - 1; ++i) {
            swapped = false;

            // Last i elements are already sorted
            for (int j = 0; j < array.length - 1 - i; ++j) {
                // Swap if this pair is out of order
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                    swapped = true;
                }
            }

            // If no elements were swapped for the entire array, that means the array has already been sorted - so we can
            // just stop the iterations early.
            if (!swapped) {
                break;
            }
        }
    }


    /*
        Recursive Bubble Sort has no performance/implementation advantages over Iterative Bubble Sort.
        * Recursion Idea:
         - Base Case: If array size is 1, return.
         - Do One Pass of normal Bubble Sort. This pass fixes last element of current subarray.
         - Recur for all elements except last of current subarray.

        This increases the additional memory consumption from O(1) to O(N) (recursively calling the function for each
                                                                            element of the array).
     */
    public static void recursiveSort(int[] array) {
        recurse(array, array.length);
    }

    // Perform Bubble Sort on subarray [i ... size]
    private static void recurse(int[] array, int size) {
        // Base case
        if (size <= 1) {
            return;
        }

        // One pass of bubble sort. After this pass, the largest element is moved (bubbled) to the end.
        boolean swapped = false;
        for (int j = 0; j < size - 1; ++j) {
            if (array[j] > array[j + 1]) {
                int temp = array[j];
                array[j] = array[j + 1];
                array[j + 1] = temp;
                swapped = true;
            }
        }

        if (!swapped) {
            return;
        }

        // Recursively call the function for the rest of the array except last element
        recurse(array, size - 1);
    }

}
