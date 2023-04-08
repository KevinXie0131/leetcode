package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q236_Lowest_Common_Ancestor_of_a_Binary_Tree {
    /**
     * ⼆叉树的最近公共祖先
     * 给定⼀个⼆叉树, 找到该树中两个指定节点的最近公共祖先
     *
     * 最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表⽰为⼀个结点 x，满⾜ x 是 p、q 的祖先且 x 的深度尽可能⼤（⼀个节点也可以是它⾃⼰的祖先）
     *
     * 后序遍历 - 如果找到⼀个节点，发现左⼦树出现结点p，右⼦树出现节点q，或者 左⼦树出现结点q，右⼦树出现节点p，那么该节点就是节点p和q的最近公共祖先
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        if (root == p || root == q) return root; // 如果找到了 节点p或者q，或者遇到空节点，就返回
        // left = 递归函数(root->left);
        // right = 递归函数(root->right);
        // left与right的逻辑处理
        TreeNode left = lowestCommonAncestor(root.left, p,  q);
        TreeNode right = lowestCommonAncestor(root.right, p,  q);

        if (left == null && right == null) { // 那么如果left和right都为空，则返回left或者right都是可以的，也就是返回空
            return null;
        } else if(left != null && right == null) { // 如果left为空，right不为空，就返回right，说明⽬标节点是通过right返回的，反之依然
            return left;
        } else if(right != null && left == null) {
            return right;
        } else if (left != null && right != null) { // 如果left 和 right都不为空，说明此时root就是最近公共节点。
            return root;
        } else {
            return null;
        }
    }
    /**
     * 归纳如下三点
     *    求最⼩公共祖先，需要从底向上遍历，那么⼆叉树，只能通过后序遍历（即：回溯）实现从低向上的遍历⽅式
     *    在回溯的过程中，必然要遍历整颗⼆叉树，即使已经找到结果了，依然要把其他节点遍历完，因为要使⽤递归函数的返回值（也就是代码中的left和right）做逻辑判断。
     *    要理解如果返回值left为空，right不为空为什么要返回right，为什么可以⽤返回right传给上⼀层结果。
     *
     * 本题没有给出迭代法，因为迭代法不适合模拟回溯的过程。理解递归的解法就够了
     */
    Map<Integer, TreeNode> parent = new HashMap<Integer, TreeNode>();
    Set<Integer> visited = new HashSet<Integer>();

    public void dfs(TreeNode root){
        if(root == null){
            return;
        }
        if(root.left!=null){
            parent.put(root.left.value, root);
            dfs(root.left);
        }
        if(root.right!=null){
            parent.put(root.right.value, root);
            dfs(root.right);
        }

    }
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root);
        while(p != null){
            visited.add(p.value);
            p= parent.get(p.value);
        }

        while(q != null){
            if(visited.contains(q.value)){
                return q;
            }
            q= parent.get(q.value);
        }

        return null;
    }
}
