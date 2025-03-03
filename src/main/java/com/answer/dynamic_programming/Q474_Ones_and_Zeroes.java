package com.answer.dynamic_programming;

public class Q474_Ones_and_Zeroes {

    /**
     * Approach #3 Using Recursion [Time Limit Exceeded]
     */
    public int findMaxForm_1(String[] strs, int m, int n) {
        return calculate(strs, 0, m, n);
    }
    public int calculate(String[] strs, int i, int zeroes, int ones) {
        if (i == strs.length)
            return 0;
        int[] count = countzeroesones(strs[i]);
        int taken = -1;
        if (zeroes - count[0] >= 0 && ones - count[1] >= 0)
            taken = calculate(strs, i + 1, zeroes - count[0], ones - count[1]) + 1;
        int not_taken = calculate(strs, i + 1, zeroes, ones);
        return Math.max(taken, not_taken);
    }
    public int[] countzeroesones(String s) {
        int[] c = new int[2];
        for (int i = 0; i < s.length(); i++) {
            c[s.charAt(i)-'0']++;
        }
        return c;
    }
    /**
     * Approach #4 Using Memoization [Accepted]
     */
    public int findMaxForm_2(String[] strs, int m, int n) {
        int[][][] memo = new int[strs.length][m + 1][n + 1];
        return calculate_2(strs, 0, m, n, memo);
    }
    public int calculate_2(String[] strs, int i, int zeroes, int ones, int[][][] memo) {
        if (i == strs.length)
            return 0;
        if (memo[i][zeroes][ones] != 0)
            return memo[i][zeroes][ones];
        int[] count = countzeroesones(strs[i]);
        int taken = -1;
        if (zeroes - count[0] >= 0 && ones - count[1] >= 0)
            taken = calculate_2(strs, i + 1, zeroes - count[0], ones - count[1], memo) + 1;
        int not_taken = calculate_2(strs, i + 1, zeroes, ones, memo);
        memo[i][zeroes][ones] = Math.max(taken, not_taken);
        return memo[i][zeroes][ones];
    }
    /**
     * Approach #5 2-D Dynamic Programming [Accepted]  0-1背包（每个元素只可以使用一次）
     * dp[m][n] denotes the maximum number of strings that can be included in the subset given only i 0's and j 1's are available.
     */
    public int findMaxForm(String[] strs, int m, int n) { // m个0 n个1
        int[][] dp = new int[m + 1][n + 1]; // 装满这个容器有多少物品

        for(String s : strs){
            int one = 0, zero = 0;
            for(char c : s.toCharArray()){
                if(c == '0'){
                    zero++;
                }else if(c == '1'){
                    one++;
                }
            }

            for(int i = m; i >= zero; i--){ //反序遍历
                for(int j = n; j >= one; j--){
                    dp[i][j] = Math.max(dp[i][j], dp[i - zero][j - one] + 1);
                }
            }
        }

        return dp[m][n];
    }
    /**
     * 3-D Dynamic Programming  0-1背包
     */
    public int findMaxForm_3(String[] strs, int m, int n) {
        int length = strs.length;
        // dp[i][j][k] 表示输入字符串在子区间 [0, i] 能够使用 j 个 0 和 k 个 1 的字符串的最大数量
        int[][][] dp = new int[length + 1][m + 1][n + 1];

        for (int i = 1; i <= length; i++) {
            int[] zerosOnes = getZerosOnes(strs[i - 1]); //相当于物品的重量
            int zeros = zerosOnes[0];
            int ones = zerosOnes[1];

            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    dp[i][j][k] = dp[i - 1][j][k];
                    if (j >= zeros && k >= ones) {
                        // 对应于不选当前第 i 个字符串 和 选择当前第 i 个字符串的情况下能够得到的字符串数目的最大值
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - zeros][k - ones] + 1);
                    }
                }
            }
        }
        return dp[length][m][n];
    }
    public int[] getZerosOnes(String str) {
        int[] zerosOnes = new int[2];
        int length = str.length();
        for (int i = 0; i < length; i++) {
            zerosOnes[str.charAt(i) - '0']++;
        }
        return zerosOnes;
    }
}
