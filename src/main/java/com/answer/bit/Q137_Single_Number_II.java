package com.answer.bit;

import java.util.*;

public class Q137_Single_Number_II {
    /**
     * Given an integer array nums where every element appears three times except for one, which appears exactly once. Find the single element and return it.
     * You must implement a solution with a linear runtime complexity and use only constant extra space.
     * 只出现一次的数字 II
     * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
     * 你必须设计并实现线性时间复杂度的算法且使用常数级空间来解决此问题。
     */
    /**
     * 二进制
     * 统计所有数字每一位中 1 的数量，如果可以整除 3，说明结果这一位为 0，否则为 1。
     *
     * 使用一个长度为 32 的数组 cnt[] 记录下所有数值的每一位共出现了多少次 1，再对 cnt[] 数组的每一位进行 mod 3 操作，
     * 重新拼凑出只出现一次的数值。

     */
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; i++) { //考虑每一位 int的32个位
            int count = 0;
            for (int j = 0; j < nums.length; j++) {//考虑每一个数
                if (((nums[j] >>> i) & 1) == 1) { //当前位是否是 1
                    count++; // 得到每个二进制位总和
                }
            }

            if (count % 3 != 0) { //1 的个数是否是 3 的倍数
                ans = ans | (1 << i);
            }
         /*   if ((count % 3 & 1) == 1) {
                ans += (1 << i);
            }*/
        }
        return ans;
    }
    /**
     * 有限状态自动机
     */
    public int singleNumber_1(int[] nums) {
        int seenOnce = 0, seenTwice = 0;

        for (int num : nums) {
            // first appearence:
            // add num to seen_once
            // don't add to seen_twice because of presence in seen_once

            // second appearance:
            // remove num from seen_once
            // add num to seen_twice

            // third appearance:
            // don't add to seen_once because of presence in seen_twice
            // remove num from seen_twice
            seenOnce = ~seenTwice & (seenOnce ^ num);
            seenTwice = ~seenOnce & (seenTwice ^ num);
        }

        return seenOnce;
    }
    /**
     * 方法一：哈希表
     * 时间复杂度：O(n)，其中 n 是数组的长度。
     * 空间复杂度：O(n)
     */
    public int singleNumber_2(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<Integer, Integer>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int num = entry.getKey(), occ = entry.getValue();
            if (occ == 1) {
                ans = num;
                break;
            }
        }
        return ans;
    }
}
