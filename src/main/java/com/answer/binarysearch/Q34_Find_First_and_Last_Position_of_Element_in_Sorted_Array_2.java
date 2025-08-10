package com.answer.binarysearch;

public class Q34_Find_First_and_Last_Position_of_Element_in_Sorted_Array_2 {
    /**
     * Template III
     */
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        res[0] = leftSearch(nums, target);
        res[1] = rightSearch(nums, target);
        return res;
    }

    private int leftSearch(int[] nums, int target){
        if(nums == null || nums.length == 0) return -1;

        int l = 0, r = nums.length - 1;
        while(l <= r){
            int mid = l + (r - l) / 2;
            if(nums[mid] < target){
                l = mid + 1;
            }
            else{
                r = mid - 1;
            }
        }
     //   if(l < nums.length && nums[l] == target) return l; // can be commented
        if(r >= 0 && nums[r] == target) return r;

        return -1;
    }

    private int rightSearch(int[] nums, int target){
        if(nums == null || nums.length == 0) return -1;

        int l = 0, r = nums.length - 1;
        while(l <= r){
            int mid = l + (r - l) / 2;
            if(nums[mid] <= target){
                l = mid + 1;
            }
            else{
                r = mid - 1;
            }
        }
        if(r >= 0 && nums[r] == target) return r;
    //    if(l < nums.length && nums[l] == target) return l; // can be commented

        return -1;
    }
}
