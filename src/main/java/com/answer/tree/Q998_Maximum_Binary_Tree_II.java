package com.answer.tree;

import com.template.TreeNode;

public class Q998_Maximum_Binary_Tree_II {
    /**
     * 最大二叉树 II
     * 最大树 定义：一棵树，并满足：其中每个节点的值都大于其子树中的任何其他值。 A maximum tree is a tree where every node has a value greater than any other value in its subtree.
     * 给你最大树的根节点 root 和一个整数 val 。
     * 就像 之前的问题 那样，给定的树是利用 Construct(a) 例程从列表 a（root = Construct(a)）递归地构建的：
     *  如果 a 为空，返回 null 。
     *  否则，令 a[i] 作为 a 的最大元素。创建一个值为 a[i] 的根节点 root 。
     *  root 的左子树将被构建为 Construct([a[0], a[1], ..., a[i - 1]]) 。
     *  root 的右子树将被构建为 Construct([a[i + 1], a[i + 2], ..., a[a.length - 1]]) 。
     *  返回 root 。
     * 请注意，题目没有直接给出 a ，只是给出一个根节点 root = Construct(a) 。
     * 假设 b 是 a 的副本，并在末尾附加值 val。题目数据保证 b 中的值互不相同。
     * 返回 Construct(b) 。
     */
    /**
     * 方法一：遍历右子节点
     *
     */
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        TreeNode parent = null;
        TreeNode cur = root;

        while(cur != null){
            if(val > cur.value){
                if(parent == null){
                    return new TreeNode(val, root, null);
                }
                TreeNode node = new TreeNode(val, cur, null);
                parent.right = node;
                return root;
            }else{
                parent = cur;
                cur = cur.right;
            }
        }
        parent.right = new TreeNode(val);
        return root;
    }
    /**
     * Another form
     */
    public TreeNode insertIntoMaxTree_1(TreeNode root, int val) {
        TreeNode node = new TreeNode(val);
        TreeNode cur = root, prev = null;
        while(cur != null && cur.value > val){
            prev = cur;
            cur = cur.right;
        }
        if(prev == null){
            node.left = cur;
            return node;
        }else{
            prev.right = node;
            node.left = cur;
            return root;
        }
    }
    /**
     * Recursion
     */
    public TreeNode insertIntoMaxTree_2(TreeNode root, int val) {
        if(root == null){
            return new TreeNode(val);
        }
        if(root.value < val){
            return new TreeNode(val, root, null);
        }
        root.right = insertIntoMaxTree_2(root.right, val);

        return root;
    }
}
