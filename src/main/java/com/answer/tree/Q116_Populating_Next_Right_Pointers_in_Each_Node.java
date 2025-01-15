package com.answer.tree;


import java.util.*;

public class Q116_Populating_Next_Right_Pointers_in_Each_Node {
    /**
     * 只能使用常量级额外空间, 基本上就是要求使用递归了，迭代的方式一定会用到栈或者队列
     * 递归: 最关键的点是可以通过上一层递归 搭出来的线，进行本次搭线。
     */
    public Node connect_0(Node root) {
        trasverse(root);
        return root;
    }
    public void trasverse(Node root) {
        if(root == null) return; // 中
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
        trasverse(root.left);  // 左
        trasverse(root.right); //右
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
                Node cur = queue.poll();
                // 遍历每一行的时候，如果不是最后一个Node，则指向下一个Node；如果是最后一个Node，则指向null
                if (i < size - 1) {
                    cur.next = queue.peek(); //如果不是最后一个Node 则指向下一个Node
                } else {
                    cur.next = null;  //如果是最后一个Node 则指向null (可省略)
                }
                if (cur.left != null) {queue.offer(cur.left);}
                if (cur.right != null) {queue.offer(cur.right);}
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
                if (node.left != null) {queue.offer(node.left);}
                if (node.right != null) {queue.offer(node.right);}
            }
            nodePre.next = null;// 本层最后一个节点指向null (可省略)
        }
        return root;
    }
}