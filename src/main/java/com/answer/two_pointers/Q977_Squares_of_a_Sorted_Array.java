package com.answer.two_pointers;

import java.util.Arrays;

public class Q977_Squares_of_a_Sorted_Array {
    /**
     * Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number
     * sorted in non-decreasing order.
     * 一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
     * nums is sorted in non-decreasing order. nums 已按 非递减顺序 排序
     * Follow up: Squaring each element and sorting the new array is very trivial, could you find an O(n)
     * solution using a different approach?
     * 进阶：请你设计时间复杂度为 O(n) 的算法解决本问题
     */
    public static void main(String[] args) {
        /**
         * 示例 1：
         *  输入：nums = [-4,-1,0,3,10]
         *  输出：[0,1,9,16,100]
         *  解释：平方后，数组变为 [16,1,0,9,100] 排序后，数组变为 [0,1,9,16,100]
         */
        int[] nums = {-4,-1,0,3,10};
        int[] res = sortedSquares_0(nums);
        System.out.println(Arrays.toString(res));
    }
    /**
     * 双指针
     */
   static public int[] sortedSquares(int[] nums) {
        int len = nums.length;
        int[] result = new int[len];

        for(int left = 0, right = len - 1, index = len - 1; left <= right;){
            if(nums[left] * nums[left] < nums[right] * nums[right]){
                result[index] = nums[right] * nums[right];
                right--;
            } else{
                result[index] = nums[left] * nums[left];
                left++;
            }
            index--;
        }

        return result;
    }
    /**
     * 双指针
     * 可以使用两个指针分别指向位置 0 和 n−1，每次比较两个指针对应的数，选择较大的那个逆序放入答案并移动指针。
     * 这种方法无需处理某一指针移动至边界的情况
     */
    static public int[] sortedSquares_0(int[] nums) {
        int left = 0, right = nums.length - 1;
        int[] result = new int[nums.length];
        int index = result.length - 1;

        while (left <= right) {
            if (nums[left] * nums[left] > nums[right] * nums[right]) {
                result[index] = nums[left] * nums[left];     // 正数的相对位置是不变的， 需要调整的是负数平方后的相对位置
                left++;
            } else {
                result[index] = nums[right] * nums[right];
                right--;
            }
            index--;
        }
        return result;
    }
    /**
     * anther form
     */
    public int[] sortedSquares1(int[] nums) {
        int[] res = new int[nums.length];
        int index = res.length- 1;

        int left = 0, right = nums.length - 1;
        while(left <= right){
            if(Math.abs(nums[left]) < Math.abs(nums[right])){
                res[index--] = nums[right] * nums[right];
                right--;
            } else {
                res[index--] = nums[left] * nums[left];
                left++;
            }
        }
        return res;
    }
    /**
     * 双指针，两头往中间遍历，每次对比两边绝对值大小，挑出大的放result数组(从右往左)
     */
    public int[] sortedSquares5(int[] nums) { // 非递减顺序 排序的整数数组
        int n = nums.length;
        int[] res = new int[n];
        // 最左元素和最右元素的下标。
        int l = 0, r = n - 1;
        for(int i = n - 1; i >= 0; i--) {
            if(nums[l] + nums[r] > 0) {
                res[i] = nums[r] * nums[r];
                r--;
            } else {
                res[i] = nums[l] * nums[l];
                l++;
            }
        }
        return res;
    }
    /**
     * 最简单的方法就是将数组 nums 中的数平方后直接排序。
     * 时间复杂度：O(nlogn)，其中 n 是数组 nums 的长度。
     * 空间复杂度：O(logn)。除了存储答案的数组以外，我们需要 O(logn) 的栈空间进行排序
     */
    public int[] sortedSquares4(int[] nums) {
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            ans[i] = nums[i] * nums[i];
        }
        Arrays.sort(ans);
        return ans;
    }
}
