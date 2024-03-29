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
     * HashSet - this method works
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
                if(countOfChar == 0) return ch;
                // Once a match is found we reduce frequency left.
                // This eliminates the possibility of a false match later.
                counterS.put(ch, countOfChar - 1);
            }
        }
        return extraChar;
    }
    /**
     * Use counter
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
     * Find sum
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
     * XOR
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
