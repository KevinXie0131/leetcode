package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q94_Binary_Tree_Inorder_Traversal {
    /**
     * 中序遍历（迭代法）
     * 中序遍历是左中右，先访问的是二叉树顶部的节点，然后一层一层向下访问，直到到达树左面的最底部，再开始处理节点（也就是在把节点的数值放进result数组中），
     * 这就造成了处理顺序和访问顺序是不一致的。
     *
     * 那么在使用迭代法写中序遍历，就需要借用指针的遍历来帮助访问节点，栈则用来处理节点上的元素。
     */
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(root == null)
            return result;

        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        //define a pointer to track nodes
        TreeNode cur = root;

        while(!stack.isEmpty() || cur != null){
            // if it is not null, push to stack and go down the tree to left
            if(cur != null){ // 指针来访问节点，访问到最底层
                stack.push(cur);  // 将访问的节点放进栈
                cur = cur.left;  // 左
                // if no left child pop stack, process the node then let p point to the right
            }else{
                TreeNode node = stack.pop();  // 从栈里弹出的数据，就是要处理的数据（放进result数组里的数据）
                result.add(node.value);  // 中
                cur = node.right;    // 右
            }
        }
        return result;
    }
    /**
     * 遍历 (迭代法) 模板
     * 记录节点值的位置不同
     */
    public List<Integer> inorderTraversal_1(TreeNode node) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            TreeNode cur = stack.pop();
            list.add(cur.value); //这里记录节点值
            node = cur.right;
        }
        return list;
    }
}
