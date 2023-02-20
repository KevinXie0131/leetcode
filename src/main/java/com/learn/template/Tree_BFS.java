package com.learn.template;

import com.template.TreeNode;
import java.util.*;

public class Tree_BFS {
    /**
     * 二叉树: BFS
     */
    public int fn(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int ans = 0;

        while (!queue.isEmpty()) {
            int currentLength = queue.size();
            // 做一些当前层的操作

            for (int i = 0; i < currentLength; i++) {
                TreeNode node = queue.remove();
                // 根据题意补充代码
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }

        return ans;
    }
}
