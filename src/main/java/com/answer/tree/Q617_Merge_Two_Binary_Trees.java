package com.answer.tree;

import com.template.TreeNode;
import java.util.*;

public class Q617_Merge_Two_Binary_Trees {
    /**
     * 合并二叉树
     * 给你两棵二叉树： root1 和 root2 。
     * 想象一下，当你将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠（而另一些不会）。你需要将这两棵树合并成一棵新二叉树。合并的规则是：如果两个节点重叠，那么将这两个节点的值相加作为合并后节点的新值；否则，不为 null 的节点将直接作为新二叉树的节点。
     * 返回合并后的二叉树。
     * 注意: 合并过程必须从两个树的根节点开始。
     * You are given two binary trees root1 and root2.
     * Imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not. You need to merge the two trees into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of the new tree.
     * Return the merged tree.
     * Note: The merging process must start from the root nodes of both trees.
     */
    public static void main(String[] args) {
      //  root1 = [1,3,2,5]
      //  root2 = [2,1,3,null,4,null,7]
        TreeNode root1 = new TreeNode(1);
        TreeNode root1a = new TreeNode(3);
        TreeNode root1b = new TreeNode(2);
        TreeNode root1c = new TreeNode(5);
        TreeNode root2 = new TreeNode(2);
        TreeNode root2a = new TreeNode(1);
        TreeNode root2b = new TreeNode(3);
        TreeNode root2c = new TreeNode(4);
        TreeNode root2d = new TreeNode(7);
        root1.left = root1a;
        root1.right = root1b;
        root1a.left = root1c;
        root2.left = root2a;
        root2.right = root2b;
        root2a.right = root2c;
        root2b.right = root2d;

        TreeNode node = mergeTrees4(root1, root2);
        System.out.println(node);
    }
    /**
     * 合并两个⼆叉树
     * 注意: 合并必须从两个树的根节点开始。
     *
     * 递归 前序遍历是最好理解的
     * 中序遍历, 后序遍历也是可以的
     */
    public TreeNode mergeTrees0(TreeNode root1, TreeNode root2) {
        // 确定终止条件
        if(root2 == null) return root1; // 如果t1 == NULL，两个树合并就应该是t2（如果t2也为NULL也无所谓，合并之后就是NULL）
        if(root1 == null) return root2; // 如果t2 == NULL，那么两个数合并就是t1（如果t1也为NULL也无所谓，合并之后就是NULL）

        // 确定单层递归的逻辑
        // 修改了t1的数值和结构, 重复利用一下t1这个树，t1就是合并之后树的根节点（就是修改了原来树的结构）
        // 因为题目没有说不能改变树的值和结构
        root1.value += root2.value;                          // 中

        root1.left = mergeTrees(root1.left, root2.left);     // 左
        root1.right = mergeTrees(root1.right, root2.right);  // 右
        return root1;
    }
    /**
     * 中序遍历也是可以的
     */
    public TreeNode mergeTrees0a(TreeNode root1, TreeNode root2) {
        if(root2 == null) return root1;
        if(root1 == null) return root2;

        root1.left = mergeTrees(root1.left, root2.left);     // 左
        root1.value += root2.value;                          // 中
        root1.right = mergeTrees(root1.right, root2.right);  // 右
        return root1;
    }
    /**
     * 后序遍历依然可以
     */
    public TreeNode mergeTrees0b(TreeNode root1, TreeNode root2) {
        if(root2 == null) return root1;
        if(root1 == null) return root2;

        root1.left = mergeTrees(root1.left, root2.left);     // 左
        root1.right = mergeTrees(root1.right, root2.right);  // 右
        root1.value += root2.value;                          // 中
        return root1;
    }
    /**
     * 重新定义新的节点，不修改原有两个树的结构
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) { // 如果t1为空，合并之后就应该是t2
            return root2;
        }
        if (root2 == null) { // 如果t2为空，合并之后就应该是t1
            return root1;
        }
        TreeNode node = new TreeNode(root1.value + root2.value); // 中 重新定义新的节点，不修改原有两个树的结构
        node.left = mergeTrees(root1.left, root2.left);                // 左
        node.right = mergeTrees(root1.right, root2.right);             // 右
        return node;
    }
    /**
     * 递归 同上
     */
    public TreeNode mergeTrees1(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) { // 可以省略
            return null;
        }
        if (root1 == null && root2 != null) {
            return root2;
        }
        if (root2 == null && root1!=null) {
            return root1;
        }
        TreeNode node = new TreeNode(root1.value + root2.value);
        node.left = mergeTrees1(root1.left,  root2.left);
        node.right = mergeTrees1(root1.right,  root2.right);
        return node;
    }
    /**
     * 迭代法 使用队列迭代
     * 把两个树的节点同时加⼊队列
     * 广度优先搜索
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

            if(r1.left != null && r2.left != null){ // 如果两棵树左节点都不为空，加⼊队列
                queue.offer(r1.left);
                queue.offer(r2.left);
            }
            if(r1.right != null && r2.right != null){ // 如果两棵树右节点都不为空，加⼊队列
                queue.offer(r1.right);
                queue.offer(r2.right);
            }
            if(r1.left == null && r2.left != null) { // 当t1的左节点 为空 t2左节点不为空，就赋值过去
                r1.left = r2.left;
            }
            if(r1.right == null && r2.right != null) { // 当t1的右节点 为空 t2右节点不为空，就赋值过去
                r1.right = r2.right;
            }
        }
        return root1;
    }
    /**
     * 使用栈迭代
     */
    public static TreeNode mergeTrees4(TreeNode root1, TreeNode root2) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        if(root1 == null) return root2;
        if(root2 == null) return root1;
        stack.push(root2);
        stack.push(root1);
        while(!stack.isEmpty()){
            TreeNode node1 = stack.pop();
            TreeNode node2 = stack.pop();
            node1.value = node1.value + node2.value;

            if(node1.right != null && node2.right != null) {
                stack.push(node2.right);
                stack.push(node1.right);
            }
            if(node1.left != null && node2.left != null) {
                stack.push(node2.left);
                stack.push(node1.left);
            }
            if(node1.left == null && node2.left != null) {
                node1.left = node2.left;
            }
            if(node1.right == null && node2.right != null) {
                node1.right = node2.right;
            }
          /*  if (node2.right != null && node1.right != null) {
                stack.push(node2.right);
                stack.push(node1.right);
            } else if (node1.right == null) {
                    node1.right = node2.right;
                }
            }
            if (node2.left != null && node1.left != null) {
                stack.push(node2.left);
                stack.push(node1.left);
            } else if (node1.left == null) {
                    node1.left = node2.left;
                }
            }*/
        }
        return root1;
    }

}
