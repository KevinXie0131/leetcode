package com.answer.bit;

public class Q338_Counting_Bits {
    /**
     * Approach 1: Pop Count (Brian Kernighan's Algorithm: https://www.educative.io/answers/what-is-kernighans-algorithm)
     * Time complexity: O(nlogn)
     * Space complexity: O(1)
     */
    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        for(int i = 0; i <= n; i++){
            res[i] = count(i);
        }
        return res;
    }
    /**
     * 101 & 100 = 100
     * 100 & 011 = 0
     *
     * 110 & 101 = 100
     * 100 & 011 = 0
     *
     * 1011 & 1010 = 1010
     * 1010 & 1001 = 1000
     * 1000 & 0111 = 0
     */
    public int count(int x){
        int count = 0;
        while(x > 0){
            x = x & (x - 1); // zeroing out the least significant nonzero bit
            count++;
        }

        return count;
    }
    /**
     * Approach 2: DP + Most Significant Bit
     */
    // TO DO
    /**
     * Approach 3: DP + Least Significant Bit
     */
    // TO DO
    /**
     * Approach 4: DP + Last Set Bit
     */
    // TO DO
}
