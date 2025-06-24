package com.answer.dynamic_programming;

import java.util.Arrays;

public class Q1035_Uncrossed_Lines {
    /**
     * 不相交的线
     * 在两条独立的水平线上按给定的顺序写下 nums1 和 nums2 中的整数。
     * 现在，可以绘制一些连接两个数字 nums1[i] 和 nums2[j] 的直线，这些直线需要同时满足：
     *  nums1[i] == nums2[j]
     *  且绘制的直线不与任何其他连线（非水平线）相交。
     * 请注意，连线即使在端点也不能相交：每个数字只能属于一条连线。
     * 以这种方法绘制线条，并返回可以绘制的最大连线数。
     *
     * 示例 1：
     * 输入：nums1 = [1,4,2], nums2 = [1,2,4]
     * 输出：2
     * 解释：可以画出两条不交叉的线，如上图所示。
     * 但无法画出第三条不相交的直线，因为从 nums1[1]=4 到 nums2[2]=4 的直线将与从 nums1[2]=2 到 nums2[1]=2 的直线相交。
     */
    public static void main(String[] args) {
       int[] nums1 = {1,4,2};
       int[] nums2 = {1,2,4};
        System.out.println(maxUncrossedLines(nums1, nums2));
    }
    /**
     * 直线不能相交，这就是说明在字符串nums1中 找到一个与字符串nums2相同的子序列，且这个子序列不能改变相对顺序，
     * 只要相对顺序不改变，连接相同数字的直线就不会相交。 公共子序列指的是相对顺序不变
     * 本题说是求绘制的最大连线数，其实就是求两个字符串的最长公共子序列的长度！
     *
     * Approach 3: Dynamic Programming with Space Optimization
     * Note that the above recursive relation is exactly the same as in the classical problem, Longest Common Subsequence (LCS)
     * 寻找相同元素，并保持相同顺序。和Q1143.最长公共子序列 是一样的，代码一致
     */
    public static int maxUncrossedLines(int[] nums1, int[] nums2) {
        int M = nums1.length;
        int N = nums2.length;

        int[][] dp = new int[M + 1][N + 1];

        for(int i = 1; i <= M; i++){
            for(int j = 1; j <= N; j++){
                if(nums1[i - 1] == nums2[j - 1]){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        for(int[] arr : dp){
            System.out.println(Arrays.toString(arr));
        }
        return dp[M][N];
    }
}
