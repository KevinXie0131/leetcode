package com.answer.hashmap;

import java.util.*;

public class Q217_Contains_Duplicate {
    /**
     * 存在重复元素
     * 给你一个整数数组 nums 。如果任一值在数组中出现 至少两次 ，返回 true ；如果数组中每个元素互不相同，返回 false 。
     * Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.
     * 示例 1：
     *  输入：nums = [1,2,3,1]
     *  输出：true
     *  解释：元素 1 在下标 0 和 3 出现。
     */
    /**
     * 使用 set
     * 时间复杂度 : O(n)
     * 空间复杂度 : O(n)
     */
    public boolean containsDuplicate_0(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true; // 遍历数组，数字放到 set 中。如果数字已经存在于 set 中，直接返回 true
            }
            set.add(num);
        }
        return false;
    }
    /**
     * 另一种形式
     * 把 nums 中的元素都丢到哈希集合中（去重）。如果哈希集合的大小小于 n，则说明有重复元素。
     */
    public boolean containsDuplicate_0a(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int x : nums) {
            set.add(x);
        }
        return set.size() < nums.length;
    }
    /**
     * Approach #3 (Hash Table) 哈希表
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int n : nums){
            if(!set.add(n)){ // 如果插入一个元素时发现该元素已经存在于哈希表中，则说明存在重复的元素。
                return true;
            }
        }
        return false;
    }
    /**
     * 使用 HashMap
     */
    public boolean containsDuplicate4(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int n : nums){
            map.put(n, map.getOrDefault(n, 0) + 1);
            if(map.get(n) > 1){
                return true;
            }
        }
        return false;
    }
    /**
     * Approach #2 (Sorting) 排序
     * 时间复杂度 : O(nlogn)。即排序的时间复杂度。扫描的时间复杂度 O(n) 可忽略。
     * 空间复杂度 : O(1)。 没有用到额外空间。如果深究 Arrays.sort(nums) 使用了栈空间，那就是 O(logn)。
     */
    public boolean containsDuplicate_1(int[] nums) {
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 1; i++){
            if(nums[i] == nums[i + 1]){ // 每次判断相邻的两个元素是否相等，如果相等则说明存在重复的元素。
                return true;
            }
        }
        return false;
    }
}
