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
     *
     * 「滑动窗口」是固定大小的，「双指针」是不固定大小的；
     * 「滑动窗口」一定是同向移动的，「双指针」可以相向移动。
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
    /**
     * Official answer
     */
    public int equalSubstring_1(String s, String t, int maxCost) {
        int n = s.length();
        int[] diff = new int[n];
        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(s.charAt(i) - t.charAt(i));
        }
        int maxLength = 0;
        int start = 0, end = 0;
        int sum = 0;
        while (end < n) {
            sum += diff[end];
            while (sum > maxCost) {
                sum -= diff[start];
                start++;
            }
            maxLength = Math.max(maxLength, end - start + 1);
            end++;
        }
        return maxLength;
    }
}