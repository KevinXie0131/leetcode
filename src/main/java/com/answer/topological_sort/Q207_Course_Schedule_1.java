package com.answer.topological_sort;

import java.util.*;

public class Q207_Course_Schedule_1 {
    /**
     * 广度优先搜索
     * 1.在开始排序前，扫描对应的存储空间（使用邻接表），将入度为 0 的结点放入队列。
     * 2、只要队列非空，就从队首取出入度为 0 的结点，将这个结点输出到结果集中，并且将这个结点的所有邻接结点（它指向的结点）的入度减 1，在减 1 以后，如果这个被减 1 的结点的入度为 0 ，就继续入队。
     * 3、当队列为空的时候，检查结果集中的顶点个数是否和课程数相等即可。
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0) {
            return false;
        }
        // 特判
        int pLen = prerequisites.length;
        if (pLen == 0) {
            return true;
        }
        int[] inDegree = new int[numCourses]; // 入度数组：通过结点的索引，我们能够得到指向这个结点的结点个数。
        HashSet<Integer>[] adj = new HashSet[numCourses];// 邻接表：通过结点的索引，我们能够得到这个结点的后继结点；
        for (int i = 0; i < numCourses; i++) {
            adj[i] = new HashSet<>();
        }
        for (int[] p : prerequisites) {
            inDegree[p[0]]++;
            adj[p[1]].add(p[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        // 首先加入入度为 0 的结点
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        // 记录已经出队的课程数量
        int cnt = 0;
        while (!queue.isEmpty()) {
            Integer top = queue.poll();
            cnt += 1;
            // 遍历当前出队结点的所有后继结点
            for (int successor : adj[top]) {
                inDegree[successor]--;
                if (inDegree[successor] == 0) {
                    queue.add(successor);
                }
            }
        }
        return cnt == numCourses;
    }
    /**
     * 深度优先遍历
     * 首先检测是否存在环，然后使用「深度优先遍历」，在「后序」的部分把课程添加到结果集，然后再逆序，就是「拓扑排序」的结果（没有提供参考代码）；
     * 在深度优先遍历的过程中，设置个别有特殊意义的变量，通过这些变量得到「拓扑排序」的结果（下面提供了参考代码）。
     *
     * 要使用逆邻接表。其实就是检测这个有向图中有没有环，只要存在环，这些课程就不能按要求学完
     *
     * 第 1 步：构建逆邻接表；
     * 第 2 步：递归处理每一个还没有被访问的结点，具体做法很简单：对于一个结点来说，先输出指向它的所有顶点，再输出自己。
     * 第 3 步：如果这个顶点还没有被遍历过，就递归遍历它，把所有指向它的结点都输出了，再输出自己。注意：当访问一个结点的时候，应当先递归访问它的前驱结点，直至前驱结点没有前驱结点为止。
     */
    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0) {
            return false;
        }
        int plen = prerequisites.length;
        if (plen == 0) {
            return true;
        }
        int[] marked = new int[numCourses];
        // 初始化有向图 begin
        HashSet<Integer>[] graph = new HashSet[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new HashSet<>();
        }
        // 初始化有向图 end
        // 有向图的 key 是前驱结点，value 是后继结点的集合
        for (int[] p : prerequisites) {
            graph[p[1]].add(p[0]);
        }

        for (int i = 0; i < numCourses; i++) {
            if (dfs(i, graph, marked)) {
                // 注意方法的语义，如果图中存在环，表示课程任务不能完成，应该返回 false
                return false;
            }
        }
        // 在遍历的过程中，一直 dfs 都没有遇到已经重复访问的结点，就表示有向图中没有环
        // 所有课程任务可以完成，应该返回 true
        return true;
    }
    /**
     * 注意这个 dfs 方法的语义
     * @param i      当前访问的课程结点
     * @param graph
     * @param marked 如果 == 1 表示正在访问中，如果 == 2 表示已经访问完了
     * @return true 表示图中存在环，false 表示访问过了，不用再访问了
     */
    private boolean dfs(int i,
                        HashSet<Integer>[] graph,
                        int[] marked) {
        if (marked[i] == 1) {// 如果访问过了，就不用再访问了
            return true;// 从正在访问中，到正在访问中，表示遇到了环
        }
        if (marked[i] == 2) {
            return false; // 表示在访问的过程中没有遇到环，这个节点访问过了
        }
        // 走到这里，是因为初始化呢，此时 marked[i] == 0
        marked[i] = 1;// 表示正在访问中
        HashSet<Integer> successorNodes = graph[i]; // 后继结点的集合

        for (Integer successor : successorNodes) {
            if (dfs(successor, graph, marked)) {
                return true;     // 层层递归返回 true ，表示图中存在环
            }
        }
        // i 的所有后继结点都访问完了，都没有存在环，则这个结点就可以被标记为已经访问结束
        marked[i] = 2;  // 状态设置为 2
        return false; // false 表示图中不存在环
    }
}
