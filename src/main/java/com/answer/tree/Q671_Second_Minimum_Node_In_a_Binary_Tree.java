package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q671_Second_Minimum_Node_In_a_Binary_Tree {
    /**
     * 二叉树中第二小的节点
     * 给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。
     * 更正式地说，即 root.val = min(root.left.val, root.right.val) 总成立。
     * 给出这样的一个二叉树，你需要输出所有节点中的 第二小的值 。
     * 如果第二小的值不存在的话，输出 -1 。
     * Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes. More formally, the property root.val = min(root.left.val, root.right.val) always holds.
     * Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.
     * If no such second minimum value exists, output -1 instead.
     */
    public static void main(String[] args) {
        //[2,2,5,null,null,5,7]
       /* TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(7);
        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;*/
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(2);
        node1.left = node2;
        node1.right = node3;

        System.out.println(findSecondMinimumValue(node1));
    }

    /**
     * 本题核心条件 :「root.val = min(root.left.val, root.right.val)」和「每个子节点数量要么是 0 要么是 2」。
     * 我们可以设计如下递归函数，含义为 从 root 为根的树进行搜索，找到值比 cur 大的最小数。然后使用全局变量 ans 存储答案
     */
    static int min = Integer.MAX_VALUE;
    static int oldMin = 0;
    public static int findSecondMinimumValue(TreeNode root) {
        oldMin = root.value;
        dfs(root, root.value);
        return  min < Integer.MAX_VALUE ? (int) min : -1;
    }
    public static void dfs(TreeNode root, int cur){
        if(root == null) return;

        if(root.value != cur){
            if(min == Integer.MAX_VALUE){
                min = root.value;
            }else{
                if(root.value < min && oldMin < root.value){
                    min = root.value;
                }
            }
        }

        dfs(root.left, cur);
        dfs(root.right, cur);
    }
    /**
     * Approach #1: Brute Force
     */
    static Set<Integer> uniques = new TreeSet<Integer>();

    public  static int findSecondMinimumValue_1(TreeNode root) {
        dfs(root);
        List<Integer> sortedList = new ArrayList<>(uniques);
        Collections.sort(sortedList);
        if(sortedList.size() < 2){
            return -1;
        }
        return sortedList.get(1);
    }
    public static  void dfs(TreeNode root){
        if (root != null) {
            uniques.add(root.value);
            dfs(root.left);
            dfs(root.right);
        }
    }
}
