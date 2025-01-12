package com.answer.math;

public class Q50_Pow_x_n {
    public static void main(String[] args) {
        double x = 2.00000;
        int n = 8;
        double res = myPow_2(x, n);
        System.out.println(res);
    }

    /**
     * Approach 1: Brute Force - Time Limit Exceeded for 1.00000 & 2147483647
     */
    static public double myPow(double x, int n) {
        double res = 1.0;

        int k = n;
        if(n < 0){
            k = -n;
        }
        for(int i = 0; i < k; i++){
            res *= x;
        }
        if(n < 0){
            res = 1 / res;
        }
        return res;
    }
    /**
     * Official answer
     */
    public double myPow_1(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        double ans = 1;
        for (long i = 0; i < N; i++){
            ans = ans * x;
        }
        return ans;
    }
    /**
     * Approach 2: Fast Power Algorithm Recursive
     * Time: logN
     * Space: logN
     */
    public static double myPow_2(double x, int n) {
        int k = n;
        if(n < 0){
            k = -n;
        }

        double res = recursion(x, k);
        if(n < 0){
            res = 1.0 / res;
        }
        return res;
    }
    public static double recursion(double x, int n){ // 递归 分治法（时间复杂度：logN）
        if(n == 0){
            return 1.0;
        }

        double half = recursion(x, n/2);
        if(n % 2 == 0){
            return half * half; //偶数
        }else{
            return half * half * x;  //奇数
        }
    }
    /**
     * Approach 3: Fast Power Algorithm Iterative
     */
    public static double myPow_3(double x, int n) {
        int k = n;
        if(n < 0){
            k = -n;
        }

        double res = 1.0;

        for(int i = k; i > 0; i = i / 2){
            if(i % 2 == 1){
                res =  res * x;
            }
            x = x * x;
        }

        if(n < 0){
            res = 1.0 / res;
        }
        return res;
    }

}
