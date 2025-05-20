package com.answer.bit;

public class Q504_Base_7 {
    public static void main(String[] args) {
        int num = 100;
        System.out.println(convertToBase7(num));// 202
        int num1 = -7;
        System.out.println(convertToBase7(num1));// -10
    }
    /**
     * Given an integer num, return a string of its base 7 representation.
     * 给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。
     */
    /**
     * 方法一：倒推 + 迭代
     * 当输入为负时，我们可以先取 num 的绝对值来求七进制，最后再添加负号。
     */
    static public String convertToBase7(int num) {
        if(num == 0)return "0";

        boolean isNeg = false;
        if(num < 0) isNeg = true;

        num = Math.abs(num);

        StringBuffer digits  = new StringBuffer();
        while(num > 0){
            int r = num % 7;
            num /= 7;
            digits .append(r);
        }
        if(isNeg) digits .append("-");

        return  digits .reverse().toString();
    }
}
