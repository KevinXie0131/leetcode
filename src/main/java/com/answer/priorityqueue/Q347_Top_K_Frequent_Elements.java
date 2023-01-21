package com.answer.priorityqueue;

import java.util.*;
import java.util.stream.*;

public class Q347_Top_K_Frequent_Elements {

    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3,4,4,4,5,5,5};
        int k = 2;

        int[] result = topKFrequent_2(nums, k);
        System.out.println(Arrays.toString(result));
    }

    /**
     * 大顶堆 max heap
     */
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
    /**
     * 小顶堆 min heap
     */
    public static int[] topKFrequent_1(int[] nums, int k) {
        int[] result = new int[k];
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();

        /**
         * 根据map的value值，构建于一个大顶堆（o1 - o2: 小顶堆， o2 - o1 : 大顶堆）
         */
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((o1, o2) -> o1.getValue() - o2.getValue());

        for(Map.Entry<Integer, Integer> entry : entries){
            if(queue.size() == k){
                if(queue.peek().getValue() < entry.getValue()){
                    queue.poll();
                    queue.offer(entry);
                }
            } else {
                queue.offer(entry);
            }

        }

        for(int i = k-1; i >= 0; i--){
            result[i] = queue.poll().getKey();
        }

        return result;
    }

    /**
     * TreeMap
     */
    public static int[] topKFrequent_2(int[] nums, int k) {
        // 统计每个数字出现的次数
        Map<Integer, Integer> counterMap = IntStream.of(nums).boxed().collect(Collectors.toMap(e -> e, e -> 1, Integer::sum));
        // 定义二叉搜索树：key 是数字出现的次数，value 是出现了key次的数字列表。
        TreeMap<Integer, List<Integer>> treeMap = new TreeMap<>();
        // 维护一个有 k 个数字的二叉搜索树：
        // 不足 k 个直接将当前数字加入到树中；否则判断当前树中的最小次数是否小于当前数字的出现次数，若是，则删掉树中出现次数最少的一个数字，将当前数字加入树中。
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : counterMap.entrySet()) {
            int num = entry.getKey();
            int cnt = entry.getValue();
            if (count < k) {
                treeMap.computeIfAbsent(cnt, ArrayList::new).add(num);
                count++;
            } else {
                Map.Entry<Integer, List<Integer>> firstEntry = treeMap.firstEntry();
                if (cnt > firstEntry.getKey()) {
                    treeMap.computeIfAbsent(cnt, ArrayList::new).add(num);
                    List<Integer> list = firstEntry.getValue();
                    if (list.size() == 1) {
                        treeMap.pollFirstEntry();
                    } else {
                        list.remove(list.size() - 1);
                    }
                }
            }
        }
        // 构造返回结果
        int[] res = new int[k];
        int idx = 0;
        for (List<Integer> list : treeMap.values()) {
            for (int num : list) {
                res[idx++] = num;
            }
        }
        return res;
    }
}
