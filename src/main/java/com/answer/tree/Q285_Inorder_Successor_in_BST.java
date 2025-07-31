package com.answer.tree;

import com.template.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q285_Inorder_Successor_in_BST {
    /**
     * 二叉搜索树中的中序后继
     * 给定一棵二叉搜索树（BST）的根节点 root 和树中的一个节点 p，找到 p 的中序后继节点，并返回。
     * 节点 p 的中序后继节点是值比 p.val 大的所有节点中键值最小的那个节点。
     * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
     * The successor of a node p is the node with the smallest key greater than p.val.
     */
    public static void main(String[] args) {
        // 构造如下BST:
        //      5
        //     / \
        //    3   6
        //   / \
        //  2   4
        // /
        //1
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2); n2.left = n1;
        TreeNode n4 = new TreeNode(4);
        TreeNode n3 = new TreeNode(3); n3.left = n2; n3.right = n4;
        TreeNode n6 = new TreeNode(6);
        TreeNode n5 = new TreeNode(5); n5.left = n3; n5.right = n6;
        Q285_Inorder_Successor_in_BST solution = new Q285_Inorder_Successor_in_BST();
        // 测试：节点3的后继应为4
        printSuccessor(n5, n3, solution); // 3->4
        // 测试：节点4的后继应为5
        successor = null; res = null; printSuccessor(n5, n4, solution); // 4->5
        // 测试：节点5的后继应为6
        successor = null; res = null; printSuccessor(n5, n5, solution); // 5->6
        // 测试：节点6的后继应为null
        successor = null; res = null; printSuccessor(n5, n6, solution); // 6->null
        // 测试：最左侧节点1的后继应为2
        successor = null; res = null; printSuccessor(n5, n1, solution); // 1->2
        // 测试：只有一个节点的树
        TreeNode single = new TreeNode(42);
        successor = null; res = null; printSuccessor(single, single, solution); // 42->null
    }

    static void printSuccessor(TreeNode root, TreeNode p, Q285_Inorder_Successor_in_BST solution) {
        TreeNode succ = solution.inorderSuccessor5(root, p);
        System.out.println("Node " + p.value + " successor is: " + (succ == null ? "null" : succ.value));
    }
    /**
     * 从根节点开始遍历。如果当前节点的值大于 p 的值，则有可能是后继，记录下来，并继续往左子树查找；
     * 如果当前节点的值小于或等于 p 的值，则后继一定在右子树，往右查找；
     * 直到遍历结束，返回记录的后继节点。
     */
    static public TreeNode inorderSuccessor4(TreeNode root, TreeNode p) {
        TreeNode successor = null; // 返回给定节点 p 的中序后继节点
        while(root != null){
            if(p.value < root.value){
                successor = root;  // 当前节点可能是后继，尝试往左找更小的
                root = root.left;
            } else {
                root = root.right;   // 当前节点及左子树都不会是后继，往右找
            }
        }
        return successor;
    }
    /**
     * 递归
     */
    static TreeNode successor = null;

    static public TreeNode inorderSuccessor5(TreeNode root, TreeNode p) {
        dfs(root, p);
        return successor;
    }

    static private void dfs(TreeNode root, TreeNode p) {
        if(root == null) {
            return;
        }
        if(p.value < root.value){
            successor = root;
            inorderSuccessor5(root.left, p);
        } else {
            inorderSuccessor5(root.right, p);
        }
    }
    /**
     * 递归 中序遍历
     */
    static TreeNode res = null;

    static public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        inorder(root, p);
        return res;
    }

    static private void inorder(TreeNode root, TreeNode p) {
        if(root == null) {
            return;
        }
        inorder(root.left, p);   // 左
        if(res == null && root.value > p.value){  // 中
            res = root;
        }
        inorder(root.right, p);// 右
    }
    /**
     * 递归 中序遍历
     */
    static public TreeNode inorderSuccessor1(TreeNode root, TreeNode p) {
        return inorder1(root, p);
    }

    static private TreeNode inorder1(TreeNode root, TreeNode p) {
        if(root == null) {
            return null;
        }
        TreeNode left = inorder1(root.left, p);   // 左
        if(left != null) return left;
        if(left == null && root.value > p.value){  // 中
            return root;
        }
        TreeNode right = inorder1(root.right, p);// 右
        if(right != null) return right;
        return null;
    }
    /**
     * 中序遍历 (迭代法)
     */
    static public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        TreeNode res = null;
        Deque<TreeNode> stack = new ArrayDeque<>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left; // 左
            }

            TreeNode cur = stack.pop();
            if(res == null && cur.value > p.value){
                res = cur;
                break;
            }
            root = cur.right; // 右
        }
        return res;
    }
}
