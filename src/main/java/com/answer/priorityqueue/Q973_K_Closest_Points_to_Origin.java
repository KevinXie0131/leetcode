package com.answer.priorityqueue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Q973_K_Closest_Points_to_Origin {
    /**
     * 最接近原点的 K 个点
     * 给定一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面(plane)上的一个点，并且是一个整数 k ，返回离原点 (0,0) 最近的 k 个点( closest points to the origin )。
     * 这里，平面上两点之间的距离是 欧几里德距离(Euclidean distance)（ √(x1 - x2)2 + (y1 - y2)2 ）。
     * 你可以按 任何顺序 返回答案。除了点坐标的顺序之外，答案 确保 是 唯一 的。
     *
     * 示例 1：
     *  输入：points = [[1,3],[-2,2]], k = 1
     *  输出：[[-2,2]]
     *  解释：
     *  (1, 3) 和原点之间的距离为 sqrt(10)，
     *  (-2, 2) 和原点之间的距离为 sqrt(8)，
     *  由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
     *  我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
     */
    /**
     * TopK 的问题 最小堆
     */
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        for(int[] point : points){
            int x = point[0];
            int y = point[1];
            int dist = x * x + y * y;
            queue.offer(new int[]{dist, x, y});
        }

        int[][] res = new int[k][2];
        for(int i = 0; i < k; i++){
            int[] point = queue.poll();
            res[i] = new int[]{point[1], point[2]};
        }
        return res;
    }
    /**
     * 使用一个大根堆实时维护前 k 个最小的距离平方。
     * 时间复杂度：O(nlogk)
     * 空间复杂度：O(k)
     */
    public int[][] kClosest0(int[][] points, int k) {
        // 本题是求前 K 小，因此用一个容量为 K 的大根堆，每次 poll 出最大的数，那堆中保留的就是前 K 小
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> b[0] - a[0]);

        for(int[] point : points){
            int x = point[0];
            int y = point[1];
            int dist = x * x + y * y;
            if(queue.size() < k){
                queue.offer(new int[]{dist, x, y});
            } else {
                if(queue.peek()[0] > dist){ // 如果当前点的距离平方比堆顶的点的距离平方要小，就把堆顶的点弹出，再插入当前的
                    queue.poll();
                    queue.offer(new int[]{dist, x, y});
                }
            }
        }

        int[][] res = new int[k][2];
        for(int i = k - 1; i >= 0; i--){
            int[] point = queue.poll();
            res[i] = new int[]{point[1], point[2]};
        }
        return res;
    }
    /**
     * another form
     */
    public int[][] kClosest3(int[][] points, int k) {
        // 默认是小根堆，实现大根堆需要重写一下比较器。
        PriorityQueue<int[]> pq = new PriorityQueue<>((p1, p2) -> p2[0] * p2[0] + p2[1] * p2[1] - p1[0] * p1[0] - p1[1] * p1[1]);
        for (int[] point: points) {
            if (pq.size() < k) { // 如果堆中不足 K 个，直接将当前 point 加入即可
                pq.offer(point);
            } else if (pq.comparator().compare(point, pq.peek()) > 0) { // 否则，判断当前点的距离是否小于堆中的最大距离，若是，则将堆中最大距离poll出，将当前点加入堆中。
                pq.poll();
                pq.offer(point);
            }
        }
        // 返回堆中的元素
        int[][] res = new int[pq.size()][2];
        int idx = 0;
        for (int[] point: pq) {
            res[idx++] = point;
        }
        return res;
    }
    /**
     * 排序: 将每个点到原点的欧几里得距离的平方从小到大排序后，取出前 k 个即可。
     */
    public int[][] kClosest1(int[][] points, int k) {
/*        Arrays.sort(points, new Comparator<int[]>() {
            public int compare(int[] point1, int[] point2) {
                return (point1[0] * point1[0] + point1[1] * point1[1]) - (point2[0] * point2[0] + point2[1] * point2[1]);
            }
        });*/
        Arrays.sort(points,(point1, point2) -> (point1[0] * point1[0] + point1[1] * point1[1]) - (point2[0] * point2[0] + point2[1] * point2[1]));
        return Arrays.copyOfRange(points, 0, k);
    }
    /**
     * 快速排序 时间复杂度O(N)
     */
    public int[][] kClosest4(int[][] points, int K) {
        // 使用快速选择（快排变形） 获取 points 数组中距离最小的 K 个点（第 4 个参数是下标，因此是 K - 1）
        return quickSelect(points, 0, points.length - 1, K - 1);
    }

    private int[][] quickSelect(int[][] points, int lo, int hi, int idx) {
        if (lo > hi) {
            return new int[0][0];
        }
        // 快排切分后，j 左边的点距离都小于等于 points[j], j 右边的点的距离都大于等于 points[j]。
        int j = partition(points, lo, hi);
        // 如果 j 正好等于目标idx，说明当前points数组中的[0, idx]就是距离最小的 K 个元素
        if (j == idx) {
            return Arrays.copyOf(points, idx + 1);
        }
        // 否则根据 j 与 idx 的大小关系判断找左段还是右段
        return j < idx? quickSelect(points, j + 1, hi, idx): quickSelect(points, lo, j - 1, idx);
    }

    private int partition(int[][] points, int lo, int hi) {
        int[] v = points[lo];
        int dist = v[0] * v[0] + v[1] * v[1];
        int i = lo, j = hi + 1;
        while (true) {
            while (++i <= hi && points[i][0] * points[i][0] + points[i][1] * points[i][1] < dist);
            while (--j >= lo && points[j][0] * points[j][0] + points[j][1] * points[j][1] > dist);
            if (i >= j) {
                break;
            }
            int[] tmp = points[i];
            points[i] = points[j];
            points[j] = tmp;
        }
        points[lo] = points[j];
        points[j] = v;
        return j;
    }
}
