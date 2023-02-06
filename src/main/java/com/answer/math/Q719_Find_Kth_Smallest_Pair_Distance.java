package com.answer.math;

import java.util.Arrays;
import java.util.*;

public class Q719_Find_Kth_Smallest_Pair_Distance {
    public static void main(String[] args) {
        int[] nums = {1,3,1};
        int k = 1;
        System.out.println(smallestDistancePair_1(nums, k));
    }
    /**
     * Approach #1: Heap [Time Limit Exceeded]
     */
    public static int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        PriorityQueue<Node> heap = new PriorityQueue<Node>(nums.length,
                Comparator.<Node> comparingInt(node -> nums[node.nei] - nums[node.root]));
        for (int i = 0; i + 1 < nums.length; ++i) {
            heap.offer(new Node(i, i+1));
        }

        Node node = null;
        for (; k > 0; --k) {
            node = heap.poll();
            if (node.nei + 1 < nums.length) {
                heap.offer(new Node(node.root, node.nei + 1));
            }
        }
        return nums[node.nei] - nums[node.root];
    }
    /**
     * Binary Search
     */
    public static int smallestDistancePair_1(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int left = 0, right = nums[n - 1] - nums[0];
        while(left <= right){
            int mid = (left + right) >>> 1;
            if(getCount(mid, nums) < k){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return left;
    }
    public static int getCount(int dis, int[] nums){
        int count = 0;
        int l = 0;
        for(int r = 0; r < nums.length; r++){
            while(nums[r] - nums[l] > dis){
                l++;
            }
            count += r - l;
        }
        return count;
    }
}

class Node {
    int root;
    int nei;
    Node(int r, int n) {
        root = r;
        nei = n;
    }
}
