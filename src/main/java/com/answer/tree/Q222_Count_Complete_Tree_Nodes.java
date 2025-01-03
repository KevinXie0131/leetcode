package com.answer.tree;

import com.template.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Q222_Count_Complete_Tree_Nodes {
    /**
     * 精简的代码版本，其实不建议⼤家照着这个来写，代码确实精简，但隐藏了⼀些内容，连遍历的顺序都看不出来
     * 时间复杂度：O(n)
     * 空间复杂度：O(log n)，算上了递归系统栈占用的空间
     */
    public int countNodes(TreeNode root) {
        if (root == null){
            return 0;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }
    /**
     * 递归 (按照普通⼆叉树的逻辑)
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
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
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
     * 完全⼆叉树
     * 完全⼆叉树只有两种情况，情况⼀：就是满⼆叉树，情况⼆：最后⼀层叶⼦节点没有满
     *    对于情况⼀，可以直接⽤ 2^树深度 - 1 来计算，注意这⾥根节点深度为1
     *    对于情况⼆，分别递归左孩⼦，和右孩⼦，递归到某⼀深度⼀定会有左孩⼦或者右孩⼦为满⼆叉树，然后依然可以按照情况1来计算。
     *
     * 可以看出如果整个树不是满⼆叉树，就递归其左右孩⼦，直到遇到满⼆叉树为⽌，⽤公式计算这个⼦树（满⼆叉树）的节点数量
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
    /**
     * More clear answer
     * 时间复杂度：O(logn * logn)
     * 空间复杂度：O(logn)
     */
    public int countNodes_5(TreeNode root) {
        if (root == null) return 0;
        TreeNode left = root.left;
        TreeNode right = root.right;

        int leftHeight = 0, rightHeight = 0; // 这⾥初始为0是有⽬的的，为了下⾯求指数⽅便
        while (left != null) { // 求左⼦树深度
            left = left.left;
            leftHeight++;
        }
        while (right != null) { // 求右⼦树深度
            right = right.right;
            rightHeight++;
        }

        if (leftHeight == rightHeight) {
            return (2 << leftHeight) - 1; // 注意(2<<1) 相当于2^2，所以leftHeight初始为0
        }

        return countNodes_5(root.left) + countNodes_5(root.right) + 1;
    }
}
