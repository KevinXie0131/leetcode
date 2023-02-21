package com.learn;

import java.util.LinkedList;
import java.util.*;

public class TestNaryTree {
    /**
     * N-ary Tree Preorder Traversal
     * Approach 1: Iterations
     */
    public List<Integer> preorder(Node root) {
        LinkedList<Node> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }

        stack.add(root);
        while (!stack.isEmpty()) {
            Node node = stack.pollLast();
            output.add(node.val);
            Collections.reverse(node.children);
            for (Node item : node.children) {
                stack.add(item);
            }
        }
        return output;
    }
    /**
     * N-ary Tree Postorder Traversal
     * Approach 1: Iterations
     */
    public List<Integer> postorder(Node root) {
        LinkedList<Node> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }

        stack.add(root);
        while (!stack.isEmpty()) {
            Node node = stack.pollLast();
            output.addFirst(node.val);
            for (Node item : node.children) {
                if (item != null) {
                    stack.add(item);
                }
            }
        }
        return output;
    }
    /**
     * Classical Recursion Solution of N-ary Tree
     * 1. "Top-down" Solution
     *
     * 1. return specific value for null node
     * 2. update the answer if needed                              // answer <-- params
     * 3. for each child node root.children[k]:
     * 4.      ans[k] = top_down(root.children[k], new_params[k])  // new_params <-- root.val, params
     * 5. return the answer if needed                              // answer <-- all ans[k]
     */
    /**
     * 2. "Bottom-up" Solution
     *
     * 1. return specific value for null node
     * 2. for each child node root.children[k]:
     * 3.      ans[k] = bottom_up(root.children[k])    // call function recursively for all children
     * 4. return answer                                // answer <- root.val, all ans[k]
     */
}

// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
