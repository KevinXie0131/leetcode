package com.answer.math.carry;

import org.omg.Messaging.SyncScopeHelper;

public class Q67_Add_Binary {
    /**
     * Given two binary strings a and b, return their sum as a binary string.
     * 给你两个二进制字符串 a 和 b ，以二进制字符串的形式返回它们的和。
     */
    public static void main(String[] args) {
        String a = "11", b = "1";
        System.out.println(addBinary(a, b));
    }
    /**
     * 二进制字符串相加
     * 将两个字符串较短的用 0 补齐，使得两个字符串长度一致，然后从末尾进行遍历计算，得到最终结果。
     */
    static public String addBinary(String a, String b) {
        StringBuffer sb = new StringBuffer();
        int n = a.length();
        int m = b.length();
        int i = n - 1, j = m - 1;
        int carry = 0;
        while(i >= 0 || j >= 0){
            int x = i >= 0 ? a.charAt(i) - '0' : 0;
            int y = j >= 0 ? b.charAt(j) - '0' : 0;
            int sum = x + y + carry;
            sb.insert(0, sum % 2); // Change to 2 since is it binary
            carry = sum / 2; // 二进制

            i--;
            j--;
        }
        if(carry > 0){
            sb.insert(0, carry); // 最后如果有进位，则在前方进行字符串拼接添加进位
        }
        return sb.toString();
    }
    /**
     * 二进制下列竖式计算，从后向前遍历两个字符串，使用 carry 保存进位情况，按位相加即可。
     * move carry into while
     */
    public String addBinary_1(String a, String b) {
        StringBuffer sb = new StringBuffer();
        int n = a.length();
        int m = b.length();
        int i = n - 1, j = m - 1;
        int carry = 0;
        while(i >= 0 || j >= 0 || carry > 0){
            int x = i >= 0 ? a.charAt(i) - '0' : 0;
            int y = j >= 0 ? b.charAt(j) - '0' : 0;
            int sum = x + y + carry;
            sb.append(sum % 2);// 满二进一
            carry = sum / 2;

            i--;
            j--;
        }
        return sb.reverse().toString(); // 到一个反向字符，需要最后再进行翻转
    }
    /**
     * Runtime Error
     * 194 / 296 testcases passed
     * java.lang.NumberFormatException: For input string: "10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101" under radix 2
     */
    public String addBinary3(String a, String b) {
        return Integer.toBinaryString(
                Integer.parseInt(a, 2) + Integer.parseInt(b, 2)
        );
    }
    /**
     * 方法一：模拟
     */
    public String addBinary5(String a, String b) {
        StringBuffer ans = new StringBuffer();

        int n = Math.max(a.length(), b.length()), carry = 0;
        for (int i = 0; i < n; ++i) {
            carry += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0;
            carry += i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;
            ans.append((char) (carry % 2 + '0'));
            carry /= 2;
        }
        if (carry > 0) {
            ans.append('1');
        }
        return ans.reverse().toString();
    }
}
