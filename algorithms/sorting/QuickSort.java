package algorithms.sorting;

public class QuickSort {
    /*
        Quicksort is a Divide-and-Conquer algorithm.
        * Choose an element in the array to be the pivot. Partition an array around the pivot element such that all elements
        smaller than the pivot are to the left of the pivot and all elements bigger than the pivot are to the right of it.
        * Recursively apply the algorithm to each part - the left-of-the-pivot part and the right-of-the-pivot part.


        The best-case pivot would divide the array into two equal parts, which would halve the problem size. This means
        that the pivot is the median of the elements, and in order to find the median, we would need an already sorted
        array.
        Ways of choosing a pivot:
        - Select a random pivot.
        - Select the leftmost or rightmost element as the pivot.
        - Take the first, middle, and last value of the array, and choose the median of those three numbers as the pivot
        (median-of-three method).
        - Use a median-finding algorithm such as the median-of-medians algorithm.


        - Quick Sort is tail recursive and hence, all tail call optimizations can be done. To make sure at most O(log N)
        space is used, recurse first into the smaller side of the partition followed by a tail call to recurse into the other.
        - When the number of elements is less than a threshold k, keep it on hold until the whole array has been processed.
        Then, perform insertion sort on it. Stopping the recursion early leaves the array k-sorted, meaning that each element
        is at most k positions away from its final sorted position. In this case, insertion sort takes O(kn) time to finish
        the sort, which is linear if k is a constant. This makes suboptimal use of the cache memories in modern architectures.


        * Comparison-based sorting.
        * Time Complexity
           - Average and Best-case O(N logN)
           - Worst-case O(N^2)
        * Space Complexity  O(N) (naive); O(logN) (optimized)
           - The in-place partition logic uses O(1) space and the recursive quick sort algorithm takes O(log N) space.
        * Not Stable algorithm
           - However any sorting algorithm can be made stable by considering indexes as comparison parameter.
        * Not Adaptive


        USE:
        - When fast sorting is desired since quicksort has an average case complexity of O(N log N) which is better than
          bubble or insertion sort.
        - When a stable sort is Not needed.
        - Divide-and-conquer strategy enables the use of parallelization
        AVOID:
        - When space is limited like in embedded systems
        - When ordering of elements matter in the final sorted list , i.e., stable sorting is desired

    */

    public static void sort(int[] array) {
        recursion(array, 0, array.length - 1);
        //recursionWithHoarePartition(array, 0, array.length - 1);
    }

    private static void recursion(int[] array, int leftIndex, int rightIndex) {
        // Base case:
        if (leftIndex >= rightIndex) {
            return;
        }

        // Partition the array using Lomuto Partitioning into two parts around the pivot
        int partitionIndex = partition(array, leftIndex, rightIndex);

        // Recursively sort the left and right partitioned sub-arrays
        recursion(array, leftIndex, partitionIndex - 1);
        recursion(array, partitionIndex + 1, rightIndex);
    }

    /*
        Lomuto Partitioning
        It works by iterating over the input array, swapping elements that are less than or equal to a pre-selected pivot element.
        This scheme is more compact and easy to understand, it is frequently used in introductory material, although it
        is less efficient than Hoare's original scheme e.g., when all elements are equal.
    */
    private static int partition(int[] array, int leftIndex, int rightIndex) {

        // We can use median-of-three method to choose pivot (and swap it with the last element) as optimization.
        //getPivotAsMedianOfThree(array, leftIndex, rightIndex);

        // Select the rightmost element as pivot (this could very well be another element).
        int pivot = array[rightIndex];

        int partitionIndex = leftIndex;

        // All elements lesser or equal to the pivot are pushed to the left of the partition index.
        for (int i = leftIndex; i < rightIndex; ++i) {
            if (array[i] <= pivot) {
                swap(array, i, partitionIndex);
                ++partitionIndex;
            }
        }

        // Finally, place pivot at its correct position by swapping array[partitionIndex] and the pivot (array[rightIndex]).
        swap(array, partitionIndex, rightIndex);

        // Return the position of the pivot
        return partitionIndex;
    }

    private static void recursionWithHoarePartition(int[] array, int leftIndex, int rightIndex) {
        // Base case:
        if (leftIndex >= rightIndex) {
            return;
        }

        // Partition the array using Hoare Partitioning into two parts around the pivot
        int partitionIndex = partitionHoare(array, leftIndex, rightIndex);

        // Recursively sort the left and right partitioned sub-arrays
        recursionWithHoarePartition(array, leftIndex, partitionIndex);
        recursionWithHoarePartition(array, partitionIndex + 1, rightIndex);
    }

