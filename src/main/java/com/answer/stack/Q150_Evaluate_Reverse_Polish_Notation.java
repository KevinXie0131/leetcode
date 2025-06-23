package com.answer.stack;

import java.util.*;

public class Q150_Evaluate_Reverse_Polish_Notation {
    /**
     * 一个字符串数组 tokens ，表示一个根据 逆波兰表示法 表示的算术表达式。
     * You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.
     * 请你计算该表达式。返回一个表示表达式值的整数。
     * Evaluate the expression. Return an integer that represents the value of the expression.
     * 注意：
     *  有效的算符为 '+'、'-'、'*' 和 '/' 。
     *  每个操作数（运算对象）都可以是一个整数或者另一个表达式。
     *  两个整数之间的除法总是 向零截断 。
     *  表达式中不含除零运算。
     *  输入是一个根据逆波兰表示法表示的算术表达式。
     *  答案及所有中间计算结果可以用 32 位 整数表示。
     *  The valid operators are '+', '-', '*', and '/'.
     *  Each operand may be an integer or another expression.
     *  The division between two integers always truncates toward zero.
     *  There will not be any division by zero.
     *  The input represents a valid arithmetic expression in a reverse polish notation.
     *  The answer and all the intermediate calculations can be represented in a 32-bit integer.
     * 示例 1：
     *  输入：tokens = ["2","1","+","3","*"]
     *  输出：9
     *  解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
     * 示例 2：
     *  输入：tokens = ["4","13","5","/","+"]
     *  输出：6
     *  解释：该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
     */
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
