package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q145_Binary_Tree_Postorder_Traversal {
    /**
     * 后序遍历
     * 遍历 (迭代法) 模板
     */
    public List<Integer> postorderTraversal(TreeNode node) {
        List<Integer> list =  new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                list.add(node.value); // 记录节点值
                stack.push(node);
                node = node.right; //右节点
            }

            TreeNode cur = stack.pop();
            node = cur.left; // 左节点
        }

        Collections.reverse(list); //  结果反转 中-右-左 变成 左-右-中
        return list;
    }
}
