package com.answer.array;

public class Q498_Diagonal_Traverse {
    /**
     * Approach 2: Simulation
     * 假设矩阵的行数为n，列数为m，那么对角线的总数为: n + m - 1
     */
    public int[] findDiagonalOrder(int[][] mat) {
        if (mat.length == 0 || mat[0].length == 0) return new int[0];
        int n = mat.length, m = mat[0].length;
        int[] res = new int[n * m];

        for (int i = 0, idx = 0; i < n + m - 1; i++)
        {
            if (i % 2 == 0) {//偶数对角线
                // 如果是偶数对角线 ，确定其横坐标x，从下往上遍历，将mat[x][i - x]加入res中
                for (int x = Math.min(i, n - 1); x >= Math.max(0, i - m + 1); x--) //从下往上遍历
                    res[idx++] = mat[x][i - x];
            }
            else {            //奇数对角线
               // 如果是奇数对角线 ，确定其横坐标x，从上往下遍历，将mat[x][i - x]加入 res中
                for (int x = Math.max(0, i - m + 1); x <= Math.min(i, n - 1); x++)//从上往下遍历
                    res[idx++] = mat[x][i - x];
            }
        }
        return res;
    }

}
