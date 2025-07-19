package com.answer.linkedlist;

public class Q328_Odd_Even_Linked_List {
    /**
     * 奇偶链表
     * 给定单链表的头节点 head ，将所有索引为奇数的节点和索引为偶数的节点分别分组，保持它们原有的相对顺序，然后把偶数索引节点分组连接到奇数索引节点分组之后，返回重新排序的链表。
     * 第一个节点的索引被认为是 奇数 ， 第二个节点的索引为 偶数 ，以此类推。
     * 请注意，偶数组和奇数组内部的相对顺序应该与输入时保持一致。
     * 你必须在 O(1) 的额外空间复杂度和 O(n) 的时间复杂度下解决这个问题。
     * Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices, and return the reordered list.
     * The first node is considered odd, and the second node is even, and so on.
     * Note that the relative order inside both the even and odd groups should remain as it was in the input.
     * You must solve the problem in O(1) extra space complexity and O(n) time complexity.
     * 示例 1:
     *  输入: head = [1,2,3,4,5]
     *  输出: [1,3,5,2,4]
     */
    /**
     * 分离节点后合并
     * 对于原始链表，每个节点都是奇数节点或偶数节点。头节点是奇数节点，头节点的后一个节点是偶数节点，相邻节点的奇偶性不同。
     * 因此可以将奇数节点和偶数节点分离成奇数链表和偶数链表，然后将偶数链表连接在奇数链表之后，合并后的链表即为结果链表。
     */
    public ListNode oddEvenList(ListNode head) {
        if(head == null){
            return null;
        }
        ListNode evenHead = head.next;
        ListNode odd = head;
        ListNode even = evenHead; // evenHead 是偶数链表的头节点

        while(even != null && even.next != null){
            odd.next = even.next;  // 下一个奇数索引节点是当前偶数索引节点的下一个节点
            odd = odd.next;   // 更新当前奇数索引节点
            even.next = odd.next;   // 下一个偶数索引节点是新的奇数索引节点的下一个节点
            even = even.next;    // 更新偶数索引节点
        }
        odd.next = evenHead;
        return head;
    }
    /**
     * another form
     * 第一个节点的索引被认为是 奇数 ， 第二个节点的索引为 偶数 ，以此类推。
     * 请注意，偶数组和奇数组内部的相对顺序应该与输入时保持一致。
     */
    public ListNode oddEvenList_1(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return head;
        ListNode evenFirst = head.next;  // 记录偶数索引节点链表头节点
        ListNode odd = head, even = head.next; // odd奇数，even偶数
        boolean isOdd = true; // true为奇数，false偶数
        ListNode cur = head.next.next;
        while (cur != null) {
            if (isOdd) { // 奇数
                odd.next = cur;
                odd = cur;
            } else { // 偶数
                even.next = cur;
                even = cur;
            }
            isOdd = !isOdd;
            cur = cur.next;
        }
        //合并
        odd.next = evenFirst;  // 最后一个奇数索引节点和首个偶数索引节点拼接起来
        even.next = null;   //偶数链表尾节点指向null
        return head;
    }
}
