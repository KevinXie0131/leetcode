package com.answer.graph;

import java.util.*;

public class TopologicalSorting {
    /**
     * 拓扑排序是经典的图论问题
     * 拓扑排序的应用场景: 大学排课，例如 先上A课，才能上B课，上了B课才能上C课，上了A课才能上D课，等等一系列这样的依赖顺序。 问给规划出一条 完整的上课顺序。
     *
     * 概括来说，给出一个 有向图，把这个有向图转成线性的排序 就叫拓扑排序。
     * 当然拓扑排序也要检测这个有向图 是否有环，即存在循环依赖的情况，因为这种情况是不能做线性排序的。所以拓扑排序也是图论中判断有向无环图的常用方法
     *
     * 其实只要能在把 有向无环图 进行线性排序 的算法 都可以叫做 拓扑排序。
     * 实现拓扑排序的算法有两种：卡恩算法（BFS广度优先搜索）和DFS
     *
     * 节点的入度表示 有多少条边指向它，节点的出度表示有多少条边 从该节点出发。
     * 所以当我们做拓扑排序的时候，应该优先找 入度为 0 的节点，只有入度为0，它才是出发节点
     *
     * 拓扑排序的过程，其实就两步：
     *      找到入度为0 的节点，加入结果集
     *      将该节点从图中移除
     * 循环以上两步，直到 所有节点都在图中被移除了。
     * 结果集的顺序，就是我们想要的拓扑排序顺序 （结果集里顺序可能不唯一）
     */
    public static void main(String[] args) {
        int[][] input = {
                {0, 1},
                {0, 2},
                {1, 3},
                {2, 4}};
        topologicalSorting(5, 4, input );
    }
    public static void topologicalSorting(int numFile , int row,  int[][] input) {
        int n = numFile;
        int m = row;

        List<List<Integer>> umap = new ArrayList<>(); // 记录文件依赖关系
        int[] inDegree = new int[n]; // 记录每个文件的入度

        for (int i = 0; i < n; i++) {
            umap.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int s = input[i][0]; // s->t，先有s才能有t
            int t = input[i][1];
            umap.get(s).add(t); // 记录s指向哪些文件
            inDegree[t]++; // t的入度加一
        }
        // 因为每次寻找入度为0的节点，不一定只有一个节点，可能很多节点入度都为0，所以要将这些入度为0的节点放到队列里，依次去处理。
        Queue<Integer> queue = new LinkedList<>(); // 找入度为0 的节点，我们需要用一个队列放存放。
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                // 入度为0的文件，可以作为开头，先加入队列
                queue.add(i);
            }
        }
        List<Integer> result = new ArrayList<>(); // 开始从队列里遍历入度为0 的节点，将其放入结果集。
        // 拓扑排序
        while (!queue.isEmpty()) {
            int cur = queue.poll(); // 当前选中的文件
            result.add(cur);
            // 将该节点从图中移除
            // 本质是要将 该节点作为出发点所连接的节点的 入度 减一 就可以了，这样好能根据入度找下一个节点，不用真在图里把这个节点删掉
            for (int file : umap.get(cur)) { // 遍历cur指向的节点
                inDegree[file]--; // cur的指向的文件入度-1
                // 如果指向的节点减一之后，入度为0，说明是我们要选取的下一个节点，放入队列。
                if (inDegree[file] == 0) {
                    queue.add(file); // 入度为0， 这样才能作为下一轮选取的节点。
                }
            }
        }
        if (result.size() == n) {
            for (int i = 0; i < result.size(); i++) {
                System.out.print(result.get(i));
                if (i < result.size() - 1) {
                    System.out.print(" ");
                }
            }
        } else {
            System.out.println(-1);
        }
    }
}
