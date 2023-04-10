package com.answer.tree;

import com.template.TreeNode;
import java.util.*;

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
    /**
     * 迭代法
     * 可以通过三个队列来模拟，⼀个队列放遍历的节点，⼀个队列放左区间下表，⼀个队列放右区间下表
     */
    public TreeNode sortedArrayToBST_1(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(0); // 初始根节点
        Deque<TreeNode> nodeQue = new ArrayDeque<>(); // 放遍历的节点
        Deque<Integer> leftQue = new ArrayDeque<>(); // 保存左区间下表
        Deque<Integer> rightQue = new ArrayDeque<>(); // 保存右区间下表

        nodeQue.offer(root); // 根节点⼊队列
        leftQue.offer(0); // 0为左区间下表初始位置
        rightQue.offer(nums.length - 1); // nums.size() - 1为右区间下表初始位置

        while (!nodeQue.isEmpty()) {
            TreeNode curNode = nodeQue.poll();
            int left = leftQue.poll();
            int right = rightQue.poll();

            int mid = left + ((right - left) / 2);
            curNode.value = nums[mid]; // 将mid对应的元素给中间节点

            if (left <= mid - 1) { // 处理左区间
                curNode.left = new TreeNode(0);
                nodeQue.offer(curNode.left);
                leftQue.offer(left);
                rightQue.offer(mid - 1);
            }
            if (right >= mid + 1) { // 处理右区间
                curNode.right = new TreeNode(0);
                nodeQue.offer(curNode.right);
                leftQue.offer(mid + 1);
                rightQue.offer(right);
            }
        }
        return root;
    }
}
