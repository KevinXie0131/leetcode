package com.answer.tree;

import com.template.TreeNode;
import java.util.*;

public class Q437_Path_Sum_III_1 {
}
/**
 * 版本一：BFS + DFS
 */
class Solution {
    int count, num, targetSum;

    public int pathSum(TreeNode root, int targetSum) {
        if(root == null) {
            return 0;
        }
        this.targetSum = targetSum;
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while(!q.isEmpty()){ // BFS遍历所有结点
            TreeNode head = q.poll();
            check(head, 0); // 考察以当前结点为起始的满足要求的路径数量
            if(head.left != null) q.add(head.left);
            if(head.right != null) q.add(head.right);
        }
        return count;
    }

    private void check(TreeNode node, long sum){
        if(node == null){
            return;
        }
        sum = sum + node.value;
        if(sum == targetSum) {
            count++; // 一旦满足，立即累计
        }
        check(node.left, sum);
        check(node.right, sum);
    }
}
/**
 * 版本二：DFS + DFS(不带返回值)
 */
class Solution_1 {
    int count, num, targetSum;

    public int pathSum(TreeNode root, int targetSum) {
        if(root == null){
            return 0;
        }
        this.targetSum = targetSum;
        dfs(root); // DFS遍历所有结点
        return count;
    }

    private void dfs(TreeNode node){
        if(node == null) {
            return;
        }
        check(node, 0); // 考察以当前结点为起始的满足要求的路径数量
        dfs(node.left);
        dfs(node.right);
    }

    private void check(TreeNode node, int sum){
        if(node == null) {
            return;
        }
        sum = sum + node.value;
        if(sum == targetSum) {
            count++; // 一旦满足，立即累计
        }
        check(node.left, sum);
        check(node.right, sum);
    }
}
/**
 * 版本三：DFS + DFS(带返回值)
 */
class Solution_2 {
    public int pathSum(TreeNode root, int targetSum) {
        if(root == null) {
            return 0;
        }
        int count = nodeSum(root, targetSum);
        return count + pathSum(root.left, targetSum) + pathSum(root.right, targetSum);
    }

    private int nodeSum(TreeNode node, int targetSum){
        if(node == null) {
            return 0;
        }
        int count = 0, val = node.value;
        if(val == targetSum) {
            count++;
        }
        return count + nodeSum(node.left, targetSum - val) + nodeSum(node.right, targetSum - val);
    }
}
/**
 * 解法二：前缀和
 */
class Solution_3 {
    int targetSum, count = 0;
    Map<Integer, Integer> map;

    public int pathSum(TreeNode root, int targetSum) {
        if(root == null) {
            return 0;
        }
        this.targetSum = targetSum;
        this.map = new HashMap<>();
        map.put(0, 1); // 表示前缀和为0的节点为空，有一个空。否则若pre_i = targetSum，将错过从root到i这条路径。
        dfs(root, 0);
        return count;
    }
    // 两节点间的路径和 = 两节点的前缀和之差
    // 假如题目给定数值为5
    //    节点1的前缀和为: 1
    //    节点3的前缀和为: 1 + 2 + 3 = 6
    //    prefix(3) - prefix(1) == 5
    //    所以 节点1 到 节点3 之间有一条符合要求的路径( 2 --> 3 )
    //
    // 前缀和的递归回溯思路: 从当前节点反推到根节点(反推比较好理解，正向其实也只有一条)，有且仅有一条路径，因为这是一棵树
    private void dfs(TreeNode node, int preSum){
        if(node == null){
            return;
        }
        preSum += node.value;
        // preSum-targetSum相当于找路径的起点，当前点到起点的距离就是target
        count += map.getOrDefault(preSum - targetSum, 0); // #1 累计满足要求的前缀和数量
        map.put(preSum, map.getOrDefault(preSum, 0) + 1); // #2 先累计再put（先#1，再#2）
        dfs(node.left, preSum);
        dfs(node.right, preSum);
        map.put(preSum, map.get(preSum) - 1); // 路径退缩，去掉不再在路径上的当前结点的前缀和。必存在，无需使用getOrDefault。
    }
}
