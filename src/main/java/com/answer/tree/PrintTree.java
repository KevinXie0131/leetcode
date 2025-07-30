package com.answer.tree;

import com.template.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrintTree {
    // Function to find the height of the binary tree
    static int findHeight(TreeNode root) {
        if (root == null) {
            return -1;
        }

        int leftHeight = findHeight(root.left);
        int rightHeight = findHeight(root.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    // Helper function to perform inorder traversal and
    // populate the 2D matrix
    static void inorder(TreeNode root, int row, int col,
                        int height,
                        List<List<String>> ans) {
        if (root == null) {
            return;
        }

        // Calculate offset for child positions
        int offset = (int) Math.pow(2, height - row - 1);

        // Traverse the left subtree
        if (root.left != null) {
            inorder(root.left, row + 1, col - offset,
                    height, ans);
        }

        // Place the current node's value in the matrix
        ans.get(row).set(col, String.valueOf(root.value));

        // Traverse the right subtree
        if (root.right != null) {
            inorder(root.right, row + 1, col + offset,
                    height, ans);
        }
    }
    // Function to convert the binary tree to a 2D matrix
    static List<List<String>> treeToMatrix(TreeNode root) {

        // Find the height of the tree
        int height = findHeight(root);

        // Rows are height + 1; columns are 2^(height+1) - 1
        int rows = height + 1;
        int cols = (int) Math.pow(2, height + 1) - 1;

        // Initialize 2D matrix with empty strings
        List<List<String>> ans = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            List<String> row = new ArrayList<>(Collections
                    .nCopies(cols, ""));
            ans.add(row);
        }

        // Populate the matrix using inorder traversal
        inorder(root, 0, (cols - 1) / 2, height, ans);

        return ans;
    }
    // Function to print a 2D matrix
    static void print2DArray(List<List<String>> arr) {
        for (List<String> row : arr) {
            for (String cell : row) {
                if (cell.isEmpty()) {
                    System.out.print(" ");
                } else {
                    System.out.print(cell);
                }
            }
            System.out.println();
        }
    }
}
