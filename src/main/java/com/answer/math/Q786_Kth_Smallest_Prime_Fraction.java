package com.answer.math;

import java.util.*;

public class Q786_Kth_Smallest_Prime_Fraction {
    public static void main(String[] args) {
        int[] arr = {1,2,3,5};
        int k = 3;
        System.out.println(Arrays.toString(kthSmallestPrimeFraction(arr, k)));
    }

    /**
     * Approach #2: Heap - 多路归并
     * Time: O(klogn)
     * Space: O(n)
     */
    public static int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int[] res = new int[2];
        int m = arr.length;

        PriorityQueue<int[]> heap = new PriorityQueue<int[]>((x, y) -> arr[x[0]] * arr[y[1]] - arr[y[0]] * arr[x[1]]);
        /**
         * PriorityQueue<int[]> q = new PriorityQueue<>((a,b)->Double.compare(b[0]*1.0/b[1],a[0]*1.0/a[1]));
         */
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
    /**
     * Approach #1: Binary Search
     */
    double eps = 1e-8;

    public int[] kthSmallestPrimeFraction_2(int[] arr, int k) {
        int n = arr.length;
        double left = 0;
        double right = 1;
        int[] ans = new int[2];
        while (right - left > eps) {
            double mid = (left + right) / 2;
            if (check(arr, mid, ans) >= k) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return ans;
    }

    int check(int[] arr, double val, int[] ans) {
        int count = 0;
        int n = arr.length;
        for (int i = 0, j = 1; j < n; j++) {
            while (arr[i + 1] * 1.0 / arr[j] <= val) {
                i++;
            }
            if (arr[i] * 1.0 / arr[j] <= val) {
                count += i + 1;
            }
            if (Math.abs(arr[i] * 1.0 / arr[j] - val) < eps) {
                ans[0] = arr[i];
                ans[1] = arr[j];
            }
        }
        return count;
    }
}
