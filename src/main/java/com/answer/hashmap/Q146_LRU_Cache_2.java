package com.answer.hashmap;

import java.util.*;

public class Q146_LRU_Cache_2 {
    /**
     * LRU 缓存算法的核心数据结构就是哈希链表LinkedHashMap
     * 从尾部插入，也就是说靠尾部的数据是最近使用的，靠头部的数据是最久为使用的。
     */
    int cap;
    LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<>();

    public Q146_LRU_Cache_2(int capacity) {
        this.cap = capacity;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        // 将 key 变为最近使用
        makeRecently(key);
        return cache.get(key);
    }

    public void put(int key, int val) {
        if (cache.containsKey(key)) {
            cache.put(key, val);  // 修改 key 的值
            makeRecently(key);// 将 key 变为最近使用
            return;
        }

        if (cache.size() >= this.cap) {
            int oldestKey = cache.keySet().iterator().next();   // 链表头部就是最久未使用的 key
            cache.remove(oldestKey);
        }
        cache.put(key, val);  // 将新的 key 添加链表尾部
    }

    private void makeRecently(int key) {
        int val = cache.get(key);
        cache.remove(key);// 删除 key，重新插入到队尾
        cache.put(key, val);
    }
}
