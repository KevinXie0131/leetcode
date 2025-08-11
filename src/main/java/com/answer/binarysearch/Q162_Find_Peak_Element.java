package com.answer.binarysearch;

public class Q162_Find_Peak_Element {
    /**
     * 寻找峰值
     * A peak element is an element that is strictly greater than its neighbors.
     * 峰值元素是指其值严格大于左右相邻值的元素。
     * Given a 0-indexed integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.
     * 给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
     * You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is always considered to be strictly greater than a neighbor that is outside the array.
     * 你可以假设 nums[-1] = nums[n] = -∞ 。
     * You must write an algorithm that runs in O(log n) time.
     * 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
     *
     * 示例 1：
     *  输入：nums = [1,2,3,1]
     *  输出：2
     *  解释：3 是峰值元素，你的函数应该返回其索引 2。
     * 示例 2：
     *  输入：nums = [1,2,1,3,5,6,4]
     *  输出：1 或 5
     *  解释：你的函数可以返回索引 1，其峰值元素为 2；或者返回索引 5， 其峰值元素为 6。
     * 对于所有有效的 i 都有 nums[i] != nums[i + 1] nums[i] != nums[i + 1] for all valid i.
     */
    /**
     * Approach 1: Linear Scan
     * 由于题目保证了 nums[i]!=nums[i+1]，那么数组 nums 中最大值两侧的元素一定严格小于最大值本身。因此，最大值所在的位置就是一个可行的峰值位置。
     * 我们对数组 nums 进行一次遍历，找到最大值对应的位置即可。
     */
    public int findPeakElement(int[] nums) {
        int index = 0;

        for(int i = 0; i< nums.length; i++){
            if(nums[i] > nums[index]){
                index = i;
            }
        }
        return index;
    }
    /**
     * 模拟: 首先，我们根据峰值的定义（严格大于左右相邻值的元素），遍历一遍数组，即可找到满足条件的峰值。
     */
    public int findPeakElement7(int[] nums) {
        int n = nums.length;
        // 数组只有一个元素，必为峰值
        if(n == 1) return 0;
        for(int i = 0; i < n; ++i) {
            if(i == 0) {  // 对 i = 0 和 i = n - 1 进行特判
                if(nums[i] > nums[i + 1]) return i;
            } else if(i == n - 1) {
                if(nums[i] > nums[i - 1]) return i;
            } else {
                if(nums[i] > nums[i + 1] && nums[i] > nums[i - 1]) {
                    return i; // 其余情况下要保证比两侧值都大
                }
            }
        }
        return 0;
    }
    /**
     * Approach 3: Recursive Binary Search
     */
    public int findPeakElement_1(int[] nums) {
        return search(nums, 0, nums.length - 1);
    }

