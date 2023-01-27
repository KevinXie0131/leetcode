package com.answer.array;

public class Q485_Max_Consecutive_Ones {

    public static void main(String[] args) {
        int[] nums = {1,1,0,1,1,1};
        int res = findMaxConsecutiveOnes_1(nums);
        System.out.println(res);
    }
    /**
     * Brute force
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
     * Sliding window
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
}
