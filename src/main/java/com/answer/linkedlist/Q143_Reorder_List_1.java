package com.answer.linkedlist;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q143_Reorder_List_1 {
    /**
     *
     */
    public static void main(String[] args) {
        //head = [1,2,3,4,5]
          ListNode node5 = new ListNode(5, null);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1,node2);

        reorderList(node1);
        node1.print();
    }
    /**
     * 递归&回溯
     * 重排的直观思路是，一个指针从head，一个从tail分别向中间访问合并，直到中间节点，但是单链表不指向其前驱节点，所以没办法直接实现该方式。
     * 但如果使用递归遍历链表时，递归函数执行完成回溯到方法时，本质上实现了从后继向前访问，而如果我们利用递归函数的返回值实现从头部向后的访问，
     * 就实现了同步从head和tail向中间访问，并且节点一一对应。
     */
   static public void reorderList(ListNode head) {
       reorderList(head, head);
   }

    static private ListNode reorderList(ListNode head, ListNode tail) {
        // 如果tail为null，说明已经递归到链表尾部，这时候需要重新连接尾部节点与头部节点，故返回head
        if (tail == null) return head;
        // 一直递归到尾部
        ListNode returnNode = reorderList(head, tail.next);
        // 回溯到方法，returnNode即为与tail对应的正向访问节点
        // 如果returnNode是null，说明处理完成，直接返回
        if (returnNode == null) {
            return null;
        }
        // 如果returnNode或returnNode的后继等于tail，说明完成，注意tail即为尾节点，next需要set null
        if (returnNode == tail || returnNode.next == tail) {
            tail.next = null;
            return null;
        }
        // 将尾部遍历节点指向对应的头部遍历节点的next，正向节点指向尾部遍历节点
        tail.next = returnNode.next;
        returnNode.next = tail;
        // 返回头部向后访问的下一节点
        return tail.next;
    }
    /**
     * anther form
     */
    static private ListNode reorderList1(ListNode head, ListNode tail) {
        if (tail == null) {
            return head;
        }
        ListNode reverse = reorderList1(head, tail.next);
        if(reverse == null) return null;

        if (reverse.next == tail || reverse == tail) {
            tail.next = null;
            return null;
        }
        tail.next = reverse.next;
        reverse.next = tail;
        reverse = tail.next;
        return reverse;
    }
    /**
     * 在这个演示过程中，我们将通过例子来解释递归方法 reorderList(ListNode head, ListNode tail) 的执行过程。下面的链表是作为示例使用的：
     * 1 -> 2 -> 3 -> 4 -> 5
     *
     * 步骤 1：递归到链表尾部
     *
     * 在递归 reorderList(head, tail) 时，头节点 head 从头开始，尾节点 tail 从头开始。递归通过移动 tail 到下一个节点来向链表的尾部推进，同时 head 保持不变。
     *
     * 调用栈中的递归层次如下：
     *
     * 第 1 次调用：head = 1, tail = 2
     * 第 2 次调用：head = 1, tail = 3
     * 第 3 次调用：head = 1, tail = 4
     * 第 4 次调用：head = 1, tail = 5
     * 第 5 次调用：head = 1, tail = null
     * 在第 5 次调用时，tail 为 null，递归停止并返回 head（值为 1）。
     *
     * 步骤 2：递归回溯
     *
     * 回溯从第 5 次调用开始，returnNode 是从递归返回的 head。
     *
     * 在回溯的过程中，我们将当前的 tail 节点插入到 returnNode 和 returnNode.next 之间：
     *
     * 第 4 次调用：returnNode = 1, tail = 5
     *
     * tail.next = returnNode.next（值为 2）
     * returnNode.next = tail（值为 5）
     * return tail.next（值为 2）
     * 现在链表看起来像：1 -> 5 -> 2 -> 3 -> 4
     * 第 3 次调用：returnNode = 2, tail = 4
     *
     * tail.next = returnNode.next（值为 3）
     * returnNode.next = tail（值为 4）
     * return tail.next（值为 3）
     * 现在链表看起来像：1 -> 5 -> 2 -> 4 -> 3
     * 第 2 次调用：returnNode = 3, tail = null
     *
     * 链表已完成重新排列
     * 步骤 3：链表重新排列
     *
     * 最终，重新排列后的链表如下：
     *
     * 1 -> 5 -> 2 -> 4 -> 3
     *
     * 这就是 reorderList 方法的递归执行过程和重新排列后的链表结构。你可以通过调试或打印每一步的结果来更详细地查看这个过程。
     */

}
