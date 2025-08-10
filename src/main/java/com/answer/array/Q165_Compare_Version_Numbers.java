package com.answer.array;

import java.util.Arrays;

public class Q165_Compare_Version_Numbers {
    /**
     * 比较版本号: 给你两个 版本号字符串 version1 和 version2 ，请你比较它们。版本号由被点 '.' 分开的修订号组成。修订号的值 是它 转换为整数 并忽略前导零。
     * 比较版本号时，请按 从左到右的顺序 依次比较它们的修订号。如果其中一个版本字符串的修订号较少，则将缺失的修订号视为 0。
     * 返回规则如下：
     *  如果 version1 < version2 返回 -1，
     *  如果 version1 > version2 返回 1，
     *  除此之外返回 0。
     * 示例 1：
     *  输入：version1 = "1.2", version2 = "1.10"
     *  输出：-1
     *  解释：version1 的第二个修订号为 "2"，version2 的第二个修订号为 "10"：2 < 10，所以 version1 < version2。
     */
    public static void main(String[] args) {
        System.out.println(Integer.parseInt("001"));
        System.out.println(Double.parseDouble("11.2"));
    }
    /**
     * 版本号由被点 '.' 分开的修订号组成。修订号的值 是它 转换为整数 并忽略前导零。
     * A version string consists of revisions separated by dots '.'.
     * The value of the revision is its integer conversion ignoring leading zeros.
     *
     * Approach 1: Split + Parse, Two Pass 字符串分割
     */
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");

        for(int i = 0; i < v1.length || i < v2.length; i++){
            int ver1 = 0, ver2 = 0;
            if(i < v1.length){
                ver1 = Integer.parseInt(v1[i]);
            }
            if(i < v2.length){
                ver2 = Integer.parseInt(v2[i]);
            }

            if(ver1 > ver2){
                return 1;
            }else if(ver1 < ver2){
                return -1;
            }
         //   if (ver1 != ver2) return ver1 > ver2 ? 1 : -1;
        }

        return 0;
    }
    /**
     * Approach 2: Two Pointers, One Pass 双指针
     */
    public int compareVersion_1(String version1, String version2) {
        int i = 0, j = 0;
        int n = version1.length(), m = version2.length();

        while(i < n || j < m){
            int num1 = 0, num2 = 0;
            while(i < n && version1.charAt(i) != '.'){
                num1 = num1 * 10 + (version1.charAt(i) - '0');
                i++;
            }
            i++; // 跳过点号
            while(j < m && version2.charAt(j) != '.'){
                num2 = num2 * 10 + (version2.charAt(j) - '0');
                j++;
            }
            j++; // 跳过点号
            if(num1 != num2){
                return num1 > num2 ? 1 : -1;
            }
        }

        return 0;
    }
}
