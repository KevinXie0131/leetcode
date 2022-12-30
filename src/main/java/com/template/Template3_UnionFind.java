package com.template;

public class Template3_UnionFind {

    public static void main(String[] args) {

        UnionFind uf = new UnionFind(10);

        uf.union(3, 5);
        uf.union(5, 8);
        uf.union(8, 0);

        System.out.println(uf.find(3));

        System.out.println(uf.isConnected(3, 5));
        System.out.println(uf.isConnected(3, 0));
    }

}
