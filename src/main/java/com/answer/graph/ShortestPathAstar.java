package com.answer.graph;

public class ShortestPathAstar {
    /**
     *
     */
    public static void main(String[] args) {
        int[][] input = {{1, 2, 1},
                {1, 3, 1},
                {1, 5, 2},
                {2, 6, 1},
                {2, 4, 2},
                {2, 3, 2},
                {3, 4, 1},
                {4, 5, 1},
                {5, 6, 2},
                {5, 7, 1},
                {6, 7, 1}};
        aStar(7, 11, input );
    }

    public static void aStar(int vertex , int edge,  int[][] input) {

    }
}
