package com.answer.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class Q1365_How_Many_Numbers_Are_Smaller_Than_the_Current_Number {
    /**
     * 统计数组中比它小的所有数字的数目。
     * Constraints:
     *  2 <= nums.length <= 500
     *  0 <= nums[i] <= 100
     */
    public static void main(String[] args) {
      int []nums = {8,1,2,2,3};
      int[] result = smallerNumbersThanCurrent(nums);
      System.out.println(Arrays.toString(result));
    }
    /**
     * 暴力: 对于数组中的每一个元素，我们都遍历数组一次，统计小于当前元素的数的数目。
     */
    public int[] smallerNumbersThanCurrent_0(int[] nums) {
        int n = nums.length;
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (nums[j] < nums[i]) {
                    cnt++;
                }
            }
            ret[i] = cnt;
        }
        return ret;
    }
    /**
     * 排序
     * 将数组排序，并记录每一个数在原数组中的位置。对于排序后的数组中的每一个数，
     * 我们找出其左侧第一个小于它的数，这样就能够知道数组中小于该数的数量。
     */
    static public int[] smallerNumbersThanCurrent_1(int[] nums) {
        int n = nums.length;
        int[][] data = new int[n][2];
        for (int i = 0; i < n; i++) {
            data[i][0] = nums[i];
            data[i][1] = i;
        }
/*      Arrays.sort(data, new Comparator<int[]>() {
            public int compare(int[] data1, int[] data2) {
                return data1[0] - data2[0];
            }
        });  */

        int[] ret = new int[n];
        int prev = -1;
        for (int i = 0; i < n; i++) {
            if (prev == -1 || data[i][0] != data[i - 1][0]) {
                prev = i;
            }
            ret[data[i][1]] = prev;
        }
        return ret;
    }
    /**
     * Another form
     */
    static public int[] smallerNumbersThanCurrent_2(int[] nums) {
        int n = nums.length;
        int[][] data = new int[n][2];
        for (int i = 0; i < n; i++) {
            data[i][0] = nums[i];
            data[i][1] = i;
        }
        Arrays.sort(data, (data1, data2) -> data1[0] - data2[0]);

        int[] ret = new int[n];
        ret[0] = data[0][1];

        int prev = 0;
        for (int i = 1; i < n; i++) {
            if (data[i - 1][0] < data[i][0]) {
                prev = i;
            }
            ret[data[i][1]] = prev;
        }
        return ret;
    }
    /**
     * 计数排序
     * 注意到数组元素的值域为 [0,100]，所以可以考虑建立一个频次数组 cnt ，cnt[i] 表示数字 i 出现的次数。
     * 那么对于数字 i 而言，小于它的数目就为 cnt[0...i−1] 的总和。
     */
    static public int[] smallerNumbersThanCurrent_3(int[] nums) {
        int[] cnt = new int[101];
        int n = nums.length;
        for (int i = 0; i < n; i++) { // 统计每个数字出现的频次
            cnt[nums[i]]++;
        }
        for (int i = 1; i <= 100; i++) { // 累加计数数组，count[i] 表示小于等于 i 的数字的数量
            cnt[i] += cnt[i - 1];
        }
        int[] ret = new int[n];// 生成结果数组
        for (int i = 0; i < n; i++) {
            ret[i] = nums[i] == 0 ? 0 : cnt[nums[i] - 1]; // 0 前面没有比它小的数字; 获取小于 nums[i] 的数量
        }
        return ret;
        // 好像 res 数组也没必要单独申请，直接在原数组修改也不是不行的
/*        for (int i = 0; i < n; i++) {
            if(nums[i] > 0) nums[i] = cnt[nums[i] - 1]; // 0 前面没有比它小的数字; 获取小于 nums[i] 的数量
        }
        return nums;*/
    }
    /**
     * 可以排序之后加哈希，时间复杂度为O(n\log n)
     */
    static public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] result = new int[nums.length];
        int[] rank = new int[101];
        int[] copyNums = Arrays.copyOf(nums, nums.length);
        Arrays.sort(copyNums); // 从小到大排序之后, 其实每一个数值的下标就代表这前面有几个比它小的了。元素下标就是小于当前数字的数字

        for(int i = copyNums.length - 1; i >= 0; i--){ // 从后向前，记录 copyNums[i] 对应的下标
            rank[copyNums[i]] = i; // 在构造数组hash的时候，从后向前遍历，这样hash里存放的就是相同元素最左面的数值和下标了。
        }
        // 此时hash里保存的每一个元素数值 对应的 小于这个数值的个数
        for(int i = 0; i < result.length; i++){
            result[i] = rank[nums[i]];
        }
        return result;
    }
    /**
     * 排序 + HashMap
     * 你的索引是多少，就有多少个数字小于你, 严格说应该是 小于等于你
     */
    public int[] smallerNumbersThanCurrent2(int[] nums) {
        int[] result = new int[nums.length];
        HashMap<Integer, Integer> map = new HashMap<>();  // 记录数字 nums[i] 有多少个比它小的数字
        int[] copyNums = Arrays.copyOf(nums, nums.length);
        Arrays.sort(copyNums);
        // 需要一个技巧了，在构造数组hash的时候，从后向前遍历，这样hash里存放的就是相同元素最左面的数值和下标了: [1, 2, 2, 3, 8]
        for(int i = copyNums.length - 1; i >= 0; i--){  // 记录该数字的排名
            map.put(copyNums[i], i); // 这个时候的下标，同时代表了前面有多少个数字
        }
/*
        for (int i = 0; i < copyNums.length; i++) {
            if (!map.containsKey(copyNums[i])) { // 遇到了相同的数字，那么不需要更新该 number 的情况
                map.put(copyNums[i], i);
            }
        }
*/
        for(int i = 0; i < result.length; i++){
            result[i] = map.get(nums[i]); // 获取每个数字小于它的数量
        }
        return result;
    }
}
