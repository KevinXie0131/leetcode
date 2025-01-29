package com.answer.math;

public class Q342_Power_of_Four {
    public static void main(String[] args) {
        System.out.println(isPowerOfFour_6(16));
    }
    /**
     * Bineary Search 二分查找法
     */
    public boolean isPowerOfFour(int n) {
        int left = 0, right = n;

        while(left <= right){
            int mid = (left + right) >>> 1;
            double val = Math.pow(4, mid); // 使用都double，防止整数溢出 pow()本身返回的是double
            if(val == n){
                return true;
            }else if(val > n){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return false;
    }
    /**
     * Brute force
     */
    public static boolean isPowerOfFour_1(int n) {
        if (n < 1) return false;
        while (n % 4 == 0) {
            n = n / 4;
        }
        return n == 1;
    }
    /**
     * Convert brute force to recursion
     */
    public static boolean isPowerOfFour_1a(int n) {
        if (n < 1) return false;
        if (n == 1) return true;
        if (n % 4 != 0) return false;
        return isPowerOfFour_1a(n / 4);
    }
    /**
     * Bit manipulation 位运算
     */
    public boolean isPowerOfFour_2(int n) {
        if (n < 1) return false;
        for (int i = 1, sub = 1; i < 32; i = i + 2, sub <<= 2)
            if (sub == n) return true;
        return false;
    }

    public boolean isPowerOfFou_3(int n) {
        return n > 0 && (n & (n - 1)) == 0 && (n & 0xaaaaaaaa) == 0; // 4的幂一定是2的幂, 但是2的幂不一定是4的幂，比如2^3 = 8不是4的幂
    }

    public boolean isPowerOfFour_4(int n) {
        return n > 0 && (n & (n - 1)) == 0 && n % 3 == 1; // 4的幂 % 3 = 1， 而2的幂 % 3 = 2
    }
    /**
     * use Sqrt() first
     */
    public boolean isPowerOfFour_5(int n) {
        if (n <= 0) {
            return false;
        }
        int x = (int)Math.sqrt(n); // 转化为2的幂的判断
        return x * x == n && (x & -x) == x;

    }
    /**
     * Math
     */
    public static boolean isPowerOfFour_6(int num) {
        System.out.println(Math.log(num));
        System.out.println(Math.log(2));
        System.out.println(Math.log(num) / Math.log(2));
        return (num > 0) && (Math.log(num) / Math.log(4) % 2 == 0);
    }
}
