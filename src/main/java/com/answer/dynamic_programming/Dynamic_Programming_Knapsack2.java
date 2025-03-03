package com.answer.dynamic_programming;

import java.util.Arrays;

public class Dynamic_Programming_Knapsack2 {
    /**
     * Multiple Knapsacks Problem 多重背包
     */
    public static void main(String[] args) {

        int[] value = {15,15,20,20,20,30,30};
        int[] weight =  {1,1,3,3,3,4,4};
        int[] nums = {2, 3, 2};

        int bagWeight = 10;
       /* for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 1) { // nums[i]保留到1，把其他物品都展开
                weight.push_back(weight[i]);
                value.push_back(value[i]);
                nums[i]--;
            }
        } */

        int[] dp = new int[bagWeight + 1];

        for(int i = 0; i < weight.length; i++) { // 遍历物品
            for(int j = bagWeight; j >= weight[i]; j--) { // 遍历背包容量
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
            System.out.println(Arrays.toString(dp));
        }

/*        for(int i = 0; i < weight.length; i++) { // 遍历物品
            for (int j = bagWeight; j >= weight[i]; j--) { // 遍历背包容量
                // 以上为01背包，然后加⼀个遍历个数
                for (int k = 1; k <= nums[i] && (j - k * weight[i]) >= 0; k++) { // 遍历个数
                    dp[j] = Math.max(dp[j], dp[j - k * weight[i]] + k * value[i]);
                }
            }
        }*/
    }
}
