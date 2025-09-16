package com.answer.tree;

import com.template.TreeNode;
import java.util.*;

public class Q700_Search_in_a_Binary_Search_Tree {
    /**
     * 二叉搜索树中的搜索
     * 给定二叉搜索树（BST）的根节点 root 和一个整数值 val。
     * 你需要在 BST 中找到节点值等于 val 的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 null 。
     * You are given the root of a binary search tree (BST) and an integer val.
     * Find the node in the BST that the node's value equals val and return the subtree rooted with that node. If such a node does not exist, return null.
     */
    /**
     * ⼆叉搜索树中的搜索
     * ⼆叉搜索树是⼀个有序树：
     *    若它的左⼦树不空，则左⼦树上所有结点的值均⼩于它的根结点的值；
     *    若它的右⼦树不空，则右⼦树上所有结点的值均⼤于它的根结点的值；
     *    它的左、右⼦树也分别为⼆叉搜索树
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.value == val) {
            return root;
        }
        // 如果root->val > val，搜索左⼦树，如果root->val < val，就搜索右⼦树，最后如果都没有搜索到，就返回NULL。
        TreeNode result = null;
        if (root.value > val) {
            result = searchBST(root.left, val);
        }
        // 递归函数的返回值是什么? 是 左子树如果搜索到了val，要将该节点返回。 如果不用一个变量将其接住，那么返回值不就没了
        if (root.value < val) {
            result = searchBST(root.right, val);
        }
        return result;
    }
    /**
     * 递归遍历
     */
    public TreeNode searchBST1(TreeNode root, int val) {
        // 确定终止条件
        if (root == null || root.value == val) { // 如果root为空，或者找到这个数值了，就返回root节点
            return root;
        }
        // 确定单层递归的逻辑
        if (root.value < val) { // 因为二叉搜索树的节点是有序的，所以可以有方向的去搜索
            return searchBST(root.right, val);
        }
        if (root.value > val) {
            return searchBST(root.left, val); // 不要忘了 递归函数还有返回值
        }
        return null;
    }
    /**
     * 另一种形式  递归，利用二叉搜索树特点，优化
     */
    public TreeNode searchBST_3(TreeNode root, int val) {
        if(root == null) {
            return null;
        }
        if(root.value == val){
            return root;
        } else if(root.value > val){
            return searchBST_3(root.left , val);
        }else  {
            return searchBST_3(root.right , val);
        }
    }
    /**
     * 递归，普通二叉树
     */
    public TreeNode searchBST_5(TreeNode root, int val) {
        if (root == null || root.value == val) {
            return root;
        }
        TreeNode left = searchBST(root.left, val);
        if (left != null) {
            return left;
        }
        return searchBST(root.right, val);
/*      TreeNode left = searchBST(root.left, val);
        TreeNode right = searchBST(root.right, val);

        if(left != null ) return left ;
        if(right != null ) return right ;
        return null;*/
    }
    /**
     * 迭代法
     *
     * ⼆叉树遍历的迭代法，可能⽴刻想起使⽤栈来模拟深度遍历，使⽤队列来模拟⼴度遍历。
     * 对于⼆叉搜索树可就不⼀样了，因为⼆叉搜索树的特殊性，也就是节点的有序性，可以不使⽤辅助栈或者队列就可以写出迭代法
     *
     * 对于⼀般⼆叉树，递归过程中还有回溯的过程，例如⾛⼀个左⽅向的分⽀⾛到头了，那么要调头，在⾛右分⽀。
     * ⽽对于⼆叉搜索树，不需要回溯的过程，因为节点的有序性就帮我们确定了搜索的⽅向。
     * 例如要搜索元素为3的节点，我们不需要搜索其他节点，也不需要做回溯，查找的路径已经规划好了。中间节点如果⼤于3就向左⾛，如果⼩于3就向右⾛，
     */
    public TreeNode searchBST2(TreeNode root, int val) {
        while (root != null) {
            if (root.value == val) {
                return root;
            } else if (root.value > val) {
                root = root.left;
            } else if (root.value < val) {
                root = root.right;
            }
/*            else {
                root = root.right;
            }*/
        }
        return null;
    }
    /**
     * 迭代，普通二叉树
     */
    public TreeNode searchBST_6(TreeNode root, int val) {
        if (root == null || root.value == val) {
            return root;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            if (pop.value == val) {
                return pop;
            }
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
        return null;
    }
}
