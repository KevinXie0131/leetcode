package com.answer.math;

import java.util.Arrays;

public class Q342_Power_of_Four {
    /**
     * Given an integer n, return true if it is a power of four. Otherwise, return false.
     * An integer n is a power of four, if there exists an integer x such that n == 4^x
     * 4的幂
     * 给定一个整数，写一个函数来判断它是否是 4 的幂次方。如果是，返回 true ；否则，返回 false 。
     * 整数 n 是 4 的幂次方需满足：存在整数 x 使得 n == 4^x
     */
    public static void main(String[] args) {
        System.out.println(isPowerOfFour_7(64));
    }
    /**
     * Bineary Search 二分查找法
     */
    public boolean isPowerOfFour(int n) {
        int left = 0, right = n;

        while(left <= right){
            int mid = (left + right) >>> 1;
            // double pow(double a, double b)
            double val = Math.pow(4, mid); // 使用都double，防止整数溢出 pow()本身返回的是double
            if(val == n){
                return true;
            }else if(val > n){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return false;
    }
    /**
     * Brute force
     */
    public static boolean isPowerOfFour_1(int n) {
        if (n < 1) return false;
        while (n % 4 == 0) {
            n = n / 4;
        }
        return n == 1;
    }
    /**
     * Convert brute force to recursion
     */
    public static boolean isPowerOfFour_1a(int n) {
        if (n < 1) return false;        //负数不可能是4的幂
        if (n == 1) return true;        //1也是4的幂
        if (n % 4 != 0) return false;   //如果不能够被4整除，肯定不是4的幂
        return isPowerOfFour_1a(n / 4); //如果能被4整除，除以4然后递归调用
    }
    /**
     * Bit manipulation 位运算
     */
    public boolean isPowerOfFour_2(int n) {
        if (n < 1) return false;

        int sub = 1;
        for (int i = 1; i < 32; i = i + 2){
            if (sub == n) {
                return true;
            }
            sub <<= 2;
        }
        return false;
    }
    /**
     * 方法一：二进制表示中 1 的位置
     * 4的幂一定是2的幂, 但是2的幂不一定是4的幂，比如2^3 = 8不是4的幂
     * n & (n - 1) 判断 n 是否是 2 的幂
     *
     * 如果 n 是 4 的幂，那么 n 的二进制表示中有且仅有一个 1，并且这个 1 出现在从低位开始的第偶数个二进制位上
     * （这是因为这个 1 后面必须有偶数个 0）。
     * 将 n 和 mask 进行按位与运算，如果结果为 0，说明 n 二进制表示中的 1 出现在偶数的位置，否则说明其出现在奇数的位置
     * mask 的二进制表示为10101010101010101010101010101010, 16 进制的形式AAAAAAAA
     */
    public boolean isPowerOfFou_3(int n) {
        return n > 0 && (n & (n - 1)) == 0 && (n & 0xaaaaaaaa) == 0; // 2AAAAAAA works, since n 是一个「有符号」的 32 位整数
    }
    /**
     * 方法二：取模性质
     */
    public boolean isPowerOfFour_4(int n) {
        return n > 0 && (n & (n - 1)) == 0 && n % 3 == 1; // 4的幂 % 3 = 1， 而2的幂 % 3 = 2
    }
    /**
     * 找规律就行 首先n 要大于零
     * 1 --> 1;4 --> 100;8 --> 10000;16 --> 1000000 前置零为单数个 且只有一个1
     */
    public boolean isPowerOfFour_4a(int n) {
        return n > 0 && Integer.numberOfLeadingZeros(n) % 2 == 1 && Integer.bitCount(n) == 1;
    }
    /**
     * use Sqrt() first
     * sqrt + lowbit
     * 我们可以先对 n 执行 sqrt 函数，然后应用 lowbit 函数快速判断 sqrt(n) 是否为 2 的幂
     */
    public boolean isPowerOfFour_5(int n) {
        if (n <= 0) {
            return false;
        }
        int x = (int)Math.sqrt(n); // 转化为2的幂的判断
        return x * x == n && (x & -x) == x;  // (x & -x) == x是判断x是否是2的倍数
    }
    // 一个简单的 sqrt 的实现。复杂度为 O(logn)
    int getSqrt(int n) {
        long l = 0, r = n;
        while (l < r) {
            long mid = l + r >> 1;
            if (mid * mid >= n) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return (int)r;
    }
    /**
     * Math
     */
    public static boolean isPowerOfFour_6(int num) {
        System.out.println(Math.log(num));
        System.out.println(Math.log(2));
        System.out.println(Math.log(num) / Math.log(2));
        return (num > 0) && (Math.log(num) / Math.log(4) % 2 == 0);
    }
    /**
     *
     */
    public static boolean isPowerOfFour_7(int num) {
        System.out.println(Math.log(num));
        System.out.println(Math.log(4));
        System.out.println(Math.log(num) / Math.log(4));
        return (num > 0) && (Math.log(num) / Math.log(4) % 1 == 0);
    }
}
