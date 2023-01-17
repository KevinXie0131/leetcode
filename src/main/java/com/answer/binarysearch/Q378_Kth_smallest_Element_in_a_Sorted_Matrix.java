package com.answer.binarysearch;

import java.util.*;

public class Q378_Kth_smallest_Element_in_a_Sorted_Matrix {
    public static void main(String[] args) {
        int [][] matrix = {{1,5,9},{10,11,13},{12,13,15}};
        int k = 8;
      //  int [][] matrix ={{1,2},{1,3}};
      //  int k = 2;

        System.out.println(kthSmallest(matrix, k));
        System.out.println(kthSmallest_BinarySearch(matrix, k));
    }

    /**
     * Approach 1: Min-Heap approach
     */
    public static int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<int[]> pq =
                new PriorityQueue<>(new Comparator<int[]>() {
                    public int compare(int[] a, int[] b) {
                        return a[0] - b[0];
                    }
                });

        int len = matrix.length;
        for(int i = 0; i < len; i++){
            pq.offer(new int[]{matrix[i][0], i, 0});
        }
        for(int i = 0; i < k - 1; i++){
            int[] cur = pq.poll();
            // If we have any new elements in the current row, add them
            if (cur[2] != len - 1) {
                pq.offer(new int[]{matrix[cur[1]][cur[2] + 1], cur[1], cur[2] + 1});
            }

        }

        return pq.poll()[0];
    }

    /**
     * Approach 2: Binary Search
     */
    public static int kthSmallest_BinarySearch(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (check(matrix, mid, k, n)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static boolean check(int[][] matrix, int mid, int k, int n) {
        int i = n - 1;
        int j = 0;
        int num = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] <= mid) {
                num += i + 1; //这个计数的方法很特别哦，不是一行一行加的。是一列一列加的！
                j++;
            } else {
                i--;
            }
        }
        return num >= k;
    }

}
