package com.answer.topological_sort;

import java.util.*;

public class Q210_Course_Schedule_II {
    /**
     * 课程表 II
     * 总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites ，
     * 其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。
     *  例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
     * 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。
     * All the pairs [ai, bi] are distinct.
     * 示例 1：
     *   输入：numCourses = 2, prerequisites = [[1,0]]
     *   输出：[0,1]
     *   解释：总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
     * 示例 2：
     *   输入：numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
     *   输出：[0,2,1,3]
     *   解释：总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
     *   因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
     */
    public static void main(String[] args) {
        int numCourses = 4;
        int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};
        System.out.println(Arrays.toString(findOrder1(numCourses, prerequisites)));
    }
    /**
     * 算法流程：
     *  1、在开始排序前，扫描对应的存储空间（使用邻接表），将入度为 0 的结点放入队列。
     *  2、只要队列非空，就从队首取出入度为 0 的结点，将这个结点输出到结果集中，并且将这个结点的所有邻接结点（它指向的结点）的入度减 1，
     *     在减 1 以后，如果这个被减 1 的结点的入度为 0 ，就继续入队。
     *  3、当队列为空的时候，检查结果集中的顶点个数是否和课程数相等即可。
     * （思考这里为什么要使用队列？如果不用队列，还可以怎么做，会比用队列的效果差还是更好？）
     * 在代码具体实现的时候，除了保存入度为 0 的队列，我们还需要两个辅助的数据结构：
     *  1、邻接表：通过结点的索引，我们能够得到这个结点的后继结点；
     *  2、入度数组：通过结点的索引，我们能够得到指向这个结点的结点个数。
     * 这个两个数据结构在遍历题目给出的邻边以后就可以很方便地得到。
     */
    static public int[] findOrder(int numCourses, int[][] prerequisites) {
        HashSet<Integer>[] adj = new HashSet[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adj[i] = new HashSet<>();
        }
        // [1,0] 0 -> 1
        int[] inDegree = new int[numCourses];
        for (int[] p : prerequisites) {
            adj[p[1]].add(p[0]);
            inDegree[p[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int[] res = new int[numCourses];
        // 当前结果集列表里的元素个数，正好可以作为下标
        int count = 0;

        while (!queue.isEmpty()) {
            // 当前入度为 0 的结点
            Integer head = queue.poll();
            res[count] = head;
            count++;

            Set<Integer> successors = adj[head];
            for (Integer nextCourse : successors) {
                inDegree[nextCourse]--;
                // 马上检测该结点的入度是否为 0，如果为 0，马上加入队列
                if (inDegree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                }
            }
        }
        // 如果结果集中的数量不等于结点的数量，就不能完成课程任务，这一点是拓扑排序的结论
        if (count == numCourses) {
            return res;
        }
        return new int[0];
    }
    /**
     * 深度优先遍历
     */
   static public int[] findOrder1(int numCourses, int[][] prerequisites) {
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
        // 使用 Stack 或者 List 记录递归的顺序，这里使用 Stack
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < numCourses; i++) {
            if (dfs(i, graph, marked, stack)) {
                // 注意方法的语义，如果图中存在环，表示课程任务不能完成，应该返回空数组
                return new int[0];
            }
        }
        // 在遍历的过程中，一直 dfs 都没有遇到已经重复访问的结点，就表示有向图中没有环
        // 所有课程任务可以完成，应该返回 true
        // 下面这个断言一定成立，这是拓扑排序告诉我们的结论
        assert stack.size() == numCourses;
        int[] ret = new int[numCourses];
        // 想想要怎么得到结论，我们的 dfs 是一致将后继结点进行 dfs 的
        // 所以压在栈底的元素，一定是那个没有后继课程的结点
        // 那个没有前驱的课程，一定在栈顶，所以课程学习的顺序就应该是从栈顶到栈底
        // 依次出栈就好了
        for (int i = 0; i < numCourses; i++) {
            ret[i] = stack.pop();
        }
        return ret;
    }
    /**
     * 注意这个 dfs 方法的语义
     * @param i      当前访问的课程结点
     * @param graph
     * @param marked 如果 == 1 表示正在访问中，如果 == 2 表示已经访问完了
     * @return true 表示图中存在环，false 表示访问过了，不用再访问了
     */
    static private boolean dfs(int i, HashSet<Integer>[] graph, int[] marked, Stack<Integer> stack) {
        if (marked[i] == 1) { // 如果访问过了，就不用再访问了
            return true;// 从正在访问中，到正在访问中，表示遇到了环
        }
        if (marked[i] == 2) {
            return false;  // 表示在访问的过程中没有遇到环，这个节点访问过了
        }
        // 走到这里，是因为初始化呢，此时 marked[i] == 0
        marked[i] = 1;// 表示正在访问中
        // 后继结点的集合
        HashSet<Integer> successorNodes = graph[i];
        for (Integer successor : successorNodes) {
            if (dfs(successor, graph, marked, stack)) {
                return true;  // 层层递归返回 true ，表示图中存在环
            }
        }
        // i 的所有后继结点都访问完了，都没有存在环，则这个结点就可以被标记为已经访问结束
        marked[i] = 2; // 状态设置为 2
        stack.add(i);
        return false;   // false 表示图中不存在环
    }
}
