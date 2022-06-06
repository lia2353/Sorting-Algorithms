package algorithms.sorting;

public class Main {

    public static void main(String[] args) {
        int[] sortedArray = SelectionSort.sort(new int[] {16, -25, 38, 55, 38, 1, 12, 120});

        print(sortedArray);
    }


    private static void print(final int[] array) {
        for (int elem : array) {
            System.out.println(elem);
        }
    }
}
