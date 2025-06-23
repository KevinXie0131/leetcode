package com.answer.graph;

import java.util.*;

public class Q802_Find_Eventual_Safe_States {
    /**
     * There is a directed graph of n nodes with each node labeled from 0 to n - 1. The graph is represented by a 0-indexed 2D integer array graph where graph[i] is an integer array of nodes adjacent to node i, meaning there is an edge from node i to each node in graph[i].
     * A node is a terminal node if there are no outgoing edges. A node is a safe node if every possible path starting from that node leads to a terminal node (or another safe node).
     * Return an array containing all the safe nodes of the graph. The answer should be sorted in ascending order.
     * 找到最终的安全状态
     * 有一个有 n 个节点的有向图，节点按 0 到 n - 1 编号。图由一个 索引从 0 开始 的 2D 整数数组 graph表示， graph[i]是与节点 i 相邻的节点的整数数组，这意味着从节点 i 到 graph[i]中的每个节点都有一条边。
     * 如果一个节点没有连出的有向边，则该节点是 终端节点 。如果从该节点开始的所有可能路径都通向 终端节点 ，则该节点为 终端节点（或另一个安全节点）。
     * 返回一个由图中所有 安全节点 组成的数组作为答案。答案数组中的元素应当按 升序 排列。
     *
     * 示例 1：
     *  输入：graph = [[1,2],[2,3],[5],[0],[5],[],[]]
     *  输出：[2,4,5,6]
     *  解释：节点 5 和节点 6 是终端节点，因为它们都没有出边。
     *       从节点 2、4、5 和 6 开始的所有路径都指向节点 5 或 6 。
     *       Nodes 5 and 6 are terminal nodes as there are no outgoing edges from either of them.
     *       Every path starting at nodes 2, 4, 5, and 6 all lead to either node 5 or 6.
     * 示例 2：
     *  输入：graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
     *  输出：[4]
     *  解释: 只有节点 4 是终端节点，从节点 4 开始的所有路径都通向节点 4 。
     *        Only node 4 is a terminal node, and every path starting at node 4 leads to node 4.
     *
     * graph[i] is sorted in a strictly increasing order. graph[i] 按严格递增顺序排列。
     * The graph may contain self-loops. 图中可能包含自环。
     */
    /**
     * 拓扑排序
     * 对于一个起始节点，如果从该节点出发，无论每一步选择沿哪条有向边行走，最后必然在有限步内到达终点，则将该起始节点称作是安全的。
     * 也就是说，对于某一个节点，如果它当前在某个环内，或者有可能走到某个环上，那么它就是不安全的，因为如果遇到环，就无法在有限步内到达终点。
     *
     * 拓扑排序的一个重要应用是在给定的有向图中判定是否存在环路
     */
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        List<List<Integer>> new_graph = new ArrayList<List<Integer>>(); // 反图，邻接表存储
        int[] Indeg = new int[n];  // 节点入度

