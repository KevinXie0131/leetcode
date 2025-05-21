package com.answer.bit;

public class Q338_Counting_Bits {
    /**
     * Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n),
     * ans[i] is the number of 1's in the binary representation of i
     * 给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
     * Follow up: It is very easy to come up with a solution with a runtime of O(n log n). Can you do it in linear time O(n) and possibly in a single pass?
     * 进阶：很容易就能实现时间复杂度为 O(n log n) 的解决方案，你可以在线性时间复杂度 O(n) 内用一趟扫描解决此问题吗？
     */
    public int[] countBits_0(int n) {
        int[] res = new int[n + 1];
        for(int i = 0; i <= n; i++){
            res[i] = Integer.bitCount(i); // 内置函数用于计算给定的整数的二进制表示中的 1 的数目
        }
        return res;
    }
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
     * 时间复杂度：O(nlogn)。需要对从 0 到 n 的每个整数使用计算「一比特数」，
     * 对于每个整数计算「一比特数」的时间都不会超过 O(logn)。
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
     * 一个数比如a，如果是偶数，那么a比特位1的个数和(a/2)比特位1的个数是一样的，因为一个数是偶数那么他的二进制比他的一半的二进制只是多了一个0而已。
     * 如果是奇数就不一样了，他会比除以2的结果多了一个1（比如9的二进制比4的二进制多一个1，19的二进制比9的二进制多一个1，等等）。
     */
    public int[] countBits_a(int num) {
        int[] bits = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            //一个数的比特位1的个数先让他等于他一半的比特位量
            bits[i] = bits[i / 2];
            //如果是奇数还要加1
            if ((i & 1) == 1)
                bits[i]++;
        }
        return bits;
    }
    /**
     * 另一种形式
     * 对于所有的数字，只有两类：
     *  奇数：二进制表示中，奇数一定比前面那个偶数多一个 1，因为多的就是最低位的 1。
     *  偶数：二进制表示中，偶数中 1 的个数一定和除以 2 之后的那个数一样多。因为最低位是 0，除以 2 就是右移一位，也就是把那个 0 抹掉而已，所以 1 的个数是不变的
     */
    public int[] countBits_b(int num) {
        int[] bits = new int[num + 1];
        bits[0] = 0;
        for (int i = 1; i <= num; i++) {
            if(i % 2 == 1) {
                bits[i] = bits[i - 1] + 1;
            } else {
                bits[i] = bits[i/2];
            }
        }
        return bits;
    }
    /**
     * 另一种形式(时间复杂度：O(n))
     */
    public int[] countBits1(int n) {
        int[] res = new int[n + 1];
        for(int i = 1; i <= n; i++){
            res[i] = res[i  & (i - 1)] + 1;
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
