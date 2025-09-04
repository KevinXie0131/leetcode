package com.answer.backtracking;

import java.util.*;

public class Q22_Generate_Parentheses_1 {

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String>();
        if(n == 0){
            return result;
        }
        backtracking(new StringBuffer(), n, n, result);
        return result;
    }

    public void backtracking(StringBuffer sb, int left, int right, List<String> result){
        if(left == 0 && right == 0){
            if(valid(sb.toString().toCharArray())){
                result.add(sb.toString());
            }
        }
        //  if(right < left){
        //       return;
        //   }
        if(left > 0){
            sb.append("(");
            backtracking(sb, left - 1, right, result);
            sb.deleteCharAt(sb.length() - 1);
        }
        if(right > 0){
            sb.append(")");
            backtracking(sb, left, right - 1, result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public boolean valid(char[] current) {
        int balance = 0;
        for (char c: current) {
            if (c == '(') {
                ++balance;
            } else {
                --balance;
            }
            if (balance < 0) {
                return false;
            }
        }
        return balance == 0;
    }
}
