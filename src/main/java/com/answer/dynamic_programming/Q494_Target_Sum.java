package com.answer.dynamic_programming;

import java.util.Arrays;

public class Q494_Target_Sum {
    public static void main(String[] args) {
        int[] nums = {1,1,1,1,1};
        int target = 3;
        System.out.println(findTargetSumWays_1(nums, target));
    }
    /**
     * Approach 1: Brute Force / Backtracking 回溯算法
     * 类似Q39 Combination Sum
     */
    static int count = 0;

    public static int findTargetSumWays_1(int[] nums, int S) {
        calculate(nums, 0, 0, S);
        return count;
    }

    public static void calculate(int[] nums, int i, int sum, int S) {
        if (i == nums.length) {
            if (sum == S) {
                count++;
            }
        } else {
            calculate(nums, i + 1, sum + nums[i], S);
            calculate(nums, i + 1, sum - nums[i], S);
        }
    }
    /**
     * Approach 2: Recursion with Memoization 记忆化
     */
    int total;
    public int findTargetSumWays_2(int[] nums, int S) {
        total = Arrays.stream(nums)
                      .sum();

        int[][] memo = new int[nums.length][2 * total + 1];
        for (int[] row : memo) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }
        return calculate1(nums, 0, 0, S, memo);
    }

    public int calculate1(int[] nums, int i, int sum, int S, int[][] memo) {
        if (i == nums.length) {
            if (sum == S) {
                return 1;
            } else {
                return 0;
            }
        } else {
            if (memo[i][sum + total] != Integer.MIN_VALUE) {
                return memo[i][sum + total];
            }
            int add = calculate1(nums, i + 1, sum + nums[i], S, memo);
            int subtract = calculate1(nums, i + 1, sum - nums[i], S, memo);
            memo[i][sum + total] = add + subtract;
            return memo[i][sum + total];
        }
    }
    /**
     * 2D Dynamic Programming 动态规划 （二维dp数组）
     * 0-1背包（每个元素只可以使用一次） “有多少种不同的填满背包最大容量的方法“
     * 本题要如何使表达式结果为target，既然为target，那么就一定有 left组合 - right组合 = target。
     * left + right = sum，而sum是固定的。right = sum - left
     * left - (sum - left) = target 推导出 left = (target + sum)/2 。
     * target是固定的，sum是固定的，left就可以求出来。此时问题就是在集合nums中找出和为left的组合。
     */
    public int findTargetSumWays_4(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int diff = sum - target;
        if (diff < 0 || diff % 2 != 0) {
            return 0;
        }
        int n = nums.length, neg = diff / 2; // neg类似背包的容量
        // dp[i][j] 表示在数组 nums 的前 i 个数中选取元素，使得这些元素之和等于 j 的方案数
        int[][] dp = new int[n + 1][neg + 1]; // dp[i][j]：使用 下标为[0, i]的nums[i]能够凑满j（包括j）这么大容量的包，有dp[i][j]种方法。
        dp[0][0] = 1; // 只有背包容量为 物品0 的容量的时候，方法为1，正好装满。

        for (int i = 1; i <= n; i++) {
            int num = nums[i - 1];
            for (int j = 0; j <= neg; j++) {
                // 不放物品i：即背包容量为j，里面不放物品i，装满有dp[i - 1][j]中方法。
                // 放物品i： 即：先空出物品i的容量，背包容量为（j - 物品i容量），放满背包有 dp[i - 1][j - 物品i容量] 种方法。
                // 本题中，物品i的容量是nums[i]，价值也是nums[i]。递推公式：dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i]];
                // 考到这个递推公式，我们应该注意到，j - nums[i] 作为数组下标，如果 j - nums[i] 小于零呢？
                // 说明背包容量装不下 物品i，所以此时装满背包的方法值 等于 不放物品i的装满背包的方法，即：dp[i][j] = dp[i - 1][j];
                // 所以递推公式： if (nums[i] > j) dp[i][j] = dp[i - 1][j];
                //               else dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i]];
                dp[i][j] = dp[i - 1][j];
                if (j >= num) {
                    dp[i][j] += dp[i - 1][j - num]; // 类似装满背包的容量有多少种方法
                }
            }
        }
        return dp[n][neg];
    }
    /**
     * Approach 4: 1D Dynamic Programming
     * 因为每个物品（题目中的1）只用一次！
     * 这次和之前遇到的背包问题不一样了，之前都是求容量为j的背包，最多能装多少。
     * 本题则是装满有几种方法。其实这就是一个组合问题了。
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        if(target > sum) return 0;  // 此时没有方案
        if((target + sum) % 2 == 1) return 0; // 计算的过程中向下取整，如果除以2的余数不为0,此时没有方案
        int size = (target + sum) / 2;
        if(size < 0) return 0;

        int[] dp = new int[size + 1];
        dp[0] = 1; // 同样初始为1 ,即装满背包为0的方法有一种，放0件物品。

        for(int i = 0; i < nums.length; i++){ // 遍历物品放在外循环，遍历背包在内循环，且内循环倒序（为了保证物品只使用一次）
            for(int j= size; j >= nums[i]; j--){
                // 二维DP数组递推公式： dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i]];
                // 去掉维度i 之后，递推公式：dp[j] = dp[j] + dp[j - nums[i]] ，即：dp[j] += dp[j - nums[i]]
                dp[j] += dp[j - nums[i]];
            }
        }

        return dp[size];
    }
}
