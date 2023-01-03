package com.template;

import java.util.*;

public class Template1_Tree_Recursion_Stack {

    public static void main(String[] args) {
        Node root = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);

        root.setLeft(node2);
        root.setRight(node5);
        node2.setLeft(node3);
        node2.setRight(node4);
        node5.setLeft(node6);
        node5.setRight(node7);

        /**
         * 1  2  3  4  5  6  7
         */
        preOrderRecursion(root);
        System.out.println();

        /**
         * 3  4  2  6  7  5  1
         */
        postOrderRecursion(root);
    }

    public static void preOrderRecursion (Node root) {
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);

        while(!stack.isEmpty()) {
            Node node = stack.pop();
            if (node != null) {
                System.out.print(node.value + "  ");
            } else {
                continue;
            }
            if(node.right != null) {  // right is before left
                stack.push(node.right);
            }
            if(node.left != null) {
                stack.push(node.left);
            }
        }
    }

    public static void postOrderRecursion (Node root) {
        List<Integer> list = new ArrayList<>();

        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);

        while(!stack.isEmpty()) {
            Node node = stack.pop();
            if (node != null) {
                list.add(node.value);
            } else {
                continue;
            }
            if(node.left != null) {   // left is before leftright
                stack.push(node.left);
            }
            if(node.right != null) {
                stack.push(node.right);
            }
        }

        Collections.reverse(list);
        list.stream().forEach(e -> System.out.print(e + "  "));
    }
}