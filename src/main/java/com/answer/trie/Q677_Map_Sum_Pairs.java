package com.answer.trie;

public class Q677_Map_Sum_Pairs {
    /**
     * 键值映射
     * 设计一个 map ，满足以下几点:
     *  字符串表示键，整数表示值
     *  返回具有前缀等于给定字符串的键的值的总和
     *  实现一个 MapSum 类：
     *    MapSum() 初始化 MapSum 对象
     *    void insert(String key, int val) 插入 key-val 键值对，字符串表示键 key ，整数表示值 val 。如果键 key 已经存在，
     *    那么原来的键值对 key-value 将被替代成新的键值对。
     *    int sum(string prefix) 返回所有以该前缀 prefix 开头的键 key 的值的总和。
     * 示例 1：
     *  输入：["MapSum", "insert", "sum", "insert", "sum"]
     *       [[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
     *  输出：[null, null, 3, null, 5]
     *  解释：
     *  MapSum mapSum = new MapSum();
     *  mapSum.insert("apple", 3);
     *  mapSum.sum("ap");           // 返回 3 (apple = 3)
     *  mapSum.insert("app", 2);
     *  mapSum.sum("ap");           // 返回 5 (apple + app = 3 + 2 = 5)
     * key and prefix consist of only lowercase English letters.
     */
    public static void main(String[] args) {
        Q677_Map_Sum_Pairs mapSum = new Q677_Map_Sum_Pairs();
        mapSum.insert("apple", 3);
        System.out.println(mapSum.sum("ap"));// 返回 3 (apple = 3)
        mapSum.insert("app", 2);
        System.out.println( mapSum.sum("ap"));  // 返回 5 (apple + app = 3 + 2 = 5)
    }
    /**
     * Trie + DFS
     */
    Trie4 trie4;

    public Q677_Map_Sum_Pairs() {
        trie4 = new Trie4();
    }
    // 不能简单记录单词的结束位置，还要存储 key 对应的 val 是多少
    public void insert(String key, int val) {
        trie4.insert(key, val);
    }
    // 先对入参 prefix 进行字典树搜索，到达尾部后再使用 DFS 搜索后面的所有方案，并累加结果。
    public int sum(String prefix) {
        return trie4.searchPrefix(prefix);
    }
}

class Trie4 {
    private Trie4[] children; // 字典树的子节点
    private int value;

    public Trie4() {
        children = new Trie4[26]; //  //初始时每个都是26个小写字母
        value = 0;
    }

    public void insert(String word, int val) { // 插入字符串
        Trie4 node = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new Trie4();
            }
            node = node.children[index];  //指针下移
        }
        node.value = val;
    }

    public int searchPrefix(String word) {
        Trie4 node = this;
        // 找到前缀 prefix 的最后一个节点
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.children[index] != null) {
                node = node.children[index];
            } else {
                return 0; // 不存在这个前缀，返回0
            }
        }
        return getAllSum(node); // 从这个节点往下DFS
    }

    // 辅助函数，求以 node 为根节点的子树的节点和
    private int getAllSum(Trie4 node) {
        if (node == null){
            return 0;
        }
        int sum = 0;

        for (int i = 0; i < 26; i++) {
            sum += getAllSum(node.children[i]);
        }
        return sum + node.value;
    }
    /**
     * another form
     */
    private int getAllSum_1(Trie4 node) {
        if (node == null){
            return 0;
        }
        int sum = node.value;

        for (int i = 0; i < 26; i++) {
            sum += getAllSum(node.children[i]);
        }
        return sum;
    }
}
