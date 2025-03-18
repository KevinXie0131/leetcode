package com.answer.hashmap;

import java.util.*;

public class Q16_3Sum_Closest {

    public static void main(String[] args) {
        int[] nums = {0,1,2};
        int target = 3;
        int result = threeSumClosest(nums, target);
        System.out.println(result);
    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums); // 首先将数组排序

        int result = nums[0] + nums[1] + nums[2];
        for(int i = 0; i < nums.length - 2; i++){
            int left = i + 1;
            int right = nums.length - 1;
            while(left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if(Math.abs(target -sum) < Math.abs(target - result)){
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
}
