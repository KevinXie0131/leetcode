package com.answer.tree;

import com.template.TreeNode;

public class Q298_Binary_Tree_Longest_Consecutive_Sequence {
    /**
     * 二叉树最长连续序列
     * 给你一棵指定的二叉树的根节点 root ，请你计算其中 最长连续序列路径 的长度。
     * 最长连续序列路径 是依次递增 1 的路径。该路径，可以是从某个初始节点到树中任意节点，通过「父 - 子」关系连接而产生的任意路径。且必须从父节点到子节点，反过来是不可以的。
     * Given a binary tree, find the length of the longest consecutive sequence path.
     * The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).
     */
    public static void main(String[] args) {
        Q298_Binary_Tree_Longest_Consecutive_Sequence solution = new Q298_Binary_Tree_Longest_Consecutive_Sequence();
        // 用例1: [1,null,3,2,4,null,null,null,5], 最长序列为3->4->5，长度3
        //            1
        //             \
        //              3
        //             / \
        //            2   4
        //                 \
        //                  5
        TreeNode root1 = new TreeNode(1);
        root1.right = new TreeNode(3);
        root1.right.left = new TreeNode(2);
        root1.right.right = new TreeNode(4);
        root1.right.right.right = new TreeNode(5);
        maxLen1 = 0; maxLen = 0; max = 0; System.out.println("Test1: " + (solution.longestConsecutive2(root1) == 3)); // true
        // 用例2: [2,null,3,2,null,1], 最长只有2->3，长度2
        //            2
        //             \
        //              3
        //             /
        //            2
        //           /
        //          1
        TreeNode root2 = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.right.left = new TreeNode(2);
        root2.right.left.left = new TreeNode(1);
        maxLen1 = 0; maxLen = 0; max = 1;  System.out.println("Test2: " + (solution.longestConsecutive2(root2) == 2)); // true
        // 用例3: 所有节点相同 [5,5,5], 最长只有1
        TreeNode root3 = new TreeNode(5);
        root3.left = new TreeNode(5);
        root3.right = new TreeNode(5);
        maxLen1 = 0; maxLen = 0; max = 1; System.out.println("Test3: " + (solution.longestConsecutive2(root3) == 1)); // true
        // 用例4: 空树
        maxLen1 = 0; maxLen = 0; max = 1; System.out.println("Test4: " + (solution.longestConsecutive2(null) == 0)); // true
        // 用例5: 只有一个节点
        TreeNode root5 = new TreeNode(42);
        maxLen1 = 0; maxLen = 0; max = 1; System.out.println("Test5: " + (solution.longestConsecutive2(root5) == 1)); // true
    }
    /**
     * 递归遍历二叉树，每到一个节点，判断它和父节点是否连续（当前节点值等于父节点值加1）。
     * 若连续，长度加1，否则重置为1。
     * 用 maxLen 记录遍历过程中遇到的最大连续长度。
     */
    static int maxLen = 1;

    static public int longestConsecutive(TreeNode root) {
        dfs1(root, null, 1);
        return maxLen;
    }
    // 递归 / 先序遍历
    static public void dfs(TreeNode root, TreeNode parent, int len){
        if(root == null){
            return;
        }
        if(parent != null && root.value == parent.value + 1) {
            len++;
        } else {
            len = 1;
        }
        maxLen = Math.max(maxLen, len);
        dfs(root.left, root, len);
        dfs(root.right, root, len);
    }
    // 递归 / 后序遍历
    static public int dfs1(TreeNode root, TreeNode parent, int len){ // works too
        if(root == null){
            return 0;
        }
        int left = dfs1(root.left, root, len);
        int right = dfs1(root.right, root, len);

        if(root.left != null && root.left.value == root.value + 1) {
            left++;
        } else {
            left = 1;
        }
        if(root.right != null && root.right.value == root.value + 1) {
            right++;
        } else {
            right = 1;
        }
        int curLen = Math.max(left, right);
        maxLen = Math.max(maxLen, curLen);
        return curLen;
    }
    /**
     * 利用分治法的思想，对左右子节点分别处理，如果左子节点存在且节点值比其父节点值大1，则递归调用函数，
     * 如果节点值不是刚好大1，则递归调用重置了长度的函数
     */
    static int maxLen1 = 1;

    static public int longestConsecutive1(TreeNode root) {
        if (root == null){
            return 0;
        }
        dfs1(root, 1);
        return maxLen1;
    }
    // 自顶向下
    static public void dfs1(TreeNode root, int len){
        maxLen1 = Math.max(maxLen1, len);

        if (root.left != null) {
            if (root.left.value == root.value + 1) {
                dfs1(root.left, len + 1);
            } else {
                dfs1(root.left, 1);
            }
        }
        if (root.right != null) {
            if (root.right.value == root.value + 1) {
                dfs1(root.right, len + 1);
            } else {
                dfs1(root.right, 1);
            }
        }
    }
    /**
     * refer to Q687_Longest_Univalue_Path
     */
    static int max = 1;

    public static int longestConsecutive2(TreeNode root) {
        if(root == null){
            return 0;
        }
        dfs(root);
        return max;
    }

    public static int dfs(TreeNode node){
        if(node != null && node.left == null && node.right == null){
            return 1;
        }
        int left = 0;
        int right = 0;
        if(node.left != null){
            if(node.value == node.left.value - 1){
                left = dfs(node.left) + 1;
            } else{
                left = dfs(node.left);
            }
        }
        if(node.right != null){
            if(node.value == node.right.value - 1){
                right = dfs(node.right) + 1;
            } else{
                right = dfs(node.right);
            }
        }
        int res = Math.max(left , right);
        max = Math.max(max, res);
        return res;
    }
}
