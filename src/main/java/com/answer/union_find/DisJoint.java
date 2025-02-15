package com.answer.union_find;

//并查集模板
public class DisJoint{
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