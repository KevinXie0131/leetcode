package com.answer.priorityqueue;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Q1337_The_K_Weakest_Rows_in_a_Matrix {
    /**
     * 矩阵中战斗力最弱的 K 行
     * 一个大小为 m * n 的矩阵 mat，矩阵由若干军人和平民组成，分别用 1 和 0 表示。
     * 请你返回矩阵中战斗力最弱的 k 行的索引，按从最弱到最强排序。
     * 如果第 i 行的军人数量少于第 j 行，或者两行军人数量相同但 i 小于 j，那么我们认为第 i 行的战斗力比第 j 行弱。
     * 军人 总是 排在一行中的靠前位置，也就是说 1 总是出现在 0 之前。
     * given an m x n binary matrix mat of 1's (representing soldiers) and 0's (representing civilians). The soldiers are positioned in front of the civilians. That is, all the 1's will appear to the left of all the 0's in each row.
     * A row i is weaker than a row j if one of the following is true:
     *  The number of soldiers in row i is less than the number of soldiers in row j.
     *  Both rows have the same number of soldiers and i < j.
     * Return the indices of the k weakest rows in the matrix ordered from weakest to strongest.
     *
     * 示例 1：
     * 输入：mat =
     * [[1,1,0,0,0],
     *  [1,1,1,1,0],
     *  [1,0,0,0,0],
     *  [1,1,0,0,0],
     *  [1,1,1,1,1]],
     *  k = 3
     * 输出：[2,0,3]
     * 解释：每行中的军人数目：
     *  行 0 -> 2
     *  行 1 -> 4
     *  行 2 -> 1
     *  行 3 -> 2
     *  行 4 -> 5
     *  从最弱到最强对这些行排序后得到 [2,0,3,1,4]
     */
    /**
     * 最小堆
     */
    public int[] kWeakestRows(int[][] mat, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        int[] res = new int[k];

        for (int index = 0; index < mat.length; index++) {
            int count = 0;
            for (int i = 0; i < mat[index].length; i++) {
                if (mat[index][i] == 1) {
                    count++;
                } else {
                    break;
                }
            }
            queue.offer(new int[]{count, index});
        }
        for (int i = 0; i < k; i++) {
            res[i] = queue.poll()[1];
        }
        return res;
    }

    /**
     * 朴素解法
     * 对矩阵进行遍历，统计每一行的军人数量，并以二元组的形式进行存储。
     * 然后对所有行的战力进行排序，选出战力最小的 k 个下标即是答案。
     * 时间复杂度：遍历矩阵的复杂度为 O(m∗n)；排序复杂度为 O(mlogm)；构造答案复杂度为 O(k)。整体复杂度为 O(max(m∗n,mlogm))
     * 空间复杂度：O(m) 空间用于存储所有的行战力；O(logm) 空间用于排序。整体复杂度为 O(m+logm)
     */
    public int[] kWeakestRows1(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] all = new int[m][2];

        for (int i = 0; i < m; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                count += mat[i][j];
            }
            all[i] = new int[]{count, i};
        }

        Arrays.sort(all, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);

        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = all[i][1];
        }
        return ans;
    }
    /**
     * 二分 + 优先队列（堆）
     * 根据「军人总是排在靠前位置」的特性，我们可以通过「二分」找到每一行最后一个军人的位置，
     * 从而在 O(logn) 的复杂度内统计出每行的军人数量（战力情况）。
     * 同时由于我们只需要「战力最弱」的 k 行数据，这引导我们可以建立一个「战力大根堆」来做，
     * 「战力大根堆」存放着数量最多为 k 的战力二元组，堆顶元素为战力最大的数对。
     * 每次通过「二分」得到当前行的战力值后，判断当前战力值与堆顶元素的战力大小关系：
     *  如果当前战力值比堆顶的元素要大：直接丢弃当前战力值（不可能属于在第 k 小的集合中）；
     *  如果当前战力值比堆顶的元素要小：将堆顶元素弹出，将当前行放入堆中。
     *
     *  时间复杂度：二分得到每行的战力情况，复杂度为 O(mlogn)；堆中最多有 k 个元素，将行信息存入堆中复杂度为 O(mlogk)；
     *             构造答案复杂度为 O(klogk)。整体复杂度为 O(m∗(logn+logk))
     * 空间复杂度：O(k)
     */
    public int[] kWeakestRows2(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        PriorityQueue<int[]> q = new PriorityQueue<>((a,b)->{ // 最大堆
            if (a[0] != b[0]) return b[0] - a[0];
            return b[1] - a[1];
        });
       // PriorityQueue<int[]> q = new PriorityQueue<>((a,b)-> a[0] != b[0] ? b[0] - a[0] : b[1] - a[1]); // works to

        for (int i = 0; i < m; i++) {
            int left = 0, right = n - 1;
            while (left <= right) { // 可以通过二分查找的方法，找出一行中最后的那个 1 的位置。如果其位置为 pos，那么这一行 1 的个数就为 pos+1
                int mid = (left + right ) >>> 1;
                if (mat[i][mid] == 1) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            int cur = left == n ? n - 1 : mat[i][left] == 1 ? left : left - 1;

            if (q.size() == k && q.peek()[0] > cur) {
                q.poll();
            }
            if (q.size() < k) {
                q.add(new int[]{cur, i});
            }
        }
        int[] ans = new int[k];
        int idx = k - 1;
        while (!q.isEmpty()) {
            ans[idx--] = q.poll()[1];
        }
        return ans;
    }
}
