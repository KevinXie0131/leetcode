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
        System.out.println(kSmallestPairs_1(nums1, nums2, k));
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
    /**
     * Binary Search
     */
    static int[] nums1, nums2;
    static int n, m;
    public static List<List<Integer>> kSmallestPairs_1(int[] n1, int[] n2, int k) {
        nums1 = n1; nums2 = n2;
        n = nums1.length; m = nums2.length;
        List<List<Integer>> ans = new ArrayList<>();
        int l = nums1[0] + nums2[0];
        int r = nums1[n - 1] + nums2[m - 1];
        while (l < r) {
            int mid = (int)(0L + l + r >> 1);
            if (check(mid, k)) {
                r = mid;
            }else{
                l = mid + 1;
            }
        }
        int x = r;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (nums1[i] + nums2[j] < x) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums1[i]);
                    temp.add(nums2[j]);
                    ans.add(temp);
                } else {
                    break;
                }
            }
        }
        for (int i = 0; i < n && ans.size() < k; i++) {
            int a = nums1[i], b = x - a;
            int c = -1, d = -1;
            l = 0; r = m - 1;
            while (l < r) {
                int mid = (int)(0L + l + r >> 1);
                if (nums2[mid] >= b) {
                    r = mid;
                }else{
                    l = mid + 1;
                }
            }
            if (nums2[r] != b) {
                continue;
            }
            c = r;
            l = 0; r = m - 1;
            while (l < r) {
                int mid = (int)(0L + l + r + 1) >> 1;
                if (nums2[mid] <= b) {
                    l = mid;
                }else{
                    r = mid - 1;
                }
            }
            d = r;
            for (int p = c; p <= d && ans.size() < k; p++) {
                List<Integer> temp = new ArrayList<>();
                temp.add(a);
                temp.add(b);
                ans.add(temp);
            }
        }
        return ans;
    }
    static boolean check(int x, int k) {
        int ans = 0;
        for (int i = 0; i < n && ans < k; i++) {
            for (int j = 0; j < m && ans < k; j++) {
                if (nums1[i] + nums2[j] <= x) {
                    ans++;
                }else{
                    break;
                }
            }
        }
        return ans >= k;
    }
}
