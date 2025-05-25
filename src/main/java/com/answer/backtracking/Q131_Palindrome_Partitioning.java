package com.answer.backtracking;

import java.util.*;

public class Q131_Palindrome_Partitioning {
    /**
     * Given a string s, partition s such that every substring of the partition is a palindrome.
     * Return all possible palindrome partitioning of s.
     * 分割回文串
     * 一个字符串 s，请你将 s 分割成一些 子串，使每个子串都是 回文串。返回 s 所有可能的分割方案。
     */
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
    // 切割问题类似组合问题
    //    例如对于字符串abcdef：
    //    组合问题：选取一个a之后，在bcdef中再去选取第二个，选取b之后在cdef中再选取第三个.....。
    //    切割问题：切割一个a之后，在bcdef中再去切割第二段，切割b之后在cdef中再切割第三段.....。
    static public void backtracking(String s, int startIndex){
        // 如果起始位置已经⼤于s的⼤⼩，说明已经找到了⼀组分割⽅案了
        // 切割线切到了字符串最后面，说明找到了一种切割方法，此时就是本层递归的终止条件。
        if(startIndex >= s.length()){ // 分割完毕
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
     *
     * DFS 枚举每个子串的分割位置，而分割的依据就是保证当前子串是一个回文串，所以这就需要频繁的对任意截取的一个子串进行检查。
     * 为了提高运行效率，可以在 DFS 之前预处理一个状态数组 f[i][j]，用来表示字符串 s[i] 到 s[j] 这部分子串是不是一个回文串。
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
            result1.add(new ArrayList<>(path1)); //如果起始位置大于s的大小，说明找到了一组分割方案
        } else {
            for (int i = startIndex; i < str.length(); ++i) {
                if (dp[startIndex][i]) { //是回文子串，进入下一步递归
                    path1.addLast(str.substring(startIndex, i + 1)); //先将当前子串保存入path
                    backtracking1(str, i + 1);    //起始位置后移，保证不重复
                    path1.pollLast();
                } else {

                    continue;//不是回文子串，跳过
                }
            }
        }
    }
    //通过动态规划判断是否是回文串,参考动态规划 Q125 Valid Palindrome 回文子串
    public void isPalindrome1(char[] array) {
        for (int i = 0; i <= array.length; ++i) {
            dp[i][i] = true;
        }
        for (int i = 1; i < array.length; ++i) { // for (int i = 0; i < array.length; ++i) { // works too
            for (int j = i; j >= 0; --j) { // 从下到上
                if (array[j] == array[i]) {
                    if (i - j <= 1) { // 如果i-j不构成字符串  // if (i - j <= 2) { // works too
                        dp[j][i] = true;
                    } else if (dp[j + 1][i - 1]) {
                        dp[j][i] = true;
                    }
                }
            }
        }
    }
}
