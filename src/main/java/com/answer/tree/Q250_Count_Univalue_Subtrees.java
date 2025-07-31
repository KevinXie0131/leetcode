package com.answer.tree;

import com.template.TreeNode;

public class Q250_Count_Univalue_Subtrees {
    /**
     * 统计同值子树
     * 给定一个二叉树，统计该二叉树数值相同的子树个数。
     * 同值子树是指该子树的所有节点都拥有相同的数值。
     * Given a binary tree, count the number of uni-value subtrees.
     * A Uni-value subtree means all nodes of the subtree have the same value.
     */
    public static void main(String[] args) {
        // Tree:   5
        //        / \
        //       1   5
        //          / \
        //         5   5
        TreeNode root = buildTree(new Integer[]{5, 1, 5, null, null, 5, 5});
        System.out.println(countUnivalSubtrees1(root) == 4);
        //   1
        //  / \
        // 1   1
        res = 0;
        TreeNode root1 = buildTree(new Integer[]{1,1,1});
        count = 0;
        System.out.println(countUnivalSubtrees1(root1) == 3);
        //   1
        //  / \
        // 2   3
        res = 0;
        TreeNode root2 = buildTree(new Integer[]{1,2,3});
        count = 0;
        System.out.println(countUnivalSubtrees1(root2) == 2);
    }

    static private TreeNode buildTree(Integer[] vals) {
        if (vals.length == 0) return null;
        TreeNode[] nodes = new TreeNode[vals.length];
        for (int i = 0; i < vals.length; i++) {
            if (vals[i] != null) {
                nodes[i] = new TreeNode(vals[i]);
            }
        }
        for (int i = 0, j = 1; j < vals.length; i++) {
            if (nodes[i] != null) {
                if (j < vals.length) nodes[i].left = nodes[j++];
                if (j < vals.length) nodes[i].right = nodes[j++];
            }
        }
        return nodes[0];
    }
    /**
     * 递归
     * 解法不是很高效，含有大量的重复 check
     */
    static int res = 0;

    static public int countUnivalSubtrees(TreeNode root) {
        if(root == null) return 0;
        if(inUniVal(root, root.value)) res++;
        countUnivalSubtrees(root.left);
        countUnivalSubtrees(root.right);
        return res;
    }

    static private boolean inUniVal(TreeNode root, int value){
        if(root == null) return true;
        boolean middle = root.value == value;
        boolean left = inUniVal(root.left, value);
        boolean right = inUniVal(root.right, value);
        return middle && left && right;
    }
    /**
     * 递归判断每个子树是否为“同值子树”（即所有节点值都相同）。
     * 如果是，则计数器加一。
     * 递归左右子树，只有当左、右子树都是同值子树并且与当前节点值相等时，当前树才是同值子树。
     */
    static private int count = 0;

    static public int countUnivalSubtrees1(TreeNode root) {
        isUnival2(root);
        return count;
    }

    static private boolean isUnival1(TreeNode node) {
        if (node == null) {
            return true;
        }
        // 从下往上 check，采用后序遍历
        boolean left = isUnival1(node.left);//左子树都一样吗？
        boolean right = isUnival1(node.right);

        if (!left || !right) { //左右子树有不一样
            return false;
        }
        if (node.left != null && node.left.value != node.value) { // root值跟左右不相等
            return false;
        }
        if (node.right != null && node.right.value != node.value) {
            return false;
        }
        count++;
        return true;
    }
    /**
     * another from
     */
    static private boolean isUnival2(TreeNode node) {
        if (node == null) {
            return true;
        }

        boolean left = isUnival2(node.left);
        boolean right = isUnival2(node.right);

        if (left && right) {
            if (node.left != null && node.value != node.left.value) {
                return false;
            }
            if (node.right != null && node.value != node.right.value) {
                return false;
            }
            count++;
            return true;
        }
        return false;
    }
}
