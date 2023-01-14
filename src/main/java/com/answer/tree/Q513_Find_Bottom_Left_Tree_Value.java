package com.answer.tree;

import com.template.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q513_Find_Bottom_Left_Tree_Value {
    int result, maxHeight;
    public int findBottomLeftValue(TreeNode root) {
        result = 0;
        maxHeight = -1;
        dfs(root, 0);
        return result;
    }

    private void dfs(TreeNode node, int level) {
        if (node == null) return;
        if (level > maxHeight) {
            maxHeight = level;
            result = node.value;
        }
        dfs(node.left, level + 1);
        dfs(node.right, level + 1);

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
