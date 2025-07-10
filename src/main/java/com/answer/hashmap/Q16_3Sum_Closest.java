package com.answer.hashmap;

import java.util.*;

public class Q16_3Sum_Closest {
    /**
     * 最接近的三数之和
     * 给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。
     * 返回这三个数的和。
     * 假定每组输入只存在恰好一个解。
     * Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is closest to target.
     * Return the sum of the three integers.
     * You may assume that each input would have exactly one solution.
     *
     * 示例 1：
     *  输入：nums = [-1,2,1,-4], target = 1
     *  输出：2
     *  解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2)。
     */
    public static void main(String[] args) {
        int[] nums = {0,1,2};
        int target = 3;
        int result = threeSumClosest(nums, target);
        System.out.println(result);
    }
    /**
     * 双指针法  无需去重
     * 本题思路与 Q15. 三数之和 类似，只是更新结果的条件变为比较差值的大小，保留差值更小的结果
     */
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums); // 首先将数组排序

        int result = nums[0] + nums[1] + nums[2];
        for(int i = 0; i < nums.length - 2; i++){
            int left = i + 1;
            int right = nums.length - 1;
            while(left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if(Math.abs(target - sum) < Math.abs(target - result)){
                    result = sum;
                } else if (sum < target){
                    left++;
                } else if (sum > target){
                    right--;
                } else {
                    return result;
                }
            }
        }
        return result;
    }
    /**
     * 对双指针法进行优化
     */
    public static int threeSumClosest1(int[] nums, int target) {
        Arrays.sort(nums); // 首先将数组排序

        int result = nums[0] + nums[1] + nums[2];
        for(int i = 0; i < nums.length - 2; i++){
            if (i > 0 && nums[i] == nums[i - 1]) { // 优化
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while(left < right){
                // 满足条件的情况下就可以直接取值，而不需要双指针一步步的判断来进行取值，减少了双指针的移动。
                // 如果 target 的值比 nums[i] + nums[left] + nums[left + 1] 的值还小，那么双指针无论怎么取，最后都会取到 nums[i] + nums[left] + nums[left + 1]
                // 同理可证 target 的值比nums[i] + nums[right] + nums[right - 1] 的值还大的情况。
                // 判断最小值
                int min = nums[i] + nums[left] + nums[left + 1];  // 优化
                if(target < min){
                    if(Math.abs(result - target) > Math.abs(min - target))
                        result = min;
                    break;
                }
                //判断最大值
                int max = nums[i] + nums[right] + nums[right - 1];  // 优化
                if(target > max){
                    if(Math.abs(result - target) > Math.abs(max - target))
                        result = max;
                    break;
                }

                int sum = nums[i] + nums[left] + nums[right];
                if(Math.abs(target - sum) < Math.abs(target - result)){
                    result = sum;
                } else if (sum < target){
                    left++;
                    while (right > left && nums[left] == nums[left - 1]){ // 优化 解决nums[left]重复
                        left++;
                    }
                } else if (sum > target){
                    right--;
                    while (right > left && nums[right + 1] == nums[right]){ // 优化  解决nums[right]重复
                        right--;
                    }
                } else {
                    return result;  // 判断三数之和是否等于target
                }
            }
        }
        return result;
    }
}
