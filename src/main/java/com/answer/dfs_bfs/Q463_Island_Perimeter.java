package com.answer.dfs_bfs;

public class Q463_Island_Perimeter {
    /**
     * 岛屿的周长Perimeter
     * 给定一个 row x col 的二维网格地图 grid ，其中：grid[i][j] = 1 表示陆地， grid[i][j] = 0 表示水域。
     * 网格中的格子 水平和垂直 方向相连（对角线方向不相连）(not diagonally)。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
     * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
     *
     * 输入：grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
     * 输出：16
     * 解释：它的周长是上面图片中的 16 个黄色的边
     */
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
                    for(int k = 0 ; k < 4; k++){  // 上下左右四个方向
                        int x = i + dirX[k];  // 计算周边坐标x,y
                        int y = j + dirY[k];
                        // 当前位置是陆地，并且从当前位置4个方向扩展的'新位置'是'水域'或'新位置'越界，则会为周长贡献一条边
                        if(x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == 0){ // x, y 在边界上或者是水域
                            sum++; // 遇到边界或者水，周长加一
                          //  continue; // can be commented
                        }
                    }
                }
            }
        }
        return sum;
    }
    /**
     * 解法二: 计算出总的岛屿数量，因为有一对相邻两个陆地，边的总数就减2，那么在计算出相邻岛屿的数量就可以了。
     *        result = 岛屿数量 * 4 - connect * 2; // 总周长 = 4 * 土地个数 - 2 * 接壤边的条数
     */
    public int islandPerimeter_4(int[][] grid) {
        // 计算岛屿的周长
        // 方法二 : 遇到相邻的陆地总周长就-2
        int sum = 0; // 陆地数量
        int connect = 0; // 相邻陆地数量

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    sum++;
                    // 统计上面和左边的相邻陆地
                    if(i >= 1 && grid[i - 1][j] == 1){ // 统计上边相邻陆地
                        connect++;
                    }
                    if(j >= 1 && grid[i][j - 1] == 1){ // 统计左边相邻陆地
                        connect++;
                    }
                    // 为什么没统计下边和右边？ 因为避免重复计算
                }
            }
        }
        return sum * 4 - connect * 2;
    }
    /**
     * 延伸 - 传统DFS解法(使用visited数组)（遇到边界 或是 海水 就edge++）
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
                if(visited[i][j] == false && grid[i][j] == 1){
                    result += dfs1(grid,  i,  j);
                }
            }
        }
        return result;
    }

    public int dfs1(int[][] grid, int x, int y){
        // 如果遇到 边界（x < 0 || y < 0 || x >= grid.length || y >= grid[0].length）或是 遇到海水(grid[x][y] == 0)就return 1（edge + 1）
        if(x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == 0){
            return 1;
        }
        if(visited[x][y] == true){   // 如果该地已经拜访过，就return 0 避免重复计算
            return 0;
        }
        int temp = 0;
        visited[x][y] = true;
        for(int k = 0; k < 4; k++){
            temp += dfs1(grid,  x + dir[k][0],  y + dir[k][1]);  //用temp 把edge存起來
        }
        return temp;
    }
    /**
     * DFS
     * 这道题用 DFS 来解并不是最优的方法。对于岛屿，直接用数学的方法求周长会更容易。不过这道题是一个很好的理解 DFS 遍历过程的例题
     */
    public int islandPerimeter(int[][] grid) {
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){ // 题目限制只有一个岛屿，计算一个即可
                    return dfs(grid, i, j);
                }
            }
        }
        return 0;
    }

    int dfs(int[][] grid, int r, int c) {
        if(!inArea(grid, r, c)){ // 函数因为「坐标 (r, c) 超出网格范围」返回，对应一条黄色的边
            return 1;
        }
        if(grid[r][c] == 0){      // 函数因为「当前格子是海洋格子」返回，对应一条蓝色的边
            return 1;
        }
        if(grid[r][c] == 2){ // 函数因为「当前格子是已遍历的陆地格子」返回，和周长没关系
            return 0;
        }
        grid[r][c] = 2;// 将格子标记为「已遍历过」
        return dfs(grid, r - 1, c)  // 访问上、下、左、右四个相邻结点
             + dfs(grid, r + 1, c)
             + dfs(grid, r, c - 1)
             + dfs(grid, r, c + 1);
    }
    // 判断坐标 (r, c) 是否在网格中
    boolean inArea(int[][] grid, int r, int c) {
        return 0 <= r && r < grid.length && 0 <= c && c < grid[0].length;
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
