package com.answer.dynamic_programming;

import java.util.Arrays;

public class Q740_Delete_and_Earn {
    public static void main(String[] args) {
        int[] nums = {2,2,3,3,3,4};
        System.out.println(deleteAndEarn(nums));
    }
    /**
     * Dynamic Programming
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
            f[i][1] = f[i - 1][0] + i * cnts[i];
            f[i][0] = Math.max(f[i - 1][1], f[i - 1][0]);
        }
        for(int[] arr : f){
            System.out.println(Arrays.toString(arr));
        }

        return Math.max(f[max][0], f[max][1]);
    }
}
