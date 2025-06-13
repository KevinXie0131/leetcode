package com.answer.greedy;

import java.util.Arrays;

public class Q561_Array_Partition {
    /**
     * 数组拆分
     * 定长度为 2n 的整数数组 nums ，你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从 1 到 n 的 min(ai, bi) 总和最大。
     * 返回该 最大总和 。
     * 输入：nums = [1,4,3,2]
     * 输出：4
     * 解释：所有可能的分法（忽略元素顺序）为：
     * 1. (1, 4), (2, 3) -> min(1, 4) + min(2, 3) = 1 + 2 = 3
     * 2. (1, 3), (2, 4) -> min(1, 3) + min(2, 4) = 1 + 2 = 3
     * 3. (1, 2), (3, 4) -> min(1, 2) + min(3, 4) = 1 + 3 = 4
     * 所以最大总和为 4
     */
    /**
     * Approach 1: Sorting - Greedy
     * The number paired with x will be the second smallest element in the given list. Hence, we will pair each element with the closest unpaired number
     * in ascending sorted order. After sorting the given list, the first element can be paired with the second element,
     * the third element can be paired with the fourth, and so on.
     *
     * 先对数组进行排序。
     * 由于每两个数，我们只能选择当前小的一个进行累加。
     * 因此我们猜想应该从第一个位置进行选择，然后隔一步选择下一个数。这样形成的序列的求和值最大。
     *
     * 于每个数对，只有两者中较小的值可以计入最后结果，而较大的值则会“浪费”。因此，为了得到和的最大值，我们必须尽可能地去保留数组中的大数。
     * 而最大的值一定会被“浪费”，第二大的值可以保留。所以为了得到正确结果我们可以让数组有序，之后每次间隔取值即可
     *
     * 给数组里的元素两两组队，每对取其中小的那个做加和，求最大加和是多少。
     * 考虑每次组队我们都会损失队伍里更大的数字，我们控制让损失的数字尽量小就好了。
     * 即我们每次选择一个数字，就用第一个比它大的数字作为损失的数字即可。
     */
    public int arrayPairSum(int[] nums) {
        // Sort the list in ascending order
        Arrays.sort(nums);
        // Initialize sum to zero
        int maxSum = 0;
        for (int i = 0; i < nums.length; i += 2) {  //选择每对中更小的那个
            // Add every element at even positions (0-indexed)
            maxSum += nums[i];
        }
        return maxSum;
    }
}
