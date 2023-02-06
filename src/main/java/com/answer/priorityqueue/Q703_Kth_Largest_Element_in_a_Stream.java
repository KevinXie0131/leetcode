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
     * Approach: Heap
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
}
