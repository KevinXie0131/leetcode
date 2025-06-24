package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q98_Validate_Binary_Search_Tree {
    /**
     * 验证二叉搜索树
     * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
     * 有效 二叉搜索树定义如下：
     *  节点的左子树只包含 小于 当前节点的数。
     *  节点的右子树只包含 大于 当前节点的数。
     *  所有左子树和右子树自身必须也是二叉搜索树。
     * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
     * A valid BST is defined as follows:
     *  The left subtree of a node contains only nodes with keys less than the node's key.
     *  The right subtree of a node contains only nodes with keys greater than the node's key.
     *  Both the left and right subtrees must also be binary search trees.
     *
     */
    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        boolean isValid = isValidBST5(root);
        System.out.println(isValid);
    }
    /**
     * 验证⼆叉搜索树
     *
     * 要知道中序遍历下，输出的⼆叉搜索树节点的数值是有序序列
     * 有了这个特性，验证⼆叉搜索树，就相当于变成了判断⼀个序列是不是递增的了
     */
    /**
     * 递归法
     * 递归中序遍历将⼆叉搜索树转变成⼀个数组, 然后只要⽐较⼀下，这个数组是否是有序的，注意⼆叉搜索树中不能有重复元素
     * 但其实不用转变成数组，可以在递归遍历的过程中直接判断是否有序
     *
     * 陷阱1
     *    不能单纯的⽐较左节点⼩于中间节点，右节点⼤于中间节点就完事了, 我们要⽐较的是 左⼦树所有节点⼩于中间节点，右⼦树所有节点⼤于中间节点
     */
    List<Integer> list = new ArrayList<>();

    public boolean isValidBST_0(TreeNode root) {
        traversal(root);
        for (int i = 1; i < list.size(); i++) {
            // 注意要⼩于等于，搜索树⾥不能有相同元素
            if (list.get(i) <= list.get(i - 1)) return false;
        }
        return true;
    }
    void traversal(TreeNode root) {
        if (root == null) return;

        traversal(root.left);
        list.add(root.value); // 将⼆叉搜索树转换为有序数组
        traversal(root.right);
    }
    /**
     * Double.MAX_VALUE is the biggest finite number that double can hold. (A very large positive number.)
     * Double.MIN_VALUE is the smallest positive number that double can hold. (A very small positive number.)
     * So -Double.MAX_VALUE is a very large negative number. That's a lower number than a very small positive number.
     * Contrarily, Integer.MIN_VALUE is the most negative number that an int can hold. It has a different meaning from the similarly named constant in Double.
     */
    static double max = -Double.MAX_VALUE ;
    //  long max = Long.MIN_VALUE; // 这个也可以
    public static boolean isValidBST5(TreeNode root) {
        if(root == null) return true;

        boolean left = isValidBST5( root.left); // 左
/*        if (!left) {  // 可以加上
            return false;
        }*/

        if(root.value > max){ // 中序遍历，验证遍历的元素是不是从小到大
            max = root.value; // 中
        }else {
            return false;
        }

        boolean right = isValidBST5( root.right); // 右
        return left && right;
    }
    /**
     * 陷阱2
     *    样例中最⼩节点 可能是int的最⼩值，如果这样使⽤最⼩的int来⽐较也是不⾏的。此时可以初始化⽐较元素为double的最⼩值。
     */
    public boolean isValidBST(TreeNode root) {
        double result = -Double.MAX_VALUE; // 因为后台测试数据中有int最⼩值
        Deque<TreeNode> stack = new ArrayDeque<>();

        while (!stack.isEmpty() || root != null) {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }

            TreeNode cur = stack.pop();
            if(cur.value <= result ) {
                return false;
            } else {
                result = cur.value;
            }
            root = cur.right;
        }
        return true;
    }
    /**
     * 迭代法 同上
     */
    public boolean isValidBST1(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        long pre = Long.MIN_VALUE; // 记录前⼀个节点
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left; // 左
            }

            TreeNode cur = stack.pop(); // 中
            if (cur.value > pre) {
                pre = cur.value; //保存前⼀个访问的结点
            } else {
                return false;
            }
            root = cur.right; // 右

        }
        return true;
    }
    /**
     * 递归
     * 因为后台数据有int最小值测试用例, 建议避免初始化最小值，如下方法取到最左面节点的数值来比较
     */
    TreeNode pre = null; // ⽤来记录前⼀个节点

    public boolean isValidBST2(TreeNode root) {

        if (root == null) return true;

        boolean left = isValidBST(root.left);         // 左

        if (pre != null && pre.value >= root.value) { // 中序遍历，验证遍历的元素是不是从⼩到⼤
            return false;                             // 中
        }
        pre = root; // 记录前⼀个节点

        boolean right = isValidBST(root.right);      // 右

        return left && right;
    }
    /**
     * 简洁实现·递归解法
     */
    public boolean isValidBST3(TreeNode root) {
        return validBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean validBST(TreeNode root,  long lower, long upper){
        if(root==null){
            return true;
        }
        if (root.value >= upper || root.value <= lower){
            return false;
        }

        boolean left = validBST(root.left, lower, root.value);
        boolean right = validBST(root.right, root.value, upper);
        return left && right;
    }
    /**
     *  简洁实现·中序遍历
     *  对二叉树进行中序遍历，每遍历到一个节点都和当前已遍历的最后一个节点值比较，只要能满足递增关系就继续遍历，直到遍历所有节点。
     */
    private long prev = Long.MIN_VALUE;
    public boolean isValidBST_5(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isValidBST_5(root.left)) {
            return false;
        }
        if (root.value <= prev) { // 不满足二叉搜索树条件
            return false;
        }
        prev = root.value;
        return isValidBST_5(root.right);
    }
}
