package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q513_Find_Bottom_Left_Tree_Value {
    /**
     * 找树左下角的值
     * 给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
     * 假设二叉树中至少有一个节点。
     * Given the root of a binary tree, return the leftmost value in the last row of the tree.
     */
    /**
     * 本地要找出树的最后⼀⾏找到最左边的值。此时⼤家应该想起⽤层序遍历是⾮常简单的了，反⽽⽤递归的话会⽐较难⼀点
     *
     * 如果需要遍历整颗树，递归函数就不能有返回值。如果需要遍历某⼀条固定路线，递归函数就⼀定要有返回值
     */
    int result, maxHeight;

    public int findBottomLeftValue(TreeNode root) {
        result = 0; // 全局变量 最⼤深度最左节点的数值
        maxHeight = -1; // 全局变量 记录最⼤深度
        dfs(root, 0);
        return result;
    }
    /**
     * 递归
     * ⾸先要是最后⼀⾏，然后是最左边的值, 所以要找深度最⼤的叶⼦节点
     * 可以使⽤前序遍历，这样才先优先左边搜索，然后记录深度最⼤的叶⼦节点，此时就是树的最后⼀⾏最左边的值
     */
    private void dfs(TreeNode node, int level) { // 是要遍历整个树找到最深的叶⼦节点，需要遍历整颗树，所以递归函数没有返回值
        if (node == null) {
            return;
        }
        if (level > maxHeight) { // 先序遍历
            maxHeight = level;   // 更新最⼤深度
            result = node.value; // 最⼤深度最左⾯的数值
        }
        dfs(node.left, level + 1);  // 隐藏着回溯
        dfs(node.right, level + 1); // 隐藏着回溯
        /**
         * 回溯
         * if (root.left != null) { // 左
         *      depth++; // 深度加⼀
         *      traversal(root.left, depth);
         *      depth--; // 回溯，深度减⼀
         * }
         * if (root.right != null) { // 右
         *      depth++; // 深度加⼀
         *      traversal(root.right, depth);
         *      depth--; // 回溯，深度减⼀
         * }
         */
    }
    /**
     * 先序：根左右；中序：左根右；后序：左右根。不管哪种顺序肯定是先访问到左子树，再访问右子树。所以只用管深度就行
     * 保证优先左边搜索，然后记录深度最大的叶子节点，此时就是树的最后一行最左边的值。
     * 中序遍历
     */
    private void dfs1(TreeNode node, int level) {
        if (node == null) {
            return;
        }
        dfs1(node.left, level + 1);
        if (level > maxHeight) {
            maxHeight = level;
            result = node.value;
        }
        dfs1(node.right, level + 1);
    }
    /**
     * 后序遍历
     */
    private void dfs2(TreeNode node, int level) {
        if (node == null){
            return;
        }
        dfs2(node.left, level + 1);
        dfs2(node.right, level + 1);
        if (level > maxHeight) {
            maxHeight = level;
            result = node.value;
        }
    }
    /**
     * 迭代法 / 前序遍历
     */
    public int findBottomLeftValue1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int value = 0;

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode tmpNode = queue.poll();
            // 先右后左
            if (tmpNode.right != null) { // 右在左前面
                queue.offer(tmpNode.right);
            }
            if (tmpNode.left != null) {
                queue.offer(tmpNode.left);
            }
            value = tmpNode.value;
        }
        return value;
    }
    /**
     * 层序遍历 (本题使用层序遍历再合适不过了，比递归要好理解得多！只需要记录最后一行第一个节点的数值就可以了。)
     */
    public int findBottomLeftValue_2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int value = 0;

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
       //     value = queue.peek().value
            for(int i = 0; i < size; i++){
                TreeNode tmpNode = queue.poll();
                if (i == 0) {  // 记录最后⼀⾏第⼀个元素
                    value = tmpNode.value;
                }
                if (tmpNode.left != null) {
                    queue.offer(tmpNode.left);
                }
                if (tmpNode.right != null) {
                    queue.offer(tmpNode.right);
                }
            }
        }
        return value;
    }
}
