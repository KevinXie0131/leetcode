package com.answer.tree;

import com.template.TreeNode;
import java.util.*;

public class Q108_Convert_Sorted_Array_to_Binary_Search_Tree {
    /**
     * 将有序数组转换为二叉搜索树
     * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 平衡 二叉搜索树。
     * Given an integer array nums where the elements are sorted in ascending order, convert it to a height-balanced binary search tree.
     *
     * 平衡二叉树 是指该树所有节点的左右子树的高度相差不超过 1。
     * A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never differs by more than one.
     */
    public static void main(String[] args) {
        int[] nums = {-10,-3,0,5,9};
        TreeNode root  = sortedArrayToBST_1(nums);
        System.out.println(root);
        List<List<String>> result = PrintTree.treeToMatrix(root);
        PrintTree.print2DArray(result);
    }
    /**
     * 将有序数组转换为⼆叉搜索树 (构造⼀棵⼆叉平衡搜索树)
     * 将⼀个按照升序排列的有序数组，转换为⼀棵⾼度平衡⼆叉搜索树
     * 本题中，⼀个⾼度平衡⼆叉树是指⼀个⼆叉树每个节点 的左右两个⼦树的⾼度差的绝对值不超过 1。
     *
     * 本质就是寻找分割点，分割点作为当前节点，然后递归左区间和右区间
     * 因为有序数组构造⼆叉搜索树，寻找分割点就⽐较容易了: 分割点就是数组中间位置的节点。
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        TreeNode root = traversal(nums, 0, nums.length - 1); // 定义的区间为左闭右闭区间[left, right]
        return root;
    }
    /**
     * 每次选择中间节点作为根节点，然后递归对左右子树构造即可。
     *  1-> 2 -> 3 -> 4 -> 5 -> 6 -> 7
     *           4
     *        /    \
     *       2      6
     *      / \    / \
     *     1   3  5   7
     */
    public TreeNode traversal(int[] nums, int left , int right){ // ⽤递归函数的返回值来构造中节点的左右孩⼦
        if(left > right){ // 终止条件: 当 left > right 时，就终止，返回 null，因为这个时候就是空节点。
            return null;
        }
        // 左闭右闭
        // 总是选择中间位置左边的数字作为根节点
        int mid = (left + right) >>> 1; // 获取中间节点的下标mid，以中间节点作为root，这样可以让左右两边的节点数量接近
        TreeNode root = new TreeNode(nums[mid]); // 在构造⼆叉树的时候尽量不要重新定义左右区间数组，⽽是⽤下表来操作原数组
        root.left = traversal(nums, left, mid - 1);   // root的左孩⼦接住下⼀层左区间的构造节点
        root.right = traversal(nums, mid + 1, right); // 右孩⼦接住下⼀层右区间构造的节点

        return root;
    }
    /**
     * 递归: 左闭右开 [left,right)
     */
    public TreeNode sortedArrayToBST1(int[] nums) {
        return sortedArrayToBST1(nums, 0, nums.length);
    }

    public TreeNode sortedArrayToBST1(int[] nums, int left, int right) {
        if (left >= right) {
            return null;
        }
/*        if (right - left == 1) {  // 可省略
            return new TreeNode(nums[left]);
        }*/
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST1(nums, left, mid);
        root.right = sortedArrayToBST1(nums, mid + 1, right);
        return root;
    }
    /**
     * 迭代法
     * 可以通过三个队列来模拟，⼀个队列放遍历的节点，⼀个队列放左区间下表，⼀个队列放右区间下表
     * 其实思路也是一样的，不断中间分割，然后递归处理左区间，右区间，也可以说是分治。
     */
    public static TreeNode sortedArrayToBST_1(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(0); // 初始根节点
        Deque<TreeNode> nodeQue = new ArrayDeque<>(); // 放遍历的节点 // 存储待处理的节点
        Deque<Integer> leftQue = new ArrayDeque<>(); // 保存左区间下表  // 存储左边界
        Deque<Integer> rightQue = new ArrayDeque<>(); // 保存右区间下表 // 存储右边界

        nodeQue.offer(root); // 根节点⼊队列
        leftQue.offer(0); // 0为左区间下表初始位置
        rightQue.offer(nums.length - 1); // nums.size() - 1为右区间下表初始位置
        // 当nodeQue中还有待处理的节点时，去除该节点以及其对应的左右边界，计算中间下标，用中间的元素为该节点赋值；
        // 如果该节点左侧还有未处理的元素，新建一个空节点作为其左孩子，存入nodeQue中，并存入左孩子对应的左右边界，右侧同理；
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
