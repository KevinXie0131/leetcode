package com.answer.trie;

import java.util.*;

public class Q642_Design_Search_Autocomplete_System_1 {
    /**
     * 设计搜索自动补全系统（Leetcode 642）Java 实现
     * 支持历史热度，输入字符返回前三条热度最高且前缀匹配的句子。
     */
    // Trie 节点定义
    private class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>(); // 子节点
        Map<String, Integer> counts = new HashMap<>(); // 以当前节点为前缀的句子及其热度
        boolean isWord = false; // 是否为句子结尾
    }

    private TrieNode root;
    private StringBuilder currentInput;

    /**
     * 构造方法，初始化历史句子和热度
     */
    public Q642_Design_Search_Autocomplete_System_1(String[] sentences, int[] times) {
        root = new TrieNode();
        currentInput = new StringBuilder();
        for (int i = 0; i < sentences.length; i++) {
            add(sentences[i], times[i]);
        }
    }

    /**
     * 添加句子到 Trie 中，并记录热度
     */
    private void add(String sentence, int count) {
        TrieNode node = root;
        for (char c : sentence.toCharArray()) {
            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c);
            node.counts.put(sentence, node.counts.getOrDefault(sentence, 0) + count);
        }
        node.isWord = true;
    }

    /**
     * 处理用户输入字符
     * @param c 输入字符，若为 '#' 则提交当前前缀为新句子
     * @return 匹配当前前缀的热度前三的句子
     */
    public List<String> input(char c) {
        if (c == '#') {
            String sentence = currentInput.toString();
            add(sentence, 1); // 新输入的句子热度+1
            currentInput = new StringBuilder();
            return new ArrayList<>();
        }
        currentInput.append(c);
        TrieNode node = root;
        for (char ch : currentInput.toString().toCharArray()) {
            if (!node.children.containsKey(ch)) return new ArrayList<>();
            node = node.children.get(ch);
        }
        // 优先队列，热度高优先，热度相同按字典序
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
                (a, b) -> a.getValue().equals(b.getValue())
                        ? a.getKey().compareTo(b.getKey())
                        : b.getValue() - a.getValue()
        );
        pq.addAll(node.counts.entrySet());
        List<String> res = new ArrayList<>();
        int k = 3;
        while (!pq.isEmpty() && k-- > 0) {
            res.add(pq.poll().getKey());
        }
        return res;
    }
}
