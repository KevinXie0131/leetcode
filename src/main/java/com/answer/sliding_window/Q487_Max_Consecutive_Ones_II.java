package com.answer.sliding_window;

public class Q487_Max_Consecutive_Ones_II {

    public static void main(String[] args) {
        int[] nums = {1,0,1,1,0,1};
        System.out.println(findMaxConsecutiveOnes(nums));
    }

    /**
     * Approach 2: Sliding Window
     */
    public static int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int count = 0;
        int left = 0;
        int right = 0;

        while(right < nums.length){
            if(nums[right] == 0){
                count++;
                while(count > 1){
                    /**
                     * count -= nums[l++] == 0 ? 1 : 0;
                     */
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
