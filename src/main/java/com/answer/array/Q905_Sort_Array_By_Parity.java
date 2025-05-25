package com.answer.array;

public class Q905_Sort_Array_By_Parity {
    /**
     * 按奇偶排序数组: 给你一个整数数组 nums，将 nums 中的的所有偶数元素移动到数组的前面，后跟所有奇数元素。
     * 返回满足此条件的 任一数组 作为答案。
     * 示例 1：
     *  输入：nums = [3,1,2,4]
     *  输出：[2,4,3,1]
     */
    /**
     * Sort Array By Parity 按奇偶排序数组
     *  move all the even integers at the beginning of the array followed by all the odd integers.
     * 将 nums 中的的所有偶数元素移动到数组的前面，后跟所有奇数元素。
     *
     * 找到最左边的奇数 nums[i] 和最右边的偶数 nums[j]，交换这两个数。
     * 交换后，下标在 [0,i] 中的数都是偶数，下标在 [j,n−1] 中的数都是奇数。于是问题变成下标在 [i+1,j−1] 中的数如何交换，
     * 这是个规模更小的子问题，处理方式同上。
     * 重复该过程，直到下标范围不足两个数，此时没有元素需要交换
     */
    public int[] sortArrayByParity(int[] nums) { // 双指针 + 一次遍历 + 原地交换
        int left = 0, right = nums.length - 1;
        while(left < right){ // 循环直到不足两个数
            if(nums[left] % 2 == 0){  // 寻找最左边的奇数
                left++;
            }
            else if(nums[right] % 2 == 1){ // 寻找最右边的偶数
               right--;
            }
            else {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                // 交换后，问题变成 [i+1,j-1] 的子问题
                left++;
                right--;
            }
        }

        return nums;
    }
    /**
     * Another form
     */
    public int[] sortArrayByParity_1(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            while (left < right && nums[left] % 2 == 0) {
                left++;
            }
            while (left < right && nums[right] % 2 == 1) {
                right--;
            }
            if (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
        }
        return nums;
    }
}
