package algorithms.sorting;

public class Main {

    public static void main(String[] args) {

        int[] worstCaseArray = {99, 85, 61, 23, 2, 0, -14};
        int[] averageArray = {16, -25, 38, 55, 38, 1, 12, 120};
        int[] bestCaseArray = {-3, -2, -1, 0, 1, 2, 3};

        print(averageArray);

        //SelectionSort.sort(averageArray);
        //SelectionSort.stableSort(averageArray);

        //BubbleSort.sort(averageArray);
        //BubbleSort.improvedSort(averageArray);
        //BubbleSort.recursiveSort(averageArray);

        //InsertionSort.sort(averageArray);
        //InsertionSort.recursiveSort(averageArray);
        //InsertionSort.binarySort(averageArray);

        MergeSort.sort(averageArray);

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
