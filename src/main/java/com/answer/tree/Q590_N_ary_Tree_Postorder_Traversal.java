package com.answer.tree;

import java.util.*;

public class Q590_N_ary_Tree_Postorder_Traversal {
    /**
     * N 叉树的后序遍历
     * 给定一个 n 叉树的根节点 root ，返回 其节点值的 后序遍历 。
     * n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
     * Given the root of an n-ary tree, return the postorder traversal of its nodes' values.
     * Nary-Tree input serialization is represented in their level order traversal. Each group of children is separated by the null value (See examples)
     */
    /**
     * 递归
     */
    public List<Integer> postorder(Node root) {
        List list = new LinkedList();

        recursion(root, list);

        return list;
    }

    private void recursion(Node root, List list){
        if(root == null) return;

        for(Node node : root.children){
            recursion(node, list);
        }
        list.add(root.val);
    }
    /**
     * 迭代（栈实现）
     * N叉树的后序遍历代码操作的顺序是，根节点->子节点（从左到右进栈，出来的顺序为右到左），对应得到的遍历结果就是 根节点+子节点（从右到左）。再把这个结果反转一下，遍历结果就是 子节点（从左到右）+ 根节点。
     */
    public List<Integer> postorder1(Node root) {
        LinkedList list = new LinkedList();
        Deque<Node> stack = new ArrayDeque<>();

        if(root == null) return list;
        stack.push(root);

        while(!stack.isEmpty()){
            Node node = stack.pop();
            list.add(node.val); // 用头插法进行反转，list.add(0, node.val) / list.addFirst(node.val); ，也可以在最后用函数反转

            List<Node> nodes = node.children;
            for(int i = 0; i <= nodes.size() - 1; i++){
                stack.push(nodes.get(i));
            }
        }
        Collections.reverse(list); // 利用前序遍历反转
        return list;
    }
}
