package com.answer.tree;

import com.template.TreeNode;

public class Q687_Longest_Univalue_Path {
    /**
     * 最长同值路径
     * 给定一个二叉树的 root ，返回 最长的路径的长度 ，这个路径中的 每个节点具有相同值 。 这条路径可以经过也可以不经过根节点。
     * 两个节点之间的路径长度 由它们之间的边数表示。
     * Given the root of a binary tree, return the length of the longest path, where each node in the path has the same value. This path may or may not pass through the root.
     * The length of the path between two nodes is represented by the number of edges between them.
     */
    public static void main(String[] args) {
        //  [1,4,5,4,4,null,5]
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.right = node4;
        node4.right = node5;
   /*     TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(1);
        root.left = node1;*/
        System.out.println(longestUnivaluePath0(root));
    }
    /**
     * 路径长度是边数，不是节点数。
     * 可以从树中任意节点开始，不必经过根。
     * 返回的是最长同值路径的边数，即节点数-1。
     */
    static private int maxLen = 0;

    static public int longestUnivaluePath0(TreeNode root) {
        dfs0(root);
        return maxLen;
    }
    // 返回以当前节点为起点的、向下的同值路径最长边数
    static private int dfs0(TreeNode node) {
        if (node == null) return 0;
        int left = dfs0(node.left); // 递归左右子树
        int right = dfs0(node.right);

        int leftPath = 0, rightPath = 0;
        if (node.left != null && node.left.value == node.value) {  // 如果左子节点和当前节点值相等，可以向左延伸
            leftPath = left + 1;
        }
        if (node.right != null && node.right.value == node.value) {  // 如果右子节点和当前节点值相等，可以向右延伸
            rightPath = right + 1;
        }
        maxLen = Math.max(maxLen, leftPath + rightPath);// 更新全局最长路径（可能穿过当前节点，左+右）
        return Math.max(leftPath, rightPath);// 返回当前节点可向父节点延续的最长同值路径
    }
    /**
     * Recursion 同上
     */
    static int res = 0;

    public static int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return res;
    }

    public static int dfs(TreeNode node){
        if(node == null){
            return 0;
        }
        int left = 0;
        int right = 0;
        if(node.left != null){
            if(node.value == node.left.value){
                left = dfs(node.left) + 1;
            } else{
                left = dfs(node.left);
            }
        }
        if(node.right != null){
            if(node.value == node.right.value){
                right = dfs(node.right) + 1;
            } else{
                right = dfs(node.right);
            }
        }
        res = Math.max(res, left + right);
        return Math.max(left, right);
    }
    /**
     * Recursion
     */
    int ans = 0;

    public int longestUnivaluePath_2(TreeNode root) {
        if (root == null) return 0;
        dfs_2(root, Integer.MAX_VALUE);
        return ans;
    }

    int dfs_2(TreeNode root, int val) {
        if (root == null) {
            return 0;
        }
        int L = dfs_2(root.left, root.value);
        int R = dfs_2(root.right, root.value);
        ans = Math.max(ans, L + R);
        return root.value != val ? 0 : Math.max(L, R) + 1;
    }
}
