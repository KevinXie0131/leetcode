package com.answer.priorityqueue;

import java.util.*;

public class Q692_Top_K_Frequent_Words {
    public static void main(String[] args) {
        String[] words = {"the","day","is","sunny","the","the","the","sunny","is","is"};
        int k = 4;
        List<String> list = topKFrequent_2(words, k);
        System.out.println(list);
    }
    /**
     * Use Hashmap
     */
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new TreeMap<String, Integer>();
        List<String> list = new ArrayList<>();

        for(String s : words){
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        for(Map.Entry<String, Integer> entry : map.entrySet()){
            list.add(entry.getKey());
        }

        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return map.get(o1) == map.get(o2) ? o1.compareTo(o2) : map.get(o2) - map.get(o1);
            }
        });
/*        candidates.sort((a, b) -> {
            // 字符串频率相等按照字典序比较使得大的在堆顶,Java 可以直接使用 compareTo 方法即可。
            if (count.get(a).equals(count.get(b))) {
                return a.compareTo(b);
            } else {
                // 字符串频率不等则按照频率排列。
                return count.get(b) - count.get(a);
            }
        });*/
        return list.subList(0, k);
    }

    /**
     * Use PriorityQueue - Min heap
     */
    public static List<String> topKFrequent_1(String[] words, int k) {
        Map<String, Integer> map = new TreeMap<String, Integer>();
        List<String> list = new ArrayList<>();

        for(String s : words){
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        /**
         * Min Heap
         */
        PriorityQueue<Map.Entry<String, Integer>> queue = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue() == o2.getValue() ? o2.getKey().compareTo(o1.getKey()) : o1.getValue() - o2.getValue();
            }
        });
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            queue.offer(entry);
            if(queue.size() > k){
               queue.poll();
            }
        }
        while(!queue.isEmpty()){
            list.add(queue.poll().getKey());
        }
        Collections.reverse(list);
        return list;
    }
    /**
     * Use PriorityQueue - Max heap
     */
    public static List<String> topKFrequent_2(String[] words, int k) {
        Map<String, Integer> map = new TreeMap<String, Integer>();
        List<String> list = new ArrayList<>();

        for(String s : words){
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        /**
         * Max Heap
         */
        Queue<Map.Entry<String, Integer>> queue = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue() == o2.getValue() ? o1.getKey().compareTo(o2.getKey()) : o2.getValue() - o1.getValue();
            }
        });
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            queue.offer(entry);
        }
        for (int i = 0; i < k ; i++) {
            list.add(queue.poll().getKey());
        }
        return list;
    }
    /**
     * Trie 字典树/前缀树
     */
}
