package com.answer.hashmap;

import java.util.*;

public class Q389_Find_the_Difference {
    public static void main(String[] args) {
        String s = "abcd";
        String t = "abcde";
        char c = findTheDifference_1(s, t);
        System.out.println(c);
    }
    /**
     * doesn't work for "a"& "aa"
     */
    public char findTheDifference(String s, String t) {
        Set<Character> set = new HashSet<>();

        char[] c1 = t.toCharArray();
        for(char c : c1){
            set.add(c);
        }
        char[] c2 = s.toCharArray();
        for(char c : c2){
            set.remove(c);
        }
        return set.iterator().next();
    }
    /**
     *
     */
    public static char findTheDifference_1(String s, String t) {
        int[] count = new int[26];
        char[] c1 = t.toCharArray();
        char[] c2 = s.toCharArray();

        for(char c : c1){
            count[c -'a']++;
        }
        for(char c : c2){
            count[c -'a']--;
        }
        for(int i = 0; i < count.length; i++){
            if(count[i] > 0){
                return (char)('a' + i);
            }
        }
        return 'a';
    }
    /**
     *
     */
    public char findTheDifference_2(String s, String t) {
        int sum = 0;
        char[] c1 = t.toCharArray();
        char[] c2 = s.toCharArray();

        for(char c : c1){
            sum += c -'a';
        }
        for(char c : c2){
            sum -= c -'a';
        }

        return (char)('a' + sum);
    }
    /**
     *
     */
    public char findTheDifference_3(String s, String t) {
        int xor = 0;
        char[] c1 = t.toCharArray();
        char[] c2 = s.toCharArray();

        for(char c : c1){
            xor ^= c;
        }
        for(char c : c2){
            xor ^= c;
        }

        return (char)xor;
    }
    /**
     *
     */
    public char findTheDifference_4(String s, String t) {
        char exor = 0;
        for (char c : (new StringBuilder()).append(s).append(t).toString().toCharArray()) {
            exor ^= c;
        }
        return exor;

    }
}
