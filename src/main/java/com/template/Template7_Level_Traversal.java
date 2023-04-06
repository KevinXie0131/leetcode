package com.template;

import java.util.*;

public class Template7_Level_Traversal {

    static List<List<Integer>> resListRec = new ArrayList<List<Integer>>();
    static List<List<Integer>> resListRec1 = new ArrayList<List<Integer>>();
    static List<Integer> resListRec2 = new ArrayList<Integer>();
    static List<Integer> resListRec3 = new ArrayList<Integer>();
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

        dfsRecursionLevel(root,0);
        System.out.println(resListRec);

        dfsRecursionLevelAnother(root,0);
        System.out.println(resListRec1);

        Collections.reverse(resListRec1);
        System.out.println(resListRec1);

        dfsIteration(root);
        System.out.println(resListRec2);

        bfsIteration(root);
        System.out.println(resListRec3);

        bfsIterationLevel(root);
        System.out.println(resList);

    }

    //DFS--递归方式
    public static void dfsRecursionLevel(TreeNode node, Integer deep) {
        if (node == null) return;
        deep++;

        if (resListRec.size() < deep) {
            //当层级增加时，list的Item也增加，利用list的索引值进行层级界定
            List<Integer> item = new ArrayList<Integer>();
            resListRec.add(item);
        }
        resListRec.get(deep - 1).add(node.value);

        dfsRecursionLevel(node.left, deep);
        dfsRecursionLevel(node.right, deep);
    }

    public static void dfsRecursionLevelAnother(TreeNode node, Integer deep) {
        if (node == null) return;
     //   deep++;

        if (resListRec1.size() == deep) {
            //当层级增加时，list的Item也增加，利用list的索引值进行层级界定
            List<Integer> item = new ArrayList<Integer>();
            resListRec1.add(item);
        }
        resListRec1.get(deep).add(node.value);

        dfsRecursionLevelAnother(node.left, deep + 1);
        dfsRecursionLevelAnother(node.right, deep + 1);
    }

    //DFS--迭代方式--借助Stack
    public static void dfsIteration(TreeNode node) {
        if (node == null) return;

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            TreeNode tmpNode = stack.pop();
            resListRec2.add(tmpNode.value);

            if (tmpNode.right != null) {
                stack.push(tmpNode.right);
            }
            if (tmpNode.left != null) {
                stack.push(tmpNode.left);
            }
        }
    }

    //BFS--迭代方式--借助Queue
    public static void bfsIteration(TreeNode node) {
        if (node == null) return;

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            TreeNode tmpNode = queue.poll();
            resListRec3.add(tmpNode.value);

            if (tmpNode.left != null) {
                queue.offer(tmpNode.left);
            }
            if (tmpNode.right != null) {
                queue.offer(tmpNode.right);
            }
        }
    }

    /**
     * ⼆叉树的层序遍历
     */
    //BFS--迭代方式--借助队列
    public static void bfsIterationLevel(TreeNode node) {
        if (node == null) return;

        Queue<TreeNode> que = new LinkedList<TreeNode>();
        que.offer(node);

        while (!que.isEmpty()) {
            List<Integer> itemList = new ArrayList<Integer>();
            /**
             * 这⾥⼀定要使⽤固定⼤⼩size，不要使⽤que.size()，因为que.size是不断变化的
             */
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
