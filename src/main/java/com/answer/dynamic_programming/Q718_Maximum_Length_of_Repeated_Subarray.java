package com.answer.dynamic_programming;

import java.util.*;

public class Q718_Maximum_Length_of_Repeated_Subarray {
    /**
     * 最长重复子数组
     * 给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。
     *
     * 示例 1：
     * 输入：nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
     * 输出：3
     * 解释：长度最长的公共子数组是 [3,2,1] 。
     */
    /**
     * 注意题目中说的子数组，其实就是连续子序列。
     *
     * Approach #1: Brute Force with Initial Character Map
     * 要求两个数组中最长重复子数组，如果是暴力的解法 只需要先两层for循环确定两个数组起始位置，然后再来一个循环可以是for或者while，
     * 来从两个起始位置开始比较，取得重复子数组的长度。
     * Time Complexity: O(M∗N∗min(M,N)), where M,N are the lengths of A, B
     * Space Complexity: O(N), the space used by Bstarts
     */
    public int findLength_0(int[] A, int[] B) {
        int ans = 0;
        Map<Integer, ArrayList<Integer>> Bstarts = new HashMap();
        for (int j = 0; j < B.length; j++) {
            Bstarts.computeIfAbsent(B[j], x -> new ArrayList()).add(j);
        }
        for (int i = 0; i < A.length; i++) {
            if (!Bstarts.containsKey(A[i])){
                continue;
            }
            for (int j : Bstarts.get(A[i])) {
                int k = 0;
                while (i + k < A.length && j + k < B.length && A[i + k] == B[j + k]) {
                    k++;
                }
                ans = Math.max(ans, k);
            }
        }
        return ans;
    }
    /**
     * 2-D Dynamic Programming
     * 最长重复子数组
     * Does a Subarray have to be contiguous?
     * A subarray or substring will always be contiguous, but a subsequence need not be contiguous.
     * 时间复杂度：O(n × m)，n 为A长度，m为B长度
     * 空间复杂度：O(n × m)
     */
    public int findLength(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length + 1][nums2.length + 1]; // dp: 以i-1为尾的num1 和 以j-1为尾的num2 的最长重复子数组长度
        int result = 0;
        //dp[i][0]和dp[0][j]都被初始化为0
        for(int i = 1; i <= nums1.length; i++){ // 其实dp[i][j]的定义也就决定着，我们在遍历dp[i][j]的时候i 和 j都要从1开始
            for(int j = 1; j <= nums2.length; j++){
                if(nums1[i - 1] == nums2[j - 1]){ // 即当A[i - 1] 和B[j - 1]相等的时候，dp[i][j] = dp[i - 1][j - 1] + 1;
                    dp[i][j] = dp[i - 1][j - 1] + 1; // 两个数值相同，num1和num2同时回退一步
                    // result = Math.max(result, dp[i][j]);  // 也可以
                }
                if(dp[i][j] > result){
                    result = dp[i][j];
                }
            }
        }
        return result;
    }
    /**
     * 另一种形式(实现起来麻烦一些)
     * 如果定义 dp[i][j]为 以下标i为结尾的A，和以下标j 为结尾的B，那么 第一行和第一列毕竟要进行初始化，
     * 如果nums1[i] 与 nums2[0] 相同的话，对应的 dp[i][0]就要初始为1， 因为此时最长重复子数组为1。 nums2[j] 与 nums1[0]相同的话，同理
     */
    public int findLength_1(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length + 1][nums2.length + 1]; // dp[i][j]为 以下标i为结尾的A，和以下标j 为结尾的B，最长重复子数组长度
        int result = 0;

        // 要对第一行，第一列经行初始化
        for (int i = 0; i < nums1.length; i++) if (nums1[i] == nums2[0]) dp[i][0] = 1;
        for (int j = 0; j < nums2.length; j++) if (nums1[0] == nums2[j]) dp[0][j] = 1;

        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j] && i > 0 && j > 0) { // 防止 i-1 出现负数
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                if (dp[i][j] > result) result = dp[i][j];
            }
        }
        return result;
    }
    /**
     * 1-D Dynamic Programming
     * 空间压缩
     * 时间复杂度：O(n × m)，n 为A长度，m为B长度
     * 空间复杂度：O(m)
     */
    public int findLength_2(int[] nums1, int[] nums2) {
        int[] dp = new int[nums2.length + 1];
        int result = 0;

        for(int i = 1; i <= nums1.length; i++){
            for(int j = nums2.length; j >= 1; j--) {
                if(nums1[i - 1] == nums2[j - 1]) {
                    dp[j] = dp[j - 1] + 1;
                } else {
                    dp[j] = 0; // 注意这里不相等的时候要有赋0的操作
                }
                if(dp[j] > result) {
                    result = dp[j];
                }
            }
        }
        return result;
    }
}
