package com.answer.tree;

import com.template.TreeNode;

public class Q700_Search_in_a_Binary_Search_Tree {
    /**
     * ⼆叉搜索树中的搜索
     * ⼆叉搜索树是⼀个有序树：
     *    若它的左⼦树不空，则左⼦树上所有结点的值均⼩于它的根结点的值；
     *    若它的右⼦树不空，则右⼦树上所有结点的值均⼤于它的根结点的值；
     *    它的左、右⼦树也分别为⼆叉搜索树
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null)
            return null;
        if (root.value == val)
            return root;
        // 如果root->val > val，搜索左⼦树，如果root->val < val，就搜索右⼦树，最后如果都没有搜索到，就返回NULL。
        TreeNode result = null;
        if (root.value > val)
            result = searchBST(root.left, val);
        if (root.value < val)
            result = searchBST(root.right, val);

        return result;

    }
    /**
     *
     */
    public TreeNode searchBST1(TreeNode root, int val) {
        if (root == null || root.value == val) {
            return root;
        }
        if (root.value < val) {
            return searchBST(root.right, val);
        }
        if (root.value > val) {
            return searchBST(root.left, val);
        }
        return null;
    }
    /**
     * 迭代法
     *
     * ⼆叉树遍历的迭代法，可能⽴刻想起使⽤栈来模拟深度遍历，使⽤队列来模拟⼴度遍历。
     * 对于⼆叉搜索树可就不⼀样了，因为⼆叉搜索树的特殊性，也就是节点的有序性，可以不使⽤辅助栈或者队列就可以写出迭代法
     *
     * 对于⼀般⼆叉树，递归过程中还有回溯的过程，例如⾛⼀个左⽅向的分⽀⾛到头了，那么要调头，在⾛右分⽀。
     * ⽽对于⼆叉搜索树，不需要回溯的过程，因为节点的有序性就帮我们确定了搜索的⽅向。
     * 例如要搜索元素为3的节点，我们不需要搜索其他节点，也不需要做回溯，查找的路径已经规划好了。中间节点如果⼤于3就向左⾛，如果⼩于3就向右⾛，
     */
    public TreeNode searchBST2(TreeNode root, int val) {
        while (root != null) {
            if (root.value == val) {
                return root;
            } else if (root.value > val) {
                root = root.left;
            } else if (root.value < val) {
                root = root.right;
            }

        }
        return null;
    }
}
