package com.answer.binarysearch;

public class Q81_Search_in_Rotated_Sorted_Array_II {
    /**
     * 已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了旋转
     *  Refer to Q33. Search in Rotated Sorted Array
     */
    public boolean search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return false;
        }
        if (n == 1) {
            return nums[0] == target;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[l] == nums[mid] && nums[mid] == nums[r]) { // remove elements with the same value
                ++l;
                --r;
            } else if (nums[l] <= nums[mid]) {
                if (nums[l] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return false;

    }

}
