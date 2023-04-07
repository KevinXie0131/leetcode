package com.answer.tree;

import com.template.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Q110_Balanced_Binary_Tree {
    /**
     * ⼀棵⾼度平衡⼆叉树定义为：⼀个⼆叉树每个节点的左右两个⼦树的⾼度差的绝对值不超过1
     *
     * ⼆叉树节点的深度：指从根节点到该节点的最长简单路径边的条数。
     * ⼆叉树节点的⾼度：指从该节点到叶⼦节点的最长简单路径边的条数
     * 但leetcode中强调的深度和⾼度很明显是按照节点来计算的
     *
     * 关于根节点的深度究竟是1 还是 0，不同的地⽅有不⼀样的标准，leetcode的题⽬中都是以节
     * 点为⼀度，即根节点深度是1。但维基百科上定义⽤边为⼀度，即根节点的深度是0，我们暂时以leetcode为准
     *
     * 求深度可以从上到下去查 所以需要前序遍历（中左右），⽽⾼度只能从下到上去查，所以只能后序遍历（左右中）
     * 求的是⼆叉树的最⼤深度，也⽤的是后序遍历? 那是因为代码的逻辑其实是求的根节点的⾼度，⽽根节点的⾼度就是这颗树的最⼤深度，所以才可以使⽤后序遍历
     */
    public boolean isBalanced(TreeNode root) {
        return getHeight(root) != -1 ? true: false; // -1 表示已经不是平衡⼆叉树了，否则返回值是以该节点为根节点树的⾼度
    }
    /**
     * 如果当前传⼊节点为根节点的⼆叉树已经不是⼆叉平衡树了，还返回⾼度的话就没有意义了。
     * 所以如果已经不是⼆叉平衡树了，可以返回-1 来标记已经不符合平衡树的规则了
     */
    int getHeight(TreeNode node) {
        if(node == null) {
            return 0;
        }
        int left = getHeight(node.left); // 左
        if (left == -1) {
            return -1;
        }
        int right = getHeight(node.right); // 右
        if (right == -1) {
            return -1;
        }

        if (Math.abs(left - right) > 1) { // 中
            return -1;
        } else {
            return Math.max(left, right) + 1; // 以当前节点为根节点的最⼤⾼度
        }
    }
    /**
     * 代码精简
     */
    public boolean isBalanced1(TreeNode root) {
        return getHeight1(root) != -1 ? true: false;
    }

    int getHeight1(TreeNode node) {
        if(node == null) {
            return 0;
        }
        int left = getHeight(node.left);
        int right = getHeight(node.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        } else {
            return Math.max(left, right) + 1;
        }
    }
}
