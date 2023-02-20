package com.answer.prefix_sum;

public class Q724_Find_Pivot_Index {
    public static void main(String[] args) {
        int[] nums = {1,7,3,6,5,6};
     //   int[] nums = {2,1,-1};
        System.out.println(pivotIndex_1(nums));
    }
    /**
     * Approach #1: Prefix Sum
     */
    public static int pivotIndex_1(int[] nums) {
        int sum = 0;
        int leftsum = 0;
        for (int x: nums) {
            sum += x;
        }
        for (int i = 0; i < nums.length; ++i) {
            if (leftsum == sum - leftsum - nums[i]) {
                return i;
            }
            leftsum += nums[i];
        }
        return -1;
    }
}
