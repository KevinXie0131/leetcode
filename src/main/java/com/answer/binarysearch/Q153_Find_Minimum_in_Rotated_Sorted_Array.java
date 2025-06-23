package com.answer.binarysearch;

public class Q153_Find_Minimum_in_Rotated_Sorted_Array {
    /**
     * 寻找旋转排序数组中的最小值
     * Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,2,4,5,6,7] might become:
     *  [4,5,6,7,0,1,2] if it was rotated 4 times.
     *  [0,1,2,4,5,6,7] if it was rotated 7 times.
     * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
     * Given the sorted rotated array nums of unique elements, return the minimum element of this array.
     * You must write an algorithm that runs in O(log n) time.
     *
     * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
     *  若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
     *  若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
     * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
     * 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
     * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
     *
     * 示例 1：
     *  输入：nums = [3,4,5,1,2]
     *  输出：1
     *  解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
     * All the integers of nums are unique. nums 中的所有整数 互不相同
     * nums is sorted and rotated between 1 and n times.nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
     */
    /**
     * 二分查找
     * 考虑数组中的最后一个元素 x：在最小值右侧的元素（不包括最后一个元素本身），它们的值一定都严格小于 x；而在最小值左侧的元素，
     * 它们的值一定都严格大于 x。因此，我们可以根据这一条性质，通过二分查找的方法找出最小值
     *
     * 第一种情况是 nums[pivot]<nums[high]。这说明 nums[pivot] 是最小值右侧的元素，因此我们可以忽略二分查找区间的右半部分。
     * 第二种情况是 nums[pivot]>nums[high]。这说明 nums[pivot] 是最小值左侧的元素，因此我们可以忽略二分查找区间的左半部分。
     *
     * 由于数组不包含重复元素，并且只要当前的区间长度不为 1，pivot 就不会与 high 重合；
     * 而如果当前的区间长度为 1，这说明我们已经可以结束二分查找了。因此不会存在 nums[pivot]=nums[high] 的情况。
     */
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < nums[high]) { // 如果中间数值相遇最右数值，则有可能是
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return nums[low];
    }
    /**
     *
     */
    public int findMin_1(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }
}
