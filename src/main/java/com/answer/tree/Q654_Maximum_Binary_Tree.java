package com.answer.tree;

import com.template.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q654_Maximum_Binary_Tree {
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
        for (int i = left + 1; i< right; i++) {
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
