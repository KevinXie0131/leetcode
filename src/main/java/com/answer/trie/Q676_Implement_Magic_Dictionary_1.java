package com.answer.trie;

public class Q676_Implement_Magic_Dictionary_1 {
    /**
     * 使用字典树优化枚举
     * 从前缀树的根节点开始，对于当前遍历到的字母：
     *
     * 我们首先判断是否存在与其相同的子节点，如果存在，则继续向下遍历
     *  否则我们需要判断是否还有剩余的修改次数，如果没有，则说明无法匹配，返回 false。
     *  如果有剩余的修改次数，我们可以尝试对当前的字母进行修改，然后继续向下遍历，如果当前的字母修改后对应的子节点存在，则说明可以匹配，否则说明无法匹配，返回 false。
     *  如果我们遍历到了单词的结尾，且修改次数恰好为 1，那么说明可以匹配，返回 true。
     */
    Trie root;

    public void MagicDictionary() {
        root = new Trie();
    }

    public void buildDict(String[] dictionary) {
        for (String word : dictionary) {
            Trie cur = root;
            for (int i = 0; i < word.length(); ++i) {
                char ch = word.charAt(i);
                int idx = ch - 'a';
                if (cur.child[idx] == null) {
                    cur.child[idx] = new Trie();
                }
                cur = cur.child[idx];
            }
            cur.isFinished = true;  // 标记到达末尾
        }
    }

    public boolean search(String searchWord) {
        return dfs(searchWord, root, 0, false);
    }
    // 当前遍历到的字典树上的节点是 node 以及待查询字符串 searchWord 的第 pos 个字符，并且在之前的遍历中是否已经替换过恰好一个字符
    // （如果替换过，那么 modified 为 true，否则为 false）。
    private boolean dfs(String searchWord, Trie node, int pos, boolean modified) {
        // 当 pos 等于 searchWord 的长度时，说明递归完成。此时我们需要检查 node 是否是一个字典树上的结束节点
        // （即一个单词的末尾），同时需要保证 modified 为 true，因为我们必须进行一次修改。
        if (pos == searchWord.length()) {
            return modified && node.isFinished;
        }
        int idx = searchWord.charAt(pos) - 'a';
        if (node.child[idx] != null) { // 如果 node 有一个值为 searchWord[pos] 的子节点，那么我们就可以继续进行递归。
            if (dfs(searchWord, node.child[idx], pos + 1, modified)) {
                return true;
            }
        }
        // 如果 modified 为 false，我们可以将 searchWord[pos] 替换成任意一个是 node 子节点的字符，
        // 将 modified 置为 true 并继续进行递归。
        if (!modified) {
            for (int i = 0; i < 26; ++i) {
                if (i != idx && node.child[i] != null) {
                    if (dfs(searchWord, node.child[i], pos + 1, true)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    class Trie {
        boolean isFinished; // 判读是否为单词末尾
        Trie[] child;

        public Trie() {
            isFinished = false;
            child = new Trie[26];
        }
    }
}
