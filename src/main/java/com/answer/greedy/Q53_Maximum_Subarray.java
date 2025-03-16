package com.answer.greedy;

public class Q53_Maximum_Subarray {
    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray0(nums));
    }
    /**
     * Approach 3: Divide and Conquer (Advanced) 分治法 提交后右Time Limit Exceeded
     */
    public static int maxSubArray0(int[] nums) {
        return getMax(nums, 0, nums.length - 1);
    }
    public static int getMax(int[] nums, int left, int right){
        if(left == right){
            return nums[left];
        }
        int mid = (right + left) >>> 1;
        int leftMax =  getMax(nums, left, mid);
        int rightMax = getMax(nums, mid + 1, right);
        int crossMax = getCrossMax(nums, left, right); // 求中间开始最大值

        return Math.max(crossMax, Math.max(leftMax, rightMax)); // 从左边最大的，右边最大的 和 中间最大的 取最大值
    }
    public static int getCrossMax(int[] nums, int left, int right){
        int mid = (right + left) >>> 1;
        int leftSum = nums[mid];
        int leftMax = leftSum;
        for(int i = mid - 1; i >=0; i--){
            leftSum += nums[i];
            leftMax = Math.max(leftMax, leftSum);
        }
        int rightSum = nums[mid + 1];
        int rightMax = rightSum;
        for(int i = mid + 2; i <= nums.length - 1; i++){
            rightSum += nums[i];
            rightMax = Math.max(rightMax, rightSum);
        }
        return leftMax + rightMax; // 从中间节点向两边找最大的总和
    }
    /**
     * Greedy 贪心解法
     * 局部最优：当前“连续和”为负数的时候立刻放弃，从下一个元素重新计算“连续和”，因为负数加上下一个元素 “连续和”只会越来越小。
     * 全局最优：选取最大“连续和”
     * 局部最优的情况下，并记录最大的“连续和”，可以推出全局最优。
     * 其关键在于：不能让“连续和”为负数的时候加上下一个元素，而不是 不让“连续和”加上一个负数
     */
    public static int maxSubArray(int[] nums) {
        int sum = 0;
        int max = Integer.MIN_VALUE; // 要初始化为最小负数
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            max = Math.max(max, sum);  // 取区间累计的最大值（相当于不断确定最大子序终止位置）
            if(sum < 0){
                sum = 0; // 这相当于是暴力解法中的不断调整最大子序和区间的起始位置。相当于重置最大子序起始位置，因为遇到负数一定是拉低总和
            }
        }
        return max;
    }
    /**
     * Brute force 暴力解法
     * Time Limit Exceeded
     * 时间复杂度: O(N2)
     */
    public int maxSubArray_1(int[] nums) {
        int result = Integer.MIN_VALUE;
        int count = 0;
        for (int i = 0; i < nums.length; i++) { // 设置起始位置
            count = 0;
            for (int j = i; j < nums.length; j++) { // 每次从起始位置i开始遍历寻找最大值
                count += nums[j];
                result = count > result ? count : result; // if (count > result) result = count; 如果 count 取到最大值了，及时记录下来了
            }
        }
        return result;
    }
    /**
     * Dynamic Programming 动态规划
     * 最大的连续子序列的和 (连续子数组（子数组最少包含一个元素)
     */
    public int maxSubArray_3(int[] nums) {
        int result = nums[0];
        int[] dp = new int[nums.length]; // dp[i]：包括下标i（以nums[i]为结尾）的最大连续子序列和
        dp[0] = nums[0]; // 用第一个数值来初始化

        for(int i = 1; i < nums.length; i++){
            // dp[i]只有两个方向可以推出来：
            //    dp[i - 1] + nums[i]，即：nums[i]加入当前连续子序列和
            //    nums[i]，即：从头开始计算当前连续子序列和
            // 一定是取最大的，所以dp[i] = max(dp[i - 1] + nums[i], nums[i]);
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]); // 状态转移公式, nums[i]是从头开始计数

            if(dp[i] > result){ // result 保存dp[i]的最大值
                result = dp[i];
            }
        //    result = result > dp[i] ? result : dp[i];  // 也可以
        }
        return result;
    }
    /**
     * 一维dp数组
     * 因为dp[i]的递推公式只与前一个值有关，所以可以用一个变量代替dp数组，空间复杂度为O(1)
     */
    public int maxSubArray_4(int[] nums) {
        int res = nums[0];
        int pre = nums[0];
        for(int i = 1; i < nums.length; i++) {
            pre = Math.max(pre + nums[i], nums[i]);
            res = Math.max(res, pre);
        }
        return res;
    }
}
