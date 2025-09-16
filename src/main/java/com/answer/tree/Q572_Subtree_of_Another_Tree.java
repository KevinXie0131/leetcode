package com.answer.tree;

import com.template.TreeNode;
import java.util.*;

public class Q572_Subtree_of_Another_Tree {
    /**
     * 另一棵树的子树
     * 给你两棵二叉树 root 和 subRoot 。检验 root 中是否包含和 subRoot 具有相同结构和节点值的子树。如果存在，返回 true ；否则，返回 false 。
     * 二叉树 tree 的一棵子树包括 tree 的某个节点和这个节点的所有后代节点。tree 也可以看做它自身的一棵子树。
     * Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with the same structure and node values of subRoot and false otherwise.
     * A subtree of a binary tree tree is a tree that consists of a node in tree and all of this node's descendants. The tree tree could also be considered as a subtree of itself.
     */
    /**
     * 深度优先搜索暴力匹配
     * 判断 t 是否为 s 的子树的三个条件是或的关系，即：
     *   当前两棵树相等；
     *   或者，t 是 s 的左子树；
     *   或者，t 是 s 的右子树。
     */
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        }
        if (root == null || subRoot == null) {
            return false;
        }
        boolean isSame = isSameTree(root, subRoot);
        boolean isLeftSame = isSubtree(root.left, subRoot);
        boolean isRightSame = isSubtree(root.right, subRoot);

        return isSame || isLeftSame || isRightSame;
    }
    // refer to Q100_Same_Tree
    // 判断两个树是否相等的三个条件是与的关系，即：
    //   当前两个树的根节点值相等；
    //   并且，s 的左子树和 t 的左子树相等；
    //   并且，s 的右子树和 t 的右子树相等。
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) { // 两树均空自然相等
            return true;
        } else if (p == null || q == null) { // 一空一非空，自然不等
            return false;
        } else if (p.value != q.value) {
            return false;
        }
        boolean left = isSameTree(p.left, q.left); // 递归判断
        boolean right = isSameTree(p.right, q.right);
        return left && right;
    }
    /**
     * another form
     */
    public boolean isSametree1(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(p == null || q == null) return false;
        return p.value == q.value && isSametree1(p.left, q.left) && isSametree1(p.right,q.right);
    }
    /**
     * 使用序列化进行唯一表示
     */
    Set<String> seen = new HashSet<>();

    public boolean isSubtree1(TreeNode root, TreeNode subRoot) {
        dfs(root, true);

        String res = dfs(root, false);
        if(seen.contains(res)){
            return true;
        } else {
            return false;
        }
    }

    public String dfs(TreeNode node, boolean isIncluded) {
        if (node == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(node.value);
        sb.append("(");
        sb.append(dfs(node.left, isIncluded));
        sb.append(")(");
        sb.append(dfs(node.right, isIncluded));
        sb.append(")");
        String serial = sb.toString();
        if(isIncluded) {
            seen.add(serial);
        }
        return serial;
    }
}