        for(int i = 0; i < n; i++) {
            new_graph.add(new ArrayList<Integer>());
        }
        for(int i = 0; i < n; i++) {  // 存反向图，并统计入度
            for(int j = 0; j < graph[i].length; j++) {
                new_graph.get(graph[i][j]).add(i);
            }
            Indeg[i] = graph[i].length; // 原数组记录的节点出度，在反图中就是入度
        }
        // 拓扑排序
        Queue<Integer> q = new LinkedList<Integer>();  // BFS 求反向图拓扑排序
        for(int i = 0; i < n; i++) {// 首先将入度为 0 的点存入队列
            if(Indeg[i] == 0) {
                q.offer(i);
            }
        }
        while(!q.isEmpty()) {
            int cur = q.poll(); // 每次弹出队头元素
            for(int x : new_graph.get(cur)) {
                Indeg[x]--;// 将以其为起点的有向边删除，更新终点入度
                if(Indeg[x] == 0) {
                    q.offer(x);
                }
            }
        }
        // 最终入度（原图中出度）为 0 的所有点均为安全点
        List<Integer> ret = new ArrayList<Integer>();
        for(int i = 0; i < n; i++) {// 遍历答案：如果某个节点出现在拓扑序列，说明其进入过队列，说明其入度为 0
            if(Indeg[i] == 0) ret.add(i);
        }
        return ret;
    }
    /*
   拓扑排序:
   前置定义:如果从该节点开始的所有可能路径都通向终端节点，则该节点为安全节点。
   终端节点是指没有出边的节点，也就是出度为0的节点
   所有可能的路径(注意是所有)都指向出度为0的节点，表明怎么走都是有出口的不会成环，那问题就简单了
   不妨跟以往的常规拓扑排序反过来，我们以前是让入度为0的节点，最后检测出环
   我们现在可以让出度为0的节点优先入队，然后弹出删除时将指向该节点的节点出度-1，然后有出度为0的继续入队，以此类推...
   最后剩下的就是成环的节点，而我们要求的反过来的不成环的节点，最后进行排序
    */
    public List<Integer> eventualSafeNodes1(int[][] graph) {
        int n = graph.length;
        boolean[] vis = new boolean[n]; // 标记访问的(不在环中的节点)
        List<Integer>[] list = new ArrayList[n];    // idx:被指向的节点，list[idx]:出发指向idx的节点集合
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }
        int[] outDegrees = new int[n];  // 出度数组
        for (int i = 0; i < n; i++) {
            outDegrees[i] = graph[i].length;
            // 统计i指向的节点，终点的节点都是有i指向的
            for (int j = 0; j < graph[i].length; j++) {
                list[graph[i][j]].add(i);
            }
        }
        // 出度为0的优先入度
        Queue<Integer> que = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (outDegrees[i] == 0) {
                que.add(i);
                vis[i] = true;
            }
        }
        while (!que.isEmpty()) {
            int poll = que.poll();
            // 删除节点poll后，原本指向poll的节点全部入度-1，若有入度为0马上入队
            for (int num : list[poll]) {
                if (--outDegrees[num] == 0) {
                    que.add(num);
                    vis[num] = true;
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (vis[i]) res.add(i); // 不在环中的节点就是所求
        }
        return res;
    }
    /**
     * DFS深搜
     * 使用一个visited数组，0表示未访问，1表示访问中，2表示访问完毕且安全的节点，
     * 如果DFS的过程中再次遇到同一个节点（即visited[i]==1），则表示这个节点是不安全的。
     */
    public List<Integer> eventualSafeNodes3(int[][] graph) {
        List<Integer> resultList = new ArrayList<>(); // 存储结果集
        int n = graph.length;
        // 0表示未访问过，1表示访问中，2表示是安全点
        int[] visited = new int[n]; // 这里visited的作用有三个：1、记录DFS过程中访问过的节点；2、剪枝，防止重复访问；3、记录安全节点。

        for (int i = 0; i < n; i++) {
            if (safe(graph, i, visited)) {  // 如果从当前节点出发是安全的，则加入结果集中
                resultList.add(i);
            }
        }
        return resultList;
    }

    private boolean safe(int[][] graph, int index, int[] visited) {
        if (visited[index] != 0) {
            return visited[index] == 2;  // 不是0，说明被访问过，判断是否为2
        }
        // 说明未被访问过，设置为访问中
        visited[index] = 1;
        for (int next : graph[index]) {
            if (!safe(graph, next, visited)) { // DFS过程中再次遇到相同的节点，说明有环，即为不安全，直接返回false
                return false;
            }
        }
        visited[index] = 2; // 下游节点全部遍历完成，都是安全的，说明当前节点安全，返回true
        return true;
    }
    /**
     * 拓扑排序
     * 拓扑排序是寻找入度为0的节点，而题目给的数据是寻找最终能找到出度为0的节点，所以，我们可以用类似的思想。
     * 可以按照以下步骤进行：
     *  1. 先整理出所有节点的出度（就是graph子数组的长度），
     *  2. 把出度为0的节点入队，
     *  3. 从队列中弹出这些节点，将入他们的那些节点的出度减一，若减到0，则把那个节点也入队
     *  4. 重复上述步骤，直到队列中没有元素了
     * 因为要计算谁入了我，所以，要整理一下给定的数组，而你看到这样整理之后像是把原来的图倒过来了，所以，很多题解又叫他反图。
     */
    public List<Integer> eventualSafeNodes5(int[][] graph) {
        int n = graph.length;
        int[] deg = new int[n];// 记录每个节点的出度
        List<List<Integer>> list = new ArrayList<>();  // 记录谁入了我
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }
        // 整理数据
        for (int i = 0; i < n; i++) {
            deg[i] = graph[i].length;
            for (int j : graph[i]) {
                list.get(j).add(i);
            }
        }
        // BFS求拓扑排序的过程（此循环可以与上面的循环合并）
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (deg[i] == 0) {
                queue.offer(i);
            }
        }
        // 循环找出所有符合的节点
        while (!queue.isEmpty()) {
            int index = queue.poll();
            // 找出谁入了我，把他们的出度减一
            List<Integer> whos = list.get(index);
            for (Integer who : whos) {
                if (--deg[who] == 0) {
                    queue.offer(who);
                }
            }
        }
        // 最后，deg中为0的元素就是我们要找的安全节点
        List<Integer> resultList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (deg[i] == 0) {
                resultList.add(i);
            }
        }
        return resultList;
    }
}
