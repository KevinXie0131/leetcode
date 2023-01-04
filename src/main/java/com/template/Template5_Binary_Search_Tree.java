package com.template;

public class Template5_Binary_Search_Tree {

    static TreeNode pre = null;

    //[5,1,4,null,null,3,6]
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5, new TreeNode(1), new TreeNode(4));
        root.left.setLeft(null);
        root.left.setRight(null);
        root.right.setLeft(new TreeNode(3));
        root.right.setRight(new TreeNode(6));

        boolean result = isValidBST(root);
        System.out.println(result);
    }

    public static boolean isValidBST(TreeNode root) {

        if (root == null) return true;

        boolean left = isValidBST(root.left);

        if (pre != null && pre.getValue() >= root.getValue()) {
            return false;
        }
        pre = root;

        boolean right = isValidBST(root.right);

        return left && right;
    }
}