    /*
        Hoare Partitioning
        Instead of working across the array from left to right, it initializes two "pointers" that start at the two ends,
        the two "pointers" move toward each other until an inversion is found (a smaller value on the left side and a
        greater value on the right side).Then, the two "pointers" swap values and the process continues.

        This means that we have more iterations, and more comparisons, but fewer swaps. This can be important since often
        comparing memory values is cheaper than swapping them.

        - If we pick the last element as pivot, then the partition may cause infinite recursion (Example [10, 5, 6, 20]
          and pivot = 20, then returned index will always be rightIndex.
          To handle a random pivot, swap that random element with the first element and simply follow the below algorithm.
        - This partition is more efficient than Lomuto’s partition because it does three times fewer swaps on average,
          and it creates efficient partitions even when all values are equal.
        - Not stable and worst-case is also O(N^2).
        - Note that the pivot’s final location is not necessarily at the index that was returned, and the next two segments
        that the main algorithm recurs on are [leftIndex ... partitionIndex] and [partitionIndex+1 .. rightIndex].
    */
    private static int partitionHoare(int[] array, int leftIndex, int rightIndex) {
        // Select the leftmost element as pivot.
        int pivot = array[leftIndex];

        int leftPointer = leftIndex;
        int rightPointer = rightIndex;

        while (true) {
            // Find leftmost element greater than pivot.
            while (array[leftPointer] < pivot) {
                ++leftPointer;
            }
            // Find rightmost element smaller than or equal to pivot.
            while (array[rightPointer] > pivot) {
                --rightPointer;
            }

            //  Returns the index of the last element of the less-than-pivot side
            if (leftPointer >= rightPointer) {
                return rightPointer;
            }

            swap(array, leftPointer, rightPointer);
            ++leftPointer;
            --rightPointer;
        }
    }
    

    /*
       QuickSort Tail Call Optimization (Reducing worst-case space to O(logN)).

       To reduce the recursion depth, recur first for the smaller half of the array, and use a tail call to recurse into
       the other half.

    */
    public static void optimizedSort(int[] array) {
        optimizedTailRecursion(array, 0, array.length - 1);
    }

    private static void optimizedTailRecursion(int[] array, int leftIndex, int rightIndex) {

        while (leftIndex < rightIndex) {
            // Partition the array into two parts around the pivot
            int partitionIndex = partition(array, leftIndex, rightIndex);

            // If left part is smaller, then we make recursive call for left part. Else for the right part.
            if (partitionIndex - leftIndex < rightIndex - partitionIndex) {
                optimizedTailRecursion(array, leftIndex, partitionIndex - 1);
                leftIndex = partitionIndex + 1;
            } else {
                optimizedTailRecursion(array, partitionIndex + 1, rightIndex);
                rightIndex = partitionIndex - 1;
            }
        }
    }

    /*
       Hybrid QuickSort

       Quicksort algorithm is efficient if the size of the input is very large. But, insertion sort is more efficient than
       quick sort in case of small arrays as the number of comparisons and swaps are less compared to quicksort. So we
       combine the two algorithms to sort efficiently using both approaches.

       If the array size is greater than the threshold value (10), then the Quicksort function is called for that portion
       of the array. Else, Insertion sort is called.
    */
    private static final int threshold = 10;

    public static void hybridSort(int[] array) {
        hybridRecursion(array, 0, array.length - 1);
    }

    private static void hybridRecursion(int[] array, int leftIndex, int rightIndex) {

        while (leftIndex < rightIndex) {

            // Check if array size is less than threshold
            if (rightIndex - leftIndex < threshold) {
                InsertionSort.insertionSort(array, leftIndex, rightIndex);
                break;
            } else {
                // Partition the array into two parts around the pivot
                int partitionIndex = partition(array, leftIndex, rightIndex);

                // Tail call optimization – recur on the smaller sub-array
                if (partitionIndex - leftIndex < rightIndex - partitionIndex) {
                    optimizedTailRecursion(array, leftIndex, partitionIndex - 1);
                    leftIndex = partitionIndex + 1;
                } else {
                    optimizedTailRecursion(array, partitionIndex + 1, rightIndex);
                    rightIndex = partitionIndex - 1;
                }
            }
        }
    }

    /*
       The above function can be easily converted to an iterative version with the help of an auxiliary stack.

       - Partition process is the same in both recursive and iterative. The same techniques to choose optimal pivot can
       also be applied to the iterative version.
       - The above-mentioned optimizations for recursive quicksort can also be applied to the iterative version:
          - To reduce the stack size, first push the indexes of smaller half.
          - Use insertion sort when the size reduces below an experimentally calculated threshold.
    */
    public static void iterativeSort(int[] array) {
        iterative(array, 0, array.length - 1);
    }

