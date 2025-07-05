package com.answer.stack;

import java.util.*;

public class Q20_Valid_Parentheses {
    /**
     * 有效的括号
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * 有效字符串需满足：
     *  左括号必须用相同类型的右括号闭合。
     *  左括号必须以正确的顺序闭合。
     *  每个右括号都有一个对应的相同类型的左括号。
     * 示例 1：
     *  输入：s = "()"
     *  输出：true
     * 示例 2：
     *  输入：s = "()[]{}"
     *  输出：true
     * 示例 3：
     *  输入：s = "(]"
     *  输出：false
     * 示例 4：
     *  输入：s = "([])"
     *  输出：true
     */
    public static void main(String[] args) {
       String s = "]";
        System.out.println(isValid(s));
    }
    /**
     * 第一种情况：已经遍历完了字符串，但是栈不为空，说明有相应的左括号没有右括号来匹配，所以return false
     * 第二种情况：遍历字符串匹配的过程中，发现栈里没有要匹配的字符。所以return false
     * 第三种情况：遍历字符串匹配的过程中，栈已经为空了，没有匹配的字符了，说明右括号没有找到对应的左括号return false
     *
     * 那么什么时候说明左括号和右括号全都匹配了呢，就是字符串遍历完之后，栈是空的，就说明全都匹配了。
     */
    static public boolean isValid(String s) {
        if(s.isEmpty()) return true;
        Deque<Character> stack = new ArrayDeque<>(); // 栈
        for(char c : s.toCharArray()){
            if(c == '['){ //碰到左括号，就把相应的右括号入栈
                stack.push(']');
            }else if(c == '{'){
                stack.push('}');
            }else if(c == '('){
                stack.push(')');
            // 第三种情况：遍历字符串匹配的过程中，栈已经为空了，没有匹配的字符了，说明右括号没有找到对应的左括号 return false
            // 第二种情况：遍历字符串匹配的过程中，发现栈里没有我们要匹配的字符。所以return false
            } else if(stack.isEmpty() || c != stack.pop()){ //如果是右括号判断是否和栈顶元素匹配
                return false;
            }
        }
        //最后判断栈中元素是否匹配
        //return stack.isEmpty();
        // 第一种情况：此时我们已经遍历完了字符串，但是栈不为空，说明有相应的左括号没有右括号来匹配，所以return false，否则就return true
        if(!stack.isEmpty()){
            return false;
        }
        return true;
    }
    /**
     * 使用HashMap
     * 遍历字符串，每遇到一个左括号时，将与其匹配的右括号入栈；当遇到右括号时，与栈顶元素比较配对，不一致则匹配失败
     */
    public static boolean isValid_1(String s) {
        int n = s.length();
        if(n % 2 == 1) { // 如果s的长度为奇数，一定不符合要求
            return false;
        }
        HashMap<Character,Character> pairs  = new HashMap<>();
        pairs .put(']','[');
        pairs .put(')','(');
        pairs .put('}','{');
        Deque<Character> stack = new ArrayDeque<>();
        for(int i = 0; i < n; i++){
            char c = s.charAt(i);
            if(pairs .containsKey(c)){
                if(stack.isEmpty() || stack.peek() != pairs .get(c)){
                    return false;
                }
                stack.pop();
            }else{
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
    /**
     * Use StringBuffer as stack
     */
    public boolean isValid_2(String s) {
        StringBuffer sb = new StringBuffer();

        for(char c : s.toCharArray()){
            if(c == '['){
                sb.append(']');
            }else if(c == '{'){
                sb.append('}');
            }else if(c == '('){
                sb.append(')');
            } else if(sb.length() == 0){
                return false;
            } else {
                char ch = sb.charAt(sb.length() - 1);
                if(ch != c){
                    return false;
                }else{
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }
        return sb.length() == 0;
    }
}
