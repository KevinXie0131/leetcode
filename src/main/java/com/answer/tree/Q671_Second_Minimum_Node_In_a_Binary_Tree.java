package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q671_Second_Minimum_Node_In_a_Binary_Tree {
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
