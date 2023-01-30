package com.answer.math;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Q326_Power_of_Three {
    public static void main(String[] args) {
        System.out.println(isPowerOfThree_2(243));
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
}
