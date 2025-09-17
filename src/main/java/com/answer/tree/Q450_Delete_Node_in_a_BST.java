package com.answer.tree;

import com.template.TreeNode;

public class Q450_Delete_Node_in_a_BST {
    /**
     * 删除二叉搜索树中的节点
     * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
     * 一般来说，删除节点可分为两个步骤：
     *  首先找到需要删除的节点；
     *  如果找到了，删除它。
     * Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.
     * Basically, the deletion can be divided into two stages:
     *  Search for a node to remove.
     *  If the node is found, delete the node.
     */
    /**
     * 删除⼆叉搜索树中的节点 (⼆叉搜索树删除节点就涉及到结构调整了)
     * ⼀般来说，删除节点可分为两个步骤：
     *      ⾸先找到需要删除的节点；
     *      如果找到了，删除它。
     *      说明： 要求算法时间复杂度为 O(h)，h 为树的⾼度。
     *
     * 第⼀种情况：没找到删除的节点，遍历到空节点直接返回了
     * 找到删除的节点
     *      第⼆种情况：左右孩⼦都为空（叶⼦节点），直接删除节点， 返回NULL为根节点
     *      第三种情况：删除节点的左孩⼦为空，右孩⼦不为空，删除节点，右孩⼦补位，返回右孩⼦为根节点
     *      第四种情况：删除节点的右孩⼦为空，左孩⼦不为空，删除节点，左孩⼦补位，返回左孩⼦为根节点
     *      第五种情况：左右孩⼦节点都不为空，则将删除节点的左⼦树头结点（左孩⼦）放到删除节点的右⼦树的最左⾯节点的左孩⼦上，
     *                 返回删除节点右孩⼦为新的根节点。
     */
    public TreeNode deleteNode0(TreeNode root, int key) { // 递归解法1(最好理解的版本)
        if(root == null) {
            return null;
        }
        if(root.value == key){
            if(root.left == null && root.right == null){
                return null;
            }else if(root.left == null){
                return root.right;
            }else if(root.right == null){
                return root.left;
            }else {
                TreeNode cur = root.right;
                while(cur.left != null){
                    cur = cur.left;
                }
                cur.left = root.left;
                root = root.right;
                return root;
            }
        }
        if(key < root.value) root.left = deleteNode(root.left,  key);
        if(key > root.value) root.right = deleteNode(root.right,  key);
        return root;
    }
    /**
     * 递归
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) { // 第⼀种情况：没找到删除的节点，遍历到空节点直接返回了
            return null;
        }
        // 这⾥相当于把新的节点返回给上⼀层，上⼀层就要⽤ root->left 或者 root->right接住
        if(root.value > key){
            root.left = deleteNode(root.left, key);
        } else if(root.value < key) {
            root.right = deleteNode(root.right, key);
        } else {
            // 第⼆种情况：左右孩⼦都为空（叶⼦节点），直接删除节点， 返回NULL为根节点
            // 第三种情况：其左孩⼦为空，右孩⼦不为空，删除节点，右孩⼦补位 ，返回右孩⼦为根节点
            // 第四种情况：其右孩⼦为空，左孩⼦不为空，删除节点，左孩⼦补位，返回左孩⼦为根节点
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            // 第五种情况：左右孩⼦节点都不为空，则将删除节点的左⼦树放到删除节点的右⼦树的最左⾯节点的左孩⼦的位置
            // 并返回删除节点右孩⼦为新的根节点。
       //     else if(root.right != null && root.right != null){  // can be commented
                TreeNode node = root.right;
                while(node.left != null){ // 找右⼦树最左⾯的节点
                    node = node.left;
                }
                node.left = root.left; // 把要删除的节点（root）左⼦树放在cur的左孩⼦的位置
                root = root.right; // 返回旧root的右孩⼦作为新root
        //    }
        }
        return root;
    }
    /**
     * 迭代法
     * 将⽬标节点（删除节点）的左⼦树放到 ⽬标节点的右⼦树的最左⾯节点的左孩⼦位置上,并返回⽬标节点右孩⼦为新的根节点
     */
    public TreeNode deleteNode_0(TreeNode root, int key) {
        if (root == null) {
            return root;
        }
        //寻找对应的对应的前面的节点，以及他的前一个节点
        TreeNode cur = root;
        TreeNode pre = null; // 记录cur的⽗节点，⽤来删除cur
        while (cur != null) {
            if (cur.value == key) {
                break;
            }
            pre = cur;
            if (cur.value > key) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        if (pre == null) { // 如果搜索树只有头结点
            return deleteOneNode(cur);
        }
        // pre 要知道是删左孩⼦还是右孩⼦
        if (pre.left != null && pre.left.value == key) {
            pre.left = deleteOneNode(cur);
        }
        if (pre.right != null && pre.right.value == key) {
            pre.right = deleteOneNode(cur);
        }
        return root;
    }

    TreeNode deleteOneNode(TreeNode target) {
        if (target == null) {
            return target;
        }
        if (target.right == null) {
            return target.left;
        }
       /* if (target.left == null) { // can be commented
            return target.right;
        }*/
        TreeNode cur = target.right;
        while (cur.left != null) {
            cur = cur.left;
        }
        cur.left = target.left;
        return target.right;
/*        target = target.right;
        return target;*/
    }
    /**
     * 迭代 official answer
     */
    public TreeNode deleteNode1(TreeNode root, int key) {
        TreeNode cur = root, curParent = null;
        while (cur != null && cur.value != key) {
            curParent = cur;
            if (cur.value > key) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        if (cur == null) {
            return root;
        }
        if (cur.left == null && cur.right == null) {
            cur = null;
        } else if (cur.right == null) {
            cur = cur.left;
        } else if (cur.left == null) {
            cur = cur.right;
        } else {
            TreeNode successor = cur.right, successorParent = cur;
            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }
            if (successorParent.value == cur.value) {
                successorParent.right = successor.right;
            } else {
                successorParent.left = successor.right;
            }
            successor.right = cur.right;
            successor.left = cur.left;
            cur = successor;
        }
        if (curParent == null) {
            return cur;
        } else {
            if (curParent.left != null && curParent.left.value == key) {
                curParent.left = cur;
            } else {
                curParent.right = cur;
            }
            return root;
        }
    }
}
