package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q530_Minimum_Absolute_Difference_in_BST {
    int result = Integer.MAX_VALUE;
    TreeNode pre = null;

    public int getMinimumDifference(TreeNode root) {
        find(root);

        return result;
    }

    private void find(TreeNode root) {

        if (root == null) return;

        find(root.left);

        if (pre != null) {
            result = Math.min(result, root.value-pre.value);
        }
        pre = root;

        find(root.right);

    }
    /**
     *
     */
    List<Integer> list = new ArrayList<>();

    public int getMinimumDifference1(TreeNode root) {
        double result = Double.MAX_VALUE;
        inOrder1(root);

        for (int i = 0; i < list.size()-1; i++) {
            result = Math.min(list.get(i+1) - list.get(i), result);
        }

        return (int)result;
    }

    public void inOrder1 (TreeNode root) {
        if (root == null) return;

        inOrder1 (root.left);
        list.add(root.value);
        inOrder1 (root.right);
    }
    /**
     *
     */
    double result0 = Double.MAX_VALUE;
    int pre0 = -1;
    public int getMinimumDifference2(TreeNode root) {

        inOrder2(root);

        return (int)result;
    }

    public void inOrder2 (TreeNode root) {
        if (root == null) return;

        inOrder2 (root.left);

        if (pre0 < 0) {
            pre0 = root.value;
        } else {
            result = Math.min(root.value - pre0, result);
            pre0 = root.value;
        }

        inOrder2 (root.right);
    }
    /**
     *
     */
    double result1 = Double.MAX_VALUE;
    int pre1 = -1;
    Deque<TreeNode> stack = new ArrayDeque<>();

    public int getMinimumDifference3(TreeNode root) {

        while (!stack.isEmpty() || root != null) {
            while(root != null){
                stack.push(root);
                root= root.left;
            }

            TreeNode cur = stack.pop();

            if (pre1 < 0){
                pre1 = cur.value;
            } else {
                result1 = Math.min(cur.value-pre1, result);
                pre1 = cur.value;
            }

            root= cur.right;

        }

        return (int)result1;
    }

}
