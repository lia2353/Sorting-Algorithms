package algorithms.sorting;

import java.util.Arrays;

public class CountingSort {
    /*
        * Belongs to Integer sorts (sometimes called counting sorts)
        - Not a comparison-based sorting.
        - Integer sorts determine for each element how many elements are less than it. If there are 14 elements that are less than x, then x will be placed in the 15th slot. This information is used to place each element into the correct slot immediately — no need to rearrange arrays.
        * Time Complexity  O(N + R)
           - where R is the range (and the size ot count array).
        * Space Complexity O(N + R)
           - Uses a temporary arrays making it a Non-In-place algorithm.
        * Not Stable algorithm
           - It can be made stable.

        - The algorithm is efficient if the range of input data is not significantly greater than the number of objects
          to be sorted (Consider the situation where the input sequence is in range 1 to 10K and the data is 10, 5, 10K, 5K).
    */

    // Stable version
    public static void sort(int[] array) {
        // Sorts negative and positive numbers
        int rangeFrom = Arrays.stream(array).min().getAsInt();
        int rangeTo = Arrays.stream(array).max().getAsInt();
        int range = rangeTo - rangeFrom + 1;

        // Auxiliary array used first to store the numbers of unique elements, and then (after the second loop) to store
        // the positions where each unique element should be placed.
        int[] count = new int[range];
        Arrays.fill(count, 0);

        int[] inputArray = Arrays.copyOf(array, array.length);

        // Counts the number of times each unique element occurs within the input array.
        for (int element : inputArray) {
            ++count[element - rangeFrom];
        }

        // Perform prefix sum computation on count in order to determine, for each unique element, the position range where
        // these elements should be placed in the sorted array; elements k should be placed ending in position count[k]-1.
        for (int i = 1; i < count.length; ++i) {
            count[i] += count[i - 1];
        }

        // Loop over the input array in reverse order, moving each element into it's sorted position in the sorted array.
        for (int i = inputArray.length - 1; i >= 0; --i) {
            int position = inputArray[i] - rangeFrom;
            --count[position];
            array[count[position]] = inputArray[i];
        }
    }

    // Not stable
    public static void simpleSort(int[] array) {
        // Sorts negative and positive numbers
        int rangeFrom = Arrays.stream(array).min().getAsInt();
        int rangeTo = Arrays.stream(array).max().getAsInt();
        int range = rangeTo - rangeFrom + 1;

        int[] count = new int[range];
        Arrays.fill(count, 0);

        int[] inputArray = Arrays.copyOf(array, array.length);

        // Counts the number of occurs within the input array.
        for (int element : inputArray) {
            ++count[element - rangeFrom];
        }

        // Simply add count[k] copies of the element k to the sorted array.
        int index = 0;
        for (int i = 0; i < count.length; ++i) {
            if (count[i] > 0) {
                int element = i + rangeFrom;

                for (int c = 0; c < count[i]; ++c) {
                    array[index++] = element;
                }
            }
        }
    }

}
