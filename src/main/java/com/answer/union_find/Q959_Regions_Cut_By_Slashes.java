package com.answer.union_find;

public class Q959_Regions_Cut_By_Slashes {
    /**
     * An n x n grid is composed of 1 x 1 squares where each 1 x 1 square consists of a '/', '\', or blank space ' '.
     * These characters divide the square into contiguous regions.
     * Given the grid grid represented as a string array, return the number of regions.
     * Note that backslash characters are escaped, so a '\' is represented as '\\'.
     * 由斜杠划分区域
     * 在由 1 x 1 方格组成的 n x n 网格 grid 中，每个 1 x 1 方块由 '/'、'\' 或空格构成。这些字符会将方块划分为一些共边的区域。
     * 给定网格 grid 表示为一个字符串数组，返回 区域的数量 。
     * 请注意，反斜杠字符是转义的，因此 '\' 用 '\\' 表示。
     *
     * 输入：grid = ["/\\","\\/"]
     * 输出：5
     * 解释：回想一下，因为 \ 字符是转义的，所以 "/\\" 表示 /\，而 "\\/" 表示 \/。
     */
    public static void main(String[] args) {
       String[] grid = {"/\\","\\/"};
       System.out.println(regionsBySlashes(grid));
    }
    /**
     * 并查集
     * 这是一个关于连通性的问题，让我们求解连通分量的个数
     * 可以用深度优先遍历（Depth First Search）、广度优先遍历（Breadth First Search）和并查集（Disjoint Sets），由于只要求计算结果，不要求给出具体的连通信息，可以使用并查集。
     */
    static int[] connected;

    static public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        int size = 4 * n * n; // 4 * N * N的并查集数组
        connected = new int[size];
        for(int i = 0; i < size; i++) {
            connected[i] = i;
        }

        for(int row = 0 ; row < n; row++){
            char[] ch = grid[row].toCharArray();
            // 「斜杠」、「反斜杠」把单元格拆分成的 2 个三角形的形态，在做合并的时候需要分类讨论。根据「斜杠」、「反斜杠」分割的特点，我们把一个单元格分割成逻辑上的 4 个部分。
            // 单元格内：
            //  如果是空格：合并 0、1、2、3；
            //  如果是斜杠：合并 0、3，合并 1、2；
            //  如果是反斜杠：合并 0、1，合并 2、3。
            for(int col = 0 ; col < n; col++){
                int index = 4 * (row * n + col);  // 二维网格转换为一维表格，index 表示将单元格拆分成 4 个小三角形以后，编号为 0 的小三角形的在并查集中的下标
                char c = ch[col];
                // 单元格内合并
                if(c == '/'){
                    union(index + 0, index + 3);  // 合并 0、3，合并 1、2
                    union(index + 1, index + 2);
                } else if((c == '\\')){
                    union(index + 0, index + 1); // 合并 0、1，合并 2、3
                    union(index + 2, index + 3);
                } else {
                    union(index + 0, index + 1);
                    union(index + 1, index + 2);
                    union(index + 2, index + 3);
                }
                // 单元格间合并
                // 向右：合并 1 （当前单元格）和 3（当前单元格右边 1 列的单元格），上图中红色部分；
                if(col < n - 1){
                    union(index + 1, 4 * (row * n + col + 1) + 3); // 向右合并：1（当前）、3（右一列）
                }
                // 向下：合并 2 （当前单元格）和 0（当前单元格下边 1 列的单元格），上图中蓝色部分。
                if(row < n - 1){
                    union(index + 2, 4 * ((row + 1) * n + col)); // 向下合并：2（当前）、0（下一行）
                }
            }
        }
        int count = 0;
        for(int i = 0 ; i < connected.length; i++){
            if(connected[i] == i) count++;
        }
        return count;
    }

    public static void union(int index1, int index2) {
        connected[find(index2)] = find(index1);
    }

    public static int find(int index) {
        if (connected[index] != index) {
            connected[index] = find(connected[index]);
        }
        return connected[index];
    }
}
