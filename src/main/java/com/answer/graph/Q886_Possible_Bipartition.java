package com.answer.graph;

import java.util.*;

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
    /**
     * Refer to Q785 Is Graph Bipartite
     */
    public boolean possibleBipartition(int n, int[][] dislikes) {
        boolean[][] dislikeStatus = new boolean[n + 1][n + 1];
        int[] groups = new int[n + 1]; //存储每个人的分组情况
        // 为了操作方便（代码量），直接使用邻接矩阵。由于是互相不喜欢，不存在一个喜欢另一个，另一个不喜欢一个的情况，因此这是无向图。
        // 而无向图邻接矩阵实际上是会浪费空间
        for(int[] dislike : dislikes){
            dislikeStatus[dislike[0]][dislike[1]] = true;
            dislikeStatus[dislike[1]][dislike[0]] = true;
        }
        //遍历每个人 尝试给他们分组
        for (int i = 1; i <= n; i++) {
            if (groups[i] == 0 && !dfs(dislikeStatus, i, groups, 1, n)) { // 如果没分组 且分组失败
                return false;
            }
        }
        return true;
    }

    private boolean dfs(boolean[][] dislikeStatus, int index, int[] groups, int group, int n) {
        groups[index] = group;

        for (int i = 1; i <= n ; i++) {
            if (dislikeStatus[index][i]) {  //如果不喜欢
                if(groups[i] != 0 && groups[i] == group){
                    return false;
                }
                if(groups[i] == 0 && !dfs(dislikeStatus, i, groups, -group, n)){
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * 深度优先搜索 邻接表
     */
    public boolean possibleBipartition1(int n, int[][] dislikes) {
        int[] groups = new int[n + 1]; //存储每个人的分组情况

        ArrayList<Integer>[] dislikeStatus = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++){
            dislikeStatus[i] = new ArrayList<>();
        }
        for(int[] dislike : dislikes){   // 用二维数组存储每个人不喜欢的人的列表
            dislikeStatus[dislike[0]].add(dislike[1]);
            dislikeStatus[dislike[1]].add(dislike[0]);
        }
        //遍历每个人 尝试给他们分组
        for (int i = 1; i <= n; i++) {
            if (groups[i] == 0 && !dfs1(dislikeStatus, i, groups, 1, n)) { // 如果没分组 且分组失败
                return false;// 如果没分组且分组后有冲突
            }
        }
        return true;
    }

    private boolean dfs1(ArrayList<Integer>[] dislikeStatus, int index, int[] groups, int group, int n) {
        groups[index] = group;      // 分组
        ArrayList<Integer> list = dislikeStatus[index];
        // 遍历不喜欢人列表
        for (int next : list) {
          //如果不喜欢
            if(groups[next] != 0 && groups[next] == group){ // 如果分组并且和当前不喜欢的人在一组，冲突
                return false;
            }
            if(groups[next] == 0 && !dfs1(dislikeStatus, next, groups, -group, n)){   // 如果未分组且分组后有冲突
                return false;
            }
        }
        return true;
    }
    /**
     * 广度优先搜索
     */
    public boolean possibleBipartition2(int n, int[][] dislikes) {
        int[] groups = new int[n + 1];

        ArrayList<Integer>[] dislikeStatus = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++){
            dislikeStatus[i] = new ArrayList<>();
        }
        for(int[] dislike : dislikes){
            dislikeStatus[dislike[0]].add(dislike[1]);
            dislikeStatus[dislike[1]].add(dislike[0]);
        }

        for (int i = 1; i <= n; ++i) {
            if (groups[i] == 0) {
                Queue<Integer> queue = new ArrayDeque<Integer>();
                queue.offer(i);
                groups[i] = 1;

                while (!queue.isEmpty()) {
                    int index = queue.poll();

                    for (int next : dislikeStatus[index]) {
                        if (groups[next] != 0 && groups[next] == groups[index]) {
                            return false;
                        }
                        if (groups[next] == 0) {
                            groups[next] = -groups[index];
                            queue.offer(next);
                        }
                    }
                }
            }
        }
        return true;
    }
}
