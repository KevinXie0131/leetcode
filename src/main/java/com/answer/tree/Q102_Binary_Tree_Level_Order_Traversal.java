package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q102_Binary_Tree_Level_Order_Traversal {
    /**
     * 层序遍历
     * 需要借用一个辅助数据结构即队列来实现，队列先进先出，符合一层一层遍历的逻辑，而用栈先进后出适合模拟深度优先遍历也就是递归的逻辑。
     * 层序遍历方式就是图论中的广度优先遍历
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if (root == null) {
            return list;
        }
        // BFS--迭代方式--借助队列
        // 通过一个队列来辅助操作，队列中存储的是当前层的所有节点，每次循环把队列中的当前全部节点弹出，并把弹出节点的左右孩子再加入队列，
        // 这样当一次循环结束，就完成了当前层节点的遍历以及下层节点的入队。
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> sublist = new ArrayList<>();
            int size = queue.size();  // 这里一定要使用固定大小size，不要使用que.size()，因为que.size是不断变化的

            while (size > 0) {
                TreeNode cur = queue.poll();
                sublist.add(cur.value);

                if (cur.left != null) {queue.offer(cur.left);}
                if (cur.right != null) {queue.offer(cur.right);}

                size--;
            }
            list.add(sublist);
        }

        return list;
    }
}
