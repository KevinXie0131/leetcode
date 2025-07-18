package com.answer.tree;

import com.template.TreeNode;

public class Q951_Flip_Equivalent_Binary_Trees {
    /**
     * 翻转等价二叉树
     * 我们可以为二叉树 T 定义一个 翻转操作 ，如下所示：选择任意节点，然后交换它的左子树和右子树。
     * 只要经过一定次数的翻转操作后，能使 X 等于 Y，我们就称二叉树 X 翻转 等价 于二叉树 Y。
     * 这些树由根节点 root1 和 root2 给出。如果两个二叉树是否是翻转 等价 的函数，则返回 true ，否则返回 false 。
     * For a binary tree T, we can define a flip operation as follows: choose any node, and swap the left and right child subtrees.
     * A binary tree X is flip equivalent to a binary tree Y if and only if we can make X equal to Y after some number of flip operations.
     * Given the roots of two binary trees root1 and root2, return true if the two trees are flip equivalent or false otherwise.
     */
    /**
     * There are 3 cases:
     *
     *     If root1 or root2 is null, then they are equivalent if and only if they are both null.
     *     Else, if root1 and root2 have different values, they aren't equivalent.
     *     Else, let's check whether the children of root1 are equivalent to the children of root2. There are two different ways to pair these children.
     */
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == root2)
            return true;
        if (root1 == null || root2 == null || root1.value != root2.value)
            return false;

        return (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right) ||
                flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left));

    }

    /**
     *
     */
    public boolean flipEquiv_1(TreeNode root1, TreeNode root2) {
        if (root1 == root2)
            return true;

        if (root1 == null || root2 == null)
            return false;

        boolean d1 = flipEquiv(root1.left, root2.left);
        boolean d2 = flipEquiv(root1.right, root2.right);
        boolean d3 = flipEquiv(root1.left, root2.right);
        boolean d4 = flipEquiv(root1.right, root2.left);
        return root1.value == root2.value &&
                ((d1 && d2) || (d3 && d4));

    }
}
