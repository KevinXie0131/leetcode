package com.answer.tree;

import com.template.TreeNode;

public class Q450_Delete_Node_in_a_BST {

    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null;

        if(root.value > key){
            root.left = deleteNode(root.left, key);
        } else if(root.value < key) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null) {return root.right; }
            else if (root.right == null) {return root.left; }
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

    /**
     *
     */
    public TreeNode deleteNode1(TreeNode root, int key) {
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
}
