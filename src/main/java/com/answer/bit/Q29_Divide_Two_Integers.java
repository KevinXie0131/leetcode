package com.answer.bit;

import java.util.*;

public class Q29_Divide_Two_Integers {
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

        return dividendFlag ^ divisorFlag ? (int)-res : (int)res;
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
