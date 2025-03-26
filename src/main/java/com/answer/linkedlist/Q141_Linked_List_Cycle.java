package com.answer.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class Q141_Linked_List_Cycle {
    /**
     * 本题是 Q142. 环形链表 II 的简化版，只需判断快慢指针能否相遇即可。
     * fast-slow pointers
     * 快慢指针法， 分别定义 fast 和 slow指针，从头结点出发，fast指针每次移动两个节点，slow指针每次移动一个节点，如果 fast 和 slow指针在途中相遇 ，说明这个链表有环。
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        // 空链表、单节点链表一定不会有环
        while(fast != null && fast.next != null){
            fast = fast.next.next; // 快指针，一次移动两步
            slow = slow.next;      // 慢指针，一次移动一步
            if(fast == slow){       // 快慢指针相遇，表明有环
                return true;
            }
        }
        return false;  // 正常走到链表末尾，表明没有环
    }
    /**
     * Use set
     */
    public boolean hasCycle_1(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while(head != null){
/*            if(!set.add(head)){
                return true;
            }*/
            if (set.contains(head)) {
                return true;
            }
            set.add(head);
            head = head.next;
        }
        return false;
    }
}
