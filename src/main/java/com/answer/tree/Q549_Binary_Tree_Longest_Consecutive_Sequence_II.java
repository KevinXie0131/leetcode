package com.answer.tree;

import com.template.TreeNode;

public class Q549_Binary_Tree_Longest_Consecutive_Sequence_II {
    /**
     * 二叉树最长连续序列 II
     * 给定二叉树的根 root ，返回树中最长连续路径的长度。
     * 连续路径是路径中相邻节点的值相差 1 的路径。此路径可以是增加或减少。
     *  例如， [1,2,3,4] 和 [4,3,2,1] 都被认为有效，但路径 [1,2,4,3] 无效。
     * 另一方面，路径可以是子-父-子顺序，不一定是父子顺序。
     * Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.
     * Especially, this path can be either increasing or decreasing. For example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid. On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.
     */
    public static void main(String[] args) {
        Q549_Binary_Tree_Longest_Consecutive_Sequence_II solution = new Q549_Binary_Tree_Longest_Consecutive_Sequence_II();
        // 用例1: [2,1,3]
        //     2
        //    / \
        //   1   3
        // 最长序列: 1-2-3, 长度3
        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(3);
        System.out.println("Test1: " + (solution.longestConsecutive(root1) == 3)); // true
        // 用例2: [1,2,0,3]
        //      1
        //     / \
        //    2   0
        //   /
        //  3
        // 最长序列: 3-2-1-0, 长度4
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(0);
        root2.left.left = new TreeNode(3);
        maxLen = 0; System.out.println("Test2: " + (solution.longestConsecutive(root2) == 4)); // true
        // 用例3: [3,2,4,1]
        //      3
        //     / \
        //    2   4
        //   /
        //  1
        // 最长序列: 1-2-3-4, 长度4
        TreeNode root3 = new TreeNode(3);
        root3.left = new TreeNode(2);
        root3.left.left = new TreeNode(1);
        root3.right = new TreeNode(4);
        maxLen = 0; System.out.println("Test3: " + (solution.longestConsecutive(root3) == 4)); // true
        // 用例4: 所有节点相同 [5,5,5]
        TreeNode root4 = new TreeNode(5);
        root4.left = new TreeNode(5);
        root4.right = new TreeNode(5);
        maxLen = 0;  System.out.println("Test4: " + (solution.longestConsecutive(root4) == 1)); // true
        // 用例5: 空树
        maxLen = 0; System.out.println("Test5: " + (solution.longestConsecutive(null) == 0)); // true
        // 用例6: 只有一个节点
        TreeNode root6 = new TreeNode(100);
        maxLen = 0; System.out.println("Test6: " + (solution.longestConsecutive(root6) == 1)); // true
    }
    /**
     * 支持递增和递减的连续路径，可以从父到子或从子到父连接（路径不一定要是根到叶，也可以是树中任意一段）。
     * 递增（如 1-2-3）和递减（如 3-2-1）都可以。
     * 路径可以从任意节点到任意节点，不必经过根。
     */
    static int maxLen = 0;

    static public int longestConsecutive(TreeNode root) {
        dfs(root);
        return maxLen;
    }
    /**
     * 返回以当前节点为根的最长递增和递减连续序列长度
     * int[0] - 以当前节点为根的递增长度
     * int[1] - 以当前节点为根的递减长度
     */
    static public int[] dfs(TreeNode root) {
        if(root == null) return new int[]{0, 0};
        int incr = 1, decr = 1;
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);

        if(root.left != null){
            if(root.left.value + 1 == root.value) {
             //   incr = left[0] + 1; // works too
                incr =  Math.max(incr, left[0] + 1);
            }
            if(root.left.value - 1 == root.value){
            //    decr = left[1] + 1; // works too
                decr =  Math.max(decr, left[1] + 1);
            }
        }
        if(root.right != null){
            if(root.right.value + 1 == root.value) {
                incr =  Math.max(incr, right[0] + 1);
            }
            if(root.right.value - 1 == root.value){
                decr =  Math.max(decr, right[1] + 1);
            }
        }
        // 经过当前节点的最长连续路径（可能从左子树递增到当前节点再递减到右子树，或反之）
        maxLen = Math.max(maxLen, incr + decr - 1);  // 经过当前节点的最长路径：inc + dec - 1
        return new int[]{incr, decr};
    }
}
