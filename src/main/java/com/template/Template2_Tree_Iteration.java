package com.template;

import java.util.*;

public class Template2_Tree_Iteration {

    /**
     *          1
     *        |   |
     *      2       5
     *     | |     | |
     *    3   4   6   7
     */
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

        preOrderIteration(root);
        System.out.println();

        inOrderIteration(root);
        System.out.println();

        postOrderIteration(root);
    }

    public static void preOrderIteration (Node node) {
        Deque<Node> stack = new ArrayDeque<>();

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                System.out.print(node.value + " ");
                stack.push(node);
                node = node.left;
            }

            Node cur = stack.pop();
            node = cur.right;
        }
    }

    public static void inOrderIteration (Node node) {
        Deque<Node> stack = new ArrayDeque<>();

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            Node cur = stack.pop();
            System.out.print(cur.value + " ");
            node = cur.right;
        }
    }

    public static void postOrderIteration (Node node) {
        Deque<Node> stack = new ArrayDeque<>();
        ArrayList<Integer> list = new ArrayList<>();

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                list.add(node.value);
                stack.push(node);
                node = node.right; // left -> right
            }

            Node cur = stack.pop();
            node = cur.left; // right -> left
        }

        Collections.reverse(list);
        list.stream().forEach(e -> System.out.print(e + " "));
    }
}
