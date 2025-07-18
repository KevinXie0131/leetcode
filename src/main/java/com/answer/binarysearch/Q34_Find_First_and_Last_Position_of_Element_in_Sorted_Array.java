package com.answer.binarysearch;

import java.util.Arrays;

public class Q34_Find_First_and_Last_Position_of_Element_in_Sorted_Array {
    /**
     * 在排序数组中查找元素的第一个和最后一个位置
     * Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
     * If target is not found in the array, return [-1, -1].
     * You must write an algorithm with O(log n) runtime complexity.
     * 一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
     * 如果数组中不存在目标值 target，返回 [-1, -1]。
     * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
     */
    public static void main(String[] args) {
/*        int[] nums = {1, 2, 2};
        int target = 2;*/
        int[] nums = {5,7,7,8,8,10};
        int target = 8;
        System.out.println(Arrays.toString(searchRange_7(nums, target))); // 输出：[3,4]
    }
    /**
     * refer to Q704_Binary_Search
     */
    public int[] searchRange_0(int[] nums, int target) {
        int[] result = new int[2];
        int left = 0, right = nums.length - 1; // 闭区间 [left, right]
        while (left <= right) { // 寻找左侧边界的二分搜索
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] >= target) {
                right = mid - 1;
            }
        }
       // result[0] = left < nums.length && nums[left] == target ? left : -1;
        result[0] = left;

        left = 0;
        right = nums.length - 1;
        while (left <= right) { // 寻找右侧边界的二分查找
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
       // result[1] = right >= 0 && nums[right] == target ? right : -1;
        result[1] = right;
        return result;
    }
    /**
     * 先找到这个数的右边相邻数字，也就是 >target 的第一个数。在所有数都是整数的前提下，>target 等价于 ≥target+1，
     * 这样就可以复用我们已经写好的二分函数了，即 lowerBound(nums, target + 1)，算出这个数的下标后，将其减一，就得到 ≤target 的最后一个数的下标。
     */
    public int[] searchRange_8(int[] nums, int target) {
        int start = lowerBound(nums, target);
        if (start == nums.length || nums[start] != target) {
            return new int[]{-1, -1}; // nums 中没有 target
        }
        // 如果 start 存在，那么 end 必定存在
        int end = lowerBound(nums, target + 1) - 1;
        return new int[]{start, end};
    }

    // lowerBound 返回最小的满足 nums[i] >= target 的下标 i
    // 如果数组为空，或者所有数都 < target，则返回 nums.length
    // 要求 nums 是非递减的，即 nums[i] <= nums[i + 1]
    private int lowerBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1; // 闭区间 [left, right]
        while (left <= right) { // 区间不为空
            // 循环不变量：
            // nums[left-1] < target
            // nums[right+1] >= target
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) { // 只需在target <= nums[mid] 时，让 right = mid - 1即可，这样我们就可以继续在 mid 的左区间继续找
                right = mid - 1; // 范围缩小到 [left, mid-1]
            } else {
                left = mid + 1; // 范围缩小到 [mid+1, right]
            }
        }
        // 循环结束后 left = right+1
        // 此时 nums[left-1] < target 而 nums[left] = nums[right+1] >= target
        // 所以 left 就是第一个 >= target 的元素下标
        return left;
    }
    /**
     * From 睡不醒的鲤鱼
     * 求左右边界的二分查找。
     * 为什么计算 mid 时需要 + 1？
     * 当 l = r - 1 时，mid = l + r >> 1 = l，若更新语句为 l = mid，则区间未变化，会导致死循环，因此需要 + 1 操作。
     */
    static public int[] searchRange_7(int[] nums, int target) {
        int[] ans = new int[2];
        // 找到左端点（第一个 >= target 的元素下标）
        // 左侧：nums[i] < target
        // 右侧：nums[i] >= target
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] >= target) r = mid;
            else l = mid + 1;
        }
        if (nums[l] != target) return new int[]{-1, -1};
        ans[0] = l;
        // 找到右端点（最后一个 >= target 的元素下标）
        // 左侧：nums[i] <= target
        // 右侧：nums[i] > target
        l = 0; r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2 + 1;
            if (nums[mid] <= target) l = mid;
            else r = mid - 1;
        }
        ans[1] = r;
        return ans;
    }
    /**
     * 解法2
     *  1、首先，在 nums 数组中二分查找 target；
     *  2、如果二分查找失败，则 binarySearch 返回 -1，表明 nums 中没有 target。此时，searchRange 直接返回 {-1, -1}；
     *  3、如果二分查找成功，则 binarySearch 返回 nums 中值为 target 的一个下标。然后，通过左右滑动指针，
     *     来找到符合题意的区间
     */
    static public int[] searchRange(int[] nums, int target) {
        int index = binarySearch(nums, target); // 二分查找

        if (index == -1) { // nums 中不存在 target，直接返回 {-1, -1}
            return new int[] {-1, -1}; // 匿名数组
        }
        // nums 中存在 target，则左右滑动指针，来找到符合题意的区间
        int left = index;
        int right = index;
        // 向左滑动，找左边界
        while (left - 1 >= 0 && nums[left - 1] == nums[index]) { // 防止数组越界。逻辑短路，两个条件顺序不能换
            left--;
        }
        // 向右滑动，找右边界
        while (right + 1 < nums.length && nums[right + 1] == nums[index]) { // 防止数组越界。
            right++;
        }
        return new int[] {left, right};
    }
    // 二分查找
    static public int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1; // 不变量：左闭右闭区间

        while (left <= right) { // 不变量：左闭右闭区间
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1; // 不变量：左闭右闭区间
            }
        }
        return -1; // 不存在
    }
    /**
     * 解题思路同上
     */
    public static int[] searchRange_3(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = (right - left) / 2 + left;
            if (target == nums[mid]) {
                int l = mid, r = mid;
                while (l >= 0 && target == nums[l]) {
                    l--;
                }
                while (r <= nums.length - 1 && target == nums[r]) {
                    r++;
                }
                return new int[]{++l, --r};
            } else if (target < nums[mid]) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (target == nums[left] && target == nums[right]) return new int[]{left, right};
        if (target == nums[left]) return new int[]{left, left};
        if (target == nums[right]) return new int[]{right, right};
        return new int[]{-1, -1};
    }
    /**
     * 解法三
     */
    public int[] searchRange3(int[] nums, int target) {
        int left = searchLeft(nums,target);
        int right = searchRight(nums,target);
        return new int[]{left,right};
    }

    public int searchLeft(int[] nums,int target){
        // 寻找元素第一次出现的地方
        int left = 0;
        int right = nums.length-1;
        while(left<=right){
            int mid = left+(right-left)/2;
            // >= 的都要缩小 因为要找第一个元素
            if(nums[mid]>=target){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        // right = left - 1
        // 如果存在答案 right是首选
        if(right>=0&&right<nums.length&&nums[right]==target){
            return right;
        }
        if(left>=0&&left<nums.length&&nums[left]==target){
            return left;
        }
        return -1;
    }

    public int searchRight(int[] nums,int target){
        // 找最后一次出现
        int left = 0;
        int right = nums.length-1;
        while(left<=right){
            int mid = left + (right-left)/2;
            // <= 的都要更新 因为我们要找最后一个元素
            if(nums[mid]<=target){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        // left = right + 1
        // 要找最后一次出现 如果有答案 优先找left
        if(left>=0&&left<nums.length&&nums[left]==target){
            return left;
        }
        if(right>=0&&right<=nums.length&&nums[right]==target){
            return right;
        }
        return -1;
    }
}
