package com.answer.stack;

import java.util.*;

public class Q772_Basic_Calculator_III {
    public static void main(String[] args) {
        System.out.println(calculate("(2+6* 3+5- (3*14/7+2)*5)+3"));
        System.out.println(calculate("2*(5+5*2)/3+(6/2+8)"));
        System.out.println(calculate("6-4/2"));
        System.out.println(calculate("1+1"));
    }
    /**
     * https://algo.monster/liteproblems/772
     */
    static private int precedence(char op) {
        if (op == '(' || op == ')') return 0;
        else if (op == '+' || op == '-') return 1;
        else return 2;
    }

    static private int apply_operand(char op, int a, int b) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            default:
                return a / b;
        }
    }

    static public int calculate(String s) {
        Stack<Integer> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') continue;

            if (Character.isDigit(s.charAt(i))) {
                int num = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                nums.push(num);
                i--;
            }
            else if (s.charAt(i) == '(') {
                ops.push(s.charAt(i));
            }
            else if (s.charAt(i) == ')') {
                while (ops.peek() != '(') {
                    int b = nums.pop();
                    int a = nums.pop();
                    nums.push(apply_operand(ops.pop(), a, b));
                }
                ops.pop();
            } else {
                while (!ops.empty() && ops.peek() != '(' && precedence(ops.peek()) >= precedence(s.charAt(i))) {
                    int b = nums.pop();
                    int a = nums.pop();
                    nums.push(apply_operand(ops.pop(), a, b));
                }
                ops.push(s.charAt(i));
            }
        }

        while (!ops.empty()) {
            int b = nums.pop();
            int a = nums.pop();
            nums.push(apply_operand(ops.pop(), a, b));
        }

        return nums.peek();
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