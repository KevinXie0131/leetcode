package com.answer.math;

import java.util.*;

public class Q373_Find_K_Pairs_with_Smallest_Sums {

    /**
     * PriorityQueue
     */
    public static void main(String[] args) {
        int[] nums1 = {1,2,4,5,6};
        int [] nums2 = {3,5,7,9};
        int k = 3;
        System.out.println(kSmallestPairs(nums1, nums2, k));
    }
    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        PriorityQueue<int[]> heap = new PriorityQueue<>(k, (o1, o2)->{
            return nums1[o1[0]] + nums2[o1[1]] - (nums1[o2[0]] +  nums2[o2[1]]);
        });
        int m = nums1.length;
        int n = nums2.length;
        for(int i = 0; i < Math.min(k, m); i++){
            heap.offer(new int[]{i, 0});
        }

        while(k > 0 && !heap.isEmpty()){
            int[] pos = heap.poll();
            res.add(Arrays.asList(nums1[pos[0]], nums2[pos[1]]));
            int index = pos[1];
            if(index < n - 1){
                heap.offer(new int[]{pos[0], index + 1});
            }
            k--;
        }
        return res;
    }
}
