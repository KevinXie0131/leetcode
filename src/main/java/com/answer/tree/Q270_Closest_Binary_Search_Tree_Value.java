package com.answer.tree;

import com.template.TreeNode;

public class Q270_Closest_Binary_Search_Tree_Value {
    public static void main(String[] args) {
          //[2,1,3]
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.left = node2;
        node1.right = node3;
        System.out.println(closestValue(node1, 0.142857));
    }

    static int res = Integer.MAX_VALUE;

    public static int closestValue(TreeNode root, double target) {
        double closest = Integer.MAX_VALUE;
        dfs(root, target, closest);
        return res;
    }

    public static void dfs(TreeNode root, double target, double closest){
        if(root == null) {
            return;
        }

        dfs(root.left, target, closest);

        double absVal = Math.abs(target - (double)root.value);
        if(absVal < closest){
            closest = absVal;
            res = root.value;
        }

        dfs(root.right, target, closest);
    }

}
