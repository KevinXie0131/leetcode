package com.template;

public class UnionFindEnhanced2 {

    private int[] id;
    private int[] level;
    private int count;

    public UnionFindEnhanced2(int N) {
        this.count = N;
        this.id = new int[N];
        this.level = new int[N];
        for (int i = 0; i < N; i ++) {
            this.id[i] = i;
            this.level[i] = 1;
        }
    }

    public int getCount () {
        return count;
    }

    public int[] getId () {
        return id;
    }

    public int[] getLevel () {
        return level;
    }

    public boolean isConnected (int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        while (p != id[p]) {
          id[p] = id[id[p]]; // Improve performance
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

        if (level[pRoot] > level[qRoot]) {
            level[pRoot] += level[qRoot];
            id[qRoot] = id[pRoot];
        } else {
            level[qRoot] += level[pRoot];
            id[pRoot] = id[qRoot];
        }

        count--;
    }

}
