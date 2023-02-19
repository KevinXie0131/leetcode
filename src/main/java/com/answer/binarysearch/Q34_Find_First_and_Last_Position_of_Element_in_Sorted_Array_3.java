package com.answer.binarysearch;

public class Q34_Find_First_and_Last_Position_of_Element_in_Sorted_Array_3 {
    /**
     * Template II
     */
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        res[0] = leftSearch(nums, target);
        res[1] = rightSearch(nums, target);
        return res;
    }

    private int leftSearch(int[] nums, int target){
        if(nums == null || nums.length == 0) return -1;

        int l = 0,r = nums.length-1;
        while(l < r){
            int mid = l + (r - l) / 2;
            if(nums[mid] < target){
                l = mid + 1;
            }
            else{
                r = mid;
            }
        }

        return nums[l] == target ? l : -1;
    }

    private int rightSearch(int[] nums, int target){
        if(nums == null || nums.length == 0) return -1;

        int l = 0,r = nums.length - 1;
        while(l < r){
            //由于"/"是下取整，l可能不变，所以括号里加1
            int mid = l + (r - l + 1)/2;
            if(nums[mid] > target){
                r = mid - 1;
            }
            else{
                l = mid;
            }
        }

        return nums[l] == target ? l : -1;
    }
}
