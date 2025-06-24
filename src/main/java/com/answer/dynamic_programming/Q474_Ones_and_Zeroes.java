package com.answer.dynamic_programming;

public class Q474_Ones_and_Zeroes {
    /**
     * 一和零
     * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
     * 请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。
     * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
     *
     * 示例 1：
     * 输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
     * 输出：4
     * 解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
     * 其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。
     */
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
     *
     * 本题中strs数组里的元素就是物品，每个物品都是一个
     * 而m和n相当于是一个背包，两个维度的背包 本题其实是01背包问题, 只不过这个背包有两个维度，一个是m 一个是n，而不同长度的字符串就是不同大小的待装物品
     */
    public int findMaxForm(String[] strs, int m, int n) { // m个0 n个1
        // dp[i][j]：最多有i个0和j个1的strs的最大子集的大小为dp[i][j]。
        int[][] dp = new int[m + 1][n + 1]; // 装满这个容器有多少物品
        // 物品就是strs里的字符串，背包容量就是题目描述中的m和n。
        for(String s : strs){  // 遍历物品
            int one = 0, zero = 0;
            for(char c : s.toCharArray()){
                if(c == '0'){
                    zero++;
                }else if(c == '1'){
                    one++;
                }
            }
        // dp[i][j] 可以由前一个strs里的字符串推导出来，strs里的字符串有zeroNum个0，oneNum个1。dp[i][j] 就可以是 dp[i - zeroNum][j - oneNum] + 1。
        // 然后我们在遍历的过程中，取dp[i][j]的最大值。所以递推公式：dp[i][j] = max(dp[i][j], dp[i - zeroNum][j - oneNum] + 1);
        // 此时大家可以回想一下01背包的递推公式：dp[j] = max(dp[j], dp[j - weight[i]] + value[i]);
        // 对比一下就会发现，字符串的zeroNum和oneNum相当于物品的重量（weight[i]），字符串本身的个数相当于物品的价值（value[i]）。
            for(int i = m; i >= zero; i--){  // 遍历背包容量且从后向前遍历
                for(int j = n; j >= one; j--){
                    dp[i][j] = Math.max(dp[i][j], dp[i - zero][j - one] + 1); // 物品的重量有了两个维度
                }
            }
        }
        return dp[m][n];
    }
    /**
     * 3-D Dynamic Programming  0-1背包
     * 本题是求 给定背包容量，装满背包最多有多少个物品。
     */
    public int findMaxForm_3(String[] strs, int m, int n) {
        int length = strs.length;
        // dp[i][j][k] 表示输入字符串在子区间 [0, i] 能够使用 j 个 0 和 k 个 1 的字符串的最大数量
        int[][][] dp = new int[length + 1][m + 1][n + 1];
        /* 	dp[i][j][k] represents, if choosing items among strs[0] to strs[i] to form a subset,
			what is the maximum size of this subset such that there are no more than m 0's and n 1's in this subset.
			Each entry of dp[i][j][k] is initialized with 0

			transition formula:
			using x[i] to indicates the number of 0's in strs[i]
			using y[i] to indicates the number of 1's in strs[i]

			dp[i][j][k] = max(dp[i-1][j][k], dp[i-1][j - x[i]][k - y[i]] + 1) */
        // num_of_zeros records the number of 0's for each str
        // num_of_ones records the number of 1's for each str
        // find the number of 0's and the number of 1's for each str in strs

        // num_of_zeros[0] indicates the number of 0's for str[0]
        // num_of_ones[0] indiates the number of 1's for str[1]

        // initialize the 1st plane of dp[i][j][k], i.e., dp[0][j][k]
        // if num_of_zeros[0] > m or num_of_ones[0] > n, no need to further initialize dp[0][j][k],
        // because they have been intialized to 0 previously
        for (int i = 1; i <= length; i++) {
            int[] zerosOnes = getZerosOnes(strs[i - 1]); //相当于物品的重量
            int zeros = zerosOnes[0];
            int ones = zerosOnes[1];

         /*	if j - num_of_zeros[i] >= 0 and k - num_of_ones[i] >= 0:
				dp[i][j][k] = max(dp[i-1][j][k], dp[i-1][j - num_of_zeros[i]][k - num_of_ones[i]] + 1)
			else:
				dp[i][j][k] = dp[i-1][j][k]
		*/
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
