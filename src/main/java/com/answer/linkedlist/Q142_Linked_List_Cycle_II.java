package com.answer.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class Q142_Linked_List_Cycle_II {
    /**
     * 环形链表 II
     * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
     * 不允许修改 链表。
     * Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.
     * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter.
     * Do not modify the linked list.
     *
     * 示例 1：
     *  输入：head = [3,2,0,-4], pos = 1
     *  输出：返回索引为 1 的链表节点
     *  解释：链表中有一个环，其尾部连接到第二个节点。
     */
    public static void main(String[] args) {
        ListNode head = new ListNode(1, null);
        ListNode result =  detectCycle(head);
        System.out.println(result);
    }
    /**
     * fast-slow pointers 快慢指针
     * 两个指针，fast 与 slow。它们起始都位于链表的头部。随后，slow 指针每次向后移动一个位置，而 fast 指针向后移动两个位置。如果链表中存在环，则 fast 指针最终将再次与 slow 指针在环中相遇。
     * 只需要再定义两个指针，分别从相遇节点和头节点出发，那么一定会在环入口处相遇
     */
    public static ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (true) {
            if (fast == null || fast.next == null) {
                return null; //遇到null了，说明不存在环
            }
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break; //第一次相遇在Z点
            }
        }
        fast = head; //从头开始走，
        while(fast != slow){   //二者相遇在Y点，则退出
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }
    /**
     * another form
     */
    public ListNode detectCycle_2(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null && slow != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {// 有环
                ListNode index1 = fast;
                ListNode index2 = head;
                // 两个指针，从头结点和相遇结点，各走一步，直到相遇，相遇点即为环入口
                while (index1 != index2) {
                    index1 = index1.next;
                    index2 = index2.next;
                }
                return index1;
            }
        }
        return null;
    }
    /**
     * Use set
     */
    public ListNode detectCycle_1(ListNode head) {
        ListNode cur = head;
        Set<ListNode> set = new HashSet<>();
        while (cur != null) {
            if (set.contains(cur)) {
                return cur;
            }else{
                set.add(cur);
            }
            cur = cur.next;
        }
        return null;
    }

}
