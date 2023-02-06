package com.answer.math;

import java.util.*;

public class Q786_Kth_Smallest_Prime_Fraction {
    public static void main(String[] args) {
        int[] arr = {1,2,3,5};
        int k = 3;
        System.out.println(Arrays.toString(kthSmallestPrimeFraction(arr, k)));
    }

    /**
     * Approach #2: Heap
     */
    public static int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int[] res = new int[2];
        int m = arr.length;

        PriorityQueue<int[]> heap = new PriorityQueue<int[]>((x, y) -> arr[x[0]] * arr[y[1]] - arr[y[0]] * arr[x[1]]);

        for(int i = 1; i < m; i++){
            heap.offer(new int[]{0, i});
        }

        while(k - 1 > 0 && !heap.isEmpty()){
            int[] pos = heap.poll();
            int x = pos[0];
            int y = pos[1];
            if(x < y - 1){
                heap.offer(new int[]{x + 1,pos[1]});
            }
            k--;
        }
        return new int[]{arr[heap.peek()[0]], arr[heap.peek()[1]]};
    }

}
