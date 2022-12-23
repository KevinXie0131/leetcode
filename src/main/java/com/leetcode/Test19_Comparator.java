package com.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Test19_Comparator {

    public static void main(String[] args) {

        Integer[] arrays = new Integer[]{3, 5, 7, 1, 9, 0};
        Arrays.sort(arrays, new java.util.Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
              //  return o1 - o2;
                return o2 - o1;
            }
        });

        System.out.println(Arrays.toString(arrays));

        PriorityQueue<Integer> heap = new PriorityQueue(arrays.length, new java.util.Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
              //  return o1 - o2;
                return o2 - o1;
            }
        });
        heap.offer(3);
        heap.offer(5);
        heap.offer(1);
        heap.offer(0);
        System.out.println(heap.poll());
        System.out.println(heap.poll());
        System.out.println(heap.poll());
        System.out.println(heap.poll());
    }

}
