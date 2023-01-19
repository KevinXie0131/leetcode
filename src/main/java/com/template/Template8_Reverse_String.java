package com.template;

public class Template8_Reverse_String {

    public static void main(String[] args) {
        String s = "abcdefg";
        char[] array = s.toCharArray();

        reverse(array, 0, s.length() - 1);
        System.out.println(array);
    }

    public static void reverse(char[] chars, int begin, int end) {
        for (int i = begin, j = end; i < j; i++, j--) {
            chars[i] ^= chars[j];
            chars[j] ^= chars[i];
            chars[i] ^= chars[j];
        }
    }

}
