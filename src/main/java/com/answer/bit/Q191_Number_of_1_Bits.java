package com.answer.bit;

public class Q191_Number_of_1_Bits {

    /**
     * Approach 1: Loop and Flip
     */
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        while(n != 0){
            //  int val = n & 1;
            if((n & 1) == 1){
                count++;
            }
            n = n >>> 1;
        }
        return count;
    }
    /**
     * 另一种形式
     */
    public int hammingWeight_0(int n) {
        int count = 0;
        while(n != 0){
            if((n % 2) == 1){
                count++;
            }
            n = n >> 1;
        }
        return count;
    }
    /**
     * Approach 2: Bit Manipulation
     *  doing a bit-wise AND of n and n -1 flips the least-significant 1-bit in n to 0
     * 时间复杂度：Number of 1-bit
     */
    public int hammingWeight_1(int n) {
        int count = 0;
        while(n != 0){
            count++;
            n = n & (n-1); // For example, 11000 & 10111 = 10000, the last 1 is removed.
        }
        return count;
    }
}
