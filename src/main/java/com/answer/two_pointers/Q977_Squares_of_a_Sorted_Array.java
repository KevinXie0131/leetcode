package com.answer.two_pointers;

public class Q977_Squares_of_a_Sorted_Array {

    public int[] sortedSquares(int[] nums) {
        int len = nums.length;
        int[] result = new int[len];

        for(int left = 0, right = len-1, index = len-1; left <= right;){
            if(nums[left] * nums[left] < nums[right] * nums[right]){
                result[index] = nums[right] * nums[right];
                right--;
            } else{
                result[index] = nums[left] * nums[left];
                left++;
            }
            index--;
        }

        return result;
    }
}
