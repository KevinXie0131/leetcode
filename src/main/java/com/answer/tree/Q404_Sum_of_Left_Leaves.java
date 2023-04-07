package com.answer.tree;

import com.template.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q404_Sum_of_Left_Leaves {
    /**
     * ⾸先要注意是判断左叶⼦，不是⼆叉树左侧节点，所以不要上来想着层序遍历
     * 左叶⼦的明确定义：如果左节点不为空，且左节点没有左右孩⼦，那么这个节点就是左叶⼦
     *
     * 递归法: 遍历顺序为后序遍历（左右中）, 通过递归函数的返回值来累加求取左叶⼦数值之和
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftValue = sumOfLeftLeaves(root.left);    // 左
        int rightValue = sumOfLeftLeaves(root.right);  // 右
        // 中
        if (root.left != null && root.left.left == null  // 左叶⼦节点处理逻辑
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
     * 迭代法
     * 通过节点的⽗节点来判断其左孩⼦是不是左叶⼦
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
