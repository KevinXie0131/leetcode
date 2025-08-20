package com.answer.trie;

import java.util.*;

public class Q212_Word_Search_II_1 {
    /**
     * 回溯算法
     * 数据范围只有 12，且 words 中出现的单词长度不会超过 10，可以考虑使用「回溯算法」。
     *
     * 起始先将所有 words 出现的单词放到 Set 结构中，然后以 board 中的每个点作为起点进行爆搜（由于题目规定在一个单词中每个格子只能被使用一次，
     * 因此还需要一个 vis 数组来记录访问过的位置）：
     * 如果当前爆搜到的字符串长度超过 10，直接剪枝；
     * 如果当前搜索到的字符串在 Set 中，则添加到答案（同时了防止下一次再搜索到该字符串，需要将该字符串从 Set 中移除）。
     *
     * 时间复杂度：共有 m∗n 个起点，每次能往 4 个方向搜索（不考虑重复搜索问题），且搜索的长度不会超过 10。
     * 整体复杂度为 O(m∗n∗4 power(10))
     */
    Set<String> set = new HashSet<>();
    List<String> ans = new ArrayList<>();
    char[][] board;
    int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int n, m;
    boolean[][] visited = new boolean[15][15];

    public List<String> findWords(char[][] _board, String[] words) {
        board = _board;
        m = board.length;
        n = board[0].length;
        for (String w : words) {
            set.add(w);
        }
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = true;
                sb.append(board[i][j]);

                dfs(i, j, sb);

                visited[i][j] = false; // 回溯
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        return ans;
    }

    void dfs(int i, int j, StringBuilder sb) {
        if (sb.length() > 10) {
            return;
        }
        if (set.contains(sb.toString())) {
            ans.add(sb.toString());
            set.remove(sb.toString());
        }
        for (int[] d : dirs) {
            int dx = i + d[0], dy = j + d[1];
            if (dx < 0 || dx >= m || dy < 0 || dy >= n) {
                continue;
            }
            if (visited[dx][dy]) {
                continue;
            }
            visited[dx][dy] = true;
            sb.append(board[dx][dy]);

            dfs(dx, dy, sb);

            visited[dx][dy] = false; // 回溯
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    /**
     * 在「解法一」中，对于任意一个当前位置 (i,j)，我们都不可避免的搜索了四联通的全部方向，这导致了那些无效搜索路径最终只有长度达到 10 才会被剪枝。
     * 要进一步优化我们的搜索过程，需要考虑如何在每一步的搜索中进行剪枝。
     *
     * 我们可以使用 Trie 结构进行建树，对于任意一个当前位置 (i,j) 而言，
     * 只有在 Trie 中存在往从字符 a 到 b 的边时，我们才在棋盘上搜索从 a 到 b 的相邻路径。
     */
    class TrieNode {
        String word;
        TrieNode[] children = new TrieNode[26];
    }

    void insert(String s) {
        TrieNode p = root;
        for (int i = 0; i < s.length(); i++) {
            int u = s.charAt(i) - 'a';
            if (p.children[u] == null){
                p.children[u] = new TrieNode();
            }
            p = p.children[u];
        }
        p.word = s;
    }

    Set<String> set1 = new HashSet<>();
    char[][] board1;
    int n1, m1;
    TrieNode root = new TrieNode();
    int[][] dirs1 = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    boolean[][] vis = new boolean[15][15];

    public List<String> findWords1(char[][] _board, String[] words) {
        board1 = _board;
        m1 = board1.length;
        n1 = board1[0].length;
        for (String w : words) {
            insert(w);
        }
        for (int i = 0; i < m1; i++) {
            for (int j = 0; j < n1; j++) {
                int u = board1[i][j] - 'a';
                if (root.children[u] != null) {
                    vis[i][j] = true;

                    dfs(i, j, root.children[u]);

                    vis[i][j] = false; // 回溯
                }
            }
        }
      /*  List<String> ans = new ArrayList<>(); // works too
        for (String s : set1){
            ans.add(s);
        }
        return ans;*/
        return new ArrayList<>(set1);
    }

    void dfs(int i, int j, TrieNode node) {
        if (node.word != null) {
            set1.add(node.word);
        }
        for (int[] d : dirs1) {
            int dx = i + d[0], dy = j + d[1];
            if (dx < 0 || dx >= m1 || dy < 0 || dy >= n1) {
                continue;
            }
            if (vis[dx][dy]) {
                continue;
            }
            int u = board1[dx][dy] - 'a';
            if (node.children[u] != null) {
                vis[dx][dy] = true;

                dfs(dx, dy, node.children[u]);

                vis[dx][dy] = false; // 回溯
            }
        }
    }
}
