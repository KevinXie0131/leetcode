package com.answer.tree;

import java.util.*;

public class Q116_Populating_Next_Right_Pointers_in_Each_Node {
    /**
     * 填充每个节点的下一个右侧节点指针
     * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
     * struct Node {
     *   int val;
     *   Node *left;
     *   Node *right;
     *   Node *next;
     * }
     * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
     * 初始状态下，所有 next 指针都被设置为 NULL。
     * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:
     * struct Node {
     *   int val;
     *   Node *left;
     *   Node *right;
     *   Node *next;
     * }
     * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
     * Initially, all next pointers are set to NULL.
     */
    /**
     * 只能使用常量级额外空间, 基本上就是要求使用递归了，迭代的方式一定会用到栈或者队列
     * 递归: 最关键的点是可以通过上一层递归 搭出来的线，进行本次搭线。
     */
    public Node connect_0(Node root) {
        trasverse(root);
        return root;
    }
    /**
     *         1
     *     /       \
     *    p   ->    3
     *   /  \      /  \
     *  4 -> 5 -> 6 -> 7
     *  p.left.next = p.right
     *  p.right.next = p.next.left
     */
    public void trasverse(Node root) {
        if(root == null) {
            return; // 中
        }
        if(root.left != null){
            root.left.next = root.right; // 操作1
        }
        if(root.right != null){
            if(root.next != null){
                root.right.next = root.next.left;  //操作2
            }else {
                root.right.next = null;
            }
        }
/*        if(root.right != null && root.next != null){ // works too
            root.right.next = root.next.left;  //操作2
        }*/
        trasverse(root.left);  // 左
        trasverse(root.right); //右
    }
    /**
     * 拉拉链解法
     * 把完美二叉树劈成两半，先处理大的两半二叉树中间的左右连接，然后递归处理小的连接
     */
    public Node connect4(Node root) {
        if (root == null) {
            return root;
        }
        Node leftNode = root.left;
        Node rightNode = root.right;
        while(leftNode != null) {
            leftNode.next = rightNode;
            leftNode = leftNode.right;
            rightNode = rightNode.left;
        }
        connect4(root.left);
        connect4(root.right);
        return root;
    }
    /**
     * 通过从根节点不断向左孩子遍历，来遍历每一层的头结点；而对单层内的节点 p 可以通过 next 指针遍历，然后进行以下操作
     * p->left->next = p->right;
     * p->right->next = p->next->left;
     * 伪代码如下：
     * leftmost = root
     * while (leftmost.left != null) {
     *     cur = leftmost
     *     while (head.next != null) {
     *         1) Establish Connection 1
     *         2) Establish Connection 2 using next pointers
     *         cur = cur.next
     *     }
     *     leftmost = leftmost.left
     * }
     */
    public Node connect3(Node root) {
        Node leftmost  = root;
        while (leftmost != null) {
            Node cur = leftmost ;  // 遍历这一层节点组织成的链表，为下一层的节点更新 next 指针
            while (cur != null) {
                if (cur.left != null) {  // CONNECTION 1
                    cur.left.next = cur.right;
                }
                if (cur.right != null && cur.next != null) {  // CONNECTION 2
                    cur.right.next = cur.next.left;
                }
                cur = cur.next;  // 指针向后移动
            }
            leftmost = leftmost.left;// 去下一层的最左的节点
        }
        return root;
    }
    /**
     * DFS
     */
    private final List<Node> pre = new ArrayList<>();

    public Node connect5(Node root) {
        dfs(root, 0); // 根节点的深度为 0
        return root;
    }

    private void dfs(Node node, int depth) {
        if (node == null) {
            return;
        }
        if (depth == pre.size()) { // node 是这一层最左边的节点
            pre.add(node);
        } else { // pre[depth] 是 node 左边的节点
            pre.get(depth).next = node; // node 左边的节点指向 node
            pre.set(depth, node);
        }
        dfs(node.left, depth + 1);
        dfs(node.right, depth + 1);
    }
    /**
     * 本题依然是层序遍历，只不过在单层遍历的时候记录一下本层的头部节点，然后在遍历的时候让前一个节点指向本节点就可以了
     */
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Node cur = queue.poll(); // 从队首取出元素
                // 遍历每一行的时候，如果不是最后一个Node，则指向下一个Node；如果是最后一个Node，则指向null
                if (i < size - 1) {   // 连接
                    cur.next = queue.peek(); //如果不是最后一个Node 则指向下一个Node
                } else {
                    cur.next = null;  //如果是最后一个Node 则指向null (可省略)
                }
                if (cur.left != null) queue.offer(cur.left);    // 拓展下一层节点
                if (cur.right != null) queue.offer(cur.right);
            }
        }
        return root;
    }
    /**
     * 迭代
     */
    public Node connect_1(Node root) {
        if (root == null) {
            return root;
        }
        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        Node nodePre = null;
        Node node = null;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {  // 开始每一层的遍历
                if (i == 0) {
                    nodePre = queue.pop(); // 取出一层的头结点
                    node = nodePre;
                } else {
                    node = queue.pop();
                    nodePre.next = node; // 本层前一个节点next指向本节点
                    nodePre = nodePre.next;
                }
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            nodePre.next = null;// 本层最后一个节点指向null (可省略)
        }
        return root;
    }
}
