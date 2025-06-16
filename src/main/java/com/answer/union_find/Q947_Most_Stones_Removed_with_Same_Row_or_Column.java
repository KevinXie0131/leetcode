package com.answer.union_find;

public class Q947_Most_Stones_Removed_with_Same_Row_or_Column {
    /**
     * On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may have at most one stone.
     * A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.
     * Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone,
     * return the largest possible number of stones that can be removed.
     * 移除最多的同行或同列石头
     * n 块石头放置在二维平面中的一些整数坐标点上。每个坐标点上最多只能有一块石头。
     * 如果一块石头的 同行或者同列 上有其他石头存在，那么就可以移除这块石头。
     * 给你一个长度为 n 的数组 stones ，其中 stones[i] = [xi, yi] 表示第 i 块石头的位置，返回 可以移除的石子 的最大数量。
     *
     * 示例 2：
     *  输入：stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
     *  输出：3
     *  解释：一种移除 3 块石头的方法如下所示：
     *  1. 移除石头 [2,2] ，因为它和 [2,0] 同行。
     *   2. 移除石头 [2,0] ，因为它和 [0,0] 同列。
     *  3. 移除石头 [0,2] ，因为它和 [0,0] 同行。
     *  石头 [0,0] 和 [1,1] 不能移除，因为它们没有与另一块石头同行/列。
     */
    public static void main(String[] args) {
        int[][] stones = {{0,0},{0,2},{1,1},{2,0},{2,2}};
        System.out.println(removeStones(stones));
    }
    /**
     *
     */
    static int[] parent;

    static public int removeStones(int[][] stones) {
        int n = stones.length;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for(int i = 0; i < n - 1; i++){
            for(int j = i + 1; j < n; j++){
                if(stones[i][0] == stones[j][0]){
                    union(i, j);
                }
            }
        }
        for(int i = 0; i < n - 1; i++){
            for(int j = i + 1; j < n; j++){
                if(stones[i][1] == stones[j][1]){
                    union(i, j);
                }
            }
        }

        int count = 0;
        for(int i = 0; i < n; i++){
            if(parent[i] == i) count++;
        }
        return n - count;
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
}
