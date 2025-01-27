package com.answer.hashmap;

import java.util.*;

public class Q217_Contains_Duplicate {
    /**
     * 使用 set
     * 时间复杂度 : O(n)
     * 空间复杂度 : O(n)
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
     * 使用 hashMap
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
