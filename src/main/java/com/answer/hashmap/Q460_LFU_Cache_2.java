package com.answer.hashmap;

import java.util.*;

public class Q460_LFU_Cache_2 {
    /**
     * get(int key) returns the value if it exists, else -1, and updates the frequency.
     * put(int key, int value) inserts or updates a key-value pair, evicting the least frequently used key if needed.
     * 双哈希表
     */
    private final int capacity;
    private int minFreq; // minFreq的变量，用来记录LFU缓存中频率最小的元素
    private final Map<Integer, Integer> keyToVal;
    private final Map<Integer, Integer> keyToFreq;
    private final Map<Integer, LinkedHashSet<Integer>> freqToKeys;

    public Q460_LFU_Cache_2(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
        this.keyToVal = new HashMap<>();
        this.keyToFreq = new HashMap<>();
        this.freqToKeys = new HashMap<>();
    }

    public int get(int key) {
        if (!keyToVal.containsKey(key)) {
            return -1;
        }
        int freq = keyToFreq.get(key);
        freqToKeys.get(freq).remove(key);

        if (freqToKeys.get(freq).size() == 0) {
            freqToKeys.remove(freq);
            if (minFreq == freq) {
                minFreq++;
            }
        }

        keyToFreq.put(key, freq + 1);
        freqToKeys.computeIfAbsent(freq + 1, ignore -> new LinkedHashSet<>()).add(key);
        return keyToVal.get(key);
    }

    public void put(int key, int value) {
        if (capacity <= 0){
            return;
        }

        if (keyToVal.containsKey(key)) {
            keyToVal.put(key, value);
            get(key); // update freq
            return;
        }
        // LFU淘汰的时候会选择两个维度，先比较频率，选择访问频率最小的元素；如果频率相同，则按时间维度淘汰掉最久远的那个元素。
        if (keyToVal.size() >= capacity) {
            int evictKey = freqToKeys.get(minFreq).iterator().next();
            freqToKeys.get(minFreq).remove(evictKey);
            if (freqToKeys.get(minFreq).size() == 0) {
                freqToKeys.remove(minFreq);
            }
            keyToVal.remove(evictKey);
            keyToFreq.remove(evictKey);
        }

        keyToVal.put(key, value);
        keyToFreq.put(key, 1);
        freqToKeys.computeIfAbsent(1, ignore -> new LinkedHashSet<>()).add(key);
        minFreq = 1;
    }
}
