package com.answer.math;

public class Q171_Excel_Sheet_Column_Number {

    public static void main(String[] args) {
        String s = "AB";
        System.out.println(titleToNumber(s));
    }

    public static int titleToNumber(String columnTitle) {
        int result = 0;
        for(char c : columnTitle.toCharArray()){
            int num = c - 'A' + 1;
            result = result * 26 + num;
        }

        return result;
    }
}
