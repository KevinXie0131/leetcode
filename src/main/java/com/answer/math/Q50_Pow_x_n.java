package com.answer.math;

public class Q50_Pow_x_n {
    /**
     * Implement pow(x, n), which calculates x raised to the power n (i.e., x^n).
     * 实现 pow(x, n) ，即计算 x 的整数 n 次幂函数（即，x^n ）。
     */
    public static void main(String[] args) {
        double x = 2.00000;
        int n = 5;
        double res = myPow_2(x, n);
        System.out.println(res);
    }
    /**
     * 快速幂 + 迭代
     * 例如 3^13 = 3^8 + 3^4 + 3^1
     * n^9 = n^1*1 + n^0*2 + n^0*4 + n^1*8
     * 时间复杂度：O(logn)，即为对 n 进行二进制拆分的时间复杂度。
     * 空间复杂度：O(1)。
     */
    static public double myPow_0(double x, int n) {
        double result = 1;
        double tmp = x;  // 贡献的初始值为 x

        if(n < 0){
            return 1 / myPow(x, -n); // 考虑负数
        }
        while(n > 0){  // 在对 n 进行二进制拆分的同时计算答案
            if((n & 1) == 1){ // 相当于n % 2 == 1  位运算&要加括号
                result *= tmp;   // 如果 n 二进制表示的最低位为 1，那么需要计入贡献
            }
            n = n >> 1;  // 舍弃 n 二进制表示的最低位，这样我们每次只要判断最低位即可
            tmp *= tmp; // 将贡献不断地平方

        }
        return result;
    }
    /**
     * Approach 3: Fast Power Algorithm Iterative 快速幂
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
     * Official answer - Time Limit Exceeded for 1.00000 & 2147483647
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
     * Approach 2: Fast Power Algorithm Recursive 快速幂 + 递归
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
     * 方法一：分而治之（自顶向下思考问题）
     * 我们的目的是 降低指数的值，因此可以将问题进行不断拆分，直到不能拆分为止，再一层一层向上传递结果。
     */
    public double myPow_5(double x, int n) {
        // Java 代码中 int32 变量 n∈[−2147483648,2147483647] ，因此当 n=−2147483648 时执行 n=−n 会因越界而赋值出错。
        // 解决方法是先将 n 存入 long 变量 b ，后面用 b 操作即可
        long b = n;
        if (n < 0) {
            return 1 / recursion_1(x, -b);
        }
        return recursion_1(x, b);
    }

    private double recursion_1(double x, long n) {
        if (n == 0) {
            return 1;
        }
        if ((n % 2) == 1) {
            return x * recursion_1(x, n - 1);
        }
        return recursion_1(x * x, n / 2);
    }
}
