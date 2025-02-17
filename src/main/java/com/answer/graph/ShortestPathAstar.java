package com.answer.graph;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class ShortestPathAstar {
    /**
     * 这是最经典的广搜类型题目
     * 提交后，发现超时了。因为本题地图足够大，且 n 也有可能很大，导致有非常多的查询。
     *  广搜中，做了很多无用的遍历，我们能不能让便利方向，向这终点的方向去遍历呢？这样我们就可以避免很多无用遍历
     */
    public static void main(String[] args) {
        int n = 7;
        int[][] input = {{5, 2, 5, 4},
                        {1, 1, 2, 2} ,
                        {1, 1, 8, 8},
                        {1, 1, 8, 7},
                        {2, 1, 3, 3},
                        {4, 6, 4, 6},
                        {2, 2, 22, 22}};
        for (int i = 0; i < n; i++) {
         // int result = bfs(input[i][0], input[i][1], input[i][2], input[i][3]);
        //  System.out.println(result);
            int result = aStar(input[i][0], input[i][1], input[i][2], input[i][3]);
            System.out.println(result);
        }
    }

    public static int bfs(int a1, int a2, int b1, int b2)
    {
        int[][] moves = new int[1001][1001];
        int[][] dir = {{-2,-1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}};

        Deque<Integer> queue = new LinkedList<>();
        queue.offer(a1);
        queue.offer(a2);

        while(!queue.isEmpty()) {
            int m = queue.poll();
            int n = queue.poll();
            if(m == b1 && n == b2) {
                return moves[b1][b2];
            }

            for(int i = 0; i < 8; i++) {
                int mm = m + dir[i][0];
                int nn = n + dir[i][1];
                if(mm < 1 || mm > 1000 || nn < 1 || nn > 1000){
                    continue;
                }
                if(moves[mm][nn] == 0) {
                    moves[mm][nn] = moves[m][n] + 1;
                    queue.offer(mm);
                    queue.offer(nn);
                }
            }
        }
        return -1;
    }
    /**
     * Astar 是一种 广搜的改良版。 有的是 Astar是 dijkstra 的改良版。
     * 其实只是场景不同而已 我们在搜索最短路的时候， 如果是无权图（边的权值都是1） 那就用广搜，代码简洁，时间效率和 dijkstra 差不多 （具体要取决于图的稠密）
     * 如果是有权图（边有不同的权值），优先考虑 dijkstra。
     * 而 Astar 关键在于 启发式函数， 也就是 影响 广搜或者 dijkstra 从 容器（队列）里取元素的优先顺序。
     *
     * BFS版本的A * (BFS 是没有目的性的 一圈一圈去搜索， 而 A * 是有方向性的去搜索, A * 可以节省很多没有必要的遍历步骤)
     * 启发式函数 要影响的就是队列里元素的排序, 这是影响BFS搜索方向的关键
     * 采用欧拉距离才能最大程度体现 点与点之间的距离。使用欧拉距离计算 和 广搜搜出来的最短路的节点数是一样的
     */
    static double Heuristic(Knight knight, int b1, int b2) { // 欧拉距离
      //  return (knight.x - b1) * (knight.x - b1) + (knight.y - b2) * (knight.y - b2); // 统一不开根号，这样可以提高精度
        return Math.sqrt(Math.pow(b1 - knight.x, 2) + Math.pow(b2 - knight.y, 2));
    }

    public static int aStar(int a1, int a2, int b1, int b2) {
        int[][] moves = new int[1001][1001];
        int[][] dir = {{-2,-1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}};
        PriorityQueue<Knight> myQueue = new PriorityQueue<>(Comparator.comparingDouble(k -> k.f)); // 比较f的最小堆

        Knight start = new Knight(a1, a2);
        start.g = 0;
        start.h = Heuristic(start, b1, b2);
        start.f = start.g + start.h;

        myQueue.offer(start);

        while(!myQueue.isEmpty()) {
            Knight cur = myQueue.poll();

            if(cur.x == b1 && cur.y == b2){
                return moves[b1][b2];
            }

            for(int i = 0; i < 8; i++) {
                Knight next = new Knight();

                next.x = cur.x + dir[i][0];
                next.y = cur.y + dir[i][1];
                if(next.x < 1 || next.x > 1000 || next.y < 1 || next.y > 1000) {
                    continue;
                }
                if(moves[next.x][next.y] == 0) {
                    moves[next.x][next.y] = moves[cur.x][cur.y] + 1;
                    // 开始计算F
                    next.g = cur.g + 5; // 统一不开根号，这样可以提高精度，马走日，1 * 1 + 2 * 2 = 5
                    next.h = Heuristic(next, b1, b2);
                    next.f = next.g + next.h;
                    myQueue.offer(next);
                }
            }
        }
        return -1;
    }

}
// F = G + H
// G = 从起点到该节点路径消耗
// H = 该节点到终点的预估消耗
class Knight {
    int x, y;
    double g, h, f;
    public Knight () {
    }
    public Knight (int a1, int a2) {
        x = a1;
        y = a2;
    }
}
