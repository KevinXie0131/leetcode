package com.answer.tree;

import com.template.TreeNode;

import java.util.ArrayDeque;
import java.util.*;

public class Q938_Range_Sum_of_BST {
    /**
     * 二叉搜索树的范围和
     * 给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。
     * Given the root node of a binary search tree and two integers low and high, return the sum of values of all nodes with a value in the inclusive range [low, high].
     */
    public static void main(String[] args) {
        // [10,5,15,3,7,13,18,1,null,6]
        TreeNode root = new TreeNode(10);
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(15);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(7);
        TreeNode node5 = new TreeNode(13);
        TreeNode node6 = new TreeNode(18);
        TreeNode node7 = new TreeNode(1);
        TreeNode node8 = new TreeNode(6);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        node3.left = node7;
        node4.left = node8;

        System.out.println(rangeSumBST_0a( root, 6, 10));
    }
    /**
     * Recursive 递归
     */
    public static int rangeSumBST(TreeNode root, int low, int high) {
        if(root == null){
            return 0;
        }
        int left = rangeSumBST(root.left, low, high);
        int right = rangeSumBST(root.right, low, high);

        if(root.value >= low && root.value <= high){
           return root.value + left + right;
        } else {
            return left + right;
        }
    }
    /**
     * Postorder Recursive / Improved - fastest 递归
     */
    public static int rangeSumBST_0(TreeNode root, int low, int high) {
        if(root == null){
            return 0;
        }
        int left = 0;
        if(root.value >= low){ // 考虑BST
            left = rangeSumBST_0(root.left, low, high);
        }
        int right = 0;
        if(root.value <= high) { // 考虑BST
            right = rangeSumBST_0(root.right, low, high);
        }
        if(root.value >= low && root.value <= high){
            return root.value + left + right;
        } else {
            return left + right;
        }
    }
    /**
     * Inorder Recursive / Improved 中序递归
     */
    static int result = 0;
    public static int rangeSumBST_0a(TreeNode root, int low, int high) {
        dfs(root, low, high);
        return result;
    }
    public static void dfs(TreeNode node, int low, int high){
        if(node == null){
            return;
        }
        dfs(node.left, low, high);
        if(node.value >= low && node.value <= high){
            result += node.value;
        }
        dfs(node.right, low, high);
    }
    /**
     * Inorder Iterative 中序迭代
     */
    public static int rangeSumBST_1(TreeNode root, int low, int high) {
        int sum = 0;
        Deque<TreeNode> stack = new ArrayDeque<>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            TreeNode cur = stack.pop();
            if(cur.value >= low && cur.value <= high){
                sum += cur.value;
            } else if(cur.value > high) { // since it is BST, it can jump out at this point.
                break;
            }
            root = cur.right;
        }
        return sum;
    }
    /**
     * Level Order Iterative 层序迭代
     */
    public static int rangeSumBST_2(TreeNode root, int low, int high) {
        int sum = 0;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(node.value > high){
                if(node.left != null) queue.offer(node.left);
            } else if (node.value < low){
                if(node.right != null) queue.offer(node.right);
            } else {
                sum += node.value;
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }
        }
        return sum;
    }
    /**
     * Level Order Iterative / Improved 层序迭代 / BFS
     */
    public static int rangeSumBST_3(TreeNode root, int low, int high) {
        int sum = 0;
        Deque<TreeNode> queue = new LinkedList<>(); // ArrayDeque cannot have null, but LinkedList can
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(node == null){
                continue;
            }
            if(node.value > high){
                queue.offer(node.left);
            } else if (node.value < low){
                queue.offer(node.right);
            } else {
                sum += node.value;
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        return sum;
    }
    /**
     * Official answer
     */
    public static int rangeSumBST_5(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }
        if (root.value < L) {
            return rangeSumBST_5(root.right, L, R);
        }
        if (root.value > R) {
            return rangeSumBST_5(root.left, L, R);
        }
        return root.value + rangeSumBST_5(root.left, L, R) + rangeSumBST_5(root.right, L, R);
    }
}
