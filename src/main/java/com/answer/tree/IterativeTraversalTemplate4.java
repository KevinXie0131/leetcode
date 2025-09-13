package com.answer.tree;

import com.template.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class IterativeTraversalTemplate4 {
    /**
     * ⼆叉搜索树中的搜索
     * 递归
     */
    public TreeNode searchBST(TreeNode root, int val) {
        // 确定终止条件
        if (root == null || root.value == val) { // 如果root为空，或者找到这个数值了，就返回root节点
            return root;
        }
        // 确定单层递归的逻辑
        else if (root.value < val) { // 因为二叉搜索树的节点是有序的，所以可以有方向的去搜索
            return searchBST(root.right, val);
        }
        else if (root.value > val) {
            return searchBST(root.left, val); // 不要忘了 递归函数还有返回值
        }
        return null;
    }
    /**
     * 迭代
     */
    public TreeNode searchBST1(TreeNode root, int val) {
        while (root != null) {
            if (root.value == val) {
                return root;
            } else if (root.value > val) {
                root = root.left;
            } else if (root.value < val) {
                root = root.right;
            }
        }
        return null;
    }
    /**
     * 相同的树
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true; // 两个节点均为空
        if (p == null || q == null) return false; // 一个节点为空 一个节点不为空
        return p.value == q.value && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);  // 两个节点均不为空
    }
    /**
     * 二叉树的最大深度
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0; // 确定终⽌条件：如果为空节点的话，就返回0，表⽰⾼度为0
        }
        // 确定单层递归的逻辑：先求它的左⼦树的深度，再求的右⼦树的深度，最后取左右深度最⼤的数值 再+1
        //                  （加1是因为算上当前中间节点）就是⽬前节点为根节点的树的深度
        int left = maxDepth(root.left);    // 左
        int right = maxDepth(root.right);  // 右
        return Math.max(left, right) + 1;  // 中 用后序遍历（左右中）来计算树的高度 / 二叉树的最大深度为左右子树的最大深度加1得到。
    }
}
