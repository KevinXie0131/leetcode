package com.answer.tree;

import com.template.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

public class Q501_Find_Mode_in_Binary_Search_Tree_1 {
    /**
     * 如果不是二叉搜索树，最直观的方法一定是把这个树都遍历了，用map统计频率，把频率排个序，最后取前面高频的元素的集合。
     */
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
    /**
     * 暴力法
     */
    public int[] findMode1(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        if (root == null){
            return list.stream().mapToInt(Integer::intValue).toArray();
        }
        // 获得频率 Map
        searchBST1(root, map);

        List<Map.Entry<Integer, Integer>> mapList = map.entrySet().stream()
                .sorted((c1, c2) -> c2.getValue().compareTo(c1.getValue()))
                .collect(Collectors.toList());
        list.add(mapList.get(0).getKey());
        // 把频率最高的加入 list
        for (int i = 1; i < mapList.size(); i++) {
            if (mapList.get(i).getValue() == mapList.get(i - 1).getValue()) {
                list.add(mapList.get(i).getKey());
            } else {
                break;
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    void searchBST1(TreeNode curr, Map<Integer, Integer> map) {
        if (curr == null) {
            return;
        }
        map.put(curr.value, map.getOrDefault(curr.value, 0) + 1);
        searchBST1(curr.left, map);
        searchBST1(curr.right, map);
    }
}
