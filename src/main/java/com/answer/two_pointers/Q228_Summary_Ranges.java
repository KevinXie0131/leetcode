package com.answer.two_pointers;

import java.util.*;

public class Q228_Summary_Ranges {
    /**
     * given a sorted unique integer array nums.
     * A range [a,b] is the set of all integers from a to b (inclusive).
     * Return the smallest sorted list of ranges that cover all the numbers in the array exactly. That is, each element of nums is covered by exactly one of the ranges, and there is no integer x such that x is in one of the ranges but not in nums.
     * Each range [a,b] in the list should be output as:
     *  "a->b" if a != b
     *  "a" if a == b
     * 汇总区间
     * 给定一个  无重复元素 的 有序 整数数组 nums 。
     * 返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表 。也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 nums 的数字 x 。
     * 列表中的每个区间范围 [a,b] 应该按如下格式输出：
     *  "a->b" ，如果 a != b
     *  "a" ，如果 a == b
     * All the values of nums are unique./ nums 中的所有值都 互不相同
     * nums is sorted in ascending order./ nums 按升序排列
     */
    public static void main(String[] args) {
        /**
         * 示例 1：
         *  输入：nums = [0,1,2,4,5,7]
         *  输出：["0->2","4->5","7"]
         *  解释：区间范围是：
         *  [0,2] --> "0->2"
         *  [4,5] --> "4->5"
         *  [7,7] --> "7"
         */
        int[] nums = {0,1,2,4,5,7};
        List<String> res = summaryRanges(nums);
        System.out.println(res);
    }
    /**
     * 给定一个  无重复元素 的 有序 整数数组 nums 。
     * 由于数组已经有序，只要当前数的下一个数和当前数不连续，那么就不构成连续区间。当前数为一个连续区间的终点，当前数的下一个数为新的连续区间的起点。
     * 我们只需要使用双指针：一个指针维护当前维护的连续区间的起点，一个指针去探寻终点即可。
     *
     * 每次遇到相邻元素之间的差值大于 1 时，我们就找到了一个区间。遍历完数组之后，就能得到一系列的区间的列表
     */
    public static List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList<>();
        int len = nums.length;

        for(int i = 0; i < len; i++){
            int left = i;
            while(i < len - 1 && nums[i + 1] == nums[i] + 1){
                i++;
            }
            // 维护下标 left 和 right 分别记录区间的起点和终点
            int right = i; //找到连续区间的右边界
            if(left != right){
                list.add(nums[left] + "->" + nums[right]); // 当 left<right 时，区间的字符串表示为 left→right
            }else{
                list.add(nums[right] + ""); // 当 left=right 时，区间的字符串表示为 left。
            }
        }
        return list;
    }
    /**
     * Two pointers双指针
     * 可以用双指针 i 和 j 找出每个区间的左右端点。
     * 遍历数组，当 j+1<n 且 nums[j+1]=nums[j]+1 时，指针 j 向右移动，否则区间 [i,j] 已经找到，将其加入答案，
     * 然后将指针 i 移动到 j+1 的位置，继续寻找下一个区间
     */
    public List<String> summaryRanges_1(int[] nums) {
        List<String> summary = new ArrayList<>();
        // j 向后遍历，直到不满足连续递增(即 nums[j] + 1 != nums[j + 1])
        for (int i = 0, j = 0; j < nums.length; ++j) {
            if (j + 1 < nums.length && nums[j + 1] == nums[j] + 1){ // check if j + 1 extends the range [nums[i], nums[j]]
                continue;
            }

            if (i == j){ // put the range [nums[i], nums[j]] into the list  // 将当前区间 [i, j] 写入结果列表
                summary.add(nums[i] + "");
            } else {
                summary.add(nums[i] + "->" + nums[j]);
            }
            i = j + 1; // 将 i 指向更新为 j + 1，作为下一个区间的开始位置
        }
        return summary;
    }
}
