package com.answer.sliding_window;

public class Q1004_Max_Consecutive_Ones_III {

    public static void main(String[] args) {
        int[] nums = {1,1,1,0,0,0,1,1,1,1,0};
        int k = 2;
        System.out.println(longestOnes(nums, k));
    }

    /**
     * Similar with Q487 Max Consecutive Ones II
     *
     * Approach: Sliding Window
     */
    public static int longestOnes(int[] nums, int k) {
        int max = 0, count = 0;
        int right = 0, left = 0;
        int len = nums.length;

        while(right < len){
            if(nums[right] == 0){
                count++;

                while(count > k){
                    if(nums[left] == 0){
                        count--;
                    }
                    left++;
                }
            }

            max = Math.max(max, right - left + 1);
            right++;
        }

        return max;
    }

}
