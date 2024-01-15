# Homework #1
In homework #1, you have two programs to complete: `InsertionSort.java` and `Utils.java`.

## Problem 1
Complete `InsertionSort.java`.

```agsl
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
        /*
            Complete code here. You must call `swap()` method.
         */
    }

    /**
     * Swap elements at position i and j in array xs
     * @param xs integer array
     * @param i index of element to be swapped
     * @param j index of element to be swapped
     */
    private static void swap(int[] xs, int i, int j) {
        /*
            Complete code here.
         */
    }
}
```

### Implementation Guidelines
* You can freely modify the body of the `insert()` method to complete the code. **However, do not add any fields or additional methods to `InsertionSort` class, and no method signatures should be changed.**
* You should check the correctness of your program by running test code, `InsertionSortTest.java`


## Problem 2
Complete `Utils.java`. You have four methods to fill in.

```agsl
public class Utils {

    /**
     * Find an index of an element matching `target`.
     * @param xs int array
     * @param target element to be found
     * @return index of a matching element, -1 otherwise
     */
    public static int findIndex(int[] xs, int target) {

        return 0;
    }

    /**
     * Calculate the sum of an array.
     * @param xs double array
     * @return sum of an array
     */
    public static double sum(double[] xs) {

        return 0.0;
    }

    /**
     * Reverse the elements of a String array. For example, ["A", "BB", "C"] => ["C", "BB", "A"]
     * @param xs String array
     * @return a newly created array containing elements of xs in reversed order
     */
    public static String[] reverse(String[] xs) {

        return null;
    }

    /**
     * Reverse the elements of a String array in place without creating a new array.
     * Input array xs itself must be transformed to an array containing elements
     * in reversed order.
     * For example, ["A", "BB", "C"] => ["C", "BB", "A"].
     * @param xs String array
     */
    public static void reverse_in_place(String[] xs) {

    }

    /**
     * Returns an array containing running averages of an array.
     * @param xs int array
     * @return an array containing running average
     *
     * Given an input xs = [1, 2, 3, 4], `average()` returns a new array
     * containing running averages [1.0, 1.5, 2.0, 2.5].
     * Here,
     *      1.0 = 1 / 1
     *      1.5 = (1 + 2) / 2
     *      2.0 = (1 + 2 + 3) / 3
     *      2.5 = (1 + 2 + 3 + 4) / 4
     */
    public static double[] average(int[] xs) {

        return null;
    }

}
```

### Implementation Guidelines
* You can freely modify the body of each method to complete the code. You can also freely add fields and `private` methods if necessary. **However, no given `public` method signatures should be changed.**
* You should check the correctness of your program by running test code, `UtilsTest.java`