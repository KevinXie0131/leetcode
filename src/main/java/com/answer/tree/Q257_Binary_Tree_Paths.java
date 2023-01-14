package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q257_Binary_Tree_Paths {

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if(root == null){
            return result;
        }

        List<String> path = new ArrayList<>();
        dfs(root, path, result);
        return result;
    }

    public void dfs(TreeNode node, List<String> path, List<String> result)     {
        if(node.left == null && node.right == null){
            path.add(node.value + "");

            StringBuffer sb = new StringBuffer();
            for(int i = 0; i < path.size() - 1; i++){
                sb.append(path.get(i)).append("->");
            }
            sb.append(path.get(path.size() - 1));
            result.add(sb.toString());
            return;
        }

        path.add(node.value + "");

        if(node.left != null){
            dfs(node.left, path, result);
            path.remove(path.size() - 1);
        }

        if(node.right != null){
            dfs(node.right, path, result);
            path.remove(path.size() - 1);
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
