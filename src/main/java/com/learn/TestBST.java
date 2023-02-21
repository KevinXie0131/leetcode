package com.learn;

import com.template.TreeNode;

public class TestBST {
    /**
     * A binary search tree (BST), a special form of a binary tree, satisfies the binary search property:
     *
     * The value in each node must be greater than (or equal to) any values stored in its left subtree.
     * The value in each node must be less than (or equal to) any values stored in its right subtree.
     *
     * inorder traversal in BST will be in ascending order
     */
    /**
     * Search in a BST
     */
    // Recursion
    public TreeNode searchBST(TreeNode root, int target) {
        if (root == null || root.value == target) {
            return root;
        }
        if (target < root.value) {
            return searchBST(root.left, target);
        }
        return searchBST(root.right, target);
    }
    // Iteration
    public TreeNode searchBST_1(TreeNode root, int target) {
        TreeNode cur = root;
        while (cur != null && cur.value != target) {
            if (target < cur.value) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return cur;
    }
    /**
     * Insertion in a BST
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);   // return a new node if root is null
        }
        if (root.value < val) {           // insert to the right subtree if val > root->val
            root.right = insertIntoBST(root.right, val);
        } else {                        // insert to the left subtree if val <= root->val
            root.left = insertIntoBST(root.left, val);
        }
        return root;
    }
    /**
     * Deletion in a BST
     *
     * 1. If the target node has no child, we can simply remove the node.
     * 2. If the target node has one child, we can use its child to replace itself.
     * 3. If the target node has two children, replace the node with its in-order successor or predecessor node and delete that node.
     */
    private TreeNode findSuccessor(TreeNode root) {
        TreeNode cur = root.right;
        while (cur != null && cur.left != null) {
            cur = cur.left;
        }
        return cur;
    }
    public TreeNode deleteNode(TreeNode root, int key) {
        // return null if root is null
        if (root == null) {
            return root;
        }

        // delete current node if root is the target node
        if (root.value == key) {
            // replace root with root->right if root->left is null
            if (root.left == null) {
                return root.right;
            }

            // replace root with root->left if root->right is null
            if (root.right == null) {
                return root.left;
            }

            // replace root with its successor if root has two children
            TreeNode p = findSuccessor(root);
            root.value = p.value;
            root.right = deleteNode(root.right, p.value);
            return root;
        }
        if (root.value < key) {
            // find target in right subtree if root->val < key
            root.right = deleteNode(root.right, key);
        } else {
            // find target in left subtree if root->val > key
            root.left = deleteNode(root.left, key);
        }
        return root;
    }
    /**
     * Kth Largest Element in a Stream
     */
    /**
     // insert a node into the BST
     private Node insertNode(Node root, int num) {
     if (root == null) {
     return new Node(num, 1);
     }
     if (root.val < num) {
     root.right = insertNode(root.right, num);
     } else {
     root.left = insertNode(root.left, num);
     }
     root.cnt++;
     return root;
     }

     private int searchKth(Node root, int k) {
     // m = the size of right subtree
     int m = root.right != null ? root.right.cnt : 0;
     // root is the m+1 largest node in the BST
     if (k == m + 1) {
     return root.val;
     }
     if (k <= m) {
     // find kth largest in the right subtree
     return searchKth(root.right, k);
     } else {
     // find (k-m-1)th largest in the left subtree
     return searchKth(root.left, k - m - 1);
     }
     }

     private Node root;
     private int m_k;

     public KthLargest(int k, int[] nums) {
     root = null;
     for (int i = 0; i < nums.length; ++i) {
     root = insertNode(root, nums[i]);
     }
     m_k = k;
     }

     public int add(int val) {
     root = insertNode(root, val);
     return searchKth(root, m_k);
     }
     */
}
