package com.answer.string;

import java.util.*;

public class Q151_Reverse_Words_in_a_String {

    public static void main(String[] args) {
     //   String s = "the sky is blue";
        String s = "a good   example";
        System.out.println(reverseWords_1(s));
    }

    public String reverseWords(String s) {
        s = s.trim();

        String[] str = s.split("\\s+");
        List<String> list = Arrays.asList(str);

        Collections.reverse(list);
        String result = String.join(" ", list);
        return result;
    }
    /**
     *
     */
    public static String reverseWords_1(String s) {
        StringBuffer sb = trimString(s);
        reverseString(sb, 0, sb.length()-1);
        reverseEachWord(sb);
        return sb.toString();
    }

    private static StringBuffer trimString(String s){
        int left = 0;
        int right = s.length() - 1;
        while(s.charAt(left) == ' '){
            left++;
        }
        while(s.charAt(right) == ' '){
            right--;
        }
       /* StringBuffer sb = new StringBuffer();
        while (left <= right) {
            char c = s.charAt(left);
            if (c != ' ' || sb.charAt(sb.length() - 1) != ' ') {
                sb.append(c);
            }
            left++;
        }
        return sb;*/
        StringBuffer sb = new StringBuffer();
        while(left <= right){
            char c = s.charAt(left);
            if(!(c == ' ' && sb.charAt(sb.length() - 1) == ' ')){
                sb.append(c);
            }
            left++;
        }
        return sb;
    }

    private static void reverseString(StringBuffer sb, int start, int end){
        while(start < end){
            char temp = sb.charAt(start);
            sb.setCharAt(start, sb.charAt(end));
            sb.setCharAt(end, temp);
            start++;
            end--;
        }
    }

    private static void reverseEachWord(StringBuffer sb) {
        int left = 0;
        int right = 1;
        int n = sb.length();
        while(left < n){
            while(right < n && sb.charAt(right) != ' '){
                right++;
            }
            reverseString(sb, left, right - 1);
            left = right + 1;
            right++;
        }
    }
}
