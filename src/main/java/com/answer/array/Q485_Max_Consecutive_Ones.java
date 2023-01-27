package com.answer.array;

public class Q485_Max_Consecutive_Ones {

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
}
