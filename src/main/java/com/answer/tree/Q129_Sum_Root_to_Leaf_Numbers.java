package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q129_Sum_Root_to_Leaf_Numbers {
    /**
     * 求根节点到叶节点数字之和
     * 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
     * 每条从根节点到叶节点的路径都代表一个数字：
     *  例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
     * 计算从根节点到叶节点生成的 所有数字之和 。
     * 叶节点 是指没有子节点的节点。
     * You are given the root of a binary tree containing digits from 0 to 9 only.
     * Each root-to-leaf path in the tree represents a number.
     *  For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
     * Return the total sum of all root-to-leaf numbers. Test cases are generated so that the answer will fit in a 32-bit integer.
     * A leaf node is a node with no children.
     */
    /**
     * refer to Q113_Path_Sum_II_1
     */
    int res = 0;

    public int sumNumbers_0(TreeNode root) {
        Deque<String> path = new ArrayDeque<>();
        dfs_0(root, path);
        return res;
    }

    private void dfs_0(TreeNode root, Deque<String> path){
        if(root == null) return;

        if(root.left == null && root.right == null){
            path.addLast(root.value  + "");
            res += Integer.valueOf(String.join("", path));
            path.removeLast();
            return;
        }
        path.addLast(root.value + "");
        dfs_0(root.left, path);
        dfs_0(root.right, path);
        path.removeLast();
    }
    /**
     * refer to Q113_Path_Sum_II_1
     */
    int res2 = 0;

    public int sumNumbers_0a(TreeNode root) {
        dfs_0a(root, 0);
        return res2;
    }

    private void dfs_0a(TreeNode root, int value){
        if(root == null) return;

        if(root.left == null && root.right == null){
            res2 += value * 10 + root.value;
            return;
        }
        dfs_0a(root.left, value * 10 + root.value);
        dfs_0a(root.right, value * 10 + root.value);
    }
    /**
     * 逻辑简单的递归
     */
    public int sumNumbers_0b(TreeNode root) {
        return helper(root, 0);
    }

    private int helper(TreeNode root, int value){ // 有返回值
        if(root == null) return 0;

        if(root.left == null && root.right == null){
            return value * 10 + root.value;
        }
        return helper(root.left, value * 10 + root.value) + helper(root.right, value * 10 + root.value);
    }
    /**
     * 本题和113.路径总和II 是类似的思路: 首先思路很明确，就是要遍历整个树把更节点到叶子节点组成的数字相加。
     */
    StringBuilder sb = new StringBuilder();
    int sum = 0;

    public int sumNumbers(TreeNode root) {
        dfs(root);
        return sum;
    }
    // 回溯: 本题其实采用前中后序都不无所谓， 因为也没有中间几点的处理逻辑。
    //      主要是当左节点不为空，path收集路径，并递归左孩子，右节点同理
    public void dfs(TreeNode root){
        if(root == null) return;

        sb.append(root.value); // 中

        if(root.left == null && root.right == null) sum +=  Integer.valueOf(sb.toString());  // 遇到了叶子节点 累加结果

        if(root.left != null) { // 左 （空节点不遍历）
            dfs(root.left); // 递归
            sb.deleteCharAt(sb.length() -1 );  // 回溯
        }
        if(root.right != null) {  // 右 （空节点不遍历）
            dfs(root.right);  // 递归
            sb.deleteCharAt(sb.length() -1 );  // 回溯
        }
    }
    /**
     * 回溯 另一种形式
     */
    List<Integer> path = new ArrayList<>();
    int sum1 = 0;

    public int sumNumbers1(TreeNode root) {
        dfs1(root);
        return sum1;
    }

    public void dfs1(TreeNode root){
        if(root == null) return;

        path.add(root.value);

        if(root.left == null && root.right == null) {
            int total = 0;   // 当是叶子节点的时候，开始处理
            for(int i = 0; i < path.size(); i++){
                total = total * 10 + path.get(i); // sum * 10 表示进位   0 <= Node.val <= 9
            }
            sum1 += total;
        }

        if(root.left != null) {
            dfs1(root.left);
            path.remove(path.size() -1 );// 注意有回溯
        }
        if(root.right != null) {
            dfs1(root.right);
            path.remove(path.size() -1 );// 注意有回溯
        }
    }
    /**
     * DFS，维护从根节点到当前节点所构成的数字，当遍历到叶子节点时，累加到结果。
     */
    int sum2 = 0;

    public int sumNumbers2(TreeNode root) {
        dfs2(root, 0);
        return sum2;
    }

    void dfs2(TreeNode node, int num){
        num = num * 10 + node.value;

        if(node.left == null && node.right == null) {
            sum2 += num;
        }
        if(node.left != null) {
            dfs2(node.left, num);
        }
        if(node.right != null) {
            dfs2(node.right, num);
        }
    }
    /**
     * 广度优先搜索, 需要维护两个队列，分别存储节点和节点对应的数字
     */
    public int sumNumbers7(TreeNode root) {
        if (root == null) return 0;

        int sum = 0;
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> numQueue = new LinkedList<>();
        nodeQueue.offer(root);
        numQueue.offer(root.value);
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int num = numQueue.poll();
            TreeNode left = node.left, right = node.right;
            if (left == null && right == null) {
                sum += num;
            } else {
                if (left != null) {
                    nodeQueue.offer(left);
                    numQueue.offer(num * 10 + left.value);
                }
                if (right != null) {
                    nodeQueue.offer(right);
                    numQueue.offer(num * 10 + right.value);
                }
            }
        }
        return sum;
    }
    /**
     * BFS直接更新子节点的值，无需另一个queue
     */
    public int sumNumbers8(TreeNode root) {
        int count = 0;
        Queue<TreeNode> nodes = new ArrayDeque<>();
        nodes.add(root);

        while (!nodes.isEmpty()){
            TreeNode node = nodes.poll();
            if (node.left == null && node.right == null){
                count += node.value;
            }
            if (node.left != null){
                node.left.value = node.value * 10 + node.left.value;
                nodes.add(node.left);
            }
            if (node.right != null){
                node.right.value = node.value * 10 + node.right.value;
                nodes.add(node.right);
            }
        }
        return count;
    }
}
