package com.answer.array;

public class Q747_Largest_Number_At_Least_Twice_of_Others {

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
