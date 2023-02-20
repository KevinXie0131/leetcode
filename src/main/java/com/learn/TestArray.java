package com.learn;

public class TestArray {
    /**
     * The first is to create a new Array, of the same size as the original. Then, we should copy the odd-indexed elements
     * and square the even-indexed elements, writing them into the new Array.

     */
    public int[] squareEven(int[] array, int length) {

        // Check for edge cases.
        if (array == null) {
            return null;
        }

        // Create a resultant Array which would hold the result.
        int result[] = new int[length];

        // Iterate through the original Array.
        for(int i = 0; i < length; i++) {

            // Get the element from slot i of the input Array.
            int element = array[i];

            // If the index is an even number, then we need to square element.
            if (i % 2 == 0) {
                element *= element;
            }

            // Write element into the result Array.
            result[i] = element;
        }

        // Return the result Array.
        return result;
    }
    /**
     * In-place Array operations
     */
    public int[] squareEven_1(int[] array, int length) {

        // Check for edge cases.
        if (array == null) {
            return array;
        }

        // Iterate through the original array.
        for(int i = 0; i < length; i++) {

            // If the index is an even number, then we need to square the element
            // and replace the original value in the Array.
            if (i % 2 == 0) {
                array[i] *= array[i];
            }
            // Notice how we don't need to do *anything* for the odd indexes? :-)
        }

        // We just return the original array. Some problems on leetcode require you
        // to return it, and other's dont.
        return array;
    }
}
