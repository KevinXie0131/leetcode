package com.answer.binarysearch;

public class Q153_Find_Minimum_in_Rotated_Sorted_Array {
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
