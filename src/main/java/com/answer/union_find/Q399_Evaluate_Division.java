package com.answer.union_find;

import java.util.*;

public class Q399_Evaluate_Division {
    /**
     * 除法求值
     * 一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式
     * Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。
     *
     * 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
     * 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。如果问题中出现了给定的已知条件中没有出现的字符串，
     * 也需要用 -1.0 替代这个答案。
     * 注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
     * 注意：未在等式列表中出现的变量是未定义的，因此无法确定它们的答案
     *
     * 示例 1：
     *  输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
     *  输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
     *  解释：
     *  条件：a / b = 2.0, b / c = 3.0
     *  问题：a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
     *  结果：[6.0, 0.5, -1.0, 1.0, -1.0 ]
     *  注意：x 是未定义的 => -1.0
     * Ai, Bi, Cj, Dj consist of lower case English letters and digits.
     */
    public static void main(String[] args) {
        List<List<String>> equations = new ArrayList<>();
        equations.add(new ArrayList<>(Arrays.asList(new String[]{"a", "b"})));
        equations.add(new ArrayList<>(Arrays.asList(new String[]{"b", "c"})));

        double[] values= {2.0, 3.0};

        List<List<String>> queries = new ArrayList<>();
        queries.add(new ArrayList<>(Arrays.asList(new String[]{"a","c"})));
        queries.add(new ArrayList<>(Arrays.asList(new String[]{"b","a"})));
        queries.add(new ArrayList<>(Arrays.asList(new String[]{"a","e"})));
        queries.add(new ArrayList<>(Arrays.asList(new String[]{"a","a"})));
        queries.add(new ArrayList<>(Arrays.asList(new String[]{"x","x"})));

        System.out.println(Arrays.toString(calcEquation(equations, values, queries)));
    }
    /**
     * Approach 2: Union-Find with Weights
     * As a reminder, the Union-Find data structure, also known as Disjoint Set, is used to track a set of elements partitioned
     * into a number of disjoint (non-overlapping) subsets. The Union-Find data structure is often applied to solve
     * the graph partition problem, where we partition a graph into a set of inter-connected subgraph.
     */
    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int equationsSize = equations.size();

        UnionFind_1 unionFind = new UnionFind_1(2 * equationsSize);
        // 第 1 步：预处理，将变量的值与 id 进行映射，使得并查集的底层使用数组实现，方便编码
        Map<String, Integer> hashMap = new HashMap<>(2 * equationsSize);
        int id = 0;
        for (int i = 0; i < equationsSize; i++) {
            List<String> equation = equations.get(i);
            String var1 = equation.get(0);
            String var2 = equation.get(1);

            if (!hashMap.containsKey(var1)) {
                hashMap.put(var1, id);
                id++;
            }
            if (!hashMap.containsKey(var2)) {
                hashMap.put(var2, id);
                id++;
            }
            unionFind.union(hashMap.get(var1), hashMap.get(var2), values[i]);
        }

        // 第 2 步：做查询
        int queriesSize = queries.size();
        double[] res = new double[queriesSize];
        for (int i = 0; i < queriesSize; i++) {
            String var1 = queries.get(i).get(0);
            String var2 = queries.get(i).get(1);

            Integer id1 = hashMap.get(var1);
            Integer id2 = hashMap.get(var2);

            if (id1 == null || id2 == null) {
                res[i] = -1.0d;
            } else {
                res[i] = unionFind.isConnected(id1, id2);
            }
        }
        return res;
    }
}

class UnionFind_1 {

    private int[] parent;
    /**
     * 指向的父结点的权值
     */
    private double[] weight;

    public UnionFind_1(int n) {
        this.parent = new int[n];
        this.weight = new double[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            weight[i] = 1.0d;
        }
    }

    public void union(int x, int y, double value) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) {
            return;
        }
        parent[rootX] = rootY;
        // 关系式的推导请见「参考代码」下方的示意图
        weight[rootX] = weight[y] * value / weight[x];
    }
    /**
     * 路径压缩
     *
     * @param x
     * @return 根结点的 id
     */
    public int find(int x) {
        if (x != parent[x]) {
            int origin = parent[x];
            parent[x] = find(parent[x]);
            weight[x] *= weight[origin];
        }
        return parent[x];
    }

    public double isConnected(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) {
            return weight[x] / weight[y];
        } else {
            return -1.0d;
        }
    }
}
