package com.answer.dynamic_programming;

public class Q918_Maximum_Sum_Circular_Subarray {
    /**
     * Dynamic Programming
     * 第一种情况：这个子数组不是环状的，就是说首尾不相连。
     * 第二种情况：这个子数组一部分在首部，一部分在尾部，我们可以将这第二种情况转换成第一种情况
     *   所以这最大的环形子数组和 = max(最大子数组和，数组总和-最小子数组和)
     */
    public int maxSubarraySumCircular(int[] nums) {
        /**
         * total为数组的总和，
         * maxSum为最大子数组和，
         * minSum为最小子数组和，
         * curMax为包含当前元素的最大子数组和，
         * curMin为包含当前元素的最小子数组和
         */
        int total = 0, maxSum = nums[0], curMax = 0, minSum =nums[0], curMin = 0;
        for (int a :nums) {
            curMax = Math.max(curMax + a, a);
            maxSum = Math.max(maxSum, curMax);
            curMin = Math.min(curMin + a, a);
            minSum = Math.min(minSum, curMin);
            total += a;
        }
        return maxSum > 0 ? Math.max(maxSum, total - minSum) : maxSum;

    }
}
