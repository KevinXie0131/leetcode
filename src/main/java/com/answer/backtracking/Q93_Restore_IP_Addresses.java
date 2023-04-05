package com.answer.backtracking;

import java.util.*;

public class Q93_Restore_IP_Addresses {

    List<String> result = new ArrayList<String>(); // 记录结果
    // startIndex: 搜索的起始位置，pointNum:添加逗点的数量
    public List<String> restoreIpAddresses(String s) {
        if(s.length()>12 || s.length()<4) return result;
        backtracking(s,0,0);
        return result;
    }

    public void backtracking(String s, int startIndex, int pointNum){
        // 逗点数量为3时，分隔结束
        // 判断第四段⼦字符串是否合法，如果合法就放进result中
        if(pointNum == 3 && isValid(s, startIndex, s.length()-1)){
            result.add(s);
            return;
        }
        if (s.length() - startIndex > 3 * (4 - pointNum)) {
            return;
        }
        for(int i = startIndex; i < s.length(); i++){
            if(isValid(s, startIndex, i)){ // 判断 [startIndex,i] 这个区间的⼦串是否合法
                s= s.substring(0, i+1)+"."+s.substring(i+1); // 在i的后⾯插⼊⼀个逗点
                pointNum++;
                backtracking(s,i+2,pointNum); // 插⼊逗点之后下⼀个⼦串的起始位置为i+2
                pointNum--; // 回溯
                s = s.substring(0, i+1)+s.substring(i+2); // 回溯删掉逗点
            } else {
                break; // 不合法，直接结束本层循环
            }
        }
    }

    // 判断字符串s在左闭⼜闭区间[start, end]所组成的数字是否合法
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
