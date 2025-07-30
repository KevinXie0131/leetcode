package com.answer.tree;

import com.template.TreeNode;
import java.util.*;

public class Q270_Closest_Binary_Search_Tree_Value {
    /**
     * 最近的二叉搜索树的值
     * 给定一个二叉搜索树（BST）的根节点 root 和一个目标值 target，返回 BST 中最接近目标值 target 的数值。
     * 你可以假设 BST 中只有一个最接近 target 的值。
     * Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
     * Note: Given target value is a floating point.
     *       You are guaranteed to have only one unique value in the BST that is closest to the target.
     */
    public static void main(String[] args) {
        //       4
        //      / \
        //     2   5
        //    / \
        //   1   3
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(3);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
    //    System.out.println(closestValue_4(node1, 3.714286));
        System.out.println(closestValue(node1, 3.714286)); // Test 1: target = 3.714286, closest is 4
        System.out.println(closestValue(node1, 2.5));   // Test 2: target = 2.5, closest is 2
        System.out.println(closestValue(node1, 1)); // Test 3: target = 1, closest is 1
        System.out.println(closestValue(node1, 5)); // Test 4: target = 5, closest is 5
        System.out.println(closestValue(node1, 2.9));   // Test 5: target = 2.9, closest is 3
    }
    /**
     * Recursion
     */
    static int res = Integer.MAX_VALUE;
    static double closest = Integer.MAX_VALUE;
    public static int closestValue(TreeNode root, double target) {
        dfs(root, target);
        return res;
    }

    public static void dfs(TreeNode root, double target){
        if(root == null) {
            return;
        }
        dfs(root.left, target);

        double absVal = Math.abs(target - (double)root.value);
        if(absVal < closest){
            closest = absVal;
            res = root.value;
        }

        dfs(root.right, target);
    }
    /**
     * Approach 2: Iterative Inorder, O(k) time
     */
    static public int closestValue_1(TreeNode root, double target) {
        double closest = Integer.MAX_VALUE;
        int res = Integer.MAX_VALUE;

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()){
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            double absVal = Math.abs(target - (double)cur.value);
            if(absVal < closest){
                closest = absVal;
                res = cur.value;
            }
            cur = cur.right;
        }
        return res;
    }
    /**
     * Approach 3: Binary Search, O(H) time
     */
    public int closestValue_3(TreeNode root, double target) {
        int closest = root.value;
        while(root != null){
            closest = Math.abs(root.value - target) < Math.abs(closest - target) ? root.value : closest;

            root = target < root.value ? root.left : root.right;
        }
        return closest;
    }
    /**
     * Another form of Approach 3
     * 利用二叉搜索树的性质，根据 target 和当前节点值的大小，决定向左还是向右遍历。
     */
    static public int closestValue_5(TreeNode root, double target) {
        if(root.value == target) return root.value;
        int res = root.value;
        while(root != null){
            if(Math.abs(root.value - target) < Math.abs(res - target)){
                res = root.value;
            }
            if(root.value < target){  // 根据 target 和当前节点值的大小关系决定向左还是向右遍历
                root = root.right;
            }else{
                root = root.left;
            }
        }
        return res;
    }
    /**
     * Approach 1: Recursive Inorder + Linear search, O(N) time
     */
    public static int closestValue_4(TreeNode root, double target) {
        List<Integer> nums = new ArrayList();
        inorder(root, nums);
        //     Collections.sort(nums,(o1, o2) -> Math.abs(o1 - target) < Math.abs(o2 - target) ? -1 : 1);
        Collections.sort(nums, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Math.abs(o1 - target) < Math.abs(o2 - target) ? -1 : 1;
            }
        });
        //  return Collections.min(nums);
        return nums.get(0);
    }

    public static void inorder(TreeNode root, List<Integer> nums) {
        if (root == null) {
            return;
        }
        inorder(root.left, nums);
        nums.add(root.value);
        inorder(root.right, nums);
    }
}
