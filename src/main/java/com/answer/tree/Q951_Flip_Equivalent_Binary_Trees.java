package com.answer.tree;

import com.template.TreeNode;

public class Q951_Flip_Equivalent_Binary_Trees {

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
