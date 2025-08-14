package com.answer.sliding_window;

public class Q485_Max_Consecutive_Ones {
    /**
     * Given a binary array nums, return the maximum number of consecutive 1's in the array.
     * 最大连续 1 的个数: 给定一个二进制数组 nums ， 计算其中最大连续 1 的个数。
     * nums[i] is either 0 or 1.
     */
    public static void main(String[] args) {
        int[] nums = {1,1,0,1,1,1};
        int res = findMaxConsecutiveOnes3(nums); // 输出：3
        System.out.println(res);
    }
    /**
     * Brute force 一次遍历
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int maxCount = 0, count = 0;

        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 1){
                count++;  // 连续 1 的个数增加
            }else{
                maxCount = Math.max(count, maxCount);
                count = 0; // 重置
            }
        }
        maxCount = Math.max(count, maxCount);
        return maxCount;
    }
    /**
     * anther form
     */
    public int findMaxConsecutiveOnes1(int[] nums) {
        int ans = 0, count = 0;
        for (int num : nums) {
            if (num == 0) {
                count = 0; // 重置
            } else {
                count++; // 连续 1 的个数增加
                ans = Math.max(ans, count); // 更新答案的最大值
            }
        }
        return ans;
    }
    /**
     * Sliding window 滑动窗口
     * 将两个指针比作一个窗口，通过移动指针的位置改变窗口的大小，观察窗口中的元素是否符合题意。
     *  初始窗口中只有数组开头一个元素。
     *  当窗口中所有元素为 1 时，右指针向右移，扩大窗口。
     *  当窗口中存在 0 时，计算连续序列长度，左指针指向右指针。
     */
    public static int findMaxConsecutiveOnes_1(int[] nums) {
        int maxCount = 0, left = 0, right = 0;
        int length = nums.length;

        while(right < length){
            if(nums[right] == 0){   //当窗口中存在 0 时，计算连续序列长度，左指针指向右指针。
                maxCount = Math.max(maxCount, right - left);
                right++;
                left = right;
            } else {
                right++;   //当窗口中所有元素为 1 时，右指针向右移，扩大窗口。
            }
        }
        maxCount = Math.max(maxCount, right - left);   // 因为最后一次连续序列在循环中无法比较，所以在循环外进行比较
        return maxCount;
    }
    /**
     * 双指针
     * 使用 left 和 right 分别代表连续 1 的左右边界。
     * 起始状态 left == right，当 left 到达第一个 1 的位置时，让 right 不断右移直到右边界。
     */
    static public int findMaxConsecutiveOnes3(int[] nums) {
        int n = nums.length;
        int ans = 0;

        for (int left = 0, right = 0; left < n; left++) {
            if (nums[left] == 1) {
                right = left;
                while (right + 1 < n && nums[right + 1] == 1) {
                    right++;
                }

                ans = Math.max(ans, right - left + 1);
                left = right;
            }
        }
        return ans;
    }
    /**
     * 动态规划
     * P[i] = P[i - 1] + 1 if nums[i] == 1
     * P[i] = 0 if nums[i] == 0
     */
    public static int findMaxConsecutiveOnes_3(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }
        int maxOnes = 0;
        int length = nums.length;

        if(nums[0] == 1) maxOnes = 1; // 初始值 例如[1, 0]

        for(int i = 1; i < length; i++){
            if(nums[i] == 1){
                nums[i] = nums[i - 1] + 1;
            }else{
                nums[i] = 0;
            }
            maxOnes = Math.max(maxOnes, nums[i]);
        }
        return maxOnes;
    }
}
