package com.leetcode.backtracking;

import java.util.*;

public class Test36_IP_Address {
    public static void main(String[] args) {
        String s = "25525511135";
        List<String> res = restoreIpAddresses(s);
        System.out.println(res);
    }

    static List<String> result = new ArrayList<String>();

    public static List<String> restoreIpAddresses(String s) {
        if(s.length()>12) return result;
        backtracking(s,0,0);
        return result;
    }

    public static void backtracking(String s, int startIndex, int pointNum){
        if (pointNum == 3) {
            if(isValid(s, startIndex, s.length()-1)){
                result.add(s);
            }
            return;
        }
        for(int i = startIndex; i < s.length(); i++){
            if(isValid(s, startIndex, i)){
                s = s.substring(0, i+1)+"."+s.substring(i+1);
                pointNum++;
                backtracking(s,i+2,pointNum);
                pointNum--;
                s = s.substring(0, i+1)+s.substring(i+2);
            } else {
                break;
            }
        }
    }

    private static Boolean isValid(String s, int start, int end) {
        if (start > end) {
            return false;
        }
        if (s.charAt(start) == '0' && start != end) { // 0开头的数字不合法
            return false;
        }
        int num = 0;
        for (int i = start; i <= end; i++) {
            if (s.charAt(i) > '9' || s.charAt(i) < '0') { // 遇到⾮数字字符不合法
                return false;
            }
            num = num * 10 + (s.charAt(i) - '0');
            if (num > 255) { // 如果⼤于255了不合法
                return false;
            }
        }
        return true;
    }
}
