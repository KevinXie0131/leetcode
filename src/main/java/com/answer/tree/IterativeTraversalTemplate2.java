package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class IterativeTraversalTemplate2 {
    /**
     * 二叉树的后序遍历 (迭代法) 模板1
     */
    public List<Integer> postorderTraversal_1(TreeNode node) {
        List<Integer> list =  new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                list.add(node.value); // 记录节点值
                stack.push(node);
                node = node.right; //右节点（比前序遍历不同的地方）
            }

            TreeNode cur = stack.pop();
            node = cur.left; // 左节点（比前序遍历不同的地方）
        }
        Collections.reverse(list); //  结果反转 中-右-左 变成 左-右-中
        return list;
    }
    /**
     * 二叉树的后序遍历 (迭代法) 模板2
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> returnList = new ArrayList<Integer>();
        if(root == null){
            return returnList;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while(!stack.isEmpty()){
            TreeNode node = stack.pop(); // 中
            returnList.add(node.value);
            if(node.left != null){   // 左（空节点不入栈）
                stack.push(node.left);
            }
            if(node.right != null){  // 右（空节点不入栈）
                stack.push(node.right);
            }
        }
        Collections.reverse(returnList);  // 将结果反转之后就是左右中的顺序了
        return returnList;
    }
    /**
     * 二叉树的后序遍历 (迭代法) 模板3: 头插法
     */
    public List<Integer> postorderTraversal_1a(TreeNode node) {
        List<Integer> list =  new LinkedList<>();
        Deque <TreeNode> stack = new ArrayDeque<>();

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                list.add(0, node.value); // 头插法
                stack.push(node);
                node = node.right;//右节点（比前序遍历不同的地方）
            }

            TreeNode cur = stack.pop();
            node = cur.left; // 左节点（比前序遍历不同的地方）
        }
        return list;
    }
    /**
     * 二叉树的后序遍历 (迭代法) 模板4: 使用一个栈来模拟递归的后序遍历
     */
    public List<Integer> postorderTraversal_3(TreeNode root) {
        List<Integer> list =  new LinkedList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode pre = null;

        while (root != null || !stack.isEmpty()) {
            while (root != null) {  // 一路向左，将路径上的节点全部压入栈
                stack.push(root);
                root = root.left;
            }
            TreeNode cur = stack.peek();   // 查看栈顶节点
            if (cur.right == null || cur.right == pre) {// 如果右子树为空或右子树已经被访问过，则可以处理当前节点
                list.add(cur.value);   // 输出
                stack.pop();
                pre = cur;
                root = null;// 当前结点下，没有要遍历的结点了
            } else {
                root = cur.right;// 右结点还没遍历，遍历右结点
            }
        }
        return list;
    }
}
