package com.answer.tree;

import com.template.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Q114_Flatten_Binary_Tree_to_Linked_List {
    /**
     * Follow up: Can you flatten the tree in-place (with O(1) extra space)? 空间复杂度是 O(1)
     * 解法一
     *   将左子树插入到右子树的地方
     *   将原来的右子树接到左子树的最右边节点
     *   考虑新的右子树的根节点，一直重复上边的过程，直到新的右子树为 null
     *     1
     *    / \
     *   2   5
     *  / \   \
     * 3   4   6
     * //将 1 的左子树插入到右子树的地方
     *     1
     *      \
     *       2         5
     *      / \         \
     *     3   4         6
     * //将原来的右子树接到左子树的最右边节点
     *     1
     *      \
     *       2
     *      / \
     *     3   4
     *          \
     *           5
     *            \
     *             6
     *  //将 2 的左子树插入到右子树的地方
     *     1
     *      \
     *       2
     *        \
     *         3       4
     *                  \
     *                   5
     *                    \
     *                     6
     *
     *  //将原来的右子树接到左子树的最右边节点
     *     1
     *      \
     *       2
     *        \
     *         3
     *          \
     *           4
     *            \
     *             5
     *              \
     *               6
     * 从每个节点的左孩子向右找到最后一个节点，然后通过这部分子链将左子树合并到右子树上，直到每个节点都完成合并。
     */
    public void flatten0(TreeNode root) {
        while (root != null) {
            //左子树为 null，直接考虑下一个节点
            if (root.left == null) {
                root = root.right;
            } else {
                // 找左子树最右边的节点
                TreeNode pre = root.left;
                while (pre.right != null) { // 首先我们需要找出左子树最右边的节点以便把右子树接过来
                    pre = pre.right;
                }
                //将原来的右子树接到左子树的最右边节点
                pre.right = root.right;
                // 将左子树插入到右子树的地方
                root.right = root.left;
                root.left = null; //设为null
                // 考虑下一个节点
                root = root.right;
            }
        }
    }
    /**
     * 方法一：前序遍历
     * 将二叉树展开为单链表之后，单链表中的节点顺序即为二叉树的前序遍历访问各节点的顺序。因此，可以对二叉树进行前序遍历，获得各节点被访问到的顺序。
     * 由于将二叉树展开为链表之后会破坏二叉树的结构，因此在前序遍历结束之后更新每个节点的左右子节点的信息，将二叉树展开为单链表。
     */
    public void flatten(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        inorder(root, result);

        TreeNode cur = root;
        for(int i = 1; i < result.size(); i++){
            cur.left = null;
            cur.right = result.get(i);
            cur = cur.right;
        }
    }

    void inorder(TreeNode root, List<TreeNode> result) {
        if(root == null) {
            return;
        }
        result.add(root);      // 中
        inorder(root.left, result);  // 左
        inorder(root.right, result); // 右
    }
}
