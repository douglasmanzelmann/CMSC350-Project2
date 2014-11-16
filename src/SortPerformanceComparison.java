import java.util.Comparator;

/**
 * Created by douga_000 on 11/9/2014.
 */
public class SortPerformanceComparison {
    static int numberOfComparisonsInQuicksort;
    static int numberOfAssignmentsInQuicksort;

    // Method:  insertionSort
    // Author:  instructorX
    // Date:    11/9/2014
    // Purpose: A method that sorts an array using an insertion sort
    public static void insertionSort(Comparable[] array) {
        int numberOfAssignments = 0;
        int numberOfComparisons = 0;

        for (int i = 1; i < array.length; i++) {
            Comparable temp = array[i];
            int j = i - 1;
            while (j >= 0 && temp.compareTo(array[j]) < 0) {
                array[j + 1] = array[j];
                j--;

                numberOfComparisons++;
                numberOfAssignments++;
            }
            array[j +1 ] = temp;
            numberOfComparisons++;
        }

        System.out.println("Number of comparisons: " + numberOfComparisons);
        System.out.println("Number of assignments: " + numberOfAssignments);
        System.out.println("Total runtime: " + (numberOfAssignments + numberOfComparisons));
    }

    // Method:  swap
    // Author:  instructorX
    // Date:    11/9/2014
    // Purpose: A method that swaps two array elements

    private static void swap(Comparable[] array, int i, int j)
    {
        Comparable temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // Method:  selectionSort
    // Author:  instructorX
    // Date:    11/9/2014
    // Purpose: A method that sorts an array using a selection sort

    public static void selectionSort(Comparable[] array) {
        int numberOfComparisons = 0;
        int numberOfAssignments = 0;

        int i;
        for (i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j].compareTo(array[minIndex]) < 0) {
                    minIndex = j;
                }
                numberOfComparisons++;
            }
            swap(array, minIndex, i);
            numberOfAssignments++;
        }

        System.out.println("Number of comparisons: " + numberOfComparisons);
        System.out.println("Number of assignments: " + numberOfAssignments);
        System.out.println("Total runtime: " + (numberOfAssignments + numberOfComparisons));
    }

    public static <E> void printArray(E[] array) {

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ", ");
            if (i % 60 == 0 && i != 0)
                System.out.println();
        }
        System.out.println();
    }


    public static void quickSort(Integer[] items, int leftIndex, int rightIndex) {
        int i, j, partitionSize, pivotValue, temp;
        partitionSize = rightIndex - leftIndex + 1;

        if(partitionSize <= 1) // base case, one item to be sorted
            return;

        pivotValue = items[(leftIndex + rightIndex) / 2];
        System.out.println("pivot value: " + pivotValue);
        i = leftIndex; // initialize the two partition indices
        j = rightIndex;

        // look for items in wrong partitions and switch them
        do {
            while (items[i] < pivotValue) {  // left item is in correct partition
                i++;
                numberOfComparisonsInQuicksort++;
            }

            while (items[j] > pivotValue) { // right item is in correct partition
                j--;
                numberOfComparisonsInQuicksort++;
            }

            if (i <= j) { // pointers have not crossed, switch items
              temp = items[i]; items[i] = items[j]; items[j]=temp;
                numberOfAssignmentsInQuicksort++;
              i++; j--;
            }

        } while (i <= j); // the pointers have not crossed

        // reduced problems
        quickSort(items, leftIndex, j); // sort left partition,
        quickSort(items, i, rightIndex); // sort right partition
    }

    // Method: main
    // Author: Douglas Manzelmann
    // Date: 11/9/2014
    // Purpose: To demonstrate the worst case scenario for insertionSort
    public static void main(String[] args) {
        int[] inputSizes = {100, 200, 300, 400, 500, 600, 700, 800, 900, 1000};

        // In this loop, arrays of sizes 100, 200, 300, ... 1000 are created.
        // In the inner loop, the values of each array are sorted in descending order
        // which is the worst case scenario for insertion sort and selection sort
        for (int i = 0; i < inputSizes.length; i++) {
            Comparable<Integer>[] insertionSortArray = new Comparable[inputSizes[i]];
            Comparable<Integer>[] selectionSortArray = new Comparable[inputSizes[i]];
            Integer[] quickSortArray = new Integer[inputSizes[i]];
            for (int j = 0; j < inputSizes[i]; j++) {
                insertionSortArray[j] = inputSizes[i] - j;
                selectionSortArray[j] = inputSizes[i] - j;

                if (j == (inputSizes[i] - 1) / 2)
                    quickSortArray[j] = 0;
                else
                    quickSortArray[j] = j + 1;

            }

            numberOfComparisonsInQuicksort = 0;
            numberOfAssignmentsInQuicksort = 0;
            System.out.println("Size of array to be sorted by quicksort: " + inputSizes[i]);
            System.out.println("Pre-sort array: ");
            printArray(quickSortArray);
            System.out.println();
            quickSort(quickSortArray, 0, (inputSizes[i]-1));
            System.out.println("Number of comparisons: " + numberOfComparisonsInQuicksort);
            System.out.println("Number of assignments: " + numberOfAssignmentsInQuicksort);
            System.out.print("Sorted array: ");
            printArray(quickSortArray);
            System.out.println();

            /*System.out.println("Size of array to be sorted by Insertion Sort: " + inputSizes[i]);
            insertionSort(insertionSortArray);
            System.out.print("Sorted array: ");
            printArray(insertionSortArray);
            System.out.println();

            System.out.println("Size of array to be sorted by Selection Sort: " + inputSizes[i]);
            insertionSort(selectionSortArray);
            System.out.print("Sorted array: ");
            printArray(selectionSortArray);
            System.out.println();*/
        }
        Integer[] testQuickSort = {10, 9, 8, 7, 5, 4, 3, 2, 1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        numberOfComparisonsInQuicksort = 0;
        System.out.println("size of array: " + testQuickSort.length);
        quickSort(testQuickSort, 0, testQuickSort.length - 1);
        System.out.println("number of comaparisons: " + numberOfComparisonsInQuicksort);
        System.out.println(" sorted array: ");
        printArray(testQuickSort);
    }
}
