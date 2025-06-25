package com.answer.binarysearch;

public class Q704_Binary_Search {
    /**
     * 二分查找
     * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果 target 存在返回下标，否则返回 -1。
     * Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to search target in nums. If target exists, then return its index. Otherwise, return -1.
     * 你必须编写一个具有 O(log n) 时间复杂度的算法。
     * You must write an algorithm with O(log n) runtime complexity.
     *
     * 示例 1:
     *  输入: nums = [-1,0,3,5,9,12], target = 9
     *  输出: 4
     *  解释: 9 出现在 nums 中并且下标为 4 / 9 exists in nums and its index is 4
     * 示例 2:
     *  输入: nums = [-1,0,3,5,9,12], target = 2
     *  输出: -1
     *  解释: 2 不存在 nums 中因此返回 -1 / 2 does not exist in nums so return -1
     *
     * 提示：你可以假设 nums 中的所有元素是不重复的。
     *      n 将在 [1, 10000]之间。
     *      nums 的每个元素都将在 [-9999, 9999]之间。
     *      All the integers in nums are unique.
     *      nums is sorted in ascending order.
     */
    public static void main(String[] args) {
       int[] nums = {-1,0,3,5,9,9,9,9,9};
       int target = -10;
       int result = higherBound(nums, target);
       System.out.println(result);
    }
    /**
     * 左闭右闭
     * 寻找一个数（基本的二分搜索）
     */
    public static int search0(int[] nums, int target) {
        if (target < nums[0] || target > nums[nums.length - 1]) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;// 左闭右闭
        while(left <= right){ // 左闭右闭
            int mid = left + ((right- left) >> 1);

            if(target < nums[mid]){
                right = mid - 1; // 左闭右闭
            }else if(target > nums[mid]){
                left = mid +1;
            }else{
                return mid;   // 直接返回
            }
        }
        return -1;
    }
    /**
     * another form 左闭右闭
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1; // 左闭右闭

        while(left <= right){  // 左闭右闭
            int mid = (left + right) >>> 1;

            if(nums[mid] == target){
                return mid;
            } else if(nums[mid] < target){
                left = mid + 1;
            } else {
                right = mid - 1; // 左闭右闭
            }
        }
        return -1;
    }
    /**
     * 寻找左侧边界的二分搜索: lowerBound 返回最小的满足 nums[i] >= target 的 i
     * 如果数组为空，或者所有数都 < target，则返回 nums.length
     * 要求 nums 是非递减的，即 nums[i] <= nums[i + 1]
     * 闭区间写法
     */
    static private int lowerBound(int[] nums, int target) { // 返回第一个等于 target 的数的下标
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
        return left; // 或者 right + 1
    }
    /**
     * based on the above template
     */
    public int search_a(int[] nums, int target) {
        int left = 0, right = nums.length - 1; // 闭区间 [left, right]
        while (left <= right) { // 区间不为空
            // 循环不变量：
            // nums[left-1] < target
            // nums[right+1] >= target
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1; // 范围缩小到 [mid + 1, right]
            } else {
                right = mid - 1; // 范围缩小到 [left, mid - 1]
            }
        }
        // 由于 while 的退出条件是 left == right + 1，所以当 target 比 nums 中所有元素都大时，会存在以下情况使得索引越界
        return left < nums.length && nums[left] == target? left : -1;
        // 或者 right + 1
        // return right + 1 < nums.length && nums[right + 1] == target? right + 1:-1;
    }
    /**
     * 寻找右侧边界的二分查找
     */
    static private int higherBound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 别返回，锁定右侧边界
                left = mid + 1;    // 所以当 nums[mid] == target 时不要立即返回, 而要收紧左侧边界以锁定右侧边界
            }
        }
        // 这里改为检查 right 越界的情况，见下图
        return right; // 或者 left - 1
    }
    /**
     * based on the above template
     */
    public int search_b(int[] nums, int target) {
        int left = 0, right = nums.length - 1; // 闭区间 [left, right]
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        // 由于 while 的退出条件是 left == right + 1，所以当 target 比 nums 中所有元素都大时，会存在以下情况使得索引越界
        // return left - 1 >=0 && nums[left - 1] == target? left - 1 : -1;
        // 或者 right
         return right >= 0 && nums[right] == target? right : -1;
    }
    /**
     * 左闭右开
     */
    public int search_1(int[] nums, int target) {
        int left = 0;
        int right = nums.length; // 左闭右开

        while(left < right){  // 左闭右开
            int mid = (left + right) >>> 1;

            if(nums[mid] == target){
                return mid;
            } else if(nums[mid] < target){
                left = mid + 1;
            } else {
                right = mid; // 左闭右开
            }
        }
        return -1;
    }
    /**
     * 递归
     */
    public int search_2(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;  // 左闭右闭

        int result = recursion(nums, target, left, right);
        return result;
    }

    public int recursion(int[] nums, int target, int left, int right) {
        if (left > right) {  // 左闭右闭
            return -1;
        }
        int mid = (left + right) >>> 1;

        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] < target) {
            return recursion(nums, target, mid + 1, right);
        } else {
            return recursion(nums, target, left, mid - 1);  // 左闭右闭
        }
    }
    // 递归
    public int recursion1 (int[] nums, int target, int left, int right){
        if(left > right){
            return -1;
        }
        int mid = left + ((right- left) >> 1); //  int mid = left + (right - left) / 2;

        if(target < nums[mid]){
            return recursion1(nums, target, left, mid - 1);
        }else if(target > nums[mid]){
            return recursion1(nums, target, mid + 1, right);
        }else{
            return mid;
        }
    }
}
