package com.answer.union_find;

public class UnionFindTemplateRank {
    /**
     * 另一种方法：按秩（rank）合并
     *            rank表示树的高度，即树中结点层次的最大值。
     * 所以在 join函数中如何合并两棵树呢？
     * 一定是 rank 小的树合入 到 rank大 的树，这样可以保证最后合成的树rank 最小，降低在树上查询的路径长度。
     *
     * 其实我们在优化并查集查询效率的时候，只用路径压缩的思路就够了，不仅代码实现精简，而且效率足够高。
     * 按秩合并的思路并没有将树形结构尽可能的扁平化，所以在整理效率上是没有路径压缩高的。
     */
    int size = 10;
    int[] father = new int[size];
    int[] rank = new int[size];
    int n = father.length;
    // 并查集初始化
    void init() {
        for (int i = 0; i < n; ++i) {
            father[i] = i;
            rank[i] = 1;  // 初始每棵树的高度都为1
        }
    }
    // 将v->u 这条边加入并查集
    void join(int u, int v) {
        u = find(u); // 寻找u的根
        v = find(v); // 寻找v的根
        if (rank[u] <= rank[v]) {
            father[u] = v; // rank小的树合入到rank大的树
        } else {
            father[v] = u;
        }
        if (rank[u] == rank[v] && u != v) {
            rank[v]++; // 如果两棵树高度相同，则v的高度+1，因为上面 if (rank[u] <= rank[v]) father[u] = v; 注意是 <=
        }
    }
    // 并查集里寻根的过程
    int find(int u) {
        if (u == father[u]){
            return u;
        } else {
            // 没有做路径压缩的，因为一旦做路径压缩，rank记录的高度就不准了，根据rank来判断如何合并就没有意义。
            // 也可以在 路径压缩的时候，再去实时修生rank的数值，但这样在代码实现上麻烦了不少，关键是收益很小
            return find(father[u]); // 注意这里不做路径压缩
        }
    }
    // 判断 u 和 v是否找到同一个根
    boolean isSame(int u, int v) {
        u = find(u);
        v = find(v);
        return u == v;
    }

}
