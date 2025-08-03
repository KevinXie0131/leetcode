package com.answer.tree;

import com.template.TreeNode;

public class Q998_Maximum_Binary_Tree_II {
    /**
     * 最大二叉树 II
     * 最大树 定义：一棵树，并满足：其中每个节点的值都大于其子树中的任何其他值。 A maximum tree is a tree where every node has a value greater than any other value in its subtree.
     * 给你最大树的根节点 root 和一个整数 val 。
     * 就像 之前的问题 那样，给定的树是利用 Construct(a) 例程从列表 a（root = Construct(a)）递归地构建的：
     *  如果 a 为空，返回 null 。
     *  否则，令 a[i] 作为 a 的最大元素。创建一个值为 a[i] 的根节点 root 。
     *  root 的左子树将被构建为 Construct([a[0], a[1], ..., a[i - 1]]) 。
     *  root 的右子树将被构建为 Construct([a[i + 1], a[i + 2], ..., a[a.length - 1]]) 。
     *  返回 root 。
     * 请注意，题目没有直接给出 a ，只是给出一个根节点 root = Construct(a) 。
     * 假设 b 是 a 的副本，并在末尾附加值 val。题目数据保证 b 中的值互不相同。
     * 返回 Construct(b) 。
     *
     * 大概意思是最大树 root 是根据特定的规则构造出来的，即给定的 root 其实对应一个具体的 nums，
     * 题目要求是将 val 追加到 nums 的尾部，然后再对得到的 nums 运用相同规则的构造，返回重新构造的最大树头结点。
     * 如果不考虑值的大小的话，val节点是nums中原有所有节点的右侧节点
     * 其最终位置分如下两种情况：
     *    val 为新 nums 中的最大值，同时 val 又是追加在原有 nums 的结尾，此时将原有的 root 挂在 val 对应节点的左子树即可，新树的根节点为 val 对应节点；
     *    否则，我们只需要不断在 root 的右子树中找目标位置 （反证法可以知，val 必不可能出现在任一非右位置，否则可推断出在 val 右边仍有元素，这与 val 位于 nums 的结尾位置冲突）
     */
    /**
     * 方法一：遍历右子节点
     * case1：如果val大于root.val，则root就是val节点的左子树节点。
     * case2：如果val大于非root.val，则非root就是val节点的左子树节点，并且非root节点的原父节点的右子树更新为val节点。
     * case3：如果val小于最底层的叶子节点，则val节点就作为该叶子节点的右子树节点。
     */
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        TreeNode parent = null;
        TreeNode cur = root;

        while(cur != null){
            if(val > cur.value){
                if(parent == null){
                    return new TreeNode(val, root, null);
                }
                TreeNode newNode = new TreeNode(val, cur, null);
                parent.right = newNode;
                return root;
            }else{
                parent = cur;
                cur = cur.right;
            }
        }
        parent.right = new TreeNode(val);
        return root;
    }
    /**
     * Another form
     * 由于val是加在最后一个位置，因此他只能插入右子树或者变为根节点。
     *  若val > root.val，则该节点是根节点，其左节点为之前的根节点
     *  若val < root.val，则递归的遍历右节点，直到找到大于的节点，该节点变为此节点的左节点；或者遍历到底部，直接变为右节点。
     */
    public TreeNode insertIntoMaxTree_1(TreeNode root, int val) {
        TreeNode newNode = new TreeNode(val);
        TreeNode cur = root, prev = null;
        while(cur != null && cur.value > val){
            prev = cur;
            cur = cur.right;
        }
        if(prev == null){
            newNode.left = cur;
            return newNode;
        }else{
            prev.right = newNode;
            newNode.left = cur;
            return root;
        }
    }
    /**
     * Recursion 递归
     * 给定最大二叉树的根节点 root，和一个整数 val，把 val 插入到最大二叉树中，保持其最大二叉树的特性并返回新的根节点。
     * 递归地插入到右子树，如果 val 大于当前根节点，则为新的根节点并把原树作为左子树。
     */
    public TreeNode insertIntoMaxTree_2(TreeNode root, int val) {
        //找到底部
        if(root == null){   // 如果root为空，直接新建节点
            return new TreeNode(val); // 递归出口是当前节点为 null，此时直接新建节点返回。
        }
        if(root.value < val){   // 如果val大于当前根节点，val作为新的根节点，原树为其左子树；
            return new TreeNode(val, root, null);
         //   TreeNode newRoot = new TreeNode(val);
         //   newRoot.left = root;
         //   return newRoot;
        }
        root.right = insertIntoMaxTree_2(root.right, val);  // 否则递归地将 val 插入到右子树

        return root;
    }
}
