package com.answer.stack;

import java.util.*;

public class Q772_Basic_Calculator_III { // Hard 困难
    /**
     * 基本计算器 III
     * 实现一个基本的计算器来计算简单的表达式字符串。
     * 表达式字符串可能包含左括号 (、右括号 )、加号 +、减号 -、乘号 *、除号 /，非负整数和空格 ' '。表达式中不允许使用任何其他字符。
     * 你可以假设给定的表达式总是有效的。
     *
     * Implement a basic calculator to evaluate a simple expression string.
     * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
     * The expression string contains only non-negative integers, +, -, *, / operators , open ( and closing parentheses ) and empty spaces . The integer division should truncate toward zero.
     *
     * You may assume that the given expression is always valid. All intermediate results will be in the range of [-2147483648, 2147483647].
     * Some examples:
     * "1 + 1" = 2
     * " 6-4 / 2 " = 4
     * "2*(5+5*2)/3+(6/2+8)" = 21
     * "(2+6* 3+5- (3*14/7+2)*5)+3"=-12
     */
    public static void main(String[] args) {
        System.out.println(calculate("(2+6* 3+5- (3*14/7+2)*5)+3"));
        System.out.println(calculate("2*(5+5*2)/3+(6/2+8)"));
        System.out.println(calculate("6-4/2"));
        System.out.println(calculate("1+1"));
    }
    /**
     * https://algo.monster/liteproblems/772
     * 加减号在乘除号面前是没有地位的，遇到了需要先去栈里待着，押后处理
     * 左括号是没有地位的，无论什么情况都需要先去栈里待着，直到右括号出现。
     */
    static public int calculate(String s) {
        Deque<Integer> nums = new ArrayDeque<>();
        Deque<Character> ops = new ArrayDeque<>();
        int n = s.length();
        int num = 0;
        boolean hasNum = false;
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                // (c - '0') 的这个括号不能省略，否则可能造成整型溢出。
                num = num * 10 + (c - '0'); // 一个字符串形式的正整数，转化成 int 型
                hasNum = true;
            }
            if (!Character.isDigit(c) && c != ' ' || i == n - 1) {  // 如果不是数字，或者是算式的末尾
                if (hasNum) {
                    nums.push(num);
                    num = 0;
                    hasNum = false;
                }
                if (c == '(') {
                    ops.push(c);
                } else if (c == ')') {
                    while (ops.peek() != '(') {
                        nums.push(applyOp(ops.pop(), nums.pop(), nums.pop()));
                    }
                    ops.pop();
                } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                    while (!ops.isEmpty() && precedence(ops.peek()) >= precedence(c)) {
                        nums.push(applyOp(ops.pop(), nums.pop(), nums.pop()));
                    }
                    ops.push(c);
                }
            }
        }
        while (!ops.isEmpty()) {
            nums.push(applyOp(ops.pop(), nums.pop(), nums.pop()));
        }
        return nums.pop();
    }

    static private int precedence(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return 0;
    }

    static private int applyOp(char op, int b, int a) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b; // assume b != 0
        }
        return 0;
    }
    /**
     * https://ttzztt.gitbooks.io/lc/content/quant-dev/basic-calculator-iii.html
     */
    static public int calculate1(String s) {
        int l1 = 0, o1 = 1;
        int l2 = 1, o2 = 1;
        Deque<Integer> stack = new ArrayDeque();

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);

            if(Character.isDigit(c)){
                int num = c - '0';

                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    num = num *10 + (s.charAt(++i) - '0');
                }

                l2 = (o2 == 1 ? l2 * num: l2 / num);

            } else if (c == '('){
                // First preserve current calculation status
                stack.offerFirst(l1); stack.offerFirst(o1);
                stack.offerFirst(l2); stack.offerFirst(o2);

                // Reset them for the next calculation
                l1 = 0; o1 = 1;
                l2 = 1; o2 = 1;

            } else if (c== ')'){
                // First preserve the result of current calculation
                int num = l1 + o1 * l2;

                // Then restore previous calculation status
                o2 = stack.poll(); l2 = stack.poll();
                o1 = stack.poll(); l1 = stack.poll();
                // Previous calculation status is now in effect
                l2 = (o2 == 1 ? l2 * num : l2 / num);

            } else if (c == '*' || c == '/'){
                o2 = ( c == '*'? 1: -1);

            } else if (c == '+' || c == '-'){
                l1 = l1 + o1 * l2;
                o1 = (c == '+'? 1: -1);
                l2 = 1; o2 = 1;
            }
        }

        return (l1 + o1 * l2);
    }
}
