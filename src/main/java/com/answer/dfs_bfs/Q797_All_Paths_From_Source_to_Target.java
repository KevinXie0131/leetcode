package com.answer.dfs_bfs;

import java.util.*;

public class Q797_All_Paths_From_Source_to_Target {
    public static void main(String[] args) {
        /**
         * 邻接表
         * 使用 数组 + 链表的方式来表示。 邻接表是从边的数量来表示图，有多少边 才会申请对应大小的链表。
         */
        int[][] graph = {{1,2},{3},{3},{}};
        System.out.println(allPathsSourceTarget_1(graph));
    }
    /**
     * 本题中的图为有向无环图DAG, 搜索过程中不会反复遍历同一个点, 因此我们无需判断当前点是否遍历过
     * Approach 1: Depth-First Search (DFS)  - Backtracking
     */
    List<List<Integer>> res= new ArrayList<>(); // 收集符合条件的路径
    Deque<Integer> stack = new ArrayDeque<>(); // 1节点到终点的路径

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        stack.push(0);
        dfs(graph, 0 , graph.length - 1);
        return res;
    }
    // 邻接表写法
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
     */
    public List<List<Integer>> allPathsSourceTarget1(int[][] graph) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        path.add(0);
        dfs1(graph, 0 , graph.length - 1, result, path);
        return result;
    }
    public void dfs1(int[][] graph, int x, int n,  List<List<Integer>> result, List<Integer> path ) {
        if(x == n){
            result.add(new ArrayList<>(path));
            return;
        }
        for(int y : graph[x]){
            path.add(y);
            dfs1(graph, y , graph.length - 1, result, path);
            path.remove(path.size() - 1);
        }
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
     * Breadth First Search
     */
    public static List<List<Integer>> allPathsSourceTarget_1(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        int size = graph.length;
        Deque<List<Integer>> queue = new ArrayDeque<>();
        queue.offer(Arrays.asList(0));

        while(!queue.isEmpty()){
            /**
             * Note: this line is usefule
             */
            int len = queue.size();
            for(int i = 0; i < len; i++){
                List<Integer> cur = queue.poll();
                int last = cur.get(cur.size() - 1);
                if(last == size - 1){
                    res.add(cur);
                    continue;
                }
                int[] route = graph[last];
                for(int num : route){
                    List<Integer> list = new ArrayList<>(cur);
                    list.add(num);
                    queue.offer(list);
                }
            }
        }
        return res;
    }
}
