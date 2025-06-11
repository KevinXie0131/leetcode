package com.answer.trie;

public class Q720_Longest_Word_in_Dictionary_3 {
    /**
     * 字典树
     * 具体地，我们把所有的字符串都扔到字典树中，然后，在这个字典树中做 DFS 搜索到最长的符合题目的字符串返回即可。
     * 这里，我们使用面向对象的思想，把字典树封装成一个类来操作，该是谁的行为就放在对应的类里面来处理。
     */
    public String longestWord(String[] words) {
        // 字典树
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        return trie.findLongestWord();
    }

    private static class Trie {
        Node root;
        String longestWord = "";

        Trie() {
            this.root = new Node();
        }

        void insert(String word) {
            // 往字典树中放一个字符串
            Node node = this.root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new Node();
                }
                node = node.children[index];
            }
            node.isEnd = true;
        }

        String findLongestWord() {
            // 从根节点开始DFS
            dfs(new StringBuilder(), root);
            return longestWord;
        }

        private void dfs(StringBuilder sb, Node node) {
            for (int i = 0; i < 26; i++) {
                Node child = node.children[i];
                // 注意题意：逐步增长
                if (child != null && child.isEnd) {
                    // 当前字母是符合条件的
                    sb.append((char) ('a' + i));

                    // 计算并与候选结果比较
                    if (sb.length() > longestWord.length()) {
                        longestWord = sb.toString();
                    }
                    // 递归
                    dfs(sb, child);
                    // 回溯：恢复状态
                    sb.deleteCharAt(sb.length() - 1); // backtracking
                }
            }
        }

        private static class Node {
            boolean isEnd;
            Node[] children;

            Node() {
                this.isEnd = false;
                this.children = new Node[26];
            }
        }
    }
}

