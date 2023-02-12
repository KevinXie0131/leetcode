package com.answer.sliding_window;

import java.util.*;

public class Q1695_Maximum_Erasure_Value {

    /**
     * Sliding window
     * Approach 2: Two Pointer Approach Using Set
     */
    public int maximumUniqueSubarray(int[] nums) {
        int max = 0, sum = 0;
        int left = 0, right = 0;
        Set<Integer> set = new HashSet<>();

        while(right < nums.length){
            int num = nums[right];
            sum += num;
            // increment start until subarray has unique elements
            while(set.contains(num)){
                sum -= nums[left];
                set.remove(nums[left]);
                left++;
            }
            set.add(num);
            // update result with maximum sum found so far
            max = Math.max(sum, max);
            right++;
        }

        return max;
    }
    /**
     * Approach 3: Two Pointer Approach Using Boolean Array
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
        int currentSum = 0;
        int k = 10001;
        int[] countMap = new int[k];
        for (int end = 0; end < nums.length; end++) {
            int currentElement = nums[end];
            countMap[currentElement]++;
            currentSum += currentElement;
            while (start < end && countMap[currentElement] > 1) {
                countMap[nums[start]]--;
                currentSum -= nums[start];
                start++;
            }
            // update result with maximum sum found so far
            result = Math.max(result, currentSum);
        }
        return result;
    }
}
