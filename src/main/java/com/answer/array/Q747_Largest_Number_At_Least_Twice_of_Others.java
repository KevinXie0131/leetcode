package com.answer.array;

public class Q747_Largest_Number_At_Least_Twice_of_Others {
    /**
     * 至少是其他数字两倍的最大数: 给你一个整数数组 nums ，其中总是存在 唯一的 一个最大整数 。
     * 请你找出数组中的最大元素并检查它是否 至少是数组中每个其他数字的两倍 。如果是，则返回 最大元素的下标 ，否则返回 -1 。
     * 示例 1：
     *  输入：nums = [3,6,1,0]
     *  输出：1
     *  解释：6 是最大的整数，对于数组中的其他整数，6 至少是数组中其他元素的两倍。6 的下标是 1 ，所以返回 1 。
     */
    /**
     * 遍历数组分别找到数组的最大值 m1 和次大值 m2
     * 为了返回最大值的下标，我们需要在计算最大值的同时记录最大值的下标。
     */
    public int dominantIndex(int[] nums) {
        int biggest = -1;
        int secondBiggest = -1;
        int index = -1;

        for(int i = 0; i < nums.length; i++){
            if(nums[i] > biggest){
                secondBiggest = biggest;
                biggest = nums[i];
                index = i;
            }else if(nums[i] > secondBiggest){
                secondBiggest = nums[i];
            }
        }
        return biggest >= secondBiggest * 2 ? index : -1;
    }
}
