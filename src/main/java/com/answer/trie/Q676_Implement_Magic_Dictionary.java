package com.answer.trie;

public class Q676_Implement_Magic_Dictionary {
    /**
     * 设计一个使用单词列表进行初始化的数据结构，单词列表中的单词 互不相同 。 如果给出一个单词，请判定能否只将这个单词中
     * 一个字母换成另一个字母，使得所形成的新单词存在于你构建的字典中。
     * Design a data structure that is initialized with a list of different words. Provided a string,
     * you should determine if you can change exactly one character in this string to match any word in the data structure.
     * <p>
     * 输入 ["MagicDictionary", "buildDict", "search", "search", "search", "search"]
     * [[], [["hello", "leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
     * 输出 [null, null, false, true, false, false]
     * 解释
     * MagicDictionary magicDictionary = new MagicDictionary();
     * magicDictionary.buildDict(["hello", "leetcode"]);
     * magicDictionary.search("hello"); // 返回 False
     * magicDictionary.search("hhllo"); // 将第二个 'h' 替换为 'e' 可以匹配 "hello" ，所以返回 True
     * magicDictionary.search("hell"); // 返回 False
     * magicDictionary.search("leetcoded"); // 返回 False
     */
    private TrieNode root;

    public void MagicDictionary() {
        root = new TrieNode();
    }

    public void buildDict(String[] dictionary) {
        for (String word : dictionary) {
            TrieNode p = root;
            for (char c : word.toCharArray()) {
                int i = c - 'a';
                if (p.children[i] == null) p.children[i] = new TrieNode();
                p = p.children[i];
            }
            p.val = true;
        }
    }
    /**
     * bool search(String searchWord) Returns true if you can change exactly one character in searchWord to
     * match any string in the data structure, otherwise returns false.
     */
    public boolean search(String searchWord) {
        // 遍历每一种替换的情况
        for (int i = 0; i < searchWord.length(); i++) {
            if (search(root, searchWord, 0, i)) return true;
        }
        return false;
    }
    // 参数：当前节点，要搜索的单词，当前第几位，替换的第几位
    private boolean search(TrieNode node, String searchWord, int index, int changeId) {
        if (node == null) return false;  // 说明没找到
        if (index == searchWord.length()) return node.val;   // 搜索完了，看看长度是否一样
        int i = searchWord.charAt(index) - 'a';
        if (index == changeId) { // 遇到修改的这一位了
            for (int j = 0; j < 26; j++) {
                if (j == i) continue;   // 修改成一样的，不考虑
                // 否则尝试修改成第j个字母，继续递归
                if (search(node.children[j], searchWord, index + 1, changeId)) return true;
            }
            return false;
        }
        // 没遇到就继续递归
        return search(node.children[i], searchWord, index + 1, changeId);
    }

    class TrieNode {
        boolean val;
        TrieNode[] children = new TrieNode[26];
    }
    /**
     * 枚举每个字典中的字符串并判断
     */
    private String[] words;

    public boolean search1(String searchWord) {
        for (String word : words) {
            if (word.length() != searchWord.length()) {
                continue;
            }

            int diff = 0;
            for (int i = 0; i < word.length(); ++i) {
                if (word.charAt(i) != searchWord.charAt(i)) {
                    ++diff;
                    if (diff > 1) {
                        break;
                    }
                }
            }
            if (diff == 1) {
                return true;
            }
        }
        return false;
    }

}
