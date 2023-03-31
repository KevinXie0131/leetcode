package com.answer.dynamic_programming;

import java.util.Arrays;

public class Q740_Delete_and_Earn {
    public static void main(String[] args) {
        int[] nums = {2,2,3,3,3,4};
        System.out.println(deleteAndEarn(nums));

        System.out.println(deleteAndEarn_2(nums));
    }
    /**
     * Dynamic Programming
     *
     */
    static int[] cnts = new int[10010];

    public static int deleteAndEarn(int[] nums) {
        int n = nums.length;
        int max = 0;
        for (int x : nums) {
            cnts[x]++;
            max = Math.max(max, x);
        }
        // f[i][0] 代表「不选」数值 i；f[i][1] 代表「选择」数值 i
        int[][] f = new int[max + 1][2];
        for (int i = 1; i <= max; i++) {
            f[i][1] = f[i - 1][0] + i * cnts[i]; //当数值 i 被选，那么前一个数只能「不选」，同时为了总和最大数值 i 要选就全部选完
            f[i][0] = Math.max(f[i - 1][1], f[i - 1][0]); // 当数值 i 不被选择，那么前一个数「可选/可不选」，在两者中取 max 即可
        }
        for(int[] arr : f){
            System.out.println(Arrays.toString(arr));
        }

        return Math.max(f[max][0], f[max][1]);
    }

    /**
     * Dynamic Programming
     * 每个位置上的数字是可以在两种前结果之上进行选择的：
     *     如果你不删除当前位置的数字，那么你得到就是前一个数字的位置的最优结果。
     *     如果你觉得当前的位置数字i需要被删，那么你就会得到i - 2位置的那个最优结果加上当前位置的数字乘以个数。
     * 以上两个结果，你每次取最大的，记录下来，然后答案就是最后那个数字了。
     *
     */
    public static int deleteAndEarn_2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        }
        int len = nums.length;
        int max = nums[0];
        for (int i = 0; i < len; ++i) {
            max = Math.max(max, nums[i]);
        }
//      构造一个新的数组all
        int[] all = new int[max + 1];
        for (int item : nums) {
            all[item] ++;
        }
        int[] dp = new int[max + 1];
        dp[1] = all[1] * 1;
        dp[2] = Math.max(dp[1], all[2] * 2);
        if(dp.length == 2){
            return dp[1];
        }
        // 动态规划求解
        for (int i = 2; i <= max; ++i) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + i * all[i]);
        }
        System.out.println(Arrays.toString(dp));
        return dp[max];
    }
}
