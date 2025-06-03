package com.answer.sliding_window;

public class Q487_Max_Consecutive_Ones_II {
    /**
     * Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0.
     * Example 1:
     *  Input: [1,0,1,1,0]
     *  Output: 4
     *  Explanation: Flip the first zero will get the the maximum number of consecutive 1s.
     *                  After flipping, the maximum number of consecutive 1s is 4.
     * 给定一个二进制数组，你可以最多将 1 个 0 翻转为 1，找出其中最大连续 1 的个数。
     * Note:  The input array will only contain 0 and 1.
     *        The length of input array is a positive integer and will not exceed 10,000
     * Follow up: What if the input numbers come in one by one as an infinite stream? In other words,
     *      you can't store all numbers coming from the stream as it's too large to hold in memory. Could you solve it efficiently?
     * 注：输入数组只包含 0 和 1.
     *     输入数组的长度为正整数，且不超过 10,000
     * 进阶：如果输入的数字是作为 无限流 逐个输入如何处理？换句话说，内存不能存储下所有从流中输入的数字。您可以有效地解决吗？
     */
    public static void main(String[] args) {
        int[] nums = {1,0,1,1,0,1};
        System.out.println(findMaxConsecutiveOnes6(nums));
    }
    /**
     * Approach 2: Sliding Window 滑动窗口
     */
    public static int findMaxConsecutiveOnes(int[] nums) {
        int max = 0, count = 0;
        int left = 0, right = 0;
        // while our window is in bounds
        while(right < nums.length){
            if(nums[right] == 0){
                // add the right most element into our window 遇到0，翻转0的数量增加
                count++;
                while(count > 1){ // if our window is invalid, contract our window 如果翻转的0的数量超过1，需要收缩左边界，直到count回到1或0
                    /**
                     * count -= nums[l++] == 0 ? 1 : 0;
                     */
                    if(nums[left] == 0){ // 如果左边界是0，减少翻转的0的数量
                        count--;
                    }
                    left++;
                }
            }
            max = Math.max(max, right - left + 1);  // update our longest sequence answer 更新最大连续1的个数
            right++;  // expand our window
        }
        return max;
    }
    /**
     * Another form of slding window
     */
    public int findMaxConsecutiveOnes_1(int[] nums) {
        int left = 0;
        int maxLen = 0;
        int count = 1;
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
     * another form
     */
    static public int findMaxConsecutiveOnes3(int[] nums) {
        int ans = 0;
        int left = 0, right = 0;
        int windowZeroCnt = 0;

        while (right < nums.length) {
            if (nums[right] == 0) {
                windowZeroCnt++;
                if (windowZeroCnt == 2) {//windowZeroCnt不能超过1，等于2就开始移动left,记录当前窗口长度
                    ans = Math.max(ans, right - left);
                }
            }
            while (windowZeroCnt == 2) {//如果窗口里一直有2个0，left一直右移，窗内保留1个0
                if (nums[left] == 0) {
                    windowZeroCnt--;//left遇到第一个0时，窗内0计数器减1
                }
                left++;
            }
            right++;
        }
        return Math.max(ans, right - left);//如果最后一个值不是0，不会出发if语句内的程序，所以最后手动判断一下最后的长度
    }
    /**
     * 如果输入的数字是作为 无限流 逐个输入如何处理？换句话说，内存不能存储下所有从流中输入的数字
     * 之前的算法需要将 left 到 right 之间所有的元素存储在内存当中，因为需要挨个检查nums[left]是否等于0
     * 如果left 和 right 之间的距离非常地长，内存存储不了，上面的算法就不适用
     *
     * 解决思路: 只需要记住0出现的位置
     *  初始化zeroIndex 用于保存0的索引，初始值为-1，代表第一个窗口里
     *  遇到第一个0时， zeroIndex还是-1，说明是第一个0，那么先不管
     *  当right 再次遇到0，此时zeroIndex 大于0，说明已经不是第一个0了，维护一下长度ans = right - left
     *  right 再次遇到0，left 直接跳到zerolndex后面1位，同时更新zerolndex为right的值, 即新的0所处索引
     *
     * 这样只需要记住left 和 right 之间出现的0的索引，left 到 right 之间有多少元素完全不用管,解决了内存不够的问题
     */
    static public int findMaxConsecutiveOnes5(int[] nums) {
        int ans = 0;
        int left = 0, right = 0;
        int zeroIndex = -1; // 记录当前窗口中 0 出现的位置

        while (right < nums.length) {
            if (nums[right] == 0) {
                if (zeroIndex >= 0) { // 说明当前窗口已经有 0
                    ans = Math.max(ans, right - left);
                    left = zeroIndex + 1;
                }
                zeroIndex = right;
            }
            right++;
        }
        return Math.max(ans, right - left);
    }
    /**
     * 对于无限流 数据，只需记录前面0的位置即可。
     * Optimized Two Pointers (Track Last Zero Index)
     * Track the previous zero's index, and move the left pointer to one after the previous zero whenever a new zero is found.
     */
    static public int findMaxConsecutiveOnes6(int[] nums) {
        int maxLen = 0, left = 0, lastZero = -1;
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                left = lastZero + 1;
                lastZero = right;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
    /**
     * Approach 1: Brute Force
     */
    public int findMaxConsecutiveOnes_3(int[] nums) {
        int longestSequence = 0;
        for (int left = 0; left < nums.length; left++) {
            int numZeroes = 0;

            // check every consecutive sequence
            for (int right = left; right < nums.length; right++) {
                // count how many 0's
                if (nums[right] == 0) {
                    numZeroes += 1;
                }
                // # update answer if it's valid
                if (numZeroes <= 1) {
                    longestSequence = Math.max(longestSequence, right - left + 1);
                }
            }
        }
        return longestSequence;
    }
}
