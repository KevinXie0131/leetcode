package com.answer.linkedlist;

import java.util.*;

public class Q23_Merge_k_Sorted_Lists {
    /**
     * 方法1： K 指针：K 个指针分别指向 K 条链表
     * 每次 O(K) 比较 K个指针求 min, 时间复杂度：O(NK)
     */
    public ListNode mergeKLists(ListNode[] lists) {
        int k = lists.length;
        ListNode dummyHead = new ListNode(0);
        ListNode tail = dummyHead;
        while (true) {
            ListNode minNode = null;
            int minNodeIndex = -1;
            for (int i = 0; i < k; i++) {
                if (lists[i] == null) {
                    continue;
                }
                if (minNode == null || lists[i].val < minNode.val) {
                    minNode = lists[i]; // 找到下一个最小的node
                    minNodeIndex = i;
                }
            }
            if (minNodeIndex == -1) { // 到达所以list的末尾
                break;
            }
            tail.next = minNode;
            tail = tail.next;
            lists[minNodeIndex] = lists[minNodeIndex].next; // 移动选取的list
        }
        return dummyHead.next;
    }
    /**
     * 方法2. 使用小根堆对 1 进行优化，每次 O(logK) 比较 K个指针求 min
     * 时间复杂度：O(NlogK)， 其中 k 为 lists 的长度，n 为所有链表的节点数之和。
     * 空间复杂度：O(k)。堆中至多有 k 个元素。
     */
    public ListNode mergeKLists_1(ListNode[] lists) {
        Queue<ListNode> pq = new PriorityQueue<>((v1, v2) -> v1.val - v2.val); // (e1, e2)->e1.val-e2.val 需要加上
        for (ListNode node: lists) { // 将每个链表的头节点加入堆中
            if (node != null) {
                pq.offer(node);
            }
        }
        ListNode dummyHead = new ListNode(0);
        ListNode tail = dummyHead;
        while (!pq.isEmpty()) {
            ListNode minNode = pq.poll();
            tail.next = minNode;
            tail = minNode;
            if (minNode.next != null) {
                pq.offer(minNode.next);
            }
        }

        return dummyHead.next;
    }
    /**
     * 方法3: 逐一合并两条链表 时间复杂度：O(NK)
     * 先合并前两个链表，再把得到的新链表和第三个链表合并，再和第四个链表合并，依此类推。
     */
    public ListNode mergeKLists_2(ListNode[] lists) {
        ListNode head = null;
        for(ListNode node : lists){
            head =  merge2Lists(head, node); // 顺序合并
        }
        return head;
    }
    // 合并两条有序链表 — 递归
    ListNode merge2Lists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = merge2Lists(l1.next, l2);
            return l1;
        }
        l2.next = merge2Lists(l1, l2.next);
        return l2;
    }
    // 合并两条有序链表 — 迭代
    ListNode merge2Lists1(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode tail = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        tail.next = l1 == null? l2: l1;
        return dummyHead.next;
    }
    /**
     * 方法4: 分而治之 - 两两合并对 1 进行优化，时间复杂度：O(NlogK)
     * 时间复杂度分析：K 条链表的总结点数是 N，平均每条链表有 N/K 个节点，因此合并两条链表的时间复杂度是 O(N/K)。
     * 从 K 条链表开始两两合并成 1 条链表，因此每条链表都会被合并 logK 次，因此 K 条链表会被合并 K∗logK 次，
     * 因此总共的时间复杂度是 K∗logK∗N/K 即 O（NlogK）。
     * 空间复杂度：O(logk)。递归深度为 O(logk)，需要用到 O(logk) 的栈空间。
     *
     * 按照一分为二再合并的逻辑，递归像是在后序遍历一棵平衡二叉树。由于平衡树的高度是 O(logk)，
     * 所以每个链表节点只会出现在 O(logk) 次合并中！这样就做到了更快的 O(nlogk) 时间。
     */
    public ListNode mergeKLists_4(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        return merge(lists, 0, lists.length - 1); // 分治合并 两两合并 - 递归
    }

    private ListNode merge(ListNode[] lists, int lo, int hi) {
        if (lo == hi) {
            return lists[lo];
        }
        int mid = lo + (hi - lo) / 2;
        ListNode l1 = merge(lists, lo, mid); // 合并左半部分
        ListNode l2 = merge(lists, mid + 1, hi); // 合并右半部分
        return merge2Lists(l1, l2);  // 最后把左半和右半合并
    }
    /**
     * 两两合并 - 迭代
     */
    public ListNode mergeKLists_5(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        int size = lists.length;
        while (size > 1) { // 只剩最后一个队列了
            int index = 0;
            for (int i = 0; i < size; i += 2) {
                if (i == size - 1) { // i 处于最后的奇数节点
                    lists[index++] = lists[i]; // 直接加入
                } else {
                    lists[index++] = merge2Lists(lists[i], lists[i + 1]);
                }
            }
            size = index;
        }
        return lists[0];
    }
}
