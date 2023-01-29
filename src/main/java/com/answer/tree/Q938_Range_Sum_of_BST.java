package com.answer.tree;

import com.template.TreeNode;

import java.util.ArrayDeque;
import java.util.*;

public class Q938_Range_Sum_of_BST {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(15);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(7);
        TreeNode node5 = new TreeNode(18);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.right = node5;

        System.out.println(rangeSumBST_0( root, 7, 15));
    }

    /**
     * Recursive
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
     * Recursive / Improved - fastest
     */
    public static int rangeSumBST_0(TreeNode root, int low, int high) {
        if(root == null){
            return 0;
        }
        int left = 0;
        if(root.value >= low){
            left = rangeSumBST(root.left, low, high);
        }
        int right = 0;
        if(root.value <= high) {
            right = rangeSumBST(root.right, low, high);
        }
        if(root.value >= low && root.value <= high){
            return root.value + left + right;
        } else {
            return left + right;
        }
    }
    /**
     * Inorder Iterative
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
     * Level Order Iterative
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
     * Level Order Iterative / Improved
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
}
