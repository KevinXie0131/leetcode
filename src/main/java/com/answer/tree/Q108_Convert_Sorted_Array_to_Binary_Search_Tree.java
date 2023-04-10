package com.answer.tree;

import com.template.TreeNode;

public class Q108_Convert_Sorted_Array_to_Binary_Search_Tree {
    /**
     * 将有序数组转换为⼆叉搜索树 (构造⼀棵⼆叉平衡搜索树)
     * 将⼀个按照升序排列的有序数组，转换为⼀棵⾼度平衡⼆叉搜索树
     * 本题中，⼀个⾼度平衡⼆叉树是指⼀个⼆叉树每个节点 的左右两个⼦树的⾼度差的绝对值不超过 1。
     *
     * 本质就是寻找分割点，分割点作为当前节点，然后递归左区间和右区间
     * 因为有序数组构造⼆叉搜索树，寻找分割点就⽐较容易了: 分割点就是数组中间位置的节点。
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        TreeNode root = dfs(nums, 0, nums.length - 1); // 左闭右闭区间[left, right]
        return root;
    }

    public TreeNode dfs(int[] nums, int left , int right){ // ⽤递归函数的返回值来构造中节点的左右孩⼦
        if(left> right){
            return null;
        }

        int mid = (left + right) >>> 1;
        TreeNode root = new TreeNode(nums[mid]); // 在构造⼆叉树的时候尽量不要重新定义左右区间数组，⽽是⽤下表来操作原数组
        root.left = dfs(nums, left, mid -1);   // root的左孩⼦接住下⼀层左区间的构造节点
        root.right = dfs(nums, mid + 1, right); // 右孩⼦接住下⼀层右区间构造的节点

        return root;
    }
}
