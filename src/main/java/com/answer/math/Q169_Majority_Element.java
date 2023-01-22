package com.answer.math;

import java.util.*;

public class Q169_Majority_Element {

    public int majorityElement(int[] nums) {
        Arrays.sort(nums);

        return nums[nums.length/2];
    }
    /**
     *
     */
    public int majorityElement_1(int[] nums) {
        Map<Integer, Long> map = new HashMap<>();

        for(int n : nums){
            map.put(n, map.getOrDefault(n, (long)0) + 1);
        }

        long limit = nums.length >> 1;

        for (Map.Entry<Integer, Long> entry : map.entrySet()){
            if (entry.getValue() > limit){
                return entry.getKey();
            }
        }
        return -1;
    }
    /**
     * Boyer-Moore Voting Algorithm
     */
    public int majorityElement_2(int[] nums) {
        int a=nums[0],b=1;
        for(int i=1;i<nums.length;i++){
            if(a==nums[i])b++;
            else{
                b--;
                if(b==0){
                    a=nums[i];
                    b=1;
                }
            }
        }
        return a;
    }
}
