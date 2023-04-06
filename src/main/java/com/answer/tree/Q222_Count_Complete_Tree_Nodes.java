package com.answer.tree;

import com.template.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Q222_Count_Complete_Tree_Nodes {
    /**
     * 精简的代码版本，其实不建议⼤家照着这个来写，代码确实精简，但隐藏了⼀些内容，连遍历的顺序都看不出来
     */
    public int countNodes(TreeNode root) {
        if (root == null){
            return 0;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }
    /**
     * 递归
     * 确定单层递归的逻辑：先求它的左⼦树的节点数量，再求的右⼦树的节点数量，最
     * 后取总和再加⼀ （加1是因为算上当前中间节点）就是⽬前节点为根节点的节点数量
     */
    public int countNodes_1(TreeNode root) {
        if (root == null){
            return 0;
        }
        int left = countNodes(root.left);   // 左
        int right = countNodes(root.right); // 右
        return left + right + 1;            // 中
    }
    /**
     * 递归法 按照普通⼆叉树的逻辑来求
     * 层序遍历
     */
    public int countNodes_2(TreeNode root) {
        if (root == null){
            return 0;
        }

        Queue<TreeNode> que = new LinkedList<TreeNode>();
        que.offer(root);
        int size = 0;
        while (!que.isEmpty()) {
            TreeNode tmpNode = que.poll();
            size++; // 记录节点数量

            if (tmpNode.left != null) que.offer(tmpNode.left);
            if (tmpNode.right != null) que.offer(tmpNode.right);
        }
        return size;
    }
    /**
     *
     */
    public int countNodes_3(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int leftDepth = getDepth(root.left);
        int rightDepth = getDepth(root.right);
        if (leftDepth == rightDepth) {// 左子树是满二叉树
            // 2^leftDepth其实是 （2^leftDepth - 1） + 1 ，左子树 + 根结点
            return (1 << leftDepth) + countNodes(root.right);
        } else {// 右子树是满二叉树
            return (1 << rightDepth) + countNodes(root.left);
        }

    }

    private int getDepth(TreeNode root) {
        int depth = 0;
        while (root != null) {
            root = root.left;
            depth++;
        }
        return depth;
    }
}
