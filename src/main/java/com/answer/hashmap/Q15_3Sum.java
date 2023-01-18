package com.answer.hashmap;

import java.util.*;
import java.util.stream.Collectors;

public class Q15_3Sum {

    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        List<List<Integer>> list = threeSum(nums);
        System.out.println(list);
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        int n = nums.length;
        if(n<3) return result;

        Arrays.sort(nums);

        for(int i = 0; i < n; i++){
            if(nums[i] > 0)  return result;

            if(i > 0 && i < n-1 && nums[i] == nums[i-1]  ){
                continue;
            }
            int left = i + 1;
            int right = n - 1;
            while(left < right){
                if(nums[i] + nums[left] + nums[right] == 0){
                    int[] res = new int[]{nums[i],nums[left],nums[right]};
                    result.add(Arrays.stream(res).boxed().collect(Collectors.toList()));

                    while(left<right && nums[left]==nums[left+1]){
                        left++;
                    }
                    while(left<right && nums[right]==nums[right-1]){
                        right--;
                    }
                    left++;
                    right--;
                }else if(nums[i] + nums[left] + nums[right] < 0){
                    left++;
                }else{
                    right--;
                }
            }
        }

        return result;
    }
}
