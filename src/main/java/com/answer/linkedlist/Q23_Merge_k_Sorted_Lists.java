package com.answer.linkedlist;

import java.util.*;

public class Q23_Merge_k_Sorted_Lists { // Hard 困难
    /**
     * 合并 K 个升序链表
     * 给你一个链表数组，每个链表都已经按升序排列。
     * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
     * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
     * Merge all the linked-lists into one sorted linked-list and return it.
     * 示例 1：
     *  输入：lists = [[1,4,5],[1,3,4],[2,6]]
     *  输出：[1,1,2,3,4,4,5,6]
     *  解释：链表数组如下：
     *  [ 1->4->5,
     *    1->3->4,
     *    2->6 ]
     *  将它们合并到一个有序链表中得到: 1->1->2->3->4->4->5->6
     */
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
     *
     * 本题与 Q21. 合并两个有序链表 思路类似，都是找到剩余链表中最小的数字插入结果，不同的是，
     * 本题使用一个小根堆加速这个查找的过程。
     */
    public ListNode mergeKLists_1(ListNode[] lists) { // 使用优先队列合并
        //  Queue<ListNode> pq = new PriorityQueue<>((v1, v2) -> {return v1.val - v2.val;}); // works too
        Queue<ListNode> pq = new PriorityQueue<>((v1, v2) -> v1.val - v2.val); // (e1, e2)->e1.val-e2.val 需要加上
        for (ListNode node: lists) { // 将所有非空链表链表的头节点加入堆中
            if (node != null) {
                pq.offer(node);  // 初始化堆
            }
        }
        ListNode dummyHead = new ListNode(0); // 哨兵节点，作为合并后链表头节点的前一个节点
        ListNode tail = dummyHead;
        while (!pq.isEmpty()) { // 循环直到堆为空
            ListNode minNode = pq.poll(); // 剩余节点中的最小节点
            tail.next = minNode;  // 把 minNode 添加到新链表的末尾
            tail = minNode; // tail = tail.next; // works too  // 准备合并下一个节点
            if (minNode.next != null) { // 下一个节点不为空
                pq.offer(minNode.next);  // 下一个节点有可能是最小节点，入堆
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
        } else {
            l2.next = merge2Lists(l1, l2.next);
            return l2;
        }
      //  l2.next = merge2Lists(l1, l2.next); // works too
      //  return l2;
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
        tail.next = l1 == null ? l2: l1;
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
     * 直接自底向上合并链表, 两两合并/四四合并/八八合并, 依此类推，直到所有链表都合并到 lists[0] 中。最后返回 lists[0]
     */
    public ListNode mergeKLists_5(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        int size = lists.length;
        while (size > 1) { // 只剩最后一个队列了 //当size为1时，及合并成了一条链表时结束循环，后返回合并后的结果
            int index = 0;
            for (int i = 0; i < size; i += 2) {
                if (i == size - 1) { // i 处于最后的奇数节点 //链表个数为奇数时，将剩余的未参与此次循环合并的最后一个链表，单独作为一条链表参加下次 while 循环合并
                    lists[index++] = lists[i]; // 直接加入
                } else { //将两条链表合并成一条链表后参加下一次 while 循环合并
                    lists[index++] = merge2Lists(lists[i], lists[i + 1]);
                }
            }
            size = index; //将当前经过“两两”合并后的链表个数赋值给size
        }
        return lists[0];
    }
    /**
     * 新建一个ArrayList，将所有的节点直接放入ArrayList中
     * 使用Collections.sort对ArrayList进行排序
     * 将排序后的值重新组合成链表
     */
    public ListNode mergeKLists6(ListNode[] lists) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        int size = 0;
        for (int i = 0 ; i < lists.length ; i++){
            while (lists[i] != null){
                arrayList.add(lists[i].val);
                size++;
                lists[i] = lists[i].next;
            }
        }

        Collections.sort(arrayList);

        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        for (int j = 0 ; j < size ; j++){
            cur.next = new ListNode(arrayList.get(j));
            cur = cur.next;
        }
        cur.next = null;
        return dummy.next;
    }
    /**
     * 定义一个数组哈希表
     *  对链表数组中每个链表的每个节点进行遍历
     *  在数组哈希表记录每个数值对应的节点个数，有keyArr[cur.val + 10000]个节点的数值为cur.val，
     *  因为节点的值可能为负，且最大为-10000，所以下 标要偏移10000
     *  遍历数组哈希表，对链表节点进行连接
     */
    public ListNode mergeKLists7(ListNode[] lists) {
        ListNode dummy = new ListNode();   //伪头节点，方便返回结果
        int[] keyArr = new int[2 * 10000 + 1];   //定义一个数组哈希表
        //对链表数组中每个链表的每个节点进行遍历
        //在数组哈希表记录每个数值对应的节点个数，有keyArr[cur.val + 10000]个节点的数值为cur.val
        //因为节点的值可能为负，且最大为-10000，所以下标要偏移10000
        for (int i = 0; i < lists.length;i++) {
            ListNode cur = lists[i];
            while (cur != null) {
                keyArr[cur.val + 10000]++;
                cur = cur.next;
            }
        }
        ListNode temp = dummy;

        for (int i = 0; i < keyArr.length; i++) { //遍历数组哈希表
            if (keyArr[i] == 0) {    //keyArr[i] == 0表示没有节点的值等于该数值，则直接跳过
                continue;
            } else {
                while (keyArr[i]-- > 0) {    //在temp后面循环连接keyArr[i]个节点值为(i-10000)的节点
                    temp.next = new ListNode(i - 10000);
                    temp = temp.next;
                }
            }
        }
        return dummy.next;
    }
    /**
     * 同上 use TreeMap
     */
    public ListNode mergeKLists7a(ListNode[] lists) {
        ListNode dummy = new ListNode();
        Map<Integer, Integer> map = new TreeMap<>();

        for (int i = 0; i < lists.length;i++) {
            ListNode cur = lists[i];
            while (cur != null) {
                map.put(cur.val, map.getOrDefault(cur.val, 0) + 1);
                cur = cur.next;
            }
        }

        ListNode temp = dummy;

        for (Integer key : map.keySet()) {
            int count = map.get(key);
            while (count-- > 0) {
                temp.next = new ListNode(key);
                temp = temp.next;
            }
        }
        return dummy.next;
    }
}
