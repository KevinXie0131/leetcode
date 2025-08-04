package com.answer.linkedlist;

import java.util.*;
import java.util.Random;

public class Q382_Linked_List_Random_Node {
    /**
     * 链表随机节点
     * 给你一个单链表，随机选择链表的一个节点，并返回相应的节点值。每个节点 被选中的概率一样 。
     * 实现 Solution 类：
     *  Solution(ListNode head) 使用整数数组初始化对象。
     *  int getRandom() 从链表中随机选择一个节点并返回该节点的值。链表中所有节点被选中的概率相等。
     *
     */
}
/**
 * 记录所有链表元素
 * 我们可以在初始化时，用一个数组记录链表中的所有元素，这样随机选择链表的一个节点，就变成在数组中随机选择一个元素。
 */
class Solution0 {
    List<Integer> list;
    Random random;

    public Solution0(ListNode head) {
        list = new ArrayList<Integer>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        random = new Random();
    }

    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}
/**
 * 数组存储法
 *  初始化时遍历链表，把节点值存入数组。
 *  需要额外空间，访问随机元素很方便。
 *  空间复杂度为 O(n)。
 */
class Solution1 {
    private int[] candidates;
    private int size;
    private Random random;

    public Solution1(ListNode head) {
        size = 0;
        ListNode cur = head;
        while(cur != null){
            size++;
            cur = cur.next;
        }
        candidates = new int[size];
        cur = head;
        int i = 0;
        while(cur != null){
            candidates[i++] = cur.val;
            cur = cur.next;
        }
        this.random = new Random();
    }
    // getRandom方法遍历链表，对于链表的第i个节点（i从1开始计数），以1/i概率选择该节点值更新结果。
    public int getRandom() {
        return candidates[random.nextInt(size)];
    }
}
/**
 * 水塘抽样法（Reservoir Sampling）: 由于空间小，时间复杂度低，可以用于大数据流中的随机抽样问题。
 *  不知道链表长度时的经典算法。
 *  一边遍历链表一边做随机替换，保证每个节点被选中概率相等。
 *  只用 O(1) 额外空间。适合长度未知或特别大的链表。
 *  时间复杂度为 O(n)。
 */
class Solution {
    private ListNode head;
    private Random random;

    public Solution(ListNode head) {
        this.head = head;
        this.random = new Random();
    }
    // getRandom方法遍历链表，对于链表的第i个节点（i从1开始计数），以1/i概率选择该节点值更新结果。
    public int getRandom() {
        int result = head.val;
        ListNode current = head.next;
        int index = 2;

        while (current != null) {
            // 生成 [0, index-1] 范围的随机数
            if (random.nextInt(index) == 0) {  // 1/i 的概率选中（替换为答案）
                result = current.val;
            }
            current = current.next;
            index++;
        }
        return result;
    }
}
/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */
