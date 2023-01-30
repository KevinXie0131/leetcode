package com.answer.math;

public class Q342_Power_of_Four {
    public static void main(String[] args) {
        System.out.println(isPowerOfFour_6(16));
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
     * Convert brute force to recursion
     */
    public static boolean isPowerOfFour_1a(int n) {
        if (n < 1) return false;
        if (n == 1) return true;
        if (n % 4 != 0) return false;
        return isPowerOfFour_1a(n / 4);
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

    public boolean isPowerOfFou_3(int n) {
        return n > 0 && (n & (n - 1)) == 0 && (n & 0xaaaaaaaa) == 0;
    }
    public boolean isPowerOfFour_4(int n) {
        return n > 0 && (n & (n - 1)) == 0 && n % 3 == 1;
    }
    /**
     * use Sqrt() first
     */
    public boolean isPowerOfFour_5(int n) {
        if (n <= 0) return false;
        int x = (int)Math.sqrt(n);
        return x * x == n && (x & -x) == x;

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
}
