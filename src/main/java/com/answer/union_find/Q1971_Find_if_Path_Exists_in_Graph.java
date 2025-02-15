package com.answer.union_find;

public class Q1971_Find_if_Path_Exists_in_Graph {
    /**
     * 这道题目是并查集基础题目，题目中各个点是双向图链接，那么判断 一个顶点到另一个顶点有没有有效路径其实就是看这两个顶点是否在同一个集合里
     * 主要就是集合问题，两个节点在不在一个集合，也可以将两个节点添加到一个集合中。如何算是同一个集合呢，有边连在一起，就算是一个集合。
     */
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        DisJoint disJoint = new DisJoint(n + 1);
        for (int i = 0; i < edges.length; ++i) {
            disJoint.join(edges[i][0], edges[i][1]);
        }
        return disJoint.isSame(source, destination);
    }

    //并查集模板
    class DisJoint{
        private int[] father;

        public DisJoint(int N) {
            father = new int[N];
            for (int i = 0; i < N; ++i){
                father[i] = i;
            }
        }

        public int find(int n) {
            return n == father[n] ? n : (father[n] = find(father[n]));
        }

        public void join (int n, int m) {
            n = find(n);
            m = find(m);
            if (n == m) return;
            father[m] = n;
        }

        public boolean isSame(int n, int m){
            n = find(n);
            m = find(m);
            return n == m;
        }

    }
 }