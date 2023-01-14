package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q94_Binary_Tree_Inorder_Traversal {

    public ArrayList<Integer> inorderTraversal(TreeNode root) {

        ArrayList<Integer> lst = new ArrayList<Integer>();

        if(root == null)
            return lst;

        Stack<TreeNode> stack = new Stack<TreeNode>();
        //define a pointer to track nodes
        TreeNode p = root;

        while(!stack.empty() || p != null){

            // if it is not null, push to stack and go down the tree to left
            if(p != null){
                stack.push(p);
                p = p.left;

                // if no left child pop stack, process the node then let p point to the right
            }else{
                TreeNode t = stack.pop();
                lst.add(t.value);
                p = t.right;
            }
        }

        return lst;
    }

    public List<Integer> inorderTraversal_1(TreeNode node) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            TreeNode cur = stack.pop();
            list.add(cur.value);
            node = cur.right;
        }


        return list;
    }
}
