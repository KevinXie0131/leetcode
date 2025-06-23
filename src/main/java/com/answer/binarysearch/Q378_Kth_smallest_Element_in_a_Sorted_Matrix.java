package com.answer.binarysearch;

import java.util.*;

public class Q378_Kth_smallest_Element_in_a_Sorted_Matrix {
    /**
     * 有序矩阵中第 K 小的元素
     * 一个 n x n 矩阵 matrix ，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
     * Given an n x n matrix where each of the rows and columns is sorted in ascending order, return the kth smallest element in the matrix.
     * 请注意，它是 排序后 的第 k 小元素，而不是第 k 个 不同 的元素。
     * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
     * 你必须找到一个内存复杂度优于 O(n2) 的解决方案。You must find a solution with a memory complexity better than O(n2).
     * @param args
     */
    public static void main(String[] args) {
        int [][] matrix = {{1,5,9},{10,11,13},{12,13,15}};
        int k = 8; // 输出：13 解释：矩阵中的元素为 [1,5,9,10,11,12,13,13,15]，第 8 小元素是 13
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
