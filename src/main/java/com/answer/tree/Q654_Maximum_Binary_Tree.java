package com.answer.tree;

import com.template.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q654_Maximum_Binary_Tree {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return dfs(nums, 0, nums.length);
    }

    public TreeNode dfs(int[] nums, int left, int right){
        if(right - left < 1){
            return null;
        }
        if(right - left == 1){
            return new TreeNode(nums[left]);
        }
        int maxIndex = left;
        int maxVal = nums[left];
        for (int i = left + 1; i< right; i++) {
            if (nums[i] > maxVal){
                maxIndex = i;
                maxVal = nums[i];
            }
        }
        TreeNode root = new TreeNode(maxVal);

        root.left = dfs(nums, left, maxIndex);
        root.right = dfs(nums, maxIndex + 1, right);

        return root;
    }
    /**
     *
     */
    public TreeNode constructMaximumBinaryTree1(int[] nums) {
        return dfs1(nums, 0, nums.length - 1);
    }

    public TreeNode dfs1(int[] nums, int left, int right){
        if(right < left){
            return null;
        }
        if(right == left){
            return new TreeNode(nums[left]);
        }
        int maxIndex = left;
        int maxVal = nums[left];
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] > maxVal){
                maxIndex = i;
                maxVal = nums[i];
            }
        }
        TreeNode root = new TreeNode(maxVal);

        root.left = dfs(nums, left, maxIndex - 1);
        root.right = dfs(nums, maxIndex + 1, right);

        return root;
    }
}
