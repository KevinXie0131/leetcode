package com.answer.binarysearch;

public class Q35_Search_Insert_Position {
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
}
