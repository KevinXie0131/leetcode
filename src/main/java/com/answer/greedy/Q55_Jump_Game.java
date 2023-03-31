package com.answer.greedy;

import java.util.Arrays;

public class Q55_Jump_Game {
    public static void main(String[] args) {
        int[] nums = {3,2,1,0,4};
        boolean res = canJump(nums);
        System.out.println(res);

        int[] nums1 = {3,2,1,0,4};
        System.out.println(canJump2(nums1));
    }

    /**
     * Greedy algorithm
     */
    public static boolean canJump(int[] nums) {
        if(nums.length == 1) return true;

        int cover = 0;

        for(int i = 0; i < nums.length; i++){
            if(cover < i){
                return false;
            }
            cover = Math.max(cover, nums[i] + i);
        }

        return true;
    }
    /**
     * Approach 4: Greedy
     * Official answer
     */
    public boolean canJump_1(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Approach 1: Backtracking
     * Time Limit Exceeded
     */
    public boolean canJump_2(int[] nums) {
        return backtracking(0, nums);
    }
    public boolean backtracking(int position, int[] nums) {
        if (position == nums.length - 1) {
            return true;
        }
        int furthestJump = Math.min(position + nums[position], nums.length - 1);

        for (int nextPosition = position + 1; nextPosition <= furthestJump; nextPosition++) {
            if (backtracking(nextPosition, nums)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Dynamic Programming
     * dp[i]表示跳到数组的第i个位置时，最多还剩下多少步。只要这个值还大于等于0，就表示第i个位置是可以到达的
     *
     * dp[i]和dp[i-1]的状态转移，考虑到跳到位置i有两种方式。
     *    1.先跳到i-1，且dp[i-1] >= 1, 此时再走一步到i位置，那此时i位置剩余的最大步骤是num[i]。
     *    2.还有一种是不必跳到i-1位置，考虑i-1位置剩下的最大步数dp[i-1]，不管之前是哪个位置跳过来的，只要从那个位置再多跳一步，就是位置i（注意：这里也有一个前提，那就是那个位置还有多跳一步的能力，就是dp[i-1] >= 1），因此此时i位置剩下的最大步数是dp[i-1] -1 。
     *    3.这两种情况取Max最大值即可。
     */
    public static boolean canJump2(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        if (nums[0] == 0) {
            return false;
        }

        int[] dp = new int[nums.length];
        dp[0] = nums[0]; //初始化

        for (int i = 1; i < nums.length; i++) {
            // 前一位置还有剩余步数走到下一位置
            if (dp[i - 1] < 1){
                return false;
            }

            dp[i] = Math.max(dp[i - 1] - 1, nums[i]);
            //当前位置剩余步数是负数，则说明不可达，直接返回，此后的位置也都不可达。
            if (dp[i] < 0) {
                return false;
            }
        }
        System.out.println(Arrays.toString(dp));
        return true;
    }
}
