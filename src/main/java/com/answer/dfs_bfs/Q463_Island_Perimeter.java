package com.answer.dfs_bfs;

public class Q463_Island_Perimeter {
    /**
     * 解法一: 岛屿问题最容易让人想到BFS或者DFS，但是这道题还真的没有必要，别把简单问题搞复杂了
     * 遍历每一个空格，遇到岛屿，计算其上下左右的情况，遇到水域或者出界的情况，就可以计算边了。
     */
    public int islandPerimeter_3(int[][] grid) {
        int[] dirX = {-1, 1, 0, 0}; // 上下左右 4 个方向
        int[] dirY = {0, 0, -1, 1};
        int sum = 0; // 岛屿周长

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    for(int k =0 ;k < 4; k++){  // 上下左右四个方向
                        int x = i + dirX[k];  // 计算周边坐标x,y
                        int y = j + dirY[k];
                        // 当前位置是陆地，并且从当前位置4个方向扩展的'新位置'是'水域'或'新位置'越界，则会为周长贡献一条边
                        if(x < 0 || x >= grid.length || y < 0 || y >=grid[0].length || grid[x][y] == 0){ // x, y 在边界上或者是水域
                            sum++;
                            continue;
                        }
                    }
                }
            }
        }
        return sum;
    }
    /**
     * 解法二: 计算出总的岛屿数量，因为有一对相邻两个陆地，边的总数就减2，那么在计算出相邻岛屿的数量就可以了。
     *        result = 岛屿数量 * 4 - cover * 2;
     */
    public int islandPerimeter_4(int[][] grid) {
        // 计算岛屿的周长
        // 方法二 : 遇到相邻的陆地总周长就-2
        int sum = 0;// 陆地数量
        int connect = 0;// 相邻陆地数量

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    sum++;
                    // 统计上面和左边的相邻陆地
                    if(i >= 1 && grid[i-1][j] == 1){ // 统计上边相邻陆地
                        connect++;
                    }
                    if(j >= 1 && grid[i][j-1] == 1){ // 统计左边相邻陆地
                        connect++;
                    }
                    // 为什么没统计下边和右边？ 因为避免重复计算
                }
            }
        }
        return sum * 4 - connect * 2;
    }
    /**
     * 延伸 - 傳統DFS解法(使用visited數組)（遇到邊界 或是 海水 就edge ++）
     */
    int result = 0;
    boolean visited[][];
    int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int islandPerimeter_5(int[][] grid) {
        int row= grid.length;
        int col = grid[0].length;
        visited = new boolean[row][col];

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(visited[i][j] ==false && grid[i][j] == 1){
                    result += dfs1(grid,  i,  j);
                }
            }
        }
        return result;
    }
    public int dfs1(int[][] grid, int x, int y){
        //如果遇到 邊界（x < 0 || y < 0 || x >= grid.length || y >= grid[0].length）或是 遇到海水(grid[x][y] == 0)就return 1（edge + 1）
        if(x < 0 || x >= grid.length || y < 0 || y >=grid[0].length || grid[x][y] == 0){
            return 1;
        }
        if(visited[x][y] == true){   //如果該地已經拜訪過，就return 0 避免重複計算
            return 0;
        }
        int temp = 0;
        visited[x][y] = true;
        for(int k =0 ;k < 4; k++){
            temp += dfs1(grid,  x + dir[k][0],  y + dir[k][1]);  //用temp 把edge存起來
        }
        return temp;
    }
    /**
     * DFS
     */
    public int islandPerimeter(int[][] grid) {
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    return dfs(grid, i, j);
                }
            }
        }

        return 0;
    }
    int dfs(int[][] grid, int r, int c) {
        if(!inArea(grid, r, c)){
            return 1;
        }
        if(grid[r][c] == 0){
            return 1;
        }
        if(grid[r][c] == 2){
            return 0;
        }
        grid[r][c] =2;
        return dfs(grid, r-1, c) + dfs(grid, r+1, c)
                + dfs(grid, r, c-1) + dfs(grid, r, c+1);

    }
    boolean inArea(int[][] grid, int r, int c) {
        return 0 <= r && r < grid.length
                && 0 <= c && c < grid[0].length;
    }
    /**
     * Brute force 同上（解法一）
     */
    public int islandPerimeter_1(int[][] grid) {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        int n = grid.length, m = grid[0].length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (grid[i][j] == 1) {
                    int cnt = 0;
                    for (int k = 0; k < 4; ++k) {
                        int tx = i + dx[k];
                        int ty = j + dy[k];
                        if (tx < 0 || tx >= n || ty < 0 || ty >= m || grid[tx][ty] == 0) {
                            cnt += 1;
                        }
                    }
                    ans += cnt;
                }
            }
        }
        return ans;
    }
}
