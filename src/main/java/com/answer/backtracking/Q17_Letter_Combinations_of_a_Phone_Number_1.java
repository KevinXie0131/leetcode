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
    /**
     * 回溯法 版本一
     * 时间复杂度: O(3^m * 4^n)，其中 m 是对应三个字母的数字个数，n 是对应四个字母的数字个数
     * 空间复杂度: O(3^m * 4^n)
     */
    static List<String> result = new ArrayList<>();
    static StringBuffer str = new StringBuffer();

    public static List<String> letterCombinations(String digits) {
        if(digits.length() == 0){
            return result;
        }
        backtracking(digits, 0);
        return result;
    }
    /**
     * 注意这个index可不是 77.组合 和216.组合总和III 中的startIndex了。
     * 这个index是记录遍历第几个数字了，就是用来遍历digits的（题目中给出数字字符串），同时index也表示树的深度。
     * 注意这里for循环，可不像是在 回溯算法求组合问题 和 回溯算法求组合总和 中从startIndex开始遍历的。
     * 因为本题每一个数字代表的是不同集合，也就是求不同集合之间的组合，而77. 组合 和216.组合总和III 都是求同一个集合中的组合！
     */
    public static void backtracking(String digits, int index){
        if (index == digits.length()) { // if(sb.length() == digits.length()){
            result.add(str.toString());
            return;
        }
        int digit = digits.charAt(index) - '0'; // 将index指向的数字转为 int
        String letters = letterMap[digit]; // 取数字对应的字符串
        /**
         * 本题不需要startIndex来控制for循环的起始位置, 如果是多个集合取组合，各个集合之间相互不影响，那么就不用startIndex
         */
        for (int i = 0; i < letters.length(); i++) {
            str.append(letters.charAt(i)); // 处理
            backtracking(digits, index + 1); // 递归，处理下一层, 注意index+1，⼀下层要处理下⼀个数字了
            str.deleteCharAt(str.length() - 1); // 回溯 剔除末尾的继续尝试
        }
    }
    /**
     *  版本二
     */
    List<String> result1 = new ArrayList<String>();

    public List<String> letterCombinations1(String digits) {
        if(digits.length() == 0){
            return result1;
        }
        backtracking1(digits, 0, "");
        return result1;
    }
    // 不建议把回溯藏在递归的参数里这种写法，很不直观
    public void backtracking1(String digits, int index, String str){ // 注意参数的不同
        if(str.length() == digits.length()){
            result1.add(str);
            return;
        }
        int digit = digits.charAt(index) - '0';
        String letters = letterMap[digit];
        for(int i = 0 ; i < letters.length(); i++){
            // 把回溯的过程放在递归函数里
            backtracking1( digits,   index + 1, str + letters.charAt(i)); // 注意这里的不同
        }
    }
}
