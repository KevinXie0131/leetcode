package com.answer.array;

public class Q209_Minimum_Size_Subarray_Sum {
    /**
     * 方法一：暴力法 Time Limit Exceeded 时间复杂度：O(n2) 效率很差
     */
    public int minSubArrayLen(int target, int[] nums) {
        int result = Integer.MAX_VALUE;

       for (int left = 0; left <  nums.length; left++) {
           int sum = 0;
            for (int right = left; right < nums.length; right++) {
                sum += nums[right];
                if (sum >= target) {
                    result = Math.min(result, right - left + 1);
                    break;
                }
            }
        }
        return result == Integer.MAX_VALUE? 0 : result;
    }
    /**
     * 滑动窗口 时间复杂度：O(n)
     * 每一轮迭代，将 nums[end] 加到 sum，如果 sum≥s，则更新子数组的最小长度（此时子数组的长度是 end−start+1），
     * 然后将 nums[start] 从 sum 中减去并将 start 右移，直到 sum<s，在此过程中同样更新子数组的最小长度。
     * 在每一轮迭代的最后，将 end 右移。
     */
    public int minSubArrayLen1(int target, int[] nums) {
        int left = 0;
        int sum = 0;
        int result = Integer.MAX_VALUE;

        for(int right = 0; right < nums.length; right++){
            sum += nums[right];
            while(sum >= target){
                result = Math.min(result, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }
        return result == Integer.MAX_VALUE? 0 : result;
    }
    /**
     * 使用队列相加（实际上我们也可以把它称作是滑动窗口，这里的队列其实就相当于一个窗口）
     * 在代码中我们不直接使用队列，我们使用两个指针，一个指向队头一个指向队尾
     */
    public int minSubArrayLen3(int target, int[] nums) {  // 同上
        int lo = 0, hi = 0, sum = 0, min = Integer.MAX_VALUE;
        while (hi < nums.length) {
            sum += nums[hi];
            hi++;
            while (sum >= target) {
                min = Math.min(min, hi - lo);
                sum -= nums[lo];
                lo++;
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
