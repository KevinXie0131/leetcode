package com.answer.hashmap;

import java.util.Arrays;

public class Q259_3Sum_Smaller {

    public static void main(String[] args) {
        int[] nums = {-2,0,1,3};
        int target = 2;
        int res = threeSumSmaller(nums, target);
        System.out.println(res);
    }

    public static int threeSumSmaller(int[] nums, int target) {
        int count = 0;
        Arrays.sort(nums);
        int n = nums.length;

        for(int i = 0; i < n-2; i++){
            int left = i + 1;
            int right = n -1;
            while(left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if(sum < target){
                    count += right - left;
                    left++;
                } else {
                    right--;
                }
            }
        }

        return count;
    }
}
