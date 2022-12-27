package com.leetcode;

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
        System.out.println((char)66);

        System.out.println("apply".compareTo("apple"));
        System.out.println("apple".compareTo("apply"));
        System.out.println("b".compareTo("a"));
        System.out.println("a".compareTo("b"));

        String ss = "abcdef";
        String ss1 = "abcdef";
        System.out.println(ss.substring(0, 2));
        System.out.println(ss1.substring(2));
    }
}
