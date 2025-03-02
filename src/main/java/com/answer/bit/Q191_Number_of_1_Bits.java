package com.answer.bit;

public class Q191_Number_of_1_Bits {
    /**
     * Approach 1: Loop and Flip 位移法
     */
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) { // 每次取n的最低位，判断是不是1，再位移
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
     */
    public int hammingWeight_1(int n) {
        int count = 0;
        while(n != 0){
            count++;
            n = n & (n-1); // For example, 11000 & 10111 = 10000, the last 1 is removed. n&(n-1)这个操作，可以起到'消除最后一个1'的功效。
        }
        return count;
    }
}
