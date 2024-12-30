package com.answer.stack;

import java.util.*;

public class Q150_Evaluate_Reverse_Polish_Notation {
    /**
     * 逆波兰表达式：是一种后缀表达式，所谓后缀就是指运算符写在后面。
     * 逆波兰表达式主要有以下两个优点：
     *   1. 去掉括号后表达式无歧义，上式即便写成 1 2 + 3 4 + * 也可以依据次序计算出正确结果。
     *   2. 适合用栈操作运算：遇到数字则入栈；遇到运算符则取出栈顶两个数字进行计算，并将结果压入栈中
     * 其实逆波兰表达式相当于是二叉树中的后序遍历。 大家可以把运算符作为中间节点，按照后序遍历的规则画出一个二叉树。
     */
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        for(String s : tokens){
            if(s.equals("+")){  // leetcode 内置jdk的问题，不能使用==判断字符串是否相等
                stack.push(stack.pop() + stack.pop());
            }else if(s.equals("-")){
                stack.push(-stack.pop() + stack.pop());  // 注意 - 和/ 需要特殊处理
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
    /**
     * Use array as stack
     */
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
