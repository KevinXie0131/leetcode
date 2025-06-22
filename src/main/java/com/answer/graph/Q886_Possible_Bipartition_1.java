package com.answer.graph;

public class Q886_Possible_Bipartition_1 {
    /**
     * 并查集
     */
    public static void main(String[] args) {
/*        int n = 4;
        int[][] dislikes = {{1,2},{1,3},{2,4}};*/
       /* int n = 3;
        int[][] dislikes = {{1,2},{1,3},{2,3}};*/
        int n = 10;
        int[][] dislikes = {{1,2},{3,4},{5,6},{6,7},{8,9},{7,8}};
        System.out.println(possibleBipartition1(n, dislikes));
    }
    /**
     * Refer to Q785 Is Graph Bipartite
     * cannot pass test case
     * n = 10
     * dislikes = [[1,2],[3,4],[5,6],[6,7],[8,9],[7,8]]
     */
    static int[] connected;

    static public boolean possibleBipartition(int n, int[][] dislikes) {
        if(dislikes.length == 0) return true;
        boolean[][] dislikeStatus = new boolean[n + 1][n + 1];

        for(int[] dislike : dislikes){
            dislikeStatus[dislike[0]][dislike[1]] = true;
            dislikeStatus[dislike[1]][dislike[0]] = true;
        }

        connected = new int[n + 1];
        for(int i = 0; i <= n; i++) {
            connected[i] = i;
        }
        int num = n;
        for(int i = 1; i <= n; i++){
            int current = find(connected, i);

            for(int j = 1; j <= n; j++){
                if(dislikeStatus[i][j] == true){
                    if(find(connected, j) == current){
                        return false;
                    }
                } else if(i != j && num > 2) {
                    union(connected, i, j);
                    num--;
                }
            }
        }
        return num == 2; // 最后只有两组
    }
    /**
     * 对于本题，我们遍历每一个人，他与他不喜欢的人不应该在同一个集合中，如果在同一个集合中，就产生了冲突，直接返回 false。
     * 如果没有冲突，那么就将他所有不喜欢的人合并到同一个集合中。遍历结束，说明没有冲突，返回 true。
     */
    static public boolean possibleBipartition1(int n, int[][] dislikes) {
        if(dislikes.length == 0) return true;
        boolean[][] dislikeStatus = new boolean[n + 1][n + 1];

        for(int[] dislike : dislikes){
            dislikeStatus[dislike[0]][dislike[1]] = true;
            dislikeStatus[dislike[1]][dislike[0]] = true;
        }

        connected = new int[n + 1];
        for(int i = 0; i <= n; i++) {
            connected[i] = i;
        }
        for(int i = 1; i <= n; i++){
            int current = find(connected, i);
            int first = 0;
            for(int j = 1; j <= n; j++){
                if(dislikeStatus[i][j] == true){
                    if(first == 0) first = j;
                    if(find(connected, j) == current){ // 判断这个人是否与他不喜欢的人相连，如果相连则表示存在冲突
                        return false;
                    }
                    if(first > 0 && first != j && find(connected, j) != find(connected, first) ) {
                        union(connected, first, j); // 将每个人不喜欢的人连通起来
                    }
                }
            }
        }
        return true;
    }

    static public void union(int[] parent, int index1, int index2) {
        parent[find(parent, index2)] = find(parent, index1);
    }

    static public int find(int[] parent, int index) {
        if (parent[index] != index) {
            parent[index] = find(parent, parent[index]);
        }
        return parent[index];
    }
}
