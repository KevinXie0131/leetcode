package com.answer.stack;

public class Q1047_Remove_All_Adjacent_Duplicates_In_String {

    public static void main(String[] args) {
        String s = "abbaca";
        System.out.println(removeDuplicates(s));
    }

    public static String removeDuplicates(String s) {
        StringBuffer stack = new StringBuffer();
        int top = -1;
        for(int i = 0 ; i < s.length(); i++){
            char ch = s.charAt(i);
            if(top >= 0 && ch == stack.charAt(top)){
                stack.deleteCharAt(top);
                top--;
            }else{
                stack.append(ch);
                top++;
            }
        }

        return stack.toString();
    }
}
