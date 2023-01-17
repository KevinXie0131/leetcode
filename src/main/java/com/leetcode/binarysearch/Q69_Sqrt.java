package com.leetcode.binarysearch;

public class Q69_Sqrt {

    public int mySqrt(int x) {
        int left = 0, right = x;
        while(left <= right){
            int mid = (left + right) >>> 1;
            if((long)mid * mid <= x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left - 1;
    }

    /**
     *
     */
    public int mySqrt_1(int x) {
        if (x < 2) return x;

        long num;
        int pivot, left = 2, right = x / 2;
        while (left <= right) {
            pivot = left + (right - left) / 2;
            num = (long)pivot * pivot;
            if (num > x) right = pivot - 1;
            else if (num < x) left = pivot + 1;
            else return pivot;
        }

        return right;
    }
}
