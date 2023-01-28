package com.answer.hashmap;

import java.util.TreeSet;

public class Q220_Contains_Duplicate_III {

    /**
     * Approach #2 (Binary Search Tree)
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {

        TreeSet<Integer> set = new TreeSet<>();
        for(int i = 0 ; i < nums.length; i++){
            if(set.ceiling(nums[i])!= null && set.ceiling(nums[i]) <= nums[i] + valueDiff){
                return true;
            }
            if(set.floor(nums[i]) != null && set.floor(nums[i]) >= nums[i] - valueDiff){
                return true;
            }
            set.add(nums[i]);
            if(set.size() > indexDiff){
                set.remove(nums[i - indexDiff]);
            }
        }
        return false;
    }
}
