package com.answer.two_pointers;

import java.util.HashMap;
import java.util.Map;

public class _TwoPointerTemplate {
    /**
     * 删除有序数组中的重复项
     * General solution 通用解法
     * 为了让解法更具有一般性，我们将原问题的「最多保留 1 位」修改为「最多保留 k 位」。
     * 对于此类问题，我们应该进行如下考虑：
     *     由于是保留 k 个相同数字，对于前 k 个数字，我们可以直接保留。
     *     对于后面的任意数字，能够保留的前提是：与当前写入的位置前面的第 k 个元素进行比较，不相同则保留。
     */
    public int removeDuplicates_1(int[] nums) {
        int index = 0;
        int k = 1;
        for (int i = 0; i < nums.length; i++) {
            if (index < k || nums[index - k] != nums[i]) {
                nums[index] = nums[i];
                index++;
            }
        }
        return index;
    }
    /**
     * 无重复字符的最长子串 1 / 滑动窗口
     */
    public int lengthOfLongestSubstring_2(String s) {
        char[] array = s.toCharArray(); // 转换成 char[] 加快效率（忽略带来的空间消耗）

        int left = 0;
        int res = 0;
        int[] counter = new int[128]; // 也可以用 HashMap<Character, Integer>，这里为了效率用的数组

        for (int right = 0; right < array.length; right++) {
            char rightChar = s.charAt(right);
            counter[rightChar]++;

            while (counter[rightChar] > 1) { // 不含有重复字符
                char leftChar = array[left];
                counter[leftChar]--;
                left++;
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }
    /**
     * 无重复字符的最长子串 2 / 滑动窗口
     */
    public static int lengthOfLongestSubstring(String s) {
        int max = 0;
        Map<Character, Integer> map = new HashMap<>(); // 哈希集合，记录每个字符是否出现过
        char[] arr = s.toCharArray();

        int left = 0;
        for(int right = 0; right < arr.length; right++){
            if(map.containsKey(arr[right])){
                // 更新窗口左边界, 加 1 表示从字符位置后一个才开始不重复
                left = Math.max(left, map.get(arr[right]) + 1); // 需要比较max，比如"abba"或者"dvdf"
            }
            map.put(arr[right], right);
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
    /**
     * 滑动窗口的模板
     *     N = len(nums) # 数组/字符串长度
     *     left, right = 0, 0 # 双指针，表示当前遍历的区间[left, right]，闭区间
     *     sums = 0 # 用于统计 子数组/子区间 是否有效，根据题目可能会改成求和/计数
     *     res = 0 # 保存最大的满足题目要求的 子数组/子串 长度
     *     while right < N: # 当右边的指针没有搜索到 数组/字符串 的结尾
     *         sums += nums[right] # 增加当前右边指针的数字/字符的求和/计数
     *         while 区间[left, right]不符合题意: # 此时需要一直移动左指针，直至找到一个符合题意的区间
     *             sums -= nums[left] # 移动左指针前需要从counter中减少left位置字符的求和/计数
     *             left += 1 # 真正的移动左指针，注意不能跟上面一行代码写反
     *         # 到 while 结束时，我们找到了一个符合题意要求的 子数组/子串
     *         res = max(res, right - left + 1) # 需要更新结果
     *         right += 1 # 移动右指针，去探索新的区间
     *     return res
     */
    /**
     * 最多可以将 1 个 0 翻转为 1，找出其中最大连续 1 的个数 / 滑动窗口1
     */
    public static int findMaxConsecutiveOnes(int[] nums) {
        int max = 0, count = 0, left = 0, right = 0;
        while(right < nums.length){
            if(nums[right] == 0){
                count++;
                while(count > 1){
                    if(nums[left] == 0){
                        count--;
                    }
                    left++;
                }
            }
            max = Math.max(max, right - left + 1);
            right++;
        }
        return max;
    }
    /**
     * 最多可以将 1 个 0 翻转为 1，找出其中最大连续 1 的个数 / 滑动窗口2
     */
    public int findMaxConsecutiveOnes_1(int[] nums) {
        int left = 0, maxLen = 0, count = 1;
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                count--;
            }
            while (count < 0) {
                if (nums[left] == 0) {
                    count++;
                }
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
    /**
     * 计算前缀和数组
     */
    static public int prefixSum(int[] nums, int target) {
        int len = nums.length;
        int[] prefixSum = new int[len + 1];
        for (int i = 0; i < len; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];//计算前缀和数组
        }

        for(int i = 1; i < len + 1; i++){
            if(prefixSum[i] - prefixSum[i - 1] == target){
                return i - 1;
            }
        }
        return  -1;
    }
}
