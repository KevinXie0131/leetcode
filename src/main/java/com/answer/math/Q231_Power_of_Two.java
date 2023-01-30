package com.answer.math;

public class Q231_Power_of_Two {
    public static void main(String[] args) {
        System.out.println( isPowerOfTwo_5(3));
        System.out.println( findSqrt(3));
    }

    /**
     * Binary Search
     */
    public static boolean isPowerOfTwo(int n) {
        int left = 0, right = n;

        while(left <= right){
            int mid = (left + right) >>> 1;
            if(Math.pow(2, mid) == n){
                return true;
            }else if(Math.pow(2, mid) > n){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return false;
    }

    /**
     * this can be accepted, but slow
     */
    public static boolean isPowerOfTwo_1(int n) {
        int count = 0;
        while(true){
            if(Math.pow(2, count) == n ){
                System.out.println(count);
                return true;
            }
            count++;
            if(count == 1000) return false;
        }
    }

    /**
     * Brute force
     *
     * (x & 1) == 1 ---等价---> (x % 2 == 1)
     * (x & 1) == 0 ---等价---> (x % 2 == 0)
     * x / 2 ---等价---> x >> 1
     * x &= (x - 1) ------> 把x最低位的二进制1给去掉
     * x & -x -----> 得到最低位的1
     * x & ~x -----> 0
     */
    public static boolean isPowerOfTwo_4(int n) {
        if (n < 1) return false;
        while (n!=1){
            if (n%2 == 1) return false;
            n = n/2;
        }
        return true;
    }
    public static boolean isPowerOfTwo_4a(int n) {
        if (n < 1) return false;
        while (n % 2 == 0) {
            n >>= 1;
        }
        return n == 1;
    }

    /**
     * Bit manipulation
     * Approach 2: Bitwise operators : Turn off the Rightmost 1-bit
     */
    public static boolean isPowerOfTwo_2(int n) {
        return n > 0 && (n & (n - 1)) == 0;
        // return n > 0 && (n & -n) == n;
    }
    /**
     * Bit manipulation
     */
    public boolean isPowerOfTwo_3 ( int n){
        final int BIG = 1 << 30;
        return n > 0 && BIG % n == 0;
    }
    /**
     * Bit manipulation
     */
    public static boolean isPowerOfTwo_5 ( int n){
        if (n < 1) return false;
        for (int i = 1, sub = 1; i < 32; ++i, sub <<= 1)
            if (sub == n) return true;
        return false;
    }

    public static boolean findSqrt(int n) {
        double res = Math.sqrt(n);
        System.out.println(res);
        /**
         * How to test if a double is an integer
         */
        System.out.println(res % 1 == 0);
        System.out.println( res == (int) res);
        System.out.println(Math.ceil(res) == Math.floor(res));
        System.out.println(res == Math.floor(res));
        return true;
    }
}
