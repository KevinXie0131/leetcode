package com.answer.array;

public class Q747_Largest_Number_At_Least_Twice_of_Others {
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
