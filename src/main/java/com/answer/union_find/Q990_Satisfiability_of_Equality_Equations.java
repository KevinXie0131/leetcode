package com.answer.union_find;

import java.util.*;

public class Q990_Satisfiability_of_Equality_Equations {
    /**
     * You are given an array of strings equations that represent relationships between variables where each string equations[i]
     * is of length 4 and takes one of two different forms: "xi==yi" or "xi!=yi".Here, xi and yi are lowercase letters
     * (not necessarily different) that represent one-letter variable names.
     * Return true if it is possible to assign integers to variable names so as to satisfy all the given equations, or false otherwise.
     * 等式方程的可满足性
     * 给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，并采用两种不同的形式之一："a==b" 或 "a!=b"。
     * 在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。
     * 只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回 true，否则返回 false。
     * equations[i].length == 4
     * equations[i][0] is a lowercase letter.
     * equations[i][1] is either '=' or '!'.
     * equations[i][2] is '='.
     * equations[i][3] is a lowercase letter.
     *
     * Example 1:
     *   Input: equations = ["a==b","b!=a"]
     *   Output: false
     * Example 2:
     *   Input: equations = ["b==a","a==b"]
     *   Output: true
     */
    public static void main(String[] args) {
     //   String[] equations = {"a==b","b!=a"};
    //    System.out.println(equationsPossible(equations));
        // 输出：false
        //解释：如果我们指定，a = 1 且 b = 1，那么可以满足第一个方程，但无法满足第二个方程。没有办法分配变量同时满足这两个方程。
     //   String[] equations1 = {"b==a", "a==b", "c==a"};
    //    System.out.println(equationsPossible(equations1)); // 输出：true
        String[] equations2 = {"a==b","b!=c","c==a", "b==d"};
        System.out.println(equationsPossible_1(equations2));
    }
    /**
     * Approach 2: Union-find 并查集
     * 题目的本质：判断多个集合之间是否有交叉矛盾。（注：重点在集合上。通过传递关系，我们可以划分出集合）
     *
     * 可以将每一个变量看作图中的一个节点，把相等的关系 == 看作是连接两个节点的边，那么由于表示相等关系的等式方程具有传递性，
     * 即如果 a==b 和 b==c 成立，则 a==c 也成立。也就是说，所有相等的变量属于同一个连通分量。因此，我们可以使用并查集来维护这种连通分量的关系。
     */
    static int[] parent;

    public static boolean equationsPossible(String[] equations) {
        int n = equations.length;
        parent = new int[26]; // 因为只有 26 个小写字母，所以只定义简单的并查集就行
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }
        // 首先遍历所有的等式，构造并查集。同一个等式中的两个变量属于同一个连通分量，因此将两个变量进行合并。
        for(String equation : equations) { // 先处理“相等”（合并）
            if (equation.charAt(1) == '=') {
                int index1 = equation.charAt(0) - 'a';
                int index2 = equation.charAt(3) - 'a';
                union(index1, index2);  // 并查集的合并 unite
            }
        }
        for(String equation : equations) { // 再处理“不等”
            if(equation.charAt(1) == '!'){
                int index1 = equation.charAt(0) - 'a';
                int index2 = equation.charAt(3) - 'a';
                if(find(index1) == find(index2)){ // 如果两个变量在同一个连通分量中，则产生矛盾，返回 false
                    return false;
                }
            }
        }
        return true; // 如果检查了所有不等式，都没有发现矛盾，返回 true
    }

    public static void union(int index1, int index2) {
        parent[find(index2)] = find(index1);
    }

    public static int find(int index) {
        if (parent[index] != index) {
            parent[index] = find(parent[index]);
        }
        return parent[index];
    }

/*    public void union(int n, int m) { // works too
        n = find(n);
        m = find(m);
        if (n == m) {
            return;
        }
        parent[m] = n;
    }

    public int find(int n) {
        return n == parent[n] ? n : (parent[n] = find(parent[n]));
    }*/
    /**
     * Approach 1: Depth-first Search (DFS)
     */
    public static boolean equationsPossible_1(String[] equations) {
        List<Integer>[] graph = new ArrayList[26];
        for (int i = 0; i < 26; ++i)
            graph[i] = new ArrayList();

        for (String eqn : equations) {
            if (eqn.charAt(1) == '=') {
                int x = eqn.charAt(0) - 'a';
                int y = eqn.charAt(3) - 'a';
                graph[x].add(y);
                graph[y].add(x);
            }
        }

        int[] unioned = new int[26];
        Arrays.fill(unioned, -1);

        for (int i = 0; i < 26; i++) {
            if (unioned[i] == -1) {
                dfs(i, i, unioned, graph);
            }
        }

        for (String eqn : equations) {
            if (eqn.charAt(1) == '!') {
                int x = eqn.charAt(0) - 'a';
                int y = eqn.charAt(3) - 'a';
                if (unioned[x] == unioned[y]) {
                    return false;
                }
            }
        }
        return true;
    }
    // mark the color of `node` as `c`
    private static void dfs(int node, int value, int[] unioned, List<Integer>[] graph) {
        unioned[node] = value;

        List<Integer> list = graph[node];
        for (int child : list) {
            if (unioned[child] == -1) {
                dfs(child, value, unioned, graph);
            }
        }
    }
}
