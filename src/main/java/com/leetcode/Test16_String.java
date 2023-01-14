package com.leetcode;

import java.util.Arrays;

public class Test16_String {

    public static void main(String[] args) {

        String s = "abc";

        char char_a = 'a';
        char char_z = 'z';
        char char_A = 'A';
        char char_Z = 'Z';
        System.out.println(Integer.valueOf(char_a));
        System.out.println(Integer.valueOf(char_z));
        System.out.println(Integer.valueOf(char_A));
        System.out.println(Integer.valueOf(char_Z));

        int int_a = 'a';
        System.out.println(int_a);
        System.out.println((char) 66);

        System.out.println("apply".compareTo("apple"));
        System.out.println("apple".compareTo("apply"));
        System.out.println("b".compareTo("a"));
        System.out.println("a".compareTo("b"));

        String ss = "abcdef";
        String ss1 = "abcdef";
        System.out.println(ss.substring(0, 2));
        System.out.println(ss1.substring(2));

        String str = "abc";
        char[] array = str.toCharArray();
        System.out.println(Arrays.toString(array));

        System.out.println(isPalindrome("abba"));
        System.out.println(isPalindrome("abcba"));
        System.out.println(isPalindrome("abcdba"));

        System.out.println(str.length());

        char c = 'x';
        String s1 = String.valueOf(c);
        System.out.println(s1);
        char[] c1 = {'x', 'y', 'z'};
        String s2 = String.valueOf(c1);
        System.out.println(s2);

        Integer i1 = Integer.valueOf("123");
        System.out.println(i1);
        int i2 = Integer.parseInt("12345");
        System.out.println(i2);
    }

    public static boolean isPalindrome(String str){
        char[] array = str.toCharArray();
        for(int i = 0, j = array.length - 1; i < j; i++, j--){
            if(array[i] != array[j]){
                return false;
            }
        }
        return true;
    }
}
