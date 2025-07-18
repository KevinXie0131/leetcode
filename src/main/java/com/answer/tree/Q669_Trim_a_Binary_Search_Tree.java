package com.answer.tree;

import com.template.TreeNode;

public class Q669_Trim_a_Binary_Search_Tree {
    /**
     * 修剪二叉搜索树
     * 给你二叉搜索树的根节点 root ，同时给定最小边界low 和最大边界 high。通过修剪二叉搜索树，使得所有节点的值在[low, high]中。修剪树 不应该 改变保留在树中的元素的相对结构 (即，如果没有被移除，原有的父代子代关系都应当保留)。 可以证明，存在 唯一的答案 。
     * 所以结果应当返回修剪好的二叉搜索树的新的根节点。注意，根节点可能会根据给定的边界发生改变。
     * Given the root of a binary search tree and the lowest and highest boundaries as low and high, trim the tree so that all its elements lies in [low, high]. Trimming the tree should not change the relative structure of the elements that will remain in the tree (i.e., any node's descendant should remain a descendant). It can be proven that there is a unique answer.
     * Return the root of the trimmed binary search tree. Note that the root may change depending on the given bounds.
     */
    /**
     * 修剪⼆叉搜索树 递归
     */
    public TreeNode trimBST_0(TreeNode root, int low, int high) {
        if (root == null) return null;
        if(root.value < low){
            TreeNode right = trimBST_0( root.right,  low,  high);
            return right;
        }
        if(root.value > high){
            TreeNode left = trimBST_0( root.left,  low,  high);
            return left;
        }
        // root在[low,high]范围内
        root.left =  trimBST_0( root.left,  low,  high);
        root.right =  trimBST_0( root.right,  low,  high);
        return root;
    }
    /**
     * 递归
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if(root == null){
            return null;
        }
        // 如果root（当前节点）的元素⼩于low的数值，那么应该递归右⼦树，并返回右⼦树符合条件的头结点。
        if (root.value < low) {
            return trimBST( root.right,  low,  high); // 寻找符合区间[low, high]的节点
        }
        // 如果root(当前节点)的元素⼤于high的，那么应该递归左⼦树，并返回左⼦树符合条件的头结点
        if (root.value > high) {
            return trimBST( root.left,  low,  high); // 寻找符合区间[low,high]的节点
        }
        if (root.value >= low && root.value <= high) {
            root.left = trimBST( root.left,  low,  high); // root->left接⼊符合条件的左孩⼦
            root.right = trimBST( root.right,  low,  high); // root->right接⼊符合条件的右孩⼦
        }
        return root;
    }
    /**
     * 迭代法
     * 在剪枝的时候，可以分为三步：
     *      将root移动到[L, R] 范围内，注意是左闭右闭区间
     *      剪枝左⼦树
     *      剪枝右⼦树
     */
    public TreeNode trimBST1(TreeNode root, int low, int high) {
        // 找到修剪之后的二叉搜索树的头节点 root
        if(root == null){
            return null;
        }
        // 处理头结点，让root移动到[L, R] 范围内，注意是左闭右闭
        while(root != null && (root.value > high || root.value < low)){
            if(root.value > high){
                root = root.left; // ⼩于L往右⾛
            }
            else{
                root = root.right; // ⼤于R往左⾛
            }
        }
        TreeNode cur = root;
        // 此时root已经在[L, R] 范围内，处理左孩⼦元素⼩于L的情况
        // 修剪 root 的左子树，将 < low 的节点删除
        while(cur != null){
            while(cur.left != null && cur.left.value < low){
                cur.left = cur.left.right;
            }
            cur = cur.left;
        }
        // 此时root已经在[L, R] 范围内，处理右孩⼦⼤于R的情况
        // 修剪 root 的右子树，将 > high 的节点删除
        cur = root;
        while(cur != null){
            while(cur.right != null && cur.right.value > high){
                cur.right = cur.right.left;
            }
            cur = cur.right;
        }
        return root;
    }
    /**
     * 迭代 同上
     * 因为二叉搜索树的有序性，不需要使用栈模拟递归的过程
     */
    public TreeNode trimBST2(TreeNode root, int low, int high) {
        if(root == null){
            return null;
        }
        while(root != null && (root.value < low || root.value > high)){
            if (root.value < low) {
                root = root.right;
            }
            if (root.value > high) {
                root = root.left;
            }
        }

        TreeNode cur = root;
        //deal with root's left sub-tree, and deal with the value smaller than low.
        while(cur != null){
            while(cur.left != null && cur.left.value < low){
                cur.left = cur.left.right;
            }
            cur = cur.left;
        }

        cur = root;  //go back to root;
        //deal with root's righg sub-tree, and deal with the value bigger than high.
        while(cur != null){
            while(cur.right != null && cur.right.value > high){
                cur.right = cur.right.left;
            }
            cur = cur.right;
        }

        return root;
    }
}
