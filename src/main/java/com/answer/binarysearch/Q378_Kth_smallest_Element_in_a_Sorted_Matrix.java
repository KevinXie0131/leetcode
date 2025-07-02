package com.answer.binarysearch;

import java.util.*;

public class Q378_Kth_smallest_Element_in_a_Sorted_Matrix {
    /**
     * 有序矩阵中第 K 小的元素
     * 一个 n x n 矩阵 matrix ，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
     * Given an n x n matrix where each of the rows and columns is sorted in ascending order, return the kth smallest element in the matrix.
     * 请注意，它是 排序后 的第 k 小元素，而不是第 k 个 不同 的元素。
     * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
     * 进阶：
     * 你能否用一个恒定的内存(即 O(1) 内存复杂度)来解决这个问题?
     * 你必须找到一个内存复杂度优于 O(n^2) 的解决方案。You must find a solution with a memory complexity better than O(n^2).
     */
    public static void main(String[] args) {
        int [][] matrix = {{1,5,9},
                           {10,11,13},
                           {12,13,15}};
        int k = 6; // 输出：13 解释：矩阵中的元素为 [1,5,9,10,11,12,13,13,15]，第 8 小元素是 13
      //  int [][] matrix ={{1,2},{1,3}};
      //  int k = 2;
     //   System.out.println(kthSmallest(matrix, k));
        System.out.println(kthSmallest3(matrix, k));
    }
    /**
     * 直接排序
     * 最直接的做法是将这个二维数组转成一维数组，并对该一维数组进行排序。最后这个一维数组中的第 k 个数即为答案。
     */
    public int kthSmallest0(int[][] matrix, int k) {
        int rows = matrix.length, columns = matrix[0].length;
        int[] sorted = new int[rows * columns];
        int index = 0;
        for (int[] row : matrix) {
            for (int num : row) {
                sorted[index++] = num;
            }
        }
        Arrays.sort(sorted);
        return sorted[k - 1];
    }
    /**
     * Approach 1: Min-Heap approach 归并排序
     * 由题目给出的性质可知，这个矩阵的每一行均为一个有序数组。问题即转化为从这 n 个有序数组中找第 k 大的数，可以想到利用归并排序的做法，归并到第 k 个数即可停止。
     * 一般归并排序是两个数组归并，而本题是 n 个数组归并，所以需要用小根堆维护，以优化时间复杂度
     */
    public static int kthSmallest(int[][] matrix, int k) {
/*        PriorityQueue<int[]> pq =
                new PriorityQueue<>(new Comparator<int[]>() {
                    public int compare(int[] a, int[] b) {
                        return a[0] - b[0];
                    }
                });*/
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        // 在于维护一组“最小值候选人”：
        // 你需要保证最小值必然从这组候选人中产生，于是每次只要从候选人中弹出最小的一个即可。
        // 我们来选择第一组候选人，在这里可以选择第一列，因为每一个数字都是其对应行的最小值，全局最小值也必然在其中。
        int len = matrix.length;
        for(int i = 0; i < len; i++){
            pq.offer(new int[]{matrix[i][0], i, 0});
        }
        for(int i = 0; i < k - 1; i++){ // 在整个矩阵中，每次弹出矩阵中最小的值，第k个被弹出的就是我们需要的数字。
            int[] cur = pq.poll(); // 弹出候选人里最小一个
            // If we have any new elements in the current row, add them 如果这一行还没被弹完
            if (cur[2] != len - 1) { // 如何找到下一个候选人, 其实非常简单，刚才弹出的位置右移一格就行了
                pq.offer(new int[]{matrix[cur[1]][cur[2] + 1], cur[1], cur[2] + 1}); // 我们每次弹出候选人当中的最小值，然后把上次弹出候选人的右边一个补进来，就能一直保证全局最小值在候选人列表中产生，
            }
        }
        return pq.poll()[0];
    }
    /**
     * another form 第二种解法只利用了一部分性质（每一行是一个有序数列，而忽视了列之间的关系）
     */
    public int kthSmallest1(int[][] matrix, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> matrix[o1[0]][o1[1]] - matrix[o2[0]][o2[1]]);
        int n = matrix.length;
        for(int i = 0; i < n; i++){
            queue.offer(new int[]{i, 0});
        }
        while(--k > 0) {
            int[] min = queue.poll();
            int x = min[0],y = min[1] + 1;
            if(y < n) {
                queue.offer(new int[]{x, y});
            }
        }
        int[] min = queue.poll();
        return matrix[min[0]][min[1]];
    }
    /**
     * 1. 找出二维矩阵中最小的数 left，最大的数 right，那么第 k 小的数必定在 left ~ right 之间
     * 2. mid=(left+right)/2；在二维矩阵中寻找小于等于 mid 的元素个数 count
     * 3. 若这个 count 小于 k，表明第 k 小的数在右半部分且不包含 mid，即 left=mid+1, right=right
     * 4. 若这个 count 大于 k，表明第 k 小的数在左半部分且可能包含 mid，即 left=left, right=mid
     * 5. 因为每次循环中都保证了第 k 小的数在 left ~ right 之间，当 left==right 时，第 k 小的数即被找出，等于 right
     * 注意：这里的 left mid right 是数值，不是索引位置。
     */
   static  public int kthSmallest3(int[][] matrix, int k) {
        int row = matrix.length;
        int col = matrix[0].length;
        int left = matrix[0][0];
        int right = matrix[row - 1][col - 1];
        while (left < right) {
            // 每次循环都保证第K小的数在start~end之间，当start==end，第k小的数就是start
            int mid = left + (right - left) / 2;

            int count = findNotBiggerThanMid(matrix, mid, row, col);  // 找二维矩阵中<=mid的元素总个数
            if (count < k) {
                left = mid + 1; // 第k小的数在右半部分，且不包含mid
            } else {
                right = mid;  // 第k小的数在左半部分，可能包含mid
            }
        }
        return right;
    }

    static private int findNotBiggerThanMid(int[][] matrix, int mid, int row, int col) {
        // 以列为单位找，找到每一列最后一个<=mid的数即知道每一列有多少个数<=mid
        int i = row - 1;
        int j = 0;
        int count = 0;
        while (i >= 0 && j < col) {
            if (matrix[i][j] <= mid) {
                count += i + 1;  // 第j列有i+1个元素<=mid
                j++;
            } else {
                i--; // 第j列目前的数大于mid，需要继续在当前列往上找
            }
        }
        return count;
    }
    /**
     * Approach 2: Binary Search 第三种解法则利用了全部性质，所以时间复杂度最佳。
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
