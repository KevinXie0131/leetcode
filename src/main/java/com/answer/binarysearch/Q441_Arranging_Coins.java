package com.answer.binarysearch;

public class Q441_Arranging_Coins {
    public static void main(String[] args) {
        int res = arrangeCoins(1804289383);
        System.out.println(res);
    }
    /**
     * Approach 1: Binary Search
     */
    public static int arrangeCoins(int n) {
        long left = 1;
        long right = n;

        while(left <= right){
            long mid = (left + right) >>> 1;
            long sum = (mid + 1) * mid / 2;

            if(sum == n){
                return (int)mid;
            }else if(sum < n){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return (int)left - 1; // return (int)right;
    }
    /**
     * Brute force
     */
    public int arrangeCoins_1(int n) {
        long sum = 0;

        for(int i = 1; ; i++){
            sum += i;
            if(sum > n){
                return i - 1;
            }
        }
    }
}
