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
     *
     * 对于二叉树中的任意节点 x，x 的值不大于以 x 为根的子树中所有节点的值 -> 二叉树根节点的值即为所有节点中的最小值
     * 我们只需要通过遍历，找出严格大于 rootvalue 的最小值，即为「所有节点中的第二小的值」。
     */
    static long secondMin = Long.MAX_VALUE;
    static long firstMin = 0;

    public static int findSecondMinimumValue(TreeNode root) {
        firstMin = root.value;
        dfs(root);
        return  secondMin < Long.MAX_VALUE ? (int) secondMin : -1;
    }

    public static void dfs(TreeNode root){
        if(root == null) return;

        if(firstMin < root.value && root.value < secondMin){
            secondMin = root.value;
        }

        dfs(root.left);
        dfs(root.right);
    }
    /**
     * Approach #1: Brute Force
     */
    static Set<Integer> uniques = new TreeSet<>();

    public  static int findSecondMinimumValue_1(TreeNode root) {
        dfs0(root);
        List<Integer> sortedList = new ArrayList<>(uniques);
        Collections.sort(sortedList);
        if(sortedList.size() < 2){
            return -1;
        }
        return sortedList.get(1);
    }

    public static  void dfs0(TreeNode root){
        if (root != null) {
            uniques.add(root.value);
            dfs0(root.left);
            dfs0(root.right);
        }
    }
    /**
     * 使用TreeSet
     */
    public int findSecondMinimumValue_2(TreeNode root) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        dfs1(root, treeSet);
        if(treeSet.size() < 2){
            return -1;
        }
        treeSet.pollFirst();
        return treeSet.pollFirst();
    }

    public void dfs1(TreeNode root, TreeSet<Integer> treeSet){
        if (root != null) {
            treeSet.add(root.value);
            dfs1(root.left, treeSet);
            dfs1(root.right, treeSet);
        }
    }
    /**
     * 根据题目对特殊二叉树的定义，对于节点root来说，root.val为整棵二叉树中最小的值，其左右子节点的val值不可能比root小，因此只需要求出左右子树中的最小值或次小值比较返回即可。
     *  若子节点的val不等于root.val，直接返回子节点的val不需要继续遍历该子树，因为该子树的其余节点不可能成为候选点。
     *  若子节点的val等于root.val， 递归调用函数求出次小点。
     * 递归中止条件：碰到叶子节点直接返回-1。
     *
     * 按照上面规则求出左右子树的对应的left和right值后进行比较：
     *  left=-1&&right=-1 => return -1
     *  left=-1 => return right
     *  right=-1 => return left
     *  else => return min(left, right)
     * 这样可以在非极端情况下少些遍历
     */
    public int findSecondMinimumValue3(TreeNode root) {
        if (root.left == null || root.right == null) return -1;
        // 第二小的值存在于左右子树不同于当前节点的最小值
        int left = root.left.value == root.value ? findSecondMinimumValue3(root.left) : root.left.value;
        int right = root.right.value == root.value ? findSecondMinimumValue3(root.right) : root.right.value;
        if (left == -1 && right == -1) {
            return -1;
        } else if (left == -1) {
            return right;
        } else if (right == -1) {
            return left;
        } else {
            return Math.min(left, right);
        }
    }
}
