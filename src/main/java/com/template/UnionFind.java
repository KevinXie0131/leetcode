package com.template;

public class UnionFind {

    private int[] id;
    private int count;

    public UnionFind (int N) {
        this.count = N;
        this.id = new int[N];
        for (int i = 0; i < N; i ++) {
            this.id[i] = i;
        }
    }

    public int getCount () {
        return count;
    }

    public int[] getId () {
        return id;
    }

    public boolean isConnected (int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        while (p != id[p]) {
          p = id[p];
        }
        return p;
    }

    public void union(int p, int q) {

        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;
        }

        id[pRoot] = qRoot;

        count--;
    }

}
