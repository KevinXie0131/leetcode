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
     *
     */
    public static List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList<>();
        int len = nums.length;

        for(int i = 0; i < len; i++){
            int l = i;
            while(i < len - 1 && nums[i + 1] == nums[i] + 1){
                i++;
            }
            int r = i;
            if(l != r){
                list.add(nums[l] + "->" + nums[r]);
            }else{
                list.add(nums[r] + "");
            }
        }
        return list;
    }
    /**
     * Two pointers
     */
    public List<String> summaryRanges_1(int[] nums) {
        List<String> summary = new ArrayList<>();
        for (int i = 0, j = 0; j < nums.length; ++j) {
            // check if j + 1 extends the range [nums[i], nums[j]]
            if (j + 1 < nums.length && nums[j + 1] == nums[j] + 1)
                continue;
            // put the range [nums[i], nums[j]] into the list
            if (i == j)
                summary.add(nums[i] + "");
            else
                summary.add(nums[i] + "->" + nums[j]);
            i = j + 1;
        }
        return summary;
    }
}
