package com.answer.dynamic_programming;

import java.util.Arrays;

public class Q494_Target_Sum {

    public static void main(String[] args) {
        int[] nums = {1,1,1,1,1};
        int target = 3;
        System.out.println(findTargetSumWays_1(nums, target));
    }
    /**
     * Approach 1: Brute Force / Backtracking
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
     * Approach 2: Recursion with Memoization
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
     * Approach 4: 1D Dynamic Programming
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        if(target > sum) return 0;
        if((target + sum) % 2 == 1) return 0;
        int size = (target + sum) / 2;
        if(size < 0) return 0;

        int[] dp = new int[size + 1];
        dp[0] = 1;

        for(int i = 0; i < nums.length; i++){
            for(int j= size; j >= nums[i]; j--){
                dp[j] += dp[j - nums[i]];
            }
        }

        return dp[size];
    }
}
