package com.answer.math;

public class Q171_Excel_Sheet_Column_Number {
    /**
     * Refer to Q168 Excel Sheet Column Title
     * For example:
     * A -> 1
     * B -> 2
     * C -> 3
     * ...
     * Z -> 26
     * AA -> 27
     * AB -> 28
     */
    public static void main(String[] args) {
        String s = "AB"; // 26^1 * 1 + 26^0 * 2 = 28
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
