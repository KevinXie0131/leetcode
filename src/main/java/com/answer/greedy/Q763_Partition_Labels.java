package com.answer.greedy;

import java.util.*;

public class Q763_Partition_Labels {
    public static void main(String[] args) {
        String s = "ababcbacadefegdehijhklij";
        System.out.println((partitionLabels(s)));
    }

    /**
     * Approach 1: Greedy
     * For each letter encountered, process the last occurrence of that letter, extending the current partition [anchor, j] appropriately.
     */
    public static List<Integer> partitionLabels(String s) {
        List<Integer> res = new ArrayList<>();
        int[] ch = new int[26];
        Arrays.fill(ch, -1);

        for(int i = 0; i < s.length(); i++){
            ch[s.charAt(i) - 'a'] = i;
        }
        int left = 0, right = 0;
        for(int i = 0; i < s.length(); i++){
            right = Math.max(right, ch[s.charAt(i) - 'a']);
            if(i == right){
                res.add(right - left + 1);
                left = right + 1;
            }
        }

        return res;
    }
}
