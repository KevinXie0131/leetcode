package com.answer.binarysearch;

public class binarySearchTemplate {
    /**
     * 左闭右闭
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1; // 左闭右闭

        while(left <= right){  // 左闭右闭
            int mid = (left + right) >>> 1;
          //  int mid = left + ((right- left) >> 1); // works too
          //  int mid = left + (right - left) / 2; // works too
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
    // 递归
    public int recursion (int[] nums, int target, int left, int right){
        if(left > right){
            return -1;
        }
        int mid = left + ((right- left) >> 1); //  int mid = left + (right - left) / 2;

        if(target < nums[mid]){
            return recursion(nums, target, left, mid - 1);
        }else if(target > nums[mid]){
            return recursion(nums, target, mid + 1, right);
        }else{
            return mid;
        }
    }
    /**
     * 寻找左侧边界的二分搜索: 返回最小的满足 nums[i] >= target 的 i
     * 闭区间写法
     * 返回第一个等于 target 的数的下标
     */
    static private int lowerBound(int[] nums, int target) {
        int left = 0, right = nums.length - 1; // 闭区间 [left, right]
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1; // 范围缩小到 [mid + 1, right]
            } else if (nums[mid] > target) {
                right = mid - 1; // 范围缩小到 [left, mid - 1]
            } else if (nums[mid] == target) {
                right = mid - 1;  // 所以当 nums[mid] == target 时不要立即返回, 而要收紧右侧边界以锁定左侧边界
            }
        }
        return left; // 或者 right + 1
    }
    /**
     * based on the above template
     * nums = {0,1,2,3,5,5,5,7,8,9} & target = 5 -> 4
     */
    public int lowerBound_1(int[] nums, int target) {
        int left = 0, right = nums.length - 1; // 闭区间 [left, right]
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1; // 范围缩小到 [mid + 1, right]
            } else if (nums[mid] >= target) {
                right = mid - 1; // 范围缩小到 [left, mid - 1]
            }
        }
        // 由于 while 的退出条件是 left == right + 1，所以当 target 比 nums 中所有元素都大时，会存在以下情况使得索引越界
        return left < nums.length && nums[left] == target? left : -1;
    }
    /**
     * 寻找右侧边界的二分查找: 返回最大的满足 nums[i] >= target 的 i
     * 闭区间写法
     * 返回最后一个等于 target 的数的下标
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
                left = mid + 1;    // 所以当 nums[mid] == target 时不要立即返回, 而要收紧左侧边界以锁定右侧边界
            }
        }
        return right; // 或者 left - 1
    }
    /**
     * based on the above template
     * nums = {0,1,2,3,5,5,5,7,8,9} & target = 5 -> 6
     */
    public int higherBound_1(int[] nums, int target) {
        int left = 0, right = nums.length - 1; // 闭区间 [left, right]
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        return right >= 0 && nums[right] == target? right : -1; // 刚好和计算下边界时条件相反，返回right
    }
    /**
     * 寻找左侧边界的二分搜索: 返回最小的满足 nums[i] >= target + 1 的 i
     * nums = {0,1,2,3,3,5,5,5,6,6,8,9} & target = 5 -> 8
     */
    static private int lowerClosestBound(int[] nums, int target) {
        int left = 0, right = nums.length - 1; // 闭区间 [left, right]
        target++;

        while (left <= right) { // 区间不为空
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1; // 范围缩小到 [mid + 1, right]
            } else if (nums[mid] > target) {
                right = mid - 1; // 范围缩小到 [left, mid - 1]
            } else if (nums[mid] == target) {
                right = mid - 1;
            }
        }
        return left; // 或者 right + 1
    }
    /**
     * 寻找右侧边界的二分查找: 返回最大的满足 nums[i] >= target - 1 的 i
     * nums = {0,1,2,3,3,5,5,5,6,6,8,9} & target = 5 -> 4
     */
    static private int higherClosestBound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        target--;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                left = mid + 1;
            }
        }
        return right; // 或者 left - 1
    }
    /**
     * 寻找比目标字母大的最小字母
     *  - 取中间m
     *  - m大于目标：答案可能是m或左侧，故先记下m，再在m左侧查找（right = m - 1）
     *  - m小于等于目标：答案可能是m右侧，在m右侧查找（left = m + 1）
     */
    public char nextGreatestLetter_(char[] letters, char target) {
        int left = 0, right = letters.length - 1;
        int result = 0;
        while(left <= right){
            int mid = left + (right - left)/2;

            if(letters[mid] <= target){
                left = mid + 1;
            }else{
                result = mid; // 先记下mid
                right = mid - 1;
            }
        }
        return letters[result];
    }
}
