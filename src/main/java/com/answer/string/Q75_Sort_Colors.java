package com.answer.string;

import java.util.Arrays;

public class Q75_Sort_Colors {
    public static void main(String[] args) {
       int[] nums = {1,0,2,0,2,1,1,0};
       sortColors_2(nums);

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
}
