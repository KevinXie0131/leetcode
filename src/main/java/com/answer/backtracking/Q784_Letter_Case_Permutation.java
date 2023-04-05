package com.answer.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Q784_Letter_Case_Permutation {
    public static void main(String[] args) {
        System.out.println(letterCasePermutation("a1b2"));
    }
    /**
     * Backtracking
     */
    static List<String> result = new ArrayList<String>();
    static public List<String> letterCasePermutation(String s) {
        char[] arr = s.toCharArray();

        backtracking(arr, 0);
        return result;
    }
    static void backtracking(char[] arr, int index){
        result.add(String.valueOf(arr));

        for(int i = index; i < arr.length; i++){
            if(arr[i] >= '0' && arr[i] <= '9'){
                continue;
            }

            arr[i] = changeLetter(arr[i]);
            backtracking(arr, i + 1);
            arr[i] = changeLetter(arr[i]);
        }
    }
    static public char changeLetter(char c) {
        return (c >= 'a' && c <= 'z') ? (char) (c - 32) : (char) (c + 32);
    }
    /**
     * Approach #1: Recursion [Accepted]
     */
    public List<String> letterCasePermutation_1(String S) {
        List<StringBuilder> ans = new ArrayList();
        ans.add(new StringBuilder());

        for (char c: S.toCharArray()) {
            int n = ans.size();
            if (Character.isLetter(c)) {
                for (int i = 0; i < n; ++i) {
                    ans.add(new StringBuilder(ans.get(i)));
                    ans.get(i).append(Character.toLowerCase(c));
                    ans.get(n+i).append(Character.toUpperCase(c));
                }
            } else {
                for (int i = 0; i < n; ++i)
                    ans.get(i).append(c);
            }
        }

        List<String> finalans = new ArrayList();
        for (StringBuilder sb: ans) {
            finalans.add(sb.toString());
        }
        return finalans;
    }
}
