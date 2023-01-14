package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q114_Binary_Tree_Preorder_Traversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> returnList = new ArrayList<Integer>();

        if(root == null)
            return returnList;

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);

        while(!stack.empty()){
            TreeNode n = stack.pop();
            returnList.add(n.value);

            if(n.right != null){
                stack.push(n.right);
            }
            if(n.left != null){
                stack.push(n.left);
            }

        }
        return returnList;
    }

    public List<Integer> preorderTraversal_1(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        List<Integer> list = new ArrayList<>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                list.add(root.value);
                stack.push(root);
                root = root.left;
            }

            TreeNode cur = stack.pop();
            root = cur.right;
        }

        return list;
    }
}
