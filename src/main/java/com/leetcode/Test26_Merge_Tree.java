package com.leetcode;

import com.template.Node;
import com.template.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Test26_Merge_Tree {

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode root1a = new TreeNode(11);
        TreeNode node2a = new TreeNode(22);
        root1.setLeft(node2);
        root1.setRight(node3);
        root1a.setLeft(node2a);
        root1a.setRight(null);

     //   TreeNode root = mergeTrees(root1, root1a);
        TreeNode root = mergeTrees1(root1, root1a);
        System.out.println(root);
    }

    public static TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        // 如果 root1 为空，则合并之后节点为 root2
        if(root1 == null){
            return root2;
        }
        // 如果 root2 为空，则合并之后节点为 root1
        if(root2 == null){
            return root1;
        }
        // 如果都存在节点，创建一个新的节点存储合并后的值
        TreeNode root = new TreeNode(root1.getValue() + root2.getValue());
        // 递归合并左子树
        root.left = mergeTrees(root1.left, root2.left);
        // 递归合并右子树
        root.right = mergeTrees(root1.right, root2.right);

        return root;
    }

    public static TreeNode mergeTrees1(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 ==null) return root1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root1);
        queue.offer(root2);
        while (!queue.isEmpty()) {
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();
            // 此时两个节点一定不为空，val相加
            node1.value = node1.value + node2.value;
            // 如果两棵树左节点都不为空，加入队列
            if (node1.left != null && node2.left != null) {
                queue.offer(node1.left);
                queue.offer(node2.left);
            }
            // 如果两棵树右节点都不为空，加入队列
            if (node1.right != null && node2.right != null) {
                queue.offer(node1.right);
                queue.offer(node2.right);
            }
            // 若node1的左节点为空，直接赋值
            if (node1.left == null && node2.left != null) {
                node1.left = node2.left;
            }
            // 若node2的左节点为空，直接赋值
            if (node1.right == null && node2.right != null) {
                node1.right = node2.right;
            }
        }
        return root1;
    }

}
