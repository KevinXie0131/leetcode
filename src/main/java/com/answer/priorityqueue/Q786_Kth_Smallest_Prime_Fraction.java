package com.answer.priorityqueue;

import java.util.*;

public class Q786_Kth_Smallest_Prime_Fraction {
    /**
     * https://leetcode.cn/problems/k-th-smallest-prime-fraction/solutions/1609883/by-lfool-1q1d/
     * 多路归并技巧总结
     */
    /**
     * 第 K 个最小的质数分数
     * 给你一个按递增顺序排序的数组 arr 和一个整数 k 。数组 arr 由 1 和若干 质数 组成，且其中所有整数互不相同。
     * You are given a sorted integer array arr containing 1 and prime numbers, where all the integers of arr are unique. You are also given an integer k.
     * 对于每对满足 0 <= i < j < arr.length 的 i 和 j ，可以得到分数 arr[i] / arr[j] 。
     * For every i and j where 0 <= i < j < arr.length, we consider the fraction arr[i] / arr[j].
     * 那么第 k 个最小的分数是多少呢?  以长度为 2 的整数数组返回你的答案, 这里 answer[0] == arr[i] 且 answer[1] == arr[j] 。
     * Return the kth smallest fraction considered. Return your answer as an array of integers of size 2, where answer[0] == arr[i] and answer[1] == arr[j].
     *
     * 示例 1：
     * 输入：arr = [1,2,3,5], k = 3
     * 输出：[2,5]
     * 解释：已构造好的分数,排序后如下所示:
     *      1/5, 1/3, 2/5, 1/2, 3/5, 2/3
     *      很明显第三个最小的分数(The third fraction)是 2/5
     * 示例 2：
     * 输入：arr = [1,7], k = 1
     * 输出：[1,7]
     */
    public static void main(String[] args) {
        int[] arr = {1,2,3,5};
        int k = 3;
        System.out.println(Arrays.toString(kthSmallestPrimeFraction(arr, k)));
    }
    /**
     * 「扫描点对」+「优先队列（堆）」
     */
    public int[] kthSmallestPrimeFraction0(int[] arr, int k) {
        int n = arr.length;
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> Double.compare(b[0] * 1.0 / b[1], a[0] * 1.0 / a[1])); // 大根堆
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double t = arr[i] * 1.0 / arr[j];
                if (q.size() < k || q.peek()[0] * 1.0 / q.peek()[1] > t) { // 如果当前计算值比堆顶元素小，那么堆顶元素不可能是第 k 小的值，使用当前计算值置换掉堆顶元素。
                    if (q.size() == k) {
                        q.poll();
                    }
                    q.add(new int[]{arr[i], arr[j]});
                }
            }
        }
        return q.poll();
    }
    /**
     * 多路归并」+「优先队列（堆）
     * 利用「数组内元素严格单调递增」的特性, 使用「优先队列（堆）」来维护多个有序序列的当前头部的最小值即可。
     */
    public int[] kthSmallestPrimeFraction_0(int[] arr, int k) {
        int n = arr.length;
        PriorityQueue<int[]> q = new PriorityQueue<>((i, j) -> { // 小顶堆
            double i1 = arr[i[0]] * 1.0 / arr[i[1]];
            double i2 = arr[j[0]] * 1.0 / arr[j[1]];
            return Double.compare(i1, i2);
        });
        for (int i = 1; i < n; i++) {
            q.add(new int[]{0, i});
        }
        // 我们首先将每个分类中最小的分数入队，优先队列底层是一个小顶堆，顶部元素最小；然后出队最小的分数，
        // 将和该分数分母一样但分子更大的下一个分数入队，这样可以保证每次出队的元素一定是最小的；进行k-1次操作后；
        // 优先队列首部就是最小的第k个元素了。
        while (--k > 0) {
            int[] poll = q.poll();
            int i = poll[0], j = poll[1];
            if (i + 1 < j) {  // 分子下标不能超过分母
                q.add(new int[]{i + 1, j});
            }
        }
        int[] poll = q.poll();
        return new int[]{arr[poll[0]], arr[poll[1]]};
    }
    /**
     * another form
     */
    public int[] kthSmallestPrimeFraction7(int[] arr, int k) {
        // 定义比较规则
        Queue<int[]> q = new PriorityQueue<>((a, b) -> arr[a[0]] * arr[b[1]] - arr[b[0]] * arr[a[1]]);
        // 加入头节点
        for (int j = 1; j < arr.length; j++) {
            q.offer(new int[]{0, j});
        }
        for (int cnt = 1; cnt <= k; cnt++) {
            // 弹出队顶元素，即最小元素
            int[] cur = q.poll();
            int i = cur[0], j = cur[1];
            if (cnt == k) {
                return new int[]{arr[i], arr[j]};
            }
            if (i + 1 < j) {// 分子下标不能超过分母
                q.offer(new int[]{i + 1, j});
            }
        }
        return new int[]{-1, -1};
    }
    /**
     * Approach #2: Heap - 多路归并/ 优先队列
     * Time: O(klogn)
     * Space: O(n)
     */
    public static int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int[] res = new int[2];
        int m = arr.length;

        PriorityQueue<int[]> heap = new PriorityQueue<int[]>((x, y) -> arr[x[0]] * arr[y[1]] - arr[y[0]] * arr[x[1]]);
        /**
         * PriorityQueue<int[]> q = new PriorityQueue<>((a,b)->Double.compare(b[0]*1.0/b[1],a[0]*1.0/a[1]));
         */
        for(int i = 1; i < m; i++){
            heap.offer(new int[]{0, i});
        }

        while(k - 1 > 0 && !heap.isEmpty()){
            int[] pos = heap.poll();
            int x = pos[0];
            int y = pos[1];
            if(x < y - 1){
                heap.offer(new int[]{x + 1,pos[1]});
            }
            k--;
        }
        return new int[]{arr[heap.peek()[0]], arr[heap.peek()[1]]};
    }
    /**
     * Sorting 自定义排序
     * 比较两个分数时，我们可直接对它们的值进行比较，会引入浮点数误差. 一种可行的替代方法是用：a×d<b×c来替代
     * 时间复杂度：O(n^2 logn)
     * 空间复杂度：O(n^2)
     */
    public int[] kthSmallestPrimeFraction_1(int[] arr, int k) {
        int m = arr.length;
        List<int[]> frac = new ArrayList<int[]>();
        for(int i = 0; i < m; i++){
            for(int j = i + 1;j < m;j++){
                frac.add(new int[]{arr[i], arr[j]});
            }
        }
        Collections.sort(frac, (x, y) -> x[0] * y[1] - x[1] * y[0]);
        return frac.get(k-1);
    }
    /**
     * 二分查找 + 双指针
     */
    public int[] kthSmallestPrimeFraction6(int[] arr, int k) {
        double l = 0, r = 1;
        while(true){
            double expectedFraction = (l + r) / 2;//在0-1.0取数，统计数组元素组合成的分数中小于expectedFraction个数
            int cnt = 0;//记录分数中小于expectedFraction个数
            int x = 1, y = arr[arr.length - 1];//分别记录分子和分母，当前x/y记录的就是最小的分数，因为x最小，y最大
            for(int j = 1; j < arr.length; j++){//分母下标从1开始遍历
                int i = 0;//分子下标从0开始
                while ((double)arr[i] / (double)arr[j] <= expectedFraction){//分子/分母 小于expectedFraction，记录
                    i++;//分子下标右移
                    if((double)x / y < (double)arr[i - 1] / arr[j]){//最新的分数大于原来记录的分数，更新
                        x = arr[i - 1];
                        y = arr[j];
                    }
                }
                cnt += i;//i对应的是符合条件的分数个数（i从0开始，i == 2结束，说明只有0，1俩下标也就是2个对应分子符合条件）
            }
            if(cnt == k){//小于expectionFraction的个数
                return new int[]{x, y};//最大的分数就是对应的第k小的个数
            }else if(cnt < k){//个数少了，将expectedFraction再扩大点，接下来的范围在(expectedFraction, r)选
                l = expectedFraction;
            }else {//个数多了，将expectedFraction再扩大点，接下来的范围在(l, expectedFraction)选
                r = expectedFraction;
            }
        }
    }
    /**
     * Approach #1: Binary Search
     */
    double eps = 1e-8;

    public int[] kthSmallestPrimeFraction_2(int[] arr, int k) {
        int n = arr.length;
        double left = 0;
        double right = 1;
        int[] ans = new int[2];
        while (right - left > eps) {
            double mid = (left + right) / 2;
            if (check(arr, mid, ans) >= k) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return ans;
    }

    int check(int[] arr, double val, int[] ans) {
        int count = 0;
        int n = arr.length;
        for (int i = 0, j = 1; j < n; j++) {
            while (arr[i + 1] * 1.0 / arr[j] <= val) {
                i++;
            }
            if (arr[i] * 1.0 / arr[j] <= val) {
                count += i + 1;
            }
            if (Math.abs(arr[i] * 1.0 / arr[j] - val) < eps) {
                ans[0] = arr[i];
                ans[1] = arr[j];
            }
        }
        return count;
    }
    /**
     * Official answer - Binary Search
     */
    public int[] kthSmallestPrimeFraction_3(int[] primes, int K) {
        double lo = 0, hi = 1;
        int[] ans = new int[]{0, 1};

        while (hi - lo > 1e-9) {
            double mi = lo + (hi - lo) / 2.0;
            int[] res = under(mi, primes);
            if (res[0] < K) {
                lo = mi;
            } else {
                ans[0] = res[1];
                ans[1] = res[2];
                hi = mi;
            }
        }
        return ans;
    }

    public int[] under(double x, int[] primes) {
        // Returns {count, numerator, denominator}
        int numer = 0, denom = 1, count = 0, i = -1;
        for (int j = 1; j < primes.length; ++j) {
            // For each j, find the largest i so that primes[i] / primes[j] < x
            // It has to be at least as big as the previous i, so reuse it ("two pointer")
            while (primes[i+1] < primes[j] * x) ++i;

            // There are i+1 fractions: (primes[0], primes[j]),
            // (primes[1], primes[j]), ..., (primes[i], primes[j])
            count += i+1;
            if (i >= 0 && numer * primes[j] < denom * primes[i]) {
                numer = primes[i];
                denom = primes[j];
            }
        }
        return new int[]{count, numer, denom};
    }
}
