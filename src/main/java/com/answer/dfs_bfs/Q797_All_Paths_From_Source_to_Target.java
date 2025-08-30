package com.answer.dfs_bfs;

import java.util.*;

public class Q797_All_Paths_From_Source_to_Target {
    /**
     * Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to node n - 1 and return them in any order.
     * The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).
     * 所有可能的路径
     * 一个有 n 个节点的 有向无环图（DAG），请你找出从节点 0 到节点 n-1 的所有路径并输出（不要求按特定顺序）
     * graph[i] 是一个从节点 i 可以访问的所有节点的列表（即从节点 i 到节点 graph[i][j]存在一条有向边）。
     * 示例：
     *   输入：graph = [[1,2],[3],[3],[]]
     *   输出：[[0,1,3],[0,2,3]]
     *   解释：有两条路径 0 -> 1 -> 3 和 0 -> 2 -> 3
     */
    /**
     * 深搜三部曲
     * dfs写法一：处理当前访问的节点
     *     void dfs1(ArrayList<ArrayList<Integer>> graph, int key, boolean[] visited) {
     *         if (visited[key]) {
     *             return;
     *         }
     *         visited[key] = true;
     *         ArrayList<Integer> keys = graph.get(key);
     *         for (int nextKey : keys) {
     *             // 深度优先搜索遍历
     *             dfs1(graph, nextKey, visited);
     *         }
     *     }
     * dfs写法二：处理下一个要访问的节点 (不需要终止条件，而是在 搜索下一个节点的时候，直接判断 下一个节点是否是我们要搜的节点。)
     *     void dfs2(ArrayList<ArrayList<Integer>> graph, int key, boolean[] visited) {
     *         ArrayList<Integer> keys = graph.get(key);
     *         for (int nextKey : keys) {
     *             if (visited[nextKey] == false) { // 确认下一个是没访问过的节点
     *                 visited[nextKey] = true;
     *                 dfs2(graph, nextKey, visited);
     *             }
     *         }
     *     }
     */
    public static void main(String[] args) {
        /**
         * 邻接表
         * 使用 数组 + 链表的方式来表示。 邻接表是从边的数量来表示图，有多少边 才会申请对应大小的链表。
         */
        int[][] graph = {{1,2},{3},{3},{}};
        System.out.println(allPathsSourceTarget1a(graph));
    }
    /**
     * 本题中的图为有向无环图DAG, 搜索过程中不会反复遍历同一个点, 因此我们无需判断当前点是否遍历过
     * Approach 1: Depth-First Search (DFS) - Backtracking
     */
    List<List<Integer>> res= new ArrayList<>(); // 收集符合条件的路径
    Deque<Integer> stack = new ArrayDeque<>(); // 节点1到终点的路径
    // 深度优先搜索 + 栈
    // 特别地，因为本题中的图为有向无环图（DAG），搜索过程中不会反复遍历同一个点，因此我们无需判断当前点是否遍历过。
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        stack.push(0);
        dfs(graph, 0 , graph.length - 1);
        return res;
    }
    // 邻接表写法 使用stack
    public void dfs(int[][] graph, int x, int n) {
        // 当前遍历的节点x 到达节点n
        if(x == n){
            // 找到符合条件的一条路径
            List<Integer> list = new ArrayList<>(stack); // Note: one should make a deep copy of the path
            Collections.reverse(list);
            res.add(list);
            return;
        }
        for(int y : graph[x]){ // 找到 x指向的节点
            stack.push(y); // 遍历到的节点加入到路径中来
            dfs(graph, y , graph.length - 1); // 进入下一层递归
            stack.pop();  // 回溯，撤销本节点
        }
    }
    /**
     * 另一种形式DFS 使用arraylist
     * n 只有 15，且要求输出所有方案，因此最直观的解决方案是使用 DFS 进行爆搜。
     * 起始将 0 进行加入当前答案，当 n−1 被添加到当前答案时，说明找到了一条从 0 到 n−1 的路径，将当前答案加入结果集。
     */
    public List<List<Integer>> allPathsSourceTarget1(int[][] graph) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        path.add(0);  //始终从0开始，所以总是需要把节点0加入
        dfs1(graph, 0 , graph.length - 1, result, path);
        return result;
    }
    //深度搜索，第一个参数是要遍历的图，第二参数是当前节点编号
    public void dfs1(int[][] graph, int x, int n,  List<List<Integer>> result, List<Integer> path ) {
        if(x == n){ //如果遍历到最后一个节点，则停止遍历
            // 使用new LinkedList<>(path)创建path的一个副本，以避免后续对path的修改影响已经添加的结果
            result.add(new LinkedList<>(path));    //达到目标节点，保存此条路径并结束搜索
            return;
        }
        //遍历当前节点所有关联的节点
        for(int y : graph[x]){
            path.add(y);      //将当前节点保存在本次搜索路径中
            dfs1(graph, y , graph.length - 1, result, path); //继续遍历与当前节点关联的节点
            path.remove(path.size() - 1);  //回溯(LinkedList的remove()方法传入整数会被作为下标，使用path.size()-1可以严格控制删除最后一个元素)
         //   path.removeLast(); // for ArrayDeque
        }
    }
    /**
     * 记忆化递归
     */
    static Map<Integer, List<List<Integer>>> cache;

    static public List<List<Integer>> allPathsSourceTarget1a(int[][] graph) {
        cache = new HashMap<>();
        return dfs4(graph, 0);
    }

    static public List<List<Integer>> dfs4(int[][] graph, int node){
        if(cache.containsKey(node)) {
            return cache.get(node);
        }
        List<List<Integer>> res = new ArrayList<>();
        if(node == graph.length - 1){
            List<Integer> cur = new ArrayList<>();
            cur.add(node);
            res.add(cur);
        }else{
            for(int next : graph[node]){
                for(List<Integer> list: dfs4(graph, next)){
                    LinkedList<Integer> cur = new LinkedList(list);
                    cur.addFirst(node);
                    res.add(cur);
                }
            }
        }
        cache.put(node, res);
        return res;
    }
    /**
     * 邻接矩阵
     * 邻接矩阵 使用 二维数组来表示图结构。 邻接矩阵是从节点的角度来表示图，有多少节点就申请多大的二维数组。
     *
     * for (int i = 1; i <= n; i++) { // 遍历节点x链接的所有节点
     *      if (graph[x][i] == 1) { // 找到 x链接的节点
     *          path.add(i); // 遍历到的节点加入到路径中来
     *          dfs(graph, i, n); // 进入下一层递归
     *          path.remove(path.size() - 1); // 回溯，撤销本节点
     *      }
     *  }
     */
    /**
     * BFS
     * 类似于树的层序遍历，每一“层”要存储已经过的路径。
     * 1、初始化“半路路径”列表ans为：[[0]]
     * 2、对于每个之前的“半路路径”，如果以k结尾，就把k邻接的m个下一个节点，放进下一轮的“半路路径”列表循环里（如果这m个节点里有n-1，就放入结果r里，不再循环）。
     */
    public List<List<Integer>> allPathsSourceTarget3(int[][] graph) {
        int n = graph.length;
        Queue<List<Integer>> queue = new LinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);  //把起始节点0加进来
        queue.offer(list);

        List<List<Integer>> res = new ArrayList<>();
        while(queue.size() > 0){ // bfs，从0开始一层一层向外搜索
            List<Integer> path = queue.poll();
            int node = path.get(path.size() - 1);
            if(node == n - 1) {  //到最后一个节点的时候，说明找了一个一条有效路径
                res.add(path);
            } else {
                for (int next : graph[node]) { //当前节点指向哪些节点（可以看做是n叉树的子节点，然后遍历他的子节点）
                    List<Integer> nextPath = new ArrayList<>(path);
                    nextPath.add(next);   //把当前节点加入到路径中
                    queue.add(nextPath);
                }
            }
        }
        return res;
    }
    /**
     * Breadth First Search
     * 每次从队列中取出一个path，首先判断其是否已经到达终点：若到达，则将这个path加到res中；若没有到达，则取path的最后一个元素，
     * 循环遍历该元素的邻居节点（graph对应的列表，即由该节点可以到达的下一个节点），将原path拼接当前邻居得到的每一条新路径都入队。
     */
    public static List<List<Integer>> allPathsSourceTarget_1(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        int size = graph.length;
        Deque<List<Integer>> queue = new ArrayDeque<>();
        queue.offer(Arrays.asList(0));

        while(!queue.isEmpty()){
            /**
             * Note: this line is useful
             */
            int len = queue.size();
            for(int i = 0; i < len; i++){
                List<Integer> cur = queue.poll();
                int last = cur.get(cur.size() - 1);  // 取path的最后一个元素 看是不是目标len(graph)-1
                if(last == size - 1){
                    res.add(cur); //  此路径已经完成 加入到结果
                    continue;
                }
                int[] route = graph[last];
                for(int num : route){ // 未完成 遍历path最后一个元素的邻居节点
                    List<Integer> list = new ArrayList<>(cur);
                    list.add(num); //  将原path拼接当前邻居得到的新路径入队
                    queue.offer(list);
                }
            }
        }
        return res;
    }
}
