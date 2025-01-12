package com.answer.priorityqueue;

import java.util.*;

public class Q703_Kth_Largest_Element_in_a_Stream {
    public static void main(String[] args) {
        Q703_Kth_Largest_Element_in_a_Stream object = new Q703_Kth_Largest_Element_in_a_Stream(3, new int[]{4,5,8,2});
        System.out.println( object.add(3));
        System.out.println( object.add(5));
        System.out.println( object.add(10));
        System.out.println( object.add(9));
        System.out.println( object.add(4));
    }

    /**
     * Approach: Heap - use a min-heap (min means that the heap will remove/find the smallest element, a max heap is the same thing but for the largest element)
     * Time:
     *   initial: O(nlogk)
     *   add: O(logk)
     * Space: O(k)
     * 为什么使用小根堆？
     * 因为我们需要在堆中保留数据流中的前K大元素，使用小根堆能保证每次调用堆的 pop() 函数时，从堆中删除的是堆中的最小的元素（堆顶）
     *
     * 时间复杂度： 初始化时间复杂度为：O(nlogk) ，其中 n 为初始化时 nums 的长度；
     *             单次插入时间复杂度为：O(logk)。
     * 空间复杂度：O(k)。需要使用优先队列存储前 k 大的元素
     */
    PriorityQueue<Integer> heap = null;
    int k = 0;

    public Q703_Kth_Largest_Element_in_a_Stream(int k, int[] nums) {
        this.heap = new PriorityQueue<>();
        this.k = k;

        for(int n : nums){
            heap.offer(n);
        }
    }

    public int add(int val) {
        this.heap.offer(val);
        while(this.heap.size() > this.k){
            this.heap.poll();
        }
        return this.heap.peek();
    }
    /**
     * 另一种形式 时间复杂度： 初始化时间复杂度为：O(klogk)
     */
    public void KthLargest1(int k, int[] nums) {
        this.heap = new PriorityQueue<>();
        this.k = k;

        for(int n : nums){
            if( this.heap.size() < k){
                this.heap.offer(n);
            }
            else if(this.heap.peek() < n){
                this.heap.poll();
                this.heap.offer(n);
            }
        }
    }
    public int add1(int val) {
        if( this.heap.size() < k){
            this.heap.offer(val);
        }
        else if(this.heap.peek() < val){
            this.heap.poll();
            this.heap.offer(val);
        }
        return this.heap.peek();
    }
}
