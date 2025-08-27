package com.answer.union_find;

import java.util.*;

public class Q684_Redundant_Connection {
    /**
     * 冗余连接
     * In this problem, a tree is an undirected graph that is connected and has no cycles.
     * 树可以看成是一个连通且 无环 的 无向 图。
     *
     * 给定往一棵 n 个节点 (节点值 1～n) 的树中添加一条边后的图。添加的边的两个顶点 (two different vertices) 包含在 1 到 n 中间，
     * 且这条附加的边不属于树中已存在的边。图的信息记录于长度为 n 的二维数组 edges ，edges[i] = [ai, bi]
     * 表示图中在 ai 和 bi 之间存在一条边。
     * Return an edge that can be removed so that the resulting graph is a tree of n nodes. If there are multiple answers,
     * return the answer that occurs last in the input.
     * 请找出一条可以删去的边，删除后可使得剩余部分是一个有着 n 个节点的树。如果有多个答案，则返回数组 edges 中最后出现的那个。
     *
     * There are no repeated edges.
     * The given graph is connected.
     * edges 中无重复元素
     * 给定的图是连通的
     */
    public static void main(String[] args) {
        int[][] edges = {{1,2},{1,3},{2,3}}; // 输出: [2,3]
     //   int[][] edges = {{1,2},{2,3},{3,4},{1,4},{1,5}}; // 输出: [1,4]
        System.out.println(Arrays.toString(findRedundantConnection9(edges)));
    }
    /**
     * 并查集基础题目。并查集可以解决什么问题：两个节点是否在一个集合，也可以将两个节点添加到一个集合中。
     * 已经判断 节点A 和 节点B 在在同一个集合（同一个根），如果将 节点A 和 节点B 连在一起就一定会出现环。
     * 判断一下边的两个节点在不在同一个集合就可以了
     */
    public int[] findRedundantConnection0(int[][] edges) {
        _UnionFindTemplate disJoint = new _UnionFindTemplate(edges.length + 1);

        for (int i = 0; i < edges.length; ++i) {
            if(disJoint.isSame(edges[i][0], edges[i][1])){
                return new int[]{edges[i][0], edges[i][1]};
            }else{
                disJoint.join(edges[i][0], edges[i][1]);
            }
        }
        return new int[]{0, 0};
    }
    /**
     * Approach #2: Union-Find
     */
    public static int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        int[] parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < n; i++) {
            int[] edge = edges[i];
            int node1 = edge[0], node2 = edge[1];
            if (find(parent, node1) != find(parent, node2)) {
                union(parent, node1, node2);   // 如果两个节点不在一个树上，则合并
            } else {
                return edge;  // 如果两个节点已经在一个树上，则这条边就是那条多余的边
            }
        }
        return new int[0];
    }

    public static void union(int[] parent, int index1, int index2) {
        parent[find(parent, index2)] = find(parent, index1);
    }

    public static int find(int[] parent, int index) {
        if (parent[index] != index) {
            parent[index] = find(parent, parent[index]);
        }
        return parent[index];
    }
    /**
     * 简单实现并查集
     * based on template
     */
    static private int[] father;

    static public int[] findRedundantConnection9(int[][] edges) {
        int N = edges.length;
        father = new int[N + 1]; // 节点值 1～n
        for (int i = 1; i <= N; i++) {
            father[i] = i;
        }

        for (int i = 0; i < N; i++) {
            int[] edge = edges[i];
            int node1 = edge[0], node2 = edge[1];
            if (!isSame(node1, node2)) {
                join(node1, node2);   // 如果两个节点不在一个树上，则合并
            } else {
                return edge;  // 如果两个节点已经在一个树上，则这条边就是那条多余的边
            }
        }
        return new int[0];
    }

    static public int find(int n) {
        return n == father[n] ? n : (father[n] = find(father[n]));
    }

    static public void join (int n, int m) {
        n = find(n);
        m = find(m);
        if (n == m) {
            return;
        }
        father[m] = n; // 找到根节点后，x根做y根的子树，y根做x根的子树都可以
    }

    static public boolean isSame(int n, int m){
        n = find(n);
        m = find(m);
        return n == m;
    }
    /**
     * Approach #1: DFS
     */
    Set<Integer> seen = new HashSet();
    int MAX_EDGE_VAL = 1000;

    public int[] findRedundantConnection_1(int[][] edges) {
        ArrayList<Integer>[] graph = new ArrayList[MAX_EDGE_VAL + 1];
/*        for (int i = 0; i <= MAX_EDGE_VAL; i++) { // works too
            graph[i] = new ArrayList();
        }*/
        Arrays.setAll(graph, i ->  new ArrayList());

        for (int[] edge: edges) {
            seen.clear();
            if (!graph[edge[0]].isEmpty() && !graph[edge[1]].isEmpty() && dfs(graph, edge[0], edge[1])) {
                return edge;
            }
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        return new int[]{0, 0};
    }

    public boolean dfs(ArrayList<Integer>[] graph, int source, int target) {
        if (!seen.contains(source)) {
            seen.add(source);
            if (source == target){
                return true;
            }
            for (int nei: graph[source]) {
                if (dfs(graph, nei, target)) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * DFS搜索邻接表，如果发现从n节点到m节点通过邻接表可以达到则这个edge（n,m）为多余的直接返回该边即可。
     */
    public int[] findRedundantConnection6(int[][] edges) {
        // 邻接表 键为节点，值为相邻节点
        Map<Integer, List<Integer>> map = new HashMap<>(); // 首先初始化一个邻接表，用来存放每个节点的邻接节点Map
        for (int[] edge : edges) {
            int n = edge[0]; // 依次获取需要判断的每一个边edge ，将第一个（起始点）即为n，第二个（目标点）即为m。
            int m = edge[1];
            //防治重复搜索已经dfs过的节点出现死循环
            Set<Integer> visited = new HashSet<>();
            // dfs 判断 n m 是否可以连同
            // 进入dfs搜索邻接表的数据判断在邻接表中是否已经存在一条路径使得n可以到达m，如果存在说明当前需要判断的这个edge（n，m）是一个多余的即为题目需要找到的，如果dfs这没找到，我们需要更新邻接表，将当前的n与m更新进去
            if(dfs(n, m, map, visited)){
                return edge;
            }
         /*   if(!map.containsKey(n)){ // works too
                map.put(n,new ArrayList<>());
            }
            map.get(n).add(m);
            if(!map.containsKey(m)){
                map.put(m,new ArrayList<>());
            }
            map.get(m).add(n);*/
            map.computeIfAbsent(n, i-> new ArrayList<>()).add(m);
            map.computeIfAbsent(m, i-> new ArrayList<>()).add(n);
        }
        return new int[0];
    }
    // visited也是必要的，因为我们不能反复查询同一节点这样会陷入死循环
    private boolean dfs(int source, int target, Map<Integer, List<Integer>> map, Set<Integer> visited) {
        if (source == target) { //同节点之间输出
            return true;
        }
        visited.add(source);
        List<Integer> neighbors  = map.get(source);
        if (neighbors == null) {
            return false;
        }
        for (int i = 0; i < neighbors.size(); i++) {
            int neighbor = neighbors.get(i);
            if(!visited.contains(neighbor) && dfs(neighbor, target, map, visited)){
                return true;
            }
        }
        return false;
    }
    /**
     * 拓扑排序
     * 拓扑排序一般用于有向无环图（DAG）。对于本题（无向图），用拓扑排序不是标准做法，但我们可以模拟“不断删除度为1的节点”，最后剩下的那条边就是成环的冗余边。
     */
    public int[] findRedundantConnection8(int[][] edges) {
        int n = edges.length;
        List<Set<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++){
            graph.add(new HashSet<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        // 记录图中每个节点的度
        int[] degree = new int[n + 1]; //节点值 1～n
        for (int i = 1; i <= n; i++){
            degree[i] = graph.get(i).size();
        }
        // 拓扑排序思想：不断删掉度为1的节点
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (degree[i] == 1) {
                queue.offer(i);
            }
        }

        boolean[] removed = new boolean[n + 1]; // 节点值 1～n
        while (!queue.isEmpty()) {
            int node = queue.poll();
            removed[node] = true;
            for (int neighbor : graph.get(node)) {
                if (!removed[neighbor]) {
                    degree[neighbor]--;
                    if (degree[neighbor] == 1) {
                        queue.offer(neighbor);
                    }
                }
            }
        }
        // 最后剩下的边就是环上的边，找最后出现的那个
        for (int i = edges.length - 1; i >= 0; i--) { // 如果有多个答案，则返回数组 edges 中最后出现的那个。
            int u = edges[i][0], v = edges[i][1];
            if (!removed[u] && !removed[v]) {
                return edges[i];
            }
        }
        return new int[0];
    }
}
