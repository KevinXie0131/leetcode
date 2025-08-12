package com.answer.math;

import java.util.Arrays;
import java.util.*;

public class Q719_Find_Kth_Smallest_Pair_Distance {
    /**
     * The distance of a pair of integers a and b is defined as the absolute difference between a and b.
     * Given an integer array nums and an integer k, return the kth smallest distance among all the pairs
     * nums[i] and nums[j] where 0 <= i < j < nums.length.
     * 找出第 K 小的数对距离
     * 数对 (a,b) 由整数 a 和 b 组成，其数对距离定义为 a 和 b 的绝对差值。
     * 给你一个整数数组 nums 和一个整数 k ，数对由 nums[i] 和 nums[j] 组成且满足 0 <= i < j < nums.length
     * 返回 所有数对距离中 第 k 小的数对距离。
     *
     * 「第 k 小的距离对」不是「第 k 小的不同距离对」：相同的距离要参与排序，相同的距离不能合并成一项
     */
    public static void main(String[] args) { // Hard 困难
        /**
         * 数对和对应的距离如下：
         * (1,3) -> 2
         * (1,1) -> 0
         * (3,1) -> 2
         * 距离第 1 小的数对是 (1,1) ，距离为 0 。
         */
        int[] nums = {1,6,1};
        int k = 3;
        System.out.println(smallestDistancePair_5(nums, k));
    }
    /**
     * 二分查找 + 滑动窗口 + 排序
     *   排序数组：首先对数组进行排序，以便更容易计算数对距离。
     *   确定二分搜索范围：数对距离的最小值是0,最大值是数组中最大元素与最小元素的差值。
     * 二分查找：
     *   在最小值和最大值之间进行二分查找，找到第 k小的数对距离。
     *   计算小于等于 mid的数对距离个数：对于每个中间值mid，计算数组中数对距离小于等于mid的个数。如果个数小于k，则第k小的数对距离在 mid的右侧；否则在左侧。
     */
    // Main method to find the k-th smallest pair distance
    static public int smallestDistancePair_0(int[] nums, int k) {
        Arrays.sort(nums); // Sort the array first

        int left = 0;
        int right = nums[nums.length - 1] - nums[0];

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int count = countPairs(nums, mid);

            if (count < k) {  // 如果小于等于 mid 的个数严格小于 k 个，说明 mid 太小了
                left = mid + 1;      // 下一轮搜索区间为 [mid + 1..right]
            } else {
                // res = mid; // works too
                right = mid - 1;// 下一轮搜索区间为 [left..mid]
            }
        }
        return left;
        //  return right + 1; // works too
    }
    // Helper method to count the number of pairs with distance <= mid
    // 统计距离（数值之差的绝对值）小于等于 threshold 的个数
    static private int countPairs(int[] nums, int threshold ) {
        int count = 0;
        int left = 0;
        // 在排好序以后的数组上是连续分布的。因此，可以使用「滑动窗口」在排好序的数组上滑动, 数出所有的小于等于某个数值的对数的个数。
        for (int right = 1; right < nums.length; ++right) {
            while (nums[right] - nums[left] > threshold ) {
                ++left;
            }
            // 此时满足 nums[right] - nums[left] <= threshold
            // right 与 [left..right - 1] 里的每一个元素的「距离」都小于等于 threshold
            // [left..right - 1] 里元素的个数为 right - left
            count += (right - left);
        }
        return count;
    }
    /**
     * Approach #1: Heap [Time Limit Exceeded]
     */
    public static int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        PriorityQueue<Node> heap = new PriorityQueue<Node>(nums.length,
                Comparator.<Node> comparingInt(node -> nums[node.nei] - nums[node.root]));
        for (int i = 0; i + 1 < nums.length; ++i) {
            heap.offer(new Node(i, i + 1));
        }

        Node node = null;
        for (; k > 0; --k) {
            node = heap.poll();
            if (node.nei + 1 < nums.length) {
                heap.offer(new Node(node.root, node.nei + 1));
            }
        }
        return nums[node.nei] - nums[node.root];
    }
    /**
     * 同上 PriorityQueue
     * Time Limit Exceeded
     * 19 / 20 testcases passed
     */
    public static int smallestDistancePair_5(int[] nums, int k) {
        Arrays.sort(nums);
        PriorityQueue<int[]> heap = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);

        for (int i = 0; i + 1 < nums.length; ++i) {
            heap.offer(new int[]{nums[i + 1] - nums[i], i, i + 1});
        }

        k--;
        int[] last = new int[]{0, 0, 0};
        while(k >= 0){
            last = heap.poll();
            if (last[2] + 1 < nums.length) {
                heap.offer(new int[]{nums[last[2] + 1] - nums[last[1]], last[1], last[2] + 1});
            }
            k--;
        }
        if(k > 0) last = heap.poll();
        return last[0];
    }
    /**
     * Binary Search 同上
     */
    public static int smallestDistancePair_1(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int left = 0, right = nums[n - 1] - nums[0];
        while(left <= right){
            int mid = (left + right) >>> 1;
            if(getCount(mid, nums) < k){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return left;
    }

    public static int getCount(int dis, int[] nums){
        int count = 0;
        int l = 0;
        for(int r = 0; r < nums.length; r++){
            while(nums[r] - nums[l] > dis){
                l++;
            }
            count += r - l;
        }
        return count;
    }
}

class Node {
    int root;
    int nei;
    Node(int r, int n) {
        root = r;
        nei = n;
    }
}
