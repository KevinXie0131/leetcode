package com.answer.tree;

import com.template.TreeNode;
import java.util.*;

public class Q538_Convert_BST_to_Greater_Tree {
    /**
     * 把二叉搜索树转换为累加树
     * 给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
     * 提醒一下，二叉搜索树满足下列约束条件：
     *  节点的左子树仅包含键 小于 节点键的节点。
     *  节点的右子树仅包含键 大于 节点键的节点。
     *  左右子树也必须是二叉搜索树。
     * 注意：本题和 1038: https://leetcode-cn.com/problems/binary-search-tree-to-greater-sum-tree/ 相同
     * Given the root of a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus the sum of all keys greater than the original key in BST.
     * As a reminder, a binary search tree is a tree that satisfies these constraints:
     *  The left subtree of a node contains only nodes with keys less than the node's key.
     *  The right subtree of a node contains only nodes with keys greater than the node's key.
     *  Both the left and right subtrees must also be binary search trees.
     *
     */
    /**
     * 把⼆叉搜索树转换为累加树（Greater Sum Tree）
     * 给出⼆叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater SumTree），使每个节点 node 的新值等于原树中⼤于或等于 node.val 的值之和
     *
     * 换⼀个⾓度来看，这就是⼀个有序数组[2, 5, 13]，求从后到前的累加数组，也就是[20, 18, 13]，是不是感觉这就简单了
     * 从树中可以看出累加的顺序是右中左，所以我们需要反中序遍历这个⼆叉树，然后顺序累加就可以了。
     * 需要定义⼀个全局变量pre，⽤来保存cur节点的前⼀个节点的数值
     */
    int pre = 0; // 记录前⼀个节点的数值
    int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        traversal(root);
        return root;
    }
    // 反序中序遍历: 要右中左来遍历⼆叉树， 中节点的处理逻辑就是让cur的数值加上前⼀个节点的数值

    // 对如下只有三个节点的搜索二叉树而言，计算结果就是，右子节点保持不变，中间节点的值是其本身与右子节点相加的和，
    // 左子节点的值是其本身与中间节点、右子节点三者的累计之和。
    // 也就是说，若求中间节点的值必须要先遍历完右子节点，而若求左子节点的值必须要遍历完中间节点和右子节点。因此，我们只需要进行一次
    // 反向中序遍历（即遍历顺序为右子树-->根节点-->左子树），在遍历过程中需要将已经遍历的节点的值进行累加，然后再赋值给当前节点。
    public void traversal(TreeNode node){  // 右中左遍历. 不需要递归函数的返回值做什么操作了，要遍历整棵树
        if(node == null) return;

        traversal(node.right); // 右

        node.value += pre; // 中  按右中左顺序遍历，累加即可
        pre = node.value;
      //  sum += node.value;  // works too
      //  node.value = sum;
        traversal(node.left); // 左
    }
    /**
     * 迭代法 中序模板题(右中左)
     * 右子节点（右子树）优先压栈。
     */
    int pre1 = 0; // 记录前⼀个节点的数值

    public TreeNode convertBST1(TreeNode root) {
        TreeNode cur = root;
        Deque<TreeNode> stack = new ArrayDeque<>();
        while(cur != null || !stack.isEmpty()){
            while(cur != null){
                stack.push(cur);
                cur = cur.right; // 右
            }
            TreeNode node = stack.pop();

            node.value += pre; // 中
            pre1= node.value;

            cur = node.left; // 左  是node.left
        }
        return root;
    }
}
