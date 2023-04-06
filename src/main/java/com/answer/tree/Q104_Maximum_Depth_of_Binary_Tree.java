package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q104_Maximum_Depth_of_Binary_Tree {
    /**
     * ⼆叉树的深度为根节点到最远叶⼦节点的最长路径上的节点数
     * 说明: 叶⼦节点是指没有⼦节点的节点
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0; // 确定终⽌条件：如果为空节点的话，就返回0，表⽰⾼度为0
        }
        // 确定单层递归的逻辑：先求它的左⼦树的深度，再求的右⼦树的深度，最后取左右
        //                    深度最⼤的数值 再+1 （加1是因为算上当前中间节点）就是⽬前节点为根节点的树的深度
        int left = maxDepth(root.left);    // 左
        int right = maxDepth(root.right);  // 右
        return Math.max(left, right) + 1;  // 中
    }
    /**
     * 代码精简
     * 精简之后的代码根本看不出是哪种遍历⽅式，也看不出递归三部曲的步骤
     */
    public int maxDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
    /**
     * 迭代法 层序遍历
     */
    public int maxDepth2(TreeNode root) {
        int depth = 0;
        if (root == null) {
            return depth;
        }

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size > 0) {
                TreeNode cur = queue.poll();

                if (cur.left != null) {queue.offer(cur.left);}
                if (cur.right != null) {queue.offer(cur.right);}

                size--;
            }
            depth++; // 记录深度
        }
        return depth;
    }

    /**
     *
     */
    int depth = 0;

    public int maxDepth3(TreeNode root) {
        dfs(root, 0);
        return depth;
    }

    public void dfs(TreeNode root, int deep) {
        if (root == null) return;

        if (depth == deep) {
            depth++;
        }

        dfs(root.left,  deep + 1);
        dfs(root.right, deep + 1);
    }
}