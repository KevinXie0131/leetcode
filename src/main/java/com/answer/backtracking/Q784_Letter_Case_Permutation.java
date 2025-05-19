package com.answer.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Q784_Letter_Case_Permutation {
    /**
     * Given a string s, you can transform every letter individually to be lowercase or uppercase
     * to create another string.
     * Return a list of all possible strings we could create.
     * 给定一个字符串 s ，通过将字符串 s 中的每个字母转变大小写，我们可以获得一个新的字符串。
     * 返回 所有可能得到的字符串集合 。
     */
    public static void main(String[] args) {
        System.out.println(letterCasePermutation_1("a1b2"));
    }
    /**
     * Backtracking
     *
     * 大写字符与其对应的小写字符的 ASCII 的差为 32
     *    如果字符是小写字符，减去 32 得到大写字符；
     *    如果字符是大写字符，加上 32 得到小写字符。
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
     * 利用List进行模拟
     */
    public static List<String> letterCasePermutation_1(String S) {
        List<StringBuilder> ans = new ArrayList();
        ans.add(new StringBuilder());

        for (char c: S.toCharArray()) {
            int n = ans.size();
            if (Character.isLetter(c)) {
                for (int i = 0; i < n; ++i) {
                    ans.add(new StringBuilder(ans.get(i)));
                    ans.get(i).append(Character.toLowerCase(c));
                    ans.get(n + i).append(Character.toUpperCase(c));
                }
            } else {
                for (int i = 0; i < n; ++i)
                    ans.get(i).append(c);
            }
        }

        List<String> result = new ArrayList();
        for (StringBuilder sb: ans) {
            result.add(sb.toString());
        }
        return result;
    }
}
