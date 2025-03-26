package com.answer.math;

public class Q168_Excel_Sheet_Column_Title {
    public static void main(String[] args) {
        int columnNumber = 701;
        String s = convertToTitle_1(columnNumber);
        System.out.println(s);
    }
    /**
     * For example:
     * A -> 1
     * B -> 2
     * C -> 3
     * ...
     * Z -> 26
     * AA -> 27
     * AB -> 28
     */
    public String convertToTitle(int columnNumber) {
        StringBuffer sb = new StringBuffer();
        while (columnNumber > 0) {
            columnNumber--;
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
