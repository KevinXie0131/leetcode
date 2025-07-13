package com.answer.hashmap;

import java.util.*;

public class Q705_Design_HashSet {
    /**
     * 设计哈希集合
     * 不使用任何内建的哈希表库设计一个哈希集合（HashSet）。
     *
     * 实现 MyHashSet 类：
     *  void add(key) 向哈希集合中插入值 key 。
     *  bool contains(key) 返回哈希集合中是否存在这个值 key 。
     *  void remove(key) 将给定值 key 从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。
     *
     * 示例：
     * 输入：["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove", "contains"]
     *      [[], [1], [2], [1], [3], [2], [2], [2], [2]]
     * 输出:[null, null, null, true, false, null, true, null, false]
     * 解释：MyHashSet myHashSet = new MyHashSet();
     *      myHashSet.add(1);      // set = [1]
     *      myHashSet.add(2);      // set = [1, 2]
     *      myHashSet.contains(1); // 返回 True
     *      myHashSet.contains(3); // 返回 False ，（未找到）
     *      myHashSet.add(2);      // set = [1, 2]
     *      myHashSet.contains(2); // 返回 True
     *      myHashSet.remove(2);   // set = [1]
     *      myHashSet.contains(2); // 返回 False ，（已移除）
     */
    /**
     * 链地址法: 为每个哈希值维护一个链表，并将具有相同哈希值的元素都放入这一链表当中
     */
    private static final int BASE = 769; // 应当将 base 取为一个质数。在这里，我们取 base=769
    private LinkedList[] data; // 链表数组，用于存储元素

    public Q705_Design_HashSet() {
        data = new LinkedList[BASE];
        for(int i = 0; i < BASE; i++){
            data[i] = new LinkedList<Integer>(); // 初始化每个链表
        }
    }

    public void add(int key) {
        int h = hash(key);  // 计算元素的哈希值
        Iterator<Integer> iterator = data[h].iterator(); //建立迭代器
        while(iterator.hasNext()){
            Integer element = iterator.next();
            if(element == key){ // 如果元素已经存在，直接返回
                return;
            }
        }
        data[h].offerLast(key);  // 在链表末尾添加元素
    }

    public void remove(int key) {
        int h = hash(key);  // 计算元素的哈希值
        Iterator<Integer> iterator = data[h].iterator(); // 建立迭代器
        while(iterator.hasNext()){
            Integer element = iterator.next();
            if(element == key){
                data[h].remove(element); // 删除元素
                return;
            }
        }
    }

    public boolean contains(int key) {
        int h = hash(key);  // 计算元素的哈希值
        Iterator<Integer> iterator = data[h].iterator(); // 建立迭代器
        while(iterator.hasNext()){
            Integer element = iterator.next();
            if(element == key){
                return true; // 如果找到了元素，返回true
            }
        }
        return false; // 没有找到元素，返回false
    }

    private static int hash(int key) {
        return key % BASE; // 除留余数法计算哈希值
    }

}
