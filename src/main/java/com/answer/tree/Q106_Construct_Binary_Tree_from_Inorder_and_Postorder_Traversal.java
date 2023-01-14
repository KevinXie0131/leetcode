package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q106_Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal {
    public TreeNode buildTree(int[] inorder, int[] postorder) {

        if(inorder.length == 0){
            return null;
        }
        int inorderRoot = -1;
        for (int i = 0; i < inorder.length; i++) {
            if (postorder[postorder.length-1] == inorder[i]) {
                inorderRoot = i;
                break;
            }
        }
        TreeNode root = new TreeNode(postorder[postorder.length-1]);

        root.right = buildTree(Arrays.copyOfRange(inorder, inorderRoot + 1, inorder.length),
                Arrays.copyOfRange(postorder, inorderRoot, postorder.length -1));

        root.left = buildTree(Arrays.copyOfRange(inorder, 0, inorderRoot),
                Arrays.copyOfRange(postorder, 0, inorderRoot));

        return root;
    }
    /**
     *
     */
    int post_idx;
    int[] postorder;
    int[] inorder;
    HashMap<Integer, Integer> idx_map = new HashMap<Integer, Integer>();

    public TreeNode helper(int in_left, int in_right) {
        // if there is no elements to construct subtrees
        if (in_left > in_right)
            return null;

        // pick up post_idx element as a root
        int root_val = postorder[post_idx];
        TreeNode root = new TreeNode(root_val);

        // root splits inorder list
        // into left and right subtrees
        int index = idx_map.get(root_val);

        // recursion
        post_idx--;
        // build right subtree
        root.right = helper(index + 1, in_right);
        // build left subtree
        root.left = helper(in_left, index - 1);
        return root;
    }

    public TreeNode buildTree1(int[] inorder, int[] postorder) {
        this.postorder = postorder;
        this.inorder = inorder;
        // start from the last postorder element
        post_idx = postorder.length - 1;

        // build a hashmap value -> its index
        int idx = 0;
        for (Integer val : inorder)
            idx_map.put(val, idx++);
        return helper(0, inorder.length - 1);
    }
}
