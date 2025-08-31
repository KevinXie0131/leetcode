package com.answer.graph;

import java.util.*;

public class Q1136_Parallel_Courses {
    /**
     * There are N courses, labelled from 1 to N.
     * We are given relations[i] = [X, Y], representing a prerequisite relationship between course X and course Y: course X has to be studied before course Y.
     * In one semester you can study any number of courses as long as you have studied all the prerequisites for the course you are studying.
     * Return the minimum number of semesters needed to study all courses.  If there is no way to study all the courses, return -1.
     * 平行课程
     * 已知有 N 门课程，它们以 1 到 N 进行编号。
     * 给你一份课程关系表 relations[i] = [X, Y]，用以表示课程 X 和课程 Y 之间的先修关系：课程 X 必须在课程 Y 之前修完。
     * 假设在一个学期里，你可以学习任何数量的课程，但前提是你已经学习了将要学习的这些课程的所有先修课程。
     * 请你返回学完全部课程所需的最少学期数。如果没有办法做到学完全部这些课程的话，就返回 -1。
     * Example 1
     *  Input: N = 3, relations = [[1,3],[2,3]]
     *  Output: 2
     *  Explanation: In the first semester, courses 1 and 2 are studied. In the second semester, course 3 is studied.
     *               在第一个学期学习课程 1 和 2，在第二个学期学习课程 3。
     * Example 2
     * Input: N = 3, relations = [[1,2],[2,3],[3,1]]
     * Output: -1
     * Explanation: No course can be studied because they depend on each other. 没有课程可以学习，因为它们相互依赖。
     */
    public static void main(String[] args) {
        int n = 3;
        int[][] relations = {{1,3},{2,3}};
        System.out.println(minimumSemesters(n, relations));
        int n1 = 3;
        int[][] relations1 = {{1,2},{2,3},{3,1}};
        System.out.println(minimumSemesters(n1, relations1));
    }
    /**
     * 拓扑排序
     * 这是典型的“有向无环图”拓扑排序问题。我们要求的是最长路径（因为只有没有先修课的可以并行学，最长依赖链决定最少学期数）。
     * 如果有环则返回 -1。
     */
    static public int minimumSemesters(int n, int[][] relations) {
        List<List<Integer>> graph = new ArrayList<>();// 建立有向图和入度数组
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>()); // 将课程之间的先修关系建立图
        }
        int[] indegree = new int[n + 1]; // 每个课程的入度
        // 构建图和计算入度
        for (int[] rel : relations) {
            graph.get(rel[0]).add(rel[1]);
            indegree[rel[1]]++;
        }
        // 将所有入度为0的课程加入队列
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        int semester = 0;
        int studied = 0;
        // 拓扑排序，每学期学所有可以学的课
        while (!queue.isEmpty()) {
            int size = queue.size();
            semester++;
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                studied++;
                for (int next : graph.get(curr)) {
                    indegree[next]--; // 将其出度的课程的入度减 1
                    if (indegree[next] == 0) { // 如果减1 后入度为 0，则将该课程入队
                        queue.offer(next);
                    }
                }
            }
        }
        // 当队列为空时，如果还有课程没有修完，则说明无法修完所有课程，返回−1。否则返回修完所有课程所需的学期数。
        return studied == n ? semester : -1;
    }
}
