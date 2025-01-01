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
        //BFS--迭代方式--借助队列
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
