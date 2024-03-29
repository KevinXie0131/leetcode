package com.answer.math;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Q326_Power_of_Three {
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
     */
    public static boolean isPowerOfThree_1(int n) {
        if (n < 1) return false;
        while (n % 3 == 0) {
            n = n / 3;
        }
        return n == 1;
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
