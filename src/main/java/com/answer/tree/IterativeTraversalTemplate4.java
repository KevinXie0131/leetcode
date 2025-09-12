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
}
