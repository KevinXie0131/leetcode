package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q106_Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal {
    /**
     * 根据⼀棵树的中序遍历与后序遍历构造⼆叉树
     * 以后序数组的最后⼀个元素为切割点，先切中序数组，根据中序数组，反过来在切后序数组。⼀层⼀层切下去，每次后序数组最后⼀个元素就是节点元素
     *
     * 递归
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {

        if(inorder.length == 0){ // 第⼀步
            return null;
        }
        int inorderRoot = -1; // 第三步：找切割点
        for (int i = 0; i < inorder.length; i++) {
            if (postorder[postorder.length-1] == inorder[i]) {
                inorderRoot = i;
                break;
            }
        }
        TreeNode root = new TreeNode(postorder[postorder.length-1]); // 第⼆步：后序遍历数组最后⼀个元素，就是当前的中间节点
        // 第四步：切割中序数组，得到 中序左数组和中序右数组
        root.right = buildTree(Arrays.copyOfRange(inorder, inorderRoot + 1, inorder.length),
                Arrays.copyOfRange(postorder, inorderRoot, postorder.length -1));
        // 第五步：切割后序数组，得到 后序左数组和后序右数组
        root.left = buildTree(Arrays.copyOfRange(inorder, 0, inorderRoot),
                Arrays.copyOfRange(postorder, 0, inorderRoot));
        // ⾸先后序数组的最后⼀个元素指定不能要了，这是切割点 也是 当前⼆叉树中间节点的元素，已经⽤了
        // 中序数组⼤⼩⼀定是和后序数组的⼤⼩相同的（这是必然）
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
