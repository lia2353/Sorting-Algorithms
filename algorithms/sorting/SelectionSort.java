package algorithms.sorting;

public class SelectionSort {

    /*
        The algorithm maintains two sub-arrays in a given array:
        - an already sorted sub-array
        - remaining sub-array which is unsorted
        Initially, the sorted sub-array is empty and the unsorted sub-array is the whole array.

        In each iteration the unsorted sub-array is traversed to find the smallest element (or largest, depending on
        sorting order). Then this element is added to the sorted sub-array by swapping it with the leftmost unsorted element.


        * Comparison-based sorting.
        * Time Complexity O(N^2)
          - Inefficient on large arrays.
          - For each element, the entire array is checked to find the smallest element. So in the worst case, N elements
            are checked for each element.
        * Space Complexity O(1)
          - In-place algorithm (Algorithm that does not need an extra space and produces an output in the same memory that
                             contains the data by transforming the input ‘in-place’. However, a small constant extra space
                             used for variables is allowed).
        * The default implementation is Not Stable, but it can be made stable (stable -> if two objects with equal keys
                                                                              appear in the same order in sorted output
                                                                              as they appear in the input array).
        * Number of Comparisons O(N^2)
           - every element is compared to every other element.
        * Number of Swaps O(N)
           - Makes the minimum possible number of swaps, n − 1 in the worst case (the final element is already in place).


        USE:
        - When the array is NOT partially sorted
        - When we have memory usage constraints
        - When a simple sorting implementation is desired
        - When the array to be sorted is relatively small

        AVOID:
        - When the array to be sorted has a large number of elements
        - When the array is nearly sorted
        - When you want a faster run time and memory is not a concern.
     */
    public static void sort(int[] array) {

        // Extend the length of the sorter array [0 ... i]; Take (i < length - 1) because single element is already sorted
        for (int i = 0; i < array.length - 1; ++i) {

            // Find the min element in the unsorted array [i ... length-1]
            // Initialize min element with the unsorted array's first element (it's index for simplicity)
            int minIndex = i;
            for (int j = i + 1; j < array.length; ++j) {
                if (array[minIndex] > array[j]) {
                    // found new minimum
                    minIndex = j;
                }
            }

            // Swap the found minimum element with the leftmost unsorted element
            if (minIndex != i) {
                swap(array, i, minIndex);
            }
        }
    }

    private static void swap(int[] array, int firstIndex, int secondIndex) {
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }

    /*
        Swapping might impact in pushing smaller key to a position greater than the bigger key (keys have equal values),
        which makes the algorithm unstable.

        Selection sort can be made Stable if instead of swapping, the minimum element is placed in its position by pushing
        every element one step forward. In simple terms it uses Insertion sort technique.
        That leads to performing O(N^2) swaps
    */
    public static void stableSort(int[] array) {

        // Extend the length of the sorter array [0 ... i]; Take (i < length - 1) because single element is already sorted
        for (int i = 0; i < array.length - 1; ++i) {

            // Find the min element in the unsorted array [i ... length-1]
            // Initialize min element with the unsorted array's first element (it's index for simplicity)
            int minIndex = i;
            for (int j = i + 1; j < array.length; ++j) {
                if (array[minIndex] > array[j]) {
                    // found new minimum
                    minIndex = j;
                }
            }

            // In the sorted sub-array shifts all larger elements back to make space for currentElement right position
            // In the unsorted sub-array shifts all larger elements back to make space for the found minimum right position.
            if (minIndex != i) {
                int temp = array[minIndex];
                while (minIndex > i) {
                    array[minIndex] = array[minIndex - 1];
                    --minIndex;
                }
                array[i] = temp;
            }

        }
    }

}

