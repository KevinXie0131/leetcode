package com.answer.tree;

import com.template.TreeNode;
import java.util.*;

public class Q226_Invert_Binary_Tree {
    /**
     * 翻转二叉树
     * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
     * Given the root of a binary tree, invert the tree, and return its root.
     */
    /**
     * 把每一个节点的左右孩子交换一下
     * 前后序遍历都可以
     * 中序不行，因为先左孩子交换孩子，再根交换孩子（做完后，右孩子已经变成了原来的左孩子），再右孩子交换孩子（此时其实是对原来的左孩子做交换）
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        // 前序遍历
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree(root.left);
        invertTree(root.right);
/*        if(root.right != null){
            invertTree(root.right);
        }
        if(root.left != null){
            invertTree(root.left);
        }*/
        return root; //函数返回时就表示当前这个节点，以及它的左右子树都已经交换完了
    }
    /**
     * 后序遍历
     */
    public TreeNode invertTree1(TreeNode root) {
        if (root == null) return root;

        invertTree(root.left);
        invertTree(root.right);

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        return root;
    }
    /**
     * another fornm
     */
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) return null;

        TreeNode left = invertTree2(root.left);
        TreeNode right = invertTree2(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
    /**
     * 文中我指的是递归的中序遍历是不行的，因为使用递归的中序遍历，某些节点的左右孩子会翻转两次。
     * 如果非要使用递归中序的方式写，也可以，如下代码就可以避免节点左右孩子翻转两次的情况
     */
    public TreeNode invertTree_2(TreeNode root) {
        if(root == null ) return root;

        if(root.left != null){
            invertTree(root.left); // 左
        }

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        if(root.left != null){
            invertTree(root.left); // 左
        }
        return root;
    }
    /**
     * BFS 广度优先遍历
     */
    public TreeNode invertTree_3(TreeNode root) {
        if (root == null) {
            return null;
        }
        ArrayDeque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);

        while (!deque.isEmpty()) { // 使用队列利用层序遍历实现交换
            int size = deque.size();
            while (size-- > 0) {
                TreeNode node = deque.poll(); //每次都从队列中拿一个节点，并交换这个节点的左右子树
                swap(node);
                if (node.left != null) deque.offer(node.left);
                if (node.right != null) deque.offer(node.right);
            }
        }
        return root;
    }

    public void swap(TreeNode root) { //左右子树交换顺序
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }
}
