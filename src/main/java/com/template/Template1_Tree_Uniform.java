package com.template;

import java.util.*;

public class Template1_Tree_Uniform {

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
        List<Integer> list = preorderTraversal(root);
        list.stream().forEach(e -> System.out.print(e + "  "));
        System.out.println();

        /**
         * 3  2  4  1  6  5  7
         */
        list = inorderTraversal(root);
        list.stream().forEach(e -> System.out.print(e + "  "));
        System.out.println();

        /**
         * 3  4  2  6  7  5  1
         */
        list = postorderTraversal(root);
        list.stream().forEach(e -> System.out.print(e + "  "));
        System.out.println();
    }

    public static List<Integer> preorderTraversal(Node root) {
        List<Integer> result = new LinkedList<>();
        Stack<Node> st = new Stack<>();
        if (root != null) st.push(root);
        while (!st.empty()) {
            Node node = st.peek();
            if (node != null) {
                st.pop(); // 将该节点弹出，避免重复操作，下面再将右中左节点添加到栈中
                if (node.right!=null) st.push(node.right);  // 添加右节点（空节点不入栈）
                if (node.left!=null) st.push(node.left);    // 添加左节点（空节点不入栈）
                st.push(node);                          // 添加中节点
                st.push(null); // 中节点访问过，但是还没有处理，加入空节点做为标记。

            } else { // 只有遇到空节点的时候，才将下一个节点放进结果集
                st.pop();           // 将空节点弹出
                node = st.peek();    // 重新取出栈中元素
                st.pop();
                result.add(node.value); // 加入到结果集
            }
        }
        return result;
    }

    public static List<Integer> inorderTraversal(Node root) {
        List<Integer> result = new LinkedList<>();
        Stack<Node> st = new Stack<>();
        if (root != null) st.push(root);
        while (!st.empty()) {
            Node node = st.peek();
            if (node != null) {
                st.pop(); // 将该节点弹出，避免重复操作，下面再将右中左节点添加到栈中
                if (node.right!=null) st.push(node.right);  // 添加右节点（空节点不入栈）
                st.push(node);                          // 添加中节点
                st.push(null); // 中节点访问过，但是还没有处理，加入空节点做为标记。

                if (node.left!=null) st.push(node.left);    // 添加左节点（空节点不入栈）
            } else { // 只有遇到空节点的时候，才将下一个节点放进结果集
                st.pop();           // 将空节点弹出
                node = st.peek();    // 重新取出栈中元素
                st.pop();
                result.add(node.value); // 加入到结果集
            }
        }
        return result;
    }
    public static List<Integer> postorderTraversal(Node root) {
        List<Integer> result = new LinkedList<>();
        Stack<Node> st = new Stack<>();
        if (root != null) st.push(root);
        while (!st.empty()) {
            Node node = st.peek();
            if (node != null) {
                st.pop(); // 将该节点弹出，避免重复操作，下面再将右中左节点添加到栈中
                st.push(node);                          // 添加中节点
                st.push(null); // 中节点访问过，但是还没有处理，加入空节点做为标记。
                if (node.right!=null) st.push(node.right);  // 添加右节点（空节点不入栈）
                if (node.left!=null) st.push(node.left);    // 添加左节点（空节点不入栈）

            } else { // 只有遇到空节点的时候，才将下一个节点放进结果集
                st.pop();           // 将空节点弹出
                node = st.peek();    // 重新取出栈中元素
                st.pop();
                result.add(node.value); // 加入到结果集
            }
        }
        return result;
    }

}
