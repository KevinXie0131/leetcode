package com.answer.priorityqueue;

import java.util.*;

public class Q692_Top_K_Frequent_Words {
    public static void main(String[] args) {
        String[] words = {"the","day","is","sunny","the","the","the","sunny","is","is"};
        int k = 4;
        List<String> list = topKFrequent_1(words, k);
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
        return list.subList(0, k);
    }

    /**
     * Use PriorityQueue
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
}
