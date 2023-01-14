package com.answer.backtracking;

import java.util.*;

public class Q131_Palindrome_Partitioning {

    List<List<String>> result = new ArrayList<List<String>>();
    /**
     * Deque<String> path = new LinkedList<>();
     */
    Deque<String> path = new ArrayDeque<>();

    public List<List<String>> partition(String s) {
        backtracking(s, 0);
        return result;
    }

    public void backtracking(String s, int startIndex){
        if(startIndex >= s.length()){
            result.add(new ArrayList(path));
            return;
        }
        for(int i = startIndex; i < s.length(); i++){
            if(isPalindrome(s, startIndex, i)){
                String newStr = s.substring(startIndex, i + 1);
                path.add(newStr);
            } else {
                continue;
            }
            backtracking(s, i + 1);
            path.removeLast();
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
