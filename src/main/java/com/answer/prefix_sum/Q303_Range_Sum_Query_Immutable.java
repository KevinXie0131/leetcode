package com.answer.prefix_sum;

import java.util.*;

public class Q303_Range_Sum_Query_Immutable {
    /**
     * Given an integer array nums, handle multiple queries of the following type:
     * Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
     * Implement the NumArray class:
     *  NumArray(int[] nums) Initializes the object with the integer array nums.
     *  int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive
     *  (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
     * 区域和检索 - 数组不可变
     * 给定一个整数数组  nums，处理以下类型的多个查询:
     * 计算索引 left 和 right （包含 left 和 right）之间的 nums 元素的 和 ，其中 left <= right
     * 实现 NumArray 类：
     *  NumArray(int[] nums) 使用数组 nums 初始化对象
     *  int sumRange(int i, int j) 返回数组 nums 中索引 left 和 right 之间的元素的 总和 ，包含 left 和 right 两点
     *  （也就是 nums[left] + nums[left + 1] + ... + nums[right] )
     */
    public static void main(String[] args) {
        /**
         * 输入：["NumArray", "sumRange", "sumRange", "sumRange"]
         * [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
         * 输出：[null, 1, -1, -3]
         */
    }
    /**
     * 前缀和
     * [left, right] = [0, right] - [0, left - 1] = preSum[right + 1] - preSum[left]
     */
    List<Integer> preSum;

    public void NumArray(int[] nums) {
        preSum = new ArrayList<>();
        preSum.add(0);

        for (int i = 0; i < nums.length; i++) {
            preSum.add(nums[i] + preSum.get(i));
        }
    }

    public int sumRange(int left, int right) {
        return preSum.get(right + 1) - preSum.get(left);
    }
    /**
     * 子数组的元素和转化成两个前缀和的差，下标区间 [left,right] 的元素和等于前缀 [0,right] 的元素和减去另一个前缀 [0,left−1] 的元素和，即
     * sums[right+1]−sums[left]
     */
    int[] sums;

    public void NumArray1(int[] nums) {
        int n = nums.length;
        sums = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sums[i + 1] = sums[i] + nums[i];
        }
    }
    // sumRange(i,j)=sums[j+1]−sums[i]
    public int sumRange1(int i, int j) {
        return sums[j + 1] - sums[i];
    }
}
