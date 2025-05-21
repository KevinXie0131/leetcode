package com.answer.bit;

public class Q201_Bitwise_AND_of_Numbers_Range {
    public static void main(String[] args) {
        int left = 5, right = 7;
        System.out.println(rangeBitwiseAnd(left, right)); // 4
    }
    /**
     * Given two integers left and right that represent the range [left, right],
     * return the bitwise AND of all numbers in this range, inclusive.
     * 给你两个整数 left 和 right ，表示区间 [left, right] ，返回此区间内所有数字 按位与 的结果（包含 left 、right 端点）。
     */
    static public int rangeBitwiseAnd(int left, int right) { // Time Limit Exceeded for [1, 2147483647]
        int res = left;
        for (int num = left + 1; num <= right; num++) {
            res &= num;
        }
        return res;
    }
    /**
     * 两数都向右移，直到移到他俩相等，这样就找到了他俩的公共前缀，
     * 刚刚向右移了几位，现在再向左移几位，这样的话就相当于把非公共前缀后面的非零位都变成0
     */
    public int rangeBitwiseAnd_1(int left, int right) { // Time Limit Exceeded for [1, 2147483647]
        int shift = 0;
        // 为什么是 left != right 因为保证的是不能全移没了，
        // 向右位移 最后的结果是 left 和 right 找到二进制位的公共前缀  这时候left 和right 已经相等了
        while (left != right) {
            left >>= 1;  // 都同步向右移
            right >>= 1;
            shift++;  // 记录移动的次数
        }
        return left << shift;  // 再向左移动刚刚向右移动的次数
    }
}
