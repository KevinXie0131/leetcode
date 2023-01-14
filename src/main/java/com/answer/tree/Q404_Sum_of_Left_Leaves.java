package com.answer.tree;

import com.template.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q404_Sum_of_Left_Leaves {

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        int leftValue = sumOfLeftLeaves(root.left);    // 左
        int rightValue = sumOfLeftLeaves(root.right);  // 右

        if (root.left != null && root.left.left == null
                && root.left.right == null) {

            return root.left.value + leftValue + rightValue;
        }
        return  leftValue + rightValue;
    }
    /**
     *
     */
    public int sumOfLeftLeaves1(TreeNode root) {
        if (root == null) return 0;

        int leftValue = sumOfLeftLeaves(root.left);
        int rightValue = sumOfLeftLeaves(root.right);

        int midValue = 0;
        if (root.left != null && root.left.left == null && root.left.right == null) {
            midValue = root.left.value;
        }
        int sum = midValue + leftValue + rightValue;
        return sum;
    }
    /**
     *
     */
    public int sumOfLeftLeaves2(TreeNode root) {
        if (root == null) return 0;
        int sum = 0;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode tmpNode = stack.pop();

            if (tmpNode.right != null) {
                stack.push(tmpNode.right);
            }
            if (tmpNode.left != null) {
                if(isLeafNode(tmpNode.left)){
                    sum += tmpNode.left.value;
                } else {
                    stack.push(tmpNode.left);
                }
            }
        }
        return sum;
    }

    public boolean isLeafNode(TreeNode node) {
        return node.left == null && node.right == null;
    }
}
