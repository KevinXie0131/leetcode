package com.answer.math;

import java.util.*;

public class Q169_Majority_Element {
    /**
     * 多数元素: 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     *
     * 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
     * Follow-up: Could you solve the problem in linear time and in O(1) space?
     */
    public static void main(String[] args) {
        int[] nums = {2,2,1,1,1,2,2};
        int res = majorityElement_0(nums);
    }
    /**
     * Divide and conquer 分治法
     * 其实这个题目考的就是分治
     * 分治的数学基础：把数据裁成两部分，那一定至少会在一部分里是存在这个众数的，递归下去拆解，直至只有一个元素，不可分割，那他就是它那部分的众数，
     * 递归回来，合并两部分，由于每一部分都得出了各自的众数，那就比较两部分中众数的数量，数量多的才算这两部分构成的元素里面的众数。
     * 时间复杂度: O(nlogn) 空间复杂度: O(n)
     */
    static public int majorityElement_0(int[] nums) {
        return findMajorElment(nums,0,nums.length-1);
    }
    // 分治法是递归
    static public int findMajorElment(int[] nums,int low,int high){
        if(low == high){ // base case; the only element in an array of size 1 is the majority element.
            return nums[low];
        }
        int mid = (high - low) / 2 + low; // recurse on left and right halves of this slice.
        int leftMajority =  findMajorElment(nums, low, mid);
        int rightMajority = findMajorElment(nums, mid + 1, high);

        if(leftMajority == rightMajority){ // if the two halves agree on the majority element, return it. 可以省略
            return leftMajority;
        }
        // otherwise, count each element and return the "winner".
        int leftCount = findMajorElentCount(nums, leftMajority, low, mid);              // 返回[low, mid]中left的数量
        int rightCount = findMajorElentCount(nums, rightMajority, mid + 1, high);  // 返回[mid + 1, high]中right的数量
        // The following can work
        // int leftCount = findMajorElentCount(nums, leftMajority, low, high);
        // int rightCount = findMajorElentCount(nums, rightMajority, low, high);
/*      int leftCount=0;  // 另一种形式
        int rightCount=0;
        for(int i = low; i <= high; i++){
            if(leftMajority == nums[i]){
                ++leftCount;
            }else if(rightMajority == nums[i]){
                ++rightCount;
            }
        }*/
        return (leftCount > rightCount) ? leftMajority : rightMajority; // 在当前区间比较leftMajority和rightMajority那个多
    }

    static  public int findMajorElentCount(int[] nums, int target, int low, int high){
        int count = 0;
        for(int i = low; i <= high; i++){
            if(target == nums[i]){
                ++count;
            }
        }
        return count;
    }
    /**
     * 使用map计数
     */
    public int majorityElement_1(int[] nums) {
        Map<Integer, Long> map = new HashMap<>();

        for(int n : nums){
            map.put(n, map.getOrDefault(n, (long)0) + 1);
        }

        long limit = nums.length >> 1; // 多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。

        for (Map.Entry<Integer, Long> entry : map.entrySet()){
            if (entry.getValue() > limit){
                return entry.getKey();
            }
        }
        return -1;
    }
    /**
     * 排序
     * 我们先将 nums 数组排序，那么下标为 n/2 的元素（下标从 0 开始）一定是众数。
     */
    public int majorityElement3(int[] nums) {
        Arrays.sort(nums); // 需要排序
        return nums[nums.length / 2];
    }
    /**
     * Boyer-Moore Voting Algorithm 摩尔投票算法: 通过消除不同元素之间的对抗来找到可能的多数元素
     */
    public int majorityElement_2(int[] nums) {
        int a = nums[0], b = 1;
        for(int i=1; i < nums.length; i++){
            if(a == nums[i]) b++;
            else{
                b--;
                if(b == 0){
                    a = nums[i];
                    b = 1;
                }
            }
        }
        return a;
    }
}
