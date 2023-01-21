package com.answer.math;

public class Q7_Reverse_Integer {

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);

        System.out.println(reverse(-123));
    }

    public static int reverse(int x) {
        int result = 0;

        while(x != 0){
            if (result < Integer.MIN_VALUE / 10 || result > Integer.MAX_VALUE / 10) {
                return 0;
            }

            int digit = x % 10;
            x = x /10;

            result = result * 10 + digit;
        }

        return result;
    }
}
