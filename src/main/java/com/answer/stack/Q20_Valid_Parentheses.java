package com.answer.stack;

import java.util.*;

public class Q20_Valid_Parentheses {
    public static void main(String[] args) {
       String s = "]";
        System.out.println(isValid(s));
    }
    static public boolean isValid(String s) {
        if(s.isEmpty()) return true;
        Deque<Character> stack = new ArrayDeque<>();
        for(char c : s.toCharArray()){
            if(c == '['){
                stack.push(']');
            }else if(c == '{'){
                stack.push('}');
            }else if(c == '('){
                stack.push(')');
            } else if(stack.isEmpty() || c != stack.pop()){
                return false;
            }
        }
        if(!stack.isEmpty()){
            return false;
        }
        return true;
    }

    /**
     *
     */
    public boolean isValid_1(String s) {
        int n = s.length();
        if(n % 2 == 1) return false;
        HashMap<Character,Character> map = new HashMap<>();
        map.put(']','[');
        map.put(')','(');
        map.put('}','{');
        Deque<Character> stack = new ArrayDeque<>();
        for(int i = 0; i < n; i++){
            char c = s.charAt(i);
            if(map.containsKey(c)){
                if(stack.isEmpty() || stack.peek() != map.get(c)){
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
