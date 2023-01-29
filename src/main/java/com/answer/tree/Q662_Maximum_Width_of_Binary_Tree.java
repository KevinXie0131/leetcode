package com.answer.tree;

import com.template.TreeNode;

import java.util.*;
import java.util.LinkedList;

public class Q662_Maximum_Width_of_Binary_Tree {
    public static void main(String[] args) {
        // [1,3,2,5,null,null,9,6,null,7]
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(9);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(7);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node2.right = node4;
        node3.left = node5;
        node4.left = node6;

        System.out.println(widthOfBinaryTree_0( root));
    }

    /**
     * doesn't work for [1,3,2,5,null,null,9,6,null,7]
     */
    public static int widthOfBinaryTree(TreeNode root) {
        int res = 0;
        if (root == null) {
            return 0;
        }

        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();

            List<TreeNode> list = new LinkedList<>();
            while (size > 0) {
                TreeNode cur = queue.poll();
                list.add(cur);
                if(cur != null){
                    queue.offer(cur.left);
                    queue.offer(cur.right);
                }
                size--;
            }
            int i = 0, j = list.size() - 1;
            while(i <= j){
                if(list.get(i) == null){
                    i++;
                }
                if(list.get(j) == null){
                    j--;
                }
                if(list.get(i) != null && list.get(j) != null){
                    res = Math.max(j - i + 1, res);
                    break;
                }
            }
        }
        return res;
    }
    /**
     * Improved and works, but it has Time Limit Exceeded
     */
    public static int widthOfBinaryTree_0(TreeNode root) {
        int res = 0;
        if (root == null) {
            return 0;
        }

        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size(), start = -1, end = -1;

            for(int i = 0; i < size; i++){
                TreeNode cur = queue.poll();
                if(cur.value != Integer.MAX_VALUE && start == -1) start = i;
                if(cur.value != Integer.MAX_VALUE) end = i;

                queue.offer(cur.left == null ? new TreeNode(Integer.MAX_VALUE, null, null) : cur.left);
                queue.offer(cur.right == null ? new TreeNode(Integer.MAX_VALUE, null, null) : cur.right);
            }
            if(start == -1) break;
            res = Math.max(res, end - start + 1);
        }
        return res;
    }
}
