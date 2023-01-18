package com.answer.hashmap;

import java.util.*;

public class Q18_4Sum {

    public static void main(String[] args) {
        int[] nums = {1,0,-1,0,-2,2};
        int target = 0;
        List<List<Integer>>  res = fourSum(nums, target);
        System.out.println(res);
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        int n = nums.length;
        if(n<4)return result;

        Arrays.sort(nums);
        for(int i = 0; i< n;i++){
            if(i>0 && nums[i] == nums[i-1]){
                continue;
            }

            for(int j = i+1; j< n;j++){
                if(j > i && nums[j] == nums[j-1]){
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
