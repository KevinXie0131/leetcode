package com.answer.tree;

import com.template.TreeNode;

public class Q108_Convert_Sorted_Array_to_Binary_Search_Tree {

    public TreeNode sortedArrayToBST(int[] nums) {
        TreeNode root = dfs(nums, 0, nums.length - 1);
        return root;
    }

    public TreeNode dfs(int[] nums, int left , int right){
        if(left> right){
            return null;
        }

        int mid = (left + right) >>> 1;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = dfs(nums, left, mid -1);
        root.right = dfs(nums, mid + 1, right);

        return root;
    }
}
