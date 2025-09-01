package com.answer.topological_sort;

import java.util.*;

public class Q207_Course_Schedule {
    /**
     * 课程表
     * 这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
     * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，
     * 表示如果要学习课程 ai 则 必须 先学习课程  bi 。
     *  例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
     * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
     * All the pairs prerequisites[i] are unique.
     * 示例 1：
     *   输入：numCourses = 2, prerequisites = [[1,0]]
     *   输出：true
     *   解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
     * 示例 2：
     *   输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
     *   输出：false
     *   解释：总共有 2 门课程。学习课程 1 之前，你需要先完成课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
     */
    public static void main(String[] args) {
        int numCourses = 4;
        int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};
        canFinish1(numCourses, prerequisites);
    }
    /**
     * 经典的「拓扑排序」问题
     * 深度优先搜索
     */
    List<List<Integer>> edges; // 存储有向图
    int[] visited;   // 标记每个节点的状态：0=未搜索，1=搜索中，2=已完成
    boolean valid = true;   // 判断有向图中是否有环

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<Integer>());
        }
        visited = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
        }
        for (int i = 0; i < numCourses && valid; ++i) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        return valid;
    }

    public void dfs(int u) {
        visited[u] = 1;  // x 正在访问中   // 将节点标记为「搜索中」
        // 搜索其相邻节点 只要发现有环，立刻停止搜索
        for (int v: edges.get(u)) {
            if (visited[v] == 0) {  // 如果「未搜索」那么搜索相邻节点
                dfs(v);
                if (!valid) {
                    return;
                }
            } else if (visited[v] == 1) {  // 如果「搜索中」说明找到了环
                valid = false;  // 找到了环
                return;
            }
        }
        visited[u] = 2;  // x 完全访问完毕
    }
    /**
     * another form
     * 一个有向图，判断图中是否有环。
     * 节点 x「正在访问中」，是说我们正在递归处理节点 x 以及它的后续节点，dfs(x) 尚未结束。
     * 对于每个节点 x，都定义三种颜色值（状态值）：
     *  0：节点 x 尚未被访问到。
     *  1：节点 x 正在访问中，dfs(x) 尚未结束。
     *  2：节点 x 已经完全访问完毕，dfs(x) 已返回。
     *
     * 误区：不能只用两种状态表示节点「没有访问过」和「访问过」。例如上图，我们先 dfs(0)，再 dfs(1)，
     * 此时 1 的邻居 0 已经访问过，但这并不能表示此时就找到了环。
     */
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        List<Integer>[] g = new ArrayList[numCourses];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] p : prerequisites) {
            g[p[1]].add(p[0]);
        }

        int[] colors = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (colors[i] == 0 && dfs1(i, g, colors)) {
                return false; // 有环
            }
        }
        return true; // 没有环
    }

    // 返回 true 表示找到了环
    private boolean dfs1(int x, List<Integer>[] g, int[] colors) {
        colors[x] = 1; // x 正在访问中
        for (int y : g[x]) {
            if (colors[y] == 1 || (colors[y] == 0 && dfs1(y, g, colors))) {
                return true; // 找到了环
            }
        }
        colors[x] = 2; // x 完全访问完毕
        return false; // 没有找到环
    }
    /**
     * 广度优先搜索
     */
    static List<List<Integer>> edges1;   // 存储有向图
    static int[] indeg1; // 存储每个节点的入度

    static public boolean canFinish1(int numCourses, int[][] prerequisites) {
        edges1 = new ArrayList<List<Integer>>(); //邻接表：通过结点的索引，我们能够得到这个结点的后继结点；
        for (int i = 0; i < numCourses; ++i) {
            edges1.add(new ArrayList<Integer>());
        }
        indeg1 = new int[numCourses]; //入度数组：通过结点的索引，我们能够得到指向这个结点的结点个数。
        for (int[] info : prerequisites) {
            edges1.get(info[1]).add(info[0]);
            ++indeg1[info[0]];
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; ++i) {  // 将所有入度为 0 的节点放入队列中
            if (indeg1[i] == 0) {
                queue.offer(i);
            }
        }

        int visited = 0;
        while (!queue.isEmpty()) {
            ++visited;
            int u = queue.poll(); // 从队首取出一个节点
            for (int v: edges1.get(u)) {
                --indeg1[v];
                if (indeg1[v] == 0) {    // 如果相邻节点 v 的入度为 0，就可以选 v 对应的课程了
                    queue.offer(v);
                }
            }
        }
        return visited == numCourses;
    }
}
