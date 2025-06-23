package com.answer.binarysearch;

public class Q81_Search_in_Rotated_Sorted_Array_II {
    /**
     * 搜索旋转排序数组 II
     * There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).
     * 已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。
     * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，
     * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
     * 例如， [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。
     *
     * 给你 旋转后 的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。如果 nums 中存在这个目标值 target ，则返回 true ，否则返回 false 。
     * 你必须尽可能减少整个操作步骤。You must decrease the overall operation steps as much as possible.
     * 题目数据保证 nums 在预先未知的某个下标上进行了旋转 nums is guaranteed to be rotated at some pivot.
     *
     * 示例 1：
     *  输入：nums = [2,5,6,0,0,1,2], target = 0
     *  输出：true
     * 示例 2：
     *  输入：nums = [2,5,6,0,0,1,2], target = 3
     *  输出：false
     */
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
