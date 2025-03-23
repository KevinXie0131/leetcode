package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q113_Path_Sum_II {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode root1 = new TreeNode(4);
        TreeNode root2 = new TreeNode(8);
        TreeNode root3 = new TreeNode(11);
        TreeNode root4 = new TreeNode(7);
        TreeNode root5 = new TreeNode(2);
        root3.left = root4;
        root3.right = root5;
        root1.left = root3;
        root.left = root1;
        root.right = root2;
        List<List<Integer>> r = pathSum1(root, 22);
        System.out.println(r);
    }
    /**
     * 类似Q112 Path Sum，但是需要找到所有路径
     * 说明: 叶⼦节点是指没有⼦节点的节点
     */
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    ArrayList<Integer> pathSum = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        dfs(root, targetSum); // 把根节点放进路径
        return result;
    }

    public void dfs(TreeNode root, int targetSum){ // 递归函数不需要返回值，因为我们要遍历整个树
        if (root == null) return;

        pathSum.add(root.value);
        if (root.left == null && root.right == null) { // 遇到了叶⼦节点切找到了和为sum的路径
            if(pathSum.stream().reduce(0, (a, b) -> a + b) == targetSum){
                ArrayList newList = new ArrayList(pathSum);
                result.add(newList);
            }
        }
        if (root.left != null) { // 左 （空节点不遍历）
            dfs(root.left,  targetSum); // 递归
            pathSum.remove(pathSum.size() - 1); // 回溯
        }
        if (root.right != null) { // 右 （空节点不遍历）
            dfs(root.right,  targetSum);
            pathSum.remove(pathSum.size() - 1); // 回溯
        }
    }
/*    public void dfs(TreeNode root, int targetSum){  // 另一种不同形式
        if (root == null) return;

        pathSum.add(root.value);
        if (root.left == null && root.right == null) {
            if (targetSum == root.value) {
                ArrayList newList = new ArrayList(pathSum);
                result.add(newList);
            }
        }
        if (root.left != null) {
            pathSum(root.left,  targetSum - root.value);
            pathSum.remove(pathSum.size() - 1);
        }
        if (root.right != null) {
            pathSum(root.right,  targetSum - root.value);
            pathSum.remove(pathSum.size() - 1);
        }
    }*/
    /*    public void dfs(TreeNode root, int targetSum){  // 另一种不同形式
        if (root == null) return;

        pathSum.add(root.value);
        targetSum -= root.value;

        if (root.left == null && root.right == null) {
            if (targetSum == 0) {
                ArrayList newList = new ArrayList(pathSum);
                result.add(newList);
            }
        }
        if (root.left != null) {
            pathSum(root.left,  targetSum);
            pathSum.remove(pathSum.size() - 1);
        }
        if (root.right != null) {
            pathSum(root.right,  targetSum);
            pathSum.remove(pathSum.size() - 1);
        }
    }*/
    /**
     * 路径总和ii要遍历整个树，找到所有路径，所以递归函数不要返回值
     */
    static  List<List<Integer>> result1 = new ArrayList<List<Integer>>();
    static List<Integer> path = new ArrayList<>();

    public static  List<List<Integer>> pathSum1(TreeNode root, int targetSum) {
        if (root == null) return result1;
        path.add(root.value);// 把根节点放进路径
        dfs(root, path, targetSum - root.value);
        return result1;
    }
    // 递归函数不需要返回值，因为我们要遍历整个树
    public static void dfs(TreeNode root, List<Integer> path , int targetSum){
        if (root.left == null && root.right == null) {
            if(targetSum == 0){
                result1.add(new ArrayList(path));  // 遇到了叶子节点且找到了和为sum的路径
                return;
            } else{
                return; // 遇到叶子节点而没有找到合适的边，直接返回
            }
        }
        if (root.left != null) {  // 左 （空节点不遍历）
            targetSum -= root.left.value;
            path.add(root.left.value);
            dfs(root.left, path, targetSum);  // 递归
            path.remove(path.size() - 1);  // 回溯
            targetSum += root.left.value;  // 回溯

        }
        if (root.right != null) { // 右 （空节点不遍历）
            targetSum -= root.right.value;
            path.add(root.right.value);
            dfs(root.right,  path, targetSum);  // 递归
            path.remove(path.size() - 1);  // 回溯
            targetSum += root.right.value;  // 回溯

        }
    }
}
