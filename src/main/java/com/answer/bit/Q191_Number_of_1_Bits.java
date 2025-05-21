package com.answer.bit;

public class Q191_Number_of_1_Bits {
    /**
     * Given a positive integer n, write a function that returns the number of set bits in its binary representation
     * (also known as the Hamming weight).
     * 给定一个正整数 n，编写一个函数，获取一个正整数的二进制形式并返回其二进制表达式中 设置位 的个数（也被称为汉明重量）。
     */
    /**
     * Approach 1: Loop and Flip 位移法
     * 算术右移 >>：舍弃最低位，高位用符号位填补；
     * 逻辑右移 >>>：舍弃最低位，高位用 0 填补。
     * 那么对于负数而言，其二进制最高位是 1，如果使用算术右移，那么高位填补的仍然是 1。也就是 n 永远不会为 0。
     */
    public int hammingWeight(int n) { // 每次取n的最低位，判断是不是1，再位移
        int count = 0;
        while(n != 0){
            //  int val = n & 1;
            if((n & 1) == 1){
                count++;
            }
            //  count += n & 1;
            n = n >>> 1; // you need to treat n as an unsigned value
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
     * Approach 2: Bit Manipulation 求与法: 逐步通过n&(n-1)，来消除n末尾的1，消除了多少次，就有多少个1。
     *  doing a bit-wise AND of n and n -1 flips the least-significant 1-bit in n to 0
     * 时间复杂度：Number of 1-bit
     *
     * 观察一下n与n-1这两个数的二进制表示：
     *    最末位一个1会变成0
     *    最末位一个1之后的0会全部变成1
     *    其他位相同
     *  x = 1011 0000
     *  x-1= 1010 1111
     *  x & (x-1) = 1010 0000
     *  n & (n−1)，其运算结果恰为把 n 的二进制位中的最低位的 1 变为 0 之后的结果。
     *  时间复杂度：O(logn)
     */
    public int hammingWeight_1(int n) {
        int count = 0;
        while(n != 0){
            count++;
            n = n & (n-1); // For example, 11000 & 10111 = 10000, the last 1 is removed. n&(n-1)这个操作，可以起到'消除最后一个1'的功效。
        }
        return count;
    }
    /**
     * 向右诸位移动32次，再判断是否为1
     */
    public int hammingWeight_3(int n) {
        int ans = 0;
        for (int i = 0; i < 32; i++) { // 右移 32 次 循环检查二进制位
            ans += (n >> i) & 1;
        }
        return ans;
    }
}
