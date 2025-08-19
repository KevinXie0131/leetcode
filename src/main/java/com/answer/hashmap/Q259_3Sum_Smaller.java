package com.answer.hashmap;

import java.util.Arrays;

public class Q259_3Sum_Smaller {
    /**
     * 较小的三数之和
     * 给定一个数组 nums，和一个目标值 target。请你统计并返回 nums 中的三元组 i, j, k 的数量，满足 0 <= i < j < k < n 且 nums[i] + nums[j] + nums[k] < target。
     * Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
     * Example:
     * Input: nums = [-2,0,1,3], and target = 2
     * Output: 2
     * Explanation: Because there are two triplets which sums are less than 2:
     *              [-2,0,1]
     *              [-2,0,3]
     * Follow up: Could you solve it in O(n^2) runtime?
     */
    public static void main(String[] args) {
        int[] nums = {-2,0,1,3};
        int target = 2;
        int res = threeSumSmaller(nums, target);
        System.out.println(res);
    }
    /**
     * 双指针
     * 如果三数之和小于target，此时就找到了(k - j)个满足要求的三元组，然后j右移以指向下一个数
     * 比如此时i, j, k分别为1, 2, 5，那么显然[(1, 2, 3), (1, 2, 4), (1, 2, 5)]都是满足要求的三元组
     * 因此count自增3；并且j自增1，这样下次while循环将会判断三元组(1, 3, 5)是否满足要求
     */
    public static int threeSumSmaller(int[] nums, int target) {
        int n = nums.length;
        int count = 0;

        Arrays.sort(nums);

        for(int i = 0; i < n - 2; i++){
            int left = i + 1;
            int right = n - 1;
            while(left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if(sum < target){
                    count += right - left; // right - left个组合都是小于目标数的
                    left++;
                } else {
                    right--;
                }
            }
        }
        return count;
    }
}
