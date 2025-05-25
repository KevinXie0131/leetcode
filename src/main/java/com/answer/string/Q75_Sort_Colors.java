package com.answer.string;

import java.util.Arrays;

public class Q75_Sort_Colors {
    /**
     * Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects
     * of the same color are adjacent, with the colors in the order red, white, and blue.
     * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
     * You must solve this problem without using the library's sort function.
     * Follow up: Could you come up with a one-pass algorithm using only constant extra space?
     * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地(in place)对它们进行排序，使得相同颜色的元素相邻，
     * 并按照红色、白色、蓝色顺序排列。
     * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
     * 必须在不使用库内置的 sort 函数的情况下解决这个问题。
     * 进阶：你能想出一个仅使用常数空间的一趟扫描算法吗？
     */
    public static void main(String[] args) {
       int[] nums = {1,0,2,0,2,1,1,0};
        sortColors_5(nums);

       System.out.println(Arrays.toString(nums));
    }
    /**
     * 单指针 两次遍历
     */
    public void sortColors(int[] nums) {
        int n = nums.length;
        int index = 0;

        for(int i = 0; i < n; i++){
            if(nums[i] == 0){
                int temp = nums[i];
                nums[i] = nums[index];
                nums[index] = temp;
                index++;
            }
        }
        for(int i = index; i < n; i++){
            if(nums[i] == 1){
                int temp = nums[i];
                nums[i] = nums[index];
                nums[index] = temp;
                index++;
            }
        }
    }
    /**
     * Two Pointers
     * one-pass algorithm using only constant extra space 原地in place
     */
    public static void sortColors_1(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return;
        }

        int p = 0, q = n - 1;
        for (int i = 0; i <= q; ++i) {
            if (nums[i] == 0) {
                nums[i] = nums[p];
                nums[p] = 0;
                ++p;
            }
            if (nums[i] == 2) {
                nums[i] = nums[q];
                nums[q] = 2;
                --q;
                if (nums[i] != 1) {
                    --i;
                }
            }
        }
        return;
    }
    /**
     * Two Pointers 双指针
     */
    public static void sortColors_2(int[] nums) {
        int n = nums.length;
        int p0 = 0, p2 = n - 1;
        for (int i = 0; i <= p2; ++i) {
            while (i <= p2 && nums[i] == 2) {
                int temp = nums[i];
                nums[i] = nums[p2];
                nums[p2] = temp;
                --p2;
            }
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;
                ++p0;
            }
        }
    }
    /**
     * Three Pointers
     * 使用三指针 i、j、k，要保证 [0, i - 1] 部分全部是 0；[i, j - 1] 部分全部是 1；[k + 1，n - 1] 部分全部是 2；
     * 我们使用 j 这个指针对数组遍历，那么当 j > k 时就完成了对数组的排序。
     *
     * i, j, k
     * 0: start -> i - 1
     * 1: i - > j
     * 2: k + 1 -> end
     */
    public static void sortColors_3(int[] nums) {
        int n = nums.length;
        int zero = 0;
        int one = 0;
        int two = n - 1;

        while (one <= two) {
            if (nums[one] == 0) {
                swap(nums, zero, one);
                zero++;
                one++;
            } else if (nums[one] == 1) {
                one++;
            } else { // =2
                swap(nums, one, two);
                two--;
            }
        }
    }
    static void  swap(int[] nums, int i, int j) {
        int c = nums[i];
        nums[i] = nums[j];
        nums[j] = c;
    }
    /**
     * O(1) 插入元素, 不是插入元素，而是修改元素
     * 先把 a[1] 改成 2，再把 a[1] 改成 1（覆盖），最后 a[0] 改成 0，得到 [0,1]。这种「覆盖」等价于「没有 2 的时候不改成 2」。
     * 如果插入的是 1 呢？跳过「把 a[p0] 改成 0」这一步。
     * 如果插入的是 2 呢？只需要把 a[i] 改成 2。
     */
    static public void sortColors_5(int[] nums) {
        int p0 = 0;
        int p1 = 0;
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            nums[i] = 2;
            if (x == 1 || x == 0) {
                nums[p1++] = 1;
            }
            if (x == 0) {
                nums[p0++] = 0;
            }
        }
    }
}
