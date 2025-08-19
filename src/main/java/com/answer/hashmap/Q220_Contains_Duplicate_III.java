package com.answer.hashmap;

import java.util.*;

public class Q220_Contains_Duplicate_III { // Hard 困难
    /**
     * 存在重复元素 III
     * 给你一个整数数组 nums 和两个整数 indexDiff 和 valueDiff 。
     * 找出满足下述条件的下标对 (i, j)：
     *  i != j,
     *  abs(i - j) <= indexDiff
     *  abs(nums[i] - nums[j]) <= valueDiff
     * 如果存在，返回 true ；否则，返回 false 。
     *
     * 示例 1：
     *  输入：nums = [1,2,3,1], indexDiff = 3, valueDiff = 0
     *  输出：true
     *  解释：可以找出 (i, j) = (0, 3) 。
     *      满足下述 3 个条件：
     *      i != j --> 0 != 3
     *      abs(i - j) <= indexDiff --> abs(0 - 3) <= 3
     *      abs(nums[i] - nums[j]) <= valueDiff --> abs(1 - 1) <= 0
     * 示例 2：
     *  输入：nums = [1,5,9,1,5,9], indexDiff = 2, valueDiff = 3
     *  输出：false
     *  解释：尝试所有可能的下标对 (i, j) ，均无法满足这 3 个条件，因此返回 false 。
     */
    /**
     * 滑动窗口 & 二分
     * 时间复杂度：TreeSet 基于红黑树，查找和插入都是 O(logk) 复杂度。整体复杂度为 O(nlogk)
     * 空间复杂度：O(k)
     *
     * 题意：数组中是否存在一个大小不超过 k 的子数组，该子数组内的最大值和最小值的差不超过 t。
     * 目的是快速让一组数据有序，那就寻找一个内部是有序的数据结构
     * 1. right 指针每次后移，如果发现 set 的大小大于 k ，则需要把 nums[left] 从 set 中删除；
     * 2. 查找 set 中是否有大于等于 nums[right] - t 的元素，如果有的话，说明在大小不超过为 k 的窗口内有绝对值差小于等于 t 的两个元素，返回 true。
     * 3. 如果把 nums 遍历了一遍仍然没有结果，则返回 false
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int n = nums.length;
        TreeSet<Long> ts = new TreeSet<>(); // 由于 nums 中的数较大，会存在 int 溢出问题，我们需要使用 long 来存储
        for (int i = 0; i < n; i++) {
            Long u = nums[i] * 1L;
            Long l = ts.floor(u);// 从 ts 中找到小于等于 u 的最大值（小于等于 u 的最接近 u 的数）
            Long r = ts.ceiling(u);   // 从 ts 中找到大于等于 u 的最小值（大于等于 u 的最接近 u 的数）
            if(l != null && u - l <= t) {
                return true;
            }
            if(r != null && r - u <= t) {
                return true;
            }

            ts.add(u);     // 将当前数加到 ts 中，并移除下标范围不在 [max(0, i - k), i) 的数（维持滑动窗口大小为 k）
            if (i >= k) {
                ts.remove(nums[i - k] * 1L);
            }
        }
        return false;
    }
    /**
     * 滑动窗口 + 有序集合 Approach #2 (Binary Search Tree)
     */
    public boolean containsNearbyAlmostDuplicate2(int[] nums, int indexDiff, int valueDiff) {
        TreeSet<Integer> set = new TreeSet<>();
        for(int i = 0 ; i < nums.length; i++){
            if(set.ceiling(nums[i])!= null && set.ceiling(nums[i]) <= nums[i] + valueDiff){
                return true;
            }
            if(set.floor(nums[i]) != null && set.floor(nums[i]) >= nums[i] - valueDiff){
                return true;
            }
            set.add(nums[i]);
            if(set.size() > indexDiff){
                set.remove(nums[i - indexDiff]);
            }
        }
        return false;
    }
    /**
     * 桶排序
     * abs(nums[i] - nums[j]) <= t，假设t = 2，则差的绝对值可以是0，1，2
     *
     * 因此坐标计算 inx， 当 x >= 0 ： x / (t + 1)
     * 当 x < 0：( x + 1 ) / (t + 1) - 1
     * 为了把某（t + 1）个数正确映射到某个容量范围的桶
     * 如 0， 1， 2，除以容量3，得到 0
     * 而 -1， -2， -3 除以3则有-1和0，出现错误，因此，
     * 把负数统一加一得到 0，-1，-2， 此时除以3是0，也出错，但若再减一则是-1，正确
     *
     * abs (i - j) <= k是桶的数量，即坐标差绝对值
     */
    long size;

    public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
        int n = nums.length;
        Map<Long, Long> map = new HashMap<>();  //桶容量
        size = t + 1L;
        for (int i = 0; i < n; i++) {
            long u = nums[i] * 1L;
            long idx = getIdx(u);
            // 由上述方法计算的桶坐标
            // 目标桶已存在（桶不为空），说明前面已有 [u - t, u + t] 范围的数字
            if (map.containsKey(idx)) {
                return true;     //当桶坐标存在，说明已经有此桶范围内的数，满足题意，差值绝对值 <= t，坐标差值为0，返回true
            }
            // 检查相邻的桶
            long l = idx - 1, r = idx + 1;
            if (map.containsKey(l) && u - map.get(l) <= t) {
                return true; // 根据算法，左边数肯定小于右边数，因此调整减数与被减数，无需绝对值. 左桶存在，说明在2t范围内 [0, 2t]可能有 [0,t]差值的数，于是相减判断，若有则返回
            }
            if (map.containsKey(r) && map.get(r) - u <= t){
                return true; //右桶桶左桶
            }
            // 建立目标桶
            map.put(idx, u);
            // 移除下标范围不在 [max(0, i - k), i) 内的桶
            if (i >= k) { // 桶数量，因为 i 从0递增，当 i == k - 1时刚好k个桶，坐标差绝对值符合题意，大于等于则不符合题意，需要将最左边，即数组坐标为i - k的数所在的桶移除
                map.remove(getIdx(nums[i - k] * 1L));
            }
        }
        return false; // 遍历结束仍没有找到，返回false
    }

    long getIdx(long u) {
        return u >= 0 ? u / size : ((u + 1) / size) - 1;
    }
}
