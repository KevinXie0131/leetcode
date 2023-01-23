package com.answer.stack;

import java.util.*;

public class Q150_Evaluate_Reverse_Polish_Notation {

    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        for(String s : tokens){
            if(s.equals("+")){
                stack.push(stack.pop() + stack.pop());
            }else if(s.equals("-")){
                stack.push(-stack.pop() + stack.pop());
            }else if(s.equals("*")){
                stack.push(stack.pop() * stack.pop());
            }else if(s.equals("/")){
                int temp1 = stack.pop();
                int temp2 = stack.pop();
                stack.push(temp2 / temp1);
            } else{
                stack.push(Integer.valueOf(s));
            }
        }

        return stack.pop();
    }

    public int evalRPN_1(String[] tokens) {
        int n = tokens.length;
        int[] stack = new int[(n + 1) / 2];
        int index = -1;
        for(String s : tokens){
            if(s.equals("+")){
                stack[index-1] = stack[index-1] + stack[index];
                index--;
            }else if(s.equals("-")){
                stack[index-1] = stack[index-1] - stack[index];
                index--;
            }else if(s.equals("*")){
                stack[index-1] = stack[index-1] * stack[index];
                index--;
            }else if(s.equals("/")){
                int temp1 = stack[index];
                int temp2 = stack[index-1];
                stack[index-1] = temp2 / temp1;
                index--;
            } else{
                index++;
                stack[index] = Integer.valueOf(s);
            }
        }

        return stack[index];
    }
}
