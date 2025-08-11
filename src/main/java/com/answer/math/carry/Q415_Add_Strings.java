package com.answer.math.carry;

public class Q415_Add_Strings {
    /**
     * Given two non-negative integers, num1 and num2 represented as string, return the sum of num1 and num2 as a string.
     * You must solve the problem without using any built-in library for handling large integers (such as BigInteger). You must also not convert the inputs to integers directly.
     * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。
     * 你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。
     */
    /**
     * Similar with Q67 Add Binary
     * 双指针模拟
     */
    public String addStrings(String num1, String num2) {
        StringBuffer sb = new StringBuffer();
        int n = num1.length();
        int m = num2.length();
        int carry = 0;
        int indexA = n - 1;
        int indexB = m - 1;
        while(indexA >= 0 || indexB >= 0 || carry > 0){
            int x = indexA >= 0 ? num1.charAt(indexA) - '0' : 0;
            int y = indexB >= 0 ? num2.charAt(indexB) - '0' : 0;
            int sum = x + y + carry;
            sb.insert(0, sum % 10);
            carry = sum / 10;
            indexA--;
            indexB--;
        }

        return sb.toString();
    }
    /**
     * Convert String to array at first, and it runs faster
     * 可以直接把字符串先转为字符数组, improve运行效率
     */
    public String addStrings_1(String num1, String num2) {
        StringBuilder s = new StringBuilder();
        int i = num1.length() - 1, j = num2.length() - 1, carry = 0;
        char[] num1Char = num1.toCharArray();
        char[] num2Char = num2.toCharArray();
        while (i >= 0 || j >= 0 || carry > 0) {
            int x = i < 0 ? 0 : num1Char[i--] - '0';
            int y = j < 0 ? 0 : num2Char[j--] - '0';
            int sum = x + y + carry;
            s.append(sum % 10);//添加到字符串尾部
            carry = sum / 10;
        }
        return s.reverse().toString();//对字符串反转

    }
    /**
     * Insert to the head of StringBuffer, and it run slower
     * 头部插入比尾部插入效率差很多，因为头部插入需要不停的修改字符的位置。
     */
    public String addStrings_2(String num1, String num2) {
        StringBuilder s = new StringBuilder();
        int carry = 0, i = num1.length() - 1, j = num2.length() - 1;
        while (i >= 0 || j >= 0 || carry > 0) {
            int x = i < 0 ? 0 : num1.charAt(i--) - '0';
            int y = j < 0 ? 0 : num2.charAt(j--) - '0';
            int sum = x + y + carry;
            s.insert(0, sum % 10);//插入到s字符串的第一个位置
            carry = sum / 10;
        }
        return s.toString();
    }
    /**
     * This method is fastest
     */
    public String addStrings_3(String num1, String num2) {
        StringBuilder s = new StringBuilder();
        int i = num1.length() - 1, j = num2.length() - 1, carry = 0;
        while (i >= 0 || j >= 0 || carry != 0) {
            int x = i < 0 ? 0 : num1.charAt(i--) - '0'; // 运行效率并不是很高
            int y = j < 0 ? 0 : num2.charAt(j--) - '0';
            int sum = x + y + carry;
            s.append(sum % 10);//添加到字符串尾部
            carry = sum / 10;
        }
        return s.reverse().toString();//对字符串反转
    }
}
