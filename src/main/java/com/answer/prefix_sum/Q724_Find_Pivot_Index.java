package com.answer.prefix_sum;

import java.util.Arrays;

public class Q724_Find_Pivot_Index {
    /**
     * Given an array of integers nums, calculate the pivot index of this array.
     * The pivot index is the index where the sum of all the numbers strictly to the left of the index is
     * equal to the sum of all the numbers strictly to the index's right.
     * If the index is on the left edge of the array, then the left sum is 0 because there are no elements
     * to the left. This also applies to the right edge of the array.
     * Return the leftmost pivot index. If no such index exists, return -1.
     * 寻找数组的中心下标
     * 一个整数数组 nums ，请计算数组的 中心下标 。
     * 数组 中心下标 是数组的一个下标，其左侧所有元素相加的和等于右侧所有元素相加的和。
     * 如果中心下标位于数组最左端，那么左侧数之和视为 0 ，因为在下标的左侧不存在元素。这一点对于中心下标位于数组最右端同样适用。
     * 如果数组有多个中心下标，应该返回 最靠近左边 的那一个。如果数组不存在中心下标，返回 -1 。
     */
    public static void main(String[] args) {
        /**
         * 输入：nums = [1, 7, 3, 6, 5, 6] 输出：3
         * 解释：中心下标是 3 。
         * 左侧数之和 sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11 ，
         * 右侧数之和 sum = nums[4] + nums[5] = 5 + 6 = 11 ，二者相等。
         */
        //   int[] nums = {1,7,3,6,5,6};
        /**
         * 输入：nums = [2, 1, -1] 输出：0
         * 解释：中心下标是 0 。
         * 左侧数之和 sum = 0 ，（下标 0 左侧不存在元素），
         * 右侧数之和 sum = nums[1] + nums[2] = 1 + -1 = 0 。
         */
        int[] nums = {2, 1, -1};
        System.out.println(pivotIndex(nums));
    }
    /**
     * 前缀和
     */
    static public int pivotIndex(int[] nums) {
        int n = nums.length;
        int[] prefixSum = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        for(int i = 1; i < n + 1; i++){
            if(prefixSum[i-1] == prefixSum[n] - prefixSum[i-1] - nums[i - 1]){
                return i-1;
            }
            // 判定一个下标是否为”中心索引“的时候，利用前缀和计算左侧值和右侧值。
           /* if(prefixSum[i-1] == prefixSum[n] - prefixSum[i] ){ // works too
                return i-1;
            }*/
        }
        return -1;
    }
    /**
     * Approach #1: Prefix Sum
     */
    public static int pivotIndex_1(int[] nums) {
        int sum = 0;
        int leftsum = 0;
        // nums 处理不涉及并行操作，使用循环要比 Arrays.stream 快
        // sum = Arrays.stream(nums).sum();
        for (int x: nums) {
            sum += x;  //数组的和
        }
        for (int i = 0; i < nums.length; ++i) {
            if (leftsum == sum - leftsum - nums[i]) {  //发现相同情况
                return i;
            }
            leftsum += nums[i];
        }
        return -1;
    }
    /**
     * 遍历一遍求出总和sum
     * 遍历第二遍求中心索引左半和leftSum
     * 同时根据sum和leftSum 计算中心索引右半和rightSum
     * 判断leftSum和rightSum是否相同
     */
    public int pivotIndex1(int[] nums) {
        int sum = 0;
        for(int n : nums){
            sum += n; // 总和
        }
        int left = 0;  // 中心索引左半和
        int right = 0;  // 中心索引右半和

        for(int i = 0; i < nums.length; i++){
            left += nums[i];
            right = sum -left + nums[i];  // leftSum 里面已经有 nums[i]，多减了一次，所以加上
            if(left == right){
                return i;
            }
        }
        return -1; // 不存在
    }
    /**
     * 前缀和
     * 记数组的全部元素之和为 total，当遍历到第 i 个元素时，设其左侧元素之和为 sum，则其右侧元素之和为 total−nums[i]−sum。
     * 左右侧元素相等即为 sum=total−nums[i]-sum，即 2×sum+nums[i]=total
     *
     * sumLeft + sumRight + nums[p] = sumTotal;
     * sumLeft = sumRight
     * 可以得出 sumLeft * 2 + nums[p] = sumTotal;
     */
    public int pivotIndex5(int[] nums) {
        int total = Arrays.stream(nums).sum();

        int sum = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (2 * sum + nums[i] == total) {
                return i;
            }
            sum += nums[i];
        }
        return -1;
    }
    /**
     * 每次比较前 i 项和和后 i项和(前项和与后项和均包含nums[i]`)。
     *  如果相等就返回 i。
     *  不相等进行下次循环直到最后。
     */
    public int pivotIndex6(int[] nums) {
        int sum1 = 0;
        for(int num: nums){
            sum1 += num;
        }
        int sum2 = 0;
        for(int i = 0; i<nums.length; i++){
            sum2 += nums[i];
            if(sum1 == sum2){
                return i;
            }
            sum1 = sum1 - nums[i];
        }
        return -1;
    }

}
