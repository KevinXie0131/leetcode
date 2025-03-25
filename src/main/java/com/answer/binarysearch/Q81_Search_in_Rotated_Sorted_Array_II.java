package com.answer.binarysearch;

public class Q81_Search_in_Rotated_Sorted_Array_II {
    /**
     * 已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了旋转
     * Refer to Q33. Search in Rotated Sorted Array
     * 这是 Q33. 搜索旋转排序数组 的延伸题目，本题中的 nums 可能包含重复元素。
     * 只需在搜索前做一下预处理，把最右侧与数组第一个数字相同的数字删去，避免nums[l]=nums[mid]=nums[r]，从而正常进行二分找到旋转点。
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
            int mid = (l + r) / 2;  // 二分找到数组旋转点
            if (nums[mid] == target) {
                return true;
            }
            if (nums[l] == nums[mid] && nums[mid] == nums[r]) { // remove elements with the same value
                ++l;
                --r;
            } else if (nums[l] <= nums[mid]) {
                if (nums[l] <= target && target < nums[mid]) { // 确定 target 在哪个区间，重新设置左右指针
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
