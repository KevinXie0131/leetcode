package com.answer.trie;

import java.util.*;

public class Q212_Word_Search_II { // Hard 困难
    /**
     * Given an m x n board of characters and a list of strings words, return all words on the board.
     * Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are
     * horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
     * 单词搜索 II
     * 给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words， 返回所有二维网格上的单词 。
     * 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
     * 同一个单元格内的字母在一个单词中不允许被重复使用。
     * board[i][j] is a lowercase English letter.
     *
     * 示例 1：
     * 输入：board = [["o","a","a","n"],
     *               ["e","t","a","e"],
     *               ["i","h","k","r"],
     *               ["i","f","l","v"]],
     *       words = ["oath","pea","eat","rain"]
     * 输出：["eat","oath"]
     */
    /**
     * 前缀树 + DFS
     */
    Set<String> res = new HashSet<>();

    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        for(String str : words){
            trie.insert(str);
        }
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                dfs(board, visited, "", i, j, trie);
            }
        }
        return new ArrayList(res);
    }

    public void dfs(char[][] board, boolean[][] visited, String str, int i, int j, Trie trie){
        if(i< 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return;
        }
        if(visited[i][j] == true) {
            return;
        }

        str += board[i][j];

        if(!trie.startsWith(str)) {
            return;
        }
        if(trie.search(str)) {
            res.add(str);
        }

        visited[i][j] = true;

        dfs(board, visited, str, i - 1, j, trie);
        dfs(board, visited, str, i + 1, j, trie);
        dfs(board, visited, str, i, j - 1, trie);
        dfs(board, visited, str, i, j + 1, trie);

        visited[i][j] = false; // backtracking
    }
    /**
     * The same Trie structure of Q208_Implement_Trie_Prefix_Tree
     */
    class Trie {
        private Trie[] children;
        private boolean isEnd;

        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }

        public void insert(String word) { // 插入字符串
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
        }

        public boolean search(String word) {
            Trie node = searchPrefix(word);
            return node != null && node.isEnd;
        }

        public boolean startsWith(String prefix) {
            return searchPrefix(prefix) != null;
        }

        private Trie searchPrefix(String prefix) { // 查找前缀
            Trie node = this;
            for (int i = 0; i < prefix.length(); i++) {
                char ch = prefix.charAt(i);
                int index = ch - 'a';
                if (node.children[index] == null) {
                    return null;
                }
                node = node.children[index];
            }
            return node;
        }
    }
}
