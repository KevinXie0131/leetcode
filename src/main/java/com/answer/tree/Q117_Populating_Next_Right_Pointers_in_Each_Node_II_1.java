package com.answer.tree;


import java.util.ArrayDeque;
import java.util.Deque;

public class Q117_Populating_Next_Right_Pointers_in_Each_Node_II_1 {
    public Node connect(Node root) {
        if(root == null)
            return null;

        Node head = new Node(0);
        Node headCur = head;
        Node cur = root;

        while(true){
            while(cur != null){
                if(cur.left != null){
                    headCur.next = cur.left;
                    headCur = headCur.next;
                }

                if(cur.right != null){
                    headCur.next = cur.right;
                    headCur = headCur.next;
                }

                cur = cur.next;
            }

            cur = head.next;
            head.next = null;    //sanity clean
            headCur = head;

            if(cur == null)
                break;
        }

        return root;

    }
}