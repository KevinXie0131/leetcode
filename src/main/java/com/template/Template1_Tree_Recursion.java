package com.template;

public class Template1_Tree_Recursion {

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

        preOrderRecursion(root);
        System.out.println();

        inOrderRecursion(root);
        System.out.println();

        postOrderRecursion(root);
    }

    public static void preOrderRecursion (Node node) {
        if (node != null) {
            System.out.print(node.value + "  ");
            preOrderRecursion(node.left);
            preOrderRecursion(node.right);
        }
    }

    public static void inOrderRecursion (Node node) {
        if (node != null) {
            inOrderRecursion(node.left);
            System.out.print(node.value + "  ");
            inOrderRecursion(node.right);
        }
    }

    public static void postOrderRecursion (Node node) {
        if (node != null) {
            postOrderRecursion(node.left);
            postOrderRecursion(node.right);
            System.out.print(node.value + "  ");
        }
    }
}
