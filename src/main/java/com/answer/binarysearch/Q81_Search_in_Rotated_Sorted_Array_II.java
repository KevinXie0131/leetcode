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
    public static void main(String[] args) {
        int[] nums = {1,0,1,1,1};
        int target = 0;
        System.out.println(search0(nums, target));
    }
    /**
     * Refer to Q33_Search_in_Rotated_Sorted_Array
     * 1. 首先明白，旋转数组后，从中间划分，一定有一边是有序的。
     * 2. 由于一定有一边是有序的，所以根据有序的两个边界值来判断目标值在有序一边还是无序一边
     * 3. 这题找目标值，遇到目标值即返回
     * 4. 注意：由于有序的一边的边界值可能等于目标值，所以判断目标值是否在有序的那边时应该加个等号
     * (在二分查找某个具体值得时候如果把握不好边界值，可以再每次查找前判断下边界值，也就是while循环里面的两个if注释)
     */
    static public boolean search0(int[] nums, int target) {
        int n = nums.length;
        int end = nums[n - 1];
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 分不清是否有序，所以从两端开始去除重复值即可
            // 注意这里不能用while， 原因有二，一是用while，如果全是一样的数字时间复杂度退化为o(n)了。
            // 二是如果一直left++，right--，最终会影响mid停留的位置与二分法计算的不符（这里需里自己调试理解）,
            // 导致下面的二分查找法失效，结果出错！所以必须，每移动一次就跳出循环，重新计算mid的值。
            if (nums[mid] == target) {
                return true;
            }
            // 这道题包含了重复元素其实影响到的是，当左端点和右端点相等时，无法判断mid在左半边有序数组还是右半边有序数组，
            // 所以只需要一直pop直到左端点和右端点不相等就可以了
            if (nums[left] == nums[mid]) {
                left++; // 导致时间复杂度为 O(n)
                if(left > n - 1) return false;
                continue;
            }
            if (nums[right] == nums[mid]) {
                right--;
                continue;
            }

            if (target <= end && nums[mid] > end) {
                // 目标在右半部分，mid在左半部分
                left = mid + 1;
            } else if (target > end && nums[mid] <= end) {
                // 目标在左半部分，mid在右半部分
                right = mid - 1;
            } else {
                // 目标和mid在同一部分
                if (nums[mid] < target) {
                    left = mid + 1;
                } else if(nums[mid] > target){
                    right = mid - 1;
                } else {
                    return true;
                }
            }
        }
        return nums[left] != target ? false : true;
    }
    /**
     * 对于数组中有重复元素的情况，二分查找时可能会有 a[l]=a[mid]=a[r]，此时无法判断区间 [l,mid] 和区间 [mid+1,r] 哪个是有序的。
     * 例如 nums=[3,1,2,3,3,3,3]，target=2，首次二分时无法判断区间 [0,3] 和区间 [4,6] 哪个是有序的。
     * 对于这种情况，我们只能将当前二分区间的左边界加一，右边界减一，然后在新区间上继续二分查找。
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
            if (nums[mid] == target) {  // 找到
                return true;
            }
            if (nums[l] == nums[mid] && nums[mid] == nums[r]) { // remove elements with the same value
                ++l;   // 处理重复元素，必须提前判断，否则下面必须都是<
                --r;
            } else if (nums[l] <= nums[mid]) {  // 左端点小于中点，说明左半部分完全有序
                if (nums[l] <= target && target < nums[mid]) { // 确定 target 在哪个区间，重新设置左右指针
                    r = mid - 1; // target位于左半部分，到左半部分二分查找
                } else {
                    l = mid + 1; // 否则为右半
                }
            } else {   // 中点小于右端点，说明右半部分完全有序
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;     // target位于右半部分，到右半部分二分查找
                } else {
                    r = mid - 1;  // 否则为左半
                }
            }
        }
        return false;
    }
}
