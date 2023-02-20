package com.learn.template;

import com.leetcode.ListNode;

public class Fast_Slow_Pointer {

    /**
     * 链表: 快慢指针
     */public int fn(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        int ans = 0;

        while (fast != null && fast.next != null) {
            // 根据题意补充代码
            slow = slow.next;
            fast = fast.next.next;
        }

        return ans;
    }
}
