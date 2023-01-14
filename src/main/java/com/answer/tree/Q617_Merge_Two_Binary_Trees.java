package com.answer.tree;

import com.template.TreeNode;

import java.util.*;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q617_Merge_Two_Binary_Trees {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        TreeNode node = new TreeNode(root1.value+root2.value);
        node.left=mergeTrees(root1.left,  root2.left);
        node.right=mergeTrees(root1.right,  root2.right);
        return node;
    }
    /**
     *
     */
    public TreeNode mergeTrees1(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return null;
        }
        if (root1 == null && root2 != null) {
            return root2;
        }
        if (root2 == null && root1!=null) {
            return root1;
        }
        TreeNode node = new TreeNode(root1.value+root2.value);
        node.left=mergeTrees1(root1.left,  root2.left);
        node.right=mergeTrees1(root1.right,  root2.right);
        return node;
    }
    /**
     *
     */
    public TreeNode mergeTrees2(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root1);
        queue.offer(root2);
        while(!queue.isEmpty()){
            TreeNode r1 = queue.poll();
            TreeNode r2 = queue.poll();

            r1.value = r1.value + r2.value;

            if(r1.left!=null && r2.left!=null){
                queue.offer(r1.left);
                queue.offer(r2.left);
            }
            if(r1.right!=null && r2.right!=null){
                queue.offer(r1.right);
                queue.offer(r2.right);
            }
            if(r1.left==null && r2.left!=null) {
                r1.left = r2.left;
            }
            if(r1.right==null && r2.right!=null) {
                r1.right = r2.right;
            }
        }

        return root1;
    }
}
