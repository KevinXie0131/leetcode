package com.answer.math;

public class Q168_Excel_Sheet_Column_Title {
    /**
     * Given an integer columnNumber, return its corresponding column title as it appears in an Excel sheet.
     * 给你一个整数 columnNumber ，返回它在 Excel 表中相对应的列名称。
     */
    public static void main(String[] args) {
        int columnNumber = 27;
        String s = convertToTitle(columnNumber);
        System.out.println(s);
    }
    /**
     * 这是一道从 1 开始的的 26 进制转换题
     * 一般性的进制转换题目无须进行额外操作，是因为我们是在「每一位数值范围在 [0,x)」的前提下进行「逢 x 进一」。
     * 但本题需要我们将从 1 开始，因此在执行「进制转换」操作前，我们需要先对 columnNumber 执行减一操作，从而实现整体偏移。
     *
     * For example:
     * A -> 1
     * B -> 2
     * C -> 3
     * ...
     * Z -> 26
     * AA -> 27
     * AB -> 28
     */
    static public String convertToTitle(int columnNumber) {
        StringBuffer sb = new StringBuffer();
        while (columnNumber > 0) {
            columnNumber--; // 需要将输入的数减一，以便将0到25映射到'A'到'Z'。
            sb.append((char) (columnNumber % 26 + 'A'));
            columnNumber /= 26;
        }

        return sb.reverse().toString();
    }
    /**
     * From 睡不醒的鲤鱼
     * A -> 1 (0)
     * ...
     * Z -> 26 (25)
     */
    public static String convertToTitle_1(int columnNumber) {
        StringBuffer sb = new StringBuffer();

        while (columnNumber > 0) {
            int remainder = columnNumber % 26;
            if (remainder == 0) {
                remainder = 26;
                columnNumber -= 26;
            }
            sb.append((char) (remainder + 'A' - 1));
            columnNumber /= 26;
        }
        return sb.reverse().toString();
    }
}
