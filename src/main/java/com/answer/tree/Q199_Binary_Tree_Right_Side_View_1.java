package com.answer.tree;

import com.template.TreeNode;
import java.util.*;

public class Q199_Binary_Tree_Right_Side_View_1 {

    List<Integer> list = new ArrayList<>();

    public List<Integer> rightSideView(TreeNode root) {
        dfs(root, 0);
        return list;
    }
    /**
     * 递归
     * 先递归右子树，再递归左子树，当某个深度首次到达时，对应的节点就在右视图中。
     */
    public void dfs(TreeNode root, int depth) {
        if (root == null) {
            return;
        }

        if (depth == list.size()) {  // 这个深度首次遇到
            list.add(root.value);
        }

        dfs(root.right, depth + 1);  // 先递归右子树，保证首次遇到的一定是最右边的节点
        dfs(root.left, depth + 1);
    }
}
