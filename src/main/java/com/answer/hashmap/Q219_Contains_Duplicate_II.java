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
     * Use HashSet as sliding window
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for(int i = 0 ; i < nums.length; i++){
            if(set.contains(nums[i])){
                return true;
            }
            set.add(nums[i]);
            if(set.size() > k){
                set.remove(nums[i - k]);
            }
        }
        return false;
    }
    /**
     * Use HashMap
     */
    public boolean containsNearbyDuplicate_1(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            int num = nums[i];
            if (map.containsKey(num) && i - map.get(num) <= k) {
                return true;
            }
            map.put(num, i);
        }
        return false;
    }
}
