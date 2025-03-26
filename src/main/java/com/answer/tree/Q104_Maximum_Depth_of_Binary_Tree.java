package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q104_Maximum_Depth_of_Binary_Tree {
    /**
     * 前序（中左右）求的就是深度，也可以用后序遍历（左右中）, 求的是高度。
     * 二叉树节点的深度：指从根节点到该节点的最长简单路径边的条数或者节点数（取决于深度从0开始还是从1开始）
     * 二叉树节点的高度：指从该节点到叶子节点的最长简单路径边的条数或者节点数（取决于高度从0开始还是从1开始）
     * 而根节点的高度就是二叉树的最大深度
     *
     * ⼆叉树的深度为根节点到最远叶⼦节点的最长路径上的节点数
     * 说明: 叶⼦节点是指没有⼦节点的节点
     *
     * 求深度可以从上到下去查 所以需要前序遍历（中左右），⽽⾼度只能从下到上去查，所以只能后序遍历（左右中）
     * 求的是⼆叉树的最⼤深度，也⽤的是后序遍历? 那是因为代码的逻辑其实是求的根节点的⾼度，⽽根节点的⾼度就是这颗树的最⼤深度，所以才可以使⽤后序遍历
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0; // 确定终⽌条件：如果为空节点的话，就返回0，表⽰⾼度为0
        }
        // 确定单层递归的逻辑：先求它的左⼦树的深度，再求的右⼦树的深度，最后取左右
        //                    深度最⼤的数值 再+1 （加1是因为算上当前中间节点）就是⽬前节点为根节点的树的深度
        int left = maxDepth(root.left);    // 左
        int right = maxDepth(root.right);  // 右
        return Math.max(left, right) + 1;  // 中 用后序遍历（左右中）来计算树的高度 / 二叉树的最大深度为左右子树的最大深度加一得到。
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
     * 使用迭代法的话，使用层序遍历是最为合适的，因为最大的深度就是二叉树的层数，和层序遍历的方式极其吻合。
     * 在二叉树中，一层一层的来遍历二叉树，记录一下遍历的层数就是二叉树的深度
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
     * DFS递归 (层序)
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
    /**
     * 使用了前序（中左右）的遍历顺序，这才是真正求深度的逻辑
     * 充分表现出求深度回溯的过程
     */
    int result = 0;
    int maxDepth4(TreeNode root) {
        result = 0;
        if (root == null) return result;
        getdepth(root, 1);
        return result;
    }
    public void getdepth(TreeNode node, int depth) {
        // 在104.二叉树的最大深度 中，如果真正求取二叉树的最大深度，代码应该写成如下：（前序遍历）
        result = Math.max(depth ,result); // 中

        if (node.left == null && node.right == null) return;

        if (node.left != null) { // 左
            depth++;    // 深度+1
            getdepth(node.left, depth);
            depth--;    // 回溯，深度-1
        }
        if (node.right != null) { // 右
            depth++;    // 深度+1
            getdepth(node.right, depth);
            depth--;    // 回溯，深度-1
        }
/*        if (node.left != null) {
            getdepth(node.left, depth + 1);
        }
        if (node.right != null) {
            getdepth(node.right, depth + 1);
        }*/
        return ;
    }
    /**
     * 递归法(求深度法)
     */
    int result1 = 0;  //定义最大深度
    int maxDepth_2(TreeNode root) {
        if (root == null) {
            return result1;
        }
        getdepth(root, 1);
        return result1;
    }
    public void getdepth2(TreeNode node, int depth) {
        //递归求解最大深度
        if (node == null) {
            return;
        }
        // 前序（中左右）
        result1 = Math.max(depth ,result1); // 中
        depth++;
        getdepth2(node.left, depth);
        getdepth2(node.right, depth);
        depth--;
    }
}