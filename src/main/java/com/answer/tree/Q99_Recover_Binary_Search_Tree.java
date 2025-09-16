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
     * 假设有一个递增序列 a=[1,2,3,4,5,6,7]。如果我们交换两个不相邻的数字，例如 2 和 6，原序列变成了 a=[1,6,3,4,5,2,7]，
     */
    public void recoverTree(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        dfs(root, list);

        TreeNode x = null;
        TreeNode y = null;
        // 第一个节点，是第一个按照中序遍历时候前一个节点大于后一个节点，我们选取前一个节点
        // 第二个节点，是在第一个节点找到之后，后面出现前一个节点大于后一个节点，我们选择后一个节点
        for(int i = 0 ; i < list.size() - 1; i++){
            if(list.get(i).value > list.get(i + 1).value){
                y = list.get(i + 1);//有可能两个节点相邻
                if(x == null) {
                    x = list.get(i);//记录第一个节点
                } else {
                    break;
                }
                /*else {
                    y = list.get(i + 1); // not correct
                }*/
            }
        }
        if(x != null && y != null){
            int temp = x.value;
            x.value = y.value;
            y.value = temp;
        }
    }

    public void dfs(TreeNode node, List<TreeNode> list) {
        if (node== null){
            return;
        }
        dfs(node.left, list);
        list.add(node);
        dfs(node.right, list);
    }
    /**
     * 另一种形式
     */
    TreeNode firstNode = null;
    TreeNode secondNode = null;
    TreeNode preNode = new TreeNode(Integer.MIN_VALUE); // 增加一个辅助的 pre 指针，记录 上一个 节点的值。

    public void recoverTree1(TreeNode root) {
        in_order(root);
        int tmp = firstNode.value;
        firstNode.value = secondNode.value;
        secondNode.value = tmp;
    }

    private void in_order(TreeNode root) {
        if (root == null) {
            return;
        }
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
     * 迭代 隐式中序遍历
     */
    public void recoverTree2(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        TreeNode pre = null, first = null, second = null;

        while(cur != null || !stack.isEmpty()){
            while(cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode node = stack.pop();

            if(pre != null && pre.value > node.value){
                second = node;
                if(first == null){
                    first = pre;
                } else {
                    break;
                }
            }
            pre = node; //更新pre节点

            cur = node.right;
        }
        //交换两个节点的值
        if(first != null && second != null){
            int temp = first.value;
            first.value = second.value;
            second.value = temp;
        }
    }
}
