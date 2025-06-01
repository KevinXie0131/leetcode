package com.answer.sliding_window;

public class Q487_Max_Consecutive_Ones_II {
    /**
     * Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0.
     * Example 1:
     *  Input: [1,0,1,1,0]
     *  Output: 4
     *  Explanation: Flip the first zero will get the the maximum number of consecutive 1s.
     *                  After flipping, the maximum number of consecutive 1s is 4.
     * 给定一个二进制数组，你可以最多将 1 个 0 翻转为 1，找出其中最大连续 1 的个数。
     * Note:  The input array will only contain 0 and 1.
     *        The length of input array is a positive integer and will not exceed 10,000
     * Follow up: What if the input numbers come in one by one as an infinite stream? In other words,
     *      you can't store all numbers coming from the stream as it's too large to hold in memory. Could you solve it efficiently?
     * 注：输入数组只包含 0 和 1.
     *     输入数组的长度为正整数，且不超过 10,000
     * 进阶：如果输入的数字是作为 无限流 逐个输入如何处理？换句话说，内存不能存储下所有从流中输入的数字。您可以有效地解决吗？
     */
    public static void main(String[] args) {
        int[] nums = {1,0,1,1,0,1};
        System.out.println(findMaxConsecutiveOnes(nums));
    }

    /**
     * Approach 2: Sliding Window
     */
    public static int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int count = 0;
        int left = 0;
        int right = 0;
        // while our window is in bounds
        while(right < nums.length){
            if(nums[right] == 0){
                // add the right most element into our window
                count++;
                while(count > 1){ // if our window is invalid, contract our window
                    /**
                     * count -= nums[l++] == 0 ? 1 : 0;
                     */
                    if(nums[left] == 0){
                        count--;
                    }
                    left++;
                }
            }
            max = Math.max(max, right - left + 1);  // update our longest sequence answer
            right++;  // expand our window
        }
        return max;
    }
    /**
     * Another form of slding window
     */
    public int findMaxConsecutiveOnes_1(int[] nums) {
        int l = 0;
        int maxLen = 0;
        int count = 1;
        for (int r = 0; r < nums.length; r++) {
            int num = nums[r];
            if (num == 0) {
                count--;
            }
            while (count < 0) {
                if (nums[l] == 0) {
                    count++;
                }
                l++;
            }
            maxLen = Math.max(maxLen, r - l + 1);
        }
        return maxLen;
    }
    /**
     * Approach 1: Brute Force
     */
    public int findMaxConsecutiveOnes_3(int[] nums) {
        int longestSequence = 0;
        for (int left = 0; left < nums.length; left++) {
            int numZeroes = 0;

            // check every consecutive sequence
            for (int right = left; right < nums.length; right++) {
                // count how many 0's
                if (nums[right] == 0) {
                    numZeroes += 1;
                }
                // # update answer if it's valid
                if (numZeroes <= 1) {
                    longestSequence = Math.max(longestSequence, right - left + 1);
                }
            }
        }
        return longestSequence;
    }
}
