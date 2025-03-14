package com.answer.dynamic_programming;

import java.util.Arrays;

public class Dynamic_Programming_Knapsack2 {
    /**
     * Multiple Knapsacks Problem 多重背包
     * 有N种物品和一个容量为V 的背包。第i种物品最多有Mi件可用，每件耗费的空间是Ci ，价值是Wi 。
     * 求解将哪些物品装入背包可使这些物品的耗费的空间 总和不超过背包容量，且价值总和最大。
     *
     * 多重背包和01背包是非常像的， 为什么和01背包像呢？
     * 每件物品最多有Mi件可用，把Mi件摊开，其实就是一个01背包问题了。
     */
    public static void main(String[] args) {
        multipleKnapsacks();
    }
    static public void multipleKnapsacks(){
        /**
         * bagWeight:背包容量
         * n:物品种类
         */
        int bagWeight, n;

        //获取用户输入数据，中间用空格隔开，回车键换行
        bagWeight = 10;
        n = 3;
        int[] weight = new int[n];
        int[] value = new int[n];
        int[] nums = new int[n];
        weight[0] = 1;
        weight[1] = 3;
        weight[2] = 4;
        value[0] = 15;
        value[1] = 20;
        value[2] = 30;
        nums[0] = 2;
        nums[1] = 3;
        nums[2] = 2;

        int[] dp = new int[bagWeight + 1];

        //先遍历物品再遍历背包，作为01背包处理
        for (int i = 0; i < n; i++) {
            for (int j = bagWeight; j >= weight[i]; j--) {
                //遍历每种物品的个数
                for (int k = 1; k <= nums[i] && (j - k * weight[i]) >= 0; k++) {
                    dp[j] = Math.max(dp[j], dp[j - k * weight[i]] + k * value[i]);
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        System.out.println(dp[bagWeight]);
    }

    public void multipleKnapsacks2(){
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
