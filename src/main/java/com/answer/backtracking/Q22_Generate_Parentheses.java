package com.answer.backtracking;

import java.util.*;

public class Q22_Generate_Parentheses {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String>();

        if(n == 0){
            return result;
        }
        backtracking(new StringBuffer(), n,n, result);
        return result;
    }
    /**
     * Backtracking 回溯
     * l >= r Valid
     * l < r Invalid
     * l == r == n Add in result
     */
    public void backtracking(StringBuffer sb, int left, int right, List<String> result){
        if(left == 0 && right == 0){
            result.add(sb.toString());
        }
        if(right < left){ // 只有left>= right才有效，在这里剪枝
            return;
        }
        if(left > 0){
            sb.append("(");
            backtracking(sb, left-1, right, result);
            sb.deleteCharAt(sb.length() - 1);
        }
        if(right > 0){
            sb.append(")");
            backtracking(sb, left, right - 1, result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    /**
     * 另一种形式
     */
    public List<String> generateParenthesis1(int n) {
        List<String> result = new ArrayList<String>();
        if(n == 0){
            return result;
        }
        backtracking1(new StringBuffer(), 0,0,n, result);
        return result;
    }

    public void backtracking1(StringBuffer sb, int left, int right, int n, List<String> result){
        if(left == n && right == n){
            result.add(sb.toString());
            return;
        }
        if(left < n){
            sb.append("(");
            backtracking1(sb, left + 1, right, n, result);
            sb.deleteCharAt(sb.length() - 1);
        }
        // 只有left>= right才有效，在这里剪枝
        if(right < n && left > right){ // if(right < left){ // 相同作用
            sb.append(")");
            backtracking1(sb, left, right + 1,n, result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
