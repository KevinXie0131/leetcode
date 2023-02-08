package com.answer.binarysearch;

public class Q367_Valid_Perfect_Square {
    public static void main(String[] args) {
        boolean isFound = isPerfectSquare(14);
        System.out.println(isFound);
    }

    /**
     * Approach 2: Binary Search - Time Limit Exceeded for 2147483647
     */
    public static boolean isPerfectSquare(int num) {
        int left = 1;
        int right = num;
        while(left <= right){
            int mid = (left + right) >>> 1;
            int value = mid * mid;
            if(value == num){
                return true;
            }else if(value < num){
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
