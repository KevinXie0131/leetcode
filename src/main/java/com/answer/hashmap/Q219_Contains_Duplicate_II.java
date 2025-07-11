package com.answer.hashmap;

import java.util.*;

public class Q219_Contains_Duplicate_II {
    /**
     * 存在重复元素 II
     * 给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，满足 nums[i] == nums[j] 且 abs(i - j) <= k 。如果存在，返回 true ；否则，返回 false 。
     * Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.
     * 示例 1：
     *  输入：nums = [1,2,3,1], k = 3
     *  输出：true
     * 示例 2：
     *  输入：nums = [1,0,1,1], k = 1
     *  输出：true
     * 示例 3：
     *  输入：nums = [1,2,3,1,2,3], k = 2
     *  输出：false
     */
    /**
     * Use HashSet as sliding window 定长滑动窗口
     * 维护一个哈希表，里面始终最多包含 k 个元素，当出现重复值时则说明在 k 距离内存在重复元素
     * 每次遍历一个元素则将其加入哈希表中，如果哈希表的大小大于 k，则移除最前面的数字
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for(int i = 0 ; i < nums.length; i++){
            if(set.contains(nums[i])){ // 判断 nums[i] 是否在哈希集合中，如果在哈希集合中则在同一个滑动窗口中有重复元素，返回 true，如果不在哈希集合中则将其加入哈希集合。
                return true;
            }
            set.add(nums[i]);
            if(set.size() > k){ // 如果 i>k，则下标 i−k−1 处的元素被移出滑动窗口，因此将 nums[i−k−1] 从哈希集合中删除；
                set.remove(nums[i - k]);
            }
        }
        return false;
    }
    /**
     * another form
     * 滑动窗口 + 哈希表: 整理题意：是否存在长度不超过的 k+1 窗口，窗口内有相同元素。
     */
    public boolean containsNearbyDuplicate0(int[] nums, int k) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (i > k) {
                set.remove(nums[i - k - 1]);  // 长度超过k，移除最左侧元素
            }
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
        }
        return false;
    }
    /**
     * Use HashMap 哈希表
     */
    public boolean containsNearbyDuplicate_1(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // 记录每个值最近出现过的索引
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            int num = nums[i];
            if (map.containsKey(num) && i - map.get(num) <= k) {
                return true; // num之前已经出现过，且当前num两个索引的差值小于k，满足条件
            }
            map.put(num, i);
        }
        return false;
    }
}
