package com.leetcode.tree;

import com.template.TreeNode;

public class Test28_Trim_Tree {
    public static void main(String[] args) {
        /**
         * [3,0,4,null,2,null,null,1], low = 1, high = 3
         */
        TreeNode root1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(0);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(1);
        root1.setLeft(node2);
        root1.setRight(node3);
        node2.setRight(node4);
        node4.setLeft(node5);

        TreeNode root = trimBST(root1, 1, 3);
        System.out.println(root);
    }

    static public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) return null;
        if(root.value < low){
            return trimBST(root.right,  low,  high);
        }
        if(root.value > high){
            return trimBST(root.left,  low,  high);
        }
        if(root.value >= low && root.value <= high){
            root.left =  trimBST(root.left,  low,  high);
            root.right =  trimBST(root.right,  low,  high);
        }

        return root;
    }

    static public TreeNode trimBST1(TreeNode root, int low, int high) {
        // 找到修剪之后的二叉搜索树的头节点 root
        if(root == null){
            return null;
        }
        while(root != null && (root.value > high || root.value < low)){
            if(root.value > high){
                root = root.left;
            }
            else{
                root = root.right;
            }
        }
        TreeNode cur = root;
        // 修剪 root 的左子树，将 < low 的节点删除
        while(cur != null){
            while(cur.left != null && cur.left.value < low){
                cur.left = cur.left.right;
            }
            cur = cur.left;
        }
        // 修剪 root 的右子树，将 > high 的节点删除
        cur = root;
        while(cur != null){
            while(cur.right != null && cur.right.value > high){
                cur.right = cur.right.left;
            }
            cur = cur.right;
        }
        return root;
    }
}
