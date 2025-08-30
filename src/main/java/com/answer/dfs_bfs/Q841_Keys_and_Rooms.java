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
     * 本题是一个有向图搜索全路径的问题。 只能用深搜（DFS）或者广搜（BFS）来搜
     *
     * Approach #1: Depth-First Search - Recursion
     * 将这 n 个房间看成有向图中的 n 个节点，那么上述关系就可以看作是图中的 x 号点到 y 号点的一条有向边
     * 问题就变成了给定一张有向图，询问从 0 号节点出发是否能够到达所有的节点。
     */
    boolean[] visited; // 利用数组 visited 标记当前节点是否访问过，以防止重复访问。
    int num;

    public boolean canVisitAllRooms_0(List<List<Integer>> rooms) {
        visited = new boolean[rooms.size()];
        dfs(rooms, 0);

   /*    for(int i = 1; i < visited.length; i++){  // works too
            if(visited[i] == false){ // 检查是否都访问到了
                return false;
            }
        }
        return true;*/
        return num == rooms.size();
    }

    public void dfs(List<List<Integer>> rooms, int n) { // 处理当前访问的节点
        visited[n] = true;
        num++;
        for(int i : rooms.get(n)){
            if(!visited[i]){
                dfs(rooms, i);    // 深度优先搜索遍历
            }
        }
    }
    /**
     * 写法二：处理下一个要访问的节点
     */
    public boolean canVisitAllRooms_0a(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        Arrays.fill(visited, false);
        visited[0] = true; // 0 节点是出发节点，一定被访问过
        dfs3(rooms, 0, visited);
        //检查是否都访问到了
        for (boolean i : visited) {
            if (i == false) {
                return false;
            }
        }
        return true;
    }

    public void dfs3(List<List<Integer>> rooms, int n,   boolean[] visited) {
        List<Integer> keys = rooms.get(n);
        for (int key : keys) {
            if (visited[key] == false) {
                visited[key] = true;
                dfs3(rooms, key, visited);
            }
        }
    }
    /**
     * Approach #1: Depth-First Search - use Stack
     * 一个从中间向一个房间深入
     *  先找第 ０ 个房间的第一个钥匙
     *  进入那个房间，再找它的第一个钥匙
     *  重复以往，直到没钥匙了，那么退回刚刚的房间
     *  找刚刚房间的第二把钥匙
     *  重复以往
     * 深度优先搜索。
     *  我们设置一个栈，先把第一个房间添加进去
     *  规定每次从栈中取出一个钥匙
     *  找这个钥匙开的房间，并且把这个房间放到栈中。
     *  当这个栈为空的时候，说明遍历完成
     */
    public static boolean canVisitAllRooms_1(List<List<Integer>> rooms) {
        if(rooms.size() == 0) {
            return false;
        }
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
        if(rooms.size() == 0) {
            return false;
        }
        boolean[] visited = new boolean[rooms.size()];
        Arrays.fill(visited, false);

        Deque<Integer> queue = new ArrayDeque<>();
        List<Integer> keys = rooms.get(0);
        for(Integer key : keys){
            queue.offer(key);
        }
        //  0 号房间开始
        visited[0] = true; // can be commented

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
    /**
     * another form / BFS
     * 一个从0号房间向外一层层扩散的过程
     *  首先找到 0 号房间，把所有 ０ 号房间的钥匙都开一遍。
     *  进入刚刚开过的房间，再把它们房间里的钥匙再开一遍。
     *  重复以往，层层递进，直到找不到符合要求的节点。
     * 广度优先搜索
     *  设置一个队列，先把初始房间添加进去
     *  规定每次从队列取出一个房间
     *  把这个房间的所有钥匙放到队列中。
     *  当这个队列为空的时候，说明遍历完成
     */
    public boolean canVisitAllRooms4(List<List<Integer>> rooms) {
        int n = rooms.size();
        int num = 0;
        boolean[] visited = new boolean[n];

        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(0); //  0 号房间开始
        visited[0] = true;

        while (!queue.isEmpty()) {
            int x = queue.poll();
            num++;
            for (int next : rooms.get(x)) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }
        return num == n;
/*        for(int i = 1; i < visited.length; i++){ // works too
            if(visited[i] == false){
                return false;
            }
        }
        return true;*/
    }

}