    public int search(int[] nums, int left, int right){
        if(left == right){
            return left;
        }
        int mid = (left + right) >>> 1;

        if(nums[mid] < nums[mid + 1]){
            return search(nums, mid + 1, right);
        }else{
            return search(nums, left, mid);
        }
    }
    /**
     * 二分查找
     * 首先要注意题目条件，在题目描述中出现了 nums[-1] = nums[n] = -∞，这就代表着 只要数组中存在一个元素比相邻元素大，那么沿着它一定可以找到一个峰值
     * 根据上述结论，我们就可以使用二分查找找到峰值
     *
     * 有nums[-1] = nums[n] = -∞兜底，峰值一定存在，才能这么爬坡
     * 题目只是要求求出一个峰值，因此只要右边存在答案就可以把左边都抛弃掉，然后只要一直确保这个区间存在答案，
     */
    public int findPeakElement0(int[] nums) { // 区间左闭右闭版本
        int length = nums.length;
        if(length == 1){
            return 0;
        }
        int left = 0;
        int right = length - 1;
        int mid = 0;
        // 每次取left = mid + 1 或 right = mid - 1。需要注意的当mid == length - 1的与mid + 1相比较会产生越界错误，
        // 但是由于nums[length] = 负无穷, 所以默认mid = length - 1的时候比它的右边打，从而 right = mid - 1
        while(left <= right){
            mid = left + (right - left) / 2;
            if(mid < length - 1 && nums[mid] < nums[mid + 1]){
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
        }
        return left; // return right + 1; // works too
    }
    /**
     * another fomm
     */
    public int findPeakElement_6(int[] nums) {
        int length = nums.length;
        if(length == 1) return 0;
        int left = 0, right = length - 1;
        int res = 0;

        while(left <= right){
            int mid = left + (right - left) / 2;
            if(mid < length - 1 && nums[mid] < nums[mid + 1]){ // nums[mid] <= nums[mid + 1] works too
                left = mid + 1;
                res = left; // keep mid + 1
            } else{
             //   res = mid; // works too, and res = left & res = mid can uncomment at the same time
                right = mid - 1;
            }
        }
        return res;
    }
    /**
     * 二分查找
     * 在二分查找中，每次会找到一个位置 mid。我们发现，mid 只有如下三种情况：
     *  mid 为一个峰值，此时我们通过比较 mid 位置元素与两边元素大小即可。
     *  mid 在一个峰值右侧，此时有 nums[mid]<nums[mid+1]，此时我们向右调整搜索范围，在 [mid+1,r] 范围内继续查找。
     *  mid 在一个峰值左侧，此时有 nums[mid]<nums[mid−1]，此时我们向左调整搜索范围，在 [l+1,mid] 范围内继续查找。
     * 细节: 还是要注意判别边界情况，防止与两侧比较时导致下标越界。
     */
    public int findPeakElement8(int[] nums) {
        int n = nums.length;
        if(n == 1) return 0;
        // 先特判两边情况
        if(nums[0] > nums[1]) return 0;
        if(nums[n - 1] > nums[n - 2]) return n - 1;

        int l = 0, r = n - 1;
        while(l <= r) {
            int mid = (l + r) / 2;
            if(mid >= 1 && mid < n - 1 && nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                return mid;// 当前为峰值
            } else if(mid >= 1 && nums[mid] < nums[mid - 1]) {
                r = mid - 1;  // 峰值在 mid 左侧
            } else if(mid < n - 1 && nums[mid] < nums[mid + 1]) {
                l = mid + 1;// 峰值在 mid 右侧
            }
        }
        return 0;
    }
    /**
     * Approach 3: Iterative Binary Search 二分查找
     * 如果 nums[i]<nums[i+1]，那么我们往右走；
     * 如果 nums[i]>nums[i+1]，那么我们往左走。
     *
     * 如果nums[mid] > nums[mid + 1]，那么在[l, mid]这个区间内一定存在一个峰值。因为[l,mid]这一段如果是单调递减的话，
     * 那么nums[l]就是峰值，否则第一个出现上升的点就是峰值。
     *
     * 如果nums[mid] < nums[mid + 1]，那么在[mid+1, r]这个区间内一定存在一个峰值。
     * 因为[mid+1,r]这一段如果是单调递增的话，那么nums[r]就是峰值，否则第一个出现下降的点就是峰值。
     */
    public int findPeakElement_2(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while(left < right){
            int mid = (left + right) >>> 1;
            if(nums[mid] < nums[mid + 1]){
                left = mid + 1; // 因为峰值至少为mid + 1
            } else {
                // 假设当前分割点 mid 满足关系 num[mid]>nums[mid+1] 的话，一个很简单的想法是 num[mid] 可能为峰值，
                // 而 nums[mid+1] 必然不为峰值，于是让 r=mid，从左半部分继续找峰值。
                right = mid; // 因为right没有-1， 所以left < right
            }
        }
        return left;
        // return right; // works too
        // return nums[right] > nums[left] ? right : left; //  works too. and safer
    }
    /**
     * Template II 二分查找
     */
    public int findPeakElement_3(int[] nums) {
        int n = nums.length;
        int l = 0;
        int r = n - 1;
        while(l < r){
            int mid = l + (r - l) / 2;
            if(nums[mid] > nums[mid + 1]){
                r = mid;//说明此时mid为下坡路，那么有可能自己本身就是山峰，或者在下山的过程中，所以right=mid而不能等于mid-1
            }
            else{
                l = mid + 1;//反之说明此时mid为上坡路，既然是上坡，那么mid肯定不是山峰，所以left=mid+1（题目要求nums[i]!=nums[i+1]，所以不可能存在“平峰”的情况）
            }
        }
        return l;
    }
}
