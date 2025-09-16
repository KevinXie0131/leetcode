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
     * 递归判断两棵树在当前节点是否等价。
     * 如果当前节点值不同，或仅有一个节点为 null，返回 false。
     * 否则递归判断两种情况：
     *    不翻转的子树等价
     *    翻转左右子树后等价
     * 只要有一种情况成立即可。
     */
    public boolean flipEquiv0(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;  // 都为空，等价
        if (root1 == null || root2 == null || root1.value != root2.value) return false; // 有一个为空或值不同，不等价
        // 两种情况：不翻转 或 翻转
        boolean notFlipped = flipEquiv0(root1.left, root2.left) && flipEquiv0(root1.right, root2.right);
        boolean flipped = flipEquiv0(root1.left, root2.right) && flipEquiv0(root1.right, root2.left);
        return notFlipped || flipped;
    }
    /**
     * refer to Q100_Same_Tree
     */
    public boolean flipEquiv0a(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;
        return  root1.value == root2.value && ((flipEquiv0a(root1.left, root2.left) && flipEquiv0a(root1.right, root2.right))
                                            || (flipEquiv0a(root1.left, root2.right) && flipEquiv0a(root1.right, root2.left)));
    }
    /**
     * There are 3 cases:
     *     If root1 or root2 is null, then they are equivalent if and only if they are both null.
     *     Else, if root1 and root2 have different values, they aren't equivalent.
     *     Else, let's check whether the children of root1 are equivalent to the children of root2. There are two different ways to pair these children.
     * 存在三种情况：
     *   如果 root1 或者 root2 是 null，那么只有在他们都为 null 的情况下这两个二叉树才等价。
     *   如果 root1，root2 的值不相等，那这两个二叉树的一定不等价。
     *   如果以上条件都不满足，也就是当 root1 和 root2 的值相等的情况下，需要继续判断 root1 的孩子节点是不是跟 root2 的孩子节点相当。因为可以做翻转操作，所以这里有两种情况需要去判断。
     */
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == root2) { //  if (root1 == null && root2 == null) { // works too
            return true;
        }
        if (root1 == null || root2 == null || root1.value != root2.value) {
            return false;
        }
        return (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right) ||
                flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left));
    }
    /**
     * another form
     */
    public boolean flipEquiv_1(TreeNode root1, TreeNode root2) {
        if (root1 == root2) {  //  if (root1 == null && root2 == null) { // works too
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        boolean d1 = flipEquiv(root1.left, root2.left);
        boolean d2 = flipEquiv(root1.right, root2.right);
        boolean d3 = flipEquiv(root1.left, root2.right);
        boolean d4 = flipEquiv(root1.right, root2.left);
        return root1.value == root2.value && ((d1 && d2) || (d3 && d4));
    }
}
