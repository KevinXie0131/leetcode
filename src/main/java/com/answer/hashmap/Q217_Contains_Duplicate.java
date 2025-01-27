package com.answer.hashmap;

import java.util.*;

public class Q217_Contains_Duplicate {
    /**
     * 使用 set
     */
    public boolean containsDuplicate_0(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num: nums) {
            if (set.contains(num)) {
                return true; // 遍历数组，数字放到 set 中。如果数字已经存在于 set 中，直接返回 true
            }
            set.add(num);
        }
        return false;
     /*   Set<Integer> set = new HashSet<>();  // 另一种形式
        for (int x : nums) {
            set.add(x);
        }
        return set.size() < nums.length; */
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
     * Approach #2 (Sorting) 排序
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
