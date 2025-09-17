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
     * 如果当前节点值 < low，整个左子树都小于 low，需要修剪整个左子树，递归处理右子树，并返回新的根。
     * 如果当前节点值 > high，整个右子树都大于 high，需要修剪整个右子树，递归处理左子树，并返回新的根。
     * 如果当前节点值在 [low, high] 内，递归修剪左右子树，返回当前节点
     */
    public TreeNode trimBST0(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        if(root.value < low){
            TreeNode right = trimBST0(root.right, low, high); // 当前节点太小，修剪其右子树
            return right;
        }
        else if(root.value > high){
            TreeNode left = trimBST0(root.left, low, high);  // 当前节点太大，修剪其左子树
            return left;
        }
        else { // root在[low,high]范围内
            root.left = trimBST0(root.left, low, high);  // 当前节点在区间内，递归修剪两边
            root.right = trimBST0(root.right, low, high);
        }
        return root;
    }
    /**
     * 递归
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if(root == null) {
            return null;
        }
        // 如果root（当前节点）的元素⼩于low的数值，那么应该递归右⼦树，并返回右⼦树符合条件的头结点。
        if (root.value < low) {
            return trimBST(root.right, low, high); // 寻找符合区间[low, high]的节点
        }
        // 如果root(当前节点)的元素⼤于high的，那么应该递归左⼦树，并返回左⼦树符合条件的头结点
        else if (root.value > high) {
            return trimBST(root.left, low, high); // 寻找符合区间[low,high]的节点
        }
        else if (root.value >= low && root.value <= high) {
            root.left = trimBST(root.left, low, high); // root.left接⼊符合条件的左孩⼦
            root.right = trimBST(root.right, low, high); // root.right接⼊符合条件的右孩⼦
        }
        return root;
    }
    /**
     * 后序递归，从底向上反馈
     * 对以 root 为根的二叉搜索树进行修剪，只保留 [low, high] 范围内的节点
     *
     * 单层逻辑是比较本节点的值是否在范围内，若在向上返回本节点，若小于范围向上返回比节点大的右孩子，若大于范围向上返回比节点小的左孩子，
     * 这时候可能有人要问了，万一右孩子还是比范围小或者左孩子还是比范围大呢，其实这种情况是不会出现的，因为我们是从底部叶子节点向上返回的，
     * 也就是说当前节点以下的所有节点都是经过考验的在范围内的节点，所以不存在上述情况
     */
    public TreeNode trimBST7(TreeNode root, int low, int high) {
        if (root == null) {  // 如果遇到空节点，直接返回空
            return null;
        }
        root.left = trimBST7(root.left, low, high); // 先递归修剪左子树，修剪后的左子树接回 root->left
        root.right = trimBST7(root.right, low, high); // 再递归修剪右子树，修剪后的右子树接回 root->right

        if (root.value < low) {// 当前节点值小于 low，说明整棵左子树都比 low 小（BST 的性质），整个左子树和当前节点都要丢弃，返回修剪后的右子树
            return root.right;
        }
        else if (root.value > high) { // 当前节点值大于 high，说明整棵右子树都比 high 大，丢弃右子树和当前节点，返回修剪后的左子树
            return root.left;
        } else {    // 如果当前节点在 [low, high] 范围内，则保留它，并返回它本身
            return root;
        }
    }
    /**
     * 迭代法 因为二叉搜索树的有序性，不需要使用栈模拟递归的过程
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
        while(cur != null){ // deal with root's left sub-tree, and deal with the value smaller than low.
            while(cur.left != null && cur.left.value < low){
                cur.left = cur.left.right;
            }
            cur = cur.left;
        }
        // 此时root已经在[L, R] 范围内，处理右孩⼦⼤于R的情况
        // 修剪 root 的右子树，将 > high 的节点删除
        cur = root; //go back to root;
        while(cur != null){ //deal with root's righg sub-tree, and deal with the value bigger than high.
            while(cur.right != null && cur.right.value > high){
                cur.right = cur.right.left;
            }
            cur = cur.right;
        }
        return root;
    }
}
