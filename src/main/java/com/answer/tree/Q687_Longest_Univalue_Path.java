package com.answer.tree;

import com.template.TreeNode;

public class Q687_Longest_Univalue_Path {
    public static void main(String[] args) {
        //  [1,4,5,4,4,null,5]
      /*  TreeNode root = new TreeNode(1);
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
        node4.right = node5;*/
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(1);
        root.left = node1;
        System.out.println(longestUnivaluePath(root));

    }

    /**
     * Recursion
     */
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
    static int res = 0;
    public static int longestUnivaluePath_1(TreeNode root) {
        dfs_1(root);
        return res;
    }

    public static int dfs_1(TreeNode root){
        if (root == null) {
            return 0;
        }
        int left = dfs_1(root.left), right = dfs_1(root.right);
        int left1 = 0, right1 = 0;
        if (root.left != null && root.left.value == root.value) {
            left1 = left + 1;
        }
        if (root.right != null && root.right.value == root.value) {
            right1 = right + 1;
        }
        res = Math.max(res, left1 + right1);
        return Math.max(left1, right1);
    }
    /**
     *
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
