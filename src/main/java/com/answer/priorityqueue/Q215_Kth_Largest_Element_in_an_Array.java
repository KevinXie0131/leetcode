package com.answer.priorityqueue;

import java.util.*;

public class Q215_Kth_Largest_Element_in_an_Array {
    /**
     * 数组中的第K个最大元素
     * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
     * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
     * Given an integer array nums and an integer k, return the kth largest element in the array.
     * Note that it is the kth largest element in the sorted order, not the kth distinct element.
     * Can you solve it without sorting?
     * 示例 1:
     *  输入: [3,2,1,5,6,4], k = 2
     *  输出: 5
     * 示例 2:
     *  输入: [3,2,3,1,2,4,5,5,6], k = 4
     *  输出: 4
     */
    public static void main(String[] args) {
        int[] nums = {3,2,1,5,6,4};
   //     int[] nums = {-1,2,0};
        int k = 2;

        System.out.println(findKthLargest1(nums, k));
    }
    /**
     * 排序法  时间复杂度是 O(nlogn)
     */
    public int findKthLargest_0(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[ nums.length - k];
    }
    /**
     * 最大堆  时间复杂度是O(NlogN)
     */
    static public int findKthLargest1(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue(Collections.reverseOrder());
        for(int i = 0; i < nums.length; i++){
            queue.offer(nums[i]);
        }
        for(int i = 0; i < k - 1; i++){
            queue.poll();
        }
        return queue.poll();
    }
    /**
     * 最小堆
     */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue();

        for(int i = 0; i < nums.length; i++){
            queue.offer(nums[i]);
        }

        for(int i = 0; i < nums.length - k; i++){
            queue.poll();
        }
        return queue.poll(); // 第k个最大的数值
    }
    /**
     * 最小堆 优化
     *
     * 维护一个大小为 k 的最小堆；
     * 遍历数组，当堆的大小小于 k 时，直接加入堆；
     * 当堆已满时，比较当前元素与堆顶元素：如果当前元素大于堆顶，弹出堆顶并加入当前元素，否则跳过；
     * 最后堆顶元素就是第 k 大的元素。
     *
     * 时间复杂度：O(nlogk)，每个元素最多进行一次堆操作；
     * 空间复杂度：O(k)，「优先队列」的大小。
     */
    public static int findKthLargest_1(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue();

        for(int i = 0; i < k; i++){
            queue.offer(nums[i]);
        }
        // 在元素入堆的过程中，不断淘汰最小值，最终留在堆中就是数组中前 k 个最大元素，并且堆顶元素为前 k 大元素中的最小值，即为第 k 个元素
        for(int i = k; i < nums.length; i++){
            if(nums[i] > queue.peek()){ // 只要当前遍历的元素比堆顶元素大，堆顶弹出，遍历的元素进去
                queue.poll();   // Java 没有 replace()，所以得先 poll() 出来，然后再放回去
                queue.offer(nums[i]);
            }
        }
        return queue.peek();
    }
    /**
     * 最小堆 优化 一次遍历
     */
    public static int findKthLargest_2(int[] nums, int k) {
        // 使用一个含有 k 个元素的最小堆，PriorityQueue 底层是动态数组，为了防止数组扩容产生消耗，可以先指定数组的长度
        PriorityQueue<Integer> queue = new PriorityQueue(k);

        for(int i = 0; i < nums.length; i++){
            if(queue.size() < k){
                queue.offer(nums[i]);
            } else{
                if(nums[i] > queue.peek()){
                    queue.poll();
                    queue.offer(nums[i]);
                }
            }
        }
        return queue.peek();
    }
    /**
     * 最小堆
     */
    public int findKthLargest_4(int[] nums, int k) {
        // init heap 'the smallest element first'
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>((n1, n2) -> n1 - n2);
        // keep k largest elements in the heap
        for (int n: nums) {
            heap.offer(n);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        // output
        return heap.peek();
    }
    /**
     * 基于快速排序的选择方法
     * 时间复杂度：O(n) 空间复杂度：O(logn)，递归使用栈空间的空间代价的期望为 O(logn)
     */
    public int findKthLargest6(int[] _nums, int k) {
        int n = _nums.length;
        return quickselect(_nums, 0, n - 1, n - k);
    }

    int quickselect(int[] nums, int l, int r, int k) {
        if (l == r) return nums[k];
        int x = nums[l], i = l - 1, j = r + 1;
        while (i < j) {
            do i++; while (nums[i] < x);
            do j--; while (nums[j] > x);
            if (i < j){ // 调整子数组的元素使得左边的元素都小于等于它，右边的元素都大于等于它
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
        }
        if (k <= j){
            return quickselect(nums, l, j, k); // 只要某次划分的 q 为倒数第 k 个下标的时候，我们就已经找到了答案
        } else {
            return quickselect(nums, j + 1, r, k);
        }
    }

}
