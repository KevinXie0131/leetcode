package com.answer.tree;

import com.template.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q100_Same_Tree {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        TreeNode root2 = new TreeNode(1);
        root2.right = new TreeNode(2);
/*        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);*/
        System.out.println(isSameTree_2(root1, root2));
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null && q != null) { // ⾸先排除空节点的情况
            return false;
        } else if (p != null && q == null) {
            return false;
        } else if (p.value != q.value) { // 排除了空节点，再排除数值不相同的情况
            return false;
        }
        // 此时就是：左右节点都不为空，且数值相同的情况
        // 此时才做递归，做下⼀层的判断
        boolean left = isSameTree(p.left, q.left); // 左⼦树: 左、 右⼦树：左 （相对于求对称⼆叉树，只需改⼀下这⾥的顺序）
        boolean right = isSameTree(p.right, q.right); // 左⼦树: 右、 右⼦树：右
        return left && right; // 左⼦树: 中、 右⼦树: 中 （逻辑处理）
    }
    /**
     * 精简之后代码
     */
    public boolean isSameTree_1(TreeNode p, TreeNode q) {
        return compare(p, q);
    }
    boolean compare(TreeNode left, TreeNode right) {
        if (left == null && right != null) {
            return false;
        } else if (left != null && right == null) {
            return false;
        } else if (left == null && right == null) {
            return true;
        } else if (left.value != right.value) {
            return false;
        } else {
            return compare(left.left, right.left) && compare(left.right, right.right);
        }
    }
    /**
     * 迭代法
     */
    static public boolean isSameTree_2(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;

        Deque<TreeNode> que = new ArrayDeque<>();
        que.offer(p);
        que.offer(q);

        while (!que.isEmpty()) {
            TreeNode leftNode = que.poll();
            TreeNode rightNode = que.poll();
            if (leftNode != null && rightNode != null && leftNode.value != rightNode.value) {
                return false;
            }
            if ((leftNode == null && rightNode == null)) {
                continue;
            }
            if ((leftNode == null && rightNode != null) || (leftNode != null && rightNode == null)) {
                return false;
            }
            // 相对于求对称⼆叉树，这⾥两个树都要保持⼀样的遍历顺序
            if((leftNode.left != null && rightNode.left == null) || leftNode.left == null && rightNode.left != null){
                return false;
            }
            if(leftNode.left != null) que.offer(leftNode.left);
            if(rightNode.left != null) que.offer(rightNode.left);
            if((leftNode.right != null && rightNode.right == null) || leftNode.right == null && rightNode.right != null){
                return false;
            }
            if(leftNode.right != null) que.offer(leftNode.right);
            if(rightNode.right != null)  que.offer(rightNode.right);
        }
        return true;
    }
}
