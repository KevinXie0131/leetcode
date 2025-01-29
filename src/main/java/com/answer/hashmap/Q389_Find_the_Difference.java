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
     * HashSet - doesn't work for "a"& "aa"
     * 提交后有java.util.NoSuchElementException
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
     * HashSet - this method works 这个方法可以运行
     */
    public char findTheDifference_0(String s, String t) {
        Set<Character> set = new HashSet<>();
        char[] array = s.concat(t).toCharArray();
        for(int i = 0; i <array.length; i++){
            if(set.contains(array[i])){
                set.remove(array[i]);
            }else{
                set.add(array[i]);
            }
        }
        return (char)set.toArray()[0];
    }
    /**
     * HashMap
     */
    public char findTheDifference_0a(String s, String t) {
        char extraChar = '\0';

        // Prepare a counter for string s.
        // This hash map holds the characters as keys and respective frequency as value.
        HashMap <Character,Integer> counterS = new HashMap <>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            counterS.put(ch, counterS.getOrDefault(ch, 0) + 1);
        }

        // Iterate through string t and find the character which is not in s.
        for (int i = 0; i < t.length(); i += 1) {
            char ch = t.charAt(i);
            if (!counterS.containsKey(ch)) {
                extraChar = ch;
                break;
            } else {
                int countOfChar = counterS.get(ch);
                if(countOfChar == 0) {
                    return ch;
                }
                // Once a match is found we reduce frequency left.
                // This eliminates the possibility of a false match later.
                counterS.put(ch, countOfChar - 1);
            }
        }
        return extraChar;
    }
    /**
     * Use counter 用数组对hash
     * int[26]是常数，所以空间复杂度O(1)
     */
    public static char findTheDifference_1(String s, String t) {
        int[] count = new int[26];
        char[] c1 = t.toCharArray();
        char[] c2 = s.toCharArray();
        // s和t长度可能不一样，所以要遍历两边
        for(char c : c1){
            count[c -'a']++;
        }
        for(char c : c2){
            count[c -'a']--;
        }
        // 题目有要求t.length == s.length + 1，所以可以遍历一边
/*        for(int i = 0 ; i < c1.length; i++){
            count[c1[i] -'a']++;
            if(i < c2.length){
                count[c2[i] -'a']--;
            }
        }*/
        for(int i = 0; i < count.length; i++){ // int[26]是常数，所以时间复杂度O(1)
            if(count[i] > 0){
                return (char)('a' + i);
            }
        }
        return 'a';
    }
    /**
     * Find sum 加法求和运算
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
  /*      for(char c : c1){
            sum += c;
        }
        for(char c : c2){
            sum -= c;
        }
        return (char)sum;*/
    }
    /**
     * XOR 异或
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
     * XOR - concat string
     */
    public char findTheDifference_4(String s, String t) {
        char exor = 0;
        for (char c : (new StringBuilder()).append(s).append(t).toString().toCharArray()) {
            exor ^= c;
        }
        return exor;

    }
}
