package com.answer.tree;

import com.template.TreeNode;
import org.apache.commons.lang3.tuple.Pair;

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

        System.out.println(widthOfBinaryTree_4( root));
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
    /**
     * Improved and works
     */
    public static int widthOfBinaryTree_0a(TreeNode root) {
        int res = 0;
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(new TreeNode(1, root.left, root.right));
        while (!queue.isEmpty()) {
            int size = queue.size(), start = -1, end = -1;

            for(int i = 0; i < size; i++){
                TreeNode cur = queue.poll();
                end = cur.value;
                if(start == -1) start = cur.value;
                if(cur.left != null) queue.offer(new TreeNode(cur.value * 2, cur.left.left, cur.left.right));
                if(cur.right != null) queue.offer(new TreeNode(cur.value * 2 + 1, cur.right.left, cur.right.right));
            }
            res = Math.max(res, end - start + 1);
        }
        return res;
    }
    /**
     * Improved and works
     */
    public static int widthOfBinaryTree_1(TreeNode root) {
        int res = 0;
        if (root == null) {
            return 0;
        }
        List<NodePair> list = new LinkedList<>();
        list.add(new NodePair(root, 1) );
        while (!list.isEmpty()) {
            List<NodePair> temp = new LinkedList<>();

            for(int i = 0; i < list.size(); i++){
                TreeNode cur = list.get(i).node;
                int value = list.get(i).val;
                if(cur.left != null){
                    temp.add(new NodePair(cur.left, value * 2 + 1) );
                }
                if(cur.right != null){
                    temp.add(new NodePair(cur.right, value * 2 + 2) );
                }
            }
            res = Math.max(res, list.get(list.size() - 1).val - list.get(0).val + 1);

            list = temp;
        }
        return res;
    }
    /**
     * Recursion
     */
    public static int widthOfBinaryTree_4(TreeNode root) {
        // 给每个节点一个编号（索引）
        // 按满二叉树来算，如果一个节点的索引为 x，那么它左右子节点的索引分别是 2x 和 2x+1
        int ans = 0;

        // 记录每个层级最小和最大的编号
        // 也可以只记录最小编号，边遍历边计算结果
        List<int[]> list = new ArrayList<>();
        dfs(list, root, 1, 0);

        for (int[] arr : list) {
            ans = Math.max(ans, arr[1] - arr[0] + 1);
        }

        return ans;
    }

    private static void dfs(List<int[]> list, TreeNode node, int index, int level) {
        if (node == null) {
            return;
        }

        if (list.size() <= level) {
            list.add(new int[] {index, index});
        } else {
            list.get(level)[1] = index;
        }

        dfs(list, node.left, 2 * index, level + 1);
        dfs(list, node.right, 2 * index + 1, level + 1);
    }
}

class NodePair {
    TreeNode node;
    Integer val;

    public NodePair(TreeNode node, Integer val) {
        this.node = node;
        this.val = val;
    }
}
