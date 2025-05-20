package com.answer.bit;

import java.util.*;

public class Q260_Single_Number_III {
    public static void main(String[] args) {
        /**
         * The expression n & -n isolates the lowest set bit. It works because -n is the two's complement of n,
         * which inverts all the bits of n and adds 1. When you perform a bitwise AND between n and -n,
         * all bits except the lowest set bit become 0.
         */
        int a = 6;
        System.out.println(-a); // -6
        System.out.println(a & -a); // 2
    }
    /**
     * Given an integer array nums, in which exactly two elements appear only once and all the other elements
     * appear exactly twice. Find the two elements that appear only once. You can return the answer in any order.
     * You must write an algorithm that runs in linear runtime complexity and uses only constant extra space.
     * 给你一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。
     * 你可以按 任意顺序 返回答案。
     * 你必须设计并实现线性时间复杂度的算法且仅使用常量额外空间来解决此问题。
     */
    /**
     * 方法一：哈希表
     * 时间复杂度：O(n)，其中 n 是数组 nums 的长度。
     * 空间复杂度：O(n)，即为哈希映射需要使用的空间。
     */
    public int[] singleNumber(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<Integer, Integer>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        int[] ans = new int[2];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            if (entry.getValue() == 1) {
                ans[index++] = entry.getKey();
            }
        }
        return ans;
    }
    /**
     * 方法二：位运算
     * 使用位运算 x & -x 取出 x 的二进制表示中最低位那个 1，设其为第 L 位,
     * 某一个数的二进制表示的第 L 位为 0，另一个数的二进制表示的第 L 位为 1
     *  nums 中的所有元素分成两类，其中一类包含所有二进制表示的第 L 位为 0 的数，另一类包含所有二进制表示的第 L 位为 1 的数
     *
     *  对于任意一个在数组 nums 中出现两次的元素，该元素的两次出现会被包含在同一类中
     *  对于任意一个在数组 nums 中只出现了一次的元素，它们会被包含在不同类中。
     */
    public int[] singleNumber_2(int[] nums) {
        int xorsum = 0;
        for (int num : nums) {
            xorsum ^= num;
        }
        // 防止溢出
        int lsb = (xorsum == Integer.MIN_VALUE ? xorsum : xorsum & (-xorsum));
        int type1 = 0, type2 = 0;
        for (int num : nums) {
            if ((num & lsb) != 0) {
                type1 ^= num;
            } else {
                type2 ^= num;
            }
        }
        return new int[]{type1, type2};
    }
    /**
     * 同上
     */
    public int[] singleNumber_1(int[] nums) {
        // difference between two numbers (x and y) which were seen only once
        int bitmask = 0;
        for (int num : nums) { // 把所有的元素进行异或操作，最终得到一个异或值。因为是不同的两个数字，所以这个值必定不为 0；
            bitmask ^= num;
        }

        // rightmost 1-bit diff between x and y
        int diff = bitmask & (-bitmask); // 取异或值最后一个二进制位为 1 的数字作为 mask，如果是 1 则表示两个数字在这一位上不同。

        int x = 0;
        // bitmask which will contain only x
        // 通过与这个 mask 进行与操作，如果为 0 的分为一个数组，为 1 的分为另一个数组。
        // 这样就把问题降低成了：“有一个数组每个数字都出现两次，有一个数字只出现了一次，求出该数字”。
        for (int num : nums) {
            if ((num & diff) != 0) {
                x ^= num;
            }
        }

        return new int[]{x, bitmask^x};
    }
}
