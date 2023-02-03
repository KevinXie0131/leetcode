package com.answer.string;

import java.util.*;

public class Q394_Decode_String {
    public static void main(String[] args) {
        String s = "3[a]2[bc]";
        System.out.println(decodeString(s));
    }

    /**
     * Approach 2: Using 2 Stack
     */
    public static String decodeString(String s) {
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
}
