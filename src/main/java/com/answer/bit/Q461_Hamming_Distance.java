package com.answer.bit;

public class Q461_Hamming_Distance {
    /**
     * The Hamming distance between two integer numbers is the number of positions at which the corresponding bits are different.
     */
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
     * When we do AND bit operation between number and number-1, the rightmost bit of one in the original number would be cleared.
     */
    public static int hammingDistance_1(int x, int y) {
        int count = 0;
        int  z = x ^ y;
        // Count number of ones in binary representation of a number
        while(z > 0){
            z &= z - 1; // remove the rightmost bit of '1'
            count++;
        }
        return count;
    }
    /**
     * Approach 2: Bit Shift
     * In order to count the number of bit 1, we could shift each of the bit to either the leftmost or the rightmost position
     * and then check if the bit is one or not.
     */
    public int hammingDistance_2(int x, int y) {
        int count = 0;
        int xor = x ^ y;

        while(xor > 0){
            if(xor % 2 == 1){
                count++;
            }
            xor = xor >> 1;
        }
        return count;
    }
}
