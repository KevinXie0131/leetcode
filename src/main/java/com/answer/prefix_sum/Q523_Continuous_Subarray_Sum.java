package com.answer.prefix_sum;

import java.util.HashMap;
import java.util.HashSet;

public class Q523_Continuous_Subarray_Sum {
    /**
     * Given an integer array nums and an integer k, return true if nums has a good subarray or false otherwise.
     * A good subarray is a subarray where:
     *  its length is at least two, and
     *  the sum of the elements of the subarray is a multiple of k.
     * Note that:
     *  A subarray is a contiguous part of the array.
     *  An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a multiple of k.
     * 连续的子数组和
     * 给你一个整数数组 nums 和一个整数 k ，如果 nums 有一个 好的子数组 返回 true ，否则返回 false：
     * 一个 好的子数组 是：
     *  长度 至少为 2 ，且
     *  子数组元素总和为 k 的倍数。
     * 注意：
     *  子数组 是数组中 连续 的部分。
     *  如果存在一个整数 n ，令整数 x 符合 x = n * k ，则称 x 是 k 的一个倍数。0 始终 视为 k 的一个倍数。
     */
    public static void main(String[] args) {
        int[] nums = {23,2,6,4,7};
        int k = 6;
        System.out.println(checkSubarraySum3(nums, k));
    }
    /**
     * 暴力
     * 注意一种特殊的情况：两个连续的0：0 % k = 0
     * 令i为左端点，j为右端点，遍历每种情况
     *  首先当出现两个连续的0时，直接返回true
     *  当发现i后面所有的数相加之和都没有k大时，就可以直接跳出循环了
     */
    public boolean checkSubarraySum0(int[] nums, int k) { // Time Limit Exceeded
        // 当出现两个连续的0时，直接返回true，因为 0 % k = 0
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == 0 && nums[i + 1] == 0) {
                return true;
            }
        }
        // 其中i为左端点，j为右端点，遍历每种情况
        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                if (sum % k == 0) {
                    return true;
                }
            }
            // 加到一起之后发现都没k大，后面的也不会再比k大了，跳过
            if (sum < k) {
                break;
            }
        }
        return false;
    }
    /**
     * 前缀和
     * 在固定i的时候移动j遍历每种情况
     * 前缀和比k小跳过，当有两个连续的0时除外
     */
    static public boolean checkSubarraySum(int[] nums, int k) {  // Time Limit Exceeded
        int n = nums.length;
        int[] prefixSum = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        for(int i = 0; i + 2 < n + 1; i++){
            for(int j = i;  j + 2 < n + 1; j++){
                if((prefixSum[j + 2] - prefixSum[i]) % k == 0){
                    return true;
                }
            }
        }
/*      for(int i = 2; i <= n; ++i){// i为右端点，j为左端点
            if(preSum[i] < k && preSum[i] != preSum[i-2]){ // 前缀和比k小跳过，当有两个连续的0时除外
                continue;
            }
            for(int j = 0; j <= i-2; ++j){ // 遍历每种左端点
                if( (preSum[i] - preSum[j]) % k == 0 ){
                    return true;
                }
            }
        } */
        return false;
    }
    /**
     * 前缀和 + HashSet
     * sum[j]−sum[i−1]=n∗k => sum[j]/k−sum[i−1]/k=n
     * 要使得两者除 k 相减为整数，需要满足 sum[j] 和 sum[i−1] 对 k 取余相同。
     * 只需要枚举右端点 j，然后在枚举右端点 j 的时候检查之前是否出现过左端点 i，使得 sum[j] 和 sum[i−1] 对 k 取余相同。
     *
     * 为什么是找两个对K取模后结果相同的前缀和呢
     * preSum[i] - preSum[j] = k * C
     * preSum[i] / k - preSum[j] / k = C
     * 因此要使得两者除 k 相减为整数，需要满足 preSum[j] 和 preSum[i−1] 对 k 取余相同。
     */
    static public boolean checkSubarraySum1(int[] nums, int k) {  // Time Limit Exceeded
        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        // 使用 HashSet 来保存出现过的值。
        // 让循环从 2 开始枚举右端点（根据题目要求，子数组长度至少为 2），每次将符合长度要求的位置的取余结果存入 HashSet。
        // 如果枚举某个右端点 j 时发现存在某个左端点 i 符合要求，则返回 True
        HashSet<Integer> set = new HashSet<>();
        for(int i = 2; i < n + 1; i++){
            set.add(prefixSum[i - 2] % k); // 存所有的左端点
            // 看是否有与右端点对K取模后结果相同的左端点
            if(set.contains(prefixSum[i] % k)){
                return true;// 找到了相同的余数就相当于找到了一个连续的前缀和是 k 的倍数
            }
        }
        return false;
    }
    /**
     * 前缀和 + HashMap
     * 同余性质:  b−a 为 k 的倍数，只需要确保 b 和 a 模 k 相同即可
     *           反过来由「b 和 a 模 k 相同」可推导出「b−a 为 k 的倍数」。
     */
    static public boolean checkSubarraySum3(int[] nums, int k) {
        HashMap<Integer,Integer> map = new HashMap<>();
        //细节2：另外一个就是之前我们都是统计个数，value 里保存的是次数，但是此时我们加了一个条件就是长度至少为 2，
        // 保存的是索引，所以我们不能继续 map.put(0,1)，应该赋初值为 map.put(0,-1)。这样才不会漏掉一些情况，
        // 例如我们的数组为[2,3,4],k = 1,当我们 map.put(0,-1) 时，当我们遍历到 nums[1] 即 3 时，则可以返回 true，
        // 因为 1-（-1）= 2，5 % 1=0 , 同时满足。
        map.put(0,-1);
        int presum = 0;
        for (int i = 0; i < nums.length; ++i) {
            presum += nums[i];
            //细节1，防止 k 为 0 的情况
            int key = k == 0 ? presum : presum % k;
            if (map.containsKey(key)) {
                if (i - map.get(key) >= 2) {
                    return true;
                }
                //因为我们需要保存最小索引，当已经存在时则不用再次存入，不然会更新索引值
                continue;
            }
            map.put(key, i);
        }
        return false;
    }
}
