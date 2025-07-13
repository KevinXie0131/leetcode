package com.answer.hashmap;

import java.util.*;

public class Q706_Design_HashMap {
    /**
     * 设计哈希映射
     * 不使用任何内建的哈希表库设计一个哈希映射（HashMap）。
     *
     * 实现 MyHashMap 类：
     *  MyHashMap() 用空映射初始化对象
     *  void put(int key, int value) 向 HashMap 插入一个键值对 (key, value) 。如果 key 已经存在于映射中，则更新其对应的值 value 。
     *  int get(int key) 返回特定的 key 所映射的 value ；如果映射中不包含 key 的映射，返回 -1 。
     *  void remove(key) 如果映射中存在 key 的映射，则移除 key 和它所对应的 value 。
     *
     * 示例：
     * 输入：["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
     *       [[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
     * 输出：[null, null, null, 1, -1, null, 1, null, -1]
     * 解释：MyHashMap myHashMap = new MyHashMap();
     *      myHashMap.put(1, 1); // myHashMap 现在为 [[1,1]]
     *      myHashMap.put(2, 2); // myHashMap 现在为 [[1,1], [2,2]]
     *      myHashMap.get(1);    // 返回 1 ，myHashMap 现在为 [[1,1], [2,2]]
     *      myHashMap.get(3);    // 返回 -1（未找到），myHashMap 现在为 [[1,1], [2,2]]
     *      myHashMap.put(2, 1); // myHashMap 现在为 [[1,1], [2,1]]（更新已有的值）
     *      myHashMap.get(2);    // 返回 1 ，myHashMap 现在为 [[1,1], [2,1]]
     *      myHashMap.remove(2); // 删除键为 2 的数据，myHashMap 现在为 [[1,1]]
     *      myHashMap.get(2);    // 返回 -1（未找到），myHashMap 现在为 [[1,1]]
     */
    /**
     * 链地址法
     * refer to Q705_Design_HashSet
     * 1). hash function design and 2). collision handling
     */
    private class Pair {
        private int key;
        private int value;

        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    private static final int BASE = 769;
    private LinkedList[] data;

    public Q706_Design_HashMap() {
        data = new LinkedList[BASE];
        for(int i = 0; i < BASE; i++){
            data[i] = new LinkedList<Pair>();
        }
    }

    public void put(int key, int value) {
        int h = hash(key);
        Iterator<Pair> iterator = data[h].iterator();
        while(iterator.hasNext()){
            Pair pair = iterator.next();
            if(pair.getKey() == key){ // key已存在，更新value
                pair.setValue(value);
                return;
            }
        }
        data[h].offerLast(new Pair(key, value)); // key不存在，新建一个
    }

    public int get(int key) {
        int h = hash(key);
        Iterator<Pair> iterator = data[h].iterator();
        while(iterator.hasNext()){
            Pair pair = iterator.next();
            if(pair.getKey() == key){ // key存在，返回value
                return pair.value;
            }
        }
        return -1; // key不存在，返回-1
    }

    public void remove(int key) {
        int h = hash(key);
        Iterator<Pair> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Pair pair = iterator.next();
            if (pair.getKey() == key) { // key存在，移除
                data[h].remove(pair);
                return;
            }
        }
    }

    private static int hash(int key) {
        return key % BASE;
    }
    /**
     * 利用链表chaining最大化减少空间浪费
     * 每个链表预设一个dummy head，这样head永远不会为空，省去判断为空的情况，略费空间
     */
    private static final int N = 1009;
    private MyListNode[] nums;

    public void MyHashMap1() {
        nums = new MyListNode[N];
        for (int i = 0; i < N; i++) {
            nums[i] = new MyListNode();
        }
    }

    private int getHashKey(int key) {
        return key % N;
    }

    public void put1(int key, int value) {
        int hashKey = getHashKey(key);
        MyListNode head = nums[hashKey];
        MyListNode p = head;
        MyListNode tail = p;
        while (p != null) {
            if (p.key == key) {
                p.val = value;
                return;
            }
            tail = p;
            p = p.next;
        }
        tail.next = new MyListNode(key, value);
    }

    public int get1(int key) {
        int hashKey = getHashKey(key);
        MyListNode head = nums[hashKey];
        MyListNode p = head;
        while (p != null) {
            if (p.key == key) {
                return p.val;
            }
            p = p.next;
        }
        return -1;
    }

    public void remove1(int key) {
        int hashKey = getHashKey(key);
        MyListNode head = nums[hashKey];
        MyListNode prev = head;
        MyListNode p = head.next;
        while (p != null) {
            if (p.key == key) {
                prev.next = p.next;
                return;
            }
            prev = p;
            p = p.next;
        }
    }

    class MyListNode {
        int key;
        int val;
        MyListNode next;

        public MyListNode() {
            this.key = -1;
            this.val = -1;
            this.next = null;
        }

        public MyListNode(int key, int val) {
            this.key = key;
            this.val = val;
            this.next = null;
        }
    }
    /**
     * 没有dummy head node，所有任何操作都要判断是否为空，代码会比较复杂，但是节省空间
     */
    private static final int N1 = 1009;
    private MyListNode[] nums1;

    public void MyHashMap32() {
        nums1 = new MyListNode[N1];
    }

    private int getHashKey2(int key) {
        return key % N1;
    }

    public void put2(int key, int value) {
        int hashKey = getHashKey2(key);
        MyListNode head = nums1[hashKey];
        if (head == null) {
            nums1[hashKey] = new MyListNode(key, value);
            return;
        }
        MyListNode p = head;
        MyListNode tail = p;
        while (p != null) {
            if (p.key == key) {
                p.val = value;
                return;
            }
            tail = p;
            p = p.next;
        }
        tail.next = new MyListNode(key, value);
    }

    public int get2(int key) {
        int hashKey = getHashKey2(key);
        MyListNode head = nums1[hashKey];
        if (head == null) {
            return -1;
        }
        MyListNode p = head;
        while (p != null) {
            if (p.key == key) {
                return p.val;
            }
            p = p.next;
        }
        return -1;
    }

    public void remove2(int key) {
        int hashKey = getHashKey2(key);
        MyListNode head = nums1[hashKey];
        if (head == null) {
            return;
        }
        MyListNode p = head;
        MyListNode prev = null;
        while (p != null) {
            if (p.key == key) {
                if (prev == null) {
                    nums1[hashKey] = head.next;
                } else {
                    prev.next = p.next;
                }
                return;
            }
            prev = p;
            p = p.next;
        }
    }
}
