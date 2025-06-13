package com.answer.greedy;

public class Q53_Maximum_Subarray {
    /**
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组 (subarray)（子数组最少包含一个元素），返回其最大和。
     * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出：6
     * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
     * Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
     * 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
     */
    public static void main(String[] args) {
       // int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
       // int[] nums = {5,4,-1,7,8};
       int[] nums = {-2,-1};
        System.out.println(maxSubArray_5(nums));
    }
    /**
     * Approach 3: Divide and Conquer (Advanced) 分治法 提交后右Time Limit Exceeded
     */
    public static int maxSubArray0(int[] nums) {
        return getMax(nums, 0, nums.length - 1);
    }
    // 辅助递归函数，在区间 [left, right] 内求最大子数组和
    public static int getMax(int[] nums, int left, int right){
        if(left == right){
            return nums[left];// 递归终止条件：只有一个元素
        }
        int mid = (right + left) >>> 1;
        int leftMax =  getMax(nums, left, mid); // 递归求解左半部分最大子数组和
        int rightMax = getMax(nums, mid + 1, right); // 递归求解右半部分最大子数组和
      //  int crossMax = getCrossMax(nums, left, right); // 求中间开始最大值 // 求跨越中点的最大子数组和
        int crossMax = maxCrossingSum(nums, left, mid, right);
        return Math.max(crossMax, Math.max(leftMax, rightMax)); // 从左边最大的，右边最大的 和 中间最大的 取最大值 // 返回三者中的最大值
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
    // 求跨越中点的最大子数组和
    static private int maxCrossingSum(int[] nums, int left, int mid, int right) {
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        // 从中点向左扫描，找最大和
        for (int i = mid; i >= left; i--) {
            sum += nums[i];
            leftSum = Math.max(leftSum, sum);
        }

        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        // 从中点+1向右扫描，找最大和
        for (int i = mid + 1; i <= right; i++) {
            sum += nums[i];
            rightSum = Math.max(rightSum, sum);
        }

        // 返回跨越中点的最大子数组和
        return leftSum + rightSum;
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
     * 时间复杂度: O(N^2)
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
           /* if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            } */
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
            pre = Math.max(pre + nums[i], nums[i]); // pre = pre > 0 ? pre + nums[i] : nums[i]; // 也可以
            res = Math.max(res, pre);
        }
        return res;
    }
    /**
     * 前缀和
     * 由于子数组的元素和等于两个前缀和的差，所以求出 nums 的前缀和，问题就变成 121. 买卖股票的最佳时机 了。
     * 本题子数组不能为空，相当于一定要交易一次。
     *
     * 我们可以一边遍历数组计算前缀和，一边维护前缀和的最小值（相当于股票最低价格），
     * 用当前的前缀和（卖出价格）减去前缀和的最小值（买入价格），就得到了以当前元素结尾的子数组和的最大值（利润），
     * 用它来更新答案的最大值（最大利润）。
     *
     * 请注意，由于题目要求子数组不能为空，应当先计算前缀和-最小前缀和，再更新最小前缀和。相当于不能在同一天买入股票又卖出股票。
     */
    public static int maxSubArray_5(int[] nums) {
        int len = nums.length;
        if(len == 1) return nums[0];

        int[] prefixSum = new int[len + 1];
        prefixSum[0] = 0;
        for (int i = 0; i < len; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i]; // 当前的前缀和
        }

        int min = 0;
        int result = Integer.MIN_VALUE;

        for (int i = 1; i < len + 1; i++) {
            result = Math.max(result, prefixSum[i] - min);// 减去前缀和的最小值
            min = Math.min(min, prefixSum[i]);  // 维护前缀和的最小值
        }

        return result;
    }
}
