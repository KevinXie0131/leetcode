package com.answer.string;

public class Q557_Reverse_Words_in_a_String_III {
    /**
     * Given a string s, reverse the order of characters in each word within a sentence while still
     * preserving whitespace and initial word order.
     * 给定一个字符串 s ，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
     */
    public static void main(String[] args) {
        String s = "Let's take LeetCode contest";
        System.out.println(reverseWords_3(s)); // "s'teL ekat edoCteeL tsetnoc"
    }
    /**
     * Reverse each word
     * Refer to Q186_Reverse_Words_in_a_String_II
     */
    public String reverseWords_6(String s) {
        char[] array = s.toCharArray();
        int start = 0;
        for(int end = 0; end <= array.length; end++){
            if(end == array.length || array[end] == ' '){
                reverse(array, start , end - 1);
                start = end + 1;
            }
        }
        return new String(array);
    }
    /**
     * Using Two Pointers - In-place 原地解法
     */
    static public String reverseWords_3(String s) {
        char[] array = s.toCharArray();
        int start = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == ' ') {
                reverse(array, start, i - 1);
                start = i + 1; // 更新start为下一个单词的左索引
                continue;
            }
            if (i == array.length - 1) { // for the last word
                reverse(array, start, i);
            }
        }
        return new String(array);
    }
    // reversing the string stored in character array chArray using two pointer approach.
    static private void reverse(char[] array, int l, int r) {
        while (l < r) {
            char temp = array[l];
            array[l] = array[r];
            array[r] = temp;
            l++;
            r--;
        }
    }
    /**
     * 原地解法
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
     * 使用额外空间
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
