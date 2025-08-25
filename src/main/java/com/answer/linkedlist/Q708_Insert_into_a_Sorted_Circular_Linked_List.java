package com.answer.linkedlist;

public class Q708_Insert_into_a_Sorted_Circular_Linked_List {
    /**
     * https://leetcode.cn/problems/4ueAj6/
     * LCR 029. 循环有序列表的插入
     */
    /**
     * Given a node from a cyclic linked list which is sorted in ascending order, write a function to insert a value into the list such that it remains a cyclic sorted list. The given node can be a reference to any single node in the list, and may not be necessarily the smallest value in the cyclic list.
     * If there are multiple suitable places for insertion, you may choose any place to insert the new value. After the insertion, the cyclic list should remain sorted.
     * If the list is empty (i.e., given node is null), you should create a new single cyclic list and return the reference to that single node. Otherwise, you should return the original given node.
     * 循环有序列表的插入
     * 给定循环升序（非严格）链表中的一个节点，写一个函数向这个链表中插入一个新节点，使得链表依然保持有序。链表中的节点具有唯一的值，但可能有多个节点有相同的值。输入的节点可以是链表中的任意一个节点，不一定是最小值节点。
     * 如果链表为空（即给定的节点为 null），则创建一个新的循环链表并返回新节点。
     * 否则，返回插入后的任意一个节点（保持循环特性即可）。
     *
     * 示例 1
     *   输入：head = [3,4,1], insertVal = 2
     *  输出：[3,4,1,2]
     *  解释：在该示例中，有一个包含 3 个节点的循环有序列表，其中各个节点的值分别是 3、4 和 1，插入值 2 后，链表变为 3->4->1->2，保持循环有序。
     * 示例 2
     *  输入：head = [], insertVal = 1
     *  输出：[1]
     *  解释：链表为空，创建一个新节点 1，构成循环链表。
     * 示例 3
     *  输入：head = [1], insertVal = 0
     *  输出：[1,0]
     */
    public static void main(String[] args) {
        Q708_Insert_into_a_Sorted_Circular_Linked_List test = new Q708_Insert_into_a_Sorted_Circular_Linked_List();
        // Test 1: Insert into [3,4,1], insert 2 => [3,4,1,2]
        Node head1 = test.makeCircularList(new int[]{3, 4, 1});
        Node res1 = test.insert_1(head1, 2);
        System.out.print("Test 1: ");
        test.printCircularList(res1, 4); // Expected: 3 4 1 2 (or rotation)
        // Test 2: Insert into [1], insert 0 => [1,0]
        Node head2 = test.makeCircularList(new int[]{1});
        Node res2 = test.insert_1(head2, 0);
        System.out.print("Test 2: ");
        test.printCircularList(res2, 2); // Expected: 1 0
        // Test 3: Insert into [], insert 5 => [5]
        Node head3 = test.makeCircularList(new int[]{});
        Node res3 = test.insert_1(head3, 5);
        System.out.print("Test 3: ");
        test.printCircularList(res3, 1); // Expected: 5
        // Test 4: Insert into [1,1,1], insert 0 => [1,1,1,0]
        Node head4 = test.makeCircularList(new int[]{1,1,1});
        Node res4 = test.insert_1(head4, 0);
        System.out.print("Test 4: ");
        test.printCircularList(res4, 4); // Expected: 1 1 1 0
        // Test 5: Insert into [1,3,5], insert 4 => [1,3,4,5]
        Node head5 = test.makeCircularList(new int[]{1,3,5});
        Node res5 = test.insert_1(head5, 4);
        System.out.print("Test 5: ");
        test.printCircularList(res5, 4); // Expected: 1 3 4 5
    }
    /**
     * 一次遍历
     */
    public Node insert_0(Node head, int insertVal) {
        Node node = new Node(insertVal);
        if (head == null) {
            node.next = node;
            return node;
        }
        if (head.next == head) {
            head.next = node;
            node.next = head;
            return head;
        }
        Node curr = head, next = head.next;
        while (next != head) {
            if (insertVal >= curr.val && insertVal <= next.val) {
                break;
            }
            if (curr.val > next.val) {
                if (insertVal > curr.val || insertVal < next.val) {
                    break;
                }
            }
            curr = curr.next;
            next = next.next;
        }
        curr.next = node;
        node.next = next;
        return head;
    }
    /**
     * 情况1：正常插入，insertVal 介于 prev 和 curr 之间。
     * 情况2：到达最大值和最小值的分界点（如 3->4->1），此时插入最大值或最小值。
     * 情况3：所有节点值都相等，或者遍历一圈都没有插入位置，直接插入。
     */
    public Node insert(Node head, int insertVal) {
        if(head == null){
            Node newNode = new Node(insertVal);
            newNode.next = newNode;
            return newNode;
        }
        Node pre = head;
        Node cur = head.next;
        boolean isFound = false;
        do {
            if(pre.val <= insertVal && insertVal <= cur.val){ // Case 1: insertVal fits between prev and curr
                isFound = true;
            } else if(pre.val > cur.val){ // Case 2: At the "end" of the sorted list 当插入节点为最小值或者最大值
                if(pre.val <= insertVal || insertVal <= cur.val){ // 满足 比最小的还小 或 比最大的还大
                    isFound = true;
                }
            }

            if(isFound){
                Node node = new Node(insertVal);
                pre.next = node;
                node.next = cur;
                return head;
            }
            pre = pre.next;
            cur = cur.next;
        } while (cur != head);
        // Case 3: All values are the same or didn't fit anywhere 当链表所有元素都相同时
        Node node = new Node(insertVal);
        pre.next = node;
        node.next = cur;
        return head;
    }
    /**
     * 找 下一个节点 >= insert && 当前节点 <= insert 的节点
     * 没有找到说明是最大或者最小值，记录值最大的最后一个节点
     */
    public Node insert_1(Node head, int insertVal) {
        if(head == null){
            Node newNode = new Node(insertVal);
            newNode.next = newNode;
            return newNode;
        }
        Node newNode = new Node(insertVal);
        Node biggest = head, cur = head;
        int biggestVal = head.val;//最大值
        while(true) {
            if(cur.val <= insertVal && cur.next.val >= insertVal) {	//找到了
                newNode.next = cur.next;
                cur.next = newNode;
                return head;
            }
            if(cur.val >= biggestVal) {	//记录最大值节点
                biggestVal = cur.val;
                biggest = cur;
            }
            if(cur.next == head) {//转了一圈了
                break;
            }
            cur = cur.next;
        }
        newNode.next = biggest.next;//插入的是最大值或最小值
        biggest.next = newNode;
        return head;
    }

    // For testing
    public Node makeCircularList(int[] arr) {
        if (arr.length == 0) {
            return null;
        }
        Node head = new Node(arr[0]);
        Node curr = head;
        for (int i = 1; i < arr.length; i++) {
            curr.next = new Node(arr[i]);
            curr = curr.next;
        }
        curr.next = head;
        return head;
    }

    // Helper to print circular list (n nodes)
    public void printCircularList(Node head, int n) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        Node curr = head;
        for (int i = 0; i < n; i++) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    // Definition for a Node.
    class Node {
        public int val;
        public Node next;

        public Node(int val) {
            this.val = val;
        }
    }
}
