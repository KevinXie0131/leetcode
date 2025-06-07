package com.answer.prefix_sum;

import java.util.HashMap;

public class Q560_Subarray_Sum_Equals_K {
    /**
     * Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
     * A subarray is a contiguous non-empty sequence of elements within an array.
     * 和为 K 的子数组
     * 一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
     * 子数组是数组中元素的连续非空序列。
     */
    public static void main(String[] args) {
       int[] nums = {1,2,3}; int k = 3; // 输出：2
        System.out.println(subarraySum4(nums, k));
    }
    /**
     * 枚举
     * 考虑以 i 结尾和为 k 的连续子数组个数，我们需要统计符合条件的下标 j 的个数，其中 0≤j≤i 且 [j..i] 这个子数组的和恰好为 k 。
     */
    public int subarraySum3(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; ++start) {
            int sum = 0;
            for (int end = start; end >= 0; --end) {
                sum += nums[end];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }
    /**
     * 题目要求连续子数组的和等于定值k，一般来说，看到连续子数组元素相关的，基本就是滑动窗口或者前缀和（笨人题刷得不多，只能总结出这两个），
     * 而滑动窗口的使用需要满足前置条件：窗口内的元素必须要有“单调性”，也就是说新元素进入窗口必须使得内部和不能减少，必须使得窗口向着不满足约束的方向前进。
     * 由于数组存在负数，因此新入元素反而可能使得窗口更加不超过k。因此排除滑动窗口，想到使用前缀和。
     * 前缀和的优势就在于能够将子数组求和的时间优化到𝑂(1)
     *
     * 初始化一个哈希表来保存左边已经被遍历的元素。这里与两数之和不同的地方在于：两数之和需要返回下标，因此值保存的是左边元素的下标，
     * 而这个题需要求出index数量和，因此值需要保存左边各个元素的数量（若有相同元素，对应值加1）。在每次遍历一个sum时，先进行答案相关的操作，
     * 然后再添加进哈希表，表示该元素已被遍历，可以在后序遍历的判断中使用。
     */
    static public int subarraySum(int[] nums, int k) {
        int len = nums.length;
        int[] prefixSum = new int[len + 1];
        for (int i = 0; i < len; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];//计算前缀和数组
        }
        // 两次遍历
        int result = 0;
        HashMap<Integer, Integer> map = new HashMap<>();  //转化为两数之和
        for (int sum : prefixSum) {
            int index = sum - k;
            if (map.containsKey(index)) { //已遍历元素中存在index
                result += map.get(index);  //加上相应的个数
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1); //当前遍历结束，更新sum出现个数，便于后续判断使用
        }
        return result;
    }
    /**
     * 前缀和
     * 对比暴力法并没有提升性能，时间复杂度仍为O(n^2)，空间复杂度成了 O(n)
     */
     public int subarraySum1(int[] nums, int k) {
        //前缀和数组
        int[] presum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            presum[i + 1] = nums[i] + presum[i];    //这里需要注意，我们的前缀和是presum[1]开始填充的
        }
        //统计个数
        int count = 0;
        for (int i = 0; i < nums.length; ++i) {
            for (int j = i; j < nums.length; ++j) {
                //注意偏移，因为我们的nums[2]到nums[4]等于presum[5]-presum[2]
                //所以这样就可以得到nums[i,j]区间内的和
                if (presum[j + 1] - presum[i] == k) {
                    count++;
                }
            }
        }
        return count;
    }
    /**
     * 前缀和 + HashMap 一次遍历
     */
    public int subarraySum2(int[] nums, int k) {
        if (nums.length == 0) {
            return 0;
        }
        HashMap<Integer,Integer> map = new HashMap<>();
        //细节，这里需要预存前缀和为 0 的情况，会漏掉前几位就满足的情况
        //例如输入[1,1,0]，k = 2 如果没有这行代码，则会返回0,漏掉了1+1=2，和1+1+0=2的情况
        //输入：[3,1,1,0] k = 2时则不会漏掉
        //因为presum[3] - presum[0]表示前面 3 位的和，所以需要map.put(0,1),垫下底
        map.put(0, 1); //要预先将 preSum[0] = 0 这个前缀和加入哈希表，即前缀和 0 出现了 1次。
        int count = 0;
        int presum = 0;
        for (int x : nums) {
            presum += x;
            //当前前缀和已知，判断是否含有 presum - k的前缀和，那么我们就知道某一区间的和为 k 了。
            if (map.containsKey(presum - k)) {
                count += map.get(presum - k);//获取次数
            }
            //更新
            map.put(presum,map.getOrDefault(presum,0) + 1);
        }
        return count;
    }
    /**
     * 写法三：一次遍历 · 其二
     * 在同一轮循环中，先把 s[i−1] 加入哈希表，再根据 s[i] 更新答案。
     * 这样写无需初始化 map[0]=1。
     */
    static public int subarraySum4(int[] nums, int k) {
        if (nums.length == 0) {
            return 0;
        }
        HashMap<Integer,Integer> map = new HashMap<>();
        int count = 0;
        int presum = 0;
        for (int x : nums) { // 改一下计算顺序就可以不用写map[0]=1
            map.put(presum, map.getOrDefault(presum, 0) + 1);  // 先存入前一个位置的前缀和，恰好也预先计入了 freq[0] = 1
            presum += x;

            count += map.getOrDefault(presum - k,0) ;
        }
        return count;
    }
}
