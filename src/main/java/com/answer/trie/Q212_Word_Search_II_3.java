package com.answer.trie;

import java.util.*;

public class Q212_Word_Search_II_3 {
    /**
     * 字典树 + DFS + 回溯 + 剪枝
     * 首先，大家肯定能想到的是遍历整个board数组，先看第一个字母在不在words单词列表的前缀中，
     * 如果在，再向上下左右扩散看前两个字母在不在words单词列表的前缀中，这样每次都要在words中查询相关前缀的效率是非常低下的，
     * 所以，我们可以考虑将words转换成前缀树（Trie）（又叫字典树），这样查找起来就快多了，而且我们可以记录沿途查询过哪些前缀了，
     * 这样就不用每次都从前缀树的根节点开始查找了。
     *
     * 我们每遍历一个字符，判断其是否在前缀树当前层，如果在，就向上下左右扩散，同时，前缀树也向下移动一层。
     * 除了使用到前缀树和DFS外，我们需要求得所有的结果，所以，需要回溯遍历整个board，对于每一个遇到的字符，
     * 都要判断其是否在前缀树中，同时，不在前缀树中的字符，直接剪枝掉即可。
     */
    // 上下左右移动的方向
    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public List<String> findWords(char[][] board, String[] words) {
        // 结果集，去重
        Set<String> resultSet = new HashSet<>();

        // 构建字典树
        TrieNode root = buildTrie(words);

        int m = board.length;
        int n = board[0].length;
        // 记录某个下标是否访问过
        boolean[][] visited = new boolean[m][n];
        // 记录沿途遍历到的元素
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // 从每个元素开始遍历
                dfs(resultSet, result, board, i, j, root, visited);
            }
        }

        // 题目要求返回List
        return new ArrayList<>(resultSet);
    }

    private void dfs(Set<String> resultSet, StringBuilder result, char[][] board,
                     int i, int j, TrieNode node, boolean[][] visited) {
        // 判断越界，或者访问过，或者不在字典树中，直接返回
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || visited[i][j]
                || node.children[board[i][j] - 'a'] == null) {
            return;
        }

        // 记录当前字符
        result.append(board[i][j]);

        // 如果有结束字符，加入结果集中
        if (node.children[board[i][j] - 'a'].isEnd) {
            resultSet.add(result.toString());
        }

        // 记录当前元素已访问
        visited[i][j] = true;

        // 按四个方向去遍历
        for (int[] dir : dirs) {
            dfs(resultSet, result, board, i + dir[0], j + dir[1], node.children[board[i][j] - 'a'], visited);
        }

        // 还原状态
        visited[i][j] = false;
        result.deleteCharAt(result.length() - 1);
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            char[] arr = word.toCharArray();
            TrieNode curr = root;
            for (char c : arr) {
                if (curr.children[c - 'a'] == null) {
                    curr.children[c - 'a'] = new TrieNode();
                }
                curr = curr.children[c - 'a'];
            }
            curr.isEnd = true;
        }
        return root;
    }

    class TrieNode {
        // 记录到这个节点是否是一个完整的单词
        boolean isEnd = false;
        // 孩子节点，题目说了都是小写字母，所以用数组，否则可以用HashMap替换
        TrieNode[] children = new TrieNode[26];
    }
}
