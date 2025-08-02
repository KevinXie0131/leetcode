package com.answer.tree;

public class Q117_Populating_Next_Right_Pointers_in_Each_Node_II_1 {
    /**
     * 117题与116不同之处在于不是完美二叉树，所以左右孩子不一定都有。
     * 代码通过dummy虚拟头结点串联下一层所有节点，用prev不断连接，层级遍历。
     * 空间复杂度O(1)，时间复杂度O(N)。
     *
     * 在上一层为下一层建立 next 指针. 位于第 x 层时为第 x+1 层建立 next 指针。一旦完成这些连接操作，移至第 x+1 层为第 x+2 层建立 next 指针。
     * 每次只要知道下一层的最左边的节点，就可以从该节点开始，像遍历链表一样遍历该层的所有节点
     */
    public Node connect(Node root) {
        if (root == null) return null;

        Node curr = root; // 当前层的节点
        Node dummy = new Node(0); // 下一层的虚拟头结点
        Node prev = dummy; // 下一层的上一个节点

        while (curr != null) {
            // 遍历当前层
            if (curr.left != null) {
                prev.next = curr.left;
                prev = prev.next;
            }
            if (curr.right != null) {
                prev.next = curr.right;
                prev = prev.next;
            }
            curr = curr.next;   //继续访问这行的下一个节点
            // 当前层遍历完毕，进入下一层
            if (curr == null) {
                curr = dummy.next; // //把下一层串联成一个链表之后，让他赋值给cur后续继续循环，直到cur为空为止
                dummy.next = null;
                prev = dummy;
            }
        }
        return root;
    }
    /**
     * another form
     */
    public Node connect1(Node root) {
        if(root == null) return null;

        Node head = new Node(0);
        Node tail = head;
        Node cur = root;

        while(true){
            while(cur != null){
                if(cur.left != null){
                    tail.next = cur.left;
                    tail = tail.next;
                }
                if(cur.right != null){
                    tail.next = cur.right;
                    tail = tail.next;
                }
                cur = cur.next;
            }
            cur = head.next;
            head.next = null;    //sanity clean
            tail = head;

            if(cur == null) break;
        }
        return root;
    }
}
