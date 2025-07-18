package com.answer.binarysearch;

public class Q702_Search_in_a_Sorted_Array_of_Unknown_Size {
    /**
     * Given an integer array sorted in ascending order, write a function to search target in nums.
     * If target exists, then return its index, otherwise return -1. However, the array size is unknown to you.
     * You may only access the array using an ArrayReader interface, where ArrayReader.get(k) returns the element of the array at index k (0-indexed).
     *
     * You may assume all integers in the array are less than 10000, and if you access the array out of bounds, ArrayReader.get will return 2147483647.
     * 搜索长度未知的有序数组
     * 给定一个升序整数数组，写一个函数搜索 nums 中数字 target。如果 target 存在，返回它的下标，否则返回 -1。
     * 注意，这个数组的大小是未知的。你只可以通过 ArrayReader 接口访问这个数组，ArrayReader.get(k) 返回数组中第 k 个元素（下标从 0 开始）。
     *
     * 你可以认为数组中所有的整数都小于 10000。如果你访问数组越界，ArrayReader.get 会返回 2147483647。
     *
     * Example 1:
     *  Input: array = [-1,0,3,5,9,12], target = 9
     *  Output: 4
     *  Explanation: 9 exists in nums and its index is 4
     * Example 2:
     *  Input: array = [-1,0,3,5,9,12], target = 2
     *  Output: -1
     *  Explanation: 2 does not exist in nums so return -1
     *
     * Note: You may assume that all elements in the array are unique.
     *       The value of each element in the array will be in the range [-9999, 9999].
     */
    /**
     * 这个题目要求你在长度未知的有序数组中搜索目标值。你不能直接用数组长度，但可以用 ArrayReader.get(index) 来访问元素。如果越界则返回 2147483647。
     * 思路：二分查找法（Binary Search）。
     *       需要先“扩展边界”——确定一个大于等于 target 的右边界。
     *       然后在 [0, right] 区间二分查找。
     * Approach 1: Binary Search
     * 1. Define search limits, i.e. left and right boundaries for the search.
     * 2. Perform binary search in the defined boundaries.
     *
     * Left shift: x << 1. The same as multiplying by 2
     * Right shift: x >> 1. The same as dividing by 2
     */
    public int search(ArrayReader reader, int target) {
        if (reader.get(0) == target) return 0;
        // search boundaries
        int left = 0, right = 1;   // 1. 寻找右边界
        //先用指数级扩展法找到一个右边界。
        //然后普通二分查找。
        //访问越界会返回 2147483647，不影响查找逻辑。
        while (reader.get(right) < target) { // 扩展右边界直到大于等于 target 或者越界
            left = right; // Move the left boundary to the right
            right <<= 1; // Extend the right boundary: right = right * 2;
        }
        // 2. 二分查找
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int val = reader.get(mid);
            if (val == target) {
                return mid;
            } else if (val > target) {
                right = mid - 1;
            } else { // val < target
                left = mid + 1;
            }
        }
        return -1;
    }
    /**
     * Binary Search
     */
    public int search_1(ArrayReader reader, int target) {
        int left = 0, right = 20000;
        while (left <= right) {
            int mid = (left + right) >> 1;
            int midVal = reader.get(mid);
            if (midVal > target) { // 越界或者非越界情况下，继续二分查找左半部分
                right = mid - 1;
            } else if (midVal == target) { // 相等返回
                return mid;
            } else { // 当前值大于目标值，继续二分查找右半部分
                left = mid + 1;
            }
        }
        return -1;
    }
}

class ArrayReader {
   public int get(int index) {
       return -1;
   }
}
