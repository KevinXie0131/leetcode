package com.answer.trie;

public class Q676_Implement_Magic_Dictionary_2 {
    /**
     * 我们定义一个根节点 root，作为字典树的根节点。也是添加和搜索单词的起点。
     * buildDict：加入所有单词，递归生成每个单词每个字符的节点；
     * search：递归搜索一个单词，并尝试改变某一位字符：
     *  我们使用一个标识位 isChanged 标记是否改变了一位字符；
     *  如果没有改变，那么就可以改变当前字符：枚举当前节点的所有子节点，去掉当前字符本身对应的节点，递归搜索，同时标记 isChanged = True；
     *  无论有没有改变，我们都可以沿着当前字符继续递归搜索，isChanged 保持不变；
     *  最终如果可以递归整个字符串，说明找到一个字符串；此时还要校验 isChanged 是否为 True，有可能找到的是原来的字符串；
     */
    private Node2 root;     // 根节点

    public void MagicDictionary() {
        root = new Node2();
    }

    public void buildDict(String[] dictionary) {
        for(String word: dictionary){ // 加入字典中所有单词
            addWord(word);
        }
    }

    public boolean search(String searchWord) {
        return DFS_Search(searchWord, 0, root, false);  // 搜索替换一个字符后的单词是否存在
    }
    /**
     * 插入一个字符串word到字典树中
     */
    private void addWord(String word) {
        Node2 node = root;      // 从根节点开始构造这个word对应的路径节点
        int n = word.length();
        for(int i = 0; i < n; i++){
            int id = word.charAt(i) - 'a'; // 将当前字符添加到当前节点对应的子节点位置，然后递归更新
            if(node.children[id] == null){
                node.children[id] = new Node2();
            }
            node = node.children[id];
        }
        node.isEnd = true; // 最后一个节点的isEnd置为true，表示一个完整的字符串
    }
    /**
     * 回溯法查找字典树是否匹配word
     * @param word: 待匹配字符串
     * @param idx: 当前匹配word[idx]字符
     * @param node: 搜索待匹配字符是否存在的节点
     * @param isChanged: 是否已经替换字符
     */
    private boolean DFS_Search(String word, int idx, Node2 node, boolean isChanged){
        if(idx == word.length()) {
            return node.isEnd && isChanged;     // 字符匹配结束，返回当前节点是否为尾节点，并且替换过字符
        }
        if(!isChanged){
            // 当前尚未替换字符, 尝试替换word[idx]字符
            for(int i = 0; i < 26; i++){
                // 替换的字符不可以是原来的字符且替换字符在字典树中存在
                if(i == word.charAt(idx) - 'a' || node.children[i] == null) {
                    continue;
                }
                if(DFS_Search(word, idx + 1, node.children[i], true)) {
                    return true;    // 匹配成功直接返回
                }
            }
        }
        // 不管是否替换了字符，都可以不替换往下走
        Node2 child = node.children[word.charAt(idx) - 'a'];  // 获取当前字符对应的节点
        return child != null && DFS_Search(word, idx + 1, child, isChanged);    // 匹配成功直接返回
    }
}
/**
 * 字典树节点
 */
class Node2{
    Node2[] children;     // 子节点列表
    boolean isEnd;       // 标记是否尾节点

    public Node2(){
        children = new Node2[26];
        isEnd = false;
    }
}

