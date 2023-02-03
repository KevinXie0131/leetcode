package com.answer.array;

import java.util.*;

public class Q448_Find_All_Numbers_Disappeared_in_an_Array {
    public static void main(String[] args) {
        int[] nums = {4,3,2,7,8,2,3,1};
      //  int[] nums = {1,1};
        List<Integer> res = findDisappearedNumbers_4(nums);
        System.out.println(res);
    }

    /**
     * Use array as counter
     */
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        int len = nums.length;
        List<Integer> res = new ArrayList<Integer>();
        int[] array = new int[len + 1];

        for(int i = 0; i <= nums.length - 1; i++){
            array[nums[i]]--;
        }
        for(int i = 1; i <= nums.length; i++){
            if(array[i] == 0){
                res.add(i);
            }
        }

        return res;
    }

    /**
     * Approach 1: Using Hash Map
     */
    public static List<Integer> findDisappearedNumbers_1(int[] nums) {
        int len = nums.length;
        List<Integer> res = new ArrayList<Integer>();
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i <= nums.length - 1; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        for(int i = 1; i <= nums.length; i++){
            if(!map.containsKey(i)){
                res.add(i);
            }
        }

        return res;
    }
    /**
     * Approach 2: O(1) Space InPlace Modification Solution
     */
    public static List<Integer> findDisappearedNumbers_2(int[] nums) {
        int len = nums.length;
        List<Integer> res = new ArrayList<>();

        for(int i = 0; i <= nums.length - 1; i++){
            int x = (nums[i] - 1) % len;
            nums[x] += len;
        }
        for(int i = 0; i <= nums.length - 1; i++){
            if(nums[i] <= len){
                res.add(i + 1);
            }
        }

        return res;
    }
    /**
     *
     */
    public static List<Integer> findDisappearedNumbers_4(int[] nums) {
        int len = nums.length;
        List<Integer> res = new ArrayList<Integer>();

        for(int i = 0; i <= nums.length - 1; i++){
            int x = Math.abs(nums[i]) - 1;
            if(nums[x] > 0){
                nums[x] = -nums[x];
            }
        }
        for(int i = 0; i <= nums.length - 1; i++){
            if(nums[i] > 0){
                res.add(i + 1);
            }
        }

        return res;
    }
}
