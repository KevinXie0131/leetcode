package com.answer.union_find;

import java.util.*;

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
     *  2. 移除石头 [2,0] ，因为它和 [0,0] 同列。
     *  3. 移除石头 [0,2] ，因为它和 [0,0] 同行。
     *  石头 [0,0] 和 [1,1] 不能移除，因为它们没有与另一块石头同行/列。
     */
    public static void main(String[] args) {
        int[][] stones = {{0,1},{1,1}};
        System.out.println(removeStones_2(stones));
    }
    /**
     * 并查集
     * 如果一块石头的 同行或者同列 上有其他石头存在，那么就可以移除这块石头。可以发现：一定可以把一个连通图里的所有顶点根据这个规则删到只剩下一个顶点。
     * 为什么这么说呢？既然这些顶点在一个连通图里，可以通过遍历的方式（深度优先遍历或者广度优先遍历）遍历到这个连通图的所有顶点。
     * 那么就可以按照遍历的方式 逆向 移除石头，最后只剩下一块石头。所以：最多可以移除的石头的个数 = 所有石头的个数 - 连通分量的个数。
     */
    static int[] parent;

    static public int removeStones(int[][] stones) {
        int n = stones.length;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        // 并查集里的元素是 描述「横坐标」和「纵坐标」的数值。因此我们需要遍历数组 stones，将每个 stone 的横坐标和纵坐标在并查集中进行合并
        for(int i = 0; i < n - 1; i++){
            for(int j = i + 1; j < n; j++){
                if(stones[i][0] == stones[j][0]){
                    join(i, j); // 「合并」的语义是：所有横坐标为 x 的石头和所有纵坐标为 y 的石头都属于同一个连通分量。
                }
            }
        }
        for(int i = 0; i < n - 1; i++){
            for(int j = i + 1; j < n; j++){
                if(stones[i][1] == stones[j][1]){
                    join(i, j); // 两个 for 循环作用是将所有石子两两合并, 如果行或者列相同，将其联通成一个子图
                }
            }
        }
        int count = 0;
        for(int i = 0; i < n; i++){
            if(parent[i] == i){
                count++;
            }
        }
        return n - count;
    }
    /**
     * 简单实现并查集
     * based on template
     */
    static public int find(int n) {
        return n == parent[n] ? n : (parent[n] = find(parent[n]));
 /*       if (parent[n] == n){ // works too
            return n;
        }
        else{
            return find(parent[n]);
        }*/
    }

    static public void join (int n, int m) {
        n = find(n);
        m = find(m);
        if (n == m) {
            return;
        }
        parent[m] = n; // 找到根节点后，x根做y根的子树，y根做x根的子树都可以
    }
    /**
     * 并查集里如何区分横纵坐标
     * 在并查集里区分「横坐标」和「纵坐标」，它们在并查集里不能相等 可以把其中一个坐标 映射 到另一个与 [0, 10000] 不重合的区间，
     * 可以的做法是把横坐标全部减去 10001 或者全部加上 10001，或者按位取反（[0, 10000] 里的 32 位整数，最高位变成 1 以后，一定不在 [0, 10000] 里）。
     *
     * 那如果以行和列作为基础，时间复杂度是可以降低的：
     *  将行和列转化到同一个维度（也就是说将行和列仅仅当作一个数字就行）
     *  当我们遍历到一个点(x, y)时，直接将x与y进行合并（说明该行和该列行的所有点都属于同一个并查集）
     *  最后用stones的大小减去并查集的个数即可
     *  但是，x和y的值可能冲突，所以这里我们将x加上10001（题目范围限定为10000）
     */
    static public int removeStones_2(int[][] stones) {
        int n = stones.length;
        parent = new int[20002];
        for (int i = 0; i < 20002; i++) {
            parent[i] = i;
        }
        // 思路：将石头行列的数值构建并查集，因此行或列需要加10001区分开
        // 合并的意思所有横坐标为 x 的石头和所有纵坐标为 y 的石头都属于同一个并查集
        for(int[] stone : stones){
           union(stone[0] + 10001, stone[1]); // 在原先基础上+10001
        }

        Set<Integer> set = new HashSet<>();
        for(int[] stone: stones){
            int x = stone[0]  + 10001;
            set.add(find1(x));
        }
        return n - set.size();
    }

    public static void union(int index1, int index2) {
        parent[find1(index2)] = find1(index1);
    }

    public static int find1(int index) {
        if (parent[index] != index) {
            parent[index] = find(parent[index]);
        }
        return parent[index];
    }
}
