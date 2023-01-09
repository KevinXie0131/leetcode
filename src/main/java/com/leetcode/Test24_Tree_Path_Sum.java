package com.leetcode;

import com.template.Node;
import com.template.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Test24_Tree_Path_Sum {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);

        root.setLeft(node2);
        root.setRight(node5);
        node2.setLeft(node3);
        node2.setRight(node4);
        node5.setLeft(node6);
        node5.setRight(node7);

        boolean isFound = hasPathSum(root, 13);
        System.out.println(isFound);

        System.out.println(hasPathSum2(root, 13));

        System.out.println(hasPathSum3(root, 13));

        System.out.println(hasPathSum4(root, 13));

        System.out.println(hasPathSum5(root, 13));

        System.out.println(pathSum0(root, 13));
    }

    /**
     * 112. Path Sum
     * https://leetcode.com/problems/path-sum/
     */
    public static boolean hasPathSum(TreeNode root, int targetsum) {
        if (root == null) {
            return false;
        }
        targetsum -= root.getValue();
        // 叶子结点
        if (root.getLeft() == null && root.getRight() == null) {
            return targetsum == 0;
        }
        if (root.getLeft() != null) {
            boolean left = hasPathSum(root.getLeft(), targetsum);
            if (left) {// 已经找到
                return true;
            }
        }
        if (root.getRight() != null) {
            boolean right = hasPathSum(root.getRight(), targetsum);
            if (right) {// 已经找到
                return true;
            }
        }
        return false;
    }


    public static boolean hasPathSum2(TreeNode root, int targetSum) {
        if (root == null) return false;

        return dfs2(root, 0, targetSum);
    }

    public static boolean dfs2(TreeNode node, int sum, int targetSum){

        if (node == null)  return false;

        sum += node.getValue();
        if (node.getLeft() == null && node.getRight() == null) {
            return sum == targetSum;
        }

        boolean left = dfs2(node.getLeft(), sum, targetSum);
        boolean right = dfs2(node.getRight(), sum, targetSum);
        return left || right;
    }

    /**
     * Backtracking DFS
     */
    static ArrayList<Integer> pathSum = new ArrayList<>();

    public static boolean hasPathSum3(TreeNode root, int targetSum) {
        if (root == null) return false;

        return dfs3(root, pathSum, targetSum);
    }

    public static boolean dfs3(TreeNode node, ArrayList<Integer> pathSum, int targetSum){

        if (node == null)  return false;

        pathSum.add(node.getValue());
        if (node.getLeft() == null && node.getRight() == null) {
            return pathSum.stream().reduce(0, (a, b) -> a + b) == targetSum;
        }

        boolean left = false;
        if (node.getLeft() != null) {
            left = dfs3(node.getLeft(), pathSum, targetSum);
            pathSum.remove(pathSum.size() - 1);
        }
        boolean right = false;
        if (node.getRight() != null) {
            right = dfs3(node.getRight(), pathSum, targetSum);
            pathSum.remove(pathSum.size() - 1);
        }
        return left || right;

    }

    /**
     * There is no need to backtracking mannual, since it is an Integer.
     */
    static Integer pathSum1 = new Integer(0);

    public static boolean hasPathSum4(TreeNode root, int targetSum) {
        if (root == null) return false;

        return dfs4(root, pathSum1, targetSum);
    }

    public static boolean dfs4(TreeNode node, Integer pathSum1, int targetSum){

        if (node == null)  return false;

        pathSum1 += node.getValue();
        if (node.getLeft() == null && node.getRight() == null) {
            return pathSum1 == targetSum;
        }

        boolean left = false;
        if (node.getLeft() != null) {
            left = dfs4(node.getLeft(), pathSum1, targetSum);
    //        pathSum1 -= node.getLeft().getValue();
        }
        boolean right = false;
        if (node.getRight() != null) {
            right = dfs4(node.getRight(), pathSum1, targetSum);
    //        pathSum1 -= node.getRight().getValue();
        }
        return left || right;

    }

    public static boolean  hasPathSum5(TreeNode root, int targetSum) {
        if (root == null) return false;

        Deque<TreeNode> stackNode = new ArrayDeque<>();
        Deque<Integer> stackVal = new ArrayDeque<>();
        stackNode.push(root);
        stackVal.push(root.getValue());

        while (!stackNode.isEmpty()) {
            int tmpVal = stackVal.pop();
            TreeNode tmpNode = stackNode.pop();

            if (tmpNode.getLeft() == null & tmpNode.getRight() == null) {
                 if (tmpVal == targetSum) return true;
            }
            if (tmpNode.getRight() != null) {
                stackNode.push(tmpNode.getRight());
                stackVal.push(tmpNode.getRight().getValue() + tmpVal);
            }
            if (tmpNode.getLeft() != null) {
                stackNode.push(tmpNode.getLeft());
                stackVal.push(tmpNode.getLeft().getValue() + tmpVal);
            }
        }

        return false;
    }

    /**
     * 113. Path Sum II
     * https://leetcode.com/problems/path-sum-ii/
     */
    static List<List<Integer>> result = new ArrayList<List<Integer>>();
    static ArrayList<Integer> pathSum0 = new ArrayList<>();

    public  static List<List<Integer>> pathSum0(TreeNode root, int targetSum) {

        dfs0(root, targetSum);
        return result;
    }

    public static void dfs0(TreeNode root, int targetSum){
        if (root == null) return;

        pathSum0.add(root.value);
        if (root.left == null && root.right == null) {
            if(pathSum0.stream().reduce(0, (a, b) -> a + b) == targetSum){
                ArrayList newList = new ArrayList(pathSum0);
                result.add(newList);
            }
        }
        if (root.left != null) {
            pathSum0(root.left,  targetSum);
            pathSum0.remove(pathSum0.size() - 1);
        }
        if (root.right != null) {
            pathSum0(root.right,  targetSum);
            pathSum0.remove(pathSum0.size() - 1);
        }
    }
}
