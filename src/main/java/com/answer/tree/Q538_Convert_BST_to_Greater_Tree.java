package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q538_Convert_BST_to_Greater_Tree {
    /**
     * 把⼆叉搜索树转换为累加树（Greater Sum Tree）
     * 给出⼆叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater SumTree），使每个节点 node 的新值等于原树中⼤于或等于 node.val 的值之和
     *
     * 换⼀个⾓度来看，这就是⼀个有序数组[2, 5, 13]，求从后到前的累加数组，也就是[20, 18, 13]，是不是感觉这就简单了
     * 从树中可以看出累加的顺序是右中左，所以我们需要反中序遍历这个⼆叉树，然后顺序累加就可以了。
     * 需要定义⼀个全局变量pre，⽤来保存cur节点的前⼀个节点的数值
     */
    int pre = 0; // 记录前⼀个节点的数值
    public TreeNode convertBST(TreeNode root) {
        traversal(root);
        return root;
    }
    // 要右中左来遍历⼆叉树， 中节点的处理逻辑就是让cur的数值加上前⼀个节点的数值
    public void traversal(TreeNode node){  // 右中左遍历. 不需要递归函数的返回值做什么操作了，要遍历整棵树
        if(node == null) return;

        traversal(node.right); // 右

        node.value += pre; // 中  按右中左顺序遍历，累加即可
        pre = node.value;

        traversal(node.left); // 左
    }
    /**
     * 迭代法 中序模板题(右中左)
     */
    int pre1 = 0; // 记录前⼀个节点的数值
    public TreeNode convertBST1(TreeNode root) {
        TreeNode cur = root;
        Deque<TreeNode> stack = new ArrayDeque<>();
        while(cur != null || !stack.isEmpty()){
            while(cur != null){
                stack.push(cur);
                cur = cur.right; // 右
            }
            TreeNode node = stack.pop();

            node.value += pre; // 中
            pre1= node.value;

            cur = node.left; // 左  是node.left
        }
        return root;
    }
}
