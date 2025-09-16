package com.answer.tree;

import com.template.TreeNode;
import java.util.*;

public class Q95_Unique_Binary_Search_Trees_II {
    /**
     * 不同的二叉搜索树 II
     * 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
     * Given an integer n, return all the structurally unique BST's (binary search trees), which has exactly n nodes of unique values from 1 to n. Return the answer in any order.
     *
     * 示例 1：
     * 输入：n = 3
     * 输出：[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
     */
    public static void main(String[] args) {
        List<TreeNode> list = generateTrees(3);
        for(TreeNode node : list){
            List<List<String>> result = PrintTree.treeToMatrix(node);
            PrintTree.print2DArray(result);
        }
    }
    /**
     * 自底向上的递归(后序遍历)，组合所有的子树
     */
    static public List<TreeNode> generateTrees(int n) {
        if(n < 1) {
            return new ArrayList<>();
        }
        return helper(1, n);
    }

    static public List<TreeNode> helper(int start, int end){
        List<TreeNode> allTrees  = new ArrayList<>();

        if(start > end){ // 如果 start 大于 end，说明当前没有节点可用，因此返回包含 null 的列表，这个 null 表示空树。
            // 显然，如果一颗树的左子树为空，右子树不为空，要正确构建所有树，依赖于对左右子树列表的遍历，
            // 也就是上述代码两层for循环的地方，如果其中一个列表为空，那么循环都将无法进行。
            allTrees.add(null);  // 如果当前子树为空，不加null行吗？
            return allTrees;
        }
        // 遍历从 start 到 end 的每个值 i，将 i 作为根节点
        for(int i = start; i <= end; i++){// 枚举可行根节点
            // 想想为什么这行不能放在这里，而放在下面？
            // TreeNode root = new TreeNode(i);
            List<TreeNode> leftTrees = helper(start, i - 1);   // 获得所有可行的左子树集合（i 左边的部分）
            List<TreeNode> rightTrees = helper(i + 1, end);  // 获得所有可行的右子树集合（i 右边的部分）
            // 固定左孩子，遍历右孩子
            for(TreeNode left : leftTrees){ // 从左子树集合中选出一棵左子树，从右子树集合中选出一棵右子树，拼接到根节点上
                for(TreeNode right : rightTrees){
                    TreeNode currTree  = new TreeNode(i); // 对于每对左子树和右子树的组合，创建一个新的根节点 i，并将这些左子树和右子树组合成一个完整的树。
                    currTree.left = left;
                    currTree.right = right;
                    allTrees.add(currTree);
                }
            }
        }
        return allTrees;
    }
}

