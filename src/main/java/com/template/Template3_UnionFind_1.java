package com.template;

import java.util.Arrays;

public class Template3_UnionFind_1 {

    public static void main(String[] args) {
        Template3_UnionFind_1 unionFind = new Template3_UnionFind_1(6);

        union(0,1);
        union(0,2);
        union(3,4);
        union(3,5);

        System.out.println(isConnected(1, 2));
        System.out.println(isConnected(1, 5));
    }

    static int[] parents;

    public Template3_UnionFind_1(int totalNodes) {
        parents = new int[totalNodes];
        for (int i = 0; i < totalNodes; i++) {
            parents[i] = i;
        }
    }
    // 合并连通区域是通过find来操作的, 即看这两个节点是不是在一个连通区域内.
    static void  union(int node1, int node2) {
        int root1 = find(node1);
        int root2 = find(node2);
        if (root1 != root2) {
            parents[root2] = root1;
        }
    }

    static int find(int node) {
        while (parents[node] != node) {
            // 当前节点的父节点 指向父节点的父节点.
            // 保证一个连通区域最终的parents只有一个.
            parents[node] = parents[parents[node]];
            node = parents[node];
        }
        return node;
    }

    static boolean isConnected(int node1, int node2) {
        return find(node1) == find(node2);
    }

}
