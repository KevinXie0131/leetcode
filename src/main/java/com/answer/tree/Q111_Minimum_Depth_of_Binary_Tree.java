package com.answer.tree;

import com.template.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Q111_Minimum_Depth_of_Binary_Tree {
    /**
     * 最⼩深度是从根节点到最近叶⼦节点的最短路径上的节点数量。注意是叶⼦节点。
     * 什么是叶⼦节点，左右孩⼦都为空的节点才是叶⼦节点!
     *
     * 可以看出：求⼆叉树的最⼩深度和求⼆叉树的最⼤深度的差别主要在于 处理左右孩⼦不为空 的逻辑
     *
     * 本题依然是前序遍历和后序遍历都可以，前序求的是深度，后序求的是高度。
     *  1. 二叉树节点的深度：指从根节点到该节点的最长简单路径边的条数或者节点数（取决于深度从0开始还是从1开始）
     *  2. 二叉树节点的高度：指从该节点到叶子节点的最长简单路径边的条数后者节点数（取决于高度从0开始还是从1开始）
     * 那么使用后序遍历，其实求的是根节点到叶子节点的最小距离，就是求高度的过程，不过这个最小距离 也同样是最小深度。
     */
    public int minDepth0(TreeNode root) { // 递归 后序遍历（左右中）
        if (root == null) {
            return 0;
        }
        int leftDepth = minDepth(root.left); // 左
        int rightDepth = minDepth(root.right); // 右
        // 中
        // 当⼀个左⼦树为空，右不为空，这时并不是最低点
        if (root.left == null && root.right != null) { // 如果左子树为空，右子树不为空，说明最小深度是 1 + 右子树的深度。
            return 1 + rightDepth;
        }
        // 当⼀个右⼦树为空，左不为空，这时并不是最低点
        if (root.left != null && root.right == null) { // 右子树为空，左子树不为空，最小深度是 1 + 左子树的深度。
            return 1 + leftDepth;
        }

        int result = 1 + Math.min(leftDepth, rightDepth); // 最后如果左右子树都不为空，返回左右子树深度最小值 + 1 。

        return result;
    }
    /**
     * 递归 优化
     * 递归法，相比求MaxDepth要复杂点
     *  因为最小深度是从根节点到最近**叶子节点**的最短路径上的节点数量
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }

        int left = minDepth(root.left);  // 左
        int right = minDepth(root.right);  // 右
        /**
         * 当⼀个左⼦树为空，右不为空，这时并不是最低点
         *    if (node.left == NULL && node.right != NULL) {
         *       return 1 + rightDepth;
         *    }
         * 当⼀个右⼦树为空，左不为空，这时并不是最低点
         *    if (node.left != NULL && node.right == NULL) {
         *       return 1 + leftDepth;
         *    }
         */
        if (root.left == null || root.right == null) {
            return left + right +1 ;
        }
        // 左右结点都不为null
        return Math.min(left, right) + 1;
    }
    /**
     * 递归 有最大初始值
     */
    public int minDepth1(TreeNode root) {
        if(root == null) {
            return 0;
        }
        if(root.left == null && root.right == null) {
            return 1;
        }
        int ans = Integer.MAX_VALUE;
        if(root.left != null) {
            ans = Math.min(minDepth1(root.left), ans);
        }
        if(root.right != null) {
            ans = Math.min(minDepth1(root.right), ans);
        }
        return ans + 1;
    }
    /**
     * DFS递归 (层序)
     * 这个模板与Q104_Maximum_Depth_of_Binary_Tree相似
     */
    int result1 = Integer.MAX_VALUE;
    public int minDepth_4(TreeNode root) {
        if (root == null) {  // 函数递归终止条件
            return 0;
        }
        getdepth2(root, 1);
        return result1;
    }
    public void getdepth2(TreeNode node, int depth) {
        if (node == null) {
            return;
        }
        // 中，处理逻辑：判断是不是叶子结点
        if(node.left == null && node.right == null){
            result1 = Math.min(depth ,result1); // 中
        }

        getdepth2(node.left, depth + 1); // 左
        getdepth2(node.right, depth + 1); // 右
    }
    /**
     * 这个模板与Q104_Maximum_Depth_of_Binary_Tree相似, 但是用了后序遍历
     * DFS递归 (层序)（思路来自二叉树最大深度的递归法）
     * 该题求最小深度，最小深度为根节点到叶子节点的深度，所以在迭代到每个叶子节点时更新最小值。
     */
    int depth = 0;
    // 定义最小深度，初始化最大值
    int minDepth = Integer.MAX_VALUE;
    public int minDept5h(TreeNode root) {
        dep(root);
        return minDepth == Integer.MAX_VALUE ? 0 : minDepth;
    }
    void dep(TreeNode root){
        if(root == null) return ;
        // 递归开始，深度增加
        depth++;
        dep(root.left);
        dep(root.right);
        // 该位置表示递归到叶子节点了，需要更新最小深度minDepth
        if(root.left == null && root.right == null)
            minDepth = Math.min(minDepth , depth);
        // 递归结束，深度减小
        depth--;
    }
    /**
     * BFS迭代法 层序遍历
     */
    public int minDepth2(TreeNode root) {
        int result = Integer.MAX_VALUE;
        if (root == null) {
            return 0;
        }

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()) {

            int size = queue.size();
            depth++; // 记录最⼩深度
            while (size > 0) {
                TreeNode cur = queue.poll();
                // 需要注意的是，只有当左右孩子都为空的时候，才说明遍历到最低点了。如果其中一个孩子不为空则不是最低点
                if (cur.left == null && cur.right == null) {
                    result = Math.min(result, depth); // 当左右孩⼦都为空的时候，说明是最低点的⼀层了，退出
                }

                if (cur.left != null) {queue.offer(cur.left);}
                if (cur.right != null) {queue.offer(cur.right);}

                size--;
            }
        }

        return result;
    }
    /**
     * 相对于 104.二叉树的最大深度 ，本题还也可以使用层序遍历的方式来解决，思路是一样的。
     * 需要注意的是，只有当左右孩子都为空的时候，才说明遍历的最低点了。如果其中一个孩子为空则不是最低点
     */
    public int minDepth_3(TreeNode root) { // 迭代
        int depth = 0;
        if (root == null) {
            return depth;
        }

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++; // 记录深度
            while (size > 0) {
                TreeNode cur = queue.poll();

                if (cur.left != null) {queue.offer(cur.left);}
                if (cur.right != null) {queue.offer(cur.right);}
                if(cur.right == null && cur.left ==null){
                    return depth; // 当左右孩子都为空的时候， 是叶子结点, 说明是最低点的一层了，因为从上往下遍历，直接返回最小深度
                }
                size--;
            }

        }
        return depth;
    }
}