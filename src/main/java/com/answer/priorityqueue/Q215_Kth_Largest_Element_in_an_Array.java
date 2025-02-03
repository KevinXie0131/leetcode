package com.answer.priorityqueue;

import java.util.*;

public class Q215_Kth_Largest_Element_in_an_Array {

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
     * 最小堆 优化 时间复杂度是O(NlogK)
     */
    public static int findKthLargest_1(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue();

        for(int i = 0; i < k; i++){
            queue.offer(nums[i]);
        }

        for(int i = k; i < nums.length; i++){
            if(nums[i] > queue.peek()){
                queue.poll();
                queue.offer(nums[i]);
            }
        }
        return queue.peek();
    }
    /**
     * 最小堆 优化 一次遍历
     */
    public static int findKthLargest_2(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue();

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
            if (heap.size() > k)
                heap.poll();
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
        if (k <= j) return quickselect(nums, l, j, k); // 只要某次划分的 q 为倒数第 k 个下标的时候，我们就已经找到了答案
        else return quickselect(nums, j + 1, r, k);
    }

}
