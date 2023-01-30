package com.answer.math;

public class Q342_Power_of_Four {
    public static void main(String[] args) {
        System.out.println(isPowerOfFour_1(16));
    }
    /**
     * Bineary Search
     */
    public boolean isPowerOfFour(int n) {
        int left = 0, right = n;

        while(left <= right){
            int mid = (left + right) >>> 1;
            double val = Math.pow(4, mid);
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
     * Bit manipulation
     */
    public boolean isPowerOfFour_2(int n) {
        if (n < 1) return false;
        for (int i = 1, sub = 1; i < 32; i = i + 2, sub <<= 2)
            if (sub == n) return true;
        return false;
    }
}
