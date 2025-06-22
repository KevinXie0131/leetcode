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
        System.out.println(possibleBipartition(n, dislikes));
    }
    /**
     * Refer to Q785 Is Graph Bipartite
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
