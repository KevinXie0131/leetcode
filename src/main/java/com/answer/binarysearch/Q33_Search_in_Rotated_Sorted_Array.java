package com.answer.binarysearch;

public class Q33_Search_in_Rotated_Sorted_Array {
    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2}; int target = 0;
        int res = search_0(nums, target);
        System.out.println(res);
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
    /**
     * Binary search
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
            if (nums[start] <= nums[mid]) {
                //target在前半部分
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (target <= nums[end] && target > nums[mid]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }

        }
        return -1;

    }
}
