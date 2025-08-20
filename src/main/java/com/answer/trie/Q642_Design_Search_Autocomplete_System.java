package com.answer.trie;

import java.util.*;

public class Q642_Design_Search_Autocomplete_System { // Hard 困难
    /**
     * 设计搜索自动补全系统
     * 为搜索引擎设计一个搜索自动补全系统。用户会输入一条语句（最少包含一个字母，以特殊字符 '#' 结尾）。
     * 给定一个字符串数组 sentences 和一个整数数组 times ，长度都为 n ，其中 sentences[i] 是之前输入的句子， times[i] 是该句子输入的相应次数。对于除 ‘#’ 以外的每个输入字符，返回前 3 个历史热门句子，这些句子的前缀与已经输入的句子的部分相同。
     * 下面是详细规则：
     *  一条句子的热度定义为历史上用户输入这个句子的总次数。
     *  返回前 3 的句子需要按照热度从高到低排序（第一个是最热门的）。如果有多条热度相同的句子，请按照 ASCII 码的顺序输出（ASCII 码越小排名越前）。
     *  如果满足条件的句子个数少于 3 ，将它们全部输出。
     *  如果输入了特殊字符，意味着句子结束了，请返回一个空集合。
     * 实现 AutocompleteSystem 类：
     *  AutocompleteSystem(String[] sentences, int[] times): 使用数组sentences 和 times 对对象进行初始化。
     *  AutocompleteSystem(String[] sentences, int[] times): This is the constructor. The input is historical data.
     *  Sentences is a string array consists of previously typed sentences.
     *  Times is the corresponding times a sentence has been typed. Your system should record these historical data.
     *
     *  Now, the user wants to input a new sentence. The following function will provide the next character the user types:
     *  List<String> input(char c) 表示用户输入了字符 c 。
     *  如果 c == '#' ，则返回空数组 [] ，并将输入的语句存储在系统中。
     *  返回前 3 个历史热门句子，这些句子的前缀与已经输入的句子的部分相同。如果少于 3 个匹配项，则全部返回。
     * c 是小写英文字母， '#', 或空格 ' '
     * 每个被测试的句子将是一个以字符 '#' 结尾的字符 c 序列。
     *
     * Example:
     * Operation: AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2])
     * The system have already tracked down the following sentences and their corresponding times:
     * "i love you" : 5 times
     * "island" : 3 times
     * "ironman" : 2 times
     * "i love leetcode" : 2 times
     * Now, the user begins another search:
     *
     * Operation: input('i')
     * Output: ["i love you", "island","i love leetcode"]
     * Explanation:
     * There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman". Also we only need to output top 3 hot sentences, so "ironman" will be ignored.
     *
     * Operation: input(' ')
     * Output: ["i love you","i love leetcode"]
     * Explanation:
     * There are only two sentences that have prefix "i ".
     *
     * Operation: input('a')
     * Output: []
     * Explanation:
     * There are no sentences that have prefix "i a".
     *
     * Operation: input('#')
     * Output: []
     * Explanation:
     * The user finished the input, the sentence "i a" should be saved as a historical sentence in system. And the following input will be counted as a new search.
     */
    /**
     * 前缀树 + 排序/优先队列
     */
    private Trie trie = new Trie();
    private StringBuilder t = new StringBuilder();

    public Q642_Design_Search_Autocomplete_System(String[] sentences, int[] times) {
        int i = 0;
        for (String s : sentences) {
            trie.insert(s, times[i++]);
        }
    }

    public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        if (c == '#') {
            trie.insert(t.toString(), 1);
            t = new StringBuilder();
            return res;
        }
        t.append(c);
        Trie node = trie.search(t.toString());
        if (node == null) {
            return res;
        }
        PriorityQueue<Trie> q = new PriorityQueue<>((a, b) -> a.v == b.v ? b.w.compareTo(a.w) : a.v - b.v);
        dfs(node, q);
        while (!q.isEmpty()) {
            res.add(0, q.poll().w);
        }
        return res;
    }

    private void dfs(Trie node, PriorityQueue q) {
        if (node == null) {
            return;
        }
        if (node.v > 0) {
            q.offer(node);
            if (q.size() > 3) {
                q.poll();
            }
        }
        for (Trie nxt : node.children) {
            dfs(nxt, q);
        }
    }
}

class Trie {
    Trie[] children = new Trie[27];
    int v;
    String w = "";

    void insert(String w, int t) {
        Trie node = this;
        for (char c : w.toCharArray()) {
            int idx = c == ' ' ? 26 : c - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx];
        }
        node.v += t;
        node.w = w;
    }

    Trie search(String pref) {
        Trie node = this;
        for (char c : pref.toCharArray()) {
            int idx = c == ' ' ? 26 : c - 'a';
            if (node.children[idx] == null) {
                return null;
            }
            node = node.children[idx];
        }
        return node;
    }
}
