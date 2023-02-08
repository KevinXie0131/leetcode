package com.answer.two_pointers;

public class Q643_Maximum_Average_Subarray_I {
    public static void main(String[] args) {
        int[] nums = {1,12,-5,-6,50,3};
        int k = 4;
        System.out.println(findMaxAverage_2(nums, k));
    }
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
    /**
     * Approach #1 Cumulative Sum
     */
    public static double findMaxAverage_2(int[] nums, int k) {
        int[] sum = new int[nums.length];
        sum[0] = nums[0];

        for (int i = 1; i < nums.length; i++){
            sum[i] = sum[i - 1] + nums[i];
        }
        // 1,12,-5,-6,50,3
        // 1,13,8,2,52,55
        double res = sum[k - 1] * 1.0 / k;

        for (int i = k; i < nums.length; i++) {
            res = Math.max(res, (sum[i] - sum[i - k]) * 1.0 / k);
        }
        return res;
    }
}
