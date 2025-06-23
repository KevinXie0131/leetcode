package com.answer.hashmap;

import java.util.*;

public class Q1_Two_Sum {
    /**
     * 两数之和
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
     * 你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。
     * You may assume that each input would have exactly one solution, and you may not use the same element twice.
     * 你可以按任意顺序返回答案。
     * You can return the answer in any order.
     * 进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？
     * Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?
     *
     * 示例 1：
     *  输入：nums = [2,7,11,15], target = 9
     *  输出：[0,1]
     *  解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
     * 示例 2：
     *  输入：nums = [3,2,4], target = 6
     *  输出：[1,2]
     * 示例 3：
     *  输入：nums = [3,3], target = 6
     *  输出：[0,1]
     * Only one valid answer exists.只会存在一个有效答案
     */
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
     * 使用哈希表
     * 两数之和这道题目，不仅要判断y是否存在而且还要记录y的下标位置，因为要返回x 和 y的下标
     * map是一种<key, value>的结构，本题可以用key保存数值，用value在保存数值所在的下标。所以使用map最为合适。
     *
     * 使用 Hash 表记录所有已遍历元素及对应的下标，遍历的同时查找满足条件的数字是否已遍历，若找到返回即可。
     */
    public int[] twoSum_0(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];

        for(int i = 0; i < nums.length; i++){
            int temp = target - nums[i]; // 遍历当前元素，并在map中寻找是否有匹配的key
            if(map.containsKey(nums[i])){
                result[0] = map.get(nums[i]);
                result[1] = i;
            }

            map.put(temp, i);    // 如果没找到匹配对，就把访问过的元素和下标加入到map中
        }
        return result;
    }
    /**
     * 使用哈希表方法2
     */
    public int[] twoSum_1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; i++){
            //int complement = target - nums[i]; // 记录当前的目标值的余数 can use complement to replace  target - nums[i]
            if(map.containsKey(target- nums[i])){ // 查找当前的map中是否有满足要求的值
                return new int[]{map.get(target- nums[i]), i}; //  如果有，返回目标值
            }
            map.put(nums[i], i); //  如果没有，把访问过的元素和下标加入map中
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
    /**
     * 使用排序 + 双指针 It can work
     */
    public int[] twoSum_3(int[] nums, int target) {
        int m=0, n=0, k, board=0;
        int[] res=new int[2];
        int[] tmp1=new int[nums.length];
        //备份原本下标的nums数组
        System.arraycopy(nums,0, tmp1,0, nums.length);
        //将nums排序
        Arrays.sort(nums);
        //双指针
        for(int i=0,j=nums.length-1;i<j;){
            if(nums[i]+nums[j]<target)
                i++;
            else if(nums[i]+nums[j]>target)
                j--;
            else if(nums[i]+nums[j]==target){
                m=i;
                n=j;
                break;
            }
        }
        //找到nums[m]在tmp1数组中的下标
        for(k=0;k<nums.length;k++){
            if(tmp1[k]==nums[m]){
                res[0]=k;
                break;
            }
        }
        //找到nums[n]在tmp1数组中的下标
        for(int i=0;i<nums.length;i++){
            if(tmp1[i]==nums[n]&&i!=k)
                res[1]=i;
        }
        return res;
    }
}
