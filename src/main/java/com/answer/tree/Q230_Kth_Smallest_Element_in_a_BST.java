package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q230_Kth_Smallest_Element_in_a_BST {
    /**
     * 二叉搜索树中第 K 小的元素
     * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 小的元素（从 1 开始计数）。
     * Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
     */
    public static void main(String[] args) {
        // root = [3,1,4,null,2], k = 1
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(2);
        node1.left = node2;
        node1.right = node3;
        node2.right = node4;
        System.out.println(kthSmallest_0(node1, 1));
    }

    /**
     * Approach 1: Recursive Inorder Traversal
     */
    static int target = 0;
    static int res = 0;
    public static int kthSmallest(TreeNode root, int k) {
        target = k;
        recursion(root);
        return res;
    }
    static public void recursion(TreeNode root){
        if(root == null) {
            return;
        }

        recursion(root.left);
        if(target == 1){
            res = root.value;
        }
        target--;
        recursion(root.right);
    }
    /**
     * Approach 2: Iterative Inorder Traversal
     */
    public static int kthSmallest_0(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            TreeNode cur = stack.pop();
            if(k == 1) return cur.value;
            k--;
            root = cur.right;
        }
        return -1;
    }
    /**
     * Another form of recursion
     */
    public ArrayList<Integer> inorder(TreeNode root, ArrayList<Integer> arr) {
        if (root == null) return arr;
        inorder(root.left, arr);
        arr.add(root.value);
        inorder(root.right, arr);
        return arr;
    }

    public int kthSmallest_1(TreeNode root, int k) {
        ArrayList<Integer> nums = inorder(root, new ArrayList<Integer>());
        return nums.get(k - 1);
    }
    /**
     * Another form of iteration
     */
    public int kthSmallest_3(TreeNode root, int k) {
        LinkedList<TreeNode> stack = new LinkedList<>();

        while (true) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (--k == 0) return root.value;
            root = root.right;
        }
    }
}

