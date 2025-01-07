package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q235_Lowest_Common_Ancestor_of_a_Binary_Search_Tree {
    /**
     * ⼆叉搜索树的最近公共祖先 - 前序遍历
     * 给定⼀个⼆叉搜索树, 找到该树中两个指定节点的最近公共祖先
     *
     * 普通⼆叉树求最近公共祖先需要使⽤回溯，从底向上来查找，⼆叉搜索树就不⽤了，因为搜索树有序（相当于⾃带⽅向），那么只要从上向下遍历就可以了
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if ((root.left == p && root.right == q) ||
                (root.left == q && root.right == p)) {
            return root;
        }

        while ((root.value - p.value) * (root.value - q.value) > 0)
            root = p.value < root.value ? root.left : root.right;

        return root;
    }
    /**
     * 在遍历⼆叉搜索树的时候就是寻找区间[p->val, q->val]（注意这⾥是左闭又闭）
     * 那么如果 cur->val ⼤于 p->val，同时 cur->val ⼤于q->val，那么就应该向左遍历（说明⽬标区间在左⼦树上）。
     * 如果 cur->val ⼩于 p->val，同时 cur->val ⼩于 q->val，那么就应该向右遍历（⽬标区间在右⼦树）。
     */
    public TreeNode lowestCommonAncestor_0(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return null; // 可省略 不存在遇到空的情况

        if (p.value < root.value && q.value < root.value) {
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            if(left != null) {
                return left;
            }
        }
        if (p.value > root.value && q.value > root.value) {
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            if(right != null) {
                return right;
            }
        }
        return root;
    }
    /**
     * 另一种形式的递归
     * 当我们从上向下去递归遍历，第一次遇到 cur节点是数值在[q, p]区间中，那么cur就是 q和p的最近公共祖先。
     */
    public TreeNode lowestCommonAncestor_1(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode left = null;
        if (p.value < root.value && q.value < root.value) { // 搜索整个树
            left = lowestCommonAncestor_1(root.left, p, q);
        }
        TreeNode right = null;
        if (p.value > root.value && q.value > root.value) {
            right = lowestCommonAncestor_1(root.right, p, q);
        }
        if(left!= null){
            return left;
        } else if(right!= null) {
            return right;
        } else{
            return root;
        }
    }
    /**
     * 精简后代码
     */
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null; // 可省略 不存在遇到空的情况

        if(root.value > p.value && root.value > q.value){
            return lowestCommonAncestor(root.left, p,q);
        }else if(root.value < p.value && root.value < q.value){
            return lowestCommonAncestor(root.right, p,q);
        }else{
            return root;
        }
    }
    /**
     * 迭代法
     */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (p.value < root.value && q.value < root.value) {
                root = root.left;
            } else if (p.value > root.value && q.value > root.value) {
                root = root.right;
            } else {
                return root; // 剩下的情况，就是cur节点在区间中，那么cur就是最近公共祖先了，直接返回cur
            }
        }
        return root;
    }
    /**
     * Q236题解  没有运用搜索树
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        if (root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p,  q);
        TreeNode right = lowestCommonAncestor(root.right, p,  q);

        if (left == null && right == null) {
            return null;
        } else if(left != null && right == null) {
            return left;
        } else if(right != null && left == null) {
            return right;
        } else if (left != null && right != null) {
            return root;
        } else {
            return null;
        }
    }
    /**
     * Q236题解 没有运用搜索树
     */
    Map<Integer, TreeNode> parent = new HashMap<Integer, TreeNode>();
    Set<Integer> visited = new HashSet<Integer>();

    public void dfs(TreeNode root){
        if(root == null){
            return;
        }
        if(root.left!=null){
            parent.put(root.left.value, root);
            dfs(root.left);
        }
        if(root.right!=null){
            parent.put(root.right.value, root);
            dfs(root.right);
        }

    }
    public TreeNode lowestCommonAncestor4(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root);
        while(p != null){
            visited.add(p.value);
            p= parent.get(p.value);
        }

        while(q != null){
            if(visited.contains(q.value)){
                return q;
            }
            q= parent.get(q.value);
        }

        return null;
    }
}
