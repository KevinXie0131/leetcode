package com.answer.tree;

import com.template.TreeNode;
import java.util.*;

public class Q653_Two_Sum_IV_Input_is_a_BST {
    /**
     * 两数之和 IV - 输入二叉搜索树
     * 给定一个二叉搜索树 root 和一个目标结果 k，如果二叉搜索树中存在两个元素且它们的和等于给定的目标结果，则返回 true。
     * Given the root of a binary search tree and an integer k, return true if there exist two elements in the BST such that their sum is equal to k, or false otherwise.
     */
    /**
     * Approach #3 Using BST (Binary Search Tree)
     * Inorder traversal of a BST gives the nodes in ascending order
     * 深度优先搜索 + 中序遍历 + 双指针
     */
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();

        dfs(root, list);
        int i = 0, j = list.size() - 1;
        while(i < j){
            if(list.get(i) + list.get(j) > k){
                j--;
            }else if(list.get(i) + list.get(j) < k){
                i++;
            }else{
                return true;
            }
        }
        return false;
    }

    public void dfs(TreeNode root, List<Integer> list){
        if(root == null) return;

        dfs(root.left, list);
        list.add(root.value);
        dfs(root.right, list);
    }
    /**
     * Approach #1 Using HashSet + Recursion
     */
    public boolean findTarget_1(TreeNode root, int k) {
        Set<Integer> set = new HashSet();
        return dfs(root, set, k);
    }

    public boolean dfs(TreeNode root, Set<Integer> set, int k){
        if(root == null) return false;

        if(set.contains(k - root.value)){
            return true;
        }
        set.add(root.value);

        boolean left = dfs(root.left, set, k);
        boolean right = dfs(root.right, set, k);
        return left || right;
    }
    /**
     * Approach #2 Using Iteration and HashSet
     */
    public boolean findTarget_2(TreeNode root, int k) {
        Set<Integer> set = new HashSet();
        if (root == null){
            return false;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()){
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();

                if(set.contains(k - cur.value)){
                    return true;
                }
                set.add(cur.value);

                cur = cur.right;
            }
        }
        return false;
    }
    /**
     * another form
     */
    public boolean findTarget_2a(TreeNode root, int k) {
        Set<Integer> set = new HashSet();
        if (root == null) return false;
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()){
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if(set.contains(k - cur.value)){
                return true;
            }
            set.add(cur.value);
            cur = cur.right;
        }
        return false;
    }
}
