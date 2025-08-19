package com.answer.hashmap;

import java.util.*;

public class Q128_Longest_Consecutive_Sequence {
    /**
     * 最长连续序列
     * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
     * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
     * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
     * 示例：
     * 输入：nums = [100,4,200,1,3,2]
     * 输出：4
     * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
     */
    /**
     * 哈希集合法
     *  使用Set去重并提供O(1)查找。
     *  遍历数组，每遇到元素x，如果x-1不在Set中，说明x是连续序列的起点。
     *  依次判断x, x+1, x+2…是否在Set中，统计连续序列的长度。
     *  取所有起点得到的最大长度作为答案。
     */
    public int longestConsecutive(int[] nums) {
        int max = 0;
        HashSet<Integer> set = new HashSet<>();
        for(int num : nums){ // 把 nums 转成哈希集合
            set.add(num);
        }
        for(int num : set){ // 遍历哈希集合
            // 如果当前的数是一个连续序列的起点，统计这个连续序列的长度
            if(!set.contains(num - 1)){  // 只有在是连续序列起点时才开始计数
                int curNum = num;
                int curLen = 1;
                while(set.contains(curNum + 1)){ // 不断查找连续序列，直到num的下一个数不存在于数组中
                    curNum = curNum + 1;
                    curLen++;
                }
                max = Math.max(max, curLen); // 更新最长连续序列长度
            }
        }
        return max;
    }
}
