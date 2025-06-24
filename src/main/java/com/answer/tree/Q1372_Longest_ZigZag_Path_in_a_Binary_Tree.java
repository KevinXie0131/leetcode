package com.answer.tree;

public class Q1372_Longest_ZigZag_Path_in_a_Binary_Tree {
    /**
     * 二叉树中的最长交错路径
     * 给你一棵以 root 为根的二叉树，二叉树中的交错路径定义如下：
     *  选择二叉树中 任意 节点和一个方向（左或者右）。
     *  如果前进方向为右，那么移动到当前节点的的右子节点，否则移动到它的左子节点。
     *  改变前进方向：左变右或者右变左。
     *  重复第二步和第三步，直到你在树中无法继续移动。
     * 交错路径的长度定义为：访问过的节点数目 - 1（单个节点的路径长度为 0 ）。
     * 请你返回给定树中最长 交错路径 的长度。
     * You are given the root of a binary tree.
     * A ZigZag path for a binary tree is defined as follow:
     *  Choose any node in the binary tree and a direction (right or left).
     *  If the current direction is right, move to the right child of the current node; otherwise, move to the left child.
     *  Change the direction from right to left or from left to right.
     *  Repeat the second and third steps until you can't move in the tree.
     * Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0).
     * Return the longest ZigZag path contained in that tree.
     */
}
