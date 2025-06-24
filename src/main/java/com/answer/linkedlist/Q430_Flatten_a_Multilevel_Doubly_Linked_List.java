package com.answer.linkedlist;

public class Q430_Flatten_a_Multilevel_Doubly_Linked_List {
    /**
     * 扁平化多级双向链表
     * 你会得到一个双链表doubly linked list，其中包含的节点有一个下一个指针 next pointer、一个前一个指针previous pointer和一个额外的 子指针additional child pointer 。这个子指针可能指向一个单独的双向链表，也包含这些特殊的节点。这些子列表可以有一个或多个自己的子列表，以此类推，以生成如下面的示例所示的 多层数据结构multilevel data structure 。
     * 给定链表的头节点 head ，将链表 扁平化flatten ，以便所有节点都出现在单层双链表中。让 curr 是一个带有子列表的节点。子列表中的节点应该出现在扁平化列表中的 curr 之后(after) 和 curr.next 之前(before) 。
     * 返回 扁平列表的 head 。列表中的节点必须将其 所有 子指针设置为 null 。
     * Return the head of the flattened list. The nodes in the list must have all of their child pointers set to null.
     *
     * 示例 1：
     *  输入：head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
     *  输出：[1,2,3,7,8,11,12,9,10,4,5,6]
     */
}
