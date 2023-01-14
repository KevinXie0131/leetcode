package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q145_Binary_Tree_Postorder_Traversal {

    public List<Integer> postorderTraversal(TreeNode node) {
        List<Integer> list =  new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                list.add(node.value);
                stack.push(node);
                node = node.right;
            }

            TreeNode cur = stack.pop();
            node = cur.left;
        }

        Collections.reverse(list);
        return list;
    }
}
