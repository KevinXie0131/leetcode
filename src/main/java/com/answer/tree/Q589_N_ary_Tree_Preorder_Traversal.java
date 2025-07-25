package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q589_N_ary_Tree_Preorder_Traversal {
    /**
     * N 叉树的前序遍历
     * 给定一个 n 叉树的根节点  root ，返回 其节点值的 前序遍历 。
     * n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
     * Given the root of an n-ary tree, return the preorder traversal of its nodes' values.
     * Nary-Tree input serialization is represented in their level order traversal. Each group of children is separated by the null value (See examples)
     */
    /**
     * 递归
     */
    public List<Integer> preorder(Node root) {
        List list = new LinkedList();

        recursion(root, list);

        return list;
    }

    private void recursion(Node root, List list){
        if(root == null) return;

        list.add(root.val);
        for(Node node : root.children){
            recursion(node, list);
        }
    }
    /**
     * 迭代 （栈实现）
     * N叉树的前序遍历代码操作的顺序是，根节点—>子节点（从右到左进栈，出来的顺序为左到右），对应得到的遍历结果就是 根节点+子节点（从左到右）。
     */
    public List<Integer> preorder1(Node root) {
        List list = new LinkedList();
        Deque<Node> stack = new ArrayDeque<>();

        if(root == null) return list;
        stack.push(root);  // 首先把根节点入栈

        while(!stack.isEmpty()){
            Node node = stack.pop();  // 当前栈顶节点出栈
            list.add(node.val);  // 将值加入列表

            List<Node> nodes = node.children;
            for(int i = nodes.size() - 1; i >= 0; i--){ // 对该节点的每个子节点按照从右到左的顺序依次入栈
                stack.push(nodes.get(i));
            }
        }
        return list;
    }
}
