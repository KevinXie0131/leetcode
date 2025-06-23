package com.answer.binarysearch;

public class Q367_Valid_Perfect_Square {
    /**
     * 有效的完全平方数
     * 一个正整数 num 。如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
     * Given a positive integer num, return true if num is a perfect square or false otherwise.
     * 完全平方数 是一个可以写成某个整数的平方的整数。换句话说，它可以写成某个整数和自身的乘积。
     * A perfect square is an integer that is the square of an integer. In other words, it is the product of some integer with itself.
     * 不能使用任何内置的库函数，如  sqrt 。You must not use any built-in library function, such as sqrt
     *
     * 示例 1：
     *  输入：num = 16
     *  输出：true
     *  解释：返回 true ，因为 4 * 4 = 16 且 4 是一个整数。
     * 示例 2：
     *  输入：num = 14
     *  输出：false
     *  解释：返回 false ，因为 3.742 * 3.742 = 14 但 3.742 不是一个整数。
     */
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
