package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q654_Maximum_Binary_Tree {
    /**
     * 最大二叉树
     * 给定一个不重复的整数数组 nums 。 最大二叉树 可以用下面的算法从 nums 递归地构建:
     *  创建一个根节点，其值为 nums 中的最大值。
     *  递归地在最大值 左边 的 子数组前缀上 构建左子树。
     *  递归地在最大值 右边 的 子数组后缀上 构建右子树。
     * 返回 nums 构建的 最大二叉树 。
     * You are given an integer array nums with no duplicates. A maximum binary tree can be built recursively from nums using the following algorithm:
     *  Create a root node whose value is the maximum value in nums.
     *  Recursively build the left subtree on the subarray prefix to the left of the maximum value.
     *  Recursively build the right subtree on the subarray suffix to the right of the maximum value.
     * Return the maximum binary tree built from nums.
     * 示例 1：
     * 输入：nums = [3,2,1,6,0,5]
     * 输出：[6,3,5,null,2,0,null,null,1]
     * 解释：递归调用如下所示：
     * - [3,2,1,6,0,5] 中的最大值是 6 ，左边部分是 [3,2,1] ，右边部分是 [0,5] 。
     *     - [3,2,1] 中的最大值是 3 ，左边部分是 [] ，右边部分是 [2,1] 。
     *         - 空数组，无子节点。
     *         - [2,1] 中的最大值是 2 ，左边部分是 [] ，右边部分是 [1] 。
     *             - 空数组，无子节点。
     *             - 只有一个元素，所以子节点是一个值为 1 的节点。
     *     - [0,5] 中的最大值是 5 ，左边部分是 [0] ，右边部分是 [] 。
     *         - 只有一个元素，所以子节点是一个值为 0 的节点。
     *         - 空数组，无子节点。
     */
    /**
     * 构造⼀棵最⼤的⼆叉树
     * ⼀个以此数组构建的最⼤⼆叉树定义如下：
     *     ⼆叉树的根是数组中的最⼤元素。
     *     左⼦树是通过数组中最⼤值左边部分构造出的最⼤⼆叉树。
     *     右⼦树是通过数组中最⼤值右边部分构造出的最⼤⼆叉树。
     * 构造树⼀般采⽤的是前序遍历，因为先构造中间节点，然后递归构造左⼦树和右⼦树
     *
     * 注意类似⽤数组构造⼆叉树的题⽬，每次分隔尽量不要定义新的数组，⽽是通过下表索引直接在原数组上操作，这样可以节约时间和空间上的开销
     * ⼀般情况来说：如果让空节点（空指针）进⼊递归，就不加if，如果不让空节点进⼊递归，就加if限制⼀下， 终⽌条件也会相应的调整
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return dfs(nums, 0, nums.length);
    }
    // 在左闭右开区间[left, right)，构造⼆叉树
    public TreeNode dfs(int[] nums, int left, int right){
        if(right - left < 1){ // 允许空节点进入递归当然终止条件也要有相应的改变。 终⽌条件，是遇到空节点，也就是数组区间为0，就终⽌
            return null;
        }
        if(right - left == 1){ // 不加也可以
            return new TreeNode(nums[left]);
        }
/*        if (leftIndex >= rightIndex) {// 可替代上面两个 （左闭右开）
            return null;
        }*/
        // 1. 先要找到数组中最⼤的值和对应的下表， 最⼤的值构造根节点，下表⽤来下⼀步分割数组
        int maxIndex = left;      // 最大值所在位置
        int maxVal = nums[left]; ;// 最大值
        for (int i = left + 1; i < right; i++) {
            if (nums[i] > maxVal){
                maxIndex = i; // 分割点下表：maxValueIndex
                maxVal = nums[i];
            }
        }
        TreeNode root = new TreeNode(maxVal);  // 根据maxIndex划分左右子树
        // 左闭右开：[left, maxValueIndex)
        root.left = dfs(nums, left, maxIndex); // 2. 最⼤值所在的下表左区间 构造左⼦树
        // 左闭右开：[maxValueIndex + 1, right)
        root.right = dfs(nums, maxIndex + 1, right); // 3. 最⼤值所在的下表右区间 构造右⼦树
        return root;
    }
    /**
     * 递归 左闭右闭
     * 递归区间内找最大值为根节点，左边递归构建左子树，右边递归构建右子树。
     * 时间复杂度：平均 O(n log n)，最坏 O(n^2)。
     */
    public TreeNode constructMaximumBinaryTree1(int[] nums) {
        return dfs1(nums, 0, nums.length - 1);
    }

    public TreeNode dfs1(int[] nums, int left, int right){
        if(right < left){
            return null;
        }
        if(right == left){  // can be commented
            return new TreeNode(nums[left]);
        }
        int maxIndex = left;  // 找到当前区间最大值及其下标, 每次都要找一段序列中的最大值
        int maxVal = nums[left];
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] > maxVal){
                maxIndex = i;
                maxVal = nums[i];
            }
        }
        TreeNode root = new TreeNode(maxVal);

        root.left = dfs1(nums, left, maxIndex - 1);
        root.right = dfs1(nums, maxIndex + 1, right);
        return root;
    }
    /**
     * 单调栈 : 仅通过对数组nums的一次遍历，就可以得出最终结果
     * 根据题意，进行二叉树的构造。即：
     *  1> 如果栈顶元素大于待插入的元素，则：栈顶元素.right = 待插入元素。
     *  2> 如果栈顶元素小于待插入的元素，则：待插入元素.left = 栈顶元素。
     */
    public TreeNode constructMaximumBinaryTree2(int[] nums) {
        Deque<TreeNode> deque = new ArrayDeque();
        for (int i = 0; i < nums.length; i++) {
            TreeNode node = new TreeNode(nums[i]);
            while(!deque.isEmpty()) {
                TreeNode topNode = deque.peekLast();
                if (topNode.value > node.value) {
                    deque.addLast(node);
                    topNode.right = node;
                    break;
                } else {
                    deque.removeLast(); // 出栈操作
                    node.left = topNode;
                }
            }
            if (deque.isEmpty()) {
                deque.addLast(node);
            }
        }
        return deque.peek();
    }
}
