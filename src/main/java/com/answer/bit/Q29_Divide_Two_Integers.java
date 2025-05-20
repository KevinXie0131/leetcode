package com.answer.bit;

import java.util.*;

public class Q29_Divide_Two_Integers {
    /**
     * Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.
     * The integer division should truncate toward zero, which means losing its fractional part. For example, 8.345 would be truncated to 8, and -2.7335 would be truncated to -2.
     * Return the quotient after dividing dividend by divisor.
     * 给你两个整数，被除数 dividend 和除数 divisor。将两数相除，要求 不使用 乘法、除法和取余运算。
     * 整数除法应该向零截断，也就是截去（truncate）其小数部分。例如，8.345 将被截断为 8 ，-2.7335 将被截断至 -2 。
     * 返回被除数 dividend 除以除数 divisor 得到的 商 。
     */
    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE + 1);  // INT_MAX + 1 ≡ INT_MIN
        System.out.println(Integer.MIN_VALUE - 1);  // INT_MIN - 1 ≡ INT_MAX

        int dividend = 15, divisor = 2;
        System.out.println(divide_4(dividend, divisor));
    }
    /**
     * Approach 1: Repeated Subtraction - Brute force
     * This approach won't pass the large test cases. Cannot handle -2147483648 & -1
     */
    public static int divide(int dividend, int divisor) {
        boolean dividendFlag = false;
        boolean divisorFlag = false;
        if(dividend < 0){
            dividendFlag = true;
            dividend = -dividend;
        }
        if(divisor < 0){
            divisorFlag = true;
            divisor = -divisor;
        }

        long res = 0;
        while(dividend >= divisor){
            dividend = dividend - divisor;
            res++;
        }

        return dividendFlag ^ divisorFlag ? (int)-res : (int)res; // ^ 异或
    }
    /**
     * 理解一（不限制 long）
     * 现在两者都满足 x>=0，然后利用 dividend 和 divisor 均为 int，可以确定答案的绝对值落在 [0,dividend] 范围内（当且仅当 divisor 是范围在 [0,1] 的浮点数时，答案会大于 dividend）；
     * 假设答案为 x，那么在以 x 为分割点的整数数轴上，具有「二段性」，因此我们可以二分找到该分割点：
     *      大于 x 的数 y 满足 y∗b>a；
     *      小于等于 x 的数 y 不满足 y∗b>a；
     */
    int INF = Integer.MAX_VALUE;
    public int divide_1a(int _a, int _b) {
        long a = _a, b = _b;
        boolean flag = false;
        if ((a < 0 && b > 0) || (a > 0 && b < 0)) flag = true;
        if (a < 0) a = -a;
        if (b < 0) b = -b;
        long l = 0, r = a;
        while (l < r) {
            // 考虑 l = 0, r = 1 的简单情况，如果不 +1 的话，l + r >> 1 等于 0 + 1 / 2，l 仍然是 0，陷入死循环。
            long mid = l + r + 1 >> 1; // +1 操作主要是为了避免发生「死循环」，因为 >> 和 直接使用 / 一样，都属于「下取整」操作。
            if (mul(mid, b) <= a) l = mid;
            else r = mid - 1;
        }
        r = flag ? -r : r;
        if (r > INF || r < -INF - 1) return INF;
        return (int)r;
    }
    // 根据「二段性」分析，我们发现二分的 check 实现需要用到乘法，
    // 因此我们需要实现一个「不用乘法符号」的乘法实现（这可以使用倍增思想来实现 mul 操作）
    long mul(long a, long k) {
        long ans = 0;
        while (k > 0) {
            if ((k & 1) == 1) ans += a; // 「快速乘法」模板，采用了倍增的思想
            k >>= 1;
            a += a;
        }
        return ans;
    }
    /**
     * Approach 2: Repeated Exponential Searches (like binary search) - Time Limit Exceeded
     */
    public static int divide_2(int dividend, int divisor) {
        if(dividend == 0) return 0;
        if(divisor == 1) return dividend;
        // 考虑被除数为最小值的情况
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == 1) {
                return Integer.MIN_VALUE;
            }
            if (divisor == -1) {
                return Integer.MAX_VALUE;
            }
        }
        // 考虑除数为最小值的情况
        if (divisor == Integer.MIN_VALUE) {
            return dividend == Integer.MIN_VALUE ? 1 : 0;
        }

        boolean sign  = false;
        if (dividend < 0) {
            dividend = -dividend;
            sign  = !sign ;
        }
        if (divisor < 0) {
            divisor = -divisor;
            sign  = !sign ;
        }

        int quotient = 0;
        /* Once the divisor is bigger than the current dividend,
         * we can't fit any more copies of the divisor into it. */
        while (dividend >= divisor) {
            /* Now that we're in the loop, we know it'll fit at least once as
             * divivend >= divisor */
            int powerOfTwo = 1;
            int value = divisor;
            /* Check if double the current value is too big. If not, continue doubling.
             * If it is too big, stop doubling and continue with the next step */
            while (value + value < dividend) {
                value += value;
                powerOfTwo += powerOfTwo;
            }
            // We have been able to subtract divisor another powerOfTwo times.
            quotient += powerOfTwo;
            // Remove value so far so that we can continue the process with remainder.
            dividend -= value;
        }

        return sign == true ? -quotient : quotient;
    }
    /**
     * Approach 3: Adding Powers of Two
     */
    private static int HALF_INT_MIN = -1073741824;// -2**30;

    public static int divide_4(int dividend, int divisor) {

        // Special case: overflow.
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        /* We need to convert both numbers to negatives.
         * Also, we count the number of negatives signs. */
        int negatives = 2;
        if (dividend > 0) {
            negatives--;
            dividend = -dividend;
        }
        if (divisor > 0) {
            negatives--;
            divisor = -divisor;
        }

        ArrayList<Integer> doubles = new ArrayList<>();
        ArrayList<Integer> powersOfTwo = new ArrayList<>();

        /* Nothing too exciting here, we're just making a list of doubles of 1 and
         * the divisor. This is pretty much the same as Approach 2, except we're
         * actually storing the values this time. */
        int powerOfTwo = -1;
        while (divisor >= dividend) {
            doubles.add(divisor);
            powersOfTwo.add(powerOfTwo);
            // Prevent needless overflows from occurring...
            if (divisor < HALF_INT_MIN) {
                break;
            }
            divisor += divisor;
            powerOfTwo += powerOfTwo;
        }

        int quotient = 0;
        /* Go from largest double to smallest, checking if the current double fits.
         * into the remainder of the dividend. */
        for (int i = doubles.size() - 1; i >= 0; i--) {
            if (doubles.get(i) >= dividend) {
                // If it does fit, add the current powerOfTwo to the quotient.
                quotient += powersOfTwo.get(i);
                // Update dividend to take into account the bit we've now removed.
                dividend -= doubles.get(i);
            }
        }

        /* If there was originally one negative sign, then
         * the quotient remains negative. Otherwise, switch
         * it to positive. */
        if (negatives != 1) {
            return -quotient;
        }
        return quotient;
    }
}
