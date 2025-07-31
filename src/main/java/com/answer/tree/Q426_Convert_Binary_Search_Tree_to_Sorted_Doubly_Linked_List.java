package com.answer.tree;

import java.util.ArrayDeque;
import java.util.Deque;

public class Q426_Convert_Binary_Search_Tree_to_Sorted_Doubly_Linked_List {
    /**
     * 将二叉搜索树转化为排序的双向链表
     * 将一个 二叉搜索树 就地转化为一个 已排序的双向循环链表 。
     * 对于双向循环列表，你可以将左右孩子指针作为双向循环链表的前驱和后继指针，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。
     * 特别地，我们希望可以 就地 完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中最小元素的指针。
     * Convert a BST to a sorted circular doubly-linked list in-place. Think of the left and right pointers as synonymous to the previous and next pointers in a doubly-linked list.
     * Let's take the following BST as an example, it may help you understand the problem better:
     * We want to transform this BST into a circular doubly linked list. Each node in a doubly linked list has a predecessor and successor. For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.
     * The figure below shows the circular doubly linked list for the BST above. The "head" symbol means the node it points to is the smallest element of the linked list.
     */
    public static void main(String[] args) {
        Q426_Convert_Binary_Search_Tree_to_Sorted_Doubly_Linked_List solution = new Q426_Convert_Binary_Search_Tree_to_Sorted_Doubly_Linked_List();
        // 用例1: 普通二叉搜索树
        //      4
        //     / \
        //    2   5
        //   / \
        //  1   3
        Node root = new Node(4);
        root.left = new Node(2);
        root.right = new Node(5);
        root.left.left = new Node(1);
        root.left.right = new Node(3);
        Node head = solution.treeToDoublyList1(root);
        printList(head, 5); // 1 <-> 2 <-> 3 <-> 4 <-> 5 <-> (回到1)
        // 用例2: 只有一个节点
        Node single = new Node(10);
        head = solution.treeToDoublyList1(single);
        printList(head, 1); // 10 <-> (回到10)
        // 用例3: 空树
        head = solution.treeToDoublyList1(null);
        printList(head, 0); // 不输出
    }
    // 打印前 n 个节点的值，防止无限循环
    static void printList(Node head, int n) {
        Node cur = head;
        for (int i = 0; i < n && cur != null; i++) {
            System.out.print(cur.val);
            if (i < n - 1) System.out.print(" <-> ");
            cur = cur.right;
        }
        if (head != null) System.out.println(" <-> (back to " + head.val + ")");
        else System.out.println("null");
    }
    /**
     * 当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。
     *
     * 采用中序遍历，利用 prev 变量保存上一个访问的节点，head 保存链表的头节点。
     * 最后收尾相连，形成循环双向链表。
     */
    private Node prev = null; // 用于记录上一个访问的节点
    private Node head = null;// 用于记录链表的头结点
    /**
     * 将二叉搜索树转化为排序的循环双向链表
     */
    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        dfs(root);
        // 最后将首尾节点连接，形成循环双向链表
        prev.right = head;
        head.left = prev;
        return head;
    }
    /**
     * 中序遍历BST，构建排序的双向链表
     */
    private void dfs(Node curr) {
        if (curr == null) {
            return;
        }
        dfs(curr.left);
        // 处理当前节点与前一个节点的连接
        if (prev != null) {
            prev.right = curr;  // 双指针当前节点和尾节点
            curr.left = prev;
        } else {
            head = curr; // 记录链表头
        }
        prev = curr;
        dfs(curr.right);
    }
    /**
     * 这道题目实际上也是将二叉搜索树序列化。
     * 注意转换规则：左指针指向前继节点，右指针指向后继节点。
     * 实际上就是中序遍历。
     * 使用栈来帮助我们进行中序遍历。
     * 在出栈时改变它的左指针，指向，前继节点prev；prev的右指针指向当前出栈的节点。
     * 需要注意的是：在最后需要连接头指针和尾指针。
     */
    public Node treeToDoublyList1(Node root) {
        if(root == null) return root;
        Node cur = root;
        Deque<Node> stk = new ArrayDeque<>();
        Node head = null;
        Node prev = null;
        while(cur != null || !stk.isEmpty()){
            while(cur != null){
                stk.push(cur);
                cur = cur.left;
            }
            cur = stk.pop();

            if(head == null) { //头结点
                head = cur;
            }
            if(prev != null){
                prev.right = cur; //前面节点的后驱
                cur.left = prev; //当前节点的前驱
            }
            prev = cur; //前节点更新

            cur = cur.right;
        }
        prev.right = head; //最后的尾节点后继是头
        head.left = prev;  //头节点的前驱是尾节点
        return head;
    }

   static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }
}

