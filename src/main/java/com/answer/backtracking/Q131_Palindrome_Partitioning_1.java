package com.answer.backtracking;

import java.util.*;

public class Q131_Palindrome_Partitioning_1 {
    public static void main(String[] args) {
       // System.out.println(partition("abcd"));
        System.out.println(partition("aab"));
    }
    /**
     * 切割问题可以抽象为组合问题
     * 如何模拟那些切割线
     * 切割问题中递归如何终止
     * 在递归循环中如何截取子串
     * 如何判断回文
     */
    static List<List<String>> result = new ArrayList<List<String>>();;
    static Deque<String> path = new ArrayDeque<>();

    static public List<List<String>> partition(String s) {
        backtracking(s, 0, new StringBuilder());
        return result;
    }

    static public void backtracking(String str, int startIndex ,  StringBuilder sb) {
        //因为是起始位置一个一个加的，所以结束时start一定等于s.length,因为进入backtracking时一定末尾也是回文，所以path是满足条件的
        if(startIndex >= str.length()){
            result.add(new ArrayList(path));  //注意创建一个新的copy
            return;
        }
        //像前两题一样从前往后搜索，如果发现回文，进入backtracking,起始位置后移一位，循环结束照例移除path的末位
        for (int i = startIndex; i < str.length(); i++){
            sb.append(str.charAt(i));
            System.out.println(startIndex + " -> " +sb);
            if (isPalindrome(sb.toString())){
                path.add(sb.toString());
                backtracking(str, i + 1, new StringBuilder());
                path.removeLast();
            }
        }
    }

    static public boolean isPalindrome(String str) {
        char[] ch = str.toCharArray();
        for(int i= 0, j = ch.length -1; i <=j; i++, j--){
            if(ch[i] != ch[j]){
                return false;
            }
        }
        return true;
    }
}
