package com.answer.hashmap;

import java.util.*;

public class Q219_Contains_Duplicate_II {

    /**
     * Use HashSet as sliding window
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for(int i = 0 ; i < nums.length; i++){
            if(set.contains(nums[i])){
                return true;
            }
            set.add(nums[i]);
            if(set.size() > k){
                set.remove(nums[i - k]);
            }
        }
        return false;
    }
    /**
     * Use HashMap
     */
    public boolean containsNearbyDuplicate_1(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            int num = nums[i];
            if (map.containsKey(num) && i - map.get(num) <= k) {
                return true;
            }
            map.put(num, i);
        }
        return false;
    }
}
