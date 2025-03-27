package com.answer.array;

import java.util.Arrays;

public class Q189_Rotate_Array {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7};
        int k = 3;
        rotate_1(nums, k );
        System.out.println(Arrays.toString(nums));
    }
    /**
     * rotate the array to the right by k steps
     * Follow up:
     *   Try to come up with as many solutions as you can. There are at least three different ways to solve this problem.
     *   Could you do it in-place with O(1) extra space?
     */
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int[] newArr = new int[n];

        for(int i = 0; i < n; i++){
            newArr[(i + k) % n] = nums[i];
        }
        System.arraycopy(newArr, 0, nums, 0, n);
    }
    /**
     * 三次反转
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     */
    static public void rotate_1(int[] nums, int k) {
        k %= nums.length;

        reverseArray(nums, 0, nums.length - 1); // 整体反转
        reverseArray(nums, 0, k - 1);           // 反转前半部分
        reverseArray(nums, k, nums.length - 1);    // 反转后半部分
    }

    static void reverseArray (int[] nums, int i, int j) {
        while(i <= j){
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }
}
