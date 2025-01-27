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

        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (Math.pow(2, mid) == n) {
                return true;
            } else if (Math.pow(2, mid) > n) {
                right = mid - 1;
            } else {
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
     * 常用位操作
     * 判断奇偶
     * (x & 1) == 1 ---等价---> (x % 2 == 1)
     * (x & 1) == 0 ---等价---> (x % 2 == 0)
     * x / 2 ---等价---> x >> 1
     * x &= (x - 1) ------> 把x最低位的二进制1给去掉
     * x & -x -----> 得到最低位的1
     * x & ~x -----> 0
     */
    public static boolean isPowerOfTwo_4(int n) {
        if (n < 1){
            return false;
        }
        while (n != 1){
            if (n % 2 == 1) {
                return false;
            }
            n = n / 2; // 除法
        }
        return true;
    }
    // 时间复杂度：O(logn)
    public static boolean isPowerOfTwo_4a(int n) {
        if (n < 1){
            return false;
        }
        while (n % 2 == 0) {
            n >>= 1; // 通过右位操作，把n做2的幂次方的逆逻辑 (相当于除法)
        }
        return n == 1; // 尝试将 n 除干净，如果最后剩余数值为 1 则说明开始是 2 的幂。
    }
    /**
     * Bit manipulation 位运算
     * Approach 2: Bitwise operators : Turn off the Rightmost 1-bit
     * 直接利用上面掉到的常用方法 n & (n - 1) 去除最低位的1。
     * 如果 n 的二进行里面只有一个1，那么去掉最低位的1，就只可能是0，要么非0
     */
    public static boolean isPowerOfTwo_2(int n) {
        return n > 0 && (n & (n - 1)) == 0;  // For example: 1000 & 0111 = 0
        // return n > 0 && (n & -n) == n;
    }
    /**
     * Bit manipulation
     * 把 n 作为除数， 2^30 作为被除数，那么，如果 n 是 2 的幂次方的话，余数肯定为0
     */
    public boolean isPowerOfTwo_3 ( int n){
        final int BIG = 1 << 30;
        return n > 0 && BIG % n == 0;
    }
    /**
     * Bit manipulation
     * 通过左位移操作，列举出 int 型内的全部2的幂次方 (相当于乘法)
     */
    public static boolean isPowerOfTwo_5 ( int n){
        if (n < 1){
            return false;
        }
        for (int i = 1, sub = 1; i < 32; ++i, sub <<= 1)
            if (sub == n) {
                return true;
            }
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
