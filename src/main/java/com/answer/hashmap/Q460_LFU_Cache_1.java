package com.answer.hashmap;

import java.util.*;

public class Q460_LFU_Cache_1 {
    /**
     * 当缓存达到其容量 capacity 时，则应该在插入新项之前，移除最不经常使用的项。
     * 在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最久未使用 的键。
     *
     * 问：一个双向链表需要几个哨兵节点？
     * 答：一个就够了。一开始哨兵节点 dummy 的 prev 和 next 都指向 dummy。随着节点的插入，dummy 的 next 指向链表的第一个节点（最上面的书），prev 指向链表的最后一个节点（最下面的书）。
     * 问：为什么 minFreq 一定对应着最左边的非空的那摞书？
     * 答：在添加一本新书的情况下，这本新书一定是放在 freq=1 的那摞书上，此时我们把 minFreq 置为 1。在「抽出一本书且这摞书变成空」的情况下，我们会把这本书放到它右边这摞书的最上面。如果变成空的那摞书是最左边的，我们还会把 minFreq 加一。所以无论如何，minFreq 都会对应着最左边的非空的那摞书。
     * 问：有没有一些让代码变快的方法？
     * 答：有两处「移除空链表」的逻辑是可以去掉的，代码（可能）会更快。
     */
    private class Node {
        int key, value, freq = 1; // 新书只读了一次
        Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> keyToNode = new HashMap<>();
    private final Map<Integer, Node> freqToDummy = new HashMap<>();
    private int minFreq;

    public Q460_LFU_Cache_1(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = getNode(key);
        return node != null ? node.value : -1;
    }

    public void put(int key, int value) {
        Node node = getNode(key);
        if (node != null) { // 有这本书
            node.value = value; // 更新 value
            return;
        }
        // 移除最不经常使用的项, 具有相同使用频率时，应该去除 最久未使用 的键
        if (keyToNode.size() == capacity) { // 书太多了
            Node dummy = freqToDummy.get(minFreq);
            Node backNode = dummy.prev; // 最左边那摞书的最下面的书
            keyToNode.remove(backNode.key);
            remove(backNode); // 移除
            if (dummy.prev == dummy) { // 这摞书是空的
                freqToDummy.remove(minFreq); // 移除空链表
            }
        }
        node = new Node(key, value); // 新书
        keyToNode.put(key, node);
        pushFront(1, node); // 放在「看过 1 次」的最上面
        minFreq = 1;
    }

    private Node getNode(int key) {
        if (!keyToNode.containsKey(key)) { // 没有这本书
            return null;
        }
        Node node = keyToNode.get(key); // 有这本书
        remove(node); // 把这本书抽出来
        Node dummy = freqToDummy.get(node.freq);
        if (dummy.prev == dummy) { // 抽出来后，这摞书是空的
            freqToDummy.remove(node.freq); // 移除空链表
            if (minFreq == node.freq) { // 这摞书是最左边的
                minFreq++;
            }
        }
        pushFront(++node.freq, node); // 放在右边这摞书的最上面
        return node;
    }

    // 创建一个新的双向链表
    private Node newList() {
        Node dummy = new Node(0, 0); // 哨兵节点
        dummy.prev = dummy;
        dummy.next = dummy;
        return dummy;
    }

    // 在链表头添加一个节点（把一本书放在最上面）
    private void pushFront(int freq, Node x) {
        Node dummy = freqToDummy.computeIfAbsent(freq, k -> newList());
        x.prev = dummy;
        x.next = dummy.next;
        x.prev.next = x;
        x.next.prev = x;
    }

    // 删除一个节点（抽出一本书）
    private void remove(Node x) {
        x.prev.next = x.next;
        x.next.prev = x.prev;
    }
}
