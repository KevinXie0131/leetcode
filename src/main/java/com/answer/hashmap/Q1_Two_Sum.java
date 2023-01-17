package com.answer.hashmap;

import java.util.*;

public class Q1_Two_Sum {
    public static void main(String[] args) {
    //    int[] nums = {2,7,11,15};
        int[] nums = {3,2,4};
    //    int target = 9;
        int target = 6;

        int[] result = twoSum_2(nums, target);
        System.out.println(Arrays.toString(result));
    }
    /**
     * Brute force
     */
    public int[] twoSum(int[] nums, int target) {
        for(int i = 0; i<nums.length-1; i++){
            for(int j = i+1; j<nums.length; j++){
                if(nums[i] + nums[j] == target){
                    return new int[]{i, j};
                }
            }
        }

        return null;
    }
    /**
     *
     */
    public int[] twoSum_1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; i++){

            if(map.containsKey(target- nums[i])){
                return new int[]{map.get(target- nums[i]), i};
            }
            map.put(nums[i], i);
        }

        return null;
    }
    /**
     * With sanity testing
     */
    public int[] twoSum_1a(int[] nums, int target) {
        int[] res = new int[2];
        if(nums == null || nums.length == 0){
            return res;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            int temp = target - nums[i];
            if(map.containsKey(temp)){
                res[1] = i;
                res[0] = map.get(temp);
            }
            map.put(nums[i], i);
        }
        return res;

    }
    /**
     * Sorting will change index. This doesn't work.
     */
    public static int[] twoSum_2(int[] nums, int target) {
        Arrays.sort(nums);

        for(int i = 0, j = nums.length-1; i < j;){

            if(nums[i] + nums[j] == target){
                return new int[]{i, j};
            }else if (nums[i] + nums[j] < target){
                i++;
            }else{
                j--;
            }
        }

        return null;
    }
}
