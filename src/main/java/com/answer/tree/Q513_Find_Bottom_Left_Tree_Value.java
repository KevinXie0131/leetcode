package com.answer.tree;

import com.template.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q513_Find_Bottom_Left_Tree_Value {
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
        if (node == null) return;
        if (level > maxHeight) {
            maxHeight = level;   // 更新最⼤深度
            result = node.value; // 最⼤深度最左⾯的数值
        }
        dfs(node.left, level + 1);  // 隐藏着回溯
        dfs(node.right, level + 1); // 隐藏着回溯
        /**
         * 回溯
         * if (root->left) { // 左
         *      leftLen++; // 深度加⼀
         *      traversal(root->left, leftLen);
         *      leftLen--; // 回溯，深度减⼀
         * }
         * if (root->right) { // 右
         *      leftLen++; // 深度加⼀
         *      traversal(root->right, leftLen);
         *      leftLen--; // 回溯，深度减⼀
         * }
         */
    }
    /**
     *
     */
    public int findBottomLeftValue1(TreeNode root) {
        if (root == null) return 0;
        int value = 0;

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode tmpNode = queue.poll();

            if (tmpNode.right != null) {
                queue.offer(tmpNode.right);
            }
            if (tmpNode.left != null) {
                queue.offer(tmpNode.left);
            }

            value = tmpNode.value;
        }
        return value;
    }
}