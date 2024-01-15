package cse2010.hw1;

public class InsertionSort {

    /**
     * Sort an array xs in a non-increasing (i.e., decreasing) order
     * @param xs integer array
     */
    // DO NOT MODIFY THIS METHOD!!
    public static void isort(int[] xs) {
        for (int i = 1; i < xs.length; i++) {
            insert(xs, i);
        }
    }

    /**
     * Insert element xs[k] to already sorted group x[0]..x[k-1]
     * @param xs integer array
     * @param k index of element to be inserted
     */
    private static void insert(int[] xs, int k) {
        int i = k - 1;
        while (i >= 0 && xs[i] < xs[k]) {
            swap(xs, i, k);
            k = i;
            i--;
        }
    }

    /**
     * Swap elements at position i and j in array xs
     * @param xs integer array
     * @param i index of element to be swapped
     * @param j index of element to be swapped
     */
    private static void swap(int[] xs, int i, int j) {
        int temp = xs[i];
        xs[i] = xs[j];
        xs[j] = temp;
    }
}
