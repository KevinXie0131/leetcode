package com.answer.math;

public class Q171_Excel_Sheet_Column_Number {
    /**
     * Given a string columnTitle that represents the column title as appears in an Excel sheet,
     * return its corresponding column number.
     * 给你一个字符串 columnTitle ，表示 Excel 表格中的列名称。返回 该列名称对应的列序号 。
     */
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
            int num = c - 'A' + 1; // 因为 A 表示 1，所以减法后需要每个数加 1，计算其代表的数值 num = 字母 - ‘A’ + 1
            result = result * 26 + num;
        }

        return result;
    }
}
