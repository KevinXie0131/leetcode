package com.answer.hashmap;

import java.util.*;

public class Q460_LFU_Cache_3 {
    /**
     * 哈希表 + 平衡二叉树
     * 本方法需要使用到「平衡二叉树」，我们可以直接使用 TreeSet
     */
    // 缓存容量，时间戳
    int capacity, time;
    Map<Integer, Node> key_table;
    TreeSet<Node> S;

    public Q460_LFU_Cache_3(int capacity) {
        this.capacity = capacity;
        this.time = 0;
        key_table = new HashMap<Integer, Node>();
        S = new TreeSet<Node>(); // 建立一个平衡二叉树 S 来保持缓存根据 (cnt，time) 双关键字
    }

    public int get(int key) {
        if (capacity == 0) {
            return -1;
        }

        if (!key_table.containsKey(key)) { // 如果哈希表中没有键 key，返回 -1
            return -1;
        }

        Node cache = key_table.get(key);  // 从哈希表中得到旧的缓存
        S.remove(cache); // 从平衡二叉树中删除旧的缓存
        cache.cnt += 1; // 将旧缓存更新
        cache.time = ++time;
        S.add(cache); // 将新缓存重新放入哈希表和平衡二叉树中
        key_table.put(key, cache);
        return cache.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (!key_table.containsKey(key)) {
            if (key_table.size() == capacity) { // 如果到达缓存容量上限
                key_table.remove(S.first().key);   // 从哈希表和平衡二叉树中删除最近最少使用的缓存
                S.remove(S.first());
            }

            Node cache = new Node(1, ++time, key, value); // 创建新的缓存
            key_table.put(key, cache);// 将新缓存放入哈希表和平衡二叉树中
            S.add(cache);
        } else {
            Node cache = key_table.get(key);  // 这里和 get() 函数类似
            S.remove(cache);
            cache.cnt += 1;
            cache.time = ++time;
            cache.value = value;
            S.add(cache);
            key_table.put(key, cache);
        }
    }
}

class Node implements Comparable<Node> {
    int cnt, time, key, value; //  cnt 表示缓存使用的频率，time 表示缓存的使用时间，key 和 value 表示缓存的键值

    Node(int cnt, int time, int key, int value) {
        this.cnt = cnt;
        this.time = time;
        this.key = key;
        this.value = value;
    }

    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof Node) {
            Node rhs = (Node) anObject;
            return this.cnt == rhs.cnt && this.time == rhs.time;
        }
        return false;
    }
    // 将 cnt（使用频率）作为第一关键字，time（最近一次使用的时间）作为第二关键字
    public int compareTo(Node rhs) {
        return cnt == rhs.cnt ? time - rhs.time : cnt - rhs.cnt; // 当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最久未使用 的键。
    }

    public int hashCode() {
        return cnt * 1000000007 + time;
    }
}
