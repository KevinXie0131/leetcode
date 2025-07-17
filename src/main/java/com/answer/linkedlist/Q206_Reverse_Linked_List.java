package com.answer.linkedlist;

public class Q206_Reverse_Linked_List {
    /**
     * 反转链表
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     * Given the head of a singly linked list, reverse the list, and return the reversed list.
     *
     * 示例 1：
     *  输入：head = [1,2,3,4,5]
     *  输出：[5,4,3,2,1]
     *
     *  假设链表为 1→2→3→∅，我们想要把它改成 ∅←1←2←3。
     */
    public static void main(String[] args) {
      //  ListNode node6 = new ListNode(6, null);
        ListNode node5 = new ListNode(5, null);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1= new ListNode(1,node2);
        //[1,2,3,4,5]
        ListNode node = reverseList(node1);
        node.print();
    }
    /**
     * 使用dummy节点
     */
    public static ListNode reverseList_dummy(ListNode head) {
        ListNode dummy =  new ListNode(-1 , head);

        while(head != null && head.next != null){
            ListNode dnext = dummy.next;
            ListNode hnext = head.next;

            dummy.next = hnext; // 一直整体反转，效率有些低
            head.next = hnext.next;
            hnext.next = dnext;
        }
        return dummy.next;
    }
    /**
     * Iterative 迭代
     * 在遍历链表时，将当前节点的 next 指针改为指向前一个节点。由于节点没有引用其前一个节点，因此必须事先存储其前一个节点。
     * 在更改引用之前，还需要存储后一个节点。最后返回新的头引用。
     */
    public static ListNode reverseList_Iterative(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while(cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
    /**
     * Iterative 双指针法
     * 同上
     */
    public ListNode reverseList_Iterative1(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        ListNode temp = null; // 保存cur的下一个节点
        while (cur != null) {
            temp = cur.next; // 保存一下 cur的下一个节点，因为接下来要改变cur->next
            cur.next = prev; // 翻转操作
            // 更新pre 和 cur指针
            prev = cur;
            cur = temp;
        }
        return prev;
    }
    /**
     * 以链表1->2->3->4->5举例
     * @param head
     * @return
     */
    public ListNode reverseList7(ListNode head) {
        if (head == null || head.next == null) {
            // 直到当前节点的下一个节点为空时返回当前节点. 由于5没有下一个节点了，所以此处返回节点5
            return head;
        }

        ListNode newHead = reverseList7(head.next); //递归传入下一个节点，目的是为了到达最后一个节点
        /*  第一轮出栈，head为5，head.next为空，返回5
            第二轮出栈，head为4，head.next为5，执行head.next.next=head也就是5.next=4，
                      把当前节点的子节点的子节点指向当前节点
                      此时链表为1->2->3->4<->5，由于4与5互相指向，所以此处要断开4.next=null
                      此时链表为1->2->3->4<-5
                      返回节点5
            第三轮出栈，head为3，head.next为4，执行head.next.next=head也就是4.next=3，
                      此时链表为1->2->3<->4<-5，由于3与4互相指向，所以此处要断开3.next=null
                      此时链表为1->2->3<-4<-5
                      返回节点5
            第四轮出栈，head为2，head.next为3，执行head.next.next=head也就是3.next=2，
                      此时链表为1->2<->3<-4<-5，由于2与3互相指向，所以此处要断开2.next=null
                      此时链表为1->2<-3<-4<-5
                      返回节点5
            第五轮出栈，head为1，head.next为2，执行head.next.next=head也就是2.next=1，
                      此时链表为1<->2<-3<-4<-5，由于1与2互相指向，所以此处要断开1.next=null
                      此时链表为1<-2<-3<-4<-5
                      返回节点5
            出栈完成，最终头节点5->4->3->2->1
        */
        head.next.next = head;
        head.next = null;
        return newHead;
    }
    /**
     * Recursive - from tail to head // 从后向前递归
     */
    public static ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) {  // 边缘条件判断
            return head;
        }

        ListNode last  = reverseList(head.next); // 递归调用，翻转第二个节点开始往后的链表
        head.next.next = head;  // 翻转头节点与第二个节点的指向
        head.next = null; // 此时的 head 节点为尾节点，next 需要指向 NULL

        return last; // last 始终为最末的那个节点（5），节点5不停被返回，最后作为返回值返回
    }
    /**
     * Recursive - from head to tail 递归
     */
    public static ListNode reverseList_1(ListNode head) {
        return reverseList_fun(head, null );
    }

    public static ListNode reverseList_fun(ListNode curNode, ListNode previous ) {
        if(curNode == null) {
            return curNode;
        }
        ListNode next = curNode.next;
        ListNode nextNext = null;
        if(curNode.next != null) {
            nextNext= curNode.next.next;
            curNode.next.next = curNode;
        }
        curNode.next = previous;

        if(nextNext == null){
            if(next != null){
                return next;
            }else{
                return curNode;
            }
        }
        ListNode newNode = reverseList_fun(nextNext, next);
        return newNode;
    }
    /**
     * Recursive - from head to tail - more simple
     */
    public static ListNode reverseList_Recursive_3(ListNode head) {
        return reverse(null, head);
    }

    private  static ListNode reverse(ListNode prev, ListNode cur) {
        if (cur == null) {
            return prev;
        }
        ListNode temp = null;
        temp = cur.next;// 先保存下一个节点
        cur.next = prev;// 反转
        // 更新prev、cur位置
        // prev = cur;
        // cur = temp;

       // return reverse(cur, temp);
        ListNode newNode = reverse(cur, temp);
        return newNode;
    }
}
