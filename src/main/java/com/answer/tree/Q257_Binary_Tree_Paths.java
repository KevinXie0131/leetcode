package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q257_Binary_Tree_Paths {
    /**
     * 返回所有从根节点到叶⼦节点的路径。
     * 说明: 叶⼦节点是指没有⼦节点的节点
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>(); // 存放结果集的result
        if(root == null){
            return result;
        }

        List<String> path = new ArrayList<>(); // ，记录每⼀条路径的path
        dfs(root, path, result);
        return result;
    }
    /**
     * 前序遍历 + 回溯
     */
    public void dfs(TreeNode node, List<String> path, List<String> result)     {
        if(node.left == null && node.right == null){ // 这才到了叶⼦节点 终⽌处理逻辑
            path.add(node.value + "");

            StringBuffer sb = new StringBuffer();
            for(int i = 0; i < path.size() - 1; i++){ // 将path⾥记录的路径转为string格式
                sb.append(path.get(i)).append("->");
            }
            sb.append(path.get(path.size() - 1)); // 记录最后⼀个节点（叶⼦节点）
            result.add(sb.toString()); // 收集⼀个路径
            return;
        }
        // 中
        path.add(node.value + ""); // 因为是前序遍历，需要先处理中间节点，中间节点就是我们要记录路径上的节点，先放进path中
        // 递归前要加上判断语句，下⾯要递归的节点是否为空
        if(node.left != null){ // 左
            dfs(node.left, path, result);
            path.remove(path.size() - 1); // 回溯
        }

        if(node.right != null){ // 右
            dfs(node.right, path, result);
            path.remove(path.size() - 1); // 回溯
        }

    }
    /**
     *
     */
    public List<String> binaryTreePaths1(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        dfs1(root, "", res);
        return res;
    }
    public void dfs1(TreeNode node, String path, List<String> res ){
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            res.add(path + node.value);
            return;
        }
        dfs1(node.left, path + node.value + "->", res);
        dfs1(node.right, path + node.value + "->", res);

    }
    /**
     *
     */
    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<Object> stack = new Stack<>();

        stack.push(root);
        stack.push(root.value + "");
        while (!stack.isEmpty()) {

            String path = (String) stack.pop();
            TreeNode node = (TreeNode) stack.pop();

            if (node.left == null && node.right == null) {
                res.add(path);
            }

            if (node.right != null) {
                stack.push(node.right);
                stack.push(path + "->" + node.right.value);
            }

            if (node.left != null) {
                stack.push(node.left);
                stack.push(path + "->" + node.left.value);
            }
        }
        return res;
    }
    /**
     *
     */
    public List<String> binaryTreePaths3(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<Object> queue = new LinkedList<>();
        queue.add(root);
        queue.add(root.value + "");
        while (!queue.isEmpty()) {
            TreeNode node = (TreeNode) queue.poll();
            String path = (String) queue.poll();

            if (node.left == null && node.right == null) {
                res.add(path);
            }


            if (node.right != null) {
                queue.add(node.right);
                queue.add(path + "->" + node.right.value);
            }

            if (node.left != null) {
                queue.add(node.left);
                queue.add(path + "->" + node.left.value);
            }
        }
        return res;
    }
}
