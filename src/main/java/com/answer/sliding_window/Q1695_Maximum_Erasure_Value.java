package com.answer.sliding_window;

import java.util.*;

public class Q1695_Maximum_Erasure_Value {
    /**
     * You are given an array of positive integers nums and want to erase a subarray containing unique elements.
     * The score you get by erasing the subarray is equal to the sum of its elements.
     * Return the maximum score you can get by erasing exactly one subarray.
     * An array b is called to be a subarray of a if it forms a contiguous subsequence of a, that is, if it is equal to a[l],a[l+1],...,a[r] for some (l,r).
     * 删除子数组的最大得分: 一个正整数数组 nums ，请你从中删除一个含有 若干不同元素 的子数组。删除子数组的 得分 就是子数组各元素之 和 。
     * 返回 只删除一个 子数组可获得的 最大得分 。
     * 如果数组 b 是数组 a 的一个连续子序列，即如果它等于 a[l],a[l+1],...,a[r] ，那么它就是 a 的一个子数组。
     * Example 1:
     *  Input: nums = [4,2,4,5,6]
     *  Output: 17
     *  Explanation: The optimal subarray here is [2,4,5,6].
     * Example 2:
     *  Input: nums = [5,2,1,2,5,2,1,2,5]
     *  Output: 8
     *  Explanation: The optimal subarray here is [5,2,1] or [1,2,5].
     */
    /**
     * Sliding window 哈希表+滑动窗口
     * Approach 2: Two Pointer Approach Using Set
     */
    public int maximumUniqueSubarray(int[] nums) {
        int max = 0, sum = 0;
        int left = 0, right = 0;
        Set<Integer> set = new HashSet<>();

        while(right < nums.length){
            int num = nums[right];
            sum += num; // 更新当前和
            // increment start until subarray has unique elements
        //  while(set.contains(nums[right])){ // works too
            while(set.contains(num)){   // 如果当前数字已经在集合中
                sum -= nums[left];      // 从当前和中减去该数字
                set.remove(nums[left]); // 移除左指针指向的数字
                left++;                 // 移动左指针
            }
            set.add(num); // 添加当前数字到集合
            // update result with maximum sum found so far
            max = Math.max(sum, max); //  更新最大得分
            right++;
        }
        return max;
    }
    /**
     * another form works too
     */
    public int maximumUniqueSubarray_0(int[] nums) {
        int max = 0, sum = 0;
        int left = 0;
        Set<Integer> set = new HashSet<>();

        for(int right = 0; right < nums.length;  right++){
            while(set.contains(nums[right])){   // 如果当前数字已经在集合中
                sum -= nums[left];      // 从当前和中减去该数字
                set.remove(nums[left]); // 移除左指针指向的数字
                left++;                 // 移动左指针
            }
            sum += nums[right]; // 更新当前和
            set.add(nums[right]); // 添加当前数字到集合
            max = Math.max(sum, max); //  更新最大得分
        }
        return max;
    }
    /**
     * Approach 3: Two Pointer Approach Using Boolean Array
     * 1 <= nums[i] <= 10^4
     */
    public int maximumUniqueSubarray_1(int[] nums) {
        int result = 0;
        int currentSum = 0;
        int k = 10001;
        boolean[] isPresent = new boolean[k];
        int start = 0;

        for (int end = 0; end < nums.length; end++) {
            // increment start until subarray has unique elements
            while (isPresent[nums[end]]) {
                isPresent[nums[start]] = false;
                currentSum -= nums[start];
                start++;
            }
            isPresent[nums[end]] = true;
            currentSum += nums[end];
            // update result with maximum sum found so far
            result = Math.max(result, currentSum);
        }
        return result;
    }
    /**
     * Approach 4: Two Pointer Approach Using Count Map
     */
    public int maximumUniqueSubarray_2(int[] nums) {
        int start = 0;
        int result = 0;
        int sum = 0;
        int k = 10001;
        int[] counter = new int[k];

        for (int end = 0; end < nums.length; end++) {
            int cur = nums[end];
            counter[cur]++;
            sum += cur;

            while (start < end && counter[cur] > 1) {
                counter[nums[start]]--;
                sum -= nums[start];
                start++;
            }
            // update result with maximum sum found so far
            result = Math.max(result, sum);
        }
        return result;
    }
}
