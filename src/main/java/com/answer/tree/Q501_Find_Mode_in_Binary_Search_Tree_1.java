package com.answer.tree;

import com.template.TreeNode;

import java.util.*;
import java.util.HashMap;

public class Q501_Find_Mode_in_Binary_Search_Tree_1 {
    int count, maxCount, maxVal;
    ArrayList<Integer> list = new ArrayList<>();
    TreeNode pre;
    HashMap<Integer, Integer> map = new HashMap<>();

    public int[] findMode(TreeNode root) {
        recursion(root);

        for(Map.Entry entry: map.entrySet()){
            int key = (int)entry.getKey();
            int value = (int)entry.getValue();

            if(value == maxVal){
                list.add(key);
            }
            if(value > maxVal){
                maxVal = value;
                list.clear();
                list.add(key);
            }

        }

        return list.stream().mapToInt(i -> i).toArray();
    }

    public void recursion(TreeNode root){
        if (root == null) {
            return;
        }
        recursion(root.left);
        map.put(root.value, map.getOrDefault(root.value,0) + 1);
        recursion(root.right);
    }
}
