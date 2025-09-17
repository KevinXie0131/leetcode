package com.answer.tree;

import com.template.TreeNode;

public class Q701_Insert_into_a_Binary_Search_Tree {
    /**
     * 二叉搜索树中的插入操作
     * 给定二叉搜索树（BST）的根节点 root 和要插入树中的值 value ，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据 保证 ，新值和原始二叉搜索树中的任意节点值都不同。
     * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回 任意有效的结果 。
     * You re given the root node of a binary search tree (BST) and a value to insert into the tree. Return the root node of the BST after the insertion. It is guaranteed that the new value does not exist in the original BST.
     * Notice that there may exist multiple valid ways for the insertion, as long as the tree remains a BST after insertion. You can return any of them.
     */
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode root1 = new TreeNode(2);
        TreeNode root2 = new TreeNode(7);
        TreeNode root3 = new TreeNode(1);
        TreeNode root4 = new TreeNode(3);
        root.left = root1;
        root.right = root2;
        root1.left = root3;
        root1.right = root4;
        TreeNode node = insertIntoBST1 (root, 5);
    }
    /**
     * ⼆叉搜索树中的插⼊操作 (递归法)
     * 注意，可能存在多种有效的插⼊⽅式，只要树在插⼊后仍保持为⼆叉搜索树即可。 你可以返回任意有效的结果
     * 只要遍历⼆叉搜索树，找到空节点 插⼊元素就可以了，那么这道题其实就简单了. 需要调整⼆叉树的结构么？ 并不需要
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) { // 如果当前节点为空，也就意味着val找到了合适的位置，此时创建节点直接返回。
            TreeNode node = new TreeNode(val);
            return node; // 终止条件就是找到遍历的节点为null的时候，就是要插入节点的位置了，并把插入的节点返回
        }
        // 通过递归函数返回值完成了新加⼊节点的⽗⼦关系赋值操作了，下⼀层将加⼊节点返回，
        // 本层⽤root->left或者root->right将其接住
        if (root.value > val) {
            root.left = insertIntoBST(root.left, val);  // 递归创建左子树
        }
        // else { // works too
        if (root.value < val) {
            root.right = insertIntoBST(root.right, val); // 递归创建右子树
        }
        return root; // 递归函数的返回值完成⽗⼦节点的赋值是可以带来便利的
    }
    /**
     * 递归函数不⽤返回值也可以，找到插⼊的节点位置，直接让其⽗节点指向插⼊节点，结束递归，也是可以的
     */
    TreeNode parent; // 记录遍历节点的⽗节点

    public TreeNode insertIntoBST_0(TreeNode root, int val) {
        parent = new TreeNode(0);
        if (root == null) {
            root = new TreeNode(val);
            return root;
        }
        traversal(root, val);
        return root;
    }
    // 没有返回值，需要记录上⼀个节点（parent），遇到空节点了，就让parent左孩⼦或者右孩⼦指向新插⼊的节点。然后结束递归
    void traversal(TreeNode cur, int val) {
        if (cur == null) {
            TreeNode node = new TreeNode(val);
            if (val > parent.value) {
                parent.right = node;
            } else {
                parent.left = node;
            }
            return;
        }

        parent = cur;
        if (cur.value > val) traversal(cur.left, val);
        if (cur.value < val) traversal(cur.right, val);
        return;
    }
    /**
     * 迭代
     * 在迭代法遍历的过程中，需要记录⼀下当前遍历的节点的⽗节点，这样才能做插⼊节点的操作
     */
    public static TreeNode insertIntoBST1(TreeNode root, int val) {
        if (root == null) {
            TreeNode node = new TreeNode(val);
            return node;
        }
        TreeNode parent = root; // 这个很重要，需要记录上⼀个节点，否则⽆法赋值新节点
        TreeNode cur = root;
        while(cur != null){
            parent = cur;
            if(cur.value > val){
                cur = cur.left;
            } else if (cur.value < val){ // 需要加上else
                cur = cur.right;
            }
        }
        TreeNode node = new TreeNode(val);
        // 此时是⽤parent节点的进⾏赋值
        if(parent.value > val){
            parent.left =  node;
        } else if(parent.value < val){
            parent.right = node;
        }
        return root;
    }
    /**
     * 模拟 迭代
     * 具体的步骤就是：
     *    如果插入的节点值比根节点的数值小：
     *        如果根节点的左子树为空，那该节点直接插入到根节点的左孩子位置。
     *        如果根节点的左子树不为空，继续遍历左子树寻找插入的位置。
     *    如果插入的节点值比根节点的数值大：
     *        如果根节点的右子树为空，那该节点直接插入到根节点的右孩子位置。
     *        如果根节点的右子树不为空，继续遍历右子树寻找插入的位置。
     */
    public TreeNode insertIntoBST2(TreeNode root, int val) {
        if (root == null) {
            TreeNode node = new TreeNode(val);
            return node;
        }
        TreeNode cur = root;
        while(cur != null){
            if(cur.value > val){
                if (cur.left != null) {
                    cur = cur.left;
                } else {
                    cur.left = new TreeNode(val);
                    break;
                }
            } else if (cur.value < val){
                if (cur.right != null) {
                    cur = cur.right;
                } else {
                    cur.right = new TreeNode(val);
                    break;
                }
            }
        }
        return root;
    }
}
