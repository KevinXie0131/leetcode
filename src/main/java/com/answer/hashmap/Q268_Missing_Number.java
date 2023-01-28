package com.answer.hashmap;

import java.util.*;

public class Q268_Missing_Number {

    /**
     * HashSet
     */
    public int missingNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int len = nums.length;
        for(int i = 0; i <= len; i++){
            set.add(i);
        }
        for(int n : nums){
            set.remove(n);
        }
        return set.iterator().next();
    }
    /**
     *
     */
    public int missingNumber_1(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int len = nums.length;

        for(int n : nums){
            set.add(n);
        }
        for(int i = 0; i <= len; i++){
            if(!set.contains(i)){
                return i;
            }
        }
        return -1;
    }
    /**
     * Sorting
     */
    public int missingNumber_2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return n;
    }
    /**
     * Approach #3 Bit Manipulation: XOR
     */
    public int missingNumber_3(int[] nums) {
        int x = 0;
        for (int n : nums) {
            x ^= n;
        }
        for (int i = 0; i <= nums.length; i++) {
            x ^= i;
        }
        return x;
    }
    /**
     * Approach #4 Gauss' Formula
     */
    public int missingNumber_4(int[] nums) {
        int n = nums.length;
        int total = n * (n + 1) / 2;
        int arrSum = 0;
        for (int i = 0; i < n; i++) {
            arrSum += nums[i];
        }
        return total - arrSum;
    }
}
