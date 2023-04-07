package com.answer.tree;

import com.template.TreeNode;

import java.util.Arrays;
import java.util.HashMap;

public class Q105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal {
    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        TreeNode root = buildTree_1(preorder, inorder);
        System.out.println(root);
    }
    /**
     * 根据⼀棵树的前序遍历与中序遍历构造⼆叉树
     *
     * 前序和中序可以唯⼀确定⼀颗⼆叉树。
     * 后序和中序可以唯⼀确定⼀颗⼆叉树。
     * 那么前序和后序可不可以唯⼀确定⼀颗⼆叉树呢？
     *    前序和后序不能唯⼀确定⼀颗⼆叉树！，因为没有中序遍历⽆法确定左右部分，也就是⽆法分割
     */
    int pre_idx;
    int[] preorder;
    int[] inorder;
    HashMap<Integer, Integer> idx_map = new HashMap<Integer, Integer>();

    public TreeNode helper(int in_left, int in_right) {
        // if there is no elements to construct subtrees
        if (in_left > in_right)
            return null;

        // pick up post_idx element as a root
        int root_val = preorder[pre_idx];
        TreeNode root = new TreeNode(root_val);

        // root splits inorder list
        // into left and right subtrees
        int index = idx_map.get(root_val);

        // recursion
        pre_idx++;
        // build left subtree
        root.left = helper(in_left, index - 1);
        // build right subtree
        root.right = helper(index + 1, in_right);

        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;
        // start from the last postorder element
        pre_idx = 0;

        // build a hashmap value -> its index
        int idx = 0;
        for (Integer val : inorder)
            idx_map.put(val, idx++);
        return helper(0, inorder.length - 1);
    }

    public static TreeNode buildTree_1(int[] preorder, int[] inorder) {

        if(inorder.length == 0){ // 第⼀步
            return null;
        }
        int inorderRoot = -1; // 第三步：找切割点
        for (int i = 0; i < inorder.length; i++) {
            if (preorder[0] == inorder[i]) {
                inorderRoot = i;
                break;
            }
        }
        TreeNode root = new TreeNode(preorder[0]);

        root.right = buildTree_1(Arrays.copyOfRange(preorder, inorderRoot + 1, preorder.length),
                Arrays.copyOfRange(inorder, inorderRoot + 1, inorder.length));

        root.left = buildTree_1(Arrays.copyOfRange(preorder, 1, inorderRoot + 1),
                Arrays.copyOfRange(inorder, 0, inorderRoot));

        return root;
    }
}
