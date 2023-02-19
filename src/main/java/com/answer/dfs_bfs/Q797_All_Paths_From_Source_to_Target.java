package com.answer.dfs_bfs;

import java.util.*;

public class Q797_All_Paths_From_Source_to_Target {
    public static void main(String[] args) {
        int[][] graph = {{1,2},{3},{3},{}};
        System.out.println(allPathsSourceTarget_1(graph));
    }
    /**
     * 本题中的图为有向无环图DAG, 搜索过程中不会反复遍历同一个点, 因此我们无需判断当前点是否遍历过
     * Approach 1: Depth-First Search (DFS)  - Backtracking
     */
    List<List<Integer>> res= new ArrayList<>();
    Deque<Integer> stack = new ArrayDeque<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        stack.push(0);
        dfs(graph, 0 , graph.length - 1);
        return res;
    }

    public void dfs(int[][] graph, int x, int n) {
        if(x == n){
            List<Integer> list = new ArrayList<>(stack); // Note: one should make a deep copy of the path
            Collections.reverse(list);
            res.add(list);
            return;
        }
        for(int y : graph[x]){
            stack.push(y);
            dfs(graph, y , graph.length - 1);
            stack.pop();
        }
    }
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
