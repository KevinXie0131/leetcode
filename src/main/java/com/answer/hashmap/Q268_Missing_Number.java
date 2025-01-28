package com.answer.hashmap;

import java.util.*;

public class Q268_Missing_Number {
    /**
     * HashSet 哈希集合
     * 使用哈希集合，可以将时间复杂度降低到 O(n)。
     */
    public int missingNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int len = nums.length;
        for(int i = 0; i <= len; i++){
            set.add(i);
        }
        for(int n : nums){
            set.remove(n);
        }
        return set.iterator().next();
    }
    /**
     * HashSet 同上
     */
    public int missingNumber_1(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int len = nums.length;

        for(int n : nums){
            set.add(n);
        }
        for(int i = 0; i <= len; i++){
            if(!set.contains(i)){
                return i;
            }
        }
        return -1;
    }
    /**
     * Sorting 排序法
     */
    public int missingNumber_2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return n;
    }
    /**
     * Approach #3 Bit Manipulation: XOR 位运算
     * 数组 nums 中有 n 个数，在这 n 个数的后面添加从 0 到 n 的每个整数，则添加了 n+1 个整数，共有 2n+1 个整数。
     * 由于上述 2n+1 个整数中，丢失的数字出现了一次，其余的数字都出现了两次，因此对上述 2n+1 个整数进行按位异或运算，结果即为丢失的数字。
     */
    public int missingNumber_3(int[] nums) {
        int x = 0;
        for (int n : nums) {
            x ^= n;
        }
        for (int i = 0; i <= nums.length; i++) {
            x ^= i;
        }
        return x;
    }
    /**
     * Approach #4 Gauss' Formula 数学
     */
    public int missingNumber_4(int[] nums) {
        int n = nums.length;
        int total = n * (n + 1) / 2;
        int arrSum = 0;
        for (int i = 0; i < n; i++) {
            arrSum += nums[i];
        }
        return total - arrSum;
    }
}
