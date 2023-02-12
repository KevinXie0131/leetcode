package com.answer.sliding_window;


public class Q1208_Get_Equal_Substrings_Within_Budget {
    public static void main(String[] args) {
        String s = "abcd";
        String t = "bcdf";
        int maxCost = 3;
        System.out.println(equalSubstring(s, t, maxCost));
    }

    /**
     * Similar with Q487 Max Consecutive Ones II
     *
     * Sliding window
     */
    public static int equalSubstring(String s, String t, int maxCost) {
        int max = 0;
        int left = 0, right = 0;

        while(right < s.length()){
            int diff = Math.abs(s.charAt(right) - t.charAt(right));
            maxCost -= diff;

            if(maxCost < 0){
                int diff1 = Math.abs(s.charAt(left) - t.charAt(left));
                maxCost += diff1;
                left++;
            }

            max = Math.max(max, right - left + 1);
            right++;
        }

        return max;
    }

}