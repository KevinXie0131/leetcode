package com.answer.prefix_sum;

import java.util.HashMap;

public class Q930_Binary_Subarrays_With_Sum {
    /**
     * Given a binary array nums and an integer goal, return the number of non-empty subarrays with a sum goal.
     * A subarray is a contiguous part of the array.
     * 和相同的二元子数组
     * 一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。
     * 子数组 是数组的一段连续部分。
     */
    public static void main(String[] args) {
        int[] nums = {1,0,1,0,1};
        int goal = 2;
        /**
         * 输出：4
         * 解释：有 4 个满足题目要求的子数组：[1,0,1]、[1,0,1,0]、[0,1,0,1]、[1,0,1]
         */
        System.out.println(numSubarraysWithSum1(nums, goal));
    }
    /**
     * 前缀和 + HashMap
     * similar with Q1248_Count_Number_of_Nice_Subarrays
     */
    static public int numSubarraysWithSum(int[] nums, int goal) {
        int len = nums.length;
        int[] prefixSum = new int[len + 1];
        for (int i = 0; i < len; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        int count = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < len + 1; i++){
            if(map.containsKey(prefixSum[i] - goal)){
                count += map.get(prefixSum[i] - goal);
            }
            map.put(prefixSum[i], map.getOrDefault(prefixSum[i], 0) + 1);
        }
        return count;
    }
    /**
     * 思路一：滑动窗口
     * 按照常规的滑动窗口思路，只要设置一个左指针和一个右指针
     *   滑动窗口元素和小于goal：右指针右移
     *   滑动窗口元素和大于goal：左指针右移
     * 但是这里要注意一个点：那就是子数组前面为0怎么办
     * 很明显，仅仅是上面两个指针，左指针必然会跳过0，然后右移
     * 那么我们可以考虑设置两个左指针，一个指向那一堆0前面的1，一个指向那一堆0后的1
     * 比如：
     * 1 0 0 0 0 1 1 0 1
     * L1 L2 R
     * 那么L2与L1之间有几个0，那这个部分就有多少种子数组
     * 同时本题只含有0与1，那么肯定可以保证滑动窗口内的元素和要么等于goal，要么等于goal+1
     * 那么我们可以定义：
     *  L1与R直接的窗口内元素为goal
     *  L2与R直接的窗口内元素为goal+1
     */
    static public int numSubarraysWithSum1(int[] nums, int goal) {
        int n = nums.length;
        // left1与left2之间夹着的是很多个0
        int left1 = 0, left2 = 0, right = 0;
        int sum1 = 0, sum2 = 0;
        int res = 0;
        // 右边界
        while (right < n) {
            sum1 += nums[right];
            // sum1 要等于 goal+1
            while (left1 <= right && sum1 > goal) {
                sum1 -= nums[left1];
                left1++;
            }
            sum2 += nums[right];
            // sum2 要等于 goal
            while (left2 <= right && sum2 >= goal) {
                sum2 -= nums[left2];
                left2++;
            }
            // 其中的每个0都能算一种情况
            res += left2 - left1;
            // 右指针右移
            right++;
        }
        return res;
    }
}
