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
     * rotate the array to the right by k steps 将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
     * Follow up:
     *   Try to come up with as many solutions as you can. There are at least three different ways to solve this problem.
     *   Could you do it in-place with O(1) extra space?
     * 进阶：
     *   尽可能想出更多的解决方案，至少有 三种 不同的方法可以解决这个问题。
     *   你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？
     */
    public void rotate(int[] nums, int k) { // 使用额外的数组
        int n = nums.length;
        int[] newArr = new int[n];

        for(int i = 0; i < n; i++){ // 将原数组下标为 i 的元素放至新数组下标为 (i+k) mod n 的位置
            newArr[(i + k) % n] = nums[i];
        }
        System.arraycopy(newArr, 0, nums, 0, n); // 最后将新数组拷贝至原数组即可
    }
    /**
     * 三次反转
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     */
    static public void rotate_1(int[] nums, int k) { // 数组翻转
        k %= nums.length;
        // 可以先将所有元素翻转，这样尾部的 k mod n 个元素就被移至数组头部，然后我们再翻转 [0,k mod n−1] 区间的元素
        // 和 [k mod n,n−1] 区间的元素即能得到最后的答案
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
