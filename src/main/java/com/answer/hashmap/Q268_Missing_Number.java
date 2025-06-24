package com.answer.hashmap;

import java.util.*;

public class Q268_Missing_Number {
    /**
     * 丢失的数字
     * 给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
     * Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.
     * 示例 1：
     * 输入：nums = [3,0,1]
     * 输出：2
     * 解释：n = 3，因为有 3 个数字，所以所有的数字都在范围 [0,3] 内。2 是丢失的数字，因为它没有出现在 nums 中。
     * 示例 2：
     * 输入：nums = [0,1]
     * 输出：2
     * 解释：n = 2，因为有 2 个数字，所以所有的数字都在范围 [0,2] 内。2 是丢失的数字，因为它没有出现在 nums 中。
     * 示例 3：
     * 输入：nums = [9,6,4,2,3,5,7,0,1]
     * 输出：8
     * 解释：n = 9，因为有 9 个数字，所以所有的数字都在范围 [0,9] 内。8 是丢失的数字，因为它没有出现在 nums 中。
     */
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
        return n; // 输出最后一个值
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
        int total = n * (n + 1) / 2; // 求和公式
        int arrSum = 0;
        for (int i = 0; i < n; i++) {
            arrSum += nums[i];
        }
        return total - arrSum;
    }
}
