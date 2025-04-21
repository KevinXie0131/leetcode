package com.answer.prefix_sum;

import java.util.HashMap;

public class Q560_Subarray_Sum_Equals_K {
    /**
     * 题目要求连续子数组的和等于定值k，一般来说，看到连续子数组元素相关的，基本就是滑动窗口或者前缀和（笨人题刷得不多，只能总结出这两个），
     * 而滑动窗口的使用需要满足前置条件：窗口内的元素必须要有“单调性”，也就是说新元素进入窗口必须使得内部和不能减少，必须使得窗口向着不满足约束的方向前进。
     * 由于数组存在负数，因此新入元素反而可能使得窗口更加不超过k。因此排除滑动窗口，想到使用前缀和。
     * 前缀和的优势就在于能够将子数组求和的时间优化到𝑂(1)
     */
    public int subarraySum(int[] nums, int k) {
        //计算前缀和数组
        int len = nums.length;
        int[] prefixSum = new int[len + 1];
        prefixSum[0] = 0;
        for (int i = 0; i < len; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        int result = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int sum : prefixSum) {
            int index = sum - k;
            if (map.containsKey(index)) { //已遍历元素中存在index
                result += map.get(index);  //加上相应的个数
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1); //当前遍历结束，更新sum出现个数，便于后续判断使用
        }
        return result;
    }
}
