package com.answer.priorityqueue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.*;

public class Offer_40_Least_K_Numbers {
    /**
     * 剑指 Offer 40 题目：「最小的k个数」
     * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
     * 示例 1：
     *  输入：arr = [3,2,1], k = 2
     *  输出：[1,2] 或 [2,1]
     * 示例 2：
     *  输入：arr = [0,1,2,1], k = 1
     *  输出：[0]
     * 限制：
     *  0 <= k <= arr.length <= 10000
     *  0 <= arr[i] <= 10000
     */
    public static void main(String[] args) {
        int[] arr = {3, 2, 3, 2, 2, 1, 1, 4, 4, 5};
        int k = 4;

        int[] result = getLeastNumbers(arr, k);
        System.out.println(Arrays.toString(result));
    }
    /**
     * 优先队列: 用一个大根堆实时维护数组的前 k 小值
     * 时间复杂度：O(nlogk)，其中 n 是数组 arr 的长度。由于大根堆实时维护前 k 小值，所以插入删除都是 O(logk) 的时间复杂度，最坏情况下数组里 n 个数都会插入
     * 空间复杂度：O(k)，因为大根堆里最多 k 个数
     */
    public static int[] getLeastNumbers(int[] arr, int k) {
        if(k == 0) return new int[]{};

        int[] result = new int[k];
      /*  PriorityQueue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>() {
            public int compare(Integer num1, Integer num2) {
                return num2 - num1;
            }
        });*/
      //  PriorityQueue<Integer> queue = new PriorityQueue<Integer>((a, b) -> b - a);
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < k; i++) {
            queue.offer(arr[i]); // 堆内元素不足 k 个：直接将 arr[i] 放入堆内
        }
        // 从第 k+1 个数开始遍历，如果当前遍历到的数比大根堆的堆顶的数要小，就把堆顶的数弹出，再插入当前遍历到的数。
        for (int i = k; i < arr.length; i++) {
            if (queue.peek() > arr[i]) {
                queue.poll();
                queue.offer(arr[i]);
            }
        }
        // 最后将大根堆里的数存入数组返回即可
        for (int i = k - 1; i >= 0; i--) {
            result[i] = queue.poll(); // 使用堆中元素「逆序」构造答案
        }
        return result;
    }
    /**
     * 优先队列（小根堆）
     * 一个直观的想法是使用「优先队列（小根堆）」，起始将所有元素放入堆中，然后再从堆中取出 k 个元素并「顺序」构造答案。
     * 时间复杂度：建堆复杂度为 O(nlogn)，构造答案复杂度为 O(klogn)。整体复杂度为 O(nlogn)
     * 空间复杂度：O(n+k)
     */
    public int[] smallestK(int[] arr, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> a - b);
        for (int i : arr){
            queue.add(i);
        }
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = queue.poll();
        }
        return ans;
    }
    /**
     * Treemap
     */
    public static int[] getLeastNumbers_1(int[] arr, int k) {
        if (k == 0 || arr.length == 0) {
            return new int[0];
        }
        // TreeMap的key是数字, value是该数字的个数。
        // cnt表示当前map总共存了多少个数字。
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int cnt = 0;
        for (int num : arr) {
            // 1. 遍历数组，若当前map中的数字个数小于k，则map中当前数字对应个数+1
            if (cnt < k) {
                map.put(num, map.getOrDefault(num, 0) + 1);
                cnt++;
                continue;
            }
            // 2. 否则，取出map中最大的Key（即最大的数字), 判断当前数字与map中最大数字的大小关系：
            //    若当前数字比map中最大的数字还大，就直接忽略；
            //    若当前数字比map中最大的数字小，则将当前数字加入map中，并将map中的最大数字的个数-1。
            Map.Entry<Integer, Integer> entry = map.lastEntry();
            if (entry.getKey() > num) {
                map.put(num, map.getOrDefault(num, 0) + 1);
                if (entry.getValue() == 1) {
                    map.pollLastEntry();
                } else {
                    map.put(entry.getKey(), entry.getValue() - 1);
                }
            }

        }
        // 最后返回map中的元素
        int[] res = new int[k];
        int idx = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int freq = entry.getValue();
            while (freq-- > 0) {
                res[idx++] = entry.getKey();
            }
        }
        return res;
    }
    /**
     * use array as counter
     */
    public static int[] getLeastNumbers_2(int[] arr, int k) {
        if (k == 0 || arr.length == 0) {
            return new int[0];
        }
        // 统计每个数字出现的次数
        int[] counter = new int[10001];
        for (int num : arr) {
            counter[num]++;
        }
        // 根据counter数组从头找出k个数作为返回结果
        int[] res = new int[k];
        int idx = 0;
        for (int num = 0; num < counter.length; num++) {
           /* while (counter[num]-- > 0 && idx < k) {
                res[idx++] = num;
            }*/
            while (counter[num] > 0 && idx < k) {
                counter[num]--;
                res[idx] = num;
                idx++;
            }
            if (idx == k) {
                break;
            }
        }
        return res;
    }
}
