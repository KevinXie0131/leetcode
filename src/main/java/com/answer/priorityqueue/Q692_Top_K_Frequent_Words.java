package com.answer.priorityqueue;

import java.util.*;

public class Q692_Top_K_Frequent_Words {
    public static void main(String[] args) {
        String[] words = {"the","day","is","sunny","the","the","the","sunny","is","is", "a", "b"};
        int k = 4;
        List<String> list = topKFrequent_4(words, k);
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
     * Trie 字典树/前缀树 + PriorityQueue
     */
    public static List<String> topKFrequent_4(String[] words, int k) {
        List<String> result = new ArrayList<>();

        Trie root = new Trie();
        for(String word : words){
            root.insert(word, k);
        }
        Queue<WrodTuple>  queue = new PriorityQueue<>(new Comparator<WrodTuple>() {
            @Override
            public int compare(WrodTuple o1, WrodTuple o2) {
                return o1.getFrequency() == o2.getFrequency() ? o1.getVal().compareTo(o2.getVal()) : o2.getFrequency() - o1.getFrequency();
            }
        });
        dfs(root, queue);
        while(k-- > 0){
            result.add(queue.poll().val);
        }
        return result;
    }

    static void dfs(Trie node, Queue<WrodTuple>  queue) {
        if( node.isEnd ) {
            queue.offer(new WrodTuple(node.val, node.frequency));
        }
        // Recursion exit
        if( node.children.length == 0) {
            return ;
        }
        // Recursion
        Trie[] children = node.children;
        for( int i = 0; i < children.length; i++ ) {
            if( children[i] != null ) {
                dfs(node.children[i], queue);
            }
        }
    }

    static class Trie {
        private Trie[] children;
        private boolean isEnd;
        private String val;
        private int frequency;

        public Trie() {
            children = new Trie[26];
            isEnd = false;
            val = "";
            frequency = 0;
        }

        public void insert(String word, int k) { // 插入字符串
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                int index = ch - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new Trie();
                }
                node = node.children[index];
            }
            node.isEnd = true;
            node.val = word;
            node.frequency++;
        }
    }

    static class WrodTuple{
        private String val;
        private int frequency;

        public WrodTuple(String val,  int frequency){
            this.val = val;
            this.frequency = frequency;
        }

        public String getVal() {
            return val;
        }

        public int getFrequency() {
            return frequency;
        }
    }
}

