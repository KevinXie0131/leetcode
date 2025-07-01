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
     * 旋转后的数组一定被分成了前后两部分且两半都是升序数组，且前一半的最小值一定大于后一半的最大值，
     * 只要用二分找到后一半的第一个元素即可
     */
    public int findMin0(int[] nums) {
        // 1.min初始值为第一段升序数组的最小值，而且目前不知道数组是有两段升序还是只有一段升序
        int min = nums[0];
        // 2.先正常二分查找
        int left = 0, right = nums.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            // 3.如果中间位置比min小，那么这个mid位置一定在第二段升序数组中，那么最小值一定在mid或者它的左边，
            // 这是因为每段都是升序的
            if(nums[mid] < min){
                min = nums[mid];    // 先更新min，然后向左边遍历
                right = mid - 1;
            }
            // 4. 如果中间位置比min大，那么mid位置一定在第一段升序部分，因为第二段升序的最大值也要小于第一段升序的最小值
            // 所以直接向mid的右边遍历
            else {
                left = mid + 1;
            }
        }
        return min;
    }
    /**
     * 旋转排序数组的特性：最多两段数组有序，最少一段数组有序[相当于数组平移]
     * 数组最后一位数的特性：一定 >=最小值，最小值一定在该数及左边的范围内
     * 若有旋转[平移]，则前段数组的最小值一定 > 后段数组的最大值
     */
    public int findMin2(int[] nums) {
        int n = nums.length;
        int left = 0, right = n - 1;
        // 二分的是位置，依据是当前位置的值与最后一位值的大小
        // 注：数组内数值互不相同
        while (left <= right) {
            int m = left + (right - left) / 2;
            // 若当前位置数小等于[等于处理单个数]末位，则在后段数组，应左移[染蓝色]
            if (nums[m] <= nums[n - 1])
                right = m - 1;
            else // 否则是在前段数组，需右移[染红色]
                left = m + 1;
        }
        return nums[left];
    }
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
     * 同上
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
