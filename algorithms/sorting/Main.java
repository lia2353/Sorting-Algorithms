package algorithms.sorting;

public class Main {

    public static void main(String[] args) {

        int[] worstCaseArray = {99, 85, 61, 23, 2, 0, -14};
        int[] averageArray = {16, -25, 38, 55, 38, 1, 12, 120, 9};
        int[] biggerArray = {24, 97, 40, 67, 88, -85, 15, 66, 53, 44, -26, 48, 16, 52, 44, 23, 97, 18, 44, 80};
        int[] bestCaseArray = {-3, -2, -1, 0, 1, 2, 3};
        int[] repeatArray = {6, -8, -3, -3, 2, 5, 6, 2, -10, -3};

        print(averageArray);

        //SelectionSort.sort(averageArray);
        //SelectionSort.stableSort(averageArray);

        //BubbleSort.sort(averageArray);
        //BubbleSort.improvedSort(averageArray);
        //BubbleSort.recursiveSort(averageArray);

        //InsertionSort.sort(averageArray);
        //InsertionSort.recursiveSort(averageArray);
        //InsertionSort.binarySort(averageArray);

        //MergeSort.recursiveSort(averageArray);
        //MergeSort.iterativeSort(averageArray);

        //QuickSort.sort(averageArray);
        //QuickSort.optimizedSort(averageArray);
        //QuickSort.hybridSort(biggerArray);
        //QuickSort.threeWaySort(countingSortArray);
        //QuickSort.iterativeSort(averageArray);

        //CountingSort.sort(countingSortArray);
        //CountingSort.simpleSort(countingSortArray);

        print(averageArray);

    }


    private static void print(final int[] array) {
        System.out.print(array[0]);

        for (int i = 1; i < array.length; ++i) {
            System.out.print(", " + array[i]);
        }

        System.out.println();
    }
}
