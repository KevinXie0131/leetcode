package com.answer.tree;

import com.template.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Q100_Same_Tree {

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
}
