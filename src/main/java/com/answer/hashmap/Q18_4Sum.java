package com.answer.hashmap;

import java.util.*;

public class Q18_4Sum {

    public static void main(String[] args) {
        int[] nums = {1,0,-1,0,-2,2};
        int target = 0;
        List<List<Integer>>  res = fourSum(nums, target);
        System.out.println(res);
    }
    /**
     * 推荐使用双指针法
     * 四数之和的双指针解法是两层for循环nums[k] + nums[i]为确定值，依然是循环内有left和right下标作为双指针，
     * 找出nums[k] + nums[i] + nums[left] + nums[right] == target的情况，
     * 三数之和的时间复杂度是O(n^2)，四数之和的时间复杂度是O(n^3) 。
     *
     * 对于15.三数之和 双指针法就是将原本暴力O(n^3)的解法，降为O(n^2)的解法，
     * 四数之和的双指针解法就是将原本暴力O(n^4)的解法，降为O(n^3)的解法。
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>(); // 结果集
        int n = nums.length;
        if(n<4){
            return result;
        }

        Arrays.sort(nums); // 排序数组
        for(int i = 0; i< n;i++){
            // 剪枝处理
            if (nums[i] > target && nums[i] >= 0) {
                break;
            }
            // 对nums[i]去重
            if(i>0 && nums[i] == nums[i-1]){
                continue;
            }

            for(int j = i+1; j< n;j++){
                // 第二级剪枝
                if (nums[j] + nums[i] > target && nums[j] + nums[i] >= 0) {
                    break;
                }
                // 对nums[j]去重
                if(j > i + 1 && nums[j] == nums[j-1]){
                    continue;
                }
                int left = j + 1;
                int right = n-1;
                while(left < right){
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];
                    if(sum < target){
                        left++;
                    } else if(sum > target){
                        right--;
                    } else {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left] , nums[right]));
                        // 对nums[left]和nums[right]去重
                        while(left<right && nums[left]==nums[left+1]){
                            left++;
                        }
                        while(left<right && nums[right]==nums[right-1]){
                            right--;
                        }
                        left++;
                        right--;
                    }
                }
            }
        }
        return result;
    }

}
