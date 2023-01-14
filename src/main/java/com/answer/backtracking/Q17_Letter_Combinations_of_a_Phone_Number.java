package com.answer.backtracking;

import java.util.*;

public class Q17_Letter_Combinations_of_a_Phone_Number {

    public List<String> letterCombinations(String digits) {
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

    public void backtracking(String digits, List<String> result, int startIndex, StringBuffer path,  Map<Character, String> map ){
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
}
