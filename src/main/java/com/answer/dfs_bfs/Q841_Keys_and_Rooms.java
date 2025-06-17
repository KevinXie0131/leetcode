package com.answer.dfs_bfs;

import java.util.*;

public class Q841_Keys_and_Rooms {
    /**
     * 钥匙和房间
     * 有 n 个房间，房间按从 0 到 n - 1 编号。最初，除 0 号房间外的其余所有房间都被锁住。你的目标是进入所有的房间。然而，你不能在没有获得钥匙的时候进入锁住的房间。
     * 当你进入一个房间，你可能会在里面找到一套 不同的钥匙(distinct keys)，每把钥匙上都有对应的房间号，即表示钥匙可以打开的房间。你可以拿上所有钥匙去解锁其他房间。
     * 给你一个数组 rooms 其中 rooms[i] 是你进入 i 号房间可以获得的钥匙集合。如果能进入 所有 房间返回 true，否则返回 false。
     * Given an array rooms where rooms[i] is the set of keys that you can obtain if you visited room i, return true if you can visit all the rooms, or false otherwise.
     *
     * 示例 1：
     *  输入：rooms = [[1],[2],[3],[]]
     *  输出：true
     *  解释：我们从 0 号房间开始，拿到钥匙 1。
     *      之后我们去 1 号房间，拿到钥匙 2。
     *      然后我们去 2 号房间，拿到钥匙 3。
     *      最后我们去了 3 号房间。由于我们能够进入每个房间，我们返回 true。
     * 示例 2：
     *  输入：rooms = [[1,3],[3,0,1],[2],[0]]
     *  输出：false
     *  解释：我们不能进入 2 号房间。 We can not enter room number 2 since the only key that unlocks it is in that room.
     */
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
