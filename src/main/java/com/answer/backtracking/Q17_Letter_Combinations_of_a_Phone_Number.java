package com.answer.backtracking;

import java.util.*;

public class Q17_Letter_Combinations_of_a_Phone_Number {
    /**
     * 电话号码的字母组合: 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     */
    public static void main(String[] args) {
        System.out.println(letterCombinations2("23")); // 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
    }
    /**
     * 首先存储每个数字对应的所有可能的字母，然后进行回溯操作。回溯过程中维护一个字符串，表示已有的字母排列，
     * 并记录当前回溯位置。每次尝试对应位置数字的所有字母，即可得到完整排列。
     */
    public static List<String> letterCombinations(String digits) {
        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        List<String> result = new ArrayList<>();
        if(digits.length() == 0){
            return result;
        }
        backtracking(digits, result, 0, new StringBuffer(), map);
        return result;
    }

    public static void backtracking(String digits, List<String> result, int startIndex, StringBuffer path,  Map<Character, String> map ){
        if(path.length() == digits.length()){
            result.add(path.toString());
            return;
        }
        String str = map.get(digits.charAt(startIndex));
        for(int i = 0; i < str.length(); i++){
            path.append(str.charAt(i));
            backtracking(digits, result, startIndex + 1,path,map);
            path.deleteCharAt(startIndex);
        }
    }
    /**
     * use array
     */
    public List<String> letterCombinations1(String digits) {
        String[] map = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        List<String> result = new ArrayList<>();
        if(digits.length() == 0){
            return result;
        }
        backtracking1(digits, result, 0, new StringBuffer(), map);
        return result;
    }

    public  void backtracking1(String digits, List<String> result, int startIndex, StringBuffer path,  String[] map ){
        if(path.length() == digits.length()){
            result.add(path.toString());
            return;
        }
        String str = map[digits.charAt(startIndex) - '0'];
        for(int i = 0; i < str.length(); i++){
            path.append(str.charAt(i));
            backtracking1(digits, result, startIndex + 1,path,map);
            path.deleteCharAt(startIndex);
        }
    }
    /**
     * 利用队列求解
     * 可以利用队列的先进先出特点，再配合循环完成
     * 先将2对应的字符"a","b","c"依次放入队列中, 之后再从队列中拿出第一个元素"a"，跟3对应的字符"d","e","f"挨个拼接
     * 按照同样的方式，再将"b"从队列中拿出，再跟3对应的字符"d","e","f"挨个拼接
     */
    static public List<String> letterCombinations2(String digits) {
        if(digits==null || digits.length()==0) {
            return new ArrayList<String>();
        }
        //一个映射表，第二个位置是"abc“,第三个位置是"def"。。。
        //这里也可以用map，用数组可以更节省点内存
        String[] letter_map = {" ","*","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        List<String> res = new ArrayList<>();

        res.add("");  //先往队列中加入一个空字符
        for(int i = 0; i < digits.length(); i++) {
            String letters = letter_map[digits.charAt(i) - '0'];   //由当前遍历到的字符，取字典表中查找对应的字符串
            int size = res.size();

            for(int j = 0; j < size; j++) {  //计算出队列长度后，将队列中的每个元素挨个拿出来
                String tmp = res.remove(0);//每次都从队列中拿出第一个元素

                for(int k= 0; k < letters.length(); k++) { //然后跟"def"这样的字符串拼接，并再次放到队列中
                    res.add(tmp + letters.charAt(k));
                }
            }
        }
        return res;
    }
}
