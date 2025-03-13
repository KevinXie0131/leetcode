package com.answer.dynamic_programming;

public class Q416_Partition_Equal_Subset_Sum {
    /**
     * classic Knapsack problem 0-1背包（每个元素只可以使用一次）
     * Approach 3: Bottom Up Dynamic Programming
     * 判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等
     *
     * 这是 背包算法可以解决的经典类型题目。
     * 本题的本质是，能否把容量为 sum / 2的背包装满。一个数字只有一个维度，即 重量等于价值。
     * 当数字 可以装满 承载重量为 sum / 2 的背包的背包时，这个背包的价值也是 sum / 2。
     *
     * 01背包相对于本题，主要要理解，题目中物品是nums[i]，重量是nums[i]，价值也是nums[i]，背包体积是sum/2。
     */
    public boolean canPartition_1(int[] nums) {
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        if(sum % 2 == 1) return false; //总和为奇数，不能平分
        int target = sum / 2;

        //  dp[i][j]表示从数组的 [0, i] 这个子区间内挑选一些正整数每个数只能用一次，使得这些数的和恰好等于 j。
        int[][] dp = new int[nums.length + 1][target + 1]; // 初始化为0

        for(int i = 1; i <= nums.length; i++){ // //物品 i 的重量是 nums[i]，其价值也是 nums[i]
            for(int j = 1; j <= target; j++){
                //如果某个物品单独的重量恰好就等于背包的重量，那么也是满足dp数组的定义的
                if (nums[i - 1] == target) {
                    return true;
                }

                if (j < nums[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 如果某个物品的重量小于j，那就可以看该物品是否放入背包
                    // dp[i - 1][j]表示该物品不放入背包，如果在 [0, i - 1] 这个子区间内已经有一部分元素，使得它们的和为 j ，那么 dp[i][j] = true；
                    // dp[i - 1][j - nums[i]]表示该物品放入背包。如果在 [0, i - 1] 这个子区间内就得找到一部分元素，使得它们的和为 j - nums[i]。
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - nums[i - 1]] + nums[i - 1]); //  0-1背包
                }
            }

            //剪枝一下，每一次完成內層的for-loop，立即檢查是否dp[target] == target，優化時間複雜度（26ms -> 20ms）
            if(dp[i][target] == target)
                return true;
        }
        return dp[nums.length][target] == target;  // 每个元素即是重量，也是价值
    }
    /**
     * Approach 4: Optimised Dynamic Programming - Using 1D Array
     *
     * 在动态规划：关于01背包问题，你该了解这些！（滚动数组）中就已经说明：
     * 如果使⽤⼀维dp数组，物品遍历的for循环放在外层，遍历背包的for循环放在内层，且内层for循环倒叙遍历
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        if(sum % 2 == 1) return false;
        int target = sum / 2;  // 背包体积是sum/2

        // 每个数组中的元素不会超过 100，数组的⼤⼩不会超过 200总和不会⼤于20000，背包最⼤只需要其中⼀半，所以10001⼤⼩就可以了
        int[] dp = new int[10001]; // dp[j] 表示: 容量（所能装的重量）为j的背包，所背的物品价值最大可以为dp[j]
        // 01背包
        for(int i = 0; i < nums.length; i++){
            for(int j= target; j >= nums[i]; j--){ // 每⼀个元素⼀定是不可重复放⼊，所以从⼤到⼩遍历
                // 01背包的递推公式为：dp[j] = max(dp[j], dp[j - weight[i]] + value[i]);
                // 本题，相当于背包里放入数值，那么物品i的重量是nums[i]，其价值也是nums[i]。
                // 所以递推公式：dp[j] = max(dp[j], dp[j - nums[i]] + nums[i])
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }
        // 集合中的元素正好可以凑成总和target
        return dp[target] == target;
    }
}
