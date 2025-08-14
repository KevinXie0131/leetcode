package com.answer.sliding_window;

public class Q1493_Longest_Subarray_of_1s_After_Deleting_One_Element {
    /**
     * Given a binary array nums, you should delete one element from it.
     * Return the size of the longest non-empty subarray containing only 1's in the resulting array.
     * Return 0 if there is no such subarray.
     * 删掉一个元素以后全为 1 的最长子数组: 一个二进制数组 nums ，你需要从中删掉一个元素。
     * 请你在删掉元素的结果数组中，返回最长的且只包含 1 的非空子数组的长度。如果不存在这样的子数组，请返回 0 。
     * 示例 1：
     *  输入：nums = [1,1,0,1]
     *  输出：3
     *  解释：删掉位置 2 的数后，[1,1,1] 包含 3 个 1 。
     * 示例 2：
     *  输入：nums = [0,1,1,1,0,1,1,0,1]
     *  输出：5
     *  解释：删掉位置 4 的数字后，[0,1,1,1,1,1,0,1] 的最长全 1 子数组为 [1,1,1,1,1] 。
     */
    /**
     * Similar with Q487 Max Consecutive Ones II
     * Sliding window 滑动窗口
     * 直接把所求子数组看成只含一个0的子数组，然后再减掉1就行了
     *
     * 滑动窗口枚举右端点，当发现窗口中0的个数大于1个时缩小窗口。这一题不一样的点在于必须要移除一个元素，
     * 所以最后判断的是max(res, right-left)，而不是right-left+1.
     *
     * 注意：题目要求必须删除一个元素。
     *  只包含一个0，将这个0删掉；
     *  不包含0，随便删一个元素；
     */
    public int longestSubarray(int[] nums) {
        int left = 0, right = 0;
        int max = 0, count = 0;

        while (right < nums.length) {
            if (nums[right] == 0) { //记录0的个数
                count++;
             /* while (count > 1) { // works too
                    if (nums[left] == 0) {
                        count--;
                    }
                    left++;
                }*/
            }
            while (count > 1) { //如果又遇到一个0，就把前一个0给扔掉
                if (nums[left] == 0) {
                    count--;
                }
                left++;
            }
            // 当前窗口长度为 (right - left + 1)，删掉一个 0
            max = Math.max(max, right - left); //本来是i - left + 1,但是0不计入，所以再减1
            right++;
        }
        return max;
    }
}
