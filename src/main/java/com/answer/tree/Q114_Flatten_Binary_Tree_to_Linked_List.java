package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q114_Flatten_Binary_Tree_to_Linked_List {
    /**
     * 二叉树展开为链表
     * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
     *  展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
     *  展开后的单链表应该与二叉树 先序遍历 顺序相同。
     * Given the root of a binary tree, flatten the tree into a "linked list":
     *  The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
     *  The "linked list" should be in the same order as a pre-order traversal of the binary tree.
     */
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
            if (root.left == null) {  //左子树为 null，直接考虑下一个节点
                root = root.right;
            } else {
                TreeNode pre = root.left; // 找左子树最右边的节点
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
     * 先序遍历 深度优先
     * 如果有左孩子有右孩子，就找一个结点指向左孩子的最右叶子结点，然后把这一串插入父结点和右孩子中间。
     */
    public void flatten5(TreeNode root) {
        if (root == null) return;

        if (root.left != null) {
            TreeNode cur = root.left;
            while (cur.right != null) {
                cur = cur.right;
            }
            cur.right = root.right;
            root.right = root.left;
            root.left = null;
        }
        //  flatten5(root.left); // can be commented
        flatten5(root.right); //这时没有左树，只右递归
    }
    /**
     * 递归
     * 我们把 1 的右指针指向 2，那么 1 的原本的右孩子就丢失了，也就是 5 就找不到了。
     * 解决方法的话，我们可以逆过来进行。我们依次遍历 6 5 4 3 2 1，然后每遍历一个节点就将当前节点的右指针更新为上一个节点。
     * 遍历到 5，把 5 的右指针指向 6。6 <- 5 4 3 2 1。
     * 遍历到 4，把 4 的右指针指向 5。6 <- 5 <- 4 3 2
     *  6 5 4 3 2 1 的遍历顺序其实变形的后序遍历，遍历顺序是右子树->左子树->根节点。
     */
    private TreeNode pre = null;

    public void flatten1(TreeNode root) {
        if (root == null) return;

        flatten1(root.right);
        flatten1(root.left);
        root.right = pre; // 利用一个全局变量 pre，更新当前根节点的右指针为 pre，左指针为 null。
        root.left = null;
        pre = root;
    }
    /**
     * 迭代 (changed based on 后序遍历template)
     */
    public void flatten2(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode pre = null;

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root); // 添加根节点
                root = root.right;// 递归添加右节点
            }
            TreeNode cur = stack.peek();  // 已经访问到最右的节点了
            if (cur.left  == null || cur.left  == pre) {  // 在不存在左节点或者右节点已经访问过的情况下，访问根节点
                stack.pop();
                /**************修改的地方***************/
                cur.right = pre;
                cur.left = null;
                /*************************************/
                pre = cur;
                root = null;// 当前结点下，没有要遍历的结点了
            } else {
                root = cur.left;  // 左节点还没有访问过就先访问左节点
            }
        }
    }
    /**
     * 特殊的先序遍历
     * 先序遍历的顺序是 1 2 3 4 5 6。
     * 遍历到 2，把 1 的右指针指向 2。1 -> 2 3 4 5 6。
     * 遍历到 3，把 2 的右指针指向 3。1 -> 2 -> 3 4 5 6。
     * 因为我们用栈保存了右孩子，所以不需要担心右孩子丢失了。用一个 pre 变量保存上次遍历的节点。
     */
    public void flatten4(TreeNode root) {
        if (root == null) return;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        TreeNode pre = null;

        while (!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            /***********修改的地方*************/
            if(pre != null){
                pre.right = temp;
                pre.left = null;
            }
            /********************************/
            if (temp.right != null){
                stack.push(temp.right);
            }
            if (temp.left != null){
                stack.push(temp.left);
            }
            /***********修改的地方*************/
            pre = temp;
            /********************************/
        }
    }
    /**
     * 方法一：前序遍历
     * 将二叉树展开为单链表之后，单链表中的节点顺序即为二叉树的前序遍历访问各节点的顺序。因此，可以对二叉树进行前序遍历，获得各节点被访问到的顺序。
     * 由于将二叉树展开为链表之后会破坏二叉树的结构，因此在前序遍历结束之后更新每个节点的左右子节点的信息，将二叉树展开为单链表。
     */
    public void flatten(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        preorderTraversal(root, result);

        TreeNode cur = root;
        for(int i = 1; i < result.size(); i++){
            cur.left = null;
            cur.right = result.get(i);
            cur = cur.right;
        }
    }

    void preorderTraversal(TreeNode root, List<TreeNode> result) {
        if(root == null) {
            return;
        }
        result.add(root);      // 中
        preorderTraversal(root.left, result);  // 左
        preorderTraversal(root.right, result); // 右
    }
}
