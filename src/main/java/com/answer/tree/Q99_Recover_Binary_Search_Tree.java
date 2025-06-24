package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q99_Recover_Binary_Search_Tree {
    /**
     * 恢复二叉搜索树
     * 给你二叉搜索树的根节点 root ，该树中的 恰好 两个节点的值被错误地交换。请在不改变其结构的情况下，恢复这棵树 。
     * You are given the root of a binary search tree (BST), where the values of exactly two nodes of the tree were swapped by mistake. Recover the tree without changing its structure.
     */
    /**
     * 先读取所有节点，再判断顺序有问题的节点，再交换
     */
    public void recoverTree(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        dfs(root, list);

        TreeNode x= null;
        TreeNode y = null;

        for(int i =0 ;i < list.size()-1; i++){
            if(list.get(i).value > list.get(i + 1).value){
                y = list.get(i + 1);
                if(x==null) { //有可能两个节点不相邻
                    x = list.get(i);//记录第一个节点
                }
            }
        }
        if(x!= null && y != null){
            int temp = x.value;
            x.value = y.value;
            y.value = temp;
        }
    }

    public void dfs(TreeNode node, List<TreeNode> list) {
        if (node== null) return;

        dfs(node.left, list);
        list.add(node);
        dfs(node.right, list);
    }
    /**
     * 另一种形式
     */
    TreeNode firstNode = null;
    TreeNode secondNode = null;
    TreeNode preNode = new TreeNode(Integer.MIN_VALUE);

    public void recoverTree1(TreeNode root) {
        in_order(root);
        int tmp = firstNode.value;
        firstNode.value = secondNode.value;
        secondNode.value = tmp;
    }

    private void in_order(TreeNode root) {
        if (root == null) return;
        in_order(root.left);
        if (firstNode == null && preNode.value > root.value) {
            firstNode = preNode;
        }
        if (firstNode != null && preNode.value > root.value) {
            secondNode = root;
        }
        preNode = root;
        in_order(root.right);
    }
    /**
     * From 睡不醒的鲤鱼
     * Morris 遍历 + 寻找逆序对。
     * Follow up: A solution using O(n) space is pretty straight-forward. Could you devise a constant O(1) space solution?
     */
    TreeNode first = null, second = null;
    public void recoverTree3(TreeNode root) {
        TreeNode last = null, cur = root;
        while (cur != null) {
            if (cur.left == null) {
                check(last, cur);
                last = cur;
                cur = cur.right;
            } else {
                TreeNode p = cur.left;
                while (p.right  != null && p.right != cur) p = p.right;

                if (p.right == null) {p.right = cur; cur = cur.left;}
                else {
                    p.right = null;
                    check(last, cur);
                    last = cur;
                    cur = cur.right;
                }
            }
        }
        if(first!= null && second != null){
            int temp = first.value;
            first.value = second.value;
            second.value = temp;
        }
    }

    void check(TreeNode last, TreeNode cur) {
        if (last != null && last.value > cur.value) {
            if (first == null) {first = last; second = cur;}
            else second = cur;
        }
    }
}
