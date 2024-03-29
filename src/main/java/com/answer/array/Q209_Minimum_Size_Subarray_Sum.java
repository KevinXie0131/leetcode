package com.answer.array;

public class Q209_Minimum_Size_Subarray_Sum {

    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int sum = 0;
        int result = Integer.MAX_VALUE;

        for(int right = 0; right < nums.length; right++){
            sum += nums[right];
            while(sum >= target){
                result = Math.min(result, right - left + 1);
                sum -= nums[left++];
            }
        }

        return result == Integer.MAX_VALUE? 0 : result;
    }
}
