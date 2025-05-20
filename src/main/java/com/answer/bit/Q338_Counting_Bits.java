package com.answer.bit;

public class Q338_Counting_Bits {
    /**
     * Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n),
     * ans[i] is the number of 1's in the binary representation of i
     * 给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
     */
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
    public int count(int x){ // 位运算
        int count = 0;
        while(x > 0){
            x = x & (x - 1); // zeroing out the least significant nonzero bit
            count++;
        }

        return count;
    }
    /**
     * 另一种形式(时间复杂度：O(n))
     */
    public int[] countBits1(int n) {
        int[] res = new int[n + 1];
        for(int i = 1; i <= n; i++){
            res[i] = res[i  & (i -1)] + 1;
        }
        return res;
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
