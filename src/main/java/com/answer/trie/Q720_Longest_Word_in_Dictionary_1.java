package com.answer.trie;

public class Q720_Longest_Word_in_Dictionary_1 {
    /**
     * 字典树
     * 由于符合要求的单词的每个前缀都是符合要求的单词，因此可以使用字典树存储所有符合要求的单词。
     *
     * 如果一个单词是符合要求的单词，则比较当前单词与答案，如果当前单词的长度大于答案的长度，
     * 或者当前单词的长度等于答案的长度且当前单词的字典序小于答案的字典序，则将答案更新为当前单词。
     */
    public String longestWord(String[] words) {
        Trie6 trie = new Trie6();
        for (String word : words) {
            trie.insert(word);
        }
        String longest = "";
        for (String word : words) {
            if (trie.search(word)) {
                if (word.length() > longest.length() || (word.length() == longest.length() && word.compareTo(longest) < 0)) {
                    longest = word;
                }
            }
        }
        return longest;
    }
}

class Trie6 {
    Trie6[] children;
    boolean isEnd;

    public Trie6() {
        children = new Trie6[26];
        isEnd = false;
    }

    public void insert(String word) {
        Trie6 node = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new Trie6();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        Trie6 node = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null || !node.children[index].isEnd) { // 判断每个 words[i] 是否为「合法单词」
                return false;
            }
            node = node.children[index];
        }
        return node != null && node.isEnd;
    }
}
