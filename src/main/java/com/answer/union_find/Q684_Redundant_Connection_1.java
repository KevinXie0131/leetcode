package com.answer.union_find;

import java.util.*;

public class Q684_Redundant_Connection_1 {
    /**
     * DFS Recursion
     */
    public int[] findRedundantConnection(int[][] edges) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] edge : edges) {
            int source = edge[0];
            int target = edge[1];
            if(dfs(source, target, map, new HashSet<>())){
                return edge;
            }
            map.computeIfAbsent(source, i-> new ArrayList<>()).add(target);
            map.computeIfAbsent(target, i-> new ArrayList<>()).add(source);
        }
        return new int[0];
    }

    private boolean dfs(int source, int target, Map<Integer, List<Integer>> map, Set<Integer> visited) {
        if (source == target) {
            return true;
        }
        visited.add(source);
        List<Integer> neighbors = map.get(source);
        if(neighbors == null) {
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
     * DFS Iteration
     */
    public int[] findRedundantConnection1(int[][] edges) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] edge : edges) {
            int source = edge[0];
            int target = edge[1];

            Deque<Integer> stack = new ArrayDeque<>();
            Set<Integer> visited = new HashSet<>();
            visited.add(source);
            stack.push(source);
            while(!stack.isEmpty()){
                int e = stack.pop();
                if(e == target){
                    return edge;
                }
                List<Integer> neighbors = map.get(e);
                if(neighbors != null) {
                    for (int i = 0; i < neighbors.size(); i++) {
                        int neighbor = neighbors.get(i);
                        if(!visited.contains(neighbor)){
                            visited.add(neighbor);
                            stack.push(neighbor);
                        }
                    }
                }
            }
            map.computeIfAbsent(source, i-> new ArrayList<>()).add(target);
            map.computeIfAbsent(target, i-> new ArrayList<>()).add(source);
        }
        return new int[0];
    }
    /**
     * BFS Iteration
     */
    public int[] findRedundantConnection2(int[][] edges) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] edge : edges) {
            int source = edge[0];
            int target = edge[1];

            Deque<Integer> queue = new ArrayDeque<>();
            Set<Integer> visited = new HashSet<>();
            visited.add(source);
            queue.offer(source);
            while(!queue.isEmpty()){
                int e = queue.poll();
                if(e == target){
                    return edge;
                }
                List<Integer> neighbors = map.get(e);
                if(neighbors != null) {
                    for (int i = 0; i < neighbors.size(); i++) {
                        int neighbor = neighbors.get(i);
                        if(!visited.contains(neighbor)){
                            visited.add(neighbor);
                            queue.offer(neighbor);
                        }
                    }
                }
            }
            map.computeIfAbsent(source, i-> new ArrayList<>()).add(target);
            map.computeIfAbsent(target, i-> new ArrayList<>()).add(source);
        }
        return new int[0];
    }
}
