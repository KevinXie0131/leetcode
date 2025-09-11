package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class IterativeTraversalTemplate1 {
    /**
     * 二叉树的前序遍历 (迭代法) 模板1
     */
    public List<Integer> preorderTraversal_1(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        List<Integer> list = new ArrayList<>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                list.add(root.value); //一开始就记录节点值
                stack.push(root);
                root = root.left; //找到中间节点 和 左节点
            }

            TreeNode cur = stack.pop();
            root = cur.right; //最后再找 右节点
        }
        return list;
    }
    /**
     * 二叉树的前序遍历 (迭代法) 模板2
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> returnList = new ArrayList<Integer>();
        if(root == null){
            return returnList;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while(!stack.isEmpty()){
            TreeNode node = stack.pop(); // 中
            returnList.add(node.value);

            if(node.right != null){  // 右（空节点不入栈）
                stack.push(node.right);
            }
            if(node.left != null){   // 左（空节点不入栈）
                stack.push(node.left);
            }
        }
        return returnList;
    }
}
