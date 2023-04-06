package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q101_Symmetric_Tree {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;

        return dfs(root.left, root.right);
    }
    /**
     * 本题遍历只能是“后序遍历”，因为我们要通过递归函数的返回值来判断两个⼦树的内侧节点
     * 和外侧节点是否相等。
     */
    public boolean dfs(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {  // if (left == NULL && right != NULL) return false;
            return false;                     // else if (left != NULL && right == NULL) return false;
        }
        if (left.value != right.value) { // 排除了空节点，再排除数值不相同的情况
            return false;
        }
        // ⽐较⼆叉树外侧是否对称：传⼊的是左节点的左孩⼦，右节点的右孩⼦。
        // ⽐较内测是否对称，传⼊左节点的右孩⼦，右节点的左孩⼦。
        // 如果左右都对称就返回true ，有⼀侧不对称就返回false 。

        // 此时就是：左右节点都不为空，且数值相同的情况
        // 此时才做递归，做下⼀层的判断
        boolean isLeft = dfs(left.left, right.right); // 左⼦树：左、 右⼦树：右
        boolean isRight = dfs(left.right, right.left); // 左⼦树：右、 右⼦树：左

        return isLeft && isRight; // 左⼦树：中、 右⼦树：中（逻辑处理）
    }

    /**
     * 使⽤队列 迭代法
     * 在迭代法中我们使⽤了队列，需要注意的是这不是层序遍历，⽽且仅仅通过⼀个容器来成对
     * 的存放我们要⽐较的元素，知道这⼀本质之后就发现，⽤队列，⽤栈，甚⾄⽤数组，都是可以的。
     */
    public boolean isSymmetric_1c(TreeNode root) {

        if (root == null) {
            return true;
        }

        Deque<TreeNode> queue = new LinkedList<>();
        queue.offerFirst(root.left); // 将左⼦树头结点加⼊队列
        queue.offerLast(root.right); // 将右⼦树头结点加⼊队列
        while (!queue.isEmpty()) { // 接下来就要判断这这两个树是否相互翻转

            TreeNode left = queue.pollFirst();
            TreeNode right = queue.pollLast();

            if (left == null && right == null) { // 左节点为空、右节点为空，此时说明是对称的
                continue;
            }
            if (left == null || right == null) { // 左右⼀个节点不为空，或者都不为空但数值不相同，返回false
                return false;
            }
            if (left.value != right.value) {
                return false;
            }
            queue.offerFirst(left.left);  // 加⼊左节点左孩⼦
            queue.offerFirst(left.right); // 加⼊右节点右孩⼦
            queue.offerLast(right.right); // 加⼊左节点右孩⼦
            queue.offerLast(right.left);  // 加⼊右节点左孩⼦
        }

        return true;
    }
}
