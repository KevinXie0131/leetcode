package com.answer.priorityqueue;

import java.util.*;

public class Q347_Top_K_Frequent_Elements {

    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3};
        int k = 2;

        int[] result = topKFrequent(nums, k);
        System.out.println(Arrays.toString(result));
    }

    public static int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();

        /**
         * 根据map的value值，构建于一个大顶堆（o1 - o2: 小顶堆， o2 - o1 : 大顶堆）
         */
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((o1, o2) -> o2.getValue() - o1.getValue());

        for(Map.Entry<Integer, Integer> entry : entries){
            queue.offer(entry);
        }

        for(int i = k-1; i >= 0; i--){
            result[i] = queue.poll().getKey();
        }

        return result;
    }
}
