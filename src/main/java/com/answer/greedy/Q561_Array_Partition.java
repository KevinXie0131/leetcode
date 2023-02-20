package com.answer.greedy;

import java.util.Arrays;

public class Q561_Array_Partition {
    /**
     * Approach 1: Sorting - Greedy
     * The number paired with x will be the second smallest element in the given list. Hence, we will pair each element with the closest unpaired number
     * in ascending sorted order. After sorting the given list, the first element can be paired with the second element,
     * the third element can be paired with the fourth, and so on.
     */
    public int arrayPairSum(int[] nums) {
        // Sort the list in ascending order
        Arrays.sort(nums);
        // Initialize sum to zero
        int maxSum = 0;
        for (int i = 0; i < nums.length; i += 2) {
            // Add every element at even positions (0-indexed)
            maxSum += nums[i];
        }
        return maxSum;
    }
}
