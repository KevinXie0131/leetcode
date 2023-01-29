package com.answer.priorityqueue;

public class Q263_Ugly_Number {

    /**
     * An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.
     */
    public boolean isUgly(int n) {
        if(n <= 0) return false;
        int[] factors = {2, 3, 5};
        for(int factor : factors){
            while(n % factor == 0){
                n = n / factor;
            }
        }
        return n == 1;
    }
    /**
     *
     */
    public boolean isUgly_1(int n) {
        if (n <= 0) return false;

        while(n % 2 == 0) n = n / 2;
        while(n % 3 == 0) n = n / 3;
        while(n % 5 == 0) n = n / 5;

        return n == 1;
    }
}
