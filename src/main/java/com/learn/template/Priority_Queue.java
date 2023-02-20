package com.learn.template;

import java.util.*;

public class Priority_Queue {

    private static final int CRITERIA = 1;

    /**
     * 找到堆的前 k 个元素
     */
    public int[] fn(int[] arr, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(CRITERIA);
        for (int num: arr) {
            heap.add(num);
            if (heap.size() > k) {
                heap.remove();
            }
        }

        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = heap.remove();
        }

        return ans;
    }
}
