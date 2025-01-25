package com.answer.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q212_Word_Search_II_2 {
    /**
     * Trie 前缀树（字典树）+ DFS + 回溯
     */
    private TrieNode root;   // 根节点

    public List<String> findWords(char[][] board, String[] words) {
        int m = board.length;
        int n = board[0].length;
        // 构建words的字典树
        root = new TrieNode();
        for(String word: words){
            if(word.length() > m * n){
                continue;  // 字符串长度超过二维矩阵尺寸，肯定无法构成
            }
            insert(word);
        }
        // 以二维网格的每个位置(i,j)为起点，寻找以其为首字符的所有字符串
        List<String> res = new ArrayList<>();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                dfs_Search(board, i, j, root, 0, res);
            }
        }
        return res;
    }
    /**
     * 将单词word插入字段数root
     */
    private void insert(String word) {
        TrieNode node = root;      // 从根节点开始构造这个word对应的路径节点
        int n = word.length();
        for(int i = 0; i < n; i++){
            char ch = word.charAt(i);
            if(!node.children.containsKey(ch)){
                // 字符ch对应的节点不存在，新建一个
                node.children.put(ch, new TrieNode());
            }
            // 更新node
            node = node.children.get(ch);
        }
        node.str = word;   // 尾节点记录单词，用于后序查找的时候快速得到
    }
    /**
     * 深度优先搜索的同时，判断当前路径构成的字符串是否为查找单词
     * @param board: 二维网格
     * @param r: 行号
     * @param c: 列号
     * @param node：当前字符对应的路径节点
     * @param len: 当前路径构成的字符串长度
     * @param res：结果集
     */
    private void dfs_Search(char[][] board, int r, int c, TrieNode node, int len, List<String> res){
        if(len > 10){
            return;     // 字符串长度超过10，返回
        }
        char ch = board[r][c];  // 获取当前行列对应的字符
        if(!node.children.containsKey(ch)){
            return;  // 当前字符对应的节点不存在，即构造的字符串不在words中
        }
        TrieNode last = node;  // 记录当前node
        node = node.children.get(ch);  // 更新当前node为当前字符对应得到的节点
        if(node.str.length() > 0){
            res.add(node.str);    // 当前节点记录了一个单词，则得到了一个words中的单词
            node.str = "";     // 匹配了单词，不重复匹配
        };
        if(node.children.size() == 0){
            // 当前节点没有后序字符了，那么这个节点一定是某个单词最后一个字符对应的节点。
            // 并且不是其他任何单词的前缀，因此匹配完了之后，可以将这个字符从其父节点的childran列表中删除。
            last.children.remove(ch);
            return;
        }
        len++;  // 更新长度
        board[r][c] = '*';  // 用特殊符号标记当前位置已使用
        // 四个方向转递递归
        if(r - 1 >= 0 && board[r - 1][c] != '*'){
            dfs_Search(board, r - 1, c, node, len, res);
        }
        if(r + 1 < board.length && board[r + 1][c] != '*'){
            dfs_Search(board, r + 1, c, node, len, res);
        }
        if(c - 1 >= 0 && board[r][c - 1] != '*'){
            dfs_Search(board, r, c - 1, node, len, res);
        }
        if(c + 1 < board[0].length && board[r][c + 1] != '*'){
            dfs_Search(board, r, c + 1, node, len, res);
        }
        board[r][c] = ch;   // 回溯，这个位置处理完了恢复成原来的字符
    }

    class TrieNode{
        Map<Character, TrieNode> children;     // 子节点列表
        String str;              // 如果是尾节点，存储对应的单词

        public TrieNode(){
            children = new HashMap<>();
            str = "";
        }
    }
}
