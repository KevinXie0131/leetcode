package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q530_Minimum_Absolute_Difference_in_BST {
    /**
     * ⼆叉搜索树的最⼩绝对差
     * 给你⼀棵所有节点为⾮负值的⼆叉搜索树，请你计算树中任意两节点的差的绝对值的最⼩值
     *
     * 注意是⼆叉搜索树，⼆叉搜索树可是有序的, 遇到在⼆叉搜索树上求什么最值啊，差值之类的，就把它想成在⼀个有序数组上求最值，求差值，这样就简单多了
     */
    /**
     * 递归
     */
    int result = Integer.MAX_VALUE;
    TreeNode pre = null;

    public int getMinimumDifference(TreeNode root) {
        find(root);
        return result;
    }
    private void find(TreeNode root) {

        if (root == null) return;

        find(root.left);

        if (pre != null) {
            result = Math.min(result, root.value - pre.value);
        }
        pre = root;

        find(root.right);

    }
    /**
     * 递归
     * 最直观的想法，就是把⼆叉搜索树转换成有序数组，然后遍历⼀遍数组，就统计出来最⼩差值了
     */
    List<Integer> list = new ArrayList<>();

    public int getMinimumDifference1(TreeNode root) {
        double result = Double.MAX_VALUE;
        inOrder1(root);

        for (int i = 0; i < list.size()-1; i++) { // 统计有序数组的最⼩差值
            result = Math.min(list.get(i+1) - list.get(i), result);
        }

        return (int)result;
    }
    public void inOrder1 (TreeNode root) {
        if (root == null) return;

        inOrder1 (root.left);
        list.add(root.value); // 将⼆叉搜索树转换为有序数组
        inOrder1 (root.right);
    }
    /**
     * 其实在⼆叉搜素树中序遍历的过程中，我们就可以直接计算了。
     * 需要⽤⼀个pre节点记录⼀下cur节点的前⼀个节点
     */
    double result0 = Double.MAX_VALUE;
    int pre0 = -1;
    public int getMinimumDifference2(TreeNode root) {

        inOrder2(root);

        return (int)result;
    }

    public void inOrder2 (TreeNode root) {
        if (root == null) return;

        inOrder2 (root.left); // 左

        if (pre0 < 0) {      // 中
            pre0 = root.value;
        } else {
            result = Math.min(root.value - pre0, result);
            pre0 = root.value; // 记录前⼀个
        }

        inOrder2 (root.right); // 右
    }
    /**
     * 迭代
     */
    double result1 = Double.MAX_VALUE;
    int pre1 = -1;
    Deque<TreeNode> stack = new ArrayDeque<>();

    public int getMinimumDifference3(TreeNode root) {

        while (!stack.isEmpty() || root != null) {
            while(root != null){ // 指针来访问节点，访问到最底层
                stack.push(root); // 将访问的节点放进栈
                root= root.left; // 左
            }

            TreeNode cur = stack.pop();

            if (pre1 < 0){
                pre1 = cur.value; // 中
            } else {
                result1 = Math.min(cur.value-pre1, result);
                pre1 = cur.value;
            }

            root= cur.right; // 右

        }
        return (int)result1;
    }
}
