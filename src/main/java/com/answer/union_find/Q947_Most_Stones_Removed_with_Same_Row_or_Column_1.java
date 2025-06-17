package com.answer.union_find;

import java.util.*;

public class Q947_Most_Stones_Removed_with_Same_Row_or_Column_1 {
    /**
     * 深度优先搜索
     * 将这个二维平面抽象成图，把石子看作「点」，石子间的同行或同列关系看作「边」。如果两个石子同属某一行或某一列，我们就认为这两个石子之间有一条边。
     * 由题意可知，对于任意一个点，只要有点和它相连，我们就可以将其删除。
     *
     * 这样我们只需要统计整张图中有多少个极大连通子图（也叫做连通块或连通分量）即可。最终能够留下来的点的数量，即为连通块的数量。
     * 我们用总点数减去连通块的数量，即可知道我们可以删去的点的最大数量
     */
    public int removeStones(int[][] stones) {
        int n = stones.length;
        List<List<Integer>> edge = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; i++) {
            edge.add(new ArrayList<Integer>());
            for (int j = 1; j < n; j++) {
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    edge.get(i).add(j);
                }
            }
        }
        boolean[] visited = new boolean[n];
        int num = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                num++;
                dfs(i, edge, visited);
            }
        }
        return n - num;
    }

    public void dfs(int x, List<List<Integer>> edge, boolean[] visited) {
        visited[x] = true;
        for (int y : edge.get(x)) {
            if (!visited[y]) {
                dfs(y, edge, visited);
            }
        }
    }

}
