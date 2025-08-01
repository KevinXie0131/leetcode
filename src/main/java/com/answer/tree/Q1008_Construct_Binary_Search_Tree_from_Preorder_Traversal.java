package com.answer.tree;

import com.template.TreeNode;

public class Q1008_Construct_Binary_Search_Tree_from_Preorder_Traversal {
    /**
     * 给定一个整数数组，它表示BST(即 二叉搜索树 )的 先序遍历 ，构造树并返回其根。
     * 保证 对于给定的测试用例，总是有可能找到具有给定需求的二叉搜索树。
     * 二叉搜索树 是一棵二叉树，其中每个节点， Node.left 的任何后代的值 严格小于 Node.val , Node.right 的任何后代的值 严格大于 Node.val。
     * 二叉树的 前序遍历 首先显示节点的值，然后遍历Node.left，最后遍历Node.right。
     * Given an array of integers preorder, which represents the preorder traversal of a BST (i.e., binary search tree), construct the tree and return its root.
     * It is guaranteed that there is always possible to find a binary search tree with the given requirements for the given test cases.
     * A binary search tree is a binary tree where for every node, any descendant of Node.left has a value strictly less than Node.val, and any descendant of Node.right has a value strictly greater than Node.val.
     * A preorder traversal of a binary tree displays the value of the node first, then traverses Node.left, then traverses Node.right.
     */
    /**
     * refer to Q449_Serialize_and_Deserialize_BST
     */
    public TreeNode bstFromPreorder(int[] preorder) {
        return build2(preorder, 0, preorder.length - 1);
    }
    // 二分法构造
    private TreeNode build2(int[] vals, int left, int right) {
        if(left > right) return null;

        int val = vals[left];
        TreeNode node = new TreeNode(val);
        int j = left + 1;
        /*while (j <= right && vals[j] <= val){ // works too
            j++;
        }*/
        while (j <= right){
            if(vals[j] > val){
                break;
            }
            j++;
        }
        node.left = build2(vals, left + 1, j - 1);
        node.right = build2(vals, j, right);
        return node;
    }
    /**
     * refer to Q449_Serialize_and_Deserialize_BST
     * nodes[lo]必是当前段的根节点，接着在[lo,hi]中搜索第一个比根节点值大的位置right_start
     * nodes[lo]的左子树就可以在[lo+1,right_start−1]段中递归构造
     * nodes[lo]的右子树就可以在[right_start,hi]段中递归构造
     */
    public TreeNode bstFromPreorder2(int[] preorder) {
        int[] idx = {0};
        return build(preorder, idx, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    // 先序遍历构造
    // 通过下限和上限来控制指针移动的范围
    private TreeNode build(int[] vals, int[] idx, int lower, int upper) {
        if (idx[0] == vals.length) {// 递归终止条件
            return null;
        }
        int val = vals[idx[0]];
        // 当前值必须在有效区间
        if (val < lower || val > upper) { // 当前值不在合法区间，不能放在这里
            return null;
        }
        TreeNode node = new TreeNode(val);
        idx[0]++;   // 构建当前节点
        node.left = build(vals, idx, lower, val - 1); // 构建左子树，区间为：[lower, val-1]
        node.right = build(vals, idx, val + 1, upper); // 构建右子树，区间为：[val+1, upper]
        return node;
    }
    /**
     * 通过递归地将每个后续节点插入到已有的二叉搜索树中，从而逐步构建整棵树。
     */
    // 主函数，从给定的前序遍历数组构建二叉搜索树
    public TreeNode bstFromPreorder3(int[] preorder) {
        TreeNode root = new TreeNode(preorder[0]);   // 创建根节点，使用第一个元素作为根
        for (int i = 1; i < preorder.length; i++) {   // 遍历前序数组中的剩余元素，并依次插入到树中
            insert(root, preorder[i]);
        }
        return root;  // 返回构建好的二叉搜索树的根节点
    }
    // 辅助函数，用于将值val插入到以node为根的子树中
    private TreeNode insert(TreeNode node, int val) {
        if (node == null) {  // 如果当前节点为空，则创建新节点并返回
            return new TreeNode(val);
        }
        if (val < node.value) {// 如果插入值小于当前节点值，则递归地在左子树中插入
            node.left = insert(node.left, val);
        }
        else if (node.value < val) {     // 如果插入值大于当前节点值，则递归地在右子树中插入
            node.right = insert(node.right, val);
        }
        return node;// 返回当前节点（保持链的连续性）
    }
    /**
     * 迭代
     */
    private void insert0(TreeNode root, int val) {
        TreeNode node = new TreeNode(val);
        TreeNode cur = root;
        while (true) {
            if (cur.value > val) {   //如果要插入的结点data比结点p的值小，就往p结点的左子节点找，否则往p的右子节点找
                if (cur.left == null) { //如果p的左子节点等于空，直接放进去
                    cur.left = node;
                    break;
                } else {
                    cur = cur.left;
                }
            } else {
                if (cur.right == null) {  //如果p的右子节点等于空，直接放进去
                    cur.right = node;
                    break;
                } else {
                    cur = cur.right;
                }
            }
        }
    }
}
