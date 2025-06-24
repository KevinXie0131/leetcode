package com.answer.tree;

import com.template.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Q1382_Balance_a_Binary_Search_Tree {
    /**
     * 将二叉搜索树变平衡
     * 给你一棵二叉搜索树，请你返回一棵 平衡后 的二叉搜索树，新生成的树应该与原来的树有着相同的节点值。如果有多种构造方法，请你返回任意一种。
     * 如果一棵二叉搜索树中，每个节点的两棵子树高度差不超过 1 ，我们就称这棵二叉搜索树是 平衡的
     * Given the root of a binary search tree, return a balanced binary search tree with the same node values. If there is more than one answer, return any of them.
     * A binary search tree is balanced if the depth of the two subtrees of every node never differs by more than 1.
     */
    /**
     * 可以中序遍历把二叉树转变为有序数组，然后在根据有序数组构造平衡二叉搜索树
     * 98.验证二叉搜索树 学习二叉搜索树的特性
     * 108.将有序数组转换为二叉搜索树 学习如何通过有序数组构造二叉搜索树
     */
    List<Integer> list = new ArrayList<>();
    public TreeNode balanceBST(TreeNode root) {
        traversal(root);
        Integer[] nums = list.toArray(new Integer[0]);
        return build(nums, 0, nums.length - 1);

    }
    void traversal(TreeNode root) { // 递归中序遍历将⼆叉搜索树转变成⼀个数组
        if (root == null) return;

        traversal(root.left);
        list.add(root.value); // 将⼆叉搜索树转换为有序数组
        traversal(root.right);
    }
    public TreeNode build(Integer[] nums, int left , int right){ // 将有序数组转换为⼀棵⼆叉平衡搜索树
        if(left> right){
            return null;
        }
        int mid = (left + right) >>> 1;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = build(nums, left, mid -1);
        root.right = build(nums, mid + 1, right);

        return root;
    }
    /**
     * 另一种形式： 传入ArrayList
     */
    public TreeNode build1(ArrayList<Integer> nums, int left , int right){ // 将有序数组转换为⼀棵⼆叉平衡搜索树
        if(left> right){
            return null;
        }
        int mid = (left + right) >>> 1;
        TreeNode root = new TreeNode(nums.get(mid));
        root.left = build1(nums, left, mid -1);
        root.right = build1(nums, mid + 1, right);

        return root;
    }
}
