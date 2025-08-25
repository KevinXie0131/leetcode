package com.answer.linkedlist;

import java.util.*;

public class Q1265_Print_Immutable_Linked_List_in_Reverse {
    /**
     * 逆序打印不可变链表
     * 一个不可变的链表，使用下列接口逆序打印每个节点的值：ImmutableListNode: 描述不可变链表的接口，链表的头节点已给出。
     * 需要使用以下函数来访问此链表（不能直接访问ImmutableListNode）：ImmutableListNode.printValue()：打印当前节点的值。
     * ImmutableListNode.getNext()：返回下一个节点。
     * 输入只用来内部初始化链表。您不可以通过修改链表解决问题。也就是说，您只能通过上述 API 来操作链表。
     * 示例 1：
     *   输入：head = [1,2,3,4]
     *   输出：[4,3,2,1]
     */
    public static void main(String[] args) {
        // 构造链表: 1 -> 2 -> 3 -> 4
        ImmutableListNode node4 = new ImmutableListNodeImpl(4, null);
        ImmutableListNode node3 = new ImmutableListNodeImpl(3, node4);
        ImmutableListNode node2 = new ImmutableListNodeImpl(2, node3);
        ImmutableListNode node1 = new ImmutableListNodeImpl(1, node2);

        Q1265_Print_Immutable_Linked_List_in_Reverse solution = new Q1265_Print_Immutable_Linked_List_in_Reverse();
        System.out.print("Expected output: 4 3 2 1\nActual output: ");
        solution.printLinkedListInReverse(node1);
    }
    /**
     * 只能用 printValue() 打印节点值，不能直接访问或修改节点。
     * 用 getNext() 访问下一个节点。
     * 推荐用栈，避免递归导致栈溢出。
     * 时间复杂度 O(N)，空间复杂度 O(N)。
     */
    public void printLinkedListInReverse1(ImmutableListNode head) {
        Deque<ImmutableListNode> stack = new ArrayDeque<>();
        while(head != null){
            stack.push(head);
            head = head.getNext();
        }

        while(!stack.isEmpty()){
             stack.pop().printValue();
        }
    }
    /**
     * 由于递归可能导致栈溢出，通常只建议链表长度不大时用
     */
    public void printLinkedListInReverse(ImmutableListNode head) {
        if(head == null){
            return;
        }
        printLinkedListInReverse(head.getNext());
        head.printValue();
    }
}

// Definition for ImmutableListNode provided by LeetCode
interface ImmutableListNode {
    public void printValue(); // print the value of this node.
    public ImmutableListNode getNext(); // return the next node.
}

// Mock implementation for testing
class ImmutableListNodeImpl implements ImmutableListNode {
    private final int value;
    private final ImmutableListNode next;

    public ImmutableListNodeImpl(int value, ImmutableListNode next) {
        this.value = value;
        this.next = next;
    }

    @Override
    public void printValue() {
        System.out.print(value + " ");
    }

    @Override
    public ImmutableListNode getNext() {
        return next;
    }
}
