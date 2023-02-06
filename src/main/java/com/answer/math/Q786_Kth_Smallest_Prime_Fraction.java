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
     * Time: O(klogn)
     * Space: O(n)
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
    /**
     * Sorting
     */
    public int[] kthSmallestPrimeFraction_1(int[] arr, int k) {
        int m = arr.length;
        List<int[]> frac = new ArrayList<int[]>();
        for(int i = 0; i < m; i++){
            for(int j = i + 1;j < m;j++){
                frac.add(new int[]{arr[i], arr[j]});
            }
        }
        Collections.sort(frac, (x,y)->x[0]*y[1] -x[1]*y[0]);
        return frac.get(k-1);
    }
}
