package com.answer.dynamic_programming;

public class Q918_Maximum_Sum_Circular_Subarray {
    /**
     * 环形子数组的最大和
     * 给定一个长度为 n 的环形整数数组 nums ，返回 nums 的非空 子数组 的最大可能和 。
     * 环形数组 意味着数组的末端将会与开头相连呈环状。形式上， nums[i] 的下一个元素是 nums[(i + 1) % n] ， nums[i] 的前一个元素是 nums[(i - 1 + n) % n] 。
     * 子数组 最多只能包含固定缓冲区 nums 中的每个元素一次。形式上，对于子数组 nums[i], nums[i + 1], ..., nums[j] ，不存在 i <= k1, k2 <= j 其中 k1 % n == k2 % n 。
     *
     * 示例 1：
     * 输入：nums = [1,-2,3,-2]
     * 输出：3
     * 解释：从子数组 [3] 得到最大和 3
     */
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
