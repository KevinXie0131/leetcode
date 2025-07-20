package com.answer.linkedlist;

import java.util.*;

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
    /**
     * 递归
     */
    public Node flatten(Node head) {
        Node dummy = new Node();
        dummy.next = head;
        while (head != null) {
            if (head.child == null) {
                head = head.next;
            } else {
                Node tmp = head.next;
                Node chead = flatten(head.child);
                head.next = chead;
                chead.prev = head;
                head.child = null;
                while (head.next != null) head = head.next;
                head.next = tmp;
                if (tmp != null) tmp.prev = head;
                head = tmp;
            }
        }
        return dummy.next;
    }
    /**
     * 递归（优化）
     * 一个可行的优化是，额外设计一个递归函数 dfs 用于返回扁平化后的链表“尾结点”，从而确保我们找尾结点的动作不会在每层发生。
     */
    public Node flatten1(Node head) {
        dfs(head);
        return head;
    }
    // 深度优先搜索
    Node dfs(Node head) {
        Node last = head;
        while (head != null) {
            if (head.child == null) {
                last = head;
                head = head.next;
            } else {
                Node tmp = head.next;
                Node childLast = dfs(head.child);
                head.next = head.child;
                head.child.prev = head;
                head.child = null;
                if (childLast != null) childLast.next = tmp;
                if (tmp != null) tmp.prev = childLast;
                last = head;
                head = childLast;
            }
        }
        return last;
    }
    /**
     * 回溯法:遇到有子节点的就暂存到栈里,等遍历完子节点在从栈中取出回溯
     */
    public Node flatten3(Node head) {
        Node node = head;
        Node prev = null;
        Deque<Node> stack = new ArrayDeque<>();
        while (node != null || !stack.isEmpty()) {
            if (node == null) {
                node = stack.pop();
                node.prev = prev;
                prev.next = node;
            }
            if (node.child != null) {
                if (node.next != null) stack.push(node.next);
                node.child.prev = node;
                node.next = node.child;
                node.child = null; // 把child设为null
            }
            prev = node;
            node = node.next;
        }
        return head;
    }
    /**
     * 迭代
     * 与「递归」不同的是，「迭代」是以“段”为单位进行扁平化，而「递归」是以深度（方向）进行扁平化，这就导致了两种方式对每个扁平节点的处理顺序不同。
     */
    public Node flatten2(Node head) {
        Node dummy = new Node();
        dummy.next = head;
        for (; head != null; head = head.next) {
            if (head.child != null) {
                Node tmp = head.next;
                Node child = head.child;
                head.next = child;
                child.prev = head;
                head.child = null;
                Node last = head;
                while (last.next != null) last = last.next;
                last.next = tmp;
                if (tmp != null) tmp.prev = last;
            }
        }
        return dummy.next;
    }
    /**
     * refer to Q114_Flatten_Binary_Tree_to_Linked_List 二叉树展开为链表
     * 这道题和114其实本质是一样的，只是多了 prev双向指针，所以我们只需要在114的基础上添加前置指针的修改即可
     * 将这个题转换为二叉树其实是就是将  child 看为  left ，将  next 看为 right 并且在转为链表的时候将后一个节点的  prev  指向前一个节点。
     * 1 -> 2 -> 3 -> 4 -> 5 -> 6
     *                 |
     *                 7 -> 8 -> 9 -> 10
     *                         |
     *                        11 -> 12
     * 转化为单向链表为 1 -> 2 -> 3 -> 7 -> 8 -> 11 -> 12 -> 9 -> 10 - > 4 -> 5 -> 6
     * 在这个过程中再将 prev 指向前一个即可，这也是跟114题的不同之处
     */
    Node last = null;

    public Node flatten4(Node head) {
        if(head == null) {
            return null;
        }
        flatten(head.next);
        flatten(head.child);
        head.next = last; // 该节点的下一个节点是 last
        if(last != null){
            last.prev = head; // 如果 last 为空那么就不需要赋值前置节点了
        }
        head.child = null; // 左指针即孩子指针赋值为空
        last = head; // 记录该节点
        return head;
    }
    /**
     * DFS - 二叉树先序遍历
     *
     * 多级链表结构的扁平化类似二叉树的先序遍历
     * child就相当于left tree，next相当于right tree
     * 需要维护一个prev指针用于访问当前节点的上一个节点
     * prev指针非空时，建立prev与当前节点的双向连接
     * 处理完一个child后记得把它设为null
     */
    private Node prev = null;

    public Node flatten5(Node head) {
        dfs1(head);
        return head;
    }

    private void dfs1(Node head) {
        if (head == null) return;
        Node next = head.next;
        if (prev != null) {
            prev.next = head;
            head.prev = prev;
        }
        prev = head;
        dfs1(head.child);
        head.child = null;
        dfs1(next);
    }
    /**
     * 参考 341扁平化套嵌链表 ,多叉树的前序遍历，二叉树的前序遍历
     * 看成二叉树的前序遍历
     */
    private Node ans;
    private Node cur;

    public Node flatten6(Node head) {
        if(head == null){
            return null;
        }
        dfs2(head);
        return ans;
    }
    //看成二叉树的前序遍历, 优先遍历左节点
    private void dfs2(Node node){
        if(node == null) return;
        //相当于前序遍历的主体，把遍历到的当前节点放入新的链表里
        if(cur == null){
            ans = new Node();
            ans.val = node.val;
            cur = ans;
        }else{
            Node newNode = new Node();
            newNode.val = node.val;
            cur.next = newNode;
            newNode.prev = cur;
            cur = newNode;
        }
        dfs2(node.child);  //优先迭代子节点，再迭代下一个节点
        dfs2(node.next);
    }
    // Definition for a Node.
    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    };
}
