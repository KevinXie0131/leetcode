package com.answer.bit;

public class Q29_Divide_Two_Integers {
    public static void main(String[] args) {
        int dividend = -10, divisor = 3;
        System.out.println(divide(dividend, divisor));
    }
    /**
     * Cannot handle -2147483648 & -1
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

}
