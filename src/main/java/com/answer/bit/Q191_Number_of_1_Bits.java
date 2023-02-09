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
     * Approach 2: Bit Manipulation
     *  doing a bit-wise AND of n and n -1 flips the least-significant 1-bit in n to 0
     */
    public int hammingWeight_1(int n) {
        int count = 0;
        while(n != 0){
            count++;
            n = n & (n-1);
        }
        return count;
    }
}
