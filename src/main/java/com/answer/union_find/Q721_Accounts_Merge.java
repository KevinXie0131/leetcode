package com.answer.union_find;

import java.util.*;

public class Q721_Accounts_Merge {
    /**
     * 账户合并
     * 给定一个列表 accounts，每个元素 accounts[i] 是一个字符串列表，其中第一个元素 accounts[i][0] 是 名称 (name)，其余元素是 emails 表示该账户的邮箱地址。
     * 现在，我们想合并这些账户。如果两个账户都有一些共同的邮箱地址，则两个账户必定属于同一个人。请注意，即使两个账户具有相同的名称，
     * 它们也可能属于不同的人，因为人们可能具有相同的名称。一个人最初可以拥有任意数量的账户，但其所有账户都具有相同的名称。
     * 合并账户后，按以下格式返回账户：每个账户的第一个元素是名称，其余元素是 按字符 ASCII 顺序排列 (in sorted order) 的邮箱地址。
     * 账户本身可以以 任意顺序( any order) 返回。
     */
    public static void main(String[] args) {
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("John","johnsmith@mail.com","john_newyork@mail.com"));
        accounts.add(Arrays.asList("John","johnsmith@mail.com","john00@mail.com"));
        accounts.add(Arrays.asList("Mary","mary@mail.com"));
        accounts.add(Arrays.asList("John","johnnybravo@mail.com"));
        /**
         * 输出：[["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],
         * ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
         * 解释：
         * 第一个和第三个 John 是同一个人，因为他们有共同的邮箱地址 "johnsmith@mail.com"。
         * 第二个 John 和 Mary 是不同的人，因为他们的邮箱地址没有被其他帐户使用。
         * 可以以任何顺序返回这些列表，例如答案 [['Mary'，'mary@mail.com']，['John'，'johnnybravo@mail.com']，
         * ['John'，'john00@mail.com'，'john_newyork@mail.com'，'johnsmith@mail.com']] 也是正确的。
         */
        System.out.println(accountsMerge_1(accounts));
    }
    /**
     * 方法一：哈希表 + 并查集Union Find
     * 根据题意可知：
     *  存在相同邮箱的账号一定属于同一个人
     *  名称相同的账户不一定属于同一个人
     * 由于名称相同无法判断为同1人，所以只能使用邮箱是否相同来判断是否为同一人。
     * 这样建立并查集就比较简单了：
     *  先初始化每个账户为1个连通分量
     *  遍历每个账户下的邮箱，判断该邮箱是否在其他账户下出现
     *  如果未出现，继续
     *  如果账户A、B下出现了相同的邮箱email，那么将账户A和账户B两个连通分量进行合并
     *   最后遍历并查集中每个连通分量，将所有连通分量内部账户的邮箱全部合并(相同的去重，不同的合并)
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
    /**
     * Approach 1: Depth First Search (DFS)
     */
    static HashSet<String> visited = new HashSet<>();
    static Map<String, List<String>> adjacent = new HashMap<String, List<String>>();

    public static List<List<String>> accountsMerge_1(List<List<String>> accountList) {
        int accountListSize = accountList.size();

        for (List<String> account : accountList) {
            int accountSize = account.size();
            // Building adjacency list
            // Adding edge between first email to all other emails in the account
            String accountFirstEmail = account.get(1);
            for (int j = 2; j < accountSize; j++) {
                String accountEmail = account.get(j);

                if (!adjacent.containsKey(accountFirstEmail)) {
                    adjacent.put(accountFirstEmail, new ArrayList<String>());
                }
                adjacent.get(accountFirstEmail).add(accountEmail);

                if (!adjacent.containsKey(accountEmail)) {
                    adjacent.put(accountEmail, new ArrayList<String>());
                }
                adjacent.get(accountEmail).add(accountFirstEmail);
            }
        }

        // Traverse over all th accounts to store components
        List<List<String>> mergedAccounts = new ArrayList<>();
        for (List<String> account : accountList) {
            String accountName = account.get(0);
            String accountFirstEmail = account.get(1);

            // If email is visited, then it's a part of different component
            // Hence perform DFS only if email is not visited yet
            if (!visited.contains(accountFirstEmail)) {
                List<String> mergedAccount = new ArrayList<>();
                // Adding account name at the 0th index
                mergedAccount.add(accountName);

                DFS(mergedAccount, accountFirstEmail);
                Collections.sort(mergedAccount.subList(1, mergedAccount.size()));
                mergedAccounts.add(mergedAccount);
            }
        }
        return mergedAccounts;
    }

    static private void DFS(List<String> mergedAccount, String email) {
        visited.add(email);
        // Add the email vector that contains the current component's emails
        mergedAccount.add(email);

        if (!adjacent.containsKey(email)) {
            return;
        }

        for (String neighbor : adjacent.get(email)) {
            if (!visited.contains(neighbor)) {
                DFS(mergedAccount, neighbor);
            }
        }
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
