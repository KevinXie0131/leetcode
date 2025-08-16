package com.answer.priorityqueue;

public class Q263_Ugly_Number {
    /**
     * 丑数 就是只包含质因数 2、3 和 5 的 正 整数。
     * An ugly number is a positive integer which does not have a prime factor other than 2, 3, and 5.
     * 给你一个整数 n ，请你判断 n 是否为 丑数 。如果是，返回 true ；否则，返回 false 。
     * 示例 1：
     *  输入：n = 6
     *  输出：true
     *  解释：6 = 2 × 3
     * 示例 2：
     *  输入：n = 1
     *  输出：true
     *  解释：1 没有质因数(prime factors)。
     * 示例 3：
     *  输入：n = 14
     *  输出：false
     *  解释：14 不是丑数，因为它包含了另外一个质因数 7 。
     */
    /**
     * An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.
     * 为判断 n 是否满足上述形式，可以对 n 反复除以 2,3,5，直到 n 不再包含质因数 2,3,5。若剩下的数等于 1，则说明 n 不包含其他质因数，是丑数
     * 时间复杂度：当 n 是以 2 为底的对数时，需要除以 logn 次。复杂度为 O(logn)
     */
    public boolean isUgly(int n) {
        if(n <= 0){
            return false;
        }
        int[] factors = {2, 3, 5};
        for(int factor : factors){
            while(n % factor == 0){
                n = n / factor;
            }
        }
        return n == 1;
    }
    /**
     * 对 n 执行 2 3 5 的整除操作即可，直到 n 被除干净，如果 n 最终为 1 说明是丑数
     * 注意，2 3 5 先除哪一个都是可以的，因为乘法本身具有交换律。
     */
    public boolean isUgly_1(int n) {
        if (n <= 0){
            return false;
        }
        while(n % 2 == 0) n = n / 2;
        while(n % 3 == 0) n = n / 3;
        while(n % 5 == 0) n = n / 5;

        return n == 1;
    }
}
