package com.answer.math;

import java.util.*;

public class Q229_Majority_Element_II {

    public List<Integer> majorityElement(int[] nums) {
        Map<Integer, Long> map = new HashMap<>();

        for(int n : nums){
            map.put(n, map.getOrDefault(n, (long)0) + 1);
        }

        long limit = nums.length / 3;
        List<Integer> ans = new ArrayList<>();

        for (Map.Entry<Integer, Long> entry : map.entrySet()){
            if (entry.getValue() > limit){
                ans.add(entry.getKey());
            }
        }
        return ans;
    }
    /**
     * Approach 1: Boyer-Moore Voting Algorithm
     */
    public List<Integer> majorityElement_1(int[] nums) {
        int n = nums.length;
        int a = 0, b = 0;
        int c1 = 0, c2 = 0;
        for (int i : nums) {
            if (c1 != 0 && a == i) c1++;
            else if (c2 != 0 && b == i) c2++;
            else if (c1 == 0 && ++c1 >= 0) a = i;
            else if (c2 == 0 && ++c2 >= 0) b = i;
            else {
                c1--; c2--;
            }
        }
        c1 = 0; c2 = 0;
        for (int i : nums) {
            if (a == i) c1++;
            else if (b == i) c2++;
        }
        List<Integer> ans = new ArrayList<>();
        if (c1 > n / 3) ans.add(a);
        if (c2 > n / 3) ans.add(b);
        return ans;
    }
}
