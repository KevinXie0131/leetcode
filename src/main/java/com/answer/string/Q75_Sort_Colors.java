package com.answer.string;

import java.util.Arrays;

public class Q75_Sort_Colors {
    /**
     * one-pass algorithm using only constant extra space 原地in place
     */
    public static void main(String[] args) {
       int[] nums = {1,0,2,0,2,1,1,0};
       sortColors_3(nums);

       System.out.println(Arrays.toString(nums));
    }

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
     * Two Pointers
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
}
