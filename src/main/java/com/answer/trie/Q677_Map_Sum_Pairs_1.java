package com.answer.trie;

import java.util.*;

public class Q677_Map_Sum_Pairs_1 {
    public static void main(String[] args)  {
        Q677_Map_Sum_Pairs_1 mapSum = new Q677_Map_Sum_Pairs_1();
        mapSum.insert1("apple", 3);
        System.out.println(mapSum.sum1("ap"));// 返回 3 (apple = 3)
        mapSum.insert1("app", 2);
        System.out.println( mapSum.sum1("ap"));  // 返回 5 (apple + app = 3 + 2 = 5)
        mapSum.insert1("apple", 2);
        System.out.println(mapSum.sum1("ap"));
    }
    /**
     * 暴力扫描
     * 我们将所有的 key-val 键值进行存储，每次需要搜索给定的前缀 prefix 时，我们依次搜索所有的键值。
     * 如果键值包含给定的前缀，则我们将其 val 进行相加，返回所有符合要求的 val 的和。
     */
    Map<String, Integer> map;

    public void MapSum() {
        map = new HashMap<>();
    }

    public void insert(String key, int val) {
        map.put(key, val);
    }

    public int sum(String prefix) {
        int res = 0;
        for (String s : map.keySet()) {
            if (s.startsWith(prefix)) {
                res += map.get(s);
            }
        }
        return res;
    }
    /**
     * 前缀哈希映射
     * 我们可以用哈希表存储所有可能前缀的值。当我们得到一个新的 key-val 键值，我们将 key 的每个前缀 prefix[i]
     * 都在哈希表中进行存储，我们需要更新每个前缀 prefix[i] 对应的值。我们计算出它对应的值的增加为 delta，计算方式如下：
     *
     *  如果键 key 不存在，则此时 delta 等于 val。
     *  如果键 key 存在，则此时键 key 对应得前缀的值都增加 val−map[key]，其中 map[key] 表示键 key 当前对应的值。
     *  我们在完成前缀的值改写后，同时要更新键 key 对应的值为 val。
     * 求 sum 时,我们直接利用哈希表查找给定的前缀对应的值即可。
     */
    Map<String, Integer> map1;
    Map<String, Integer> prefixmap;

    public Q677_Map_Sum_Pairs_1() {
        map1 = new HashMap<>();
        prefixmap = new HashMap<>();
    }

    public void insert1(String key, int val) {
        int delta = val - map1.getOrDefault(key, 0); // [[],["apple",3],["ap"],["app",2],["apple",2],["ap"]]
        map1.put(key, val);
        for (int i = 1; i <= key.length(); ++i) {
            String curPrefix = key.substring(0, i);
            prefixmap.put(curPrefix, prefixmap.getOrDefault(curPrefix, 0) + delta);
        }
    }

    public int sum1(String prefix) {
        return prefixmap.getOrDefault(prefix, 0);
    }
    /**
     * 字典树
     * 由于我们要处理前缀，自然而然联想到可以用 Trie（前缀树）来处理此问题。处理方法跟方法二的原理一样，
     * 我们直接在前缀对应的 Trie 的每个节点存储该前缀对应的值。
     *
     * insert 操作：原理与方法二一样，我们首先求出前缀对应的值的改变 delta，我们直接在 Trie 节点上更新键 key 的每个前缀对应的值。
     * sum 操作: 我们直接在前缀树上搜索该给定的前缀对应的值即可，如果给定的前缀不在前缀树中，则返回 0。
     * 当然在实际中我们也可以在 Trie 的节点只存储键 key 对应的 val, 每次求 sum 时利用 DFS 或者 BFS 遍历前缀树的子树即可。
     */
    class TrieNode {
        int val = 0;
        TrieNode[] next = new TrieNode[26];
    }

    TrieNode root;
    Map<String, Integer> map2;

    public void MapSum2() {
        root = new TrieNode();
        map2 = new HashMap<>();
    }

    public void insert2(String key, int val) {
        int delta = val - map2.getOrDefault(key, 0);
        map2.put(key, val);
        TrieNode node = root;

        for (char c : key.toCharArray()) {
            if (node.next[c - 'a'] == null) {
                node.next[c - 'a'] = new TrieNode();
            }
            node = node.next[c - 'a'];
            node.val += delta;
        }
    }

    public int sum2(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            if (node.next[c - 'a'] == null) {
                return 0;
            }
            node = node.next[c - 'a'];
        }
        return node.val;
    }
}
