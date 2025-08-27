package com.answer.union_find;

import java.util.*;

public class Q721_Accounts_Merge_1 {
    /**
     * 哈希表 + DFS
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, List<Integer>> emailToIdx = new HashMap<>(); // key 为邮箱地址，value 为这个邮箱对应的账户下标列表。
        for (int i = 0; i < accounts.size(); i++) {
            for (int k = 1; k < accounts.get(i).size(); k++) {
                emailToIdx.computeIfAbsent(accounts.get(i).get(k), x -> new ArrayList<>()).add(i);
            }
        }

        List<List<String>> ans = new ArrayList<>();
        boolean[] visited = new boolean[accounts.size()];
        Set<String> emailSet = new HashSet<>(); // 用于收集 DFS 中访问到的邮箱地址

        for (int i = 0; i < accounts.size(); i++) {
            if (visited[i]) {
                continue;
            }
            emailSet.clear();

            dfs(i, accounts, emailToIdx, visited, emailSet);

            List<String> res = new ArrayList<>(emailSet);
            Collections.sort(res);
            res.add(0, accounts.get(i).get(0));

            ans.add(res);
        }
        return ans;
    }

    private void dfs(int i, List<List<String>> accounts, Map<String, List<Integer>> emailToIdx, boolean[] visited, Set<String> emailSet) {
        visited[i] = true;

        for (int k = 1; k < accounts.get(i).size(); k++) { // 遍历 i 的所有邮箱地址
            String email = accounts.get(i).get(k);
            if (emailSet.contains(email)) {
                continue;
            }
            emailSet.add(email);
            for (int j : emailToIdx.get(email)) { // 遍历所有包含该邮箱地址的账户下标 j
                if (!visited[j]) { // j 没有访问过
                    dfs(j, accounts, emailToIdx, visited, emailSet);
                }
            }
        }
    }
}
