package com.answer.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Q733_Flood_Fill {
    /**
     * 图像渲染
     * 有一幅以 m x n 的二维整数数组表示的图画 image ，其中 image[i][j] 表示该图画的像素值大小。你也被给予三个整数 sr ,  sc 和 color 。你应该从像素 image[sr][sc] 开始对图像进行上色 填充 。
     *
     * 为了完成 上色工作：
     *  从初始像素开始，将其颜色改为 color。
     *  对初始坐标的 上下左右四个方向上 相邻且与初始像素的原始颜色同色的像素点执行相同操作。
     *  通过检查与初始像素的原始颜色相同的相邻像素并修改其颜色来继续 重复 此过程。
     *  当 没有 其它原始颜色的相邻像素时 停止 操作。
     *  最后返回经过上色渲染 修改 后的图像 。
     *
     * You are given an image represented by an m x n grid of integers image, where image[i][j] represents the pixel value of the image. You are also given three integers sr, sc, and color. Your task is to perform a flood fill on the image starting from the pixel image[sr][sc].
     * To perform a flood fill:
     *  Begin with the starting pixel and change its color to color.
     *  Perform the same process for each pixel that is directly adjacent (pixels that share a side with the original pixel, either horizontally or vertically) and shares the same color as the starting pixel.
     *  Keep repeating this process by checking neighboring pixels of the updated pixels and modifying their color if it matches the original color of the starting pixel.
     *  The process stops when there are no more adjacent pixels of the original color to update.
     *  Return the modified image after performing the flood fill.
     *
     * 示例 1:
     *  输入：image = [[1,1,1],[1,1,0],[1,0,1]]，sr = 1, sc = 1, color = 2
     *  输出：[[2,2,2],[2,2,0],[2,0,1]]
     *  解释：在图像的正中间，坐标 (sr,sc)=(1,1) （即红色像素）,在路径上所有符合条件的像素点的颜色都被更改成相同的新颜色（即蓝色像素）。
     *       注意，右下角的像素 没有 更改为2，因为它不是在上下左右四个方向上与初始点相连的像素点。
     *       From the center of the image with position (sr, sc) = (1, 1) (i.e., the red pixel), all pixels connected by a path of the same color as the starting pixel (i.e., the blue pixels) are colored with the new color.
     *       Note the bottom corner is not colored 2, because it is not horizontally or vertically connected to the starting pixel.
     */
    public static void main(String[] args) {
       int[][] image = {{1,1,1},{1,1,0},{1,0,1}};
       int sr = 1, sc = 1, color = 2;
       System.out.println(Arrays.deepToString(floodFill(image, sr, sc, color)));
    }
    /**
     * 广度优先搜索
     */
    static public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int m = image.length;
        int n = image[0].length;
        boolean[][] visited = new boolean[m][n];

        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{sr, sc});
        int oldColor = image[sr][sc];
        image[sr][sc] = color;
        visited[sr][sc] = true;
/*        if (oldColor == color) { // visited can be ignored
            return image;
        }*/
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            for(int k = 0; k < 4; k++){
                int x = cur[0] + dirs[k][0];
                int y = cur[1] + dirs[k][1];
                if(x >= 0 && x <= m - 1 && y >= 0 && y <= n - 1 && image[x][y] == oldColor && !visited[x][y]) {
                    image[x][y] = color;
                    visited[x][y] = true;
                    queue.offer(new int[]{x, y});
                }
            }
        }
        return image;
    }
    /**
     * 深度优先搜索
     */
    public int[][] floodFill_1(int[][] image, int sr, int sc, int color) {
        int m = image.length;
        int n = image[0].length;

        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[]{sr, sc});
        int oldColor = image[sr][sc];
        image[sr][sc] = color;

        if (oldColor == color) { // visited can be ignored
            return image; // 如果颜色相同则不处理
        }

        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while(!stack.isEmpty()){
            int[] cur = stack.pop();
            for(int k = 0; k < 4; k++){
                int x = cur[0] + dirs[k][0];
                int y = cur[1] + dirs[k][1];
                if(x >= 0 && x <= m - 1 && y >= 0 && y <= n - 1 && image[x][y] == oldColor) {
                    image[x][y] = color;
                    stack.push(new int[]{x, y});
                }
            }
        }
        return image;
    }
    /**
     * DFS
     */
    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int[][] floodFill_2(int[][] image, int sr, int sc, int color) {
        int currColor = image[sr][sc];
        if (currColor != color) {
            dfs(image, sr, sc, currColor, color);
        }
        return image;
    }

    public void dfs(int[][] image, int sr, int sc, int currColor, int color) {
        if(image[sr][sc] == currColor){
            image[sr][sc] = color;

            for(int[] dir : dirs){
                int x = sr + dir[0];
                int y = sc + dir[1];
                if(x >= 0 && x <= image.length - 1 && y >= 0 && y <= image[0].length - 1 && image[x][y] == currColor) {
                    dfs(image, x, y, currColor, color);
                }
            }
        }
    }
    /**
     * another form
     */
    public int[][] floodFill_4(int[][] image, int sr, int sc, int newColor) {
        helper(image, sr, sc, newColor, image[sr][sc]);
        return image;

    }

    void helper(int[][] image, int sr, int sc, int newColor, int oldColor) {
        if (sr < 0 || sc < 0 || sr >= image.length || sc >= image[0].length
                || image[sr][sc] != oldColor || newColor == oldColor){
            return;
        }

        image[sr][sc] = newColor;

        helper(image, sr - 1, sc, newColor, oldColor);
        helper(image, sr + 1, sc, newColor, oldColor);
        helper(image, sr, sc - 1, newColor, oldColor);
        helper(image, sr, sc + 1, newColor, oldColor);

    }
}
