package com.answer.trie;

public class Q208_Implement_Trie_Prefix_Tree {
    class Trie {
        private Trie[] children; // 指向子节点的指针数组 children。对于本题而言，数组长度为 26，即小写英文字母的数量
        private boolean isEnd; // 布尔字段 isEnd，表示该节点是否为字符串的结尾

        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }
        /**
         * 从字典树的根开始，插入字符串。对于当前字符对应的子节点，有两种情况：
         *    子节点存在。沿着指针移动到子节点，继续处理下一个字符。
         *    子节点不存在。创建一个新的子节点，记录在 children 数组的对应位置上，然后沿着指针移动到子节点，继续搜索下一个字符。
         * 重复以上步骤，直到处理字符串的最后一个字符，然后将当前节点标记为字符串的结尾
         */
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
            // 若搜索到了前缀的末尾，就说明字典树中存在该前缀。此外，若前缀末尾对应节点的 isEnd 为真，
            // 则说明字典树中存在该字符串。
            Trie node = searchPrefix(word);
            return node != null && node.isEnd;
        }

        public boolean startsWith(String prefix) {
           return searchPrefix(prefix) != null;
        }
        /**
         * 从字典树的根开始，查找前缀。对于当前字符对应的子节点，有两种情况：
         *    子节点存在。沿着指针移动到子节点，继续搜索下一个字符。
         *    子节点不存在。说明字典树中不包含该前缀，返回空指针。
         * 重复以上步骤，直到返回空指针或搜索完前缀的最后一个字符。
         */
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
    /**
     * 另一种形式
     */
    class Trie1 {
        private Node root;

        public Trie1() {
            root = new Node(); // 根节点
        }
        // 相当于生成了一条移动方向为「左-左-右」的路径。标记最后一个节点为终止节点
        public void insert(String word) {
            Node cur = root; // 初始值为 root
            for (char ch : word.toCharArray()) {
                int index = ch - 'a';
                if (cur.children[index] == null) {
                    cur.children[index] = new Node();
                }
                cur = cur.children[index];
            }
            cur.isEnd = true; // 遍历结束，把 cur 的 end 标记为 true。
        }
        // 相当于查找二叉树中是否存在一条移动方向为「左-左-右」的路径，且最后一个节点是终止节点
        public boolean search(String word) {
            Node node = find(word);
            return node != null && node.isEnd;
        }
        // 相当于查找二叉树中是否存在一条移动方向为「左-左」的路径，无其他要求
        public boolean startsWith(String prefix) {
            return find(prefix) != null;
        }

        private Node find(String word) {
            Node cur = root; // 初始值为 root
            for (char ch : word.toCharArray()) {
                int index = ch - 'a';
                if (cur.children[index] == null) {
                    return null;
                }
                cur = cur.children[index];
            }
            return cur;
        }
    }
    class Node {
        Node[] children = new Node[26]; // 26 叉树 / 对于 26 叉树的每个节点，可以用哈希表，或者长为 26 的数组来存储子节点
        boolean isEnd;
    }
}

