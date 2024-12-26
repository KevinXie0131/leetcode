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

    public int[] sortedSquares_0(int[] nums) {
        int right = nums.length - 1;
        int left = 0;
        int[] result = new int[nums.length];
        int index = result.length - 1;
        while (left <= right) {
            if (nums[left] * nums[left] > nums[right] * nums[right]) {
                // 正数的相对位置是不变的， 需要调整的是负数平方后的相对位置
                result[index] = nums[left] * nums[left];
                left++;
            } else {
                result[index] = nums[right] * nums[right];
                right++;
            }
            index--;
        }
        return result;
    }
}
