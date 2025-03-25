package com.answer.linkedlist;

public class Q2_Add_Two_Numbers {
    public static void main(String[] args) {
        // l1 = [2,4,3], l2 = [5,6,4]
        ListNode node3 = new ListNode(9, null);
        ListNode node2 = new ListNode(9, node3);
        ListNode node1= new ListNode(9,node2);

        ListNode node5a = new ListNode(9, null);
        ListNode node4a = new ListNode(9, node5a);
        ListNode node3a = new ListNode(9, node4a);
        ListNode node2a = new ListNode(9, node3a);
        ListNode node1a = new ListNode(9,node2a);

        ListNode result = addTwoNumbers_2(node1, node1a);
        result.print();
    }
    /**
     * 比较简洁的迭代法
     * 按位模拟加法，注意循环条件不要忘记 || sum
     */
    public ListNode addTwoNumbers_0(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1, null);
        ListNode cur = dummy;
        int sum = 0;
        while(l1 != null || l2 != null || sum > 0){
            if(l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if(l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            cur.next = new ListNode(sum % 10, null);
            cur = cur.next;
            sum = sum / 10;
        }
        return dummy.next;
    }
    /**
     * Iterative 迭代法
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1, null);
        ListNode cur = dummy;
        int carry = 0;
        while(l1 != null || l2 != null){
            int v1 = l1 == null ? 0 : l1.val;
            int v2 = l2 == null ? 0 : l2.val;

            int sum = v1 + v2 + carry;
            carry = sum / 10;
            sum = sum % 10;

            cur.next = new ListNode(sum, null);
            cur = cur.next;

            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
        }
        if(carry > 0){
            cur.next = new ListNode(carry, null);
        }
        return dummy.next;
    }
    /**
     * Recursive 递归法
     */
    public static ListNode addTwoNumbers_1(ListNode l1, ListNode l2) {
        if(l1 == null) { // node1是null，返回node2. node2是null，返回node1
            return l2;
        }
        if(l2 == null) {
            return l1;
        }
        int sum = l1.val + l2.val;
        ListNode head = new ListNode(sum % 10);
        head.next = addTwoNumbers_1(l1.next, l2.next);

        if(sum > 9) {
            head.next = addTwoNumbers_1(head.next, new ListNode(1));
        }
        return head;
    }
    /**
     * 递归法(另一种形式)
     */
    public static ListNode addTwoNumbers_2(ListNode l1, ListNode l2) {
        ListNode result = recursion0(l1, l2, 0);
       return result;
    }

    public static ListNode recursion(ListNode l1, ListNode l2 , int carry){
        if(l1 == null && l2 != null) {
            if(carry > 0) {
                l2.val = l2.val + carry;
                carry = 0;
            }
        }
        if(l2 == null && l1 != null) {
            if(carry > 0) {
                l1.val = l1.val + carry;
                carry = 0;
            }
        }
        if(l1 == null && l2 == null){
            if(carry > 0) {
                return new ListNode(carry);
            }else {
                return null;
            }
        }
        int v1 = l1 == null ? 0 : l1.val;
        int v2 = l2 == null ? 0 : l2.val;
        int sum = v1 + v2 + carry;
        int value = sum % 10;
        ListNode head = new ListNode(value);
        ListNode cur1 = l1 == null ? null : l1.next;
        ListNode cur2 = l2 == null ? null : l2.next;
        head.next = recursion(cur1, cur2, sum / 10);

        return head;
    }
    /**
     * 用新节点防止出现null
     */
    public static ListNode recursion0(ListNode l1, ListNode l2 , int carry){
        if(l1 == null && l2 != null) {
            if(carry > 0) {
                l2.val = l2.val + carry;
                carry = 0;
            }
            l1 = new ListNode(0); // 用新节点防止出现null
        }
        if(l2 == null && l1 != null) {
            if(carry > 0) {
                l1.val = l1.val + carry;
                carry = 0;
            }
            l2 = new ListNode(0);  // 用新节点防止出现null
        }
        if(l1 == null && l2 == null){
            return carry > 0 ? new ListNode(carry) : null;
        }
        int sum = l1.val + l2.val + carry;
        ListNode head = new ListNode(sum % 10);
        head.next = recursion(l1.next, l2.next, sum / 10);
        return head;
    }
    /**
     * 有一个简化代码的小技巧：如果递归中发现 l2 的长度比 l1 更长，那么可以交换 l1 和 l2，保证 l1 不是空节点，从而简化代码逻辑。
     */
    public   ListNode recursion1(ListNode l1, ListNode l2 , int carry){
        if (l1 == null && l2 == null) { // 递归边界：l1 和 l2 都是空节点
            return carry != 0 ? new ListNode(carry) : null; // 如果进位了，就额外创建一个节点
        }
        if (l1 == null) { // 如果 l1 是空的，那么此时 l2 一定不是空节点
            l1 = l2;
            l2 = null; // 交换 l1 与 l2，保证 l1 非空，从而简化代码
        }
        int sum = carry + l1.val + (l2 != null ? l2.val : 0); // 节点值和进位加在一起
        l1.val = sum % 10; // 每个节点保存一个数位（直接修改原链表）
        l1.next = recursion(l1.next, (l2 != null ? l2.next : null), sum / 10); // 进位
        return l1;
    }
    /**
     * 不直接修改原链表
     */
    public   ListNode recursion3(ListNode l1, ListNode l2 , int carry){
        if (l1 == null && l2 == null) {
            return carry != 0 ? new ListNode(carry) : null;
        }
        if (l1 == null) {
            l1 = l2;
            l2 = null;
        }
        int sum = carry + l1.val + (l2 != null ? l2.val : 0);
        ListNode head = new ListNode(sum % 10);
        head.next = recursion(l1.next, (l2 != null ? l2.next : null), sum / 10);
        return head;
    }
}
