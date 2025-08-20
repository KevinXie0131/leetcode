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
    public static void main(String[] args) {
        Q211_Design_Add_and_Search_Words_Data_Structure wordDictionary = new Q211_Design_Add_and_Search_Words_Data_Structure();
       /* wordDictionary.addWord("a");
        wordDictionary.addWord("a");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("a.")); // return False
        System.out.println(wordDictionary.search("bad"));  // return True
        System.out.println(wordDictionary.search(".ad"));  // return True
        System.out.println(wordDictionary.search("b.."));  // return True*/
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("b.."));
    }
    /**
     * 字典树
     * 由于待搜索的单词可能包含点号，因此在搜索过程中需要考虑点号的处理。对于当前字符是字母和点号的情况，分别按照如下方式处理：
     * 如果当前字符是字母，则判断当前字符对应的子结点是否存在，如果子结点存在则移动到子结点，继续搜索下一个字符，如果子结点不存在则说明单词不存在，返回 false；
     * 如果当前字符是点号，由于点号可以表示任何字母，因此需要对当前结点的所有非空子结点继续搜索下一个字符。
     */
    private Node root;

    public Q211_Design_Add_and_Search_Words_Data_Structure() {
        root = new Node();
    }

    public void addWord(String word) {
        Node cur = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (cur.children[index] == null) {
                cur.children[index] = new Node();
            }
            cur = cur.children[index];
        }
        cur.isEnd = true;
    }

    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    public boolean dfs(String word, int index, Node node){
        if(index == word.length()){
            return node.isEnd;
        }
        Character ch = word.charAt(index);
        if(Character.isLetter(ch)){
            int childIndex = ch - 'a';
            Node child = node.children[childIndex];
            if(child != null && dfs(word, index + 1, child)){
                return true;
            }
        } else{
            for(int i = 0; i < 26; i++){
                Node child = node.children[i];
                if(child != null && dfs(word, index + 1, child)){
                    return true;
                }
            }
         /*   for( Node child  : node.children){ // works too
                if(child != null && dfs(word, index + 1, child)){
                    return true;
                }
            }*/
        }
        return false;
    }
}

class Node {
    Node[] children = new Node[26];
    boolean isEnd;
}
