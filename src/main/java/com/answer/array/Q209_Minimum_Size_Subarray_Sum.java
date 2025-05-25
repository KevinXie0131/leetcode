package com.answer.array;

import java.util.Arrays;

public class Q209_Minimum_Size_Subarray_Sum {
    /**
     * 长度最小的子数组: 给定一个含有 n 个正整数的数组和一个正整数 target 。
     * 找出该数组中满足其总和大于等于 target 的长度最小的 子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。
     * 如果不存在符合条件的子数组，返回 0 。
     * (Subarray子数组 是数组中连续的 非空 元素序列。)
     * 示例 1：
     *  输入：target = 7, nums = [2,3,1,2,4,3]
     *  输出：2
     *  解释：子数组 [4,3] 是该条件下的长度最小的子数组。
     */
    public static void main(String[] args) {
       int target = 7;
       int[] nums = {2,3,1,2,4,3};
       System.out.println(minSubArrayLen7(target,nums));
        int[] nums1 = {1,1,1,1,1,1,1,1};
        System.out.println(minSubArrayLen7(11,nums1));
    }
    /**
     * subarray: A subarray is a contiguous non-empty sequence of elements within an array.
     * 子数组 是数组中连续的 非空 元素序列。
     *
     * return the minimal length of a subarray whose sum is greater than or equal to target
     * 方法一：暴力法 Time Limit Exceeded 时间复杂度：O(n^2) 效率很差
     */
    public int minSubArrayLen(int target, int[] nums) {
        int result = Integer.MAX_VALUE;

       for (int left = 0; left <  nums.length; left++) {
           int sum = 0;
            for (int right = left; right < nums.length; right++) {
                sum += nums[right];
                if (sum >= target) {
                    result = Math.min(result, right - left + 1);
                    break;
                }
            }
        }
        return result == Integer.MAX_VALUE? 0 : result;
    }
    /**
     * 滑动窗口Sliding Window/Two Pointers  时间复杂度：O(n)
     * 每一轮迭代，将 nums[end] 加到 sum，如果 sum≥s，则更新子数组的最小长度（此时子数组的长度是 end−start+1），
     * 然后将 nums[start] 从 sum 中减去并将 start 右移，直到 sum<s，在此过程中同样更新子数组的最小长度。
     * 在每一轮迭代的最后，将 end 右移。
     */
    public int minSubArrayLen1(int target, int[] nums) {
        int left = 0;
        int sum = 0;
        int result = Integer.MAX_VALUE;

        for(int right = 0; right < nums.length; right++){
            sum += nums[right];
            while(sum >= target){
                result = Math.min(result, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }
        return result == Integer.MAX_VALUE? 0 : result;
    }
    /**
     * 使用队列相加（实际上我们也可以把它称作是滑动窗口，这里的队列其实就相当于一个窗口）
     * 在代码中我们不直接使用队列，我们使用两个指针，一个指向队头一个指向队尾
     */
    public int minSubArrayLen3(int target, int[] nums) {  // 同上
        int lo = 0, hi = 0, sum = 0, min = Integer.MAX_VALUE;
        while (hi < nums.length) {
            sum += nums[hi];
            hi++;
            while (sum >= target) {
                min = Math.min(min, hi - lo);
                sum -= nums[lo];
                lo++;
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
    /**
     * 使用队列相减
     * 第一种是使用相加的方式，这里我们改为相减的方式，基本原理都差不多，
     */
    public int minSubArrayLen6(int target, int[] nums) {
        int lo = 0, hi = 0, min = Integer.MAX_VALUE;
        while (hi < nums.length) {
            target -= nums[hi++];
            while (target <= 0) {
                min = Math.min(min, hi - lo);
                target += nums[lo++];
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
    /**
     * 前缀和 + 二分查找
     * 因为这道题保证了数组中每个元素都为正，所以前缀和一定是递增的，这一点保证了二分的正确性。
     * 如果题目没有说明数组中每个元素都为正，这里就不能使用二分来查找这个位置了。
     *
     * prefixSum数组中的元素是递增的。我们只需要找到 prefixSum[k]-prefixSum[j]>=s，那么 k-j 就是满足的连续子数组，
     * 但不一定是最小的，所以我们要继续找，直到找到最小的为止
     * 求 prefixSum[k]-prefixSum[j]>=target 我们可以求 prefixSum[j]+target<=prefixSum[k].
     * 只需要求出 prefixSum[j]+target 的值，然后使用二分法查找即可找到这个 k。
     *
     *  Arrays.binarySearch
     *    可以找到：返回一个 >=0 的索引
     *    找不到：【从 1 开始计数】
     *      在数组范围内，返回 -（key 将要插入的位置）
 *          不在范围内：返回 -1 或者 -(len + 1)
     */
    static public int minSubArrayLen4(int target, int[] nums) {
        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        prefixSum[0] = 0;
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        // 得到前缀和之后，对于每个开始下标 i，可通过二分查找得到大于或等于 i 的最小下标 index，
        // 使得 prefixSum[bound]−prefixSum[i−1] ≥ target，并更新子数组的最小长度（此时子数组的长度是 index−(i−1)）。
        int min  = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            int targetSum = target + prefixSum[i - 1];
            int index  = Arrays.binarySearch(prefixSum, targetSum);
            if (index  < 0) {
               // index  = - index  - 1;
                index = ~index;
                // 注意这里的函数 int index = Arrays.binarySearch(sums, target);
                // 如果找到就会返回值的下标，如果没找到就会返回一个负数，这个负数取反之后就是查找的值应该在数组中的位置
                // 也可以这么理解，只要取反之后不是数组的长度，那么他就是原数组中第一个比他大的值的下标
            }
            if (index <= n ) {
                min  = Math.min(min, index  - (i - 1));
            }
        }
        return min  == Integer.MAX_VALUE ? 0 : min;
    }
    /**
     * Anther form
     */
    static int minSubArrayLen7(int target, int[] nums) {
        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        prefixSum[0] = 0;
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        int min  = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            int targetSum = target + prefixSum[i - 1];
            int index  = binarySearch(prefixSum, targetSum);
            if (index <= n ) { //  If there is no such subarray, return 0 instead.
                min = Math.min(min, index - (i - 1));
            }
        }
        return min  == Integer.MAX_VALUE ? 0 : min;
    }
    // 二分查找
    static private int binarySearch(int[] nums, int target){
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = nums[mid];

            if (midVal < target)
                low = mid + 1;
            else if (midVal > target)
                high = mid - 1;
            else
                return mid; // key found
        }
        return low < 0 ? 0: low;
        //  high > nums.length - 1 ? nums.length - 1: high; // not work
    }
    /**
     *  前缀和 Time Limit Exceeded
     */
    public int minSubArrayLen5(int target, int[] nums) {
        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        prefixSum[0] = 0;
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            int targetSum = target + prefixSum[i - 1];
            for(int j = i; j <= n; j++){
                if(prefixSum[j] >= targetSum){
                   ans = Math.min(ans, j - (i - 1));
                }
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
