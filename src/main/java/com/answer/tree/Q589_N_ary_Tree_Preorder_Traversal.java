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
     * 迭代
     */
    public List<Integer> preorder1(Node root) {
        List list = new LinkedList();
        if(root == null) return list;
        Deque<Node> stack = new ArrayDeque<>();

        stack.push(root);

        while(!stack.isEmpty()){
            Node node = stack.pop();
            list.add(node.val);

            List<Node> nodes = node.children;
            for(int i = nodes.size() - 1; i >= 0; i--){
                stack.push(nodes.get(i));
            }
        }
        return list;
    }
}
