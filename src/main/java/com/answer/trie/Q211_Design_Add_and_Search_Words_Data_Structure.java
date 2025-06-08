package com.answer.trie;

public class Q211_Design_Add_and_Search_Words_Data_Structure {
    /**
     * 添加与搜索单词 - 数据结构设计
     * 设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。
     * 实现词典类 WordDictionary ：
     *  WordDictionary() 初始化词典对象
     *  void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
     *  bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；否则，返回  false 。word 中可能包含一些 '.' ，每个 . 都可以表示任何一个字母。
     *
     * WordDictionary wordDictionary = new WordDictionary();
     * wordDictionary.addWord("bad");
     * wordDictionary.addWord("dad");
     * wordDictionary.addWord("mad");
     * wordDictionary.search("pad"); // 返回 False
     * wordDictionary.search("bad"); // 返回 True
     * wordDictionary.search(".ad"); // 返回 True
     * wordDictionary.search("b.."); // 返回 True
     *
     * word in addWord consists of lowercase English letters.
     * word in search consist of '.' or lowercase English letters.
     * There will be at most 2 dots in word for search queries.
     */
    public Q211_Design_Add_and_Search_Words_Data_Structure() {

    }

    public void addWord(String word) {

    }

    public boolean search(String word) {
        return true;
    }
}
