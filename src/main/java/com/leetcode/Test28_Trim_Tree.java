package com.leetcode;

import com.template.TreeNode;

public class Test28_Trim_Tree {
    public static void main(String[] args) {

    }

    public TreeNode trimBST(TreeNode root, int low, int high) {
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

    public TreeNode trimBST1(TreeNode root, int low, int high) {
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
