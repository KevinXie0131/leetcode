package com.answer.hashmap;

import java.util.*;

public class Q454_4Sum_II {
    /**
     * 四数相加 II
     * 给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组(tuples ) (i, j, k, l) 能满足：
     *  0 <= i, j, k, l < n
     *  nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
     *
     * 示例 1：
     * 输入：nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
     * 输出：2
     * 解释：两个元组如下：
     * 1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
     * 2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
     */
    /**
     * 之前我们讲过哈希表的经典题目：454.四数相加II ，相对于本题(第18题. 四数之和)简单很多，
     * 因为本题是要求在一个集合中找出四个数相加等于target, 同时四元组不能重复。
     * 而454.四数相加II 是四个独立的数组，只要找到A[i] + B[j] + C[k] + D[l] = 0就可以，
     * 不用考虑有重复的四个元素相加等于0的情况，所以相对于本题还是简单了不少！
     */
    /**
     * 使用哈希法
     */
    public int fourSumCount_0(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        //统计两个数组中的元素之和，同时统计出现的次数，放入map
        for(int i : nums1){
            for(int j : nums2){
                map.put(i + j, map.getOrDefault(i + j, 0) + 1);
            }
        }
        //统计剩余的两个元素的和，在map中找是否存在相加为0的情况，同时记录次数
        for(int i : nums3){
            for(int j : nums4){
                // count +=  map.getOrDefault(0 - (i + j), 0);
                if(map.containsKey(0 - (i + j))){
                    count += map.get(0 - (i + j));
                }
            }
        }
        return count;
    }
    /**
     *
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for(int a : nums1){
            for(int b : nums2){
                int sum = a + b;
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }
        int res = 0;
        for(int c : nums3){
            for(int d : nums4){
                int sum = c + d;
                if(map.containsKey(-sum)){
                    res += map.get(-sum);
                }
            }
        }
        return res;
    }
}
