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
}
