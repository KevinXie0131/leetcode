package com.answer.hashmap;

import java.util.*;

public class Q217_Contains_Duplicate {

    /**
     * Approach #3 (Hash Table)
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int n : nums){
            if(!set.add(n)){
                return true;
            }
        }
        return false;
    }
    /**
     * Approach #2 (Sorting)
     */
    public boolean containsDuplicate_1(int[] nums) {
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 1; i++){
            if(nums[i] == nums[i + 1]){
                return true;
            }
        }
        return false;
    }
}
