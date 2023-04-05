package com.answer.backtracking;

import java.util.*;

public class Q131_Palindrome_Partitioning {

    List<List<String>> result = new ArrayList<List<String>>();
    /**
     * Deque<String> path = new LinkedList<>();
     */
    Deque<String> path = new ArrayDeque<>(); // 放已经回⽂的⼦串

    public List<List<String>> partition(String s) {
        backtracking(s, 0);
        return result;
    }

    public void backtracking(String s, int startIndex){
        // 如果起始位置已经⼤于s的⼤⼩，说明已经找到了⼀组分割⽅案了
        if(startIndex >= s.length()){
            result.add(new ArrayList(path));
            return;
        }
        for(int i = startIndex; i < s.length(); i++){
            if(isPalindrome(s, startIndex, i)){ // 是回⽂⼦串
                String newStr = s.substring(startIndex, i + 1); // 获取[startIndex,i]在s中的⼦串
                path.add(newStr);
            } else {
                continue; // 不是回⽂，跳过
            }
            backtracking(s, i + 1); // 寻找i+1为起始位置的⼦串
            path.removeLast(); // 回溯过程，弹出本次已经填在的⼦串
        }

    }

    public boolean isPalindrome(String str, int start, int end){
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
}
