package com.answer.string;

public class Q557_Reverse_Words_in_a_String_III {
    public static void main(String[] args) {
        String s = "Let's take LeetCode contest";
        System.out.println(reverseWords_0(s));
    }

    /**
     * Approach 2: Using Two Pointers - In-place
     *
     * Here's the code snippet for reversing the string stored in character array chArray using two pointer approach.
     *
     * while (startIndex < endIndex) {
     *    char temp = chArray[startIndex];
     *    chArray[startIndex] = chArray[endIndex];
     *    chArray[endIndex] = temp;
     *    startIndex++;
     *    endIndex--;
     * }
     */
    public static String reverseWords(String s) {
        char[] ch = s.toCharArray();
        int len = s.length();
        int i = 0;
        while(i < len){
            int start = i;
            while(i < len && ch[i] != ' '){
                i++;
            }
            int left = start, right = i - 1;
            while(left < right){
                char temp = ch[left];
                ch[left] = ch[right];
                ch[right] = temp;
                left++;
                right--;
            }
            while(i < len && ch[i] == ' '){
                i++;
            }
        }
        return new String(ch);
    }
    /**
     * Approach 1: Traverse and Reverse each character one by one
     */
    public static String reverseWords_0(String s) {
        StringBuilder sb = new StringBuilder();
        char[] ch = s.toCharArray();
        int len = s.length();
        int i = 0;

        while(i < len){
            int start = i;
            while(i < len && ch[i] != ' '){
                i++;
            }
            for(int j = start; j < i; j++){
                sb.append(ch[start + i - 1 - j]);
            }
            while(i < len && ch[i] == ' '){
                i++;
                sb.append(' ');
            }
        }

        return sb.toString();
    }
    /**
     * use StringBuffer
     */
    public String reverseWords_1(String s) {
        String[] s1 = s.split(" ");
        StringBuilder stringBuilder1 = new StringBuilder();
        for (int i = 0; i < s1.length ;i++) {
            StringBuilder stringBuilder = new StringBuilder(s1[i]);
            stringBuilder1.append(stringBuilder.reverse());
            if(i != s1.length - 1){
                stringBuilder1.append(" ");
            }
        }
        return stringBuilder1.toString();
    }
}
