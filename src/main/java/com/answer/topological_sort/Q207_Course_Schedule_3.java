package com.answer.topological_sort;

import java.util.*;

public class Q207_Course_Schedule_3 {
    /**
     * 本题可约化为： 课程安排图是否是 有向无环图(DAG)。即课程间规定了前置条件，但不能构成任何环路，否则课程前置条件将不成立。
     * 思路是通过 拓扑排序 判断此课程安排图是否是 有向无环图(DAG) 。
     * 通过课程前置条件列表 prerequisites 可以得到课程安排图的 邻接表 adjacency，以降低算法时间复杂度
     */
    /**
     * 入度表（广度优先遍历）
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegrees = new int[numCourses];
        List<List<Integer>> adjacency = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }
        // Get the indegree and adjacency of every course.
        for(int[] cp : prerequisites) {
            indegrees[cp[0]]++;
            adjacency.get(cp[1]).add(cp[0]);
        }
        // Get all the courses with the indegree of 0.
        for(int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) queue.add(i);
        }
        // BFS TopSort.
        while(!queue.isEmpty()) {
            int pre = queue.poll();
            numCourses--;
            for(int cur : adjacency.get(pre)) {
                if (--indegrees[cur] == 0) queue.add(cur);
            }
        }
        return numCourses == 0;
    }
    /**
     * 深度优先遍历
     * 原理是通过 DFS 判断图中是否有环。
     * i == 0 ： 干净的，未被 DFS 访问
     * i == -1：其他节点启动的 DFS 访问过了，路径没问题，不需要再访问了
     * i == 1  ：本节点启动的 DFS 访问过了，一旦遇到了也说明有环了
     *
     * 然后就是遍历+标记判断有无环：
     * 1、从课程0启动DFS，先判断下，哦，节点0还没被访问，将flag[0]置1，表明被当前节点启动的dfs访问过了，在访问0时
     * 就通过以下代码一连串把3、5都访问了,返回true之前标志位置-1，这样从其他节点进来看到标志位-1时就无需再访问了，
     * 直接返回true.
     */
    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjacency = new ArrayList<>();
        for(int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }
        int[] flags = new int[numCourses];
        for(int[] cp : prerequisites) {
            adjacency.get(cp[1]).add(cp[0]);
        }
        for(int i = 0; i < numCourses; i++) {
            if (!dfs(adjacency, flags, i)) {
                return false;
            }
        }
        return true;
    }
    private boolean dfs(List<List<Integer>> adjacency, int[] flags, int i) {
        if(flags[i] == 1) return false;  //先判断再修改标志位
        if(flags[i] == -1) return true;  //别的dfs路径访问过了，我不需要访问了
        flags[i] = 1;   //只有这个标志位是干净的，别人还没有动过，我才能标记为1，说明本次dfs我遍历过它
        for(Integer j : adjacency.get(i))
            if(!dfs(adjacency, flags, j)) {
                return false;
            }
        flags[i] = -1;    //只有一次DFS完整结束了，才能执行到这一步，标记为-1，说明这条路没问题，再遇到不需要遍历了
        return true;
    }

}
