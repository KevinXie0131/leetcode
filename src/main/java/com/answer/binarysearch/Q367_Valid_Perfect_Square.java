package com.answer.binarysearch;

public class Q367_Valid_Perfect_Square {
    public static void main(String[] args) {
        boolean isFound = isPerfectSquare(2147483647);
        System.out.println(isFound);
    }
    /**
     * Approach 2: Binary Search - Time Limit Exceeded for 2147483647
     * use long instead
     *
     * 考虑使用二分查找来优化方法二中的搜索过程。因为 num 是正整数，所以若正整数 x 满足 x*x=num，则 x 一定满足 1≤x≤num。
     * 于是我们可以将 1 和 num 作为二分查找搜索区间的初始边界。
     */
    public static boolean isPerfectSquare(int num) {
        long left = 1;
        long right = num;
        while(left <= right){
            long mid = (left + right) >>> 1;
            if( mid * mid == num){
                return true;
            }else if( mid * mid < num){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return false;
    }
    /**
     * Approach 1: Brute Force
     */
    public boolean isPerfectSquare_1(int num) {
        long x = 1, square = 1;
        while (square <= num) {
            if (square == num) {
                return true;
            }
            ++x;
            square = x * x;
        }
        return false;
    }
    /**
     * Approach 2: Newton's Method
     */
    public boolean isPerfectSquare_2(int num) {
        if (num < 2) return true;

        long x = num / 2;
        while (x * x > num) {
            x = (x + num / x) / 2;
        }
        return (x * x == num);
    }
}