    private static void iterative(int[] array, int leftIndex, int rightIndex) {
        // Create an auxiliary stack
        int[] stack = new int[rightIndex - leftIndex + 1];

        // Initialize top of stack
        int top = -1;

        // Push initial values of leftIndex and rightIndex to stack
        stack[++top] = leftIndex;
        stack[++top] = rightIndex;

        // Keep popping from stack while not empty
        while (top >= 0) {
            // Pop leftIndex and rightIndex
            rightIndex = stack[top--];
            leftIndex = stack[top--];

            /// Partition the array into two parts around the pivot
            int partitionIndex = partition(array, leftIndex, rightIndex);

            // If left part has more than one element to be sorted, then push left part to stack.
            if (partitionIndex - 1 > leftIndex) {
                stack[++top] = leftIndex;
                stack[++top] = partitionIndex - 1;
            }

            // If right part has more than one element to be, then push right part to stack
            if (partitionIndex + 1 < rightIndex) {
                stack[++top] = partitionIndex + 1;
                stack[++top] = rightIndex;
            }
        }
    }


    /*
        Where 3 partitions are formed instead of just 2.
        In 3-Way QuickSort, an array array[l ... r] is divided in 3 parts:
        1) array[left ... smallerIndex] elements less than pivot.
        2) array[smallerIndex+1 ... greaterIndex-1] elements equal to pivot.
        3) array[greaterIndex ... right] elements greater than pivot.
        Then, the procedure is repeated on the first and the third partitions. Note that the second sub-array is already
        sorted and has taken its correct position in the final sorted array.


        * Time Complexity
           - Best-case O(N)  // Compared to Best-case time complexity: O(N logN) of the simple partition.
           - Average
           We can see that the number of recursive calls will be of order logN, and since each function call consists of
           a loop, the average time complexity of this solution is given by O(N logN). 2-way partitioning quick sort
           O(N log(2)N) is slightly greater than that of the 3-way partitioning quick sort O(N log(3)N).
           - Worst-case O(N^2)
        * Space Complexity O(log n)
           - In-place algorithm
           -  Since each recursion takes O(1) space, and there are logN recursions on average, the average space complexity
           is given by O(log n).
        * Not Stable algorithm

        - The 3-way partitioning quicksort would be better in case there are multiple repeated elements in the array,
        while in case of an array with distinct elements, both (2-way and 3-way sorting) are with approximately the same
        time complexity.
    */
    public static void threeWaySort(int[] array) {
        threeWayRecursion(array, 0, array.length - 1);
    }

    private static void threeWayRecursion(int[] array, int leftIndex, int rightIndex) {
        // Base case:
        if (leftIndex >= rightIndex) {
            return;
        }

        // Partition the array into three parts around the pivot
        // Select the rightmost element as pivot (this could very well be another element).
        int pivot = array[rightIndex];

        int smallerIndex = leftIndex;
        int greaterIndex = rightIndex - 1;

        for (int i = smallerIndex; i <= greaterIndex; ++i) {
            if (array[i] < pivot) {
                swap(array, i, smallerIndex);
                ++smallerIndex;
            } else if (array[i] > pivot) {
                swap(array, i, greaterIndex);
                --greaterIndex;
                --i; // unknown elements is in position i, and we should compare it with pivot
            }
        }

        // Finally, place pivot at its correct position by swapping array[greaterIndex - 1] and the pivot (array[right]).
        swap(array, ++greaterIndex, rightIndex);


        // Recursively sort the left and right partitioned sub-arrays
        threeWayRecursion(array, leftIndex, --smallerIndex);
        threeWayRecursion(array, ++greaterIndex, rightIndex);
    }


    /*
        Median-of-three
        Choose the median of the first, middle and last element of the partition for the pivot.
        (If the elements are 9, 1, 4 -> median is 4).
        This rule counters the case of sorted (or reverse-sorted) input, and gives a better estimate of the optimal pivot
        (the true median) than selecting any single element, when no information about the ordering of the input is known.

        This function puts a median into array[rightIndex] first, then that new value of array[rightIndex] is used for
        a pivot, as in the Lomuto partitioning.
    */
    private static void getPivotAsMedianOfThree(int[] array, int leftIndex, int rightIndex) {
        int middleIndex = leftIndex + (rightIndex - leftIndex) / 2;

        // Effectively sort the values in leftIndex, middleIndex and rightIndex. Putting smallest in leftIndex,
        // largest in middleIndex and median in rightIndex.
        if (array[leftIndex] > array[middleIndex]) {
            swap(array, leftIndex, middleIndex);
        }
        if (array[leftIndex] > array[rightIndex]) {
            swap(array, leftIndex, rightIndex);
        }
        if (array[rightIndex] > array[middleIndex]) {
            swap(array, rightIndex, middleIndex);
        }
    }

    private static void swap(int[] array, int firstIndex, int secondIndex) {
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }


}


