package com.answer.sliding_window;

public class Q1004_Max_Consecutive_Ones_III {
    /**
     * Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can
     * flip at most k 0's.
     * 最大连续1的个数 III: 给定一个二进制数组 nums 和一个整数 k，假设最多可以翻转 k 个 0 ，则返回执行操作后 数组中连续 1 的最大个数 。
     */
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int k = 2;
        /**
         * 输出：6
         * 解释：[1,1,1,0,0,1,1,1,1,1,1] index=5/10 从 0 翻转到 1，最长的子数组长度为 6。
         */
        System.out.println(longestOnes(nums, k));
    }
    /**
     * Similar with Q487 Max Consecutive Ones II
     * Approach: Sliding Window 滑动窗口
     * 代码思路：
     *  使用 left 和 right 两个指针，分别指向滑动窗口的左右边界。
     *  right 主动右移：right 指针每次移动一步。当 A[right] 为 0，说明滑动窗口内增加了一个 0；
     *  left 被动右移：判断此时窗口内 0 的个数，如果超过了 K，则 left 指针被迫右移，直至窗口内的 0 的个数小于等于 K 为止。
     *  滑动窗口长度的最大值就是所求。
     */
    public static int longestOnes(int[] nums, int k) {
        int max = 0, count = 0;
        int right = 0, left = 0;
        int len = nums.length;

        while(right < len){
            if(nums[right] == 0){
                count++;

                while(count > k){ //如果窗口中0的个数超过了K，要缩小窗口的大小，直到0的个数不大于K位置
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
     *
     * 以右指针作为驱动，拖着左指针向前走。右指针每次只移动一步，而左指针在内部 while 循环中每次可能移动多步。
     * 右指针是主动前移，探索未知的新区域；左指针是被迫移动，负责寻找满足题意的区间。
     */
    /**
     * 二分查找 + 前缀和
     * 最大替换次数不超过 k 次，可以将问题转换为找出连续一段区间 [l,r]，使得区间中出现 0 的次数不超过 k 次。
     * 我们可以枚举区间 左端点/右端点 ，然后找到其满足「出现 0 的次数不超过 k 次」的最远右端点/最远左端点。
     * 为了快速判断 [l,r] 之间出现 0 的个数，我们需要用到前缀和。
     * 假设 [l,r] 的区间长度为 len，区间和为 tot，那么出现 0 的格式为 len - tol，再与 k 进行比较。
     * 由于数组中不会出现负权值，因此前缀和数组具有「单调性」，那么必然满足「其中一段满足 len−tol<=k，另外一段不满足 len−tol<=k」。
     * 因此，对于某个确定的「左端点/右端点」而言，以「其最远右端点/最远左端点」为分割点的前缀和数轴，具有「二段性」。可以通过二分来找分割点。
     */
    public int longestOnes1(int[] nums, int k) {
        int n = nums.length;
        int[] sum  = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            sum [i] = sum [i - 1] + (1 - nums[i - 1]);
        }

        int ans = 0;
        for (int right = 0; right < n; right++) {
            int left = binarySearch(sum , sum [right + 1] - k);
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    public int binarySearch(int[] sum , int target) {
        int low = 0, high = sum.length - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (sum [mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
