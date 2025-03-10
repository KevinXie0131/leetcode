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
     *
     * 由于同一个字母只能出现在同一个片段，显然同一个字母的第一次出现的下标位置和最后一次出现的下标位置必须出现在同一个片段。
     *
     * 遍历一遍找到每个字符的最远位置
     * 若找到之前遍历过的字母的最远边界，则找到分割点
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
