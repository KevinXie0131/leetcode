package com.answer.tree;

import java.util.*;

public class Q559_Maximum_Depth_of_N_ary_Tree {
    /**
     * Recursion 递归法
     * refer to Q104_Maximum_Depth_of_Binary_Tree
     */
    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        int max = 0;
        for(Node child : root.children) {
            int depth = maxDepth(child);
            if(depth > max){
                max = depth;  // depth = max (depth, maxDepth(root->children[i]));
            }
        }

        return max + 1;
    }
    /**
     * Iteration 迭代法
     */
    public int maxDepth_1(Node root) {
        int depth = 0;
        if (root == null) {
            return depth;
        }

        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size > 0) {
                Node cur = queue.poll();

                for(Node child : cur.children) {
                    if (child != null) {
                        queue.offer(child);
                    }
                }
                size--;
            }
            depth++; // 记录深度
        }
        return depth;
    }
}
