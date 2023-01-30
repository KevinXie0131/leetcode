package com.answer.math;

public class Q231_Power_of_Two {
    public static void main(String[] args) {
        System.out.println( isPowerOfTwo(16));
        System.out.println( findSqrt(7));
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
