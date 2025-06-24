package com.answer.dynamic_programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q300_Longest_Increasing_Subsequence {
    /**
     * 最长递增子序列
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
     *
     * 示例 1：
     * 输入：nums = [10,9,2,5,3,7,101,18]
     * 输出：4
     * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
     */
    /**
     * Subsequence是不连续的，Subarray或者Continued Sequence是连续的
     *
     * Approach 1: Dynamic Programming 子序列问题是动态规划解决的经典问题
     *
     * First, the question is asking for the maximum or minimum of something.
     * Second, we have to make decisions that may depend on previously made decisions,
     * which is very typical of a problem involving subsequences.
     */
    public int lengthOfLIS(int[] nums) {
        int[] dp= new int[nums.length]; // dp[i]表示i之前包括i的以nums[i]结尾的最长递增子序列的长度
        Arrays.fill(dp, 1); // 每一个i，对应的dp[i]（即最长递增子序列）起始大小至少都是1

        for(int i = 1; i < nums.length; i++){
            for(int j = 0; j < i; j++){
                if(nums[i] > nums[j]){ // 位置i的最长升序子序列等于j从0到i-1各个位置的最长升序子序列 + 1 的最大值
                    dp[i] = Math.max(dp[i], dp[j] + 1); // 注意这里不是要dp[i] 与 dp[j] + 1进行比较，而是我们要取dp[j] + 1的最大值
                }
            }
        }

        int max = 1;
        for(int i = 0; i < nums.length; i++){
            max = Math.max(max, dp[i]); // 取长的子序列
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
        dp[0] = 1; //初始化就是边界情况
        int maxans = 1;

        for (int i = 1; i < nums.length; i++) { //自底向上遍历
            dp[i] = 1;
            for (int j = 0; j < i; j++) { //从下标0到i遍历
                if (nums[j] < nums[i]) {//找到前面比nums[i]小的数nums[j],即有dp[i]= dp[j]+1
                    dp[i] = Math.max(dp[i], dp[j] + 1);//因为会有多个小于nums[i]的数，也就是会存在多种组合了嘛，我们就取最大放到dp[i]
                }
            }

            maxans = Math.max(maxans, dp[i]); //求出dp[i]后，dp最大那个就是nums的最长递增子序列啦
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
        if(result.size() == 0){ // result用于保存最长上升子序列
            return 0;
        }
/*
       if(value > result.get(result.size() - 1)){ // 可省略
           return result.size();
       }
        if(value <= result.get(0)){
            return 0;
        }
*/
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
        return left; // 二分查找，找到第一个比value大的数, 并覆盖掉比value大的元素中最小的那个
    }
    // 没有使用二分查找 时间复杂度没有提高
/*    public int directSearch(ArrayList<Integer> result, int value){
        for(int i = 0; i < result.size(); i++){
            if(result.get(i) > value){
                return i;
            }
        }
        return result.size();
    }*/
}
