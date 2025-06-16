package com.answer.union_find;

import java.util.*;

public class Q1319_Number_of_Operations_to_Make_Network_Connected_1 {
    /**
     * 深度优先搜索
     * 使用深度优先搜索来得到图中的连通分量数。
     * 初始时所有节点的状态均为「待搜索」。我们每次选择一个「待搜索」的节点，从该节点开始进行深度优先搜索，并将所有搜索到的节点的状态更改为「已搜索」，这样我们就找到了一个连通分量。
     */
    List<Integer>[] edges;
    boolean[] used;

    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) { // 当计算机的数量为 n 时，我们至少需要 n−1 根线才能将它们进行连接。
            return -1;
        }

        edges = new List[n];
        for (int i = 0; i < n; ++i) {
            edges[i] = new ArrayList<Integer>();
        }
        for (int[] conn : connections) {
            edges[conn[0]].add(conn[1]);
            edges[conn[1]].add(conn[0]);
        }

        used = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; ++i) {
            if (!used[i]) {
                dfs(i);
                ++count;
            }
        }

        return count - 1;
    }

    public void dfs(int u) {
        used[u] = true;
        for (int v : edges[u]) {
            if (!used[v]) {
                dfs(v);
            }
        }
    }
}
