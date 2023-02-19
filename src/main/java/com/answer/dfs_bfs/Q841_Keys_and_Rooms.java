package com.answer.dfs_bfs;

import java.util.*;

public class Q841_Keys_and_Rooms {
    public static void main(String[] args) {
        List<List<Integer>> rooms = new ArrayList<>();
        rooms.add(Arrays.asList(1,3));
        rooms.add(Arrays.asList(3,0,1));
        rooms.add(Arrays.asList(2));
        rooms.add(Arrays.asList(0));
        System.out.println(canVisitAllRooms_1(rooms));
    }
    /**
     * Approach #1: Depth-First Search - Recursion
     */
    boolean[] visited;

    public boolean canVisitAllRooms_0(List<List<Integer>> rooms) {
        visited = new boolean[rooms.size()];
        dfs(rooms, 0);

        for(int i = 1; i < visited.length; i++){
            if(visited[i] == false){
                return false;
            }
        }
        return true;
    }
    public void dfs(List<List<Integer>> rooms, int n) {
        visited[n] = true;
        for(int i : rooms.get(n)){
            if(!visited[i]){
                dfs(rooms, i);
            }
        }
    }
    /**
     * Approach #1: Depth-First Search - use Stack
     */
    public static boolean canVisitAllRooms_1(List<List<Integer>> rooms) {
        if(rooms.size() == 0) return false;

        boolean[] visited = new boolean[rooms.size()];
        Arrays.fill(visited, false);

        Deque<Integer> stack = new ArrayDeque<>();
        List<Integer> keys = rooms.get(0);
        for(Integer key : keys){
            stack.push(key);
        }

        while(!stack.isEmpty()){
            Integer key = stack.pop();
            if(visited[key] == true){
                continue;
            }
            visited[key] = true;
            List<Integer> retrievedKeys = rooms.get(key);
            for(Integer n : retrievedKeys){
                stack.push(n);
            }
        }

        for(int i = 1; i < visited.length; i++){
            if(visited[i] == false){
                return false;
            }
        }
        return true;
    }
    /**
     * Approach 2: Breadth First Search
     */
    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        if(rooms.size() == 0) return false;

        boolean[] visited = new boolean[rooms.size()];
        Arrays.fill(visited, false);

        Deque<Integer> queue = new ArrayDeque<>();
        List<Integer> keys = rooms.get(0);
        for(Integer key : keys){
            queue.offer(key);
        }

        while(!queue.isEmpty()){
            Integer key = queue.poll();
            if(visited[key] == true){
                continue;
            }
            visited[key] = true;
            List<Integer> retrievedKeys = rooms.get(key);
            for(Integer n : retrievedKeys){
                queue.offer(n);
            }
        }

        for(int i = 1; i < visited.length; i++){
            if(visited[i] == false){
                return false;
            }
        }
        return true;
    }
}
