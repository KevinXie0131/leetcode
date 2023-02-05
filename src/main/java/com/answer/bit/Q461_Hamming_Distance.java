package com.answer.bit;

public class Q461_Hamming_Distance {

    public static void main(String[] args) {
        System.out.println(hammingDistance_1(4, 1));
    }

    /**
     * Approach 1: Built-in BitCounting Functions
     * Use built-in function
     */
    public static int hammingDistance(int x, int y) {
        int res = 0;

        int  z = x ^ y;
        res = Integer.bitCount(z);

        return res;
        // return Integer.bitCount(x ^ y);
    }

    /**
     * Approach 3: Brian Kernighan's Algorithm
     * Not use built-in function
     */
    public static int hammingDistance_1(int x, int y) {
        int count = 0;
        int  z = x ^ y;
        // Count number of ones in binary representation of a number
        while(z > 0){
            z &= z - 1;
            count++;
        }
        return count;
    }
    /**
     * Approach 2: Bit Shift
     */
}
