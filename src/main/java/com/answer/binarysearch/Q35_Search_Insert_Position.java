package com.answer.binarysearch;

import java.util.Arrays;

public class Q35_Search_Insert_Position {
    /**
     * 搜索插入位置
     * Given a sorted array of distinct integers and a target value, return the index if the target is found.
     * If not, return the index where it would be if it were inserted in order.
     * You must write an algorithm with O(log n) runtime complexity.
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * 请必须使用时间复杂度为 O(log n) 的算法。
     * 示例 1:
     *  输入: nums = [1,3,5,6], target = 5
     *  输出: 2
     * nums 为 无重复元素 的 升序 排列数组 / nums contains distinct values sorted in ascending order.
     */
    /**
     * refer to Q704_Binary_Search
     * 在一个有序数组中找第一个大于等于 target 的下标: 寻找左侧边界的二分搜索
     */
    public int searchInsert_0(int[] nums, int target) {
        int left = 0, right = nums.length - 1; // 闭区间 [left, right]
        while (left <= right) { // 区间不为空
            // 循环不变量：
            // nums[left-1] < target
            // nums[right+1] >= target
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1; // 范围缩小到 [mid + 1, right]
            } else if (nums[mid] > target) {
                right = mid - 1; // 范围缩小到 [left, mid - 1]
            } else if (nums[mid] == target) {
                // 别返回，锁定左侧边界
                right = mid - 1;  // 所以当 nums[mid] == target 时不要立即返回, 而要收紧右侧边界以锁定左侧边界
            }
        }
        return left;
        // 为什么直接return left；因为如果上面的没有返回return middle，说明最后一定是，left>right从而跳出循环的，
        // 在此之前是left=right，如果最后是right-1导致的left>right，说明原来的right位置是大于target的，
        // 所以返回原来的right位置即left位置；如果最后是left+1导致的left>right,说明是原来的的left=right这个位置小于target，
        // 而right能移动到这个位置，说明此位置右侧是大于target的，left现在加1就移动到了这样的位置，返回left即可
    }
    /**
     * 库函数写法
     * 注意：只能在没有重复元素的时候使用
     * 如果 nums 有多个值为 target 的数，返回值不一定是第一个 >= target 的数的下标
     */
    public int searchInsert_9(int[] nums, int target) {
        int i = Arrays.binarySearch(nums, target);
        return i >= 0 ? i : ~i; // ~i = -i-1
    }
    /**
     * 暴力解法 时间复杂度：O(n) 空间复杂度：O(1)
     */
    public int searchInsert0(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            // 分别处理如下四种情况
            // 1 目标值在数组所有元素之前
            // 2 目标值等于数组中某一个元素
            // 3 目标值插入数组中的位置
            if (nums[i] >= target) { // 一旦发现大于或者等于target的num[i]，那么i就是我们要的结果
                return i;
            }
        }
        // 4 目标值在数组所有元素之后的情况
        return nums.length; // 如果target是最大的，或者 nums为空，则返回nums的长度
    }
    /**
     * 有序数组sorted array，这是使用二分查找的基础条件. 时间复杂度：O(log n) 空间复杂度：O(1)
     * 第一种二分法：左闭右闭
     */
    public int searchInsert(int[] nums, int target) {
        int n = nums.length;
        // 定义target在左闭右闭的区间，[left, right]
        int left = 0;
        int right = n - 1;

        while (left <= right) { // left==right，区间[left, right]依然有效
            int mid = (left + right) >>> 1; // 防止溢出
            if (nums[mid] > target) {
                right = mid - 1; // target 在左区间，所以[left, mid - 1]
            } else if (nums[mid] < target) {
                left = mid + 1; // target 在右区间，所以[mid + 1, right]
            } else {
                // 1. 目标值等于数组中某一个元素  return mid;
                return mid;
            }
        }
        // 分别处理如下四种情况
        // 目标值在数组所有元素之前  [0, -1]
        // 目标值等于数组中某一个元素  return middle;
        // 目标值插入数组中的位置 [left, right]，return  right + 1
        // 目标值在数组所有元素之后的情况 [left, right]， 因为是右闭区间，所以 return right + 1
        return right + 1; //必须要有+1. nums = [1,3,5,6] target = 2
        // return left 也可以工作
        // return left;
        //return (left < nums.length && nums[left] < target) ? left + 1 : left; // 这个也可以，可以保证输出
    }
    /**
     * 第二种二分法：左闭右开
     */
    public int searchInsert1(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) { //左闭右开 [left, right)
            int middle = left + ((right - left) >> 1);
            if (nums[middle] > target) {
                right = middle; // target 在左区间，在[left, middle)中
            } else if (nums[middle] < target) {
                left = middle + 1; // target 在右区间，在 [middle+1, right)中
            } else { // nums[middle] == target
                return middle; // 数组中找到目标值的情况，直接返回下标
            }
        }
        // 目标值在数组所有元素之前 [0,0)
        // 目标值等于数组中某一个元素 return middle
        // 目标值插入数组中的位置 [left, right) ，return right 即可
        // 目标值在数组所有元素之后的情况 [left, right)，因为是右开区间，所以 return right
        return right;
        // return left; // 也可以工作
    }
    /**
     * From 睡不醒的鲤鱼
     * 基础二分查找
     * 另一种形式
     */
    public int searchInsert4(int[] nums, int target) {
        // 找到第一个 >= target 的元素下标
        // 左侧：nums[i] < target
        // 右侧：nums[i] >= target
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] >= target) r = mid;
            else l = mid + 1;
        }
        return l;
        //  return r;  // 也可以
    }
}
