package com.answer.tree;

import java.util.*;

public class Q429_Nary_Tree_Level_Order_Traversal_1 {

    List<List<Integer>> resListRec = new ArrayList<>();

    public List<List<Integer>> levelOrder(Node root) {
        dfs(root, 0);
        return resListRec;
    }
    /**
     * 递归
     */
    public void dfs(Node root, int deep) {
        if (root == null){
            return;
        }
        deep++;

        if (resListRec.size() < deep) {
            List<Integer> sublist = new ArrayList<>();
            resListRec.add(sublist);
        }

        resListRec.get(deep - 1).add(root.val);

        for (Node child : root.children) {
            dfs(child,  deep);
        }
    }
    /**
     * 递归(另一种形式)
     */
    public void dfs_1(Node root, int deep) {
        if (root == null) {
            return;
        }

        if (resListRec.size() == deep) {
            List<Integer> sublist = new ArrayList<>();
            resListRec.add(sublist);
        }

        resListRec.get(deep).add(root.val);

        for (Node child : root.children) {
            dfs(child,  deep + 1);
        }
    }
    /**
     * DFS / 借助哈希表
     */
    Map<Integer, List<Integer>> map = new HashMap<>();
    int max;

    public List<List<Integer>> levelOrder2(Node root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null){
            return ans;
        }
        dfs_2(root, 0);
        for (int i = 0; i <= max; i++) {
            ans.add(map.get(i));
        }
        return ans;
    }

    public void dfs_2(Node root, int depth) {
        if (root == null) {
            return;
        }
        max = Math.max(max, depth);
        List<Integer> list = map.getOrDefault(depth, new ArrayList<>());
        list.add(root.val);
        map.put(depth, list);

        for (Node child : root.children) {
            dfs_2(child,  depth + 1);
        }
    }
    /**
     * another form
     */
    public List<List<Integer>> levelOrder3(Node root) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        dfs_3(root, 0, map);
        return new ArrayList<>(map.values());
    }

    public void dfs_3(Node root, int depth, Map<Integer, List<Integer>> map) {
        if (root == null) {
            return;
        }
        map.computeIfAbsent(depth, e -> new ArrayList<>()).add(root.val);

        for (Node child : root.children) {
            dfs_3(child,  depth + 1, map);
        }
    }
}

