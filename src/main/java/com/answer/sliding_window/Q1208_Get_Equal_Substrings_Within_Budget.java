package com.answer.sliding_window;

public class Q1208_Get_Equal_Substrings_Within_Budget {
    /**
     * You are given two strings s and t of the same length and an integer maxCost.
     * You want to change s to t. Changing the ith character of s to ith character of t costs |s[i] - t[i]|
     * (i.e., the absolute difference between the ASCII values of the characters).
     * Return the maximum length of a substring of s that can be changed to be the same as the corresponding substring of t
     * with a cost less than or equal to maxCost. If there is no substring from s that can be changed to its corresponding
     * substring from t, return 0.
     * 尽可能使字符串相等: 两个长度相同的字符串，s 和 t。
     * 将 s 中的第 i 个字符变到 t 中的第 i 个字符需要 |s[i] - t[i]| 的开销（开销可能为 0），也就是两个字符的 ASCII 码值的差的绝对值。
     * 用于变更字符串的最大预算是 maxCost。在转化字符串时，总开销应当小于等于该预算，这也意味着字符串的转化可能是不完全的。
     * 如果你可以将 s 的子字符串转化为它在 t 中对应的子字符串，则返回可以转化的最大长度。
     * 如果 s 中没有子字符串可以转化成 t 中对应的子字符串，则返回 0。
     *
     * t.length == s.length
     * s and t consist of only lowercase English letters.
     *
     * 示例 1：
     *  输入：s = "abcd", t = "bcdf", maxCost = 3
     *  输出：3
     *  解释：s 中的 "abc" 可以变为 "bcd"。开销为 3，所以最大长度为 3。
     * 示例 2：
     *  输入：s = "abcd", t = "cdef", maxCost = 3
     *  输出：1
     *  解释：s 中的任一字符要想变成 t 中对应的字符，其开销都是 2。因此，最大长度为 1。
     * 示例 3：
     *  输入：s = "abcd", t = "acde", maxCost = 0
     *  输出：1
     *  解释：a -> a, cost = 0，字符串未发生变化，所以最大长度为 1。
     */
    public static void main(String[] args) {
        String s = "abcd";
        String t = "bcdf";
        int maxCost = 3;
        System.out.println(equalSubstring(s, t, maxCost));
    }
    /**
     * 滑动窗口
     * 生成一个数组 cost = abs(ord(t[i]) - ord(s[i]))
     * 问题就转化为：在一个数组中，在连续子数组的和小于等于 maxCost 的情况下，找到最长的连续子数组长度。
     */
    public int equalSubstring3(String s, String t, int maxCost) {
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        int len = sArr.length;
        int[] cost = new int[len];

        for(int i = 0; i < len; i++){
            cost[i] = Math.abs(sArr[i] - tArr[i]);
        }

        int maxLen = 0, sum = 0, left = 0;
        for (int right = 0; right < len; right++) {
            sum += cost[right];

            while (sum > maxCost) {
                sum -= cost[left];
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
    /**
     * Similar with Q487 Max Consecutive Ones II
     * Sliding window 滑动窗口
     * 「滑动窗口」是固定大小的，「双指针」是不固定大小的；
     * 「滑动窗口」一定是同向移动的，「双指针」可以相向移动。
     */
    public static int equalSubstring(String s, String t, int maxCost) {
        int max = 0;
        int left = 0, right = 0;

        while(right < s.length()){
            int diff = Math.abs(s.charAt(right) - t.charAt(right));
            maxCost -= diff; // 当前字符的转换开销

            if(maxCost < 0){ // 如果超预算，就收缩窗口
                int diff1 = Math.abs(s.charAt(left) - t.charAt(left));
                maxCost += diff1;
                left++;
            }

            max = Math.max(max, right - left + 1); // 窗口合理，更新最大窗口长度
            right++;
        }
        return max;
    }
    /**
     * Official answer
     */
    public int equalSubstring_1(String s, String t, int maxCost) {
        int n = s.length();
        int[] diff = new int[n];
        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(s.charAt(i) - t.charAt(i));
        }
        int maxLength = 0;
        int start = 0, end = 0;
        int sum = 0;
        while (end < n) {
            sum += diff[end];
            while (sum > maxCost) {
                sum -= diff[start];
                start++;
            }
            maxLength = Math.max(maxLength, end - start + 1);
            end++;
        }
        return maxLength;
    }
    /**
     * 前缀和 + 二分查找
     * 由于 diff 的的每个元素都是非负的，因此 presum 是递增的，对于每个下标 j，可以通过在 presum 内
     * 进行二分查找的方法找到符合要求的最小的下标 k。
     */
    public int equalSubstring4(String s, String t, int maxCost) {
        int n = s.length();
        int[] presum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            presum[i + 1] = presum[i] + Math.abs(s.charAt(i) - t.charAt(i));
        }
        /**
         * 对于下标范围 [0,n-1] 内的每个 i，通过二分查找的方式，在下标范围 [0,end] 内找到最小的下标 start，
         * 使得 presum[start]≥presum[end]−maxCost，此时对应的 diff 的子数组的下标范围是从 start 到 end，
         * 子数组的长度是 end−start + 1。
         */
        int maxLength = 0;
        for (int end = 0; end < n; end++) {
            int start = binarySearch(presum, end, presum[end + 1] - maxCost);
            maxLength = Math.max(maxLength, end - start + 1);
        }
        return maxLength;
    }

    public int binarySearch(int[] presum, int end, int target) {
        int low = 0, high = end;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            if (presum[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1; // 找到最小的下标 start
            }
        }
        return low;
    }
    /**
     * 前缀和 + 二分法
     * 构造 前缀和 数组 prefixSum
     * prefixSum[i] 代表 s[0..i-1] 转换到 t[0..i-1] 的总开销。也就是：
     * prefixSum[i] = prefixSum[i-1] + abs(s[i-1] - t[i-1])
     *
     * 有了前缀和，我们可以利用前缀和的减法，快速得到一个区间值。这样就可以使用 二分查找法
     * 确定区间值是否符合题意：每个 r，找到 最左的 l，使得 prefixSum[r+1] - prefixSum[l] <= maxCost
     */
    public int equalSubstring5(String s, String t, int maxCost) {
        int n = s.length(), res = 0;
        int[] prefixSum = new int[n + 1]; // 前缀和数组
        for (int i = 0; i < n; ++i) { //计算前缀和
            prefixSum[i + 1] = prefixSum[i] + Math.abs(s.charAt(i) - t.charAt(i));
        }
        for (int r = 0; r < n; r++) { // 遍历前缀和数组
            // 二分查找 l，满足 prefixSum[r+1] - prefixSum[l] <= maxCost
            int left = 0, right = r + 1;  // right = r+1 因为 prefixSum 有 n+1 长度

            while (left <= right) { // 二分查找
                int mid = left + (right - left) / 2; // 二分
                if (prefixSum[r + 1] - prefixSum[mid] > maxCost) { // 对比
                    left = mid + 1;  // 右移
                } else {
                    right = mid -1;  // 左移
                }
            }
            res = Math.max(res, r - left + 1); // 更新窗口大小
        }
        return res;
    }
}
