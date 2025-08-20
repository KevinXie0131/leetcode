package com.answer.trie;

public class Q211_Design_Add_and_Search_Words_Data_Structure_1 {
    /**
     * 字典树+回溯法：匹配特殊字符的处理
     *  ''.'' 是任意一个字符。我们需要 枚举当前字符的所有非空子节点 进行匹配
     */
    private Node1 root;     // 根节点

    public Q211_Design_Add_and_Search_Words_Data_Structure_1() {
        root = new Node1();
    }

    public void addWord(String word) {
        Node1 node = root;      // 从根节点开始构造这个word对应的路径节点
        int n = word.length();

        for(int i = 0; i < n; i++){
            int id = word.charAt(i) - 'a';   // 将当前字符添加到当前节点对应的子节点位置，然后递归更新
            if(node.children[id] == null){
                node.children[id] = new Node1();
            }
            node = node.children[id];
        }
        node.isEnd = true; // 最后一个节点的isEnd置为true，表示一个完整的字符串
    }

    public boolean search(String word) {
        return DFS_Search(word, 0, root);   // 从根节点开始匹配word[0]
    }
    /**
     * 回溯法查找字典树是否匹配word
     * @param word: 待匹配字符串
     * @param idx: 当前匹配word[idx]字符
     * @param node: 搜索待匹配字符是否存在的节点
     */
    private boolean DFS_Search(String word, int idx, Node1 node) {
        if (idx == word.length()) {
            return node.isEnd;     // 字符匹配结束，返回当前节点是否为尾节点
        }
        if (word.charAt(idx) == '.') {
            for (Node1 child : node.children) { // 当前字符为.，匹配任意字符，即枚举所有非空子节点去匹配下一个字符
                if (child != null && DFS_Search(word, idx + 1, child)) {
                    return true;    // 匹配成功直接返回
                }
            }
        } else {
            Node1 child = node.children[word.charAt(idx) - 'a'];// 获取当前字符对应的子节点
            if (child != null && DFS_Search(word, idx + 1, child)) {
                return true;    // 匹配成功直接返回
            }
        }
        return false;   // 否则匹配失败
    }

/*    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    public boolean dfs(String word, int i, Node node){ // works too
        if (node == null) return false;
        if (i >= word.length()) return node.isEnd;

        char c = word.charAt(i);
        if (c == '.') {
            for (Node next : node.children) {
                if (dfs(word, i + 1, next)) {
                    return true;
                }
            }
            return false;
        } else {
            return dfs(word, i + 1, node.children[c - 'a']);
        }
    }*/
}
/**
 * 字典树节点
 */
class Node1{
    Node1[] children;     // 子节点列表
    boolean isEnd;       // 标记是否尾节点

    public Node1(){
        children = new Node1[26];
        isEnd = false;
    }
}
