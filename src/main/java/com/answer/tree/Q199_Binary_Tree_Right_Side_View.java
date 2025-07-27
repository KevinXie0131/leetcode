package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q199_Binary_Tree_Right_Side_View {
    /**
     * 二叉树的右视图
     * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
     * Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
     */
    /**
     * 层序遍历的时候，判断是否遍历到单层的最后面的元素，如果是，就放进result数组中，随后返回result就可以了。
     * 解法：队列，迭代。
     *  每次返回每层的最后一个字段即可。
     * 小优化：每层右孩子先入队。代码略。
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // 广度优先遍历每一层节点，用队列存储每一层节点值，每一层节点出队时该层节点最后一个元素即为右视图的元素
        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size > 0) {
                TreeNode cur = queue.poll();
                if (size == 1) {  //将每⼀层的最后元素放⼊result数组中
                    list.add(cur.value);
                }

                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);

                size--;
            }
        }
        return list;
    }
}
