package com.answer.graph;

public class Q886_Possible_Bipartition {
    /**
     * 可能的二分法
     * 给定一组 n 人（编号为 1, 2, ..., n）， 我们想把每个人分进任意大小(any size)的两组。每个人都可能不喜欢其他人，那么他们不应该属于同一组。
     * 给定整数 n 和数组 dislikes ，其中 dislikes[i] = [ai, bi] ，表示不允许将编号为 ai 和  bi的人归入同一组。当可以用这种方法将所有人分进两组时，返回 true；否则返回 false。
     *
     * Example 1:
     *  Input: n = 4, dislikes = [[1,2],[1,3],[2,4]]
     *  Output: true
     *  Explanation: The first group has [1,4], and the second group has [2,3].
     * Example 2:
     *  Input: n = 3, dislikes = [[1,2],[1,3],[2,3]]
     *  Output: false
     *  Explanation: We need at least 3 groups to divide them. We cannot put them in two groups.
     */
    public boolean possibleBipartition(int n, int[][] dislikes) {
        return false;
    }
}
