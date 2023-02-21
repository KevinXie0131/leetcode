package com.learn;

import com.template.TreeNode;

public class TestTree_1 {
    /**
     * Solve Tree Problems Recursively
     * "Top-down" Solution
     * "Bottom-up" Solution
     */
    /**
     * "Top-down" Solution
     *
     * 1. return specific value for null node
     * 2. update the answer if needed                      // answer <-- params
     * 3. left_ans = top_down(root.left, left_params)      // left_params <-- root.val, params
     * 4. right_ans = top_down(root.right, right_params)   // right_params <-- root.val, params
     * 5. return the answer if needed                      // answer <-- left_ans, right_ans
     */
    /**
     * maximum_depth(root, depth):
     *
     * 1. return if root is null
     * 2. if root is a leaf node:
     * 3.     answer = max(answer, depth)         // update the answer if needed
     * 4. maximum_depth(root.left, depth + 1)     // call the function recursively for left child
     * 5. maximum_depth(root.right, depth + 1)    // call the function recursively for right child
     */
    private int answer; // don't forget to initialize answer before call maximum_depth
    private void maximum_depth(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            answer = Math.max(answer, depth);
        }
        maximum_depth(root.left, depth + 1);
        maximum_depth(root.right, depth + 1);
    }
    /**
     * "Bottom-up" Solution - regarded as a kind of postorder traversal
     *
     * 1. return specific value for null node
     * 2. left_ans = bottom_up(root.left)      // call function recursively for left child
     * 3. right_ans = bottom_up(root.right)    // call function recursively for right child
     * 4. return answers                       // answer <-- left_ans, right_ans, root.val
     */
    /**
     * maximum_depth(root):
     *
     * 1. return 0 if root is null                 // return 0 for null node
     * 2. left_depth = maximum_depth(root.left)
     * 3. right_depth = maximum_depth(root.right)
     * 4. return max(left_depth, right_depth) + 1  // return depth of the subtree rooted at root
     */
    public int maximum_depth(TreeNode root) {
        if (root == null) {
            return 0;                                   // return 0 for null node
        }
        int left_depth = maximum_depth(root.left);
        int right_depth = maximum_depth(root.right);
        return Math.max(left_depth, right_depth) + 1;   // return depth of the subtree rooted at root
    }
}
