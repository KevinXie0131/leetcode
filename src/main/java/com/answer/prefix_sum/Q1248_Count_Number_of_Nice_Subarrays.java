package com.answer.prefix_sum;

import java.util.HashMap;

public class Q1248_Count_Number_of_Nice_Subarrays {
    /**
     * Given an array of integers nums and an integer k. A continuous subarray is called nice if there are k odd numbers on it.
     * Return the number of nice sub-arrays.
     * 统计「优美子数组」
     * 给你一个整数数组 nums 和一个整数 k。如果某个连续子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。
     * 请返回这个数组中 「优美子数组」 的数目。
     *
     */
    public static void main(String[] args) {
        int[] nums = {1,1,2,1,1};
        int k = 3;
        /**
         * 输出：2
         * 解释：包含 3 个奇数的子数组是 [1,1,2,1] 和 [1,2,1,1] 。
         */
        System.out.println(numberOfSubarrays2(nums, k));
    }
    /**
     * 前缀和
     * 用pre[i]表示前i个元素中奇数的个数，那么从 j 到 i 这个子数组的奇数个数可以表示为pre[i]-pre[j-1]，因此恰好有k个奇数可以表示为：
     * pre[i]-pre[j-1]==k 这样，实际上就完全转化为了第560题
     */
    static public int numberOfSubarrays(int[] nums, int k) { // Time Limit Exceeded
        int len = nums.length;
        int[] prefixSum = new int[len + 1];
        for (int i = 0; i < len; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i] % 2 ; // prefixSum表示i个数内有多少个奇数
        }

        int count = 0;
        for(int i = 0; i < len + 1; i++){
            for(int j = 1; j < len + 1; j++){
                if(prefixSum[j] - prefixSum[i] == k){
                    count++;
                }
            }
        }
        return count;
    }
    /**
     * 前缀和 + HashMap
     * 挑选出所有区间[i,j], prefixSum[j]−prefixSum[i−1]=k，这其实就是经典的两数之和问题
     */
    public int numberOfSubarrays1(int[] nums, int k) {
        int len = nums.length;
        int[] prefixSum = new int[len + 1];
        for (int i = 0; i < len; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i] % 2 ;
        }

        int count = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < len + 1; i++){
            if(map.containsKey(prefixSum[i] - k)){
                count += map.get(prefixSum[i] - k);
            }
            map.put(prefixSum[i], map.getOrDefault(prefixSum[i], 0) + 1);
        }
        return count;
    }
    /**
     * 优化代码
     */
    static public int numberOfSubarrays2(int[] nums, int k) {
        int len = nums.length;
        int[] prefixSum = new int[len + 1];
        for (int i = 0; i < len; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i] % 2 ;
        }

        int count = 0;
        int[] hash = new int[len + 1];

        for(int i = 0; i < len + 1; i++){
            if(prefixSum[i] >= k){
                count += hash[prefixSum[i] - k];
            }
            hash[prefixSum[i]] = hash[prefixSum[i]] + 1;
        }
        return count;
    }
    /**
     * 只需将前缀区间的奇数个数保存到区间内即可，只不过将 sum += x 改成了判断奇偶的语句
     */
    public int numberOfSubarrays3(int[] nums, int k) {
        if (nums.length == 0) {
            return 0;
        }
        HashMap<Integer,Integer> map = new HashMap<>();
        //统计奇数个数，相当于我们的 presum
        int oddnum = 0;
        int count = 0;
        map.put(0,1);
        for (int x : nums) {
            // 统计奇数个数
            oddnum += x & 1;
            // 发现存在，则 count增加
            if (map.containsKey(oddnum - k)) {
                count += map.get(oddnum - k);
            }
            //存入
            map.put(oddnum,map.getOrDefault(oddnum,0)+1);
        }
        return count;
    }
    /**
     * 统计奇数的个数，数组中的奇数个数肯定不会超过原数组的长度，所以这个题目中我们可以用数组来模拟 HashMap ，
     * 用数组的索引来模拟 HashMap 的 key，用值来模拟哈希表的 value
     */
    public int numberOfSubarrays5(int[] nums, int k) {
        int len = nums.length;
        int[] map = new int[len + 1];
        map[0] = 1;
        int oddnum = 0;
        int count = 0;
        for (int i = 0; i < len; ++i) {
            //如果是奇数则加一，偶数加0，相当于没加
            oddnum += nums[i] & 1;
            if (oddnum - k >= 0) {
                count += map[oddnum-k];
            }
            map[oddnum]++;
        }
        return count;
    }
}
