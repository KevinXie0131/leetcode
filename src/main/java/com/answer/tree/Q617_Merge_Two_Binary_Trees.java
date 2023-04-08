package com.answer.tree;

import com.template.TreeNode;

import java.util.*;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q617_Merge_Two_Binary_Trees {
    /**
     * 合并两个⼆叉树
     * 注意: 合并必须从两个树的根节点开始。
     *
     * 递归 前序遍历
     * 中序遍历也是可以的
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) { // 如果t1为空，合并之后就应该是t2
            return root2;
        }
        if (root2 == null) { // 如果t2为空，合并之后就应该是t1
            return root1;
        }
        TreeNode node = new TreeNode(root1.value+root2.value); // 中 重新定义新的节点，不修改原有两个树的结构
        node.left=mergeTrees(root1.left,  root2.left);                // 左
        node.right=mergeTrees(root1.right,  root2.right);             // 右
        return node;
    }
    /**
     *
     */
    public TreeNode mergeTrees1(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return null;
        }
        if (root1 == null && root2 != null) {
            return root2;
        }
        if (root2 == null && root1!=null) {
            return root1;
        }
        TreeNode node = new TreeNode(root1.value+root2.value);
        node.left=mergeTrees1(root1.left,  root2.left);
        node.right=mergeTrees1(root1.right,  root2.right);
        return node;
    }
    /**
     * 迭代法
     * 把两
     * 个树的节点同时加⼊队列
     */
    public TreeNode mergeTrees2(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root1);
        queue.offer(root2);
        while(!queue.isEmpty()){
            TreeNode r1 = queue.poll();
            TreeNode r2 = queue.poll();

            r1.value = r1.value + r2.value; // 此时两个节点⼀定不为空，val相加

            if(r1.left!=null && r2.left!=null){ // 如果两棵树左节点都不为空，加⼊队列
                queue.offer(r1.left);
                queue.offer(r2.left);
            }
            if(r1.right!=null && r2.right!=null){ // 如果两棵树右节点都不为空，加⼊队列
                queue.offer(r1.right);
                queue.offer(r2.right);
            }
            if(r1.left==null && r2.left!=null) { // 当t1的左节点 为空 t2左节点不为空，就赋值过去
                r1.left = r2.left;
            }
            if(r1.right==null && r2.right!=null) { // 当t1的右节点 为空 t2右节点不为空，就赋值过去
                r1.right = r2.right;
            }
        }

        return root1;
    }
}
