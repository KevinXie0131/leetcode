package com.answer.backtracking;

import java.util.*;

public class Q131_Palindrome_Partitioning {
    public static void main(String[] args) {
       // System.out.println(partition("abcd"));
        System.out.println(partition("aab"));
    }
    static List<List<String>> result = new ArrayList<List<String>>();
    /**
     * Deque<String> path = new LinkedList<>();
     */
    static Deque<String> path = new ArrayDeque<>(); // 放已经回⽂的⼦串

    static public List<List<String>> partition(String s) {
        backtracking(s, 0);
        return result;
    }

    static public void backtracking(String s, int startIndex){
        // 如果起始位置已经⼤于s的⼤⼩，说明已经找到了⼀组分割⽅案了
        if(startIndex >= s.length()){
            result.add(new ArrayList(path)); //注意创建一个新的copy
            return;
        }
        // 模拟切割线，其实就是index是上一层已经确定了的分割线，i是这一层试图寻找的新分割线
        for(int i = startIndex; i < s.length(); i++){
            System.out.println(s.substring(startIndex, i + 1));
            if(isPalindrome(s, startIndex, i)){ // 是回⽂⼦串
                String newStr = s.substring(startIndex, i + 1); // 获取[startIndex,i]在s中的⼦串
                path.add(newStr);
            } else {
                continue; // 不是回⽂，跳过
            }
            backtracking(s, i + 1); // 寻找i+1为起始位置的⼦串. 切割过的地方不能重复切割所以递归函数需要传入i + 1
            path.removeLast(); // 回溯过程，弹出本次已经填在的⼦串
        }

    }

    static public boolean isPalindrome(String str, int start, int end){
        for(int i = start, j = end; i < j; i++, j--){
            if(str.charAt(i) != str.charAt(j)){
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome1(String str, int start, int end){
        char[] array = str.toCharArray();
        for(int i = start, j = end; i < j; i++, j--){
            if(array[i] != array[j]){
                return false;
            }
        }
        return true;
    }
    /**
     * 回溯+动态规划优化回文串判断
     */
    List<List<String>> result1;
    LinkedList<String> path1;
    boolean[][] dp;

    public List<List<String>> partition1(String s) {
        result1 = new ArrayList<>();
        char[] str = s.toCharArray();
        path1 = new LinkedList<>();
        dp = new boolean[str.length + 1][str.length + 1];
        isPalindrome1(str);
        backtracking1(s, 0);
        return result1;
    }
    public void backtracking1(String str, int startIndex) {
        if (startIndex >= str.length()) {
            //如果起始位置大于s的大小，说明找到了一组分割方案
            result1.add(new ArrayList<>(path));
        } else {
            for (int i = startIndex; i < str.length(); ++i) {
                if (dp[startIndex][i]) {
                    //是回文子串，进入下一步递归
                    //先将当前子串保存入path
                    path1.addLast(str.substring(startIndex, i + 1));
                    //起始位置后移，保证不重复
                    backtracking1(str, i + 1);
                    path1.pollLast();
                } else {
                    //不是回文子串，跳过
                    continue;
                }
            }
        }
    }
    //通过动态规划判断是否是回文串,参考动态规划篇 52 回文子串
    public void isPalindrome1(char[] str) {
        for (int i = 0; i <= str.length; ++i) {
            dp[i][i] = true;
        }
        for (int i = 1; i < str.length; ++i) {
            for (int j = i; j >= 0; --j) {
                if (str[j] == str[i]) {
                    if (i - j <= 1) {
                        dp[j][i] = true;
                    } else if (dp[j + 1][i - 1]) {
                        dp[j][i] = true;
                    }
                }
            }
        }
    }
}
