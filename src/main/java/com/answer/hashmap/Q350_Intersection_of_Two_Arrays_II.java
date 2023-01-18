package com.answer.hashmap;

import java.util.Arrays;
import java.util.*;

public class Q350_Intersection_of_Two_Arrays_II {

    /**
     * Approach 1: Hash Map
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int n : nums1){
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        int[] result = new int[nums1.length + nums2.length];
        int index = 0;
        for(int n: nums2){
            int count = map.getOrDefault(n, 0);
            if(count > 0){
                result[index++] = n;

                count--;
                if(count > 0){
                    map.put(n, count);
                }else{
                    map.remove(n);
                }

            }
        }
        return Arrays.copyOfRange(result, 0, index);
    }

    /**
     * Sorting & Two pointers
     */
    public static int[] intersection_1(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] result = new int[len1 + len2];
        int index = 0, index1 = 0, index2 = 0;

        while(index1 < len1 && index2 < len2){
            int n1 = nums1[index1];
            int n2 = nums2[index2];
            if(n1 == n2){
                result[index++] = n1;

                index1++;
                index2++;
            } else if(n1 > n2){
                index2++;
            } else {
                index1++;
            }
        }
        return Arrays.copyOfRange(result, 0, index);
    }
}
