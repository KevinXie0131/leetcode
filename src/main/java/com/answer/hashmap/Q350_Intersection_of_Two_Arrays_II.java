package com.answer.hashmap;

import java.util.*;

public class Q350_Intersection_of_Two_Arrays_II {
    /**
     * 两个数组的交集 II
     * 给你两个整数数组 nums1 和 nums2 ，请你以数组形式返回两数组的交集。返回结果中每个元素出现的次数，应与元素在两个数组中都出现的次数一致（如果出现次数不一致，则考虑取较小值）。可以不考虑输出结果的顺序。
     * Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must appear as many times as it shows in both arrays and you may return the result in any order.
     * 示例 1：
     * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出：[2,2]
     * 示例 2:
     * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 输出：[4,9]
     */
    /**
     * 使用Hash数组
     * refer to Q349_Intersection_of_Two_Arrays
     */
    public int[] intersect_0(int[] nums1, int[] nums2) {
        int[] hash1 = new int[1002];
        int[] hash2 = new int[1002];

        for(int i = 0; i < nums1.length; i++){
            hash1[nums1[i]]++;
        }
        for(int i = 0; i < nums2.length; i++){
            hash2[nums2[i]]++;
        }
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < 1002; i++){
            if(hash1[i] > 0 && hash2[i] > 0){
                int count = Math.min(hash1[i], hash2[i]);
                while(count-- > 0){
                    result.add(i);
                }
            }
        }
        return result.stream().mapToInt(i -> i).toArray();
    }
    /**
     * 使用HashMap
     * refer to Q349_Intersection_of_Two_Arrays
     */
    public int[] intersect_3(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int n : nums1){
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        List<Integer> ans = new ArrayList<>();
        for(int num : nums2){
            if(map.containsKey(num) && map.get(num) > 0) {
                ans.add(num);
                map.put(num, map.get(num) - 1);
            }
        }
        return ans.stream().mapToInt(i -> i).toArray();
    }
    /**
     * Approach 1: HashMap
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
     * refer to Q349_Intersection_of_Two_Arrays
     *
     * 1.如果两个数组是有序的，则可以使用双指针的方法得到两个数组的交集。
     * 2.首先对两个数组进行排序，然后使用两个指针遍历两个数组。
     * 3.初始时，两个指针分别指向两个数组的头部。每次比较两个指针指向的两个数组中的数字，如果两个数字不相等，
     *   则将指向较小数字的指针右移一位，如果两个数字相等，将该数字添加到答案，并将两个指针都右移一位。
     *   当至少有一个指针超出数组范围时，遍历结束。
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
