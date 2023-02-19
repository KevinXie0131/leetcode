package com.answer.greedy;

import com.template.TreeNode;

public class Q968_Binary_Tree_Cameras {

    /**
     * Approach 2: Greedy
     * If a node has children that are not covered by a camera, then we must place a camera here.
     * Additionally, if a node has no parent and it is not covered, we must place a camera here.
     */
    int result = 0;

    public int minCameraCover(TreeNode root) {
        if(dfs(root) == 0){
            result++;
        }
        return result;
    }

    public int dfs(TreeNode root){
        if(root == null) return 2;

        int left = dfs(root.left);
        int right = dfs(root.right);

        if(right == 2 && left == 2){
            return 0;
        }
        if(right == 0 || left == 0){
            result++;
            return 1;
        }
        if(right == 1 || left == 1){
            return 2;
        }
        return -1;
    }
}
