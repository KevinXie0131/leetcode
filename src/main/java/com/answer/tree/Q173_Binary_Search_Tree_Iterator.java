package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q173_Binary_Search_Tree_Iterator {
    /**
     * 二叉搜索树迭代器
     * 实现一个二叉搜索树迭代器类BSTIterator ，表示一个按中序遍历二叉搜索树（BST）的迭代器：
     * Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search tree (BST):
     *  BSTIterator(TreeNode root) 初始化 BSTIterator 类的一个对象。BST 的根节点 root 会作为构造函数的一部分给出。指针应初始化为一个不存在于 BST 中的数字，且该数字小于 BST 中的任何元素。
     *  boolean hasNext() 如果向指针右侧遍历存在数字，则返回 true ；否则返回 false 。
     *  int next()将指针向右移动，然后返回指针处的数字。
     * 注意，指针初始化为一个不存在于 BST 中的数字，所以对 next() 的首次调用将返回 BST 中的最小元素。
     * 你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 的中序遍历中至少存在一个下一个数字。
     */
    /**
     * Approach 1: Flattening the BST
     * 本质是中序遍历
     * 参考Q94 Binary Tree Inorder Traversal
     */
    private int index;
    private ArrayList<Integer> list;

    public Q173_Binary_Search_Tree_Iterator(TreeNode root) {
        index = 0 ;
        list = new ArrayList<>();
        inorder(root, list);
    }

    public int next() {
        return list.get(index++);
    }

    public boolean hasNext() {
        return index < list.size();
    }

    private void inorder(TreeNode root, ArrayList<Integer> list){
        if(root == null){
            return;
        }
        inorder(root.left, list);
        list.add(root.value);
        inorder(root.right, list);
    }
}
