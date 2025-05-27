package com.answer.string;

import java.util.*;

public class Q394_Decode_String {
    /**
     * Given an encoded string, return its decoded string.
     * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being
     * repeated exactly k times. Note that k is guaranteed to be a positive integer.
     * 字符串解码: 给定一个经过编码的字符串，返回它解码后的字符串。
     * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
     * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
     * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
     * 示例 1：
     *  输入：s = "3[a]2[bc]"
     *  输出："aaabcbc"
     * 示例 2：
     *  输入：s = "3[a2[c]]"
     *  输出："accaccacc"
     */
    public static void main(String[] args) {
        String s = "3[a]2[bc]";
        System.out.println(decodeString(s)); // 输出："aaabcbc"
    }
    /**
     * Approach 2: Using 1 Stack
     */
    static int ptr;

    public static String decodeString(String s) {
        LinkedList<String> stk = new LinkedList<>();
        ptr = 0;

        while (ptr < s.length()) {
            char cur = s.charAt(ptr);
            if (Character.isDigit(cur)) {
                // 获取一个数字并进栈
                String digits = getDigits(s);
                stk.addLast(digits);
            } else if (Character.isLetter(cur) || cur == '[') {
                // 获取一个字母并进栈
                stk.addLast(String.valueOf(s.charAt(ptr++)));
            } else {
                ++ptr;
                LinkedList<String> sub = new LinkedList<String>();
                while (!"[".equals(stk.peekLast())) {
                    sub.addLast(stk.removeLast());
                }
                Collections.reverse(sub);
                // 左括号出栈
                stk.removeLast();
                // 此时栈顶为当前 sub 对应的字符串应该出现的次数
                int repTime = Integer.parseInt(stk.removeLast());
                StringBuffer t = new StringBuffer();
                String o = getString(sub);
                // 构造字符串
                while (repTime-- > 0) {
                    t.append(o);
                }
                // 将构造好的字符串入栈
                stk.addLast(t.toString());
            }
        }

        return getString(stk);
    }

    public static String getDigits(String s) {
        StringBuffer ret = new StringBuffer();
        while (Character.isDigit(s.charAt(ptr))) {
            ret.append(s.charAt(ptr++));
        }
        return ret.toString();
    }

    public static String getString(LinkedList<String> v) {
        StringBuffer ret = new StringBuffer();
        for (String s : v) {
            ret.append(s);
        }
        return ret.toString();
    }
    /**
     * Approach 2: Using 2 Stack
     */
    public static String decodeString_1(String s) {
        StringBuffer res = new StringBuffer();
        int mul = 0;
        Deque<Integer> numStack = new ArrayDeque<>();
        Deque<String> strStack = new ArrayDeque<>();
        for(char c : s.toCharArray()){
            if(c == '['){
                numStack.push(mul);
                strStack.push(res.toString());
                mul = 0;
                res = new StringBuffer();
            }else if(c == ']'){
                StringBuffer temp = new StringBuffer();
                int val = numStack.pop();
                for(int i = 0; i < val; i++){
                    temp.append(res);
                }
                res = new StringBuffer(strStack.pop() + temp);
            }else if(c >= '0' && c <= '9'){
                mul = mul * 10 + Integer.parseInt(c + "");
            }else if(c >= 'a' && c <= 'z'){
                res.append(c);
            }
        }

        return res.toString();
    }
    /**
     * Recursion
     */
    public String decodeString_3(String s) {
        return dfs(s, 0)[0];
    }
    private String[] dfs(String s, int i) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        while(i < s.length()) {
            if(s.charAt(i) >= '0' && s.charAt(i) <= '9')
                multi = multi * 10 + Integer.parseInt(String.valueOf(s.charAt(i)));
            else if(s.charAt(i) == '[') {
                String[] tmp = dfs(s, i + 1);
                i = Integer.parseInt(tmp[0]);
                while(multi > 0) {
                    res.append(tmp[1]);
                    multi--;
                }
            }
            else if(s.charAt(i) == ']')
                return new String[] { String.valueOf(i), res.toString() };
            else
                res.append(String.valueOf(s.charAt(i)));
            i++;
        }
        return new String[] { res.toString() };
    }
}
