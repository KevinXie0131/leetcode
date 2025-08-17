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
     *
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
        Deque<Integer> stack = new ArrayDeque<>(); // 栈
        for(String s : tokens){
            if(s.equals("+")){  // leetcode 内置jdk的问题，不能使用==判断字符串是否相等
                stack.push(stack.pop() + stack.pop());
            }else if(s.equals("-")){
                stack.push(-stack.pop() + stack.pop());  // 注意 - 和/ 需要特殊处理
            }else if(s.equals("*")){
                stack.push(stack.pop() * stack.pop());
            }else if(s.equals("/")){
                int temp1 = stack.pop(); // 注意，栈是先进后出的，先出栈的数字是后遇到的，做运算时不要搞反。
                int temp2 = stack.pop();
                stack.push(temp2 / temp1); // 先出栈的是右操作数，后出栈的是左操作数
            } else{
                stack.push(Integer.valueOf(s));
            }
        }
        return stack.pop();
    }
    /**
     * Use array as stack 使用一个数组模拟栈操作
     * 如果遇到操作数，则将 index 的值加 1，然后将操作数赋给 stack[index]；
     * 如果遇到运算符，则将 index 的值减 1，此时 stack[index] 和 stack[index+1] 的元素分别是左操作数和右操作数，
     * 使用运算符对两个操作数进行运算，将运算得到的新操作数赋给 stack[index]。
     */
    public int evalRPN_1(String[] tokens) {
        int n = tokens.length;
        int[] stack = new int[(n + 1) / 2]; // 对于一个有效的逆波兰表达式，其长度 n 一定是奇数，且操作数的个数一定比运算符的个数多 1 个
        int index = -1;
        for (int i = 0; i < n; i++) {
            String token = tokens[i]; //switch代替if-else，效率优化
            switch (token) { // 遇到操作数时，栈内元素增加 1 个；遇到运算符时，栈内元素减少 1 个
                case "+":
                    index--;
                    stack[index] += stack[index + 1];
                    break;
                case "-":
                    index--;
                    stack[index] -= stack[index + 1];
                    break;
                case "*":
                    index--;
                    stack[index] *= stack[index + 1];
                    break;
                case "/":
                    index--;
                    stack[index] /= stack[index + 1];
                    break;
                default:
                    index++;
                    stack[index] = Integer.parseInt(token); // Integer.parseInt代替Integer.valueOf,减少自动拆箱装箱操作
            }
        }
        return stack[index];
    }
}
