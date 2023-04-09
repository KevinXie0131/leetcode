package com.answer.tree;

import com.template.TreeNode;

public class Q701_Insert_into_a_Binary_Search_Tree {
    /**
     * ⼆叉搜索树中的插⼊操作
     *
     * 注意，可能存在多种有效的插⼊⽅式，只要树在插⼊后仍保持为⼆叉搜索树即可。 你可以返回任意有效的结果
     * 只要遍历⼆叉搜索树，找到空节点 插⼊元素就可以了，那么这道题其实就简单了. 需要调整⼆叉树的结构么？ 并不需要
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            TreeNode node = new TreeNode(val);
            return node;
        }
        // 通过递归函数返回值完成了新加⼊节点的⽗⼦关系赋值操作了，下⼀层将加⼊节点返回，
        // 本层⽤root->left或者root->right将其接住
        if (root.value > val) {
            root.left = insertIntoBST(root.left, val);
        }
        if (root.value < val) {
            root.right = insertIntoBST(root.right, val);
        }
        return root; // 递归函数的返回值完成⽗⼦节点的赋值是可以带来便利的
    }
    /**
     * 递归函数不⽤返回值也可以，找到插⼊的节点位置，直接让其⽗节点指向插⼊节点，结束递归，也是可以的
     */
    TreeNode parent; // 记录遍历节点的⽗节点
    public TreeNode insertIntoBST_0(TreeNode cur, int val) {
        parent = new TreeNode(0);
        if (cur == null) {
            cur = new TreeNode(val);
        }
        traversal(cur, val);
        return cur;
    }
    // 没有返回值，需要记录上⼀个节点（parent），遇到空节点了，就让parent左孩⼦或者右孩⼦指向新插⼊的节点。然后结束递归
    void traversal(TreeNode cur, int val) {
        if (cur == null) {
            TreeNode node = new TreeNode(val);
            if (val > parent.value) parent.right = node;
            else parent.left = node;
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
    public TreeNode insertIntoBST1(TreeNode root, int val) {
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
            } else if (cur.value < val){
                cur = cur.right;
            }
        }
        TreeNode node = new TreeNode(val);
        // 此时是⽤parent节点的进⾏赋值
        if(parent.value > val){
            parent.left =  node;
        }
        if(parent.value < val){
            parent.right = node;
        }

        return root;
    }

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
