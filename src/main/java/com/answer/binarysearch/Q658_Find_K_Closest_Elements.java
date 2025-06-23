package com.answer.binarysearch;

import java.util.*;

public class Q658_Find_K_Closest_Elements {
    /**
     * 找到 K 个最接近的元素
     * 给定一个 排序好 的数组 arr ，两个整数 k 和 x ，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。
     * iven a sorted integer array arr, two integers k and x, return the k closest integers to x in the array.
     * The result should also be sorted in ascending order.
     * 整数 a 比整数 b 更接近 x 需要满足：
     * An integer a is closer to x than an integer b if:
     *   |a - x| < |b - x| 或者
     *   |a - x| == |b - x| 且 a < b
     * 示例 1：
     *  输入：arr = [1,2,3,4,5], k = 4, x = 3
     *  输出：[1,2,3,4]
     * 示例 2：
     *  输入：arr = [1,1,2,3,4,5], k = 4, x = -1
     *  输出：[1,1,2,3]
     *
     */
    /**
     * Two pointers
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int len = arr.length;
        int left = 0;
        int right = len - 1;
        int count = len - k;

        while(count > 0){
            if(x - arr[left] <= arr[right] - x){
                right--;
            }else{
                left++;
            }
            count--;
        }

        List<Integer> res = new ArrayList<Integer>();
        for(int i = left; i <= right; i++){
            res.add(arr[i]);
        }
        return res;
    }
    /**
     * Approach 1: Sort With Custom Comparator
     */
    public List<Integer> findClosestElements_1(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<Integer>();
        for(int i = 0; i <= arr.length - 1; i++){
            res.add(arr[i]);
        }

        Collections.sort(res, (a, b) -> {
            if (Math.abs(a - x) != Math.abs(b - x)) {
                return Math.abs(a - x) - Math.abs(b - x);
            } else {
                return a - b;
            }
        });
        /**
         *  Collections.sort(sortedArr, (num1, num2) -> Math.abs(num1 - x) - Math.abs(num2 - x));
         */
        List<Integer> list = res.subList(0, k);
        Collections.sort(list);
        return list;
    }
    /**
     * Approach 3: Binary Search To Find The Left Bound
     *If the element at arr[mid] is closer to x, then move the right pointer. If the element at arr[mid + k] is closer to x
     * At the end of the binary search, we have located the leftmost index for the final answer. Return the subarray starting at this index that contains k elements.
     */
    public List<Integer> findClosestElements_2(int[] arr, int k, int x) {
        List<Integer> list = new ArrayList<>();
        int n = arr.length;
        int left = 0;
        int right = n - k;
        while(left <= right){
            int mid = (left + right) >>> 1;
            if(mid + k < n && x - arr[mid] > arr[mid + k] - x){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        for (int i = left; i < left + k; i++) {
            list.add(arr[i]);
        }
        return list;
    }
    /**
     * Approach 2: Binary Search + Sliding Window
     */
    public List<Integer> findClosestElements_3(int[] arr, int k, int x) {
        List<Integer> list = new ArrayList<>();
        int n = arr.length;
        int left = 0;
        int right = 0;
        int sum = 0;
        int res = Integer.MAX_VALUE;
        int idx = 0;
        while (right < n) {
            sum += Math.abs(arr[right] - x);
            if (right - left + 1 == k) {
                if (sum < res) {
                    res = sum;
                    idx = left;
                }
                sum -= Math.abs(arr[left] - x);
                left++;
            }
            right++;
        }

        for (int i = idx; i < idx + k; i++) {
            list.add(arr[i]);
        }
        return list;
    }

    /**
     * Another form of sliding window
     */
    public List<Integer> findClosestElements_4(int[] arr, int k, int x) {
        List<Integer> result = new ArrayList<Integer>();

        // Base case
        if (arr.length == k) {
            for (int i = 0; i < k; i++) {
                result.add(arr[i]);
            }

            return result;
        }

        // Binary search to find the closest element
        int left = 0;
        int right = arr.length;
        int mid = 0;
        while (left < right) {
            mid = (left + right) / 2;
            if (arr[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        // Initialize our sliding window's bounds
        left -= 1;
        right = left + 1;

        // While the window size is less than k
        while (right - left - 1 < k) {
            // Be careful to not go out of bounds
            if (left == -1) {
                right += 1;
                continue;
            }

            // Expand the window towards the side with the closer number
            // Be careful to not go out of bounds with the pointers
            if (right == arr.length || Math.abs(arr[left] - x) <= Math.abs(arr[right] - x)) {
                left -= 1;
            } else {
                right += 1;
            }
        }

        // Build and return the window
        for (int i = left + 1; i < right; i++) {
            result.add(arr[i]);
        }

        return result;
    }
}
