package com.answer.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q17_Letter_Combinations_of_a_Phone_Number_1 {
    public static void main(String[] args) {
        System.out.println(letterCombinations(""));
    }

    static String[] letterMap = {
                "", // 0
                "", // 1
                "abc", // 2
                "def", // 3
                "ghi", // 4
                "jkl", // 5
                "mno", // 6
                "pqrs", // 7
                "tuv", // 8
                "wxyz", // 9
    };

    static List<String> result = new ArrayList<>();
    static StringBuffer str = new StringBuffer();

    public static List<String> letterCombinations(String digits) {
        if(digits.length() == 0){
            return result;
        }
        backtracking(digits, 0);
        return result;
    }

    public static void backtracking(String digits, int index){
        if (index == digits.length()) {
            result.add(str.toString());
            return;
        }
        int digit = digits.charAt(index) - '0'; // 将index指向的数字转为 int
        String letters = letterMap[digit]; // 取数字对应的字符集

        for (int i = 0; i < letters.length(); i++) {
            str.append(letters.charAt(i)); // 处理
            backtracking(digits, index + 1); // 递归，注意index+1，⼀下层要处理下⼀个数字了
            str.deleteCharAt(str.length() - 1); // 回溯
        }
    }
}
