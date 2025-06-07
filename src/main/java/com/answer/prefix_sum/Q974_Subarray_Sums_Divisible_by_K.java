package com.answer.prefix_sum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Q974_Subarray_Sums_Divisible_by_K {
    /**
     * Given an integer array nums and an integer k, return the number of non-empty subarrays that have a sum divisible by k.
     * A subarray is a contiguous part of an array.
     * 和可被 K 整除的子数组
     * 给定一个整数数组 nums 和一个整数 k ，返回其中元素之和可被 k 整除的非空 子数组 的数目。
     * 子数组 是数组中 连续 的部分。
     */
    public static void main(String[] args) {
        /**
         * 输出：7
         * 解释：有 7 个子数组满足其元素之和可被 k = 5 整除：[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
         */
     //    int[] nums = {4,5,0,-2,-3,1};
    //     int k = 5;
   //     int[] nums = {5};
    //    int k = 9;
       int[] nums = {-6,1,-5,10};
        int k = 9;
        System.out.println(subarraysDivByK2(nums, k));
    }
    /**
     * 前缀和
     * 与题目「560. 和为K的子数组」非常相似
     * 相似问题 523.连续的子数组和
     */
    static public int subarraysDivByK(int[] nums, int k) { // Time Limit Exceeded
        int[] preSum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = nums[i] + preSum[i];
        }
        int count = 0;

        for(int i = 1; i < nums.length + 1; i++){
            for(int j = i - 1; j >= 0; j--){
                if((preSum[i] - preSum[j]) % k == 0){
                    count++;
                }
            }
        }
        return count;
    }
    /**
     * 判断子数组的和能否被 k 整除就等价于判断 (P[j]−P[i−1])modk==0，根据 同余定理，只要 P[j]modk==P[i−1]modk，就可以保证上面的等式成立。
     *
     * 要判断的是 (sum[j]−sum[i])%K是否等于 0。
     * 根据 mod 运算的性质，我们知道 (sum[j]−sum[i])%K=sum[j]%K−sum[i]%K。
     * 故若想 (sum[j]−sum[i])%K=0 ，则必有 sum[j]%K=sum[i]%K。
     *
     * 生成前缀和数组的过程中，将 key=sum[j]%k 出现的频率加入结果数组， 同时将 key=sum[j]%k 的出现频率加 1。
     * 由于数组中有可能出现负数，我们需要将其加 K 从而使其 %K 之后的值为正数。
     */
    static public int subarraysDivByK1(int[] nums, int k) { // Time Limit Exceeded
        int[] preSum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = nums[i] + preSum[i];
        }
        int count = 0;

        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length + 1; i++){
            // 这里要格外注意java负数取模的特性:-5%2=-1
            // 负数对正数k取模就是一直+k直到最后一个<=0的数,-5=>-3=>-1
            // 因此针对负数的情况,可以先-5%2=-1,然后再+2=1
            // 此时再对结果取k的模就等于想要的[0,k-1]之间的数了
            int key = (preSum[i] % k + k) % k;
            if (map.containsKey(key)) {
                count += map.get(key);
            }
            map.put(key, map.getOrDefault(key,0) + 1);
        }

        return count;
    }
    /**
     * another form
     */
    static public int subarraysDivByK2(int[] nums, int k) { // Time Limit Exceeded
        int[] preSum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = nums[i] + preSum[i];
        }
        int count = 0;

        int[] hash = new int[k];
        for(int i = 0; i < nums.length + 1; i++){
            int key = (preSum[i] % k + k) % k; //当前 presum 与 K的关系，余数是几，当被除数为负数时取模结果为负数，需要纠正
            if (hash[key] != 0) {   //查询哈希表获取之前key也就是余数的次数
                count += hash[key];
            }
            hash[key] = hash[key] + 1;    //存入哈希表当前key，也就是余数
        }

        return count;
    }
}
