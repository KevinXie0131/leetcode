package com.answer.linkedlist;

public class Q82_Remove_Duplicates_from_Sorted_List_II {
    /**
     * 删除排序链表中的重复元素 II
     * 给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表
     * Given the head of a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
     * Return the linked list sorted as well.
     * 示例 1：
     *  输入：head = [1,2,3,3,4,4,5]
     *  输出：[1,2,5]
     * 示例 2：
     *  输入：head = [1,1,1,2,3]
     *  输出：[2,3]
     */
    public static void main(String[] args) {
/*        ListNode node9 = new ListNode(5, null);
        ListNode node8 = new ListNode(5, node9);
        ListNode node7 = new ListNode(4, node8);
        ListNode node6 = new ListNode(4, node7);
        ListNode node5 = new ListNode(3, node6);
        ListNode node4 = new ListNode(3, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1= new ListNode(1,node2);*/

        // [1,1,1,2,3]
        ListNode node5 = new ListNode(3, null);
        ListNode node4 = new ListNode(2, node5);
        ListNode node3 = new ListNode(1, node4);
        ListNode node2 = new ListNode(1, node3);
        ListNode node1= new ListNode(1,node2);

        ListNode node = deleteDuplicates_Recursive_1(node1);
        node.print();
    }
    /**
     * dummy node 一次遍历
     * 由于给定的链表是排好序的，因此重复的元素在链表中出现的位置是连续的，因此我们只需要对链表进行一次遍历，
     * 就可以删除重复的元素。由于链表的头节点可能会被删除，因此我们需要额外使用一个哑节点（dummy node）指向链表的头节点。
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode cur = dummy;
        // 细节: 需要注意 cur.next 以及 cur.next.next 可能为空节点，如果不加以判断，可能会产生运行错误。
        while(cur.next != null && cur.next.next != null){
            if(cur.next.val == cur.next.next.val){
                int val = cur.next.val;
                cur.next = cur.next.next;

                while(cur.next != null && cur.next.val == val){ // 将链表中所有元素值为 val 的节点全部删除
                    cur.next = cur.next.next;
                }
            }else{
                cur = cur.next;
            }
        }
        return dummy.next;
    }
    /**
     * anther form
     */
    public ListNode deleteDuplicates_0(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            int val = cur.next.val;
            if (cur.next.next.val == val) { // 后两个节点值相同
                // 值等于 val 的节点全部删除
                while (cur.next != null && cur.next.val == val) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }
    /**
     * Use dummy node and pre node
     */
    public static ListNode deleteDuplicates_1(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy; // 多加一个变量, 维护2个指针
        ListNode cur = head;
        while(cur != null && cur.next != null){
            if(cur.val == cur.next.val){
                int val = cur.val;

                while(cur != null && cur.val == val){   // 中间的全删除
                    cur = cur.next;
                }
                pre.next = cur;
            }else{
                pre = cur; // 前节点存起来
                cur = cur.next;
            }
        }
        return dummy.next;
    }
    /**
     * 删除原始链表中所有重复数字的节点，只留下不同的数字
     * delete elements within two pointers
     *
     * 由于头结点可能被删除，因此需要一个增加一个虚拟结点。
     * 遍历链表，当遇到重复结点时，循环向后移动，直到下一个不重复结点，将中间结点删除即可。
     */
    public ListNode deleteDuplicates_3(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy;
        while(pre.next != null){
            ListNode cur = pre.next;
            while(cur.next != null && cur.next.val == cur.val){
                cur = cur.next;
            }
            if(cur == pre.next){
                pre = pre.next;
            }else{
                pre.next = cur.next; // delete elements within two pointers
            }
        }
        return dummy.next;
    }
    /**
     * Recursive - from head to tail
     * 1 -> 2 -> 3 -> 3 -> 3 -> 4 -> 4 -> 5
     */
    public static ListNode deleteDuplicates_Recursive(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        if(head.val == head.next.val){ //存在相同情况
            // This part is different from Q83_Remove_Duplicates_from_Sorted_List
            while(head.next != null && head.val == head.next.val){ //等于就删除，找到不相同的
                head = head.next;
            }
            // end
            return deleteDuplicates_Recursive(head.next); // the current node doesn't need to be kept.
            // head = deleteDuplicates_Recursive(head.next); // works too
        }else{
            head.next = deleteDuplicates_Recursive(head.next); //没有相同，那就比较下一个数
        }

        return head;
    }
    /**
     * another form
     * 利用递归去重。注意需要考虑头节点是重复元素时，需要更换头节点
     */
    public ListNode deleteDuplicates_8(ListNode head) {
        if(head==null || head.next==null){
            return head;
        }
        ListNode next = head.next;
        if(next != null && head.val == next.val){  //头结点被删除
            while(next != null && head.val == next.val){
                head = next;
                next = next.next;
            }
            head = deleteDuplicates_8(next);
        }else{
            head.next = deleteDuplicates_8(next);
        }
        return head;
    }
    /**
     * Recursive - from tail to head
     */
    public static ListNode deleteDuplicates_Recursive_1(ListNode head) {
        return deleteDuplicates_Recursive_func(head, Integer.MAX_VALUE, head == null || head.next == null ? Integer.MAX_VALUE : head.next.val); // This part is different from Q83_Remove_Duplicates_from_Sorted_List
    }

    public static ListNode deleteDuplicates_Recursive_func(ListNode head, int preValue, int nextValue) {
        if(head == null){
            return head;
        }

        head.next = deleteDuplicates_Recursive_func(head.next, head.val,
                head.next == null || head.next.next == null? Integer.MAX_VALUE : head.next.next.val); // This part is different from Q83_Remove_Duplicates_from_Sorted_List

        if(head.val == preValue || head.val == nextValue){ // This part is different from Q83_Remove_Duplicates_from_Sorted_List
            return head.next;
        }else{
            return head;
        }
    }
}
