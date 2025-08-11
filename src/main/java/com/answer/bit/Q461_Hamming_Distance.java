package com.answer.bit;

public class Q461_Hamming_Distance {
    /**
     * The Hamming distance between two integer numbers is the number of positions at which the corresponding bits are different.
     * 两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。
     */
    public static void main(String[] args) {
        System.out.println(hammingDistance_1(4, 1)); // 2
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
     * Approach 3: Brian Kernighan's Algorithm (More efficient)
     * 跳过两个 1 之间的 0，直接对 1 进行计数，减少循环次数
     * Not use built-in function
     * When we do AND bit operation between number and number-1, the rightmost bit of one in the original number would be cleared.
     */
    public static int hammingDistance_1(int x, int y) {
        int count = 0;
        int  z = x ^ y;  //异或剩下1的个数就是二进制位不同的位置的数目
        // Count number of ones in binary representation of a number
        while(z > 0){ //每一次运算消去一个1，直到为0终止循环
            z &= z - 1; // remove the rightmost bit of '1'  // z= z & (z-1);
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
        /**
         * while(xor > 0){
         *    count += xor & 1;
         *    xor = xor >> 1;
         * }
         */
        return count;
    }
    /**
     * int: 32位
     */
    public int hammingDistance_3(int x, int y) {
        int distance = 0;
        int s = x ^ y;
        for (int i = 0; i < 32; i++) {
            distance += ((s >> i) & 1);
        }
        return distance;
    }
}
