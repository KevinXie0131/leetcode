package com.answer.math;

import java.util.*;

public class Q169_Majority_Element {

    public int majorityElement(int[] nums) {
        Arrays.sort(nums); // 需要排序

        return nums[nums.length/2];
    }
    /**
     * Divide and conquer 分治法
     * 其实这个题目考的就是分治
     * 分治的数学基础：把数据裁成两部分，那一定至少会在一部分里是存在这个众数的，递归下去拆解，直至只有一个元素，不可分割，那他就是它那部分的众数，
     * 递归回来，合并两部分，由于每一部分都得出了各自的众数，那就比较两部分中众数的数量，数量多的才算这两部分构成的元素里面的众数。
     */
    public int majorityElement_0(int[] nums) {
        return findMajorElment(nums,0,nums.length-1);
    }
    public int findMajorElment(int[] nums,int low,int high){
        if(low == high){ // base case; the only element in an array of size 1 is the majority element.
            return nums[low];
        }
        int mid = (high-low)/2+low; // recurse on left and right halves of this slice.
        int left =  findMajorElment(nums,low,mid);
        int right = findMajorElment(nums,mid+1,high);

        if(left==right){ // if the two halves agree on the majority element, return it.
            return left;
        }
        // otherwise, count each element and return the "winner".
        int leftCount=findMajorElentCount(nums,left,low,mid);
        int rightCount=findMajorElentCount(nums,right,mid+1,high);
        return (leftCount>rightCount)?left:right;
    }
    public int findMajorElentCount(int[] nums,int target,int low,int high){
        int count=0;
        for(int i=low;i<=high;i++){
            if(target==nums[i]){
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

        long limit = nums.length >> 1;

        for (Map.Entry<Integer, Long> entry : map.entrySet()){
            if (entry.getValue() > limit){
                return entry.getKey();
            }
        }
        return -1;
    }
    /**
     * Boyer-Moore Voting Algorithm
     */
    public int majorityElement_2(int[] nums) {
        int a=nums[0],b=1;
        for(int i=1;i<nums.length;i++){
            if(a==nums[i])b++;
            else{
                b--;
                if(b==0){
                    a=nums[i];
                    b=1;
                }
            }
        }
        return a;
    }
}
