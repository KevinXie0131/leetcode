package com.answer.sliding_window;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q567_Permutation_in_String {
    public static void main(String[] args) {
        String s1 = "ab", s2 = "eidbaooo";
        System.out.println(checkInclusion(s1, s2));
    }

    /**
     * Approach 5: Sliding Window
     */
    public static boolean checkInclusion(String s1, String s2) {
        int slen = s2.length();
        int plen = s1.length();
        if (slen < plen) {
            return false;
        }
        int[] sCount = new int[26];
        int[] pCount = new int[26];
        for(int i = 0; i < plen; i++){
            sCount[s2.charAt(i) - 'a']++;
            pCount[s1.charAt(i) - 'a']++;
        }
        if(Arrays.equals(sCount, pCount)){
            return true;
        }

        for(int i = 0; i < slen - plen; i++){
            sCount[s2.charAt(i) - 'a']--;
            sCount[s2.charAt(i + plen) - 'a']++;

            if(Arrays.equals(sCount, pCount)){
                return true;
            }
        }
        return false;
    }
}
