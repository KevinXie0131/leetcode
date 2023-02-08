package com.answer.two_pointers;

public class Q643_Maximum_Average_Subarray_I {

    /**
     * Approach #2 Sliding Window
     */
    public double findMaxAverage(int[] nums, int k) {
        int i = 0;
        int j = 0;
        int len = nums.length;
        int max = 0;
        int value = 0;

        for(; j < k; j++){
            value += nums[j];
        }
        max = value;

        while(j < len){
            value -= nums[i++];
            value += nums[j++];
            max = Math.max(max, value);
        }

        return max * 1.0 / k;
    }
    /**
     * Another form of sliding window
     */
    public double findMaxAverage_1(int[] nums, int k) {
        int len = nums.length;
        int max = 0;
        int sum = 0;

        for(int i = 0; i < k; i++){
            sum += nums[i];
        }
        max = sum;
        for(int i = k; i < len; i++){
            sum += nums[i];
            sum -= nums[i - k];
            max = Math.max(max, sum);
        }

        return max * 1.0 / k;
    }
}
