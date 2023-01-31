package com.answer.array;

import java.util.*;

public class Q1456_Maximum_Number_of_Vowels_in_a_Substring_of_Given_Length {
    public static void main(String[] args) {
        String s = "abciiidef";
        int k = 3;
        System.out.println(maxVowels(s, k));
    }

    /**
     * Use queue as sliding window
     */
    public static int maxVowels(String s, int k) {
        int n = s.length();
        char[] ch = s.toCharArray();
        int max = 0;

        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');

        Deque<Character> queue = new ArrayDeque<>();
        for(int i = 0; i < k; i++){
            if(set.contains(ch[i])){
                queue.offer(ch[i]);
            }
        }
        max = queue.size();

        for(int i = k; i < n; i++){
            if(set.contains(ch[i])){
                queue.offer(ch[i]);
            }
            if(set.contains(ch[i - k])){
                queue.poll();
            }
            max =Math.max(max, queue.size());
        }

        return max;
    }
    /**
     * Use function to check valid character
     */
    public int maxVowels_1(String s, int k) {
        int n = s.length();
        char[] ch = s.toCharArray();
        int max = 0;

        Deque<Character> queue = new ArrayDeque<>();
        for(int i = 0; i < k; i++){
            if(isVowel(ch[i])){
                queue.offer(ch[i]);
            }
        }
        max = queue.size();

        for(int i = k; i < n; i++){
            if(isVowel(ch[i])){
                queue.offer(ch[i]);
            }
            if(isVowel(ch[i - k])){
                queue.poll();
            }
            max =Math.max(max, queue.size());
        }

        return max;
    }
    public boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }
    /**
     * Use two pointer as sliding window
     */
    public int maxVowels_2(String s, int k) {
        int n = s.length();
        char[] ch = s.toCharArray();
        int max = 0;

        for(int i = 0; i < k; i++){
            if(isVowel_1(ch[i]) == 1){
                max++;
            }
        }
        int count = max;
        for(int i = k; i < n; i++){
            count += isVowel_1(ch[i]);
            count -= isVowel_1(ch[i - k]);
            max = Math.max(max, count);
        }

        return max;
    }
    public int isVowel_1(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ? 1 : 0;
    }
}
