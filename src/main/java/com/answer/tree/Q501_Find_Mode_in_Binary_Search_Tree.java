package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q501_Find_Mode_in_Binary_Search_Tree {
    int count, maxCount;
    ArrayList<Integer> list = new ArrayList<>();
    TreeNode pre;

    public int[] findMode(TreeNode root) {
        recursion(root);
        return list.stream().mapToInt(i -> i).toArray();
    }

    public void recursion(TreeNode root){
        if (root == null) {
            return;
        }
        recursion(root.left);

        if(pre == null){
            count = 1;
        } else if(pre.value == root.value) {
            count++;
        } else if (pre.value != root.value){
            count = 1;
        }
        pre = root;

        if(count == maxCount){
            list.add(root.value);
        }
        if (count > maxCount) {
            maxCount = count;
            list.clear();
            list.add(root.value);
        }

        recursion(root.right);
    }
}
