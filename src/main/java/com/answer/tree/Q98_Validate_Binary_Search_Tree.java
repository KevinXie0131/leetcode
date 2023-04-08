package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q98_Validate_Binary_Search_Tree {
    /**
     * 验证⼆叉搜索树
     *
     * 要知道中序遍历下，输出的⼆叉搜索树节点的数值是有序序列
     * 有了这个特性，验证⼆叉搜索树，就相当于变成了判断⼀个序列是不是递增的了
     */
    /**
     * 递归法
     * 递归中序遍历将⼆叉搜索树转变成⼀个数组, 然后只要⽐较⼀下，这个数组是否是有序的，注意⼆叉搜索树中不能有重复元素
     */
    List<Integer> list = new ArrayList<>();

    public boolean isValidBST_0(TreeNode root) {
        traversal(root);
        for (int i = 1; i < list.size(); i++) {
            // 注意要⼩于等于，搜索树⾥不能有相同元素
            if (list.get(i) <= list.get(i - 1)) return false;
        }
        return true;
    }
    void traversal(TreeNode root) {
        if (root == null) return;

        traversal(root.left);
        list.add(root.value); // 将⼆叉搜索树转换为有序数组
        traversal(root.right);
    }
    /**
     *
     */
    public boolean isValidBST(TreeNode root) {
        double result = -Double.MAX_VALUE;
        Deque<TreeNode> stack = new ArrayDeque<>();

        while (!stack.isEmpty() || root != null) {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }

            TreeNode cur = stack.pop();
            if(cur.value <= result ) {
                return false;
            } else {
                result = cur.value;
            }
            root = cur.right;
        }
        return true;
    }
    /**
     *
     */
    public boolean isValidBST1(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        long pre = Long.MIN_VALUE;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            TreeNode cur = stack.pop();
            if (cur.value > pre) {
                pre = cur.value;
            } else {
                return false;
            }
            root = cur.right;

        }
        return true;
    }
    /**
     *
     */
    TreeNode pre = null;

    public boolean isValidBST2(TreeNode root) {

        if (root == null) return true;

        boolean left = isValidBST(root.left);

        if (pre != null && pre.value >= root.value) {
            return false;
        }
        pre = root;

        boolean right = isValidBST(root.right);

        return left && right;
    }
    /**
     *
     */
    public boolean isValidBST3(TreeNode root) {
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean dfs(TreeNode root,  long lower, long upper){
        if(root==null){
            return true;
        }
        if (root.value >= upper || root.value <= lower){
            return false;
        }

        boolean left = dfs(root.left, lower, root.value);
        boolean right = dfs(root.right, root.value, upper);
        return left && right;

    }
}