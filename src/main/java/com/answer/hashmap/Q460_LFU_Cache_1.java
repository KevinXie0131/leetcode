package com.answer.hashmap;

import java.util.HashMap;
import java.util.Map;

public class Q460_LFU_Cache_1 {
    /**
     * 方法二：双哈希表
     *
     * 定义两个哈希表，第一个 freq_table 以频率 freq 为索引，每个索引存放一个双向链表，这个链表里存放所有使用频率为 freq 的缓存，
     * 缓存里存放三个信息，分别为键 key，值 value，以及使用频率 freq。第二个 key_table 以键值 key 为索引，
     * 每个索引存放对应缓存在 freq_table 中链表里的内存地址，这样我们就能利用两个哈希表来使得两个操作的时间复杂度均为 O(1)。
     * 同时需要记录一个当前缓存最少使用的频率 minFreq，这是为了删除操作服务的。
     */
    int minfreq, capacity;
    Map<Integer, Node> keyTable;
    Map<Integer, DoublyLinkedList> freqTable;

    public Q460_LFU_Cache_1(int capacity) {
        this.minfreq = 0;
        this.capacity = capacity;
        keyTable = new HashMap<Integer, Node>();
        freqTable = new HashMap<Integer, DoublyLinkedList>();
    }

    public int get(int key) {
        if (capacity == 0) {
            return -1;
        }
        if (!keyTable.containsKey(key)) {
            return -1;
        }
        Node node = keyTable.get(key);
        int val = node.val, freq = node.freq;
        freqTable.get(freq).remove(node);
        // 如果当前链表为空，我们需要在哈希表中删除，且更新minFreq
        if (freqTable.get(freq).size == 0) {
            freqTable.remove(freq);
            if (minfreq == freq) {
                minfreq += 1;
            }
        }
        // 插入到 freq + 1 中
        DoublyLinkedList list = freqTable.getOrDefault(freq + 1, new DoublyLinkedList());
        list.addFirst(new Node(key, val, freq + 1));
        freqTable.put(freq + 1, list);
        keyTable.put(key, freqTable.get(freq + 1).getHead());
        return val;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (!keyTable.containsKey(key)) {
            // 缓存已满，需要进行删除操作
            // 能够知道 freq_table 里目前最少使用频率的索引，同时因为我们保证了链表中从链表头到链表尾的插入时间是有序的，所以 freq_table[minFreq] 的链表中链表尾的节点即为使用频率最小且插入时间最早的节点，我们删除它同时根据情况更新 minFreq
            if (keyTable.size() == capacity) {
                // 通过 minFreq 拿到 freqTable[minFreq] 链表的末尾节点
                Node node = freqTable.get(minfreq).getTail();
                keyTable.remove(node.key);
                freqTable.get(minfreq).remove(node);
                if (freqTable.get(minfreq).size == 0) {
                    freqTable.remove(minfreq);
                }
            }
            DoublyLinkedList list = freqTable.getOrDefault(1, new DoublyLinkedList());
            list.addFirst(new Node(key, value, 1)); // 更新的时候为什么是插入到链表头，这其实是为了保证缓存在当前链表中从链表头到链表尾的插入时间是有序的，为下面的删除操作服务。
            freqTable.put(1, list);
            keyTable.put(key, freqTable.get(1).getHead());
            minfreq = 1; // 由于是新插入的，所以缓存的使用频率一定是 1，所以我们将缓存的信息插入到 freq_table 中 1 索引下的列表头即可，同时更新 key_table[key] 的信息，以及更新 minFreq = 1
        } else {
            // 与 get 操作基本一致，除了需要更新缓存的值
            Node node = keyTable.get(key);
            int freq = node.freq;
            freqTable.get(freq).remove(node);
            if (freqTable.get(freq).size == 0) {
                freqTable.remove(freq);
                if (minfreq == freq) {
                    minfreq += 1;
                }
            }
            DoublyLinkedList list = freqTable.getOrDefault(freq + 1, new DoublyLinkedList());
            list.addFirst(new Node(key, value, freq + 1));
            freqTable.put(freq + 1, list);
            keyTable.put(key, freqTable.get(freq + 1).getHead());
        }
    }

    private class Node {
        int key, val, freq;
        Node prev, next;

        Node() {
            this(-1, -1, 0);
        }

        Node(int key, int val, int freq) {
            this.key = key;
            this.val = val;
            this.freq = freq;
        }
    }

    private class DoublyLinkedList {
        Node dummyHead, dummyTail;
        int size;

        DoublyLinkedList() {
            dummyHead = new Node();
            dummyTail = new Node();
            dummyHead.next = dummyTail;
            dummyTail.prev = dummyHead;
            size = 0;
        }

        public void addFirst(Node node) {
            Node prevHead = dummyHead.next;
            node.prev = dummyHead;
            dummyHead.next = node;
            node.next = prevHead;
            prevHead.prev = node;
            size++;
        }

        public void remove(Node node) {
            Node prev = node.prev, next = node.next;
            prev.next = next;
            next.prev = prev;
            size--;
        }

        public Node getHead() {
            return dummyHead.next;
        }

        public Node getTail() {
            return dummyTail.prev;
        }
    }
}
