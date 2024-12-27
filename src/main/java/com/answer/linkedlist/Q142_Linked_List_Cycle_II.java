package com.answer.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class Q142_Linked_List_Cycle_II {

    public static void main(String[] args) {

        ListNode head = new ListNode(1, null);
        ListNode result =  detectCycle(head);
        System.out.println(result);
    }

    /**
     * fast-slow pointers
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
    /**
     *
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
}
