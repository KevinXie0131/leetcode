package com.template;

import java.util.Arrays;

public class Template3_UnionFind {

    public static void main(String[] args) {

        UnionFind uf = new UnionFind(10);
        uf.union(3, 5);
        uf.union(5, 8);
        uf.union(8, 0);
        System.out.println(uf.find(3));
        System.out.println(uf.isConnected(3, 5));
        System.out.println(uf.isConnected(3, 0));
        System.out.println(uf.getCount());
        System.out.println("id: " + Arrays.toString(uf.getId()));
        System.out.println("------------------");

        UnionFindEnhanced ufe = new UnionFindEnhanced(10);
        ufe.union(3, 5);
        ufe.union(5, 8);
        ufe.union(8, 0);
        System.out.println(ufe.find(3));
        System.out.println(ufe.isConnected(3, 5));
        System.out.println(ufe.isConnected(3, 0));
        System.out.println(ufe.getCount());
        System.out.println("id: " + Arrays.toString(ufe.getId()));

        UnionFindEnhanced2 ufe2 = new UnionFindEnhanced2(10);
        ufe2.union(3, 5);
        ufe2.union(5, 8);
        ufe2.union(9, 0);
        System.out.println(ufe2.find(3));
        System.out.println(ufe2.isConnected(3, 5));
        System.out.println(ufe2.isConnected(3, 0));
        System.out.println(ufe2.getCount());
        System.out.println("id: " + Arrays.toString(ufe2.getId()));
        System.out.println("lv: " + Arrays.toString(ufe2.getLevel()));
        ufe2.union(0, 5);
        System.out.println("id: " + Arrays.toString(ufe2.getId()));
        System.out.println("lv: " + Arrays.toString(ufe2.getLevel()));
    }

}
