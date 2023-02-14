package com.answer.union_find;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Q721_Accounts_Merge {
    /**
     * 方法一：哈希表 + 并查集
     * Union Find
     */
    int[] parent;

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // 作用：存储每个邮箱属于哪个账户 ，同时 在遍历邮箱时，判断邮箱是否出现过[去重]
        // 格式：<邮箱，账户id>
        Map<String, Integer> emailToId = new HashMap<>();
        int n = accounts.size();//id个数

        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for(int i = 0; i < n; i++){
            int num = accounts.get(i).size();
            for(int j = 1; j < num; j++){
                String email = accounts.get(i).get(j);
                if(!emailToId.containsKey(email)){ //当前邮箱没有出现过
                    emailToId.put(email, i);
                }else{
                    union(parent, i, emailToId.get(email)); //当前邮箱已经出现过，那么代表这两个用户是同一个
                }
            }
        }
        //进行完上面的步骤，同一个用户的所有邮箱已经属于同一个连通域了，但是就算在同一个连通域，不同的邮箱还是可能会对应不同的id
        // 作用： 存储每个账户下的邮箱
        // 格式： <账户id, 邮箱列表> >
        // 注意：这里的key必须是账户id，不能是账户名称，名称可能相同，会造成覆盖
        Map<Integer, List<String>> idToEmails = new HashMap<>();
        //将同一个连通域内的邮箱对应到同一个id【也就是第一次出现的id，比如4、5在同一个连通域，那么这个连通域对应的id就是4】
        for(Map.Entry<String, Integer> entry : emailToId.entrySet()){
            int id = find(parent, entry.getValue());
            List<String> emails = idToEmails.getOrDefault(id , new ArrayList<>());
            emails.add(entry.getKey());
            idToEmails.put(id, emails);
        }
        //经过上面的步骤，已经做到了id和邮箱集合对应起来，接下来把用户名对应起来就可以了
        List<List<String>> res = new ArrayList<>();
        for(Map.Entry<Integer, List<String>> entry : idToEmails.entrySet()){
            List<String> emails = entry.getValue();
            Collections.sort(emails);
            List<String> temp = new ArrayList<>();
            temp.add(accounts.get(entry.getKey()).get(0)); //先添加用户名
            temp.addAll(emails);
            res.add(temp);
        }
        return res;
    }
    public static void union(int[] parent, int index1, int index2) {
        parent[find(parent, index1)] = find(parent, index2);
    }

    public static int find(int[] parent, int index) {
        if (parent[index] != index) {
            parent[index] = find(parent, parent[index]);
        }
        return parent[index];
    }
}

class UnionFind {
    int[] parent;

    public UnionFind(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public void union(int index1, int index2) {
        parent[find(index2)] = find(index1);
    }

    public int find(int index) {
        if (parent[index] != index) {
            parent[index] = find(parent[index]);
        }
        return parent[index];
    }
}
