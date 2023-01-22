package com.answer.tree;

import com.template.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class Q173_Binary_Search_Tree_Iterator_1 {
    /**
     * Approach 2: Controlled Recursion
     */
    private TreeNode cur;
    private Deque<TreeNode> stack;

    public Q173_Binary_Search_Tree_Iterator_1(TreeNode root) {
        cur = root;
        stack = new ArrayDeque<>();
    }

    public int next() {
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        int result;
        cur = stack.pop();
        result = cur.value;
        cur = cur.right;
        return result;
    }

    public boolean hasNext() {
        return cur != null || !stack.isEmpty();
    }

}
