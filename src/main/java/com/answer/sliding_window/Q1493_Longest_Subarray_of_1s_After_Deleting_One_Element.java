package com.answer.sliding_window;

public class Q1493_Longest_Subarray_of_1s_After_Deleting_One_Element {

    /**
     * Similar with Q487 Max Consecutive Ones II
     *
     * Sliding window
     */
    public int longestSubarray(int[] nums) {
        int left = 0, right = 0;
        int max = 0, count = 0;

        while (right < nums.length) {
            if (nums[right] == 0) {
                count++;
            }
            while (count > 1) {
                if (nums[left] == 0) {
                    count--;
                }
                left++;
            }

            max = Math.max(max, right - left);
            right++;
        }

        return max;
    }
}
