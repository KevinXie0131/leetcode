package com.answer.dynamic_programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q300_Longest_Increasing_Subsequence {
    /**
     * Approach 1: Dynamic Programming
     *
     * First, the question is asking for the maximum or minimum of something.
     * Second, we have to make decisions that may depend on previously made decisions,
     * which is very typical of a problem involving subsequences.
     */
    public int lengthOfLIS(int[] nums) {
        int[] dp= new int[nums.length];
        Arrays.fill(dp, 1);

        for(int i = 1; i < nums.length; i++){
            for(int j = 0; j < i; j++){
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int max = 1;
        for(int i = 0; i < nums.length; i++){
            max = Math.max(max, dp[i]);
        }
        return max;
    }
    /**
     * 自底向上的动态规划
     * 用dp[i]表示以num[i]这个数结尾的最长递增子序列的长度
     * nums[i]结尾的自增子序列，只要找到比nums[i]小的子序列，加上nums[i]
     *
     * 很显然有这个规律：一个以nums[i]结尾的数组nums
     *     如果存在j属于区间[0，i-1],并且num[i]>num[j]的话，则有，dp(i) =max(dp(j))+1，(最优子结构)
     */
    public int lengthOfLIS1(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        //初始化就是边界情况
        dp[0] = 1;
        int maxans = 1;
        //自底向上遍历
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            //从下标0到i遍历
            for (int j = 0; j < i; j++) {
                //找到前面比nums[i]小的数nums[j],即有dp[i]= dp[j]+1
                if (nums[j] < nums[i]) {
                    //因为会有多个小于nums[i]的数，也就是会存在多种组合了嘛，我们就取最大放到dp[i]
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            //求出dp[i]后，dp最大那个就是nums的最长递增子序列啦
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }
    /**
     * Binary search
     * Time complexity: O(NlogN)
     */
    public static void main(String[] args) {
    //    int[] nums = {10,9,2,5,3,7,101,18};
        int[] nums = {0,1,0,3,2,3};
    //    int[] nums = {18,55,66,2,3,54};
     //   int[] nums = {7,7,7,7,7,7,7};
        int res = lengthOfLIS2(nums);
        System.out.println(res);
    }
    static public int lengthOfLIS2(int[] nums) {
        ArrayList<Integer> result = new ArrayList<>();
        int max = 1;
        for (int i = 0; i < nums.length; i++) {
            int position = binarySearch(result, nums[i]);

            if(position == result.size()){
                result.add(nums[i]);
            } else {
                result.set(position, nums[i]);
            }
            max = Math.max(max, result.size());
        }
        return max;
    }

    static public int binarySearch(ArrayList<Integer> result, int value){
        if(result.size() == 0){
            return 0;
        }
       if(value > result.get(result.size() - 1)){
           return result.size();
       }
        if(value <= result.get(0)){
            return 0;
        }

        int left = 0;
        int right = result.size() - 1;
        while(left <= right){
            int mid = left + ((right- left) >> 1);

            if(value < result.get(mid)){
                right = mid - 1;
            }else if(value > result.get(mid)){
                left = mid + 1;
            }else{
                return mid;
            }
        }
        return left; // 二分查找，找到第一个比value小的数, 并更新
    }

/*    public int directSearch(ArrayList<Integer> result, int value){
        for(int i = 0; i < result.size(); i++){
            if(result.get(i) > value){
                return i;
            }
        }
        return result.size();
    }*/
}
