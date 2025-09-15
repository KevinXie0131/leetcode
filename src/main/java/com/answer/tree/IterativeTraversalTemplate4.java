package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class IterativeTraversalTemplate4 {
    /**
     * ⼆叉搜索树中的搜索
     * 递归
     */
    public TreeNode searchBST(TreeNode root, int val) {
        // 确定终止条件
        if (root == null || root.value == val) { // 如果root为空，或者找到这个数值了，就返回root节点
            return root;
        }
        // 确定单层递归的逻辑
        else if (root.value < val) { // 因为二叉搜索树的节点是有序的，所以可以有方向的去搜索
            return searchBST(root.right, val);
        }
        else if (root.value > val) {
            return searchBST(root.left, val); // 不要忘了，递归函数还有返回值
        }
        return null;
    }
    /**
     * 迭代
     */
    public TreeNode searchBST1(TreeNode root, int val) {
        while (root != null) {
            if (root.value == val) {
                return root;
            } else if (root.value > val) {
                root = root.left;
            } else if (root.value < val) {
                root = root.right;
            }
        }
        return null;
    }
    /**
     * 相同的树
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true; // 两个节点均为空
        if (p == null || q == null) return false; // 一个节点为空 一个节点不为空
        return p.value == q.value && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);  // 两个节点均不为空
    }
    /**
     * 相同的树
     */
    public boolean isSameTree1 (TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }  else if (p == null || q == null || p.value != q.value) {
            return false;
        } else {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }
    /**
     * 二叉树的最大深度
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0; // 确定终⽌条件：如果为空节点的话，就返回0，表⽰⾼度为0
        }
        // 确定单层递归的逻辑：先求它的左⼦树的深度，再求的右⼦树的深度，最后取左右深度最⼤的数值 再+1
        //                  （加1是因为算上当前中间节点）就是⽬前节点为根节点的树的深度
        int left = maxDepth(root.left);    // 左
        int right = maxDepth(root.right);  // 右
        return Math.max(left, right) + 1;  // 中 用后序遍历（左右中）来计算树的高度 / 二叉树的最大深度为左右子树的最大深度加1得到。
    }
    /**
     * 路径总和
     * refer to Q113_Path_Sum_II
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) {
            return false;
        }
        return dfs(root, targetSum - root.value); // 要先减掉root.value
    }

    private boolean dfs(TreeNode root, int targetSum) {
        if (root.left == null && root.right == null){
            return targetSum == 0; // 直接判断 targetSum 是否等于 0 即可
        }

        if (root.left != null) {
            if (dfs(root.left, targetSum - root.left.value)) {// 隐藏回溯
                return true;
            }
        }
        if (root.right != null) {
            if (dfs(root.right, targetSum - root.right.value)) {// 隐藏回溯
                return true;
            }
        }
        return false;
    }
    /**
     * 路径总和
     * refer to Q113_Path_Sum_II_1
     */
    public boolean hasPathSum1(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null){
            return root.value == targetSum; // 直接判断 targetSum 是否等于 value 即可
        }

        if(hasPathSum1(root.left, targetSum - root.value)){// 隐藏回溯
            return true;
        }
        if(hasPathSum1(root.right, targetSum - root.value)){// 隐藏回溯
            return true;
        }
        return false;
    }

    /**
     * 路径总和 II
     * refer to Q113_Path_Sum_II
     */
    public List<List<Integer>> pathSum2 (TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<Integer> path = new ArrayDeque<>();
        path.add(root.value);
        dfs(root, targetSum - root.value, path, res); // 要先减掉root.value
        return res;
    }

    private void dfs(TreeNode root, int targetSum, Deque<Integer> path, List<List<Integer>> res) {
        if (root.left == null && root.right == null) {
            if(targetSum == 0) {
                res.add(new ArrayList(path));  // 遇到了叶子节点且找到了和为sum的路径
            }
            return; // 遇到叶子节点而没有找到合适的边，直接返回
        }
        if (root.left != null) {  // 左 （空节点不遍历）
            path.addLast(root.left.value);
            dfs(root.left, targetSum - root.left.value, path, res);  // 递归
            path.removeLast();  // 回溯

        }
        if (root.right != null) { // 右 （空节点不遍历）
            path.addLast(root.right.value);
            dfs(root.right, targetSum - root.right.value, path, res);  // 递归
            path.removeLast(); // 回溯
        }
    }
    /**
     * 路径总和 II
     * refer to Q113_Path_Sum_II_1
     */
    public List<List<Integer>> pathSum2a (TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<Integer> path = new ArrayDeque<>();
        dfs2(root, sum, path, res);
        return res;
    }

    private void dfs2(TreeNode node, int sum, Deque<Integer> path, List<List<Integer>> res) {
        if (node == null) {  // 递归终止条件 1
            return;
        }
        if (node.value == sum && node.left == null && node.right == null) { // 递归终止条件 2
            path.addLast(node.value); // 当前结点的值还没添加到列表中，所以要先添加，然后再移除
            res.add(new ArrayList<>(path));
            path.removeLast();
            return;
        }

        path.addLast(node.value);
        dfs2(node.left, sum - node.value, path, res);
        // 进入左右分支的 path 是一样的，这里不用写下面两行，因为一定会调用到 path.removeLast();
        // path.removeLast();
        // path.addLast(node.val);
        dfs2(node.right, sum - node.value, path, res);
        path.removeLast();
    }
}
