package com.answer.binarysearch;

public class Q704_Binary_Search {

    public static void main(String[] args) {
       int[] nums = {-1,0,3,5,9,12};
       int target = 9;

      int result =  search0(nums, target);
      System.out.println(result);
    }
    public static int search0(int[] nums, int target) {
        if (target < nums[0] || target > nums[nums.length - 1]) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = left + ((right- left) >> 1);

            if(target < nums[mid]){
                right = mid - 1;
            }else if(target > nums[mid]){
                left = mid +1;
            }else{
                return mid;
            }

        }
        return -1;
    }

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while(left <= right){
            int mid = (left + right) >>> 1;

            if(nums[mid] == target){
                return mid;
            } else if(nums[mid] < target){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;

    }

    /**
     *
     */
    public int search_1(int[] nums, int target) {
        int left = 0;
        int right = nums.length;

        while(left < right){
            int mid = (left + right) >>> 1;

            if(nums[mid] == target){
                return mid;
            } else if(nums[mid] < target){
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return -1;

    }
    /**
     *
     */
    public int search_2(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        int result = recursion(nums, target, left, right);
        return result;

    }

    public int recursion(int[] nums, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) >>> 1;
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] < target) {
            return recursion(nums, target, mid + 1, right);
        } else {
            return recursion(nums, target, left, mid - 1);
        }
    }

    public int recursion1 (int[] nums, int target, int left, int right){
        if(left > right){
            return -1;
        }

        int mid = left + ((right- left) >> 1);

        if(target < nums[mid]){
            return recursion(nums, target, left, mid - 1);
        }else if(target > nums[mid]){
            return recursion(nums, target, mid + 1, right);
        }else{
            return mid;
        }
    }
}
