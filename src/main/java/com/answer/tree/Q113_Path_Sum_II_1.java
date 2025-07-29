package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q113_Path_Sum_II_1 {
    /**
     * 回溯算法 深度优先遍历 先序遍历
     * 3 种写法，实际上都是一样的，区别仅仅在于一些细节上的处理，它们是：在当前结点非空的前提下，是否先减去当前结点的值，是否先把当前结点的值加入 path ，再判断递归终止条件，再递归调用。
     * 参考代码 1：
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        // Java 文档中 Stack 类建议使用 Deque 代替 Stack，注意：只使用栈的相关接口
        Deque<Integer> path = new ArrayDeque<>();
        dfs(root, sum, path, res);
        return res;
    }

    private void dfs(TreeNode node, int sum, Deque<Integer> path, List<List<Integer>> res) {
        if (node == null) {  // 递归终止条件 1
            return;
        }
        // 递归终止条件 2
        if (node.value == sum && node.left == null && node.right == null) {
            path.addLast(node.value);// 当前结点的值还没添加到列表中，所以要先添加，然后再移除
            res.add(new ArrayList<>(path));
            path.removeLast();
            return;
        }

        path.addLast(node.value);
        dfs(node.left, sum - node.value, path, res);
        // 进入左右分支的 path 是一样的，这里不用写下面两行，因为一定会调用到 path.removeLast();
        // path.removeLast();
        // path.addLast(node.val);
        dfs(node.right, sum - node.value, path, res);
        path.removeLast();
    }
    /**
     * 参考代码 2
     * 说明：由于先减去了当前非空结点的值，递归终止条件写 sum == 0。
     */

    public List<List<Integer>> pathSum1(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        // Java 文档中 Stack 类建议使用 Deque 代替 Stack，注意：只使用栈的相关接口
        Deque<Integer> path = new ArrayDeque<>();
        pathSum1(root, sum, path, res);
        return res;
    }

    public void pathSum1(TreeNode node, int sum, Deque<Integer> path, List<List<Integer>> res) {
        if (node == null) {   // 递归终止条件 1：遇到空结点不再递归调用
            return;
        }
        // 沿途结点必须选择，这个时候要做两件事：1、sum 减去这个结点的值；2、添加到 path 里
        sum -= node.value;
        path.addLast(node.value);
        // 递归终止条件 2：遇到叶子结点，sum 恰好为 0，说明从根结点到叶子结点的路径是一个符合要求的解
        if (sum == 0 && node.left == null && node.right == null) {
            res.add(new ArrayList<>(path));   // path 全局只有一份，必须做拷贝
            path.removeLast();      // 注意：这里 return 之前必须重置
            return;
        }

        pathSum1(node.left, sum, path, res);
        pathSum1(node.right, sum, path, res);
        // 递归完成以后，必须重置变量
        path.removeLast();
    }
    /**
     * 参考代码 3：
     * 说明：在对左右结点递归调用之前，先判断结点是否为空，左右结点非空才继续调用；
     *      对比参考代码 1 和参考代码 2：由于有可能一个结点只有左结点或者只有右结点，因此递归调用完成以后，一定要 path.removeLast();。
     */
    public List<List<Integer>> pathSum2(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Deque<Integer> path = new ArrayDeque<>();
        dfs2(root, sum, path, res);
        return res;
    }

    public void dfs2(TreeNode node, int sum, Deque<Integer> path, List<List<Integer>> res) {
        if (node == null) {
            return;
        }

        sum -= node.value;
        path.addLast(node.value);

        if (node.left == null && node.right == null && sum == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 在递归调用之前如果先判断了非空，在递归完成以后，需要重置 path
        if (node.left != null) {
            dfs2(node.left, sum, path, res);
            path.removeLast();
        }

        if (node.right != null) {
            dfs2(node.right, sum, path, res);
            path.removeLast();
        }
    }
}
