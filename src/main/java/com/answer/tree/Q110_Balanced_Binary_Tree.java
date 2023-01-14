package com.answer.tree;

import com.template.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Q110_Balanced_Binary_Tree {

    public boolean isBalanced(TreeNode root) {
        return getHeight(root) != -1 ? true: false;
    }

    int getHeight(TreeNode node) {
        if(node == null) {
            return 0;
        }
        int left = getHeight(node.left);
        if (left == -1) {
            return -1;
        }
        int right = getHeight(node.right);
        if (right == -1) {
            return -1;
        }

        if (Math.abs(left - right) > 1) {
            return -1;
        } else {
            return Math.max(left, right) + 1;
        }
    }

    /**
     *
     */
    public boolean isBalanced1(TreeNode root) {
        return getHeight1(root) != -1 ? true: false;
    }

    int getHeight1(TreeNode node) {
        if(node == null) {
            return 0;
        }
        int left = getHeight(node.left);
        int right = getHeight(node.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        } else {
            return Math.max(left, right) + 1;
        }
    }
}
