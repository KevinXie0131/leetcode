package com.answer.tree;

import com.template.TreeNode;
import java.util.*;

public class Q404_Sum_of_Left_Leaves {
    /**
     * 左叶子之和
     * 给定二叉树的根节点 root ，返回所有左叶子之和。
     * Given the root of a binary tree, return the sum of all left leaves.
     * A leaf is a node with no children. A left leaf is a leaf that is the left child of another node.
     */
    /**
     * 前序遍历
     */
    public int sumOfLeftLeaves_9(TreeNode root) {
        int sum = 0;
        dfs(root, sum);
        return sum;
    }

    public void dfs(TreeNode root, int sum) {
        if (root == null) return;
        // 中
        if (root.left != null && root.left.left == null && root.left.right == null) { // 左叶⼦节点处理逻辑
            sum += root.left.value;
        }
        dfs(root.left, sum);    // 左
        dfs(root.right, sum);  // 右
    }
    /**
     * ⾸先要注意是判断左叶⼦，不是⼆叉树左侧节点，所以不要上来想着层序遍历
     * 左叶⼦的明确定义：如果左节点不为空，且左节点没有左右孩⼦，那么这个节点就是左叶⼦
     *
     * 判断当前节点是不是左叶子是无法判断的，必须要通过节点的父节点来判断其左孩子是不是左叶子
     *
     * 递归法: 遍历顺序为后序遍历（左右中）, 通过递归函数的返回值来累加求取左叶⼦数值之和
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0; // 如果遍历到空节点，那么左叶子值一定是0
        }
        if (root.left == null && root.right== null) return 0; //其实这个也可以不写，如果不写不影响结果，但就会让递归多进行了一层。

        int leftValue = sumOfLeftLeaves(root.left);    // 左
        int rightValue = sumOfLeftLeaves(root.right);  // 右
        // 中
        if (root.left != null && root.left.left == null && root.left.right == null) { // 左叶⼦节点处理逻辑
            return root.left.value + leftValue + rightValue;
        }
        return leftValue + rightValue;
    }
    /**
     * 递归 （另一种形式的后序遍历）
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
     * 递归 （另一种形式的后序遍历）也是可以的
     */
    public int sumOfLeftLeaves_2(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right== null) return 0;
        int leftValue = sumOfLeftLeaves(root.left);    // 左
        // 通过节点的父节点来判断其左孩子是不是左叶子
        if (root.left != null && root.left.left == null && root.left.right == null) { // 左子树就是一个左叶子的情况
            leftValue = root.left.value;
        }
        int rightValue = sumOfLeftLeaves(root.right);  // 右
        int sum =  leftValue + rightValue;  // 中
        return sum;
    }
    /**
     * 迭代法 (迭代法使用前中后序都是可以的，只要把左叶子节点统计出来，就可以了)
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
    /**
     * 层序遍历迭代法
     */
    public int sumOfLeftLeaves3(TreeNode root) {
        int sum = 0;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode cur = queue.poll();

                if (cur.left != null) { // 左节点不为空
                    queue.offer(cur.left);
                    if(cur.left.left == null && cur.left.right == null){ // 左叶子节点
                        sum += cur.left.value;
                    }
                }
                if (cur.right != null) queue.offer(cur.right);

                size--;
            }
        }
        return sum;

    }
}
