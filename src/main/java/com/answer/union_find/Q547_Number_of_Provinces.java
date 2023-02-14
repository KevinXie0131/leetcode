package com.answer.union_find;

public class Q547_Number_of_Provinces {
    public static void main(String[] args) {
        int[][] isConnected = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        System.out.println(findCircleNum(isConnected));
    }
    /**
     * Wrong answer
     */
    static int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static int findCircleNum(int[][] isConnected) {
        int m = isConnected.length;
        int n = isConnected[0].length;
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    recrusion(isConnected, i, j, count);
                    count++;
                }
            }
        }
        return count;
    }
    public static void recrusion(int[][] isConnected, int i, int j, int count) {
        if (i < 0 || i > isConnected.length - 1 || j < 0 || j > isConnected[0].length - 1) {
            return;
        }
        if (isConnected[i][j] == 1) {
            isConnected[i][j] = 2;
            for (int[] d : dir) {
                recrusion(isConnected, i + d[0], j + d[1], count);
            }
        }
    }
    /**
     * Approach #1 Using Depth First Search
     */

    /**
     * Approach #2 Using Breadth First Search
     */

    /**
     * Approach #3 Using Union-Find Method
     */
}
