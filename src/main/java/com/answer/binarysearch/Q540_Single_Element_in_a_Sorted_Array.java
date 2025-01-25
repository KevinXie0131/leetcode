package com.answer.binarysearch;

public class Q540_Single_Element_in_a_Sorted_Array {
    public static void main(String[] args) {
       int[] nums = {1,1,2,3,3,4,4,8,8};
       int result = singleNonDuplicate(nums);
       System.out.println(result);
    }

    static public int singleNonDuplicate(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] == nums[mid - 1]) {
                right = mid  ;
            } else {
                left = mid;
            }
        }
        return nums[left - 1];
    }
}
