package com.template;

import java.util.*;

public class Template7_Level_Traversal {

    static List<List<Integer>> resListRec = new ArrayList<List<Integer>>();
    static List<List<Integer>> resListRec1 = new ArrayList<List<Integer>>();
    static List<List<Integer>> resList = new ArrayList<List<Integer>>();

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);

        root.setLeft(node2);
        root.setRight(node5);
        node2.setLeft(node3);
        node2.setRight(node4);
        node5.setLeft(node6);
        node5.setRight(node7);

        dfsRecursion(root,0);
        System.out.println(resListRec);

        dfsRecursionAnother(root,0);
        System.out.println(resListRec1);

        bfsIteration(root);
        System.out.println(resList);

    }

    //DFS--递归方式
    public static void dfsRecursion(TreeNode node, Integer deep) {
        if (node == null) return;
        deep++;

        if (resListRec.size() < deep) {
            //当层级增加时，list的Item也增加，利用list的索引值进行层级界定
            List<Integer> item = new ArrayList<Integer>();
            resListRec.add(item);
        }
        resListRec.get(deep - 1).add(node.value);

        dfsRecursion(node.left, deep);
        dfsRecursion(node.right, deep);
    }

    public static void dfsRecursionAnother(TreeNode node, Integer deep) {
        if (node == null) return;
     //   deep++;

        if (resListRec1.size() == deep) {
            //当层级增加时，list的Item也增加，利用list的索引值进行层级界定
            List<Integer> item = new ArrayList<Integer>();
            resListRec1.add(item);
        }
        resListRec1.get(deep).add(node.value);

        dfsRecursionAnother(node.left, deep + 1);
        dfsRecursionAnother(node.right, deep + 1);
    }

    //BFS--迭代方式--借助队列
    public static void bfsIteration(TreeNode node) {
        if (node == null) return;

        Queue<TreeNode> que = new LinkedList<TreeNode>();
        que.offer(node);

        while (!que.isEmpty()) {
            List<Integer> itemList = new ArrayList<Integer>();
            int len = que.size();

            while (len > 0) {
                TreeNode tmpNode = que.poll();
                itemList.add(tmpNode.value);

                if (tmpNode.left != null) que.offer(tmpNode.left);
                if (tmpNode.right != null) que.offer(tmpNode.right);
                len--;
            }

            resList.add(itemList);
        }

    }
}
