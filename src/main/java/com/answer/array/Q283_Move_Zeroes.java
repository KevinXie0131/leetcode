package com.answer.array;

import java.util.Arrays;

public class Q283_Move_Zeroes {

    public static void main(String[] args) {
    //    int[] nums = {0,1,0,3,12};
        int[] nums = {4,0,0,3,2,5,0,4};
        moveZeroes_3(nums);
        System.out.println(Arrays.toString(nums));

    }
    public static void moveZeroes(int[] nums) {
        int n = nums.length;
        int slow = 0, fast = 1;

        while(slow < n && fast < n){
            if(nums[slow] == 0){
                while(nums[fast] == 0){
                    fast++;
                    if(fast > n - 1){
                        return;
                    }
                }
                swap(nums, slow, fast);
                slow++;
            } else {
                slow++;
            }
        }
    }

    public static void swap(int[] nums, int left ,int right){
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
    /**
     * Two pass
     */
    public static void moveZeroes_1(int[] nums) {
        if(nums==null) {
            return;
        }
        //第一次遍历的时候，j指针记录非0的个数，只要是非0的统统都赋给nums[j]
        int j = 0;
        for(int i=0;i<nums.length;++i) {
            if(nums[i]!=0) {
                nums[j++] = nums[i];
            }
        }
        //非0元素统计完了，剩下的都是0了
        //所以第二次遍历把末尾的元素都赋为0即可
        for(int i=j;i<nums.length;++i) {
            nums[i] = 0;
        }
    }
    /**
     * One pass
     */
    public static void moveZeroes_2(int[] nums) {

        int j = 0;
        for(int i = 0; i< nums.length; i++){
            if(nums[i] != 0){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j++] = temp;
            }
        }
    }

    /**
     * Official answer
     */
    public static void moveZeroes_3(int[] nums) {
        int n = nums.length, left = 0, right = 0;
        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }
}
