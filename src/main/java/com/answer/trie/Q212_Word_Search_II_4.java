package com.answer.trie;

import java.util.*;

public class Q212_Word_Search_II_4 {
    /**
     * 剪枝优化
     * 考虑下面这种情况：在board=[['a','a','a','a']['a','a','a','a']['a','a','a','a']['a','a','a','a']]
     * 寻找单词words=[["aaa"]]，你会发现，按照方法一，要寻找好多次，实际情况，是我们只要找到一次就可以返回了，
     * 所以，我们要必要对方法一的逻辑进行优化。
     * 一种优化方向是，当我们在board中找到了某个单词，就把它从前缀树中删除，比如上面的"aaa"已经找到了，
     * 那么，删除前缀树中关于“aaa”的这个单词，前缀树实际上变成空树了，后面再遍历了任何字符都是判断不在前缀树中了，
     * 这样能够保证更快地返回结果。
     *
     * 不使用visited数组也是可以的，直接把遍历过的节点改为“#”号，恢复状态的时候再改回来也是可以的，
     */
    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 上下左右移动的方向

    public List<String> findWords(char[][] board, String[] words) {
        List<String> resultList = new ArrayList<>();// 结果集
        TrieNode root = buildTrie(words); // 构建字典树

        int m = board.length;
        int n = board[0].length;
        StringBuilder result = new StringBuilder(); // 记录沿途遍历到的元素

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(resultList, result, board, i, j, root, root); // 从每个元素开始遍历
            }
        }
        return resultList; // 题目要求返回List
    }

    private void dfs(List<String> resultList, StringBuilder result, char[][] board,
                     int i, int j, TrieNode root, TrieNode node) {
        // 判断越界，或者访问过，或者不在字典树中，直接返回
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length
                || board[i][j] == '#'
                || node == null || node.children[board[i][j] - 'a'] == null) {
            return;
        }

        char curr = board[i][j]; // 记录当前字符
        result.append(curr);

        if (node.children[board[i][j] - 'a'].isEnd) { // 如果有结束字符，加入结果集中
            String word = result.toString();
            resultList.add(word);
            deleteWordFromTrie(root, word);
        }

        board[i][j] = '#'; // 记录当前元素已访问

        for (int[] dir : dirs) { // 按四个方向去遍历
            dfs(resultList, result, board, i + dir[0], j + dir[1], root, node.children[curr - 'a']);
        }

        board[i][j] = curr;  // 还原状态
        result.deleteCharAt(result.length() - 1);
    }

    private void deleteWordFromTrie(TrieNode root, String word) {
        delete(root, word, 0);// 删除并没有那么好搞，需要先找到最后一个字符，从下往上删除
    }

    // 返回true表示可以把沿途节点删除，返回false表示不能删除沿途节点
    private boolean delete(TrieNode prev, String word, int i) {
        if (i == word.length() - 1) {
            // 如果后面还有单词则不能直接删除，比如dog和dogs是在一条链上，删除dog的时候不能把整个链删除了
            TrieNode curr = prev.children[word.charAt(i) - 'a'];
            if (hasChildren(curr)) {
                curr.isEnd = false;
                return false;
            } else {
                prev.children[word.charAt(i) - 'a'] = null;
                return true;
            }
        } else {
            // 如果后面的说可以删除，并且当前节点不是单词节点，并且没有其它子节点了，那么删除之，否则返回false
            // 比如删除dogs的时候不能把dog删除了
            // 比如同时存在dog和dig两个单词，删除dog的时候返回到d的时候，这个d是不能删除的
            if (delete(prev.children[word.charAt(i) - 'a'], word, i + 1)
                    && !prev.children[word.charAt(i) - 'a'].isEnd
                    && !hasChildren(prev.children[word.charAt(i) - 'a'])) {
                prev.children[word.charAt(i) - 'a'] = null;
                return true;
            }
            return false;
        }
    }

    private boolean hasChildren(TrieNode curr) {
        for (TrieNode child : curr.children) {
            if (child != null) {
                return true;
            }
        }
        return false;
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
        boolean isEnd = false;// 记录到这个节点是否是一个完整的单词
        TrieNode[] children = new TrieNode[26]; // 孩子节点，题目说了都是小写字母，所以用数组，否则可以用HashMap替换
    }
}
