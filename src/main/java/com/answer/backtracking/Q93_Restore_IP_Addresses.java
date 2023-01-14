package com.answer.backtracking;

import java.util.*;

public class Q93_Restore_IP_Addresses {

    List<String> result = new ArrayList<String>();

    public List<String> restoreIpAddresses(String s) {
        if(s.length()>12 || s.length()<4) return result;
        backtracking(s,0,0);
        return result;
    }

    public void backtracking(String s, int startIndex, int pointNum){
        if(pointNum == 3 && isValid(s, startIndex, s.length()-1)){
            result.add(s);
            return;
        }
        if (s.length() - startIndex > 3 * (4 - pointNum)) {
            return;
        }
        for(int i = startIndex; i < s.length(); i++){
            if(isValid(s, startIndex, i)){
                s= s.substring(0, i+1)+"."+s.substring(i+1);
                pointNum++;
                backtracking(s,i+2,pointNum);
                pointNum--;
                s = s.substring(0, i+1)+s.substring(i+2);
            } else {
                break;
            }
        }
    }

    private Boolean isValid(String s, int start, int end) {
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
