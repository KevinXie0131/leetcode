package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q235_Lowest_Common_Ancestor_of_a_Binary_Search_Tree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if ((root.left == p && root.right == q) ||
                (root.left == q && root.right == p)) {
            return root;
        }

        while ((root.value - p.value) * (root.value - q.value) > 0)
            root = p.value < root.value ? root.left : root.right;

        return root;
    }
    /**
     *
     */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (p.value < root.value && q.value < root.value) {
                root = root.left;
            } else if (p.value > root.value && q.value > root.value) {
                root = root.right;
            } else {
                return root;
            }
        }
        return root;
    }
    /**
     *
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        if (root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p,  q);
        TreeNode right = lowestCommonAncestor(root.right, p,  q);

        if (left == null && right == null) {
            return null;
        } else if(left != null && right == null) {
            return left;
        } else if(right != null && left == null) {
            return right;
        } else if (left != null && right != null) {
            return root;
        } else {
            return null;
        }
    }
    /**
     *
     */
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        if(root.value > p.value && root.value > q.value){
            return lowestCommonAncestor(root.left, p,q);
        }else if(root.value < p.value && root.value < q.value){
            return lowestCommonAncestor(root.right, p,q);
        }else{
            return root;
        }
    }
    /**
     *
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
    public TreeNode lowestCommonAncestor4(TreeNode root, TreeNode p, TreeNode q) {
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
