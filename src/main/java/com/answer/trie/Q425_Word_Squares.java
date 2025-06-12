package com.answer.trie;

import java.util.*;

public class Q425_Word_Squares { // Hard 困难
    /**
     * Given a set of words (without duplicates), find all word squares you can build from them.
     * A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).
     * For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.
     * b a l l
     * a r e a
     * l e a d
     * l a d y
     * 单词方块
     * 给定一个单词集合 words （没有重复），找出并返回其中所有的 单词方块 。 words 中的同一个单词可以被 多次 使用。你可以按 任意顺序 返回答案。
     * 一个单词序列形成了一个有效的 单词方块 的意思是指从第 k 行和第 k 列  0 <= k < max(numRows, numColumns) 来看都是相同的字符串。
     * 例如，单词序列 ["ball","area","lead","lady"] 形成了一个单词方块，因为每个单词从水平方向看和从竖直方向看都是相同的。
     * Note:
     *  There are at least 1 and at most 1000 words.
     *  All words will have the exact same length.
     *  Word length is at least 1 and at most 5.
     *  Each word contains only lowercase English alphabet a-z.
     * 示例 2：
     *  输入：words = ["abat","baba","atan","atal"]
     *  输出：[["baba","abat","baba","atal"], ["baba","abat","baba","atan"]]
     *  解释：输出包含两个单词方块，输出的顺序不重要，只需要保证每个单词方块内的单词顺序正确即可。
     */
    public static void main(String[] args) {
        Q425_Word_Squares ws = new Q425_Word_Squares();
        String[] words = {"area","lead","wall","lady","ball"};
        List<List<String>> res = ws.wordSquares(words);
        for (List<String> square : res) {
            for (String row : square) System.out.println(row);
            System.out.println("----");
        }
    }
    /**
     * 本实现使用 Trie 树高效查找前缀，配合回溯法枚举所有可能的单词方块；
     *
     * 要添加下一个单词，我们首先要获取下一个单词的前缀，第一个字母是第一个单词的第三个位置,
     * 第二个字母是第二个单词的第三个位置, 这样就构成了前缀
     */
    // Trie 节点
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        List<String> startWith = new ArrayList<>(); // 记录当前前缀对应的所有单词
    }

    // Trie 树
    class Trie {
        TrieNode root = new TrieNode();

        public Trie(String[] words) {
            for (String word : words) {
                TrieNode node = root;
                for (char c : word.toCharArray()) {
                    if (node.children[c - 'a'] == null) {
                        node.children[c - 'a'] = new TrieNode();
                    }
                    node = node.children[c - 'a'];
                    node.startWith.add(word); // 以该前缀开头的单词
                }
            }
        }

        // 查找以 prefix 开头的所有单词
        public List<String> findByPrefix(String prefix) {
            TrieNode node = root;
            for (char c : prefix.toCharArray()) {
                if (node.children[c - 'a'] == null) {
                    return new ArrayList<>();
                }
                node = node.children[c - 'a'];
            }
            return node.startWith;
        }
    }
    // 使用 Trie 节点，每个节点保存以当前前缀开头的所有单词。
    // 构建 Trie 后，回溯每一层可用前缀快速查找单词，加快搜索效率。
    // 输出所有合法的 word square。
    private List<List<String>> results; // 存放所有合法单词方块
    private int N; // 单词长度，也是方块的行列数
    private Trie trie;  // 前缀树
    // 主方法：返回所有单词方块
    public List<List<String>> wordSquares(String[] words) {
        results = new ArrayList<>();
        if (words == null || words.length == 0) return results;
        N = words[0].length();
        trie = new Trie(words); // 构建Trie

        for (String word : words) {// 尝试每个单词作为方块第一行
            List<String> square = new ArrayList<>();
            square.add(word);
            backtrack(1, square); // 递归填充下一行
        }
        return results;
    }
    // 回溯递归，尝试填充单词方块的每一行
    private void backtrack(int step, List<String> square) {
        if (step == N) {   // 如果方块已填满，加入结果集
            results.add(new ArrayList<>(square));
            return;
        }
        // 构造前缀
        StringBuilder prefix = new StringBuilder(); // 构造当前列的前缀
        for (String s : square) {
            prefix.append(s.charAt(step));
        }
        // 查找所有以 prefix 开头的单词
        List<String> candidates = trie.findByPrefix(prefix.toString());  // 查找所有匹配前缀的候选单词
        for (String candidate : candidates) {
            square.add(candidate);  // 选择
            backtrack(step + 1, square);// 递归
            square.remove(square.size() - 1); // 回溯
        }
    }


}
