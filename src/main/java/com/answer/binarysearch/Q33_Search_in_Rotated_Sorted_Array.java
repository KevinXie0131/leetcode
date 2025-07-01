package com.answer.binarysearch;

public class Q33_Search_in_Rotated_Sorted_Array {
    /**
     * 搜索旋转排序数组
     * There is an integer array nums sorted in ascending order (with distinct values).
     * 整数数组 nums 按升序排列，数组中的值 互不相同 。
     * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length)
     * such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
     * For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
     * Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
     * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，
     * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数)）。
     * 例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
     *
     * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
     * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
     */
    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2}; int target = 0; // 输出：4
        int res = search_0(nums, target);
        System.out.println(res);
    }
    /**
     * Binary search 二分查找
     * 对于有序数组，可以使用二分查找的方法查找元素。
     * 但是这道题中，数组本身不是有序的，进行旋转后只保证了数组的局部是有序的，这还能进行二分查找吗？答案是可以的。
     * 本题核心在于：对于任意一个index，其左区间和右区间至少有一个是有序的，那么就可以根据这个区间的最大值和最小值来判断Target是否在该区间内
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        int mid;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            //前半部分有序,注意此处用小于等于
            if (nums[start] <= nums[mid]) {  // start 到 mid 是顺序区间
                // target在前半部分
                // 如果target在左区间，则在左区间二分
                // 如果target在右区间(乱序区间)，start=mid+1，则能转移到右区间
                if (nums[start] <= target && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {                 // mid 到 end 是顺序区间
                if ( nums[mid] < target && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return -1;
    }
    /**
     * 简单的一次二分，但是没有合并相同的分支，易于理解
     *
     * 根据mid来切分成左右数组
     * 分别对左右数组处理
     *
     * 对左数组
     *  target在范围里 right = mid-1
     *  不在范围里,说明在右数组 left = mid+1;
     * 对右数组
     *  在范围里，left = mid+1;
     *  不在范围,说明在左数组 right = mid-1
     */
    public int search2(int[] nums, int target) {
        int n = nums.length;
        int end = nums[n - 1];
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
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
                    return mid;
                }
            }
        }
        return nums[left] != target ? -1 : left;
    }
    /**
     * From 睡不醒的鲤鱼
     * 对于旋转数组，我们无法直接根据 nums[mid] 与 target 的大小关系来判断 target 是在 mid 的左边还是右边，因此需要「分段讨论」。
     *  1.通过二分找到数组旋转点；
     *  2.确定 target 在哪个区间；
     *  3.子区间内二分查找。
     *
     * 为什么计算 mid 时需要 + 1？
     * 当 l = r - 1 时，mid = l + r >> 1 = l，若更新语句为 l = mid，则区间未变化，会导致死循环，因此需要 + 1 操作。
     */
    static public int search_0(int[] nums, int target) {
        // 找到数组旋转点（左侧最后一个下标）
        // 左侧：nums[i] >= nums[0]
        // 右侧：nums[i] < nums[0]
        int l = 0, r = nums.length - 1;
        while (l < r) { // 寻找旋转点 左边最后一个节点
            int mid = l + (r - l) / 2 + 1;
            if (nums[mid] >= nums[0]) l = mid;
            else r = mid - 1;
        } // 得到l = 3, r = 3
        // 确定 target 在哪个区间，设置 l、r
        if (target >= nums[0]) {
            l = 0; // 在左侧
        } else {
            l = r + 1; // 在右侧
            r = nums.length - 1;
        }
        // 子区间内二分查找（第一个 >= target 的元素下标）
        // 左侧：nums[i] < target
        // 右侧：nums[i] >= target
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] >= target) r = mid;
            else l = mid + 1;
        }
        // 注意：此时 l 可能越界，返回时用 r 判断
        return nums[r] == target ? r : -1;
        /**
         *  这样写也可以
         *  while (l <= r) {
         *    int mid = l + (r - l) / 2;
         *    if (nums[mid] > target) r = mid - 1;
         *    else if(nums[mid] < target) l = mid + 1;
         *    else return mid;
         * }
         * return -1;
         */
    }
}
