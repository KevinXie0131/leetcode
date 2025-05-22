package com.answer.math;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Q326_Power_of_Three {
    /**
     * Given an integer n, return true if it is a power of three. Otherwise, return false.
     * An integer n is a power of three, if there exists an integer x such that n == 3^x.
     * 3的幂
     * 给定一个整数，写一个函数来判断它是否是 3 的幂次方。如果是，返回 true ；否则，返回 false 。
     * 整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3^x
     * Follow up: Could you solve it without loops/recursion?
     */
    public static void main(String[] args) {
        System.out.println(isPowerOfThree_5(243));
    }
    /**
     * Binary Search
     */
    public boolean isPowerOfThree(int n) {
        int left = 0, right = n;

        while(left <= right){
            int mid = (left + right) >>> 1;
            double val = Math.pow(3, mid);
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
     * 不断地将 n 除以 3，直到 n=1。如果此过程中 n 无法被 3 整除，就说明 n 不是 3 的幂。
     */
    public static boolean isPowerOfThree_1(int n) {
        if (n < 1) return false;
        while (n % 3 == 0) {
            n = n / 3;
        }
        return n == 1;
    }
    /**
     * 递归法
     */
    public boolean isPowerOfThree_1c(int n) {
        if(n <= 0) return false;
        if (n == 1) return true;
        if (n % 3 != 0) return false;

        return isPowerOfThree_1c(n / 3);
    }

    public boolean isPowerOfThree_1a(int n) {
        for(int x = 0; x <= 31; x++){
            if((long)Math.pow(3, x) == n){
                return true;
            }
        }
        return false;
    }
    /**
     * 在题目给定的 32 位有符号整数的范围内，最大的 3 的幂为 3^19=1162261467。我们只需要判断 n 是否是 3^19的约数即可。
     */
    public boolean isPowerOfThree_1b(int n) {
        return n > 0 && 1162261467 % n == 0;  // return n > 0 && Math.pow(3,19) % n == 0;
    }
    /**
     * Math - has division precision issue
     */
    public static boolean isPowerOfThree_2(int num) {
        System.out.println(Math.log(num));
        System.out.println(Math.log(3));
        System.out.println(Math.log(num) / Math.log(3));
        BigDecimal a = new BigDecimal(Math.log(num));
        BigDecimal b = new BigDecimal(Math.log(3));
        System.out.println(a.divide(b, new MathContext(2, RoundingMode.FLOOR)));

        double res = Math.log(num) / Math.log(3);

        double x = Math.log(num) / Math.log(3);
        return (num > 0) && Math.abs(x - Math.round(x)) < Math.pow(10,-14);
    }

    public boolean isPowerOfThree_3(int n) {
        return n > 0 && Math.pow(3, 19) % n == 0;
    }
    /**
     * This solution is problematic because we start using doubles, which means we are subject to precision errors.
     * This means, we should never use == when comparing doubles. That is because the result of Math.log10(n) / Math.log10(3) could be 5.0000001 or 4.9999999.
     * This effect can be observed by using the function Math.log() instead of Math.log10().
     *
     * In order to fix that, we need to compare the result against an epsilon.
     */
    public static boolean isPowerOfThree_4(int n) {
        System.out.println(Math.log10(n));
        System.out.println( Math.log10(3));
        System.out.println((Math.log10(n) / Math.log10(3)));
        return (Math.log10(n) / Math.log10(3)) % 1 == 0;
    }

    public static boolean isPowerOfThree_5(int n) {
        System.out.println(Math.log(n));
        System.out.println( Math.log(3));
        System.out.println((Math.log(n) / Math.log(3)));
        System.out.println((Math.log(n) / Math.log(3)) % 1);
        return (Math.log(n) / Math.log(3) + Math.pow(10,-9)) % 1 <=  Math.pow(10,-9); //doesn't work
    }
}
