package com.answer.priorityqueue;

import java.util.*;

public class Q692_Top_K_Frequent_Words {
    /**
     * 前K个高频单词
     * 给定一个单词列表 words 和一个整数 k ，返回前 k 个出现次数最多的单词。
     * Given an array of strings words and an integer k, return the k most frequent strings.
     * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率， 按字典顺序 排序。
     * Return the answer sorted by the frequency from highest to lowest. Sort the words with the same frequency by their lexicographical order.
     *
     * 示例 1：
     *  输入: words = ["i", "love", "leetcode", "i", "love", "coding"], k = 2
     *  输出: ["i", "love"]
     *  解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
     *        注意，按字母顺序 "i" 在 "love" 之前。
     *        "i" and "love" are the two most frequent words.
     *        Note that "i" comes before "love" due to a lower alphabetical order.
     * 示例 2：
     *  输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
     *  输出: ["the", "is", "sunny", "day"]
     *  解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
     *       出现次数依次为 4, 3, 2 和 1 次。
     *       "the", "is", "sunny" and "day" are the four most frequent words,
     *       with the number of occurrence being 4, 3, 2 and 1 respectively.
     *
     * 进阶：尝试以 O(n log k) 时间复杂度和 O(n) 空间复杂度解决。
     */
    public static void main(String[] args) {
        String[] words = {"the","day","is","sunny","the","the","the","sunny","is","is", "a", "b"};
        int k = 4;
        List<String> list = topKFrequent_4(words, k);
        System.out.println(list);
    }
    /**
     * Use Hashmap 哈希表 + 排序
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
        // 排序时，如果两个字符串出现频率相同，那么我们让两字符串中字典序较小的排在前面，否则我们让出现频率较高的排在前面。
        Collections.sort(list, (o1, o2) -> map.get(o1) == map.get(o2) ? o1.compareTo(o2) : map.get(o2) - map.get(o1));

      /*  Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return map.get(o1) == map.get(o2) ? o1.compareTo(o2) : map.get(o2) - map.get(o1);
            }
        });*/
/*        candidates.sort((a, b) -> {
            // 字符串频率相等按照字典序比较使得大的在堆顶,Java 可以直接使用 compareTo 方法即可。
            if (count.get(a).equals(count.get(b))) {
                return a.compareTo(b);
            } else {
                // 字符串频率不等则按照频率排列。
                return count.get(b) - count.get(a);
            }
        });*/
        return list.subList(0, k); // 只需要保留序列中的前 k 个字符串即可
    }
    /**
     * Use PriorityQueue 优先队列 - Min heap
     */
    public static List<String> topKFrequent_1(String[] words, int k) {
        Map<String, Integer> map = new TreeMap<String, Integer>();
        List<String> list = new ArrayList<>();

        for(String s : words){
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        // 小顶堆
        PriorityQueue<Map.Entry<String, Integer>> queue = new PriorityQueue<>((o1, o2) -> o1.getValue() == o2.getValue() ? o2.getKey().compareTo(o1.getKey()) : o1.getValue() - o2.getValue());
        /*PriorityQueue<Map.Entry<String, Integer>> queue = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue() == o2.getValue() ? o2.getKey().compareTo(o1.getKey()) : o1.getValue() - o2.getValue();
            }
        });*/
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            /*queue.offer(entry);
            if(queue.size() > k){
               queue.poll();
            }*/
            if(queue.size() == k){
                if(queue.peek().getValue() < entry.getValue()){
                    queue.poll();
                    queue.offer(entry);
                }
            } else {
                queue.offer(entry);
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
        // 大顶堆
        Queue<Map.Entry<String, Integer>> queue = new PriorityQueue<>((o1, o2) -> o1.getValue() == o2.getValue() ? o1.getKey().compareTo(o2.getKey()) : o2.getValue() - o1.getValue());
     /*   Queue<Map.Entry<String, Integer>> queue = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue() == o2.getValue() ? o1.getKey().compareTo(o2.getKey()) : o2.getValue() - o1.getValue();
            }
        });*/
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            queue.offer(entry);
        }
        for (int i = 0; i < k ; i++) {
            list.add(queue.poll().getKey());
        }
        return list;
    }
    /**
     * 桶排序法
     */
    public List<String> topKFrequent_5(String[] words, int k) {
        List<String> res = new ArrayList();

        HashMap<String, Integer> map = new HashMap();
        for(int i = 0; i < words.length; i++){
            map.put(words[i], map.getOrDefault(words[i], 0) + 1);
        }

        List<String>[] list = new List[words.length + 1];
        Arrays.setAll(list, i -> new ArrayList<>());

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            int count = entry.getValue();
            list[count].add(entry.getKey());
        }

        int index = 0;
        for (int i = list.length - 1; i >= 0; i--) {
            if (list[i].size() > 0) {
                Collections.sort(list[i]);
                for (String s : list[i]) {
                    res.add(s);
                    index++;
                    if (index >= k){
                        break;
                    }
                }
                if (index >= k) {
                    break;
                }
            }
        }
        return res;
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

