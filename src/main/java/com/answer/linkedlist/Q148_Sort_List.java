package com.answer.linkedlist;

public class Q148_Sort_List {
    /**
     * 排序链表
     * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
     * Given the head of a linked list, return the list after sorting it in ascending order.
     * 示例 1：
     *  输入：head = [4,2,1,3]
     *  输出：[1,2,3,4]
     */
    public static void main(String[] args) {
        ListNode node4 = new ListNode(3, null);
        ListNode node3 = new ListNode(1, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(4, node2);
        ListNode node = sortList2(node1);
        System.out.println(node);
    }
    /**
     * 题目的进阶问题要求达到 O(nlogn) 的时间复杂度和 O(1) 的空间复杂度，时间复杂度是 O(nlogn) 的排序算法包括归并排序、堆排序和快速排序
     * (快速排序的最差时间复杂度是 O(n^2))，其中最适合链表的排序算法是归并排序。
     * 考虑到递归调用的栈空间，自顶向下归并排序的空间复杂度是 O(logn)。如果要达到 O(1) 的空间复杂度，则需要使用自底向上的实现方式。
     * Merge sorting - from top to bottom 自顶向下归并排序
     */
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode fast = head.next, slow = head;
        while(fast != null && fast.next != null){ // 找到链表的中点，以中点为分界，将链表拆分成两个子链表
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode temp = slow.next;
        slow.next = null;
        ListNode left = sortList(head); // 对两个子链表分别排序
        ListNode right = sortList(temp); // recursion
        ListNode h = new ListNode(0);
        ListNode result = h;
        while(left != null && right != null){ // 将两个排序后的子链表合并，得到完整的排序后的链表
            if(left.val < right.val){
                h.next = left;
                left = left.next;
            }else{
                h.next = right;
                right = right.next;
            }
            h = h.next;
        }
        h.next = left == null ? right : left;
        return result.next;
    }
    /**
     * 递归的终止条件是链表的节点个数小于或等于 1，即当链表为空或者链表只包含 1 个节点时，不需要对链表进行拆分和排序。
     */
    public ListNode sortList1(ListNode head) {
        return sortList(head, null);
    }

    public ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) {
            return head;
        }
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        ListNode slow = head, fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        ListNode list1 = sortList(head, mid);
        ListNode list2 = sortList(mid, tail);
        ListNode sorted = merge1(list1, list2);
        return sorted;
    }

    public ListNode merge1(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }
        return dummyHead.next;
    }
    /**
     * Approach 1: Top Down Merge Sort 自顶向下归并排序
     * Official answer
     */
   static public ListNode sortList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode mid = getMid1(head);
        ListNode left = sortList2(head);
        ListNode right = sortList2(mid);
        return merge(left, right);
    }

    static ListNode merge(ListNode list1, ListNode list2) {
        ListNode dummyHead = new ListNode();
        ListNode tail = dummyHead;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                tail.next = list1;
                list1 = list1.next;
                tail = tail.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
                tail = tail.next;
            }
        }
        tail.next = (list1 != null) ? list1 : list2;
        return dummyHead.next;
    }

    static ListNode getMid(ListNode head) {
        ListNode midPrev = null;
        while (head != null && head.next != null) {
            midPrev = (midPrev == null) ? head : midPrev.next;
            head = head.next.next;
        }
        ListNode mid = midPrev.next;
        midPrev.next = null;
        return mid;
    }
    /**
     * 同上
     */
    static ListNode getMid1(ListNode head) {
        ListNode slow = head, fast = head;
        ListNode dummy = new ListNode(-1, slow);
        while (fast != null) {
      //      slow = slow.next;
            dummy = dummy.next;
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            }
        }
        ListNode mid = dummy.next;
        dummy.next = null;
        return mid;
    }
    /**
     * Approach 2: Bottom Up Merge Sort 自底向上的归并排序
     * Official answer
     * 可以达到 O(1) 的空间复杂度。
     * 初始时 subLength=1，每个长度为 1 的子链表都是有序的。
     * 如果每个长度为 subLength 的子链表已经有序，合并两个长度为 subLength 的有序子链表，得到长度为 subLength×2 的子链表，一定也是有序的。
     * 当最后一个子链表的长度小于 subLength 时，该子链表也是有序的，合并两个有序子链表之后得到的子链表一定也是有序的。
     */
    public ListNode sortList4(ListNode head) {
        if (head == null) {
            return head;
        }
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }
        ListNode dummyHead = new ListNode(0, head);
        for (int subLength = 1; subLength < length; subLength <<= 1) { // subLength =  subLength * 2 // works too
            ListNode prev = dummyHead, curr = dummyHead.next;
            while (curr != null) {
                ListNode head1 = curr;
                for (int i = 1; i < subLength && curr.next != null; i++) {
                    curr = curr.next;
                }
                ListNode head2 = curr.next;
                curr.next = null;
                curr = head2;
                for (int i = 1; i < subLength && curr != null && curr.next != null; i++) {
                    curr = curr.next;
                }
                ListNode next = null;
                if (curr != null) {
                    next = curr.next;
                    curr.next = null;
                }
                ListNode merged = merge(head1, head2);
                prev.next = merged;
                while (prev.next != null) {
                    prev = prev.next;
                }
                curr = next;
            }
        }
        return dummyHead.next;
    }
    /**
     * 自底向上 归并排序
     * 1.遍历链表，获取链表长度 length。
     * 2.初始化步长 step=1。
     * 3.循环直到 step≥length。
     * 4.每轮循环，从链表头节点开始。
     * 5.分割出两段长为 step 的链表，合并，把合并后的链表插到新链表的末尾。重复该步骤，直到链表遍历完毕。
     * 6.把 step 扩大一倍。回到第 4 步。
     */
    public ListNode sortList6(ListNode head) {
        int length = getListLength(head); // 获取链表长度
        ListNode dummy = new ListNode(0, head); // 用哨兵节点简化代码逻辑
        // step 为步长，即参与合并的链表长度
        for (int step = 1; step < length; step *= 2) {
            ListNode newListTail = dummy; // 新链表的末尾
            ListNode cur = dummy.next; // 每轮循环的起始节点
            while (cur != null) {
                ListNode head1 = cur; // 从 cur 开始，分割出两段长为 step 的链表，头节点分别为 head1 和 head2
                ListNode head2 = splitList(head1, step);
                cur = splitList(head2, step); // 下一轮循环的起始节点
                ListNode[] merged = mergeTwoLists(head1, head2);  // 合并两段长为 step 的链表
                newListTail.next = merged[0];  // 合并后的头节点 merged[0]，插到 newListTail 的后面
                newListTail = merged[1]; // merged[1] 现在是新链表的末尾
            }
        }
        return dummy.next;
    }
    // 获取链表长度
    private int getListLength(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }
    // 分割链表
    // 如果链表长度 <= size，不做任何操作，返回空节点
    // 如果链表长度 > size，把链表的前 size 个节点分割出来（断开连接），并返回剩余链表的头节点
    private ListNode splitList(ListNode head, int size) {
        ListNode cur = head; // 先找到 nextHead 的前一个节点
        for (int i = 0; i < size - 1 && cur != null; i++) {
            cur = cur.next;
        }

        if (cur == null || cur.next == null) { // 如果链表长度 <= size
            return null; // 不做任何操作，返回空节点
        }

        ListNode nextHead = cur.next;
        cur.next = null; // 断开 nextHead 的前一个节点和 nextHead 的连接
        return nextHead;
    }
    // 21. 合并两个有序链表（双指针）
    // 返回合并后的链表的头节点和尾节点
    private ListNode[] mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(); // 用哨兵节点简化代码逻辑
        ListNode cur = dummy; // cur 指向新链表的末尾
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list1; // 把 list1 加到新链表中
                list1 = list1.next;
            } else { // 注：相等的情况加哪个节点都是可以的
                cur.next = list2; // 把 list2 加到新链表中
                list2 = list2.next;
            }
            cur = cur.next;
        }
        cur.next = list1 != null ? list1 : list2; // 拼接剩余链表
        while (cur.next != null) {
            cur = cur.next;
        }
        return new ListNode[]{dummy.next, cur}; // 循环结束后，cur 是合并后的链表的尾节点
    }
}
