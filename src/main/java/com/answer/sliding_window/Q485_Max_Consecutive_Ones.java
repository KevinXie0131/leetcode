package com.answer.sliding_window;

public class Q485_Max_Consecutive_Ones {
    /**
     * Given a binary array nums, return the maximum number of consecutive 1's in the array.
     * 最大连续 1 的个数: 给定一个二进制数组 nums ， 计算其中最大连续 1 的个数。
     * nums[i] is either 0 or 1.
     */
    public static void main(String[] args) {
        int[] nums = {1,1,0,1,1,1};
        int res = findMaxConsecutiveOnes_1(nums);
        System.out.println(res);
    }
    /**
     * Brute force 一次遍历
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int maxCount = 0, count = 0;

        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 1){
                count++;
            }else{
                maxCount = Math.max(count, maxCount);
                count = 0;
            }
        }
        maxCount = Math.max(count, maxCount);
        return maxCount;
    }
    /**
     * Sliding window 滑动窗口
     */
    public static int findMaxConsecutiveOnes_1(int[] nums) {
        int maxCount = 0, left = 0, right = 0;
        int length = nums.length;

        while(right < length){
            if(nums[right] == 0){
                maxCount = Math.max(maxCount, right - left);
                right++;
                left = right;
            } else {
                right++;
            }
        }
        maxCount = Math.max(maxCount, right - left);
        return maxCount;
    }
    /**
     * 动态规划
     * P[i] = P[i - 1] + 1 if nums[i] == 1
     * P[i] = 0 if nums[i] == 0
     */
    public static int findMaxConsecutiveOnes_3(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }
        int maxOnes = 0;
        int length = nums.length;

        if(nums[0] == 1) maxOnes = 1; // 初始值 例如[1, 0]

        for(int i = 1; i < length; i++){
            if(nums[i] == 1){
                nums[i] = nums[i - 1] + 1;
            }else{
                nums[i] = 0;
            }
            maxOnes = Math.max(maxOnes, nums[i]);
        }
        return maxOnes;
    }
}
