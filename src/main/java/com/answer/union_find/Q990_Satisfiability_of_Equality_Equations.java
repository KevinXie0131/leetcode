package com.answer.union_find;

import java.util.*;

public class Q990_Satisfiability_of_Equality_Equations {
    public static void main(String[] args) {
     //   String[] equations = {"a==b","b!=a"};
    //    System.out.println(equationsPossible(equations));
     //   String[] equations1 = {"b==a", "a==b", "c==a"};
    //    System.out.println(equationsPossible(equations1));
        String[] equations2 = {"a==b","b!=c","c==a", "b==d"};
        System.out.println(equationsPossible_1(equations2));
    }
    /**
     * Approach 2: Union-find
     */
    static int[] parent;

    public static boolean equationsPossible(String[] equations) {
        int n = equations.length;
        parent = new int[26];
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }

        for(String equation : equations) {
            if (equation.charAt(1) == '=') {
                int index1 = equation.charAt(0) - 'a';
                int index2 = equation.charAt(3) - 'a';
                union(index1, index2);
            }
        }
        for(String equation : equations) {
            if(equation.charAt(1) == '!'){
                int index1 = equation.charAt(0) - 'a';
                int index2 = equation.charAt(3) - 'a';
                if(find(index1) == find(index2)){
                    return false;
                }
            }
        }
        return true;
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
                if (unioned[x] == unioned[y])
                    return false;
            }
        }
        return true;
    }

    // mark the color of `node` as `c`
    private static void dfs(int node, int value, int[] color, List<Integer>[] graph) {
        if (color[node] == -1) {
            color[node] = value;

            List<Integer> list = graph[node];
            for (int child : list)
                dfs(child, value, color, graph);
        }
    }

}
