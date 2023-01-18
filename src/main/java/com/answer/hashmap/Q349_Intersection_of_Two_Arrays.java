package com.answer.hashmap;

import java.lang.reflect.Array;
import java.util.*;

public class Q349_Intersection_of_Two_Arrays {

    public static void main(String[] args) {
/*        int[] nums1 = {4,9,5};
        int[] nums2 = {9,4,9,8,4};*/
        int[] nums1 = {1,2,2,1};
        int[] nums2 = {2,2};
        int[] result = intersection_1(nums1, nums2);
        System.out.println(Arrays.toString(result));
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        if(nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0){
            return new int[0];
        }
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        for(int i : nums1){
            set1.add(i);
        }

        for(int i : nums2){
            if(set1.contains(i)){
                set2.add(i);
            }
        }
        int[] result = new int[set2.size()];
        result =  set2.stream().mapToInt(Number::intValue).toArray();
        return result;
    }

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
                if(!(index > 0 && result[index-1] == n1)){
                    result[index++] = n1;
                }
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
