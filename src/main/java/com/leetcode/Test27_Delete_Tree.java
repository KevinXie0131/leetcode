package com.leetcode;

import com.template.TreeNode;

public class Test27_Delete_Tree {
    public static void main(String[] args) {

        /**
         * [5,3,6,2,4,null,7]
         */
        TreeNode root1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(7);
        root1.setLeft(node2);
        root1.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setRight(node6);

        TreeNode root = deleteNode1(root1, 3);
        System.out.println(root);
    }

    static public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null;

        if(root.value > key){
            root.left = deleteNode(root.left, key);
        } else if(root.value < key) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            }
            else if (root.right == null) {
                return root.left;
            }
            else if(root.right != null && root.right != null){
                TreeNode node = root.right;
                while(node.left != null){
                    node = node.left;
                }
                node.left = root.left;
                root = root.right;
            }
        }
        return root;
    }

    static public TreeNode deleteNode1(TreeNode root, int key) {
        TreeNode cur = root, curParent = null;
        while (cur != null && cur.value != key) {
            curParent = cur;
            if (cur.value > key) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        if (cur == null) {
            return root;
        }
        if (cur.left == null && cur.right == null) {
            cur = null;
        } else if (cur.right == null) {
            cur = cur.left;
        } else if (cur.left == null) {
            cur = cur.right;
        } else {
            TreeNode successor = cur.right, successorParent = cur;
            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }
            if (successorParent.value == cur.value) {
                successorParent.right = successor.right;
            } else {
                successorParent.left = successor.right;
            }
            successor.right = cur.right;
            successor.left = cur.left;
            cur = successor;
        }
        if (curParent == null) {
            return cur;
        } else {
            if (curParent.left != null && curParent.left.value == key) {
                curParent.left = cur;
            } else {
                curParent.right = cur;
            }
            return root;
        }
    }

    // deleteNode2() could have issue
    public static TreeNode deleteNode2(TreeNode root, int key) {
        if(root == null) return null;

        TreeNode cur = root;
        TreeNode parent = root;
        while(cur != null){
            parent = cur;
            if(cur.value > key){
                cur = cur.left;
            } else if(cur.value < key) {
                cur = cur.right;
            }
            if (cur != null && cur.value == key) {
                if (cur.left == null && cur.right == null) {
                    cur = null; return root;
                }
                else if (cur.left == null) {cur = cur.right;return root; }
                else if (cur.right == null) {cur = cur.left;return root; }
                else if(cur.right != null && cur.right != null){
                    TreeNode node = cur.right;
                    while(node.left != null){
                        node = node.left;
                    }
                    node.left = cur.left;
                    //   cur = cur.right;
                    if (parent.value > cur.value) {
                        parent.left = node;
                    } else{
                        parent.right = node;
                    }

                    return root;
                }
            }

        }

        return root;
    }
}
