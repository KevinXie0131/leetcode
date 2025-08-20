package com.answer.trie;

public class Q720_Longest_Word_in_Dictionary_2 {
    /**
     * 字典树
     */
    Trie7 root = new Trie7();
    private int maxLength = 0;
    private String res = "";

    public String longestWord(String[] words) {
        for (String word : words) {
            insert(word);
        }
        getMaxLengthWord(root, 0);
        return res;
    }

    public void insert(String word) {
        int n = word.length();
        Trie7 node = this.root;

        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new Trie7();
            }
            node = node.children[c - 'a'];
        }
        node.isEnd = true;
        node.word = word;
    }
    // 通过递归遍历 node 的 children 数组并且每遍历一次深度 deep 增加 1：DFS
    public void getMaxLengthWord(Trie7 node, int deep) {
        // 若当前传入的节点不是最后一个位置上节点而是中间某位置的节点，此时直接结束
        if (deep > 0 && !node.isEnd) {
            return;
        }
        // 若当前传入的节点的深度 deep>maxLength，此时应该更新 maxLength 的值并且将该节点对应的 word 保存下来。
        if (deep > maxLength) {
            res = node.word;
            maxLength = deep;
        }
        // 然后遍历当前传入节点的 children[26] 对每一个不为空的节点都进行深度搜索，
        // 即递归遍历并且每次遍历时传入的深度要 +1（因为是 dfs 所以每次遍历的深度都要改变），即 getMaxLengthWord(node.children[i], deep + 1)。
        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                getMaxLengthWord(node.children[i], deep + 1);
            }
        }
    }
}

class Trie7 {
    Trie7[] children;
    boolean isEnd;
    String word; // 用来保存当前遍历的 word

    public Trie7() {
        children = new Trie7[26];
        isEnd = false;
    }
}
